package huulam.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import huulam.model.Employee;
import huulam.model.EmployeeViewModel;
import huulam.service.EmployeeService;
import huulam.util.Utility;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	private ModelMapper modelMapper = new ModelMapper();
	private Utility util = new Utility();

	@GetMapping("/")
	public String getIndexPage(Model model) {
		List<Employee> listEmployee = employeeService.getAllEmployees();
		List<EmployeeViewModel> listEmployeeVm = modelMapper.map(listEmployee,
				new TypeToken<List<EmployeeViewModel>>() {
				}.getType());

		model.addAttribute("listEmployees", listEmployeeVm);
		return "index";
	}

	@GetMapping("/addEmployee")
	public String getAddEmployee(Model model) {
		Employee employee = new Employee();
		EmployeeViewModel employeeVm = modelMapper.map(employee, EmployeeViewModel.class);

		model.addAttribute("employee", employeeVm);
		return "add_employee";
	}

	@GetMapping("/editEmployee/{id}")
	public String getEditEmployee(@PathVariable(value = "id") long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		EmployeeViewModel employeeVm = modelMapper.map(employee, EmployeeViewModel.class);

		model.addAttribute("employee", employeeVm);
		return "edit_employee";
	}

	@GetMapping("/deleteEmployee/{id}")
	public String getDeleteEmployee(@PathVariable(value = "id") long id, Model model) {
		employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") EmployeeViewModel employeeVm,
			Model model, RedirectAttributes redirectAttributes) {
		if (!util.isFormValid(employeeVm)) {
			if(!util.isNameValid(employeeVm.getName())) {
				model.addAttribute("name_error", true);
			}
			if(!util.isAgeValid(employeeVm.getAge())) {
				model.addAttribute("age_error", true);
			}
			if(!util.isAddressValid(employeeVm.getAddress())) {
				model.addAttribute("address_error", true);
			}
			if(!util.isRoleValid(employeeVm.getRole())) {
				model.addAttribute("role_error", true);
			}
			return "add_employee";
		} else {
			Employee employee = modelMapper.map(employeeVm, Employee.class);
			employeeService.saveEmployee(employee);
			return "redirect:/";
		}
	}
	
	@PostMapping("/editEmployee")
	public String editEmployee(@ModelAttribute("employee") EmployeeViewModel employeeVm, Model model) {
		if (!util.isFormValid(employeeVm)) {
			model.addAttribute("employee", employeeVm);
			if(!util.isNameValid(employeeVm.getName())) {
				model.addAttribute("name_error", true);
			}
			if(!util.isAgeValid(employeeVm.getAge())) {
				model.addAttribute("age_error", true);
			}
			if(!util.isAddressValid(employeeVm.getAddress())) {
				model.addAttribute("address_error", true);
			}
			if(!util.isRoleValid(employeeVm.getRole())) {
				model.addAttribute("role_error", true);
			}
			return "edit_employee";
		} else {
			Employee employee = modelMapper.map(employeeVm, Employee.class);
			employeeService.saveEmployee(employee);
			return "redirect:/";
		}
	}
	
//	@PostMapping("/saveEmployee")
//	public ModelAndView saveEmployee(@ModelAttribute("employee") EmployeeViewModel employeeVm,
//			Model model, RedirectAttributes redirectAttributes) {
//		util = new Utility();
//		if (!util.isFormValid(employeeVm)) {
//			redirectAttributes.addFlashAttribute("employee", employeeVm);
//			if(!util.isNameValid(employeeVm.getName())) {
//				//model.addAttribute("name_error", true);
//				redirectAttributes.addFlashAttribute("name_error", true);
//			}
//			if(!util.isAgeValid(employeeVm.getAge())) {
//				redirectAttributes.addFlashAttribute("age_error", true);
//			}
//			if(!util.isAddressValid(employeeVm.getAddress())) {
//				redirectAttributes.addFlashAttribute("address_error", true);
//			}
//			if(!util.isRoleValid(employeeVm.getRole())) {
//				redirectAttributes.addFlashAttribute("role_error", true);
//			}
//			return new ModelAndView("redirect:/addEmployee");
//		} else {
//			Employee employee = modelMapper.map(employeeVm, Employee.class);
//			employeeService.saveEmployee(employee);
//			return new ModelAndView("redirect:/");
//		}
//	}
	
}
