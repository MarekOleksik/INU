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

public class Information {
	static void showInfo() {
		Image imageInfo = new Image(
				ClassLoader.getSystemResourceAsStream("application/images/StatusInformation_64x.png"));
		ImageView imageViewInfo = new ImageView(imageInfo);

		Stage stageInfo = new Stage();
		VBox rootInfo = new VBox();
		rootInfo.setPadding(new Insets(10));
		rootInfo.setSpacing(20);
		HBox hboxInfo = new HBox(30);
		hboxInfo.setPadding(new Insets(10));
		hboxInfo.setSpacing(20);
		HBox hbox1Info = new HBox();
		hbox1Info.setPadding(new Insets(10));
		hbox1Info.setSpacing(20);
		hbox1Info.setAlignment(Pos.CENTER);
		Scene sceneInfo = new Scene(rootInfo, 500, 200);
		stageInfo.setTitle("Informacja");
		stageInfo.setScene(sceneInfo);
		stageInfo.setAlwaysOnTop(true);
		stageInfo.centerOnScreen();
		stageInfo.setResizable(false);
		stageInfo.initModality(Modality.APPLICATION_MODAL);

		Button buttonInfo = new Button("OK");
		Label labelInfo = new Label("To jest okienko informacyjne");

		rootInfo.getChildren().addAll(hboxInfo, hbox1Info);
		hboxInfo.getChildren().add(imageViewInfo);
		hboxInfo.getChildren().add(labelInfo);
		hbox1Info.getChildren().add(buttonInfo);

		stageInfo.showAndWait();

	}
}

