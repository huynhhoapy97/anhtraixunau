package vn.com.anhtraixunau.repositories;

import vn.com.anhtraixunau.models.AdminAccount;

public interface AdminAccountDAO {
	public int checkAccountExists(String username, String password);
	public int changePassword(String username, String password);
	public AdminAccount getInformationByUsername(String username);
}
