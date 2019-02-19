package mvc.controllers;

import javax.validation.Valid;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import mvc.entities.Student;
import mvc.entities.Teacher;

/* MVC controller with included form tags, validation, regex*/
@Controller
@RequestMapping("/teacher")
public class TeacherController {

	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		StringTrimmerEditor editor = new StringTrimmerEditor(true);
		binder.registerCustomEditor(String.class, editor);
	}
	
	@RequestMapping("/showForm")
	public String showForm(Model model) {
		
		Teacher teacher = new Teacher();
		model.addAttribute("teacher", teacher);
		
		return "teacherForm";
	}
	
	@RequestMapping("/processForm")
	public String processForm(@Valid @ModelAttribute("teacher") Teacher teacher, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "teacherForm";
		}
		
		return "teacherConfirmation";
	}
}
