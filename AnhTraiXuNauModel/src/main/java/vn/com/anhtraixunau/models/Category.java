package vn.com.anhtraixunau.models;

import java.util.Date;

public class Category {
	private Integer id;
	private String name;
	private String description;
	private String createUser;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String deleteUser;
	private Date deleteDate;
	private Integer isDelete;
	
	public Category() {
		super();
	}

	public Category(Integer id, String name, String description, String createUser, Date createDate, String updateUser,
			Date updateDate, String deleteUser, Date deleteDate, Integer isDelete) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.createUser = createUser;
		this.createDate = createDate;
		this.updateUser = updateUser;
		this.updateDate = updateDate;
		this.deleteUser = deleteUser;
		this.deleteDate = deleteDate;
		this.isDelete = isDelete;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDeleteUser() {
		return deleteUser;
	}

	public void setDeleteUser(String deleteUser) {
		this.deleteUser = deleteUser;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}
