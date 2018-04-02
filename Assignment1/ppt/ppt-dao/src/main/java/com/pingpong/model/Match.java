package com.pingpong.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Match {
	private int		matchID;
	private String	level;
	private Date start;
	private Date end;
	private ArrayList<Game> games;
	private User player1 = new User();
	private User player2 = new User();
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public Match(String level){
		this.level = level;
	}
	
	public Match(int matchID, String level){
		this.matchID = matchID;
		this.level = level;
	}
	
	public Match(int matchID, String level, Date start, Date end){
		this.matchID = matchID;
		this.level = level;
		this.start = start;
		this.end = end;
	}
	
	public Match(int matchID, String level, User p1, User p2, ArrayList<Game> games){
		this.matchID = matchID;
		this.level = level;
		this.player1 = p1;
		this.player2 = p2;
		this.games = games;
	}
	
	public Match(int matchID, String level, User p1, User p2){
		this.matchID = matchID;
		this.level = level;
		this.player1 = p1;
		this.player2 = p2;
	}
	
	public Match(String level, User p1, User p2, ArrayList<Game> games){
		this.level = level;
		this.player1 = p1;
		this.player2 = p2;
		this.games = games;
	}
	
	@Override
	public String toString() {
		return "Match [matchID=" + matchID + ", level=" + level + ", player1=" + this.getPlayer1().getUsername() + ", player2=" + this.getPlayer2().getUsername() + ", Score=" + this.getScore().toString() + "]";
	}
	
	public int getMatchID() {
		return matchID;
	}

	public void setMatchID(int matchID) {
		this.matchID = matchID;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}
	
	public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }
    
    public User getPlayer1() {
        return player1;
    }

    public void setPlayer1(User player1) {
        this.player1 = player1;
    }

    public User getPlayer2() {
        return player2;
    }

    public void setPlayer2(User player2) {
        this.player2 = player2;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return matchID == match.matchID;
    }
    
    public Score getScore() {
        Score score = new Score(0, 0);
        for (Game g : this.games) {
            int scoreP1 = g.getScore().getScoreP1();
            int scoreP2 = g.getScore().getScoreP2();
            if (scoreP1 > scoreP2 + 1 && scoreP1 >= 11){
                score.incrementScoreP1();
            }
            if (scoreP2 > scoreP1 + 1 && scoreP2 >= 11){
                score.incrementScoreP2();
            }
        }
        return score;
    }
	
}