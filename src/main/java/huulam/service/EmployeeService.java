package huulam.service;

import java.util.List;

import huulam.model.Employee;

public interface EmployeeService {
	List<Employee> getAllEmployees();
	Employee getEmployeeById(long id);
	void saveEmployee(Employee employee);
    void deleteEmployeeById(long id);
}
