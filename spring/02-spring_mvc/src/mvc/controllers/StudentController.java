package mvc.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.entities.Student;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Value("#{countryOptions}")
	private HashMap<String, String> countryOptions;
	
	@RequestMapping("/showForm")
	public String showForm(Model model) {
		
		Student student = new Student();
		model.addAttribute("student", student);
		model.addAttribute("countryOptions", countryOptions);
		
		return "studentForm";
	}
	
	@RequestMapping("/processForm")
	public String processForm(@ModelAttribute("student") Student student, Model model) {
		
		// Do something with the registered student
		
		return "studentConfirmation";
	}
}
