package org.bean;

public class User {

	private String username;

	private String account;
	
	private String password;

	private String token;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(String account, String password) {
		this.account = account;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
