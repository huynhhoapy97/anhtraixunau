package vn.com.anhtraixunau.enums;

public enum StaffMessage {
	DOES_NOT_EXISTS_STAFFID(-1, "Không tồn tại");
	
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
	
	private StaffMessage(Integer id, String message) {
		this.id = id;
		this.message = message;
	}
}
