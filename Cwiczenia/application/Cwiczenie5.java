package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class Cwiczenie5 extends Application{

	public static void main(String[] args) {
	launch(args);

	}

	@Override
	public void start(Stage primaryStage) {
		FlowPane flowPane = new FlowPane();
			for (int i=0; i<100; ++i) {
				Button btn = new Button(String.valueOf(i));
				btn.setPrefWidth(100);
				flowPane.getChildren().add(btn);
			}
			Scene scene = new Scene(flowPane, 50, 300);
			scene.setOnMouseClicked(e->stage_Clicked(e, flowPane));
			primaryStage.setScene(scene);
			primaryStage.setTitle("FlowPane");
			primaryStage.show();
	}
			int i = 0;
			public void stage_Clicked(MouseEvent e, FlowPane flowPane) {
				if (++i ==1) flowPane.setOrientation(Orientation.VERTICAL);
				else if (i==2) flowPane.setVgap(10);
				else if (i==3) flowPane.setHgap(10);
				else if (i==4) flowPane.setAlignment(Pos.TOP_RIGHT);
				else if (i==5) flowPane.setPadding(new Insets(20));
				
			}
	}


