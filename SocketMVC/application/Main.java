package application;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

	public static final String appName = "Sockets-JavaFX-MVC";

	@Override
	public void start(Stage primaryStage) throws UnknownHostException, IOException {

		ViewLoader<AnchorPane, ChatController> viewLoader = new ViewLoader<>("Chat.fxml");
		viewLoader.getController().setUserName(getUserName());
		viewLoader.getController().setHost("localhost");
		viewLoader.getController().run();
		Scene scene = new Scene(viewLoader.getLayout());
		primaryStage.setScene(scene);
		primaryStage.setTitle(appName);
		primaryStage.setOnHiding(e -> primaryStage_Hiding(e, viewLoader.getController()));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	private String getUserName() {
		TextInputDialog textInputDialog = new TextInputDialog("Anonymous");
		textInputDialog.setTitle("Tytuł");
		textInputDialog.setHeaderText("Podaj imię");
		textInputDialog.setContentText("Zaloguj się");
		Optional<String> result = textInputDialog.showAndWait();
		return result.orElse("Anonymous");
	}

	private void primaryStage_Hiding(WindowEvent e, ChatController controller) {
		controller.closeSocket();
	}

}
