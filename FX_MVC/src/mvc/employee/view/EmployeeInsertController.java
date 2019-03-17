package mvc.employee.view;


import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import mvc.employee.model.Department;
import mvc.employee.model.Employee;
import mvc.employee.model.Job;
import mvc.employee.model.dal.DepartmentsDAL;
import mvc.employee.model.Department;

public class EmployeeInsertController {
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
	private TextField idEmployeeTextField;
	@FXML
	private TextField firstNameTextField;
	@FXML
	private TextField lastNameTextField;
	@FXML
	private TextField emailTextField;
	@FXML
	private TextField phoneNumberTextField;
	@FXML
	private TextField salaryTextField;
	@FXML
	private ComboBox<Department> departmentIdComboBox;
	@FXML
	private ComboBox<Job> jobIdComboBox;
	@FXML
	private ComboBox<Department> managerIdComboBox;
	@FXML
	private DatePicker hireDateDatePicker;
	
	
	@FXML
	private void initialize() {
	

		// ustaw wartości pól
		refreshEmployee(null);
	}


	private void refreshEmployee(Employee emp) {
		if (emp != null) {
			idEmployeeTextField.setText(Integer.toString(emp.getEmployeeId()));
			firstNameTextField.setText(emp.getFirstName());
			lastNameTextField.setText(emp.getLastName());
			emailTextField.setText(emp.getEmail());
			phoneNumberTextField.setText(emp.getPhoneNumber());
			hireDateDatePicker.setValue(emp.getHireDate());
			//jobIdComboBox.setValue(emp.getJobId());
			salaryTextField.setText(Integer.toString(emp.getSalary()));
			//managerIdComboBox.setValue(Integer.toString(emp.getManagerId()));
			//departmentIdComboBox.setValue(Integer.toString(emp.getDepartmentId()));
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
	public void saveEmployee(){
		
		
	}
}
