package client;

import java.io.IOException;
import java.net.UnknownHostException;

import common.model.User;
import common.proxy.Server;

public class ClientEntryPoint {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Server serverProxy = new ServerProxy("127.0.0.1", 1343);
		System.out.println(serverProxy.findAllUsers().size());
		System.out.println(serverProxy.findAllArticles().size());
		
		//test login feature
		
		User user = serverProxy.login("user1", "asdf");
		if (user.getId() == -1){
			System.out.println("this user is not registred");
		}
		else{
			System.out.println(user);
		}
	}

}
