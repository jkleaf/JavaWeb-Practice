package bean;

public class User {

	private String id;

	private String name;

	private String pwd;

	private String email;

	private String phonenum;

	public User() {
	}

	public User(String id, String name, String pwd, String email, String phonenum) {
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.email = email;
		this.phonenum = phonenum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}

}
