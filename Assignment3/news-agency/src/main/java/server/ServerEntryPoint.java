package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import common.model.Action;
import common.model.Role;
import common.model.User;
import common.model.builder.UserBuilder;
import server.repository.ArticleRepo;
import server.repository.UserRepo;

public class ServerEntryPoint {
	private static Socket client = null;
	private static DataOutputStream out = null;
	private static DataInputStream in = null;
	private static ActionManager actionManager;
	
	public static void main(String[] args) {
		actionManager = new ActionManager(new ServerImpl(new UserRepo("users.json"), new ArticleRepo("articles.json")));
		Gson gson = new Gson();
		try {
			boolean stopped = false;
			ServerSocket server = new ServerSocket(1343); // start listening on port 8888
			while (!stopped){
			client = server.accept(); // this method is a blocking I/O call, it will not be called unless
			//a connection is established.
			out = new DataOutputStream(client.getOutputStream()); // get the output stream of client.
			in = new DataInputStream(client.getInputStream());    // get the input stream of client.
		
			String request = new BufferedReader(new InputStreamReader(in)).readLine();
			System.out.println("[Server Entry Point] got a request " + request);
			Action action = gson.fromJson(request, Action.class);
			
			
			PrintWriter printWriter = new PrintWriter(out);
			printWriter.println(actionManager.executeAction(action));
			printWriter.flush();
				
			}
			// close resources
			out.close();
			in.close();
			client.close();
			server.close();


			} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
			} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
			}
	}

}
