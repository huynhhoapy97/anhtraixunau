package vn.com.anhtraixunau.repositories;

import java.util.List;

import vn.com.anhtraixunau.models.StaffAccount;

public interface StaffAccountDAO {
	public StaffAccount getStaffAccountByStaffId(int staffId);
	public List<Integer> getListStaffAccountPermissionByStaffAccountId(int staffAccountId);
	public int insertStaffAccount(String accountUsername, int staffId, String userName);
	public int insertStaffAccountPermission(int staffAccountId, int staffAccountPermissionId);
	public int removeStaffAccountPermission(int staffAccountId, int staffAccountPermissionId);
	public int deleteStaffAccountById(int id, String userName);
	public int deleteStaffAccountPermissionByStaffAccountId(int staffAccountId);
}
