package mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.entities.Student;

@Controller
@RequestMapping("/student")
public class StudentController {

	@RequestMapping("/showForm")
	public String showForm(Model model) {
		
		Student student = new Student();
		model.addAttribute("student", student);
		
		return "studentForm";
	}
	
	@RequestMapping("/processForm")
	public String processForm(@ModelAttribute("student") Student student, Model model) {
		
		// Do something with the registered student
		
		return "studentConfirmation";
	}
}
