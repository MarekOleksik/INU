package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class VBoxDemo extends Application {
 
    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox root = new VBox();
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        
        root.setSpacing(10);
        root.setPadding(new Insets(15,20, 10,10));
        
        hbox1.setSpacing(10);
        hbox1.setPadding(new Insets(15,20, 10,10));
        
        hbox2.setSpacing(10);
        hbox2.setPadding(new Insets(15,20, 10,10));
        
        root.getChildren().addAll(hbox1, hbox2);
        // Button 1
        Button button1= new Button("Button1");
        hbox1.getChildren().add(button1);
        
        
        // Button 2
        Button button2 = new Button("Button2");
        button2.setPrefSize(100, 100);
        hbox2.getChildren().add(button2);
        
        // TextField
        TextField textField = new TextField("Text Field");
        textField.setPrefWidth(110);
          
        
      hbox2.getChildren().add(textField);
        
        // CheckBox
        CheckBox checkBox = new CheckBox("Check Box");
          
        hbox2.getChildren().add(checkBox);
        
        // RadioButton
        RadioButton radioButton = new RadioButton("Radio Button");
        hbox1.getChildren().add(radioButton);
        
        Scene scene = new Scene(root, 550, 250);
 
        primaryStage.setTitle("VBox Layout Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    
    public static void main(String[] args) {
        launch(args);
    }
    
}
