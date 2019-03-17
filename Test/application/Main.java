package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		ViewLoader<AnchorPane, RatyController> viewLoader = new ViewLoader<AnchorPane, RatyController>("Raty.fxml");
		AnchorPane anchorPane = viewLoader.getLayout();
		//AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("Raty.fxml"));
		Scene scene = new Scene(viewLoader.getLayout());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Kalkulator raty hipotecznej");
		//primaryStage.setOnHiding(e -> primaryStage_Hiding(e));
		//primaryStage.setOnCloseRequest(e -> primaryStage_CloseRequest(e));
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
