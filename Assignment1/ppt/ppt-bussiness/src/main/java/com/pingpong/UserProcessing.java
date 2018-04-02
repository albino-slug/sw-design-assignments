package com.pingpong;

import java.util.ArrayList;

import com.pingpong.dao.UserDAO;
import com.pingpong.model.User;
import com.pingpong.validation.UserValidator;

public class UserProcessing {
private UserDAO uDAO = new UserDAO();
	
	private static UserProcessing instance;
	
	public static UserProcessing instance(){
		if(instance == null){
			instance = new UserProcessing();
		}
		return (instance);
	}

	public int insertUser(User u){
		int id = 0;
		if (UserValidator.instance().validate(u) == 0){
			id = uDAO.insert(u);
		}
		else {
			System.out.println("[UserProcessing]Could not insert user because of validation reasons;");
		}
		return id;
	}
	
	public void updateUser(User u){
		if ((UserValidator.instance().validate(u) > 0) && (UserDAO.findById(u.getUserID()) != null)){
			uDAO.update(u);
		}
		else {
			System.out.println("[UserProcessing]Could not update client because of validation reasons;");
			System.out.println("[UserProcessing]validate " + (UserValidator.instance().validate(u)));
			System.out.println("[UserProcessing]find by id " + u.getUserID() + " == " + uDAO.findById(u.getUserID()));
		}
	}
	
	public void deleteUser(User u){
		if (uDAO.findById(u.getUserID()) != null){
			uDAO.delete(u);
		}
		else {
			System.out.println("[UserProcessing] Could not delete user because of validation reasons;");
		}
	}
	
	//needed for TableDisplayer compatibility
	public ArrayList<Object> selectAll(){
		ArrayList<Object> objects = new ArrayList<Object>();
		ArrayList<User> users = UserDAO.selectAll();
		for (User u : users){
			objects.add(u);
		}
		return objects;
	}
	
	public User getById(int id){
		return (UserDAO.findById(id));
	}
}
