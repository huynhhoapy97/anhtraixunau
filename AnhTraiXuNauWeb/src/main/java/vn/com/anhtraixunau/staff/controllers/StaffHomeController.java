package vn.com.anhtraixunau.staff.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.com.anhtraixunau.models.StaffAccount;

@Controller
@RequestMapping("staff")
public class StaffHomeController {
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
		viewName = "staff/index";
		pageName = "home.jsp";
		message = "";
		
		return view(modelMap, viewName, pageName, message);
	}
	
	@GetMapping("login")
	public String login(ModelMap modelMap, @RequestParam(value = "isSuccess", required = false) String isSuccess) {
		viewName = "staff/index";
		pageName = "login.jsp";
		message = "";
		
		if (isSuccess != null) {
			message = "Tài khoản hoặc Mật khẩu không đúng";
		}
		
		StaffAccount staffAccount = new StaffAccount();
		modelMap.addAttribute("staffAccount", staffAccount);
		
		return view(modelMap, viewName, pageName, message);
	}
	
	/*@PostMapping("login")
	public String login() {
		viewName = "staff/index";
		pageName = "home.jsp";
		message = "";
		
		return view(new ModelMap(), viewName, pageName, message);
	}*/
	
	@GetMapping("dashboard")
	public String dashboard(ModelMap modelMap) {
		viewName = "staff/index";
		pageName = "dashboard.jsp";
		message = "";
		
		return view(modelMap, viewName, pageName, message);
	}
	
	@GetMapping("denied")
	public String denied(ModelMap modelMap) {
		viewName = "staff/index";
		pageName = "denied.jsp";
		message = "";
		
		return view(modelMap, viewName, pageName, message);
	}
	
	@GetMapping("logout")
	public String logout(ModelMap modelMap) {
		viewName = "staff/index";
		pageName = "login.jsp";
		message = "";
		
		StaffAccount staffAccount = new StaffAccount();
		modelMap.addAttribute("staffAccount", staffAccount);
		
		return view(modelMap, viewName, pageName, message);
	}
}
