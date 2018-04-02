package com.pingpong.validation;

import com.pingpong.dao.UserDAO;

public class LoginValidator {
	static UserDAO udao = new UserDAO();
	
	public boolean inexistentUsername(String username){
		if (udao.findByUsername(username) != null){
			return false;
		}
		return true;
	}
	
	public boolean wrongPassword(String username, String password){
		if (udao.findByUsername(username).getPass().equals(password)){
			return false;
		}
		return true;
	}

}
