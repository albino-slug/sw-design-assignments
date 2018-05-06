package server.repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import common.model.User;
import storage.UserStorageManager;

public class UserRepo {
	private List<User> users;
	private final UserStorageManager userStorageManager = new UserStorageManager();
	
	public UserRepo(String storagePath){
		loadUsers(storagePath);
	}


	public void loadUsers(String storagePath) {
		try {
			users = userStorageManager.loadUsersFromFile(storagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void storeUsers(String storagePath){
		try {
			userStorageManager.updateUserFile(users, storagePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// WRITE 1 USER TO FILE
	public boolean add(User user){
		if (this.findByUsernameAndPassword(user.getUsername(), user.getPassword()).isPresent()){
			System.out.println("[UserRepo] Username already exists");
			return false;
		}
		else{
			users.add(user);
			return true;
		}
	}
	
	public Optional<User> findById(int id){
		List<User> matches = users.stream().filter(u -> u.getId() == id).collect(Collectors.toList());
		if (matches.isEmpty()){
			return Optional.empty();
		}
		if (matches.size() > 1){
			System.out.println("[UserRepo] Multiple users with the same id: " + id);
		}
		else{
			return Optional.of(matches.get(0));
		}
		return Optional.empty();
	}
	
	public List<User> findByName(String name){
		return users.stream().filter(u -> u.getUsername().equals(name)).collect(Collectors.toList());
	}
	
	public boolean deleteById(int id){
		List<User> matches = users.stream().filter(u -> u.getId() == id).collect(Collectors.toList());
		if (matches.isEmpty()){
			return false;
		}
		if (matches.size() > 1){
			System.out.println("[UserRepo] Multiple users with the same id");
		}
		else{
			users.removeIf(u -> u.getId() == id);
		}
		return false;
	}
	
	public Optional<User> findByUsernameAndPassword(String username, String password){
		List<User> matches = users.stream().filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password)).collect(Collectors.toList());
		if (matches.isEmpty()){
			return Optional.empty();
		}
		if (matches.size() > 1){
			System.out.println("[UserRepo] Multiple users with the same username");
		}
		else{
			return Optional.of(matches.get(0));
		}
		return Optional.empty();
	}


	public List<User> findAll() {
		return users;
	}
	
}
