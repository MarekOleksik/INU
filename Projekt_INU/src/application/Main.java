package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {

	private Label labelTitle, labelMesage, labelIcon, labelButtons;
	private static TextField textTitle;
	private static TextArea textMessage;
	private Button showWindow;
	private static Button btnYes, btnNo, btnCancel, btnAbort, btnRetry, btnIgnore;
	private static ComboBox<MessageBoxIcon> comboBoxIcon;
	private static ComboBox<MessageBoxButton> comboBoxButton;
	private static BorderPane border;
	private static FlowPane flow;
	private static Stage newStage;

	@Override
	public void start(Stage stage) {

		// panel vbox
		VBox root = new VBox();
		root.setPadding(new Insets(10));
		root.setSpacing(10);
		root.setAlignment(Pos.CENTER);

		// panel hbox1
		HBox hbox1 = new HBox();
		hbox1.setPadding(new Insets(10));
		hbox1.setSpacing(10);
		hbox1.setAlignment(Pos.TOP_LEFT);
		labelTitle = new Label("Tytu� okna");
		textTitle = new TextField("Tytu� okna");
		textTitle.setMinWidth(120);
		hbox1.getChildren().addAll(labelTitle, textTitle);

		// panel hbox2
		HBox hbox2 = new HBox();
		hbox2.setPadding(new Insets(10));
		hbox2.setSpacing(10);
		hbox2.setAlignment(Pos.TOP_LEFT);
		labelIcon = new Label("Ikona");
		comboBoxIcon = new ComboBox<>();
		comboBoxIcon.setItems(FXCollections.observableArrayList(MessageBoxIcon.values()));
		comboBoxIcon.setOnAction(e -> {
			MessageBoxIcon selected = comboBoxIcon.getSelectionModel().getSelectedItem();
			textTitle.setText(selected.toString());
		});
		hbox2.getChildren().addAll(labelIcon, comboBoxIcon);

		// panel hbox3
		HBox hbox3 = new HBox();
		hbox3.setPadding(new Insets(10));
		hbox3.setSpacing(10);
		hbox3.setAlignment(Pos.TOP_LEFT);
		labelMesage = new Label("Tre�� wiadomo�ci");
		textMessage = new TextArea("Tre�� wiadomo�ci do okienka");
		textMessage.setMaxWidth(200);
		textMessage.setMaxHeight(100);
		textMessage.setWrapText(true);

		hbox3.getChildren().addAll(labelMesage, textMessage);

		// panel hbox4
		HBox hbox4 = new HBox();
		hbox4.setPadding(new Insets(10));
		hbox4.setSpacing(10);
		hbox4.setAlignment(Pos.TOP_LEFT);
		labelButtons = new Label("Predefiniowany zestaw przycisk�w");
		comboBoxButton = new ComboBox<>();
		comboBoxButton.setItems(FXCollections.observableArrayList(MessageBoxButton.values()));
		hbox4.getChildren().addAll(labelButtons, comboBoxButton);

		// panel hbox5
		HBox hbox5 = new HBox();
		hbox5.setPadding(new Insets(10));
		hbox5.setSpacing(10);
		hbox5.setAlignment(Pos.CENTER);
		showWindow = new Button("Poka� okno");
		hbox5.getChildren().addAll(showWindow);
		showWindow.setOnAction(e -> show());

		root.getChildren().addAll(hbox1, hbox2, hbox3, hbox4, hbox5);

		Scene scene = new Scene(root, 400, 400);
		stage.setTitle("Praca zaliczeniowa INU");
		stage.setScene(scene);
		stage.centerOnScreen();

		stage.show();

		MessageBoxButton.AbortRetryIgnore.getCount();
	}

	public static void main(String args[]) {
		launch(args);
	}

	public static void show() {
		// btnYes.setDefaultButton(true);
		// btnNo.setCancelButton(true);
		newStage = new Stage();
		newStage.setWidth(400);
		newStage.setHeight(180);
		String title = textTitle.getText();
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
					// btnYes.setDefaultButton(true);
					// btnNo.setCancelButton(true);
				}
			}
		});
		border = new BorderPane();
		border.setPadding(new Insets(20, 30, 20, 30));

		MessageBoxIcon mbi = comboBoxIcon.getSelectionModel().getSelectedItem();
		setIcon(mbi);

		String text = textMessage.getText();
		Label textLabel = new Label(text);
		textLabel.setWrapText(true);
		border.setCenter(textLabel);
		textLabel.setPadding(new Insets(5, 5, 5, 5));
		BorderPane.setAlignment(textLabel, Pos.TOP_LEFT);

		MessageBoxButton mbb = comboBoxButton.getSelectionModel().getSelectedItem();
		setButtons(mbb);
		
		//yucv 67vbc b76 bfj vjMessageBoxResult mbr = 

		Scene scene = new Scene(border);
		newStage.setScene(scene);
		newStage.showAndWait();
	}

	private static void setButtons(MessageBoxButton buttonSet) {
		if (buttonSet == null)
			buttonSet = MessageBoxButton.OK;

		switch (buttonSet) {

		case AbortRetryIgnore:
			btnAbort = new Button("Abort");
			btnAbort.setPrefWidth(80.0);
			btnAbort.setDefaultButton(true);
			btnAbort.requestFocus();
			btnRetry = new Button("Retry");
			btnRetry.setPrefWidth(80.0);
			btnIgnore = new Button("Ignore");
			btnIgnore.setPrefWidth(80.0);
			flow = new FlowPane();
			flow.setAlignment(Pos.BOTTOM_RIGHT);
			flow.getChildren().add(btnAbort);
			flow.getChildren().add(btnRetry);
			flow.getChildren().add(btnIgnore);
			border.setBottom(flow);

			break;
		case OK:
		default:
			btnYes = new Button("Ok");
			btnYes.setPrefWidth(70.0);
			btnYes.setDefaultButton(true);
			btnYes.requestFocus();
			flow = new FlowPane();
			flow.setAlignment(Pos.BOTTOM_RIGHT);
			flow.getChildren().add(btnYes);
			border.setBottom(flow);

		case OKCancel:
			btnYes = new Button("Ok");
			btnYes.setPrefWidth(70.0);
			btnYes.setDefaultButton(true);
			btnYes.requestFocus();
			btnCancel = new Button("Cancel");
			btnCancel.setPrefWidth(70.0);
			flow = new FlowPane();
			flow.setAlignment(Pos.BOTTOM_RIGHT);
			flow.getChildren().add(btnYes);
			flow.getChildren().add(btnCancel);
			border.setBottom(flow);
			break;
		case RetryCancel:
			btnRetry = new Button("Retry");
			btnRetry.setPrefWidth(70.0);
			btnRetry.setDefaultButton(true);
			btnRetry.requestFocus();
			btnCancel = new Button("Cancel");
			btnCancel.setPrefWidth(70.0);
			flow = new FlowPane();
			flow.setAlignment(Pos.BOTTOM_RIGHT);
			flow.getChildren().add(btnRetry);
			flow.getChildren().add(btnCancel);
			border.setBottom(flow);
			break;
		case YesNo:
			btnYes = new Button("Yes");
			btnYes.setPrefWidth(70.0);
			btnYes.setDefaultButton(true);
			btnYes.requestFocus();
			btnNo = new Button("No");
			btnNo.setPrefWidth(70.0);
			flow = new FlowPane();
			flow.setAlignment(Pos.BOTTOM_RIGHT);
			flow.getChildren().add(btnYes);
			flow.getChildren().add(btnNo);
			border.setBottom(flow);
			break;
		case YesNoCancel:
			btnYes = new Button("Yes");
			btnYes.setPrefWidth(70.0);
			btnYes.setDefaultButton(true);
			btnYes.requestFocus();
			btnNo = new Button("No");
			btnNo.setPrefWidth(70.0);
			btnCancel = new Button("Cancel");
			btnCancel.setPrefWidth(70.0);
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
			Image imageInfo = new Image(
					ClassLoader.getSystemResourceAsStream("application/images/StatusInformation_64x.png"));
			ImageView imageViewInfo = new ImageView(imageInfo);
			border.setLeft(imageViewInfo);
			break;
		case Warning:
			Image imageWarn = new Image(
					ClassLoader.getSystemResourceAsStream("application/images/StatusWarning_64x.png"));
			ImageView imageViewWarn = new ImageView(imageWarn);
			border.setLeft(imageViewWarn);
			break;
		case Alert:
			Image imageAlert = new Image(
					ClassLoader.getSystemResourceAsStream("application/images/StatusAlert_64x.png"));
			ImageView imageViewAlert = new ImageView(imageAlert);
			border.setLeft(imageViewAlert);
			break;
		case CriticalError:
			Image imageCriticalError = new Image(
					ClassLoader.getSystemResourceAsStream("application/images/StatusCriticalError_64x.png"));
			ImageView imageViewCriticalError = new ImageView(imageCriticalError);
			border.setLeft(imageViewCriticalError);
			break;
		}
	}

	private static void getResponse(MessageBoxResult response) {
		switch (response) {

		case Abort:
			btnAbort.setOnAction(e->newStage.close());
			break;
		case Retry:
			break;
		case Ignore:
			break;
		case OK:
			break;
		case Cancel:
			break;
		case Yes:
			break;
		case No:
			break;
		}
	}
}