package application;

import org.omg.CORBA.Environment;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;



public class Main extends Application {

	private ChoiceBox<Environment> cbxEnv;
	private ComboBox<String> cbxUsers;
	private PasswordField passField;
	private ButtonType loginBtnType;
	private ButtonType cancelBtnType;
	private GridPane grid;

	@Override
	public void start(Stage primaryStage) throws Exception

	{

		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(12);

		HBox hbButtons = new HBox();
		hbButtons.setSpacing(10.0);

		Button btnSubmit = new Button("Ok");
		Button btnClear = new Button("Anuluj");
		Button btnExit = new Button("Wyjście");
		btnSubmit.setStyle("-fx-font-size: 15pt;");

		Label lblName = new Label("User name:");
		TextField tfName = new TextField();
		Label lblPwd = new Label("Password:");
		PasswordField pfPwd = new PasswordField();

		hbButtons.getChildren().addAll(btnSubmit, btnClear, btnExit);
		grid.add(lblName, 0, 0);
		grid.add(tfName, 1, 0);
		grid.add(lblPwd, 0, 1);
		grid.add(pfPwd, 1, 1);
		grid.add(hbButtons, 0, 2, 2, 1);
		
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(12);

		ColumnConstraints column1 = new ColumnConstraints();
		column1.setHalignment(HPos.RIGHT);
		grid.getColumnConstraints().add(column1); 

		ColumnConstraints column2 = new ColumnConstraints();
		column2.setHalignment(HPos.LEFT);
		grid.getColumnConstraints().add(column2); 
		
		Scene scene = new Scene(grid);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Pierwsza aplikacja");
		primaryStage.centerOnScreen();
		primaryStage.show();
	}


	public static void main(String[] args) {
		launch(args);
	}
}
