package vn.com.anhtraixunau.services;

import java.util.ArrayList;
import java.util.List;

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
	
	public int insertStaffAccount(String accountUsername, int staffId, String userName) {
		int result = -1;
		staffAccountDAO = new StaffAccountDAOImpl();
		
		try {
			result = staffAccountDAO.insertStaffAccount(accountUsername, staffId, userName);
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
}
