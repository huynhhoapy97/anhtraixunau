package vn.com.anhtraixunau.enums;

public enum AdminAccountMessage {
	MESSAGE_NOTICE_NULL_USERNAME(-11, "Vui lòng nhập tên tài khoản"),
	MESSAGE_NOTICE_NULL_PASSWORD(-10, "Vui lòng nhập mật khẩu thay đổi"),
	MESSAGE_NOTICE_NULL_REPASSWORD(-9, "Vui lòng nhập mật khẩu xác nhận"),
	MESSAGE_NOTICE_NULL_CONFIRMPASSWORD(-8, "Vui lòng nhập mật khẩu"),
	MESSAGE_NOTICE_NOMATCH_CONFIRMPASSWORD(-7, "Mật khẩu không đúng"),
	MESSAGE_NOTICE_NOMATCH_PASSWORD(-6, "Mật khẩu xác nhận không khớp với mật khẩu thay đổi"),
	MESSAGE_ERROR_CONFIRMPASSWORD(-5, "Lỗi đăng nhập tài khoản"),
	MESSAGE_ERROR_CHANGEPASSWORD(-4, "Lỗi thay đổi mật khẩu cho lần đầu đăng nhập"),
	MESSAGE_ERROR_CHECKACCOUNTEXISTS(-3, "Lỗi kiểm tra thông tin tài khoản"),
	MESSAGE_ERROR_EXISTS(-2, "Tài khoản không tồn tại"),
	MESSAGE_ERROR_PERMISSION(-1, "Tài khoản không có quyền truy cập"),
	MESSAGE_ERROR_PASSWORD(0, ""),
	MESSAGE_NO_ERROR(1, "");
	
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
	
	private AdminAccountMessage(Integer id, String message) {
		this.id = id;
		this.message = message;
	}
}
