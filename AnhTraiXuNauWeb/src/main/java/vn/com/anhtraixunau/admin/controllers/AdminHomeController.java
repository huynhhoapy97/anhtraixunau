package vn.com.anhtraixunau.admin.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.com.anhtraixunau.enums.AdminAccountMessage;
import vn.com.anhtraixunau.models.AdminAccount;
import vn.com.anhtraixunau.services.AdminAccountService;

@Controller
@RequestMapping("admin")
public class AdminHomeController {
	private AdminAccountService adminAccountService;
	
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
		viewName = "admin/index";
		pageName = "login.jsp";
		message = "";
		
		AdminAccount adminAccount = new AdminAccount();
		modelMap.addAttribute("adminAccount", adminAccount);
		
		return view(modelMap, viewName, pageName, message);
	}
	
	@PostMapping("login")
	public String login(@Validated @ModelAttribute("adminAccount") AdminAccount adminAccount, BindingResult bindingResult, ModelMap modelMap) {
		viewName = "admin/index";
		pageName = "login.jsp";
		message = "";

		if (bindingResult.hasErrors()) {
			pageName = "error.jsp";
		}
		else {
			adminAccountService = new AdminAccountService();
			int result = adminAccountService.checkAccountExists(adminAccount);
			
			if (result == AdminAccountMessage.MESSAGE_ERROR_PASSWORD.getId()) {
				pageName = "changepassword.jsp";
			}
			else if (result == AdminAccountMessage.MESSAGE_NO_ERROR.getId()) {
				pageName = "confirmpassword.jsp";
			}
			else {
				for (AdminAccountMessage adminAccountMessage : AdminAccountMessage.values()) {
					if (result == adminAccountMessage.getId()) {
						message = adminAccountMessage.getMessage();
						
						break;
					}
				}
			}
		}
		
		return view(modelMap, viewName, pageName, message);
	}
	
	@PostMapping("changePassword")
	public String changePassword(@Validated @ModelAttribute("adminAccount") AdminAccount adminAccount, BindingResult bindingResult, ModelMap modelMap) {
		viewName = "admin/index";
		pageName = "changepassword.jsp";
		message = "";
		
		if (bindingResult.hasErrors()) {
			pageName = "error.jsp";
		}
		else {
			adminAccountService = new AdminAccountService();
			int result = adminAccountService.changePassword(adminAccount);
			
			if (result == AdminAccountMessage.MESSAGE_NO_ERROR.getId()) {
				pageName = "confirmpassword.jsp";
			}
			else {
				for (AdminAccountMessage adminAccountMessage : AdminAccountMessage.values()) {
					if (result == adminAccountMessage.getId()) {
						message = adminAccountMessage.getMessage();
						
						break;
					}
				}
			}
		}
		
		return view(modelMap, viewName, pageName, message);
	}
	
	@PostMapping("confirmPassword")
	public String confirmPassword(@Validated @ModelAttribute("adminAccount") AdminAccount adminAccount, BindingResult bindingResult, ModelMap modelMap, HttpServletRequest httpServletRequest) {
		viewName = "admin/index";
		pageName = "confirmpassword.jsp";
		message = "";
		
		if (bindingResult.hasErrors()) {
			pageName = "error.jsp";
		}
		else {
			adminAccountService = new AdminAccountService();
			int result = adminAccountService.confirmPassword(adminAccount);
			
			if (result == AdminAccountMessage.MESSAGE_NO_ERROR.getId()) {
				pageName = "dashboard.jsp";
				viewName = "admin/dashboard";
				
				// Lưu Session user đăng nhập
				HttpSession httpSession = httpServletRequest.getSession();
				
				// Kiểm tra xem có Session user đăng nhập chưa?
				if (httpSession.getAttribute("username") == null) {
					// Chưa tồn tại Session user đăng nhập hoặc Hết thời gian tồn tại Session user đăng nhập, thì tạo lại
					httpSession.setAttribute("username", adminAccount.getUsername());
					httpSession.setMaxInactiveInterval(60*60*24);
				}
			}
			else {
				for (AdminAccountMessage adminAccountMessage : AdminAccountMessage.values()) {
					if (result == adminAccountMessage.getId()) {
						message = adminAccountMessage.getMessage();
						
						break;
					}
				}
			}
		}
		
		return view(modelMap, viewName, pageName, message);
	}
	
	@GetMapping("department")
	public String department(ModelMap modelMap) {
		viewName = "admin/index";
		pageName = "department.jsp";
		message = "";
		
		return view(modelMap, viewName, pageName, message);
	}
	
	@GetMapping("staffPermission")
	public String staffPermission(ModelMap modelMap) {
		viewName = "admin/index";
		pageName = "staffpermission.jsp";
		message = "";
		
		return view(modelMap, viewName, pageName, message);
	}
}
