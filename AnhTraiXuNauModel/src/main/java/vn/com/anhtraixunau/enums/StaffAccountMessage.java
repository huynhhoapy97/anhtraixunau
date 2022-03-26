package vn.com.anhtraixunau.enums;

public enum StaffAccountMessage {
	DOES_NOT_EXISTS_STAFFACCOUNTID(-1, "Không tồn tại"),
	STAFFACCOUNTID_ISNULL(null, "NULL");
	
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
	
	private StaffAccountMessage(Integer id, String message) {
		this.id = id;
		this.message = message;
	}
}
