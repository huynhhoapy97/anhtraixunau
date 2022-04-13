package vn.com.anhtraixunau.models;

import java.util.Date;
import java.util.List;

public class StaffAccount {
	private Integer id;
	private String username;
	private String password;
	private String repassword;
	private Date createDate;
	private String createUser;
	private Date updateDate;
	private String updateUser;
	private Date deleteDate;
	private String deleteUser;
	private Integer isDelete;
	private String email;
	private Staff staff;
	private StaffAccountTokenVerify staffAccountTokenVerify;
	private List<StaffPermission> listStaffPermission;

	public StaffAccount() {
		super();
	}

	public StaffAccount(Integer id, String username, String password, Date createDate, String createUser,
			Date updateDate, String updateUser, Date deleteDate, String deleteUser, Integer isDelete) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.createDate = createDate;
		this.createUser = createUser;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		this.deleteDate = deleteDate;
		this.deleteUser = deleteUser;
		this.isDelete = isDelete;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public String getDeleteUser() {
		return deleteUser;
	}

	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public StaffAccountTokenVerify getStaffAccountTokenVerify() {
		return staffAccountTokenVerify;
	}

	public void setStaffAccountTokenVerify(StaffAccountTokenVerify staffAccountTokenVerify) {
		this.staffAccountTokenVerify = staffAccountTokenVerify;
	}

	public List<StaffPermission> getListStaffPermission() {
		return listStaffPermission;
	}

	public void setListStaffPermission(List<StaffPermission> listStaffPermission) {
		this.listStaffPermission = listStaffPermission;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
}
