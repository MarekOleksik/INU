package mvc.employee;

import java.util.Optional;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import mvc.employee.model.dal.EmployeesDAL;
import mvc.employee.model.dal.OraConn;
import mvc.employee.view.AlertBox;
import mvc.employee.view.EmployeeController;
import mvc.employee.view.MainController;

public class Main extends Application {

	public void start(Stage primaryStage) {
		if (OraConn.open("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf", "temp04", "temp04") > 0)
			return;
		ViewLoader<BorderPane, Object> viewLoader = new ViewLoader<BorderPane, Object>("view/Main.fxml");
		BorderPane borderPane = viewLoader.getLayout();
		
		// kod otwierający okno danych pracowniczych
		ViewLoader<AnchorPane, EmployeeController> viewLoaderEmp =
		new ViewLoader<AnchorPane, EmployeeController>(	"view/EmployeeData.fxml");
		AnchorPane anchorPaneEmp = viewLoaderEmp.getLayout();
		borderPane.setCenter(anchorPaneEmp);
		((MainController) viewLoader.getController()).setStage(primaryStage);
		EmployeeController empControler = viewLoaderEmp.getController();
		((MainController) viewLoader.getController()).setStage(primaryStage);
		((MainController) viewLoader.getController()).setEmployeeFXML(viewLoaderEmp);
		// źródło danych
		empControler.setEmployees(new EmployeesDAL().getEmployees() );

		Scene scene = new Scene(borderPane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Pracownicy");
		primaryStage.setOnHiding(e -> primaryStage_Hiding(e));
		primaryStage.setOnCloseRequest(e -> primaryStage_CloseRequest(e));
		primaryStage.show();
	}

	int OraDbConnect() {
		int ret = OraConn.open("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf", "temp04", "temp04");
		if (ret > 0) { 
			AlertBox.showAndWait(AlertType.ERROR, "Nawiązanie połączenia z bazą danych", "Nieprawidłowy użytkownik lub hasło.\n" + "[" + OraConn.getErr() + "] " + OraConn.getErrMsg());
		}
		return ret;
	}

	void primaryStage_Hiding(WindowEvent e) {
		OraConn.close();
	}

	void primaryStage_CloseRequest(WindowEvent e) {
		Optional<ButtonType> result = AlertBox.showAndWait(AlertType.CONFIRMATION, "Kończenie pracy", "Czy chcesz zamknąć aplikację?");
		if (result.orElse(ButtonType.CANCEL) != ButtonType.OK)
			e.consume();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
