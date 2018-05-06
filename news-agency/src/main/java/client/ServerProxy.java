package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import common.model.Action;
import common.model.ActionType;
import common.model.Article;
import common.model.User;
import common.model.builder.UserBuilder;
import common.proxy.Server;

public class ServerProxy implements Server {
	private static Socket client = null;
	private static DataOutputStream out = null;
	private static DataInputStream in = null;
	private static Gson gson = new Gson();
	private String ip;
	private int port;
	
	
	public ServerProxy(String ip, int port) throws UnknownHostException, IOException{
		this.ip = ip;
		this.port = port;
	}

	private void createConnection(String ip, int port) throws UnknownHostException, IOException {
		client = new Socket(ip, port);
		out = new DataOutputStream(client.getOutputStream());
		in = new DataInputStream(client.getInputStream());
	}

	public void endConnection(){
		try {
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public User login(String username, String password) {
		Action action = new Action(ActionType.LOGIN);
		action.setUser(UserBuilder.anUser().withPassword(password).withUsername(username).build());
		System.out.println("[ServerProxy] going to send " + action);
		String response = null;
		try {
			response = makeRequest(action);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return gson.fromJson(response, User.class);
	}

	@Override
	public List<User> findAllUsers() {
		Action action = new Action(ActionType.FINDALL);
		String response = null;	
		try {
			response = makeRequest(action);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Arrays.asList(gson.fromJson(response, User[].class));
	}
	
//	@Override
//	public void deleteById(int id) {
//		Action action = new Action(ActionType.DELETEBYID);	
//		//TODO
//	}

	@Override
	public List<Article> findAllArticles() {
		Action action = new Action(ActionType.DISPLAYALLARTICLES);
		String response = null;	
		try {
			response = makeRequest(action);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ArrayList<Article>(Arrays.asList(gson.fromJson(response, Article[].class)));
	}
	
	private String makeRequest(Action action) throws IOException{
		try {
			createConnection(ip, port);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PrintWriter pw = new PrintWriter(out);
		pw.println(gson.toJson(action));
		pw.flush();
		String result = new BufferedReader(new InputStreamReader(in)).readLine();
		endConnection();
		return result;
	}
}
