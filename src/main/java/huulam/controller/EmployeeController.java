package huulam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import huulam.model.Employee;
import huulam.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/")
	public String getIndexPage(Model model) {
		model.addAttribute("listEmployees", employeeService.getAllEmployees()); 
		return "index";
	}
	
	@GetMapping("/addEmployee")
    public String getAddEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "add_employee";
    }
	
	@GetMapping("/editEmployee/{id}")
	public String getEditEmployee(@PathVariable(value = "id") long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "edit_employee";
	}
	
	@GetMapping("/deleteEmployee/{id}")
	public String getDeleteEmployee(@PathVariable(value = "id") long id, Model model) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}
	
	@PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

}
