package application;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CourseStage extends Stage{

	private Label labelKodPrzedmiotu, labelNazwaPrzedmiotu, labelOsobaOdpowiedzialna, labelInstytut, labelStudia;
	private TextField textKodPrzedmiotu, textNazwaPrzedmiotu, textOsobaOdpowiedzialna;
	private CheckBox chkAutIInfSt, chkInf, chkMikIOp, chkRadITecMult, chkSysEl, chkTel;
	private RadioButton radIn, radMag, radPod;
	private Button ok, anuluj;
	private GridPane grid;
	
	public void initializeCourse() {
		labelKodPrzedmiotu = new Label("Kod przedmiotu:");
		textKodPrzedmiotu = new TextField();
		labelNazwaPrzedmiotu = new Label("Nazwa przedmiotu");
		textNazwaPrzedmiotu = new TextField();
		labelOsobaOdpowiedzialna = new Label("Osoba odpowiedzialna:");
		textOsobaOdpowiedzialna = new TextField();
		grid.add(labelKodPrzedmiotu, 0, 0);
		grid.add(textKodPrzedmiotu, 1, 0);
		grid.add(labelNazwaPrzedmiotu, 0,1);
		grid.add(textNazwaPrzedmiotu, 1, 1, 2, 1);
		grid.add(labelOsobaOdpowiedzialna, 0, 2);
		grid.add(textOsobaOdpowiedzialna, 1, 2, 2, 1);
			
	}
	
	public void initializeInstitutes(){
		labelInstytut = new Label("Instytut");
		chkAutIInfSt = new CheckBox("Automatyki i Informatyki Stosowanej");
		chkInf = new CheckBox("Informatyki");
		chkMikIOp = new CheckBox("Mikroelektroniki i Technik Multimedialnych");
		chkRadITecMult = new CheckBox("Radioelektroniki i Technik Multimedialnych");
		chkSysEl = new CheckBox("Systemów Elektronicznych");
		chkTel = new CheckBox("Telekomunikacji");
		FlowPane flow = new FlowPane(Orientation.VERTICAL);
		flow.getChildren().addAll(labelInstytut, chkAutIInfSt, chkInf, chkMikIOp, chkRadITecMult, chkSysEl, chkTel);
		grid.add(flow, 1, 3);
				
	}
	
	public void initializeStudies(){
		labelStudia = new Label("Studia");
		radIn = new RadioButton("Inżynierskie");
		radMag = new RadioButton("Magisterskie");
		radPod = new RadioButton("Podyplomkowe");
		FlowPane flow1 = new FlowPane(Orientation.VERTICAL);
		flow1.getChildren().addAll(labelStudia, radIn, radMag, radPod);
		grid.add(flow1, 2, 3);

	}
	
	public void initializeButtons(){
		ok = new Button("OK");
		anuluj = new Button("Anuluj");
		FlowPane flow2 = new FlowPane(Orientation.HORIZONTAL);
		flow2.getChildren().addAll(ok, anuluj);
		grid.add(flow2, 2, 4);

	}
	
	public void initializeGrid(){
		Stage primaryStage = new Stage();
		grid = new GridPane();
		grid.setPadding(new Insets(20));
		grid.setHgap(25);
		grid.setVgap(15);
		ColumnConstraints kolumna1 = new ColumnConstraints();
		kolumna1.setMinWidth(400);
		ColumnConstraints kolumna3 = new ColumnConstraints();
		kolumna1.setMinWidth(300);
		Scene scene = new Scene(grid, 900, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Grid Test");
		primaryStage.show();
	}
}
