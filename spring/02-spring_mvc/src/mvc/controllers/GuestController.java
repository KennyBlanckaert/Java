package mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/* Basic MVC controller */
@Controller
@RequestMapping("/guest")
public class GuestController {

	@RequestMapping("/showForm")
	public String formPage() {
		return "guestForm";
	}
	
	// Without processing
	// To trigger this instead of the processForm.jsp, change to submitForm in showForm.jsp
	@RequestMapping("/submitForm")
	public String submitFormPage() {
		return "guestSubmitForm";
	}
	
	// With processing
	// @RequestParam(...) gets the parameter from the original HttpServletRequest object
	@RequestMapping("/processForm")
	public String processFormPage(@RequestParam("studentName") String name, Model model) {	
		
		String message = "Guest \"" + name + "\" successfully registered";	
		model.addAttribute("message", message);
		
		return "guestConfirmation";
	}
}
