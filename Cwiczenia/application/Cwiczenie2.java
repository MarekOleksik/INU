package application;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Cwiczenie2 extends Application{

	public static void main(String[] args) {
	launch(args);
	}

	class ClickHandler implements EventHandler<ActionEvent>{
		@Override
		public void handle(ActionEvent e){
			if (e.getSource()==btnDir){
				DirectoryChooser dirChooser = new DirectoryChooser();
				File selFile = dirChooser.showDialog(primaryStage);
				try {
					if (selFile != null)
						lblDir.setText(selFile.getCanonicalPath());
					else
						lblDir.setText("");
				}
				catch (IOException ex) {
					lblDir.setText("B³¹d wyboru katalogu");
				}
			}
		}
	}
	
	Button btnDir = new Button("Katalog...");
	Label lblDir = new Label("");
	Stage primaryStage;
	@Override
	public void start(Stage primaryStage){
		this.primaryStage = primaryStage;
		btnDir.setOnAction(new ClickHandler());
		
		FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL, 10, 10, btnDir, lblDir);
		flowPane.setAlignment(Pos.CENTER);
		flowPane.setPadding(new Insets(20));
		Scene scene = new Scene(flowPane, 600, 300);
		primaryStage.setTitle("Inner class");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
}
