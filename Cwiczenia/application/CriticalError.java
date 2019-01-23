package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CriticalError {

	static void showCriticalError() {
		Image imageCriticalError = new Image(ClassLoader.getSystemResourceAsStream("application/images/StatusCriticalError_64x.png"));
		ImageView imageViewCriticalError = new ImageView(imageCriticalError);

		Stage stageCriticalError = new Stage();
		VBox rootCriticalError = new VBox();
		rootCriticalError.setPadding(new Insets(10));
		rootCriticalError.setSpacing(20);
		HBox hboxCriticalError = new HBox(30);
		hboxCriticalError.setPadding(new Insets(10));
		hboxCriticalError.setSpacing(20);
		HBox hbox1CriticalError = new HBox();
		hbox1CriticalError.setPadding(new Insets(10));
		hbox1CriticalError.setSpacing(20);
		hbox1CriticalError.setAlignment(Pos.CENTER);
		Scene sceneCriticalError = new Scene(rootCriticalError, 500, 200);
		stageCriticalError.setTitle("B³¹d krytyczny");
		stageCriticalError.setScene(sceneCriticalError);
		stageCriticalError.setAlwaysOnTop(true);
		stageCriticalError.centerOnScreen();
		stageCriticalError.setResizable(false);
		stageCriticalError.initModality(Modality.APPLICATION_MODAL);

		Button buttonCriticalError = new Button("OK");
		Label labelCriticalError = new Label("To jest okienko b³êdu krytycznego!");
		
		

		rootCriticalError.getChildren().addAll(hboxCriticalError, hbox1CriticalError);
		hboxCriticalError.getChildren().add(imageViewCriticalError);
		hboxCriticalError.getChildren().add(labelCriticalError);
		hbox1CriticalError.getChildren().add(buttonCriticalError);

		stageCriticalError.showAndWait();

	}
}
