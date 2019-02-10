package mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	/* Return the page name (without prefix and suffix) */
	
	@RequestMapping("/")
	public String myHomePage() {
		return "home";
	}
}
