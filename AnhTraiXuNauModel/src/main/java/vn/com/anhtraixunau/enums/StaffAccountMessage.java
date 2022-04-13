package vn.com.anhtraixunau.enums;

public enum StaffAccountMessage {
	DOES_NOT_EXISTS_STAFFACCOUNTID(-1, "Không tồn tại"),
	STAFFACCOUNTID_ISNULL(-10, "NULL"),
	MESSAGE_NOTICE_NULL_PASSWORD(-9, "Vui lòng nhập mật khẩu thay đổi"),
	MESSAGE_NOTICE_NULL_REPASSWORD(-8, "Vui lòng nhập mật khẩu xác nhận"),
	MESSAGE_NOTICE_NOMATCH_PASSWORD(-7, "Mật khẩu xác nhận không khớp với mật khẩu thay đổi");
	
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
