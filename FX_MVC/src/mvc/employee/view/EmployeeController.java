package mvc.employee.view;

import java.io.IOException;
import java.time.LocalDate;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import mvc.employee.ViewLoader;
import mvc.employee.model.Employee;

public class EmployeeController {

	// TableView, TableColumn
	@FXML
	private TableView<Employee> employeeTable;
	@FXML
	private TableColumn<Employee, Integer> employeeIdColumn;
	@FXML
	private TableColumn<Employee, String> firstNameColumn;
	@FXML
	private TableColumn<Employee, String> lastNameColumn;
	@FXML
	private TableColumn<Employee, String> emailColumn;
	@FXML
	private TableColumn<Employee, String> phoneNumberColumn;
	@FXML
	private TableColumn<Employee, LocalDate> hireDateColumn;
	@FXML
	private TableColumn<Employee, String> hireDateAsStrColumn;
	@FXML
	private TableColumn<Employee, String> jobIdColumn;
	@FXML
	private TableColumn<Employee, Integer> salaryColumn;
	@FXML
	private TableColumn<Employee, Integer> managerIdColumn;
	@FXML
	private TableColumn<Employee, Integer> departmentIdColumn;

	// Label
	@FXML
	private Label employeeIdLabel;
	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label emailLabel;
	@FXML
	private Label phoneNumberLabel;
	@FXML
	private Label hireDateLabel;
	@FXML
	private Label jobIdLabel;
	@FXML
	private Label salaryLabel;
	@FXML
	private Label managerIdLabel;
	@FXML
	private Label departmentIdLabel;

	@FXML
	private void initialize() {
		employeeTable.setTableMenuButtonVisible(true);

		employeeIdColumn.setCellValueFactory(cellData -> cellData.getValue().employeeIdProperty().asObject());
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
		emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
		phoneNumberColumn.setCellValueFactory(cellData -> cellData.getValue().phoneNumberProperty());
		hireDateColumn.setCellValueFactory(cellData -> cellData.getValue().hireDateProperty());
		jobIdColumn.setCellValueFactory(cellData -> cellData.getValue().jobIdProperty());
		salaryColumn.setCellValueFactory(cellData -> cellData.getValue().salaryProperty().asObject());
		managerIdColumn.setCellValueFactory(cellData -> cellData.getValue().managerIdProperty().asObject());
		departmentIdColumn.setCellValueFactory(cellData -> cellData.getValue().departmentIdProperty().asObject());

		// ustaw wartości pól
		refreshEmployee(null);
		// słuchaj zmiany zaznaczonego wiersza
		employeeTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> refreshEmployee(newValue));
	}

	public void setEmployees(ObservableList<Employee> olEmployees) {
		employeeTable.getItems().clear();
		employeeTable.setItems(olEmployees);
		// zaznacz pierwszy wiersz w widoku tabeli (o ile nie jest pusta)
		if (!employeeTable.getItems().isEmpty())
			employeeTable.getSelectionModel().select(0);
	}

	private void refreshEmployee(Employee emp) {
		if (emp != null) {
			employeeIdLabel.setText(Integer.toString(emp.getEmployeeId()));
			firstNameLabel.setText(emp.getFirstName());
			lastNameLabel.setText(emp.getLastName());
			emailLabel.setText(emp.getEmail());
			phoneNumberLabel.setText(emp.getPhoneNumber());
			hireDateLabel.setText(emp.getHireDate().toString());
			jobIdLabel.setText(emp.getJobId());
			salaryLabel.setText(Integer.toString(emp.getSalary()));
			managerIdLabel.setText(Integer.toString(emp.getManagerId()));
			departmentIdLabel.setText(Integer.toString(emp.getDepartmentId()));
		} 
		else {
			employeeIdLabel.setText("");
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			emailLabel.setText("");
			phoneNumberLabel.setText("");
			hireDateLabel.setText("");
			jobIdLabel.setText("");
			salaryLabel.setText("");
			managerIdLabel.setText("");
			departmentIdLabel.setText("");
		}

	}
	
	@FXML
	public void deleteEmployee() {
		int selIdx = employeeTable.getSelectionModel().getSelectedIndex();
				if (selIdx >= 0)
				employeeTable.getItems().remove(selIdx);
				}
	
	@FXML
	public void insertEmployee() throws IOException {
		
		//ViewLoader<GridPane, EmployeeInsertController> viewLoader = new ViewLoader<GridPane, EmployeeInsertController>(	"/EmployeeInsert.fxml");
		//GridPane gridPane = viewLoader.getLayout();
		FXMLLoader loader = new FXMLLoader(); 
		loader.setLocation(this.getClass().getResource("EmployeeInsert.fxml"));
		GridPane gridpane = loader.load();
		try {
			gridpane = loader.load();
		} catch (IOException e) {
	
			e.printStackTrace();
		}
		Scene scene = new Scene(gridpane);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Dodawanie nowego pracownika");
		stage.showAndWait();
	}
}
