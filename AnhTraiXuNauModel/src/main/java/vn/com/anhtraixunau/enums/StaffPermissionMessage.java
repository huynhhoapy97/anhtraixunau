package vn.com.anhtraixunau.enums;

public enum StaffPermissionMessage {
	DOES_NOT_EXISTS_STAFFPERMISSIONID(-1, "Không tồn tại");
	
	private Integer id;
	private String message;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	private StaffPermissionMessage(Integer id, String message) {
		this.id = id;
		this.message = message;
	}
}
