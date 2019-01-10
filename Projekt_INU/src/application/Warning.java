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

public class Warning {
	static void showWarn() {
		Image imageWarn = new Image(ClassLoader.getSystemResourceAsStream("application/images/StatusWarning_64x.png"));
		ImageView imageViewWarn = new ImageView(imageWarn);

		Stage stageWarn = new Stage();
		VBox rootWarn = new VBox();
		rootWarn.setPadding(new Insets(10));
		rootWarn.setSpacing(20);
		HBox hboxWarn = new HBox(30);
		hboxWarn.setPadding(new Insets(10));
		hboxWarn.setSpacing(20);
		HBox hbox1Warn = new HBox();
		hbox1Warn.setPadding(new Insets(10));
		hbox1Warn.setSpacing(20);
		hbox1Warn.setAlignment(Pos.CENTER);
		Scene sceneWarn = new Scene(rootWarn, 500, 200);
		stageWarn.setTitle("Ostrze¿enie");
		stageWarn.setScene(sceneWarn);
		stageWarn.setAlwaysOnTop(true);
		stageWarn.centerOnScreen();
		stageWarn.setResizable(false);
		stageWarn.initModality(Modality.APPLICATION_MODAL);

		Button buttonWarn = new Button("OK");
		Label labelWarn = new Label("To jest okienko ostrzegawcze!");
		MessageBoxButton.AbortRetryIgnore.getCount();

		rootWarn.getChildren().addAll(hboxWarn, hbox1Warn);
		hboxWarn.getChildren().add(imageViewWarn);
		hboxWarn.getChildren().add(labelWarn);
		hbox1Warn.getChildren().add(buttonWarn);

		stageWarn.showAndWait();

	}
}
