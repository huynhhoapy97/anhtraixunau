package vn.com.anhtraixunau.services;

import org.mindrot.jbcrypt.BCrypt;

import vn.com.anhtraixunau.enums.AdminAccountMessage;
import vn.com.anhtraixunau.models.AdminAccount;
import vn.com.anhtraixunau.repositories.AdminAccountDAO;
import vn.com.anhtraixunau.repositories.AdminAccountDAOImpl;

public class AdminAccountService {
	private AdminAccountDAO adminAccountDAO;
	
	private String username;
	private String password;
	private String repassword;
	
	public int checkAccountExists(AdminAccount adminAccount) {
		try {
			username = adminAccount.getUsername();
			password = adminAccount.getPassword();
			
			if(username.trim().equals("")) {
				return AdminAccountMessage.MESSAGE_NOTICE_NULL_USERNAME.getId();
			}
			
			adminAccountDAO = new AdminAccountDAOImpl();
			int result = adminAccountDAO.checkAccountExists(username, password);
			
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();
			
			return AdminAccountMessage.MESSAGE_ERROR_CHECKACCOUNTEXISTS.getId();
		}
	}
	
	public int changePassword(AdminAccount adminAccount) {
		try {
			username = adminAccount.getUsername();
			password = adminAccount.getPassword();
			repassword = adminAccount.getRepassword();

			if (password.trim().equals("")) {
				return AdminAccountMessage.MESSAGE_NOTICE_NULL_PASSWORD.getId();
			}
			if (repassword.trim().equals("")) {
				return AdminAccountMessage.MESSAGE_NOTICE_NULL_REPASSWORD.getId();
			}
			if (!password.equals(repassword)) {
				return AdminAccountMessage.MESSAGE_NOTICE_NOMATCH_PASSWORD.getId();
			}
			
			password = BCrypt.hashpw(password, BCrypt.gensalt());
			
			adminAccountDAO = new AdminAccountDAOImpl();
			int result = adminAccountDAO.changePassword(username, password);
			
			return result;
		}
		catch (Exception e) {
			e.printStackTrace();
			
			return AdminAccountMessage.MESSAGE_ERROR_CHANGEPASSWORD.getId();
		}
	}
	
	public int confirmPassword(AdminAccount adminAccount) {
		try {
			username = adminAccount.getUsername();
			password = adminAccount.getPassword();
			
			if (password.trim().equals("")) {
				return AdminAccountMessage.MESSAGE_NOTICE_NULL_CONFIRMPASSWORD.getId();
			}
			
			// Vì mỗi lần hash password theo brcypt sẽ cho ra 1 mật khẩu khác nhau
			// Nên không thể hash password rồi truyền vào store để so sánh
			// Mà phải truyền username vào store để lấy thông tin password (password lưu trong DB là 1 chuỗi đã mã hóa)
			// Lấy password từ store trả ra để giải mã và so sánh với password từ Web truyền sang
			adminAccountDAO = new AdminAccountDAOImpl();
			AdminAccount result = adminAccountDAO.getInformationByUsername(username);
			
			String passwordBcrypt = result.getPassword();
			if (passwordBcrypt != null) {
				if (BCrypt.checkpw(password, passwordBcrypt)) {
					return 1;
				}
				else {
					return AdminAccountMessage.MESSAGE_NOTICE_NOMATCH_CONFIRMPASSWORD.getId();
				}
			}
			else {
				return AdminAccountMessage.MESSAGE_ERROR_CONFIRMPASSWORD.getId();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			
			return AdminAccountMessage.MESSAGE_ERROR_CONFIRMPASSWORD.getId();
		}
	}
}
