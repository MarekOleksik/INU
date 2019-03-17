package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RatyController {

	@FXML
	TextField kwotaTextField;
	@FXML
	TextField stopaTextField;
	@FXML
	TextField okresTextField;
	@FXML
	Button obliczButton;
	@FXML
	Label rataLabel;

	@FXML
	private void initialize() {

	}

	@FXML
	private void oblicz() {
		double kwota = Double.parseDouble(kwotaTextField.getText());
		double stopa = Double.parseDouble(stopaTextField.getText());
		double okres = Double.parseDouble(okresTextField.getText());
		double rata = kwota / (stopa * okres);
		rataLabel.setText(String.valueOf(rata));



	}

}
