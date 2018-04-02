package test;

import java.util.ArrayList;
import java.util.Date;

import com.pingpong.dao.GameDAO;
import com.pingpong.dao.MatchDAO;
import com.pingpong.dao.TournamentDAO;
import com.pingpong.dao.UserDAO;
import com.pingpong.model.Game;
import com.pingpong.model.Match;
import com.pingpong.model.Score;
import com.pingpong.model.Tournament;
import com.pingpong.model.User;

public class Test {
	static UserDAO udao = new UserDAO();
	static GameDAO gdao = new GameDAO();
	static MatchDAO mdao = new MatchDAO();
	static TournamentDAO tdao = new TournamentDAO();

	public static void main(String[] args) {
					
			displayAll();
	}
	
	static void displayAll(){
		ArrayList<User> users = new ArrayList<User>();
		users = udao.selectAll();
		ArrayList<Tournament> tours = new ArrayList<Tournament>();
		tours = tdao.selectAll();
		ArrayList<Match> matches = new ArrayList<Match>();
		matches = mdao.selectAll();
		ArrayList<Game> games = new ArrayList<Game>();
		games = gdao.selectAll();
		
		for (User us : users){
			System.out.println(us.toString());
		}
		
		for (Tournament ts : tours){
			System.out.println(ts.toString());
		}
			
		for (Match m : matches){
			System.out.println(m.toString());
		}
		
		for (Game g : games){
			System.out.println(g.toString());
		}
	}

}
