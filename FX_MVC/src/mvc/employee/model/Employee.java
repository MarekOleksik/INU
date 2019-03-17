package mvc.employee.model;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Employee {
	private IntegerProperty employeeId;
	private StringProperty firstName;
	private StringProperty lastName;
	private StringProperty email;
	private StringProperty phoneNumber;
	private ObjectProperty<LocalDate> hireDate;
	private StringProperty jobId;
	private IntegerProperty salary;
	private IntegerProperty commisionPct;
	private IntegerProperty managerId;
	private IntegerProperty departmentId;
	
	public Employee(){
		
		employeeId = new SimpleIntegerProperty(0);
		firstName = new SimpleStringProperty("");
		lastName = new SimpleStringProperty("");
		email = new SimpleStringProperty("");
		phoneNumber = new SimpleStringProperty("");
		hireDate = new SimpleObjectProperty<LocalDate>(LocalDate.now());
		jobId = new SimpleStringProperty("");
		salary = new SimpleIntegerProperty(0);
		commisionPct = new SimpleIntegerProperty(0);
		managerId = new SimpleIntegerProperty(0);
		departmentId = new SimpleIntegerProperty(0);
	}
	
	public Employee(int employeeId){
		this();
		this.employeeId.set(employeeId);
	}

	public int getEmployeeId() {
		return this.employeeId.get();
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId.set(employeeId);
	}
	public IntegerProperty employeeIdProperty() {
		return employeeId;
		}

	public String getFirstName() {
		return firstName.get();
	}

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}
	public StringProperty firstNameProperty() {
		return firstName;
		}
	
	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}
	public StringProperty lastNameProperty() {
		return lastName;
		}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email.set(email);
	}
	public StringProperty emailProperty() {
		return email;
		}

	public String getPhoneNumber() {
		return phoneNumber.get();
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber); ;
	}
	public StringProperty phoneNumberProperty() {
		return phoneNumber;
		}
	
	public LocalDate getHireDate() {
		return hireDate.get();
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate.set(hireDate); ;
	}
	public ObjectProperty hireDateProperty() {
		return hireDate;
		}
	
	public String getJobId() {
		return jobId.get();
	}

	public void setJobId(String string) {
		this.jobId.set(string);
	}

	public StringProperty jobIdProperty() {
		return jobId;
		}
	
	public int getSalary() {
		return salary.get();
	}

	public void setSalary(int salary) {
		this.salary.set(salary);
	}
	public IntegerProperty salaryProperty() {
		return salary;
		}

	public int getCommisionPct() {
		return commisionPct.get();
	}

	public void setCommisionPct(int commisionPct) {
		this.commisionPct.set(commisionPct);;
	}
	
	public IntegerProperty commisionPctProperty() {
		return commisionPct;
		}

	public int getManagerId() {
		return managerId.get();
	}

	public void setManagerId(int managerId) {
		this.managerId.set(managerId);;
	}
	
	public IntegerProperty managerIdProperty() {
		return managerId;
		}

	public int getDepartmentId() {
		return departmentId.get();
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId.set(departmentId);;
	}
	
	public IntegerProperty departmentIdProperty() {
		return departmentId;
		}
	
	@Override
	public String toString(){
		return lastName + " " + firstName;
	}
}
