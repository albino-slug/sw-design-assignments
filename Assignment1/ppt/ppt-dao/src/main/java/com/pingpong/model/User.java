package com.pingpong.model;

public class User {
	private int		userID;
	private String 	password;

	private String	email;
	private Boolean	isadmin;
	private String	username;
	
	public User(int userID, String email, String username, String password, Boolean isadmin) {
		super();
		this.userID = userID;
		this.email = email;
		this.username = username;
		this.password = password;
		this.isadmin = isadmin;
	}

	public User(String email, String username, String password, Boolean isadmin) {
		super();
		this.password = password;
		this.username = username;
		this.email = email;
		this.isadmin = isadmin;
	}

	public User() {
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", email=" + email + ", username=" + username + ", password=" + password 
				+ ", is admin=" + isadmin + "]";
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getPass() {
		return password;
	}

	public void setPass(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getIsAdmin() {
		return isadmin;
	}

	public void setIsAdmin(Boolean isadmin) {
		this.isadmin = isadmin;
	}
	
}