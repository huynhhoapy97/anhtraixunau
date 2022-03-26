package vn.com.anhtraixunau.models;

import java.util.Date;

public class Staff {
	private Integer id;
	private String firstName;
	private String lastName;
	private Date dateStart;
	private Date dateOff;
	private Date createDate;
	private String createUser;
	private Date updateDate;
	private String updateUser;
	private Date deleteDate;
	private String deleteUser;
	private Integer isDelete;
	private Department department;
	private StaffAccount staffAccount;
	
	public Staff() {
		super();
	}

	public Staff(Integer id, String firstName, String lastName, Date createDate, String createUser, Date updateDate,
			String updateUser, Date deleteDate, String deleteUser, Integer isDelete) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public StaffAccount getStaffAccount() {
		return staffAccount;
	}

	public void setStaffAccount(StaffAccount staffAccount) {
		this.staffAccount = staffAccount;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateOff() {
		return dateOff;
	}

	public void setDateOff(Date dateOff) {
		this.dateOff = dateOff;
	}
}
