package mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FormController {

	@RequestMapping("/showForm")
	public String formPage() {
		return "showForm";
	}
	
	@RequestMapping("/processForm")
	public String processFormPage() {
		return "processForm";
	}
}
