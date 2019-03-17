package application;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class FloodControl {

	private Stage stage;
	private Canvas canvas;
	private GraphicsContext graphicsContext;
	private long startNanoTime;

	private Image playingPieces;
	private Image backgroundScreen;
	private Image titleScreen;
	private UserInputQueue userInputQueue;
	private GameAnimationTimer animationTimer;
	private GameBoard gameBoard;
	Point2D boardOrigin;
	private int playerScore = 0;

	private enum State {
		TitleScreen, Playing
	};

	private State state;

	private class GameAnimationTimer extends AnimationTimer {
		@Override
		public void handle(long currentNanoTime) {
			update(currentNanoTime);
			draw(currentNanoTime);
		}
	}

	public FloodControl(Stage primaryStage) {
		stage = primaryStage;
		stage.setTitle("Flood Control");
		stage.getIcons().add(new Image("/application/content/icons/Game.png"));
		startNanoTime = System.nanoTime(); // czas rozpoczęcia gry
	}

	public void run() {
		initialize();
		loadContent();
		stage.show();
		animationTimer = new GameAnimationTimer();
		animationTimer.start();
	}

	private void initialize() {
		Group root = new Group();
		canvas = new Canvas(800, 600);
		root.getChildren().add(canvas);

		graphicsContext = canvas.getGraphicsContext2D();

		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setResizable(false);
		stage.sizeToScene();
		stage.setOnCloseRequest(e -> stage_CloseRequest(e));
		state = State.TitleScreen;

		userInputQueue = new UserInputQueue();
		scene.setOnKeyPressed(keyEvent -> userInputQueue.addKey(keyEvent));
		scene.setOnMouseClicked(mouseEvent -> userInputQueue.addMouse(mouseEvent));
		gameBoard = new GameBoard();
		boardOrigin = new Point2D(70, 89);
	}

	private void stage_CloseRequest(WindowEvent windowEvent) {
		windowEvent.consume();

		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				if (AlertBox.showAndWait(AlertType.CONFIRMATION, "Flood Control", "Do you want to stop the game?")
						.orElse(ButtonType.CANCEL) == ButtonType.OK) {
					animationTimer.stop();
					unloadContent();
					stage.close();
				}
			}
		});
	}

	private void loadContent() {
		playingPieces = new Image("/application/content/textures/Tile_Sheet.png");
		backgroundScreen = new Image("/application/content/textures/Background.png");
		titleScreen = new Image("/application/content/textures/TitleScreen.png");
	}

	private void unloadContent() {
	}

	private void update(long currentNanoTime) {
		KeyCode keyCode = userInputQueue.getKeyCode();
		switch (state) {
		case TitleScreen:
			if (keyCode == KeyCode.SPACE) {
				state = State.Playing;
				gameBoard.generateNewPieces();
			} else if (keyCode == KeyCode.ESCAPE) {
				stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
			}
			break;
		case Playing:
			if (keyCode == KeyCode.ESCAPE) {
				stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
			}
			
			gameBoard.resetWater();
			gameBoard.generateNewPieces();
			for (int y = 0; y < GameBoard.GAME_BOARD_HEIGHT; y++) {
				checkScoringChain(gameBoard.getWaterChain(y));
			}
			gameBoard.updateFadingPieces();
			handleMouseInput();
			break;
			
			//gameBoard.generateNewPieces();
			//gameBoard.resetWater();
			//for (int x = 0; x < 10; x++) {
			//	gameBoard.getWaterChain(x);
			//}
			//gameBoard.generateNewPieces();
			//for (int y = 0; y < GameBoard.GAME_BOARD_HEIGHT; y++)
			//	checkScoringChain(gameBoard.getWaterChain(y));
			//gameBoard.updateFadingPieces();
			//break;
		}
	}

	private void draw(long currentNanoTime) {
		graphicsContext.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		switch (state) {
		case TitleScreen:
			graphicsContext.drawImage(titleScreen, 0, 0);
			break;
		case Playing:
			graphicsContext.drawImage(backgroundScreen, 0, 0);

			PixelReader pixReader = playingPieces.getPixelReader();
			WritableImage emptyPiece = new WritableImage(pixReader, 1, 247, GamePiece.pieceWidth,
					GamePiece.pieceHeight);

			drawPieces(pixReader, emptyPiece);
			handleMouseInput();

			break;
		}
	}

	private void drawPieces(PixelReader pixReader, WritableImage emptyPiece) {
		for (int x = 0; x < GameBoard.GAME_BOARD_WIDTH; x++) {
			for (int y = 0; y < GameBoard.GAME_BOARD_HEIGHT; y++) {
				int pixelX = (int) boardOrigin.getX() + (x * GamePiece.pieceWidth);
				int pixelY = (int) boardOrigin.getY() + (y * GamePiece.pieceHeight);

				graphicsContext.drawImage(emptyPiece, pixelX, pixelY);

				String positionName = x + " " + y;
				if (gameBoard.fadingPieces.containsKey(positionName)) {
					drawFadingPiece(pixelX, pixelY, positionName);
				} // kolor czerwony, nowy fragment kodu
				else {
					Rectangle2D rect = gameBoard.getSourceRect(x, y);
					WritableImage thePiece = new WritableImage(pixReader, (int) rect.getMinX(), (int) rect.getMinY(),
							GamePiece.pieceWidth, GamePiece.pieceHeight);
					graphicsContext.drawImage(thePiece, pixelX, pixelY);
				}
			}
		}
	}

	private void handleMouseInput() {
		MouseEvent mouseState = userInputQueue.getMouse();
		if (mouseState == null)
			return;
		// przeksztalcenie współ. kursora myszy do współ. planszy gry
		int x = (int) (mouseState.getSceneX() - (int) boardOrigin.getX()) / GamePiece.pieceWidth;
		int y = (int) (mouseState.getSceneY() - (int) boardOrigin.getY()) / GamePiece.pieceHeight;
		// jeśli x, y mieszczą się w zakresie współ. planszy gry, to
		if ((x >= 0) && (x < GameBoard.GAME_BOARD_WIDTH) && (y >= 0) && (y < GameBoard.GAME_BOARD_HEIGHT)) {
			// jeśli lewy klawisz, to kręć kawałek w lewo
			if (mouseState.getButton() == MouseButton.PRIMARY) {
				gameBoard.addRotatingPiece(x, y, gameBoard.getPieceType(x, y), false);
				gameBoard.rotatePiece(x, y, false);
			} else if (mouseState.getButton() == MouseButton.SECONDARY) {
				gameBoard.addRotatingPiece(x, y, gameBoard.getPieceType(x, y), true);
				gameBoard.rotatePiece(x, y, true);
			}
		}
	}

	private void drawFadingPiece(int pixelX, int pixelY,
			String positionName) {
			
		
			Rectangle2D rect = 	gameBoard.fadingPieces.get(positionName).getSourceRect();
			PixelReader pixReader = null;
			//WritableImage thePiece = new WritableImage(pixReader, (int) rect.getMinX(), . . . ,	GamePiece.pieceWidth, . . . );
			// zapamiętaj alfa, zmień, narysuj, przywróć poprzednia wartość
			double currAlpha = graphicsContext.getGlobalAlpha();

			//graphicsContext.setGlobalAlpha(gameBoard.fadingPieces.get(positionName). . . .);
			//graphicsContext.drawImage(thePiece, pixelX, pixelY);
			//graphicsContext.setGlobalAlpha(currAlpha);
	}

	private void checkScoringChain(ArrayList<Point2D> waterChain) {
		if (waterChain.size() > 0) {
			// pobiera współ. ostatniego kafelka rurociagu na planszy gry
			Point2D lastPipe = waterChain.get(waterChain.size() - 1);
			// jeśli współ. X kafelka jest równa ostatniej współrzędnej
			// X planszy gry, to...
			if (lastPipe.getX() == GameBoard.GAME_BOARD_WIDTH - 1) {

				// jeśli ostatni kafelek ma przyłącze skierowana w prawo...
				if (gameBoard.hasConnector((int) lastPipe.getX(), (int) lastPipe.getY(), "Right")) {
					// rurociąg przeprowadza wodę z lewej strony na prawą
					// zatem należy zaktualizować wynik gracza
					
					playerScore += determineScore(waterChain.size());
					for (Point2D scoringSquare : waterChain) {
						// przed ustawieniem kafelka na "Empty",
						// dodać kafelek do listy "znikających" kafelków
						gameBoard.addFadingPiece((int) scoringSquare.getX(), (int) scoringSquare.getY(),
								gameBoard.getPieceType((int) scoringSquare.getX(), (int) scoringSquare.getY()));
						// usunąć z planszy poszczególne kafelki rurociągu
						gameBoard.setPiece((int) scoringSquare.getX(), (int) scoringSquare.getY(), "Empty");
					}
				}
			}
		}
	}

	private int determineScore(int squareCount) {
		return squareCount;
	}

}