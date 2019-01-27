package mvc.employee.model.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class OraConn extends Application {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		launch(args);
	}
	private static Connection connection;
	@Override
	public void start(Stage primaryStage) {
		OraConn oraJDBC = new OraConn();
		oraJDBC.testConnection();
	}

	private void testConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException ex) {
			MessageBox.showAndWait(AlertType.ERROR, "Brak sterownika Oracle JDBC.");
			//	ex.printStackTrace();
		}
		MessageBox.showAndWait(AlertType.INFORMATION, "Sterownik Oracle JDBC został zarejestrowany.");
		connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:oracle:thin:@ora3.elka.pw.edu.pl:1521:ora3inf", "temp03", "temp03");
		} catch (SQLException ex) {
			MessageBox.showAndWait(AlertType.ERROR, "	Błąd	łączenia	z 	bazą	!");
			//	ex.printStackTrace();
			return;
		}

		try {
			if (connection != null) {
				MessageBox.showAndWait(AlertType.INFORMATION, "	Połączono.");
				connection.close();
				MessageBox.showAndWait(AlertType.INFORMATION, "	Rozłączono.");
			} else
				MessageBox.showAndWait(AlertType.ERROR, "	Brak połączenia!");
		} catch (SQLException ex) {
			MessageBox.showAndWait(AlertType.ERROR, ex.getMessage());
			return;
		}
	}

	static class MessageBox {
		private MessageBox() {
		}

		public static void showAndWait(AlertType aType, String content) {
			Alert alert = new Alert(aType);
			alert.setTitle("Test sterownika");
			alert.setHeaderText("JDBC");
			alert.setContentText(content);
			alert.showAndWait();
		}
	}
	public static Connection getConnection(){
		return connection;
	}
}
