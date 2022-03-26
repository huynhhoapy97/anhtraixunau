package vn.com.anhtraixunau.repositories;

import java.sql.Date;
import java.util.List;

import vn.com.anhtraixunau.models.Staff;

public interface StaffDAO {
	public List<Staff> getListStaffInformation();
	public int insertStaff(String lastName, String firstName, int departmentId, Date dateStart, String userName);
	public int updateStaff(int staffId, String lastName, String firstName, int departmentId, Date dateStart, String userName);
	public int deleteStaff(int id, String userName);
}
