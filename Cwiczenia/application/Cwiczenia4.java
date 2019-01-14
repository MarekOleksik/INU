package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Cwiczenia4 extends Application{

	public static void main(String[] args) {
	launch(args);

	}

	@Override
	public void start(Stage primaryStage){
		Button btnNew = new Button("Nowe okno");
		
		btnNew.setOnAction(e->btnNew_Click());
		
		BorderPane borderPane = new BorderPane();
		borderPane.setCenter(btnNew);
		Scene scene = new Scene(borderPane, 400, 200);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Lambda Expression");
		primaryStage.show();
	}
	private void btnNew_Click(){
		Stage newStage = new Stage();
		
		newStage.setWidth(300);
		newStage.setHeight(200);
		newStage.setTitle("Nowe okno");
		newStage.setAlwaysOnTop(true);
		newStage.centerOnScreen();
		newStage.setResizable(false);
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.showAndWait();
	}
}
