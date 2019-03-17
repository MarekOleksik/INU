package mvc.employee.model;

import java.time.LocalDate;

public class Employee2 {
	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private LocalDate hireDate;
	private String jobId;
	private int salary;
	private int managerId;
	private int departmentId;

	public Employee2() {
	}

	public Employee2(int employeeId) {
		this.employeeId = employeeId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneName(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getEmployeeId() {
		return this.employeeId;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public LocalDate getHireDate() {
		return this.hireDate;
	}

	public String getJobId() {
		return this.jobId;
	}

	public int getSalary() {
		return this.salary;
	}

	public int getManagerId() {
		return this.managerId;
	}

	public int getDepartmentId() {
		return this.departmentId;
	}

	@Override
	public String toString() {
		return lastName + " " + firstName;
	}
}
