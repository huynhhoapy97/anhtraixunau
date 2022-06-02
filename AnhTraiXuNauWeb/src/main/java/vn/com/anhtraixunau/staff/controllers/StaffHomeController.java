package vn.com.anhtraixunau.staff.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.com.anhtraixunau.models.Category;
import vn.com.anhtraixunau.models.StaffAccount;
import vn.com.anhtraixunau.services.CategoryService;

@Controller
@RequestMapping("staff")
public class StaffHomeController {
	private String viewName;
	private String pageName;
	private String message;
	
	private CategoryService categoryService;
	
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
	public String index(ModelMap modelMap, HttpServletRequest httpServletRequest) {
		viewName = "staff/index";
		pageName = "home.jsp";
		message = "";
		
		HttpSession httpSession = httpServletRequest.getSession();
		if (httpSession.getAttribute("username") != null) {
			return "redirect:dashboard";
		}
		
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
	public String dashboard(ModelMap modelMap, HttpServletRequest httpServletRequest) {
		viewName = "staff/dashboard";
		pageName = "";
		message = "";
		
		HttpSession httpSession = httpServletRequest.getSession();
		if (httpSession.getAttribute("username") == null) {
			return "redirect:login";
		}
		
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
	
	@GetMapping("category")
	public String category(ModelMap modelMap, HttpServletRequest httpServletRequest) {
		viewName = "staff/index";
		pageName = "category.jsp";
		message = "";
		
		HttpSession httpSession = httpServletRequest.getSession();
		if (httpSession.getAttribute("username") == null) {
			return "redirect:login";
		}
		
		return view(modelMap, viewName, pageName, message);
	}
	
	@GetMapping("category/previewDescription/{categoryId}")
	public String categoryPreviewDescription(ModelMap modelMap, HttpServletRequest httpServletRequest, 
			@PathVariable("categoryId") int categoryId) {
		viewName = "staff/categorypreviewdescription";
		pageName = "";
		message = "";
		
		String description = "";
		List<Category> listCategoryInformation = new ArrayList<Category>();
		categoryService = new CategoryService();
		
		HttpSession httpSession = httpServletRequest.getSession();
		if (httpSession.getAttribute("username") == null) {
			return "redirect:login";
		}
		
		listCategoryInformation = categoryService.getListCategoryInformation();
		if (listCategoryInformation != null && listCategoryInformation.size() > 0) {
			for (Category category : listCategoryInformation) {
				if (category.getId() == categoryId) {
					description = category.getDescription();
					
					break;
				}
			}
		}
		
		modelMap.addAttribute("description", description);
		
		return view(modelMap, viewName, pageName, message);
	}
	
	@GetMapping("brand")
	public String brand(ModelMap modelMap, HttpServletRequest httpServletRequest) {
		viewName = "staff/index";
		pageName = "brand.jsp";
		message = "";
		
		HttpSession httpSession = httpServletRequest.getSession();
		if (httpSession.getAttribute("username") == null) {
			return "redirect:login";
		}
		
		return view(modelMap, viewName, pageName, message);
	}
}
