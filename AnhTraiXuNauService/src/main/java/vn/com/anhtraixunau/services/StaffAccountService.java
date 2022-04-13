package vn.com.anhtraixunau.services;

import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import vn.com.anhtraixunau.enums.StaffAccountMessage;
import vn.com.anhtraixunau.models.StaffAccount;
import vn.com.anhtraixunau.repositories.StaffAccountDAO;
import vn.com.anhtraixunau.repositories.StaffAccountDAOImpl;

public class StaffAccountService {
	private StaffAccountDAO staffAccountDAO;
	
	public List<Integer> getListStaffAccountPermissionByStaffId(int staffId) {
		List<Integer> listStaffAccountPermission = new ArrayList<Integer>();
		StaffAccount staffAccount = new StaffAccount();
		staffAccountDAO = new StaffAccountDAOImpl();
		
		try {
			staffAccount = staffAccountDAO.getStaffAccountByStaffId(staffId);
			if (staffAccount.getId() != StaffAccountMessage.STAFFACCOUNTID_ISNULL.getId()) {
				listStaffAccountPermission = staffAccountDAO.getListStaffAccountPermissionByStaffAccountId(staffAccount.getId());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listStaffAccountPermission;
	}

	public StaffAccount getStaffAccountByStaffId(int staffId) {
		StaffAccount staffAccount = new StaffAccount();
		staffAccountDAO = new StaffAccountDAOImpl();
		
		try {
			staffAccount = staffAccountDAO.getStaffAccountByStaffId(staffId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return staffAccount;
	}
	
	public int insertStaffAccount(String accountUsername, String accountEmail, int staffId, String userName) {
		int result = -1;
		staffAccountDAO = new StaffAccountDAOImpl();
		
		try {
			result = staffAccountDAO.insertStaffAccount(accountUsername, accountEmail, staffId, userName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int insertStaffAccountPermission(int staffAccountId, int staffAccountPermissionId) {
		int result = -1;
		staffAccountDAO = new StaffAccountDAOImpl();
		
		try {
			result = staffAccountDAO.insertStaffAccountPermission(staffAccountId, staffAccountPermissionId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int removeStaffAccountPermission(int staffAccountId, int staffAccountPermissionId) {
		int result = -1;
		staffAccountDAO = new StaffAccountDAOImpl();
		
		try {
			result = staffAccountDAO.removeStaffAccountPermission(staffAccountId, staffAccountPermissionId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteStaffAccountById(int id, String userName) {
		int result = -1;
		staffAccountDAO = new StaffAccountDAOImpl();
		
		try {
			result = staffAccountDAO.deleteStaffAccountById(id, userName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteStaffAccountPermissionByStaffAccountId(int staffAccountId) {
		int result = -1;
		staffAccountDAO = new StaffAccountDAOImpl();
		
		try {
			result = staffAccountDAO.deleteStaffAccountPermissionByStaffAccountId(staffAccountId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<StaffAccount> getListStaffAccountByUsername(String username) {
		List<StaffAccount> listStaffAccount = new ArrayList<StaffAccount>();
		staffAccountDAO = new StaffAccountDAOImpl();
		
		try {
			listStaffAccount = staffAccountDAO.getListStaffAccountByUsername(username);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listStaffAccount;
	}
	
	public List<Integer> getListPermissionIdByStaffAccountId(int staffAccountId){
		List<Integer> listDepartmentPermission = new ArrayList<Integer>();
		staffAccountDAO = new StaffAccountDAOImpl();
		
		try {
			listDepartmentPermission = staffAccountDAO.getListStaffAccountPermissionByStaffAccountId(staffAccountId);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listDepartmentPermission;
	}

	public int updateStaffAccount(String accountUsername, String accountEmail, String userName) {
		int result = -1;
		staffAccountDAO = new StaffAccountDAOImpl();
		
		try {
			result = staffAccountDAO.updateStaffAccount(accountUsername, accountEmail, userName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int insertStaffAccountTokenVerify(int staffAccountId, String staffAccountToken) {
		int result = -1;
		staffAccountDAO = new StaffAccountDAOImpl();
		
		try {
			result = staffAccountDAO.insertStaffAccountTokenVerify(staffAccountId, staffAccountToken);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int changePassword(StaffAccount staffAccount) {
		int result = -1;
		String username;
		String password;
		String repassword;
		
		staffAccountDAO = new StaffAccountDAOImpl();
		
		try {
			username = staffAccount.getUsername();
			password = staffAccount.getPassword();
			repassword = staffAccount.getRepassword();
			
			if (password.trim().equals("")) {
				return StaffAccountMessage.MESSAGE_NOTICE_NULL_PASSWORD.getId();
			}
			if (repassword.trim().equals("")) {
				return StaffAccountMessage.MESSAGE_NOTICE_NULL_REPASSWORD.getId();
			}
			if (!password.equals(repassword)) {
				return StaffAccountMessage.MESSAGE_NOTICE_NOMATCH_PASSWORD.getId();
			}
			
			password = BCrypt.hashpw(password, BCrypt.gensalt());
			
			result = staffAccountDAO.changePassword(username, password);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int checkStaffAccountVerify(Integer staffAccountId, String staffAccountToken) {
		int result = -1;
		staffAccountDAO = new StaffAccountDAOImpl();
		
		try {
			result = staffAccountDAO.checkStaffAccountVerify(staffAccountId, staffAccountToken);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public StaffAccount getStaffAccountByEmail(String email) {
		StaffAccount staffAccount = new StaffAccount();
		staffAccountDAO = new StaffAccountDAOImpl();
		
		try {
			staffAccount = staffAccountDAO.getStaffAccountByEmail(email);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return staffAccount;
	}
}
