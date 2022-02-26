package vn.com.anhtraixunau.repositories;

import java.util.List;

import vn.com.anhtraixunau.models.StaffPermission;

public interface StaffPermissionDAO {
	public List<StaffPermission> getListStaffPermissionExisting();
	public StaffPermission getStaffPermissionById(int id);
	public int insertStaffPermission(String name, String userName);
	public int updateStaffPermission(int id, String name, String userName);
	public int deleteStaffPermissionById(int id, String userName);
}
