package application;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class KalkulatorController {

	private String operator = "";
	private double number1 = 0;
	private boolean start = true;
	private double result = 0;
	DecimalFormat formater = (DecimalFormat) DecimalFormat.getInstance(Locale.ENGLISH);

	Operacje operacje = new Operacje();

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label wynikLabel;

	@FXML
	private Button acButton;

	@FXML
	private Button znakButton;

	@FXML
	private Button procentButton;

	@FXML
	private Button dzielenieButton;

	@FXML
	private Button siedemButton;

	@FXML
	private Button osiemButton;

	@FXML
	private Button dziewiecButton;

	@FXML
	private Button mnozenieButton;

	@FXML
	private Button czteryButton;

	@FXML
	private Button piecButton;

	@FXML
	private Button szescButton;

	@FXML
	private Button odejmowanieButton;

	@FXML
	private Button jedenButton;

	@FXML
	private Button dwaButton;

	@FXML
	private Button trzyButton;

	@FXML
	private Button dodawanieButton;

	@FXML
	private Button zeroButton;

	@FXML
	private Button kropkaButton;

	@FXML
	private Button wynikButton;

	@FXML
	void initialize() {
		wyczysc();
		wynikLabel.setText("0");
	}

	@FXML
	public void operator(ActionEvent event) {

		String value = ((Button) event.getSource()).getText();

		if (!"=".equals(value)) {
			if (!operator.isEmpty())
				return;

			operator = value;
			number1 = Double.parseDouble(wynikLabel.getText());
			wynikLabel.setText("");
		} else {
			if (operator.isEmpty())
				return;

			result = operacje.calculate(number1, Double.parseDouble(wynikLabel.getText()), operator);
			wynikLabel.setText(String.valueOf(formater.format(result)));
			operator = "";
			start = true;
			acButton.setText("AC");
		}


	}

	@FXML
	public void cyfra(ActionEvent event) {
		if (start) {
			wynikLabel.setText("");
			start = false;
		}
		String value = ((Button) event.getSource()).getText();
		wynikLabel.setText(wynikLabel.getText() + value);
		acButton.setText("CE");
	}

	@FXML
	public void wyczysc() {

		wynikLabel.setText("0");
		start=true;
	}

	@FXML
	public void zmianaZnaku() {
		Double znak = Double.parseDouble(wynikLabel.getText());
		wynikLabel.setText(String.valueOf(formater.format(znak * (-1))));
	}

	@FXML
	public void procent() {
		Double znak = Double.parseDouble(wynikLabel.getText());
		wynikLabel.setText(String.valueOf(formater.format(znak / 100)));
	}

}
