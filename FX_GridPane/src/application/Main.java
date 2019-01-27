package application;

import javafx.application.Application;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		CourseStage courseStage = new CourseStage();
		courseStage.initModality(Modality.APPLICATION_MODAL);
		courseStage.initializeGrid();
		courseStage.initializeCourse();
		courseStage.initializeInstitutes();
		courseStage.initializeStudies();
		courseStage.initializeButtons();
		courseStage.showAndWait();
	    

	}

	public static void main(String[] args) {
		launch(args);
	}
}
