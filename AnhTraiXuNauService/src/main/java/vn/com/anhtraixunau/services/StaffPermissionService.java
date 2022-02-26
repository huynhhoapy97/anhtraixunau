package vn.com.anhtraixunau.services;

import java.util.ArrayList;
import java.util.List;

import vn.com.anhtraixunau.models.StaffPermission;
import vn.com.anhtraixunau.repositories.StaffPermissionDAO;
import vn.com.anhtraixunau.repositories.StaffPermissionDAOImpl;

public class StaffPermissionService {
	private StaffPermissionDAO staffPermissionDAO;
	
	public List<StaffPermission> getListStaffPermissionExisting(){
		List<StaffPermission> listStaffPermission = new ArrayList<StaffPermission>();
		staffPermissionDAO = new StaffPermissionDAOImpl();
		
		try {
			listStaffPermission = staffPermissionDAO.getListStaffPermissionExisting();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listStaffPermission;
	}
	
	public StaffPermission getStaffPermissionById(int id) {
		StaffPermission staffPermission = new StaffPermission();
		staffPermissionDAO = new StaffPermissionDAOImpl();
		
		try {
			staffPermission = staffPermissionDAO.getStaffPermissionById(id);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return staffPermission;
	}
	
	public int insertStaffPermission(String name, String userName) {
		int result = -1;
		staffPermissionDAO = new StaffPermissionDAOImpl();
		
		try {
			result = staffPermissionDAO.insertStaffPermission(name, userName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int updateStaffPermission(int id, String name, String userName) {
		int result = -1;
		staffPermissionDAO = new StaffPermissionDAOImpl();
		
		try {
			result = staffPermissionDAO.updateStaffPermission(id, name, userName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int deleteStaffPermissionById(int id, String userName) {
		int result = -1;
		staffPermissionDAO = new StaffPermissionDAOImpl();
		
		try {
			result = staffPermissionDAO.deleteStaffPermissionById(id, userName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
