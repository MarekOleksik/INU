package application;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		for(AlertType type : AlertType.values()) {
				if (type == AlertType.NONE) 
				continue;
				
				// okienko komunikatu z nagłówkiem
				Alert alert	= new Alert(type);
				alert.setTitle (type.name());
				alert.setHeaderText	("Nagłówek");
				alert.setContentText("Treść.");
				Optional<ButtonType> result = alert.showAndWait();
				result.ifPresent(res->System.out.println(res.getText()));
				// okienko komunikatu bez nagłówka
				alert.setHeaderText(null);
				alert.showAndWait().ifPresent(res->System.out.println(res.getText()));
		}
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
