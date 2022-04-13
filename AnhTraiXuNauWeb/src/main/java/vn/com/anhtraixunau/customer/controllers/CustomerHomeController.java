package vn.com.anhtraixunau.customer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.com.anhtraixunau.models.AdminAccount;

@Controller
@RequestMapping("customer")
public class CustomerHomeController {
	private String viewName;
	private String pageName;
	private String message;
	
	private String view(ModelMap modelMap, String viewName, String...contents) {
		if (contents.length > 0) {
			String pageName = contents[0];
			String message = contents[1];
			
			modelMap.addAttribute("pageName", pageName);
			modelMap.addAttribute("message", message);
		}
		
		return viewName;
	}
	
	@GetMapping("index")
	public String index(ModelMap modelMap) {
		viewName = "customer/index";
		pageName = "home.jsp";
		message = "";
		
		return view(modelMap, viewName, pageName, message);
	}
	
	@GetMapping("login")
	public String login(ModelMap modelMap) {
		viewName = "customer/index";
		pageName = "login.jsp";
		message = "";
		
		AdminAccount adminAccount = new AdminAccount();
		modelMap.addAttribute("adminAccount", adminAccount);
		
		return view(modelMap, viewName, pageName, message);
	}
	
	@PostMapping("login")
	public String login() {
		viewName = "customer/index";
		pageName = "home.jsp";
		message = "";
		
		return view(new ModelMap(), viewName, pageName, message);
	}
	
	@GetMapping("dashboard")
	public String dashboard(ModelMap modelMap) {
		viewName = "customer/index";
		pageName = "dashboard.jsp";
		message = "";
		
		return view(modelMap, viewName, pageName, message);
	}
	
	@GetMapping("denied")
	public String denied(ModelMap modelMap) {
		viewName = "customer/index";
		pageName = "denied.jsp";
		message = "";
		
		return view(modelMap, viewName, pageName, message);
	}
	
	@GetMapping("logout")
	public String logout(ModelMap modelMap) {
		viewName = "customer/index";
		pageName = "login.jsp";
		message = "";
		
		AdminAccount adminAccount = new AdminAccount();
		modelMap.addAttribute("adminAccount", adminAccount);
		
		return view(modelMap, viewName, pageName, message);
	}
}
