package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	Button btnAdd = new Button("Add");
	Button btnSub = new Button("Sub");
	Label lblCounter = new Label("0");
	int intCounter = 0;
	
	@Override
	public void start(Stage primaryStage) {
	
			btnAdd.setOnAction(e->btnAdd_Click());
			btnSub.setOnAction(e->btnSub_Click());
			FlowPane flowPane = new FlowPane(Orientation.HORIZONTAL, 10, 10, btnAdd, btnSub, lblCounter);
			flowPane.setPadding(new Insets(20,20,20,20));
			flowPane.setAlignment(Pos.CENTER);
			Scene scene = new Scene(flowPane);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Counter");
			primaryStage.centerOnScreen();
			primaryStage.show();
	}
	public void btnAdd_Click(){
		lblCounter.setText(Integer.toString(++intCounter));
	}
	public void btnSub_Click(){
		lblCounter.setText("" + --intCounter);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
