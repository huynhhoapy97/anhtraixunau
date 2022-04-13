package vn.com.anhtraixunau.repositories;

import java.util.List;

import vn.com.anhtraixunau.models.StaffAccount;

public interface StaffAccountDAO {
	public StaffAccount getStaffAccountByStaffId(int staffId);
	public List<Integer> getListStaffAccountPermissionByStaffAccountId(int staffAccountId);
	public int insertStaffAccount(String accountUsername, String accountEmail, int staffId, String userName);
	public int insertStaffAccountPermission(int staffAccountId, int staffAccountPermissionId);
	public int removeStaffAccountPermission(int staffAccountId, int staffAccountPermissionId);
	public int deleteStaffAccountById(int id, String userName);
	public int deleteStaffAccountPermissionByStaffAccountId(int staffAccountId);
	public List<StaffAccount> getListStaffAccountByUsername(String username);
	public int updateStaffAccount(String accountUsername, String accountEmail, String userName);
	public int insertStaffAccountTokenVerify(int staffAccountId, String staffAccountToken);
	public int changePassword(String username, String password);
	public int checkStaffAccountVerify(Integer staffAccountId, String staffAccountToken);
	public String getMessageRedis(String code);
	public StaffAccount getStaffAccountByEmail(String email);
}
