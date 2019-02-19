package project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.entities.Employee;
import project.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService service;
	
	@GetMapping("/list")
	public String listEmployees(Model model) {
		model.addAttribute("employees", service.findAll());
		return "listEmployees";
	}
	
	@GetMapping("/createForm")
	public String createForm(Model model) {
		Employee employee = new Employee();
		model.addAttribute("employee", employee);
		return "employeeForm";
			
	}
	
	@GetMapping("/updateForm")
	public String updateForm(@RequestParam("id") int id, Model model) {
		Employee employee = service.findById(id);
		model.addAttribute("employee", employee);
		return "employeeForm";
			
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("id") int id, Model model) {
		service.deleteById(id);
		return "redirect:/employees/list";
			
	}
	
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee) {
		service.save(employee);
		return "redirect:/employees/list";
	}
}
