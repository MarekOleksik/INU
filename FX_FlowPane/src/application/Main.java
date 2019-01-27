package application;

import java.util.Arrays;
import java.util.Iterator;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class Main extends Application {

	

	@Override
	public void start(Stage primaryStage) {
		for (int i = 1; i < 10; i++) {
			Button btn = new Button (String.valueOf(i));
			btn.setPrefWidth(100);


		}
		//margin, border i padding na teście 
		
		FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL, 10, 10);

		flowPane.setPadding(new Insets(20, 20, 20, 20));
		flowPane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(flowPane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Pierwsza aplikacja");
		primaryStage.centerOnScreen();
		primaryStage.show();
	}

	private Object btn_Click() {
		// TODO Auto-generated method stub
		return null;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
