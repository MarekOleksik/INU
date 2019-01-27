package application;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	private Label labelTitle, labelMesage, labelIcon, labelButtons;
	private static TextField textTitle;
	private static TextArea textMessage;
	private Button showWindow, close;
	private static ComboBox<MessageBoxIcon> comboBoxIcon;
	private static ComboBox<MessageBoxButton> comboBoxButton;
	private static MessageBoxIcon selectedIcon;
	private static MessageBoxButton selectedButtons;
	private static MessageBoxResult result;

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
		labelTitle = new Label("Tytul okna");
		textTitle = new TextField("Informacja");
		textTitle.setMinWidth(120);
		hbox1.getChildren().addAll(labelTitle, textTitle);

		// panel hbox2
		HBox hbox2 = new HBox();
		hbox2.setPadding(new Insets(10));
		hbox2.setSpacing(10);
		hbox2.setAlignment(Pos.TOP_LEFT);
		labelIcon = new Label("Ikona");
		comboBoxIcon = new ComboBox<>();
		comboBoxIcon.getItems().addAll(MessageBoxIcon.values());
		comboBoxIcon.setOnAction(e -> {
			selectedIcon = comboBoxIcon.getSelectionModel().getSelectedItem();
		});
		hbox2.getChildren().addAll(labelIcon, comboBoxIcon);

		// panel hbox3
		HBox hbox3 = new HBox();
		hbox3.setPadding(new Insets(10));
		hbox3.setSpacing(10);
		hbox3.setAlignment(Pos.TOP_LEFT);
		labelMesage = new Label("Tresc wiadomosci");
		textMessage = new TextArea("Tresc wiadomosci do okienka");
		textMessage.setMaxWidth(200);
		textMessage.setMaxHeight(100);
		textMessage.setWrapText(true);
		hbox3.getChildren().addAll(labelMesage, textMessage);

		// panel hbox4
		HBox hbox4 = new HBox();
		hbox4.setPadding(new Insets(10));
		hbox4.setSpacing(10);
		hbox4.setAlignment(Pos.TOP_LEFT);
		labelButtons = new Label("Predefiniowany zestaw przyciskow");
		comboBoxButton = new ComboBox<>();
		comboBoxButton.setItems(FXCollections.observableArrayList(MessageBoxButton.values()));
		comboBoxButton.setOnAction(e -> {
			selectedButtons = comboBoxButton.getSelectionModel().getSelectedItem();
		});
		hbox4.getChildren().addAll(labelButtons, comboBoxButton);

		// panel hbox5
		HBox hbox5 = new HBox();
		hbox5.setPadding(new Insets(10));
		hbox5.setSpacing(10);
		hbox5.setAlignment(Pos.CENTER);

		showWindow = new Button("Pokaz okno");
		showWindow.setOnAction(e -> {
			result = MessageBox.show(textMessage.getText(), textTitle.getText(), selectedButtons, selectedIcon);
		});
		System.out.println("Odpowied� :" + result);
		close = new Button("Zamknij");
		close.setOnAction(e -> Platform.exit());
		hbox5.getChildren().addAll(showWindow, close);

		root.getChildren().addAll(hbox1, hbox2, hbox3, hbox4, hbox5);

		Scene scene = new Scene(root, 400, 400);
		stage.setTitle("Praca zaliczeniowa INU");
		stage.setScene(scene);
		stage.centerOnScreen();

		stage.show();

	}

	public static void main(String args[]) {
		launch(args);
	}

}