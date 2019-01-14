package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Main extends Application {

	private Label label;
	//Text lblMsg = new Text();
	//lblMsg.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
	//lblMsg.setFont(Font.font(12));
	
	


	@Override
	public void start(Stage stage) {

		VBox root = new VBox();
		HBox hbox = new HBox();
		root.setPadding(new Insets(10));
		root.setSpacing(50);
		root.setAlignment(Pos.CENTER);
		hbox.setPadding(new Insets(10));
		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER);

		this.label = new Label();

		Button button = new Button("Informacja");
		Button button1 = new Button("Ostrze�enie");
		Button button2 = new Button("Alarm");
		Button button3 = new Button("B��d krytyczny");

		button.setOnAction(e -> Information.showInfo());
		button1.setOnAction(e -> Warning.showWarn());
		button2.setOnAction(e -> Alert.showAlert());
		button3.setOnAction(e -> CriticalError.showCriticalError());

		root.getChildren().addAll(hbox, label);
		hbox.getChildren().addAll(button, button1, button2, button3);

		Scene scene = new Scene(root, 640, 480);
		stage.setTitle("Praca zaliczeniowa INU");
		stage.setScene(scene);
		stage.centerOnScreen();

		stage.show();

		MessageBoxButton.AbortRetryIgnore.getCount();
	}

	public static void main(String args[]) {
		launch(args);
	}

}