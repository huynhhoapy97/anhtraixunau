package vn.com.anhtraixunau.staff.controllers;

import java.util.UUID;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import vn.com.anhtraixunau.enums.StaffAccountMessage;
import vn.com.anhtraixunau.models.StaffAccount;
import vn.com.anhtraixunau.services.RedisService;
import vn.com.anhtraixunau.services.StaffAccountService;

@Controller
@RequestMapping("staffpassword")
public class StaffPasswordController {
	private StaffAccountService staffAccountService;
	
	@Autowired
	private RedisService redisService;
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private Gson gson;
	
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
	
	@GetMapping("changepassword")
	public String changePassword(ModelMap modelMap, @RequestParam("staffaccountid") Integer staffAccountId,
			@RequestParam("staffaccountusername") String staffAccountUsername,
			@RequestParam("staffaccounttoken") String staffAccountToken) {
		viewName = "staff/index";
		pageName = "changepassword.jsp";
		message = "";
		staffAccountService = new StaffAccountService();
		
		// Kiểm tra mã token có trùng với mã token của tài khoản đó trong bảng verify không?
		// hoặc tài khoản đó có tồn tại trong bảng verify không?
		int result = staffAccountService.checkStaffAccountVerify(staffAccountId, staffAccountToken);
		if (result == -1) {
			pageName = "error.jsp";
		}
		
		StaffAccount staffAccount = new StaffAccount();
		staffAccount.setId(staffAccountId);
		staffAccount.setUsername(staffAccountUsername);
		
		modelMap.addAttribute("staffAccount", staffAccount);
		
		return view(modelMap, viewName, pageName, message);
	}
	
	@PostMapping("changepassword")
	public String changePassword(@Validated @ModelAttribute("staffAccount") StaffAccount staffAccount, BindingResult bindingResult, ModelMap modelMap) {
		viewName = "staff/index";
		pageName = "login.jsp";
		message = "";
		staffAccountService = new StaffAccountService();
		
		if (bindingResult.hasErrors()) {
			pageName = "error.jsp";
		}
		else {
			staffAccountService = new StaffAccountService();
			int result = staffAccountService.changePassword(staffAccount);
			
			for (StaffAccountMessage staffAccountMessage : StaffAccountMessage.values()) {
				if (result == staffAccountMessage.getId()) {
					pageName = "changepassword.jsp";
					message = staffAccountMessage.getMessage();
					
					break;
				}
			}
		}
		
		modelMap.addAttribute("staffAccount", staffAccount);
		
		return view(modelMap, viewName, pageName, message);
	}
	
	@GetMapping("forgetpassword")
	public String forgetPassword(ModelMap modelMap) {
		viewName = "staff/index";
		pageName = "forgetpassword.jsp";
		message = "";
		
		StaffAccount staffAccount = new StaffAccount();
		modelMap.addAttribute("staffAccount", staffAccount);
		
		return view(modelMap, viewName, pageName, message);
	}
	
	@PostMapping("forgetpassword")
	public String forgetPassword(@Validated @ModelAttribute("staffAccount") StaffAccount staffAccount, BindingResult bindingResult
			,ModelMap modelMap, HttpServletRequest httpServletRequest) {
		viewName = "staff/index";
		pageName = "login.jsp";
		message = "Đã gửi email xác thực đổi mật khẩu";
		staffAccountService = new StaffAccountService();
		modelMap.addAttribute("staffAccount", staffAccount);
		
		if (bindingResult.hasErrors()) {
			pageName = "forgetpassword.jsp";
			message = "Có lỗi với dữ liệu";
		}
		else {
			String email = staffAccount.getEmail();
			
			// Lấy thông tin StaffAccoutn trong Redis. Nếu không có lấy từ Database
			Object result = redisService.getValue(email);
			System.out.println("result: " + result);
			
			if (result != null) {
				staffAccount = gson.fromJson((String) result, StaffAccount.class);
				System.out.println("staffAccountId: " + staffAccount.getId());
			}
			else {
				staffAccount = staffAccountService.getStaffAccountByEmail(email);
			}
			
			if (staffAccount.getUsername() != null || !staffAccount.getUsername().equals("")) {
				try {
					String token = UUID.randomUUID().toString();
					String staffAccountUsername = staffAccount.getUsername();
					int staffAccountId = staffAccount.getId();
					String mailFrom = "javamailsender97@gmail.com";
					String queryString = "/staffpassword/forgetpasswordconfirm?staffaccountid=" + staffAccountId 
							+ "&staffAccountUsername=" + staffAccountUsername
							+ "&token=" + token;
					String href = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + 
							  httpServletRequest.getServerPort() + queryString;
					String contentText = "<a href='" + href + "'>Click link</a>";
					
					// Gửi mail
					MimeMessage mimeMessage = mailSender.createMimeMessage();
					MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
					
					mimeMessageHelper.setFrom(mailFrom, "AnhTraiXuNau");
					mimeMessageHelper.setReplyTo(mailFrom, "AnhTraiXuNau");
					mimeMessageHelper.setTo(email);
					mimeMessageHelper.setSubject("Change staff account password");
					
					mimeMessage.setContent(contentText, "text/html");
					
					mailSender.send(mimeMessage);
					
					// Lưu token vào Redis
					redisService.setValue(token, email, 24*60);
					
					// Lưu thông tin StaffAccount vào Redis
					redisService.setValue(email, staffAccount, 24*60);
				}
				catch (Exception e) {
					e.printStackTrace();
					
					pageName = "forgetpassword.jsp";
					message = "Lỗi gửi email";
				}
			}
			else {
				pageName = "forgetpassword.jsp";
				message = "Email không tồn tại";
			}
		}
		
		return view(modelMap, viewName, pageName, message);
	}
	
	@GetMapping("forgetpasswordconfirm")
	public String forgetPasswordConfirm(ModelMap modelMap, @RequestParam("staffaccountid") Integer staffAccountId,
			@RequestParam("staffAccountUsername") String staffAccountUsername,
			@RequestParam("token") String token) {
		viewName = "staff/index";
		pageName = "changepassword.jsp";
		message = "";
		staffAccountService = new StaffAccountService();
		
		Object result = redisService.getValue(token);
		System.out.println("result: " + result);
		
		if (result == null) {
			// Không tìm thấy mã token gửi về email để đổi mật khẩu trong Redis
			// Chuyển về trang quên mật khẩu để gửi lại mail
			pageName = "forgetpassword.jsp";
			message = "Mã xác nhận đã hết hiệu lực. Vui lòng gửi lại mail";
		}
		
		StaffAccount staffAccount = new StaffAccount();
		staffAccount.setId(staffAccountId);
		staffAccount.setUsername(staffAccountUsername);
		
		modelMap.addAttribute("staffAccount", staffAccount);
		
		return view(modelMap, viewName, pageName, message);
	}
}
