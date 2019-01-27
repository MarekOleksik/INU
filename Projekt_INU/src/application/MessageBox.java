package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class MessageBox extends Application {

	private static Button btnYes, btnNo, btnCancel, btnAbort, btnRetry, btnIgnore;
	private static BorderPane border;
	private static FlowPane flow;
	private static Stage newStage;
	private static Image imageViewYes = new Image(
			ClassLoader.getSystemResourceAsStream("application/images/StatusOK_32x.png"));
	private static Image imageViewNo = new Image(
			ClassLoader.getSystemResourceAsStream("application/images/StatusNo_32xLG.png"));

	public static MessageBoxResult show(String labelMsg, String title, MessageBoxButton buttons, MessageBoxIcon icon) {

		newStage = new Stage();
		newStage.setWidth(400);
		newStage.setHeight(180);
		newStage.setTitle(title);
		newStage.setAlwaysOnTop(true);
		newStage.centerOnScreen();
		newStage.setResizable(false);
		newStage.initModality(Modality.APPLICATION_MODAL);
		newStage.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
				case ENTER:
					if (btnYes != null)
						btnYes.fire();
					break;
				case ESCAPE:
					if (btnNo != null)
						btnNo.fire();
					break;
				default:

				}
			}
		});
		border = new BorderPane();
		border.setPadding(new Insets(20, 30, 20, 30));

		setIcon(icon);
		Label textLabel = new Label(labelMsg);
		textLabel.setWrapText(true);
		border.setCenter(textLabel);
		textLabel.setPadding(new Insets(5, 5, 5, 5));
		BorderPane.setAlignment(textLabel, Pos.TOP_LEFT);
		setButtons(buttons);

		Scene scene = new Scene(border);
		newStage.setScene(scene);
		newStage.showAndWait();
		
		return null;
	}

	private static void setButtons(MessageBoxButton buttonSet) {

		if (buttonSet == null)
			buttonSet = MessageBoxButton.OK;

		switch (buttonSet) {

		case AbortRetryIgnore:
			btnAbort = new Button(MessageBoxButton.AbortRetryIgnore.getText(0));
			btnAbort.setPrefWidth(90.0);
			btnAbort.setPrefHeight(40.0);
			btnAbort.setDefaultButton(true);
			btnAbort.requestFocus();
			btnAbort.setOnAction(e -> newStage.close());
			btnRetry = new Button(MessageBoxButton.AbortRetryIgnore.getText(1));
			btnRetry.setPrefWidth(90.0);
			btnRetry.setPrefHeight(40.0);
			btnRetry.setOnAction(e -> newStage.close());
			btnIgnore = new Button(MessageBoxButton.AbortRetryIgnore.getText(2));
			btnIgnore.setPrefWidth(90.0);
			btnIgnore.setPrefHeight(40.0);
			btnIgnore.setOnAction(e -> newStage.close());
			flow = new FlowPane();
			flow.setAlignment(Pos.BOTTOM_RIGHT);
			flow.getChildren().add(btnAbort);
			flow.getChildren().add(btnRetry);
			flow.getChildren().add(btnIgnore);
			border.setBottom(flow);

			break;
		case OK:
		default:
			btnYes = new Button(MessageBoxButton.OK.getText(0));
			btnYes.setPrefWidth(90.0);
			btnYes.setPrefHeight(40.0);
			btnYes.setDefaultButton(true);
			btnYes.setGraphic(new ImageView(imageViewYes));
			btnYes.setGraphicTextGap(10);
			btnYes.requestFocus();
			btnYes.setOnAction(e -> newStage.close());
			flow = new FlowPane();
			flow.setAlignment(Pos.BOTTOM_RIGHT);
			flow.getChildren().add(btnYes);
			border.setBottom(flow);
			break;

		case OKCancel:
			btnYes = new Button(MessageBoxButton.OKCancel.getText(0));
			btnYes.setPrefWidth(90.0);
			btnYes.setPrefHeight(40.0);
			btnYes.setDefaultButton(true);
			btnYes.setGraphic(new ImageView(imageViewYes));
			btnYes.setGraphicTextGap(10);
			btnYes.requestFocus();
			btnYes.setOnAction(e -> newStage.close());
			btnCancel = new Button(MessageBoxButton.OKCancel.getText(1));
			btnCancel.setPrefWidth(90.0);
			btnCancel.setPrefHeight(40.0);
			btnCancel.setCancelButton(true);
			btnCancel.setOnAction(e -> newStage.close());
			flow = new FlowPane();
			flow.setAlignment(Pos.BOTTOM_RIGHT);
			flow.getChildren().add(btnYes);
			flow.getChildren().add(btnCancel);
			border.setBottom(flow);
			break;
		case RetryCancel:
			btnRetry = new Button(MessageBoxButton.RetryCancel.getText(0));
			btnRetry.setPrefWidth(90.0);
			btnRetry.setPrefHeight(40.0);
			btnRetry.setDefaultButton(true);
			btnRetry.requestFocus();
			btnRetry.setOnAction(e -> newStage.close());
			btnCancel = new Button(MessageBoxButton.RetryCancel.getText(1));
			btnCancel.setPrefWidth(90.0);
			btnCancel.setPrefHeight(40.0);
			btnCancel.setCancelButton(true);
			btnCancel.setOnAction(e -> newStage.close());
			flow = new FlowPane();
			flow.setAlignment(Pos.BOTTOM_RIGHT);
			flow.getChildren().add(btnRetry);
			flow.getChildren().add(btnCancel);
			border.setBottom(flow);
			break;
		case YesNo:
			btnYes = new Button(MessageBoxButton.YesNo.getText(0));
			btnYes.setPrefWidth(90.0);
			btnYes.setPrefHeight(40.0);
			btnYes.setDefaultButton(true);
			btnYes.setGraphic(new ImageView(imageViewYes));
			btnYes.setGraphicTextGap(10);
			btnYes.requestFocus();
			btnYes.setOnAction(e -> newStage.close());
			btnNo = new Button(MessageBoxButton.YesNo.getText(1));
			btnNo.setPrefWidth(90.0);
			btnNo.setPrefHeight(40.0);
			btnNo.setCancelButton(true);
			btnNo.setGraphic(new ImageView(imageViewNo));
			btnNo.setGraphicTextGap(10);
			btnNo.setOnAction(e -> newStage.close());
			flow = new FlowPane();
			flow.setAlignment(Pos.BOTTOM_RIGHT);
			flow.getChildren().add(btnYes);
			flow.getChildren().add(btnNo);
			border.setBottom(flow);
			break;
		case YesNoCancel:
			btnYes = new Button(MessageBoxButton.YesNoCancel.getText(0));
			btnYes.setPrefWidth(90.0);
			btnYes.setPrefHeight(40.0);
			btnYes.setDefaultButton(true);
			btnYes.setGraphic(new ImageView(imageViewYes));
			btnYes.setGraphicTextGap(10);
			btnYes.requestFocus();
			btnYes.setOnAction(e -> newStage.close());
			btnNo = new Button(MessageBoxButton.YesNoCancel.getText(1));
			btnNo.setPrefWidth(90.0);
			btnNo.setPrefHeight(40.0);
			btnNo.setCancelButton(true);
			btnNo.setGraphic(new ImageView(imageViewNo));
			btnNo.setGraphicTextGap(10);
			btnNo.setOnAction(e -> newStage.close());
			btnCancel = new Button(MessageBoxButton.YesNoCancel.getText(2));
			btnCancel.setPrefWidth(90.0);
			btnCancel.setPrefHeight(40.0);
			btnCancel.setCancelButton(true);
			btnCancel.setOnAction(e -> newStage.close());
			flow = new FlowPane();
			flow.setAlignment(Pos.BOTTOM_RIGHT);
			flow.getChildren().add(btnYes);
			flow.getChildren().add(btnNo);
			flow.getChildren().add(btnCancel);
			border.setBottom(flow);
			break;

		}
	}

	private static void setIcon(MessageBoxIcon iconSet) {
		if (iconSet == null)
			iconSet = MessageBoxIcon.Information;

		switch (iconSet) {

		case Information:
			Image imageInfo = new Image(ClassLoader.getSystemResourceAsStream(MessageBoxIcon.Information.toString()));
			ImageView imageViewInfo = new ImageView(imageInfo);
			border.setLeft(imageViewInfo);
			break;
		case Warning:
			Image imageWarn = new Image(ClassLoader.getSystemResourceAsStream(MessageBoxIcon.Warning.toString()));
			ImageView imageViewWarn = new ImageView(imageWarn);
			border.setLeft(imageViewWarn);
			break;
		case Alert:
			Image imageAlert = new Image(ClassLoader.getSystemResourceAsStream(MessageBoxIcon.Alert.toString()));
			ImageView imageViewAlert = new ImageView(imageAlert);
			border.setLeft(imageViewAlert);
			break;
		case CriticalError:
			Image imageCriticalError = new Image(
					ClassLoader.getSystemResourceAsStream(MessageBoxIcon.CriticalError.toString()));
			ImageView imageViewCriticalError = new ImageView(imageCriticalError);
			border.setLeft(imageViewCriticalError);
			break;
		}
	}

}