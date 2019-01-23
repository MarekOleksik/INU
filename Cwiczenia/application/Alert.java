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

public class Alert {
	static void showAlert() {
		Image imageAlert = new Image(ClassLoader.getSystemResourceAsStream("application/images/StatusAlert_64x.png"));
		ImageView imageViewAlert = new ImageView(imageAlert);

		Stage stageAlert = new Stage();
		VBox rootAlert = new VBox();
		rootAlert.setPadding(new Insets(10));
		rootAlert.setSpacing(20);
		HBox hboxAlert = new HBox(30);
		hboxAlert.setPadding(new Insets(10));
		hboxAlert.setSpacing(20);
		HBox hbox1Alert = new HBox();
		hbox1Alert.setPadding(new Insets(10));
		hbox1Alert.setSpacing(20);
		hbox1Alert.setAlignment(Pos.CENTER);
		Scene sceneAlert = new Scene(rootAlert, 500, 200);
		stageAlert.setTitle("Alarm");
		stageAlert.setScene(sceneAlert);
		stageAlert.setAlwaysOnTop(true);
		stageAlert.centerOnScreen();
		stageAlert.setResizable(false);
		stageAlert.initModality(Modality.APPLICATION_MODAL);

		Button buttonAlert = new Button("OK");
		Label labelAlert = new Label("To jest okienko alarmu!");
		

		rootAlert.getChildren().addAll(hboxAlert, hbox1Alert);
		hboxAlert.getChildren().add(imageViewAlert);
		hboxAlert.getChildren().add(labelAlert);
		hbox1Alert.getChildren().add(buttonAlert);

		stageAlert.showAndWait();

	}
}