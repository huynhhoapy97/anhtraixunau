package vn.com.anhtraixunau.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import vn.com.anhtraixunau.models.Staff;
import vn.com.anhtraixunau.repositories.StaffDAO;
import vn.com.anhtraixunau.repositories.StaffDAOImpl;

public class StaffService {
	private StaffDAO staffDAO;
	
	public List<Staff> getListStaffInformation(){
		List<Staff> listStaffInformation = new ArrayList<Staff>();
		staffDAO = new StaffDAOImpl();
		
		try {
			listStaffInformation = staffDAO.getListStaffInformation();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return listStaffInformation;
	}

	public int insertStaff(String lastName, String firstName, int departmentId, Date dateStart, String userName) {
		int result = -1;
		staffDAO = new StaffDAOImpl();
		
		try {
			result = staffDAO.insertStaff(lastName, firstName, departmentId, dateStart, userName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int updateStaff(int staffId, String lastName, String firstName, int departmentId, Date dateStart, String userName) {
		int result = -1;
		staffDAO = new StaffDAOImpl();
		
		try {
			result = staffDAO.updateStaff(staffId, lastName, firstName, departmentId, dateStart, userName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public int deleteStaffById(int id, String userName) {
		int result = -1;
		staffDAO = new StaffDAOImpl();
		
		try {
			result = staffDAO.deleteStaff(id, userName);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
