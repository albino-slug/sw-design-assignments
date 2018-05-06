package server;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import common.model.Action;
import common.model.ActionType;
import common.model.User;
import common.proxy.Server;

public class ActionManager {
	private final Gson gson = new Gson();
	private Server server;
	
	public ActionManager(ServerImpl serverImpl){
		server = serverImpl;
	}
	
	public String executeAction(Action action){
		System.out.println("[ActionManager] got an action " + action);
		switch(action.getType()){
		case LOGIN: 		User user = action.getUser(); 
							return gson.toJson(server.login(user.getUsername(), user.getPassword()));
		case FINDALL: 		return gson.toJson(server.findAllUsers());
//		case DELETEBYID : 	int id = action.getUser().getId();
//							return gson.toJson(server.deleteById(id));
		case DISPLAYALLARTICLES :
							return gson.toJson(server.findAllArticles());
		default : return "";
		}
	}
}
