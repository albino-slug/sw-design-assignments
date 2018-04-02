package com.pingpong.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pingpong.model.User;

public class UserValidator implements Validator<User> {
	
	private static UserValidator instance;
	
	public static UserValidator instance(){
		if (instance == null){
			instance = new UserValidator();
		}
		return (instance);
	}

	public int validate(User u) {
	    Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	    Matcher matcher = emailPattern.matcher(u.getEmail());
		if (matcher.find() == false){
			System.out.println("[Validator]Invalid user e-mail;");
			return 1;
		}
		
		String usernamePattern = "[a-zA-Z0-9\\._\\-]{4,}";
	    if (u.getUsername().matches(usernamePattern) == false){
			System.out.println("[Validator]Invalid username;");
			return 2;
		}
		
	    String passPattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
	    if (u.getPass().matches(passPattern) == false){
			System.out.println("[Validator]Invalid user password;");
			return 3;
		}
		return 0;
	}
}