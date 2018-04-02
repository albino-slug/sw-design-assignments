package com.pingpong.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Tournament {
	private int		tourID;
	private String	name;
	private String  status;
	private Date start;
	private Date end;
	private ArrayList<Match> matches = new ArrayList<Match>();
	private ArrayList<User> users = new ArrayList<User>();
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public Tournament(String name){
		this.name = name;
		this.start = new Date();
	}
	
	public Tournament(int tourID, String name){
		this.tourID = tourID;
		this.name = name;
		this.start = new Date();
		this.status = "pending";
	}
	
	public Tournament(int tourID, String name, String status){
		this.tourID = tourID;
		this.name = name;
		this.status = status;
	}
	
	public Tournament(int tourID, String name, String status, ArrayList<Match> matches){
		this.tourID = tourID;
		this.name = name;
		this.start = new Date();
		this.status = status;
		this.matches = matches;
	}
	
	@Override
	public String toString() {
		return "Tournament [tourID=" + tourID + ", name=" + name + ", status=" + status +"]";
	}
	
	public int getTourID() {
		return tourID;
	}

	public void setTourID(int tourID) {
		this.tourID = tourID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }
    
    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void addNewMatch(Match match){
        this.matches.add(match);
    }
    
    public void removeMatch(Match match){
        matches.remove(match);
}
	
}