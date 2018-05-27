package storage;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import common.model.User;

public class UserStorageManager {
	
	// LOAD ALL USERS FROM FILE
	public ArrayList<User> loadUsersFromFile(String filePath) throws IOException{
		FileReader file = new FileReader("users.json");
		try{
			String userJsons = new String(Files.readAllBytes(Paths.get(filePath)));
			
			Gson gson = new Gson();
			ArrayList<User> users = new ArrayList(Arrays.asList(gson.fromJson(userJsons, User[].class))); 
			System.out.println("[SUCCESS] read whole user file.");
			
			return users;
		} catch (IOException e){
			e.printStackTrace();
		} finally {
			file.close();
		}
		return null;
	}
	
	public void updateUserFile(List<User> users, String filePath) throws IOException{
		FileWriter file = new FileWriter(filePath);
		try{
			Gson gson = new Gson();
			String uJson = gson.toJson(users);
			file.write(uJson);
			System.out.println("[SUCCESS] updated user file.");

		} catch (IOException e){
			e.printStackTrace();
		} finally {
			file.flush();
			file.close();
		}
	}

}
