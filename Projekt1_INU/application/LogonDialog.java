package application;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class LogonDialog {
	private Dialog<Pair<Environment, String>> dialog;
	private ChoiceBox<Environment> cbxEnv;
	private ComboBox<String> cbxUsers;
	private PasswordField passField;
	private ButtonType loginBtnType;
	private ButtonType cancelBtnType;
	private GridPane grid;
	private Users users = Users.getInstance();
	final int WIDTH = 200;

	public LogonDialog(String title, String header) {

		grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 30, 20, 30));
		dialog = new Dialog<Pair<Environment, String>>();
		dialog.getDialogPane().setContent(grid);
		dialog.setResultConverter(buttonType -> resultConverter(buttonType));
		// dodanie ikony
		Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
		stage.getIcons().add(new Image(ClassLoader.getSystemResourceAsStream("application/Login_16x.png")));

		// dodanie obrazka
		Image image = new Image(ClassLoader.getSystemResourceAsStream("application/Login_64x.png"));
		ImageView imageView = new ImageView(image);
		dialog.setGraphic(imageView);

		// dodanie przycisków
		loginBtnType = new ButtonType("Logon", ButtonData.OK_DONE);
		cancelBtnType = new ButtonType("Anuluj", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(loginBtnType, cancelBtnType);
		Node loginNode = dialog.getDialogPane().lookupButton(loginBtnType);
		loginNode.setDisable(true);

		// dodanie ChoiceBox();
		cbxEnv = new ChoiceBox<Environment>();
		Label lblEnv = new Label("Œrodowisko:");
		lblEnv.setPrefWidth(WIDTH / 2);
		cbxEnv.getItems().add(Environment.Production);
		cbxEnv.getItems().add(Environment.Test);
		cbxEnv.getItems().add(Environment.Development);
		cbxEnv.setValue(cbxEnv.getItems().get(0));
		cbxEnv.setPrefWidth(WIDTH);
		cbxEnv.valueProperty().addListener((observable, oldVal, newVal) -> cbxEnv_Changed(newVal));
		grid.add(lblEnv, 0, 0);
		grid.add(cbxEnv, 1, 0);

		// dodanie ComboBox();
		cbxUsers = new ComboBox<String>();
		cbxUsers.setEditable(true);
		cbxUsers.setPrefWidth(WIDTH);
		users.getUsers(Environment.Production).forEach(user -> cbxUsers.getItems().add(user));
		cbxUsers.valueProperty().addListener((observable, oldVal, newVal) -> cbxUsers_Changed(newVal));
		Label lblUser = new Label("U¿ytkownik:");
		lblUser.setPrefWidth(WIDTH / 2);
		grid.add(lblUser, 0, 1);
		grid.add(cbxUsers, 1, 1);

		// dodanie PassField();
		passField = new PasswordField();
		passField.setPromptText("Has³o");
		passField.setPrefWidth(WIDTH);
		passField.textProperty().addListener((observable, oldVal, newVal) -> passField_Changed(newVal));
		Label lblPass = new Label("Has³o:");
		lblPass.setPrefWidth(WIDTH / 2);
		grid.add(lblPass, 0, 2);
		grid.add(passField, 1, 2);

		dialog.setTitle(title);
		dialog.setHeaderText(header);
	}

	public Optional<Pair<Environment, String>> showAndWait() {
		return dialog.showAndWait();
	}

	private void cbxEnv_Changed(Environment newVal) {
		cbxUsers.getItems().clear();
		users.getUsers(newVal).forEach(user -> cbxUsers.getItems().add(user));
	}

	private void cbxUsers_Changed(String newVal) {
		Node loginNode = dialog.getDialogPane().lookupButton(loginBtnType);
		loginNode.setDisable(newVal == null || newVal.isEmpty() || passField.getText().isEmpty());
	}

	private void passField_Changed(String newVal) {
		Node loginNode = dialog.getDialogPane().lookupButton(loginBtnType);
		loginNode.setDisable(newVal.trim().isEmpty() || cbxUsers.getValue() == null);
	}

	private Pair<Environment, String> resultConverter(ButtonType buttonType) {

		if (buttonType == loginBtnType) {
			if (users.isPassCorrect(cbxEnv.getValue(), cbxUsers.getValue(), passField.getText())) {
				return new Pair<Environment, String>(cbxEnv.getValue(), cbxUsers.getValue());
			}
		}
		return null;
	}
}