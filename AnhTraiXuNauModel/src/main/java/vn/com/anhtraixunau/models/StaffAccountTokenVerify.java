package vn.com.anhtraixunau.models;

public class StaffAccountTokenVerify {
	private Integer id;
	private String token;
	
	public StaffAccountTokenVerify() {
		super();
	}

	public StaffAccountTokenVerify(Integer id, String token) {
		super();
		this.id = id;
		this.token = token;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
