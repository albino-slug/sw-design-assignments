package common.model.builder;

import common.model.Role;
import common.model.User;

public class UserBuilder {
	private int id;
	private String username;
	private String password;
	private Role role;
	
	private UserBuilder(){
		
	}
	
	public static UserBuilder anUser(){ return new UserBuilder();}
	
	public UserBuilder withId(int id){
		this.id = id;
		return this;
	}
	public UserBuilder withUsername(String username){
		this.username = username;
		return this;
	}
	public UserBuilder withPassword(String password){
		this.password = password;
		return this;
	}
	public UserBuilder withRole(Role role){
		this.role = role;
		return this;
	}
	public User build(){
		User user = new User();
		user.setId(this.id);
		user.setPassword(this.password);
		user.setRole(this.role);
		user.setUsername(this.username);
		return user;
	}
}
