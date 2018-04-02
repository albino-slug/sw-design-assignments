package com.pingpong;

import com.pingpong.dao.UserDAO;
import com.pingpong.model.User;

public class LoginProcessing {
	private String email;
    private String username;
    private String password;
    private boolean isadmin;
    private UserDAO userDAO = new UserDAO();
    
    public enum LOGIN_STATE {
        INCORRECT_PASSWORD,
        INEXISTENT_EMAIL,
        INCORRECT_EMAIL,
        NOT_ADMIN,
        SUCCESS,
    }

    public LoginProcessing(String email, String password, boolean isadmin) {
        this.email = email;
        this.password = password;
        this.isadmin = isadmin;
    }
    
    public LOGIN_STATE processLogin(){
    	User user = userDAO.findByEmail(email);
    	return authenticate(user);
    }
    
    public LOGIN_STATE authenticate(User user){
    	if (user == null) return LOGIN_STATE.INEXISTENT_EMAIL;
        if (!user.getEmail().equals(email)) return LOGIN_STATE.INCORRECT_EMAIL;
        if (isadmin && !user.getIsAdmin()) return LOGIN_STATE.NOT_ADMIN;
        if (!user.getPass().equals(password)) return LOGIN_STATE.INCORRECT_PASSWORD;
        
        username = user.getUsername();

    	return LOGIN_STATE.SUCCESS;
    }
    
    public String getUsername() {
        return username;
    }
}
