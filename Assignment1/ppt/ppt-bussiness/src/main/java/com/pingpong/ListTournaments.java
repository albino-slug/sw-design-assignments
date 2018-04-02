package com.pingpong;

import java.util.ArrayList;

import com.pingpong.dao.TournamentDAO;
import com.pingpong.model.Tournament;

public class ListTournaments {
	private ArrayList<String> names    = new ArrayList<String>();
	private ArrayList<String> statuses = new ArrayList<String>();
	private TournamentDAO tDAO 		   = new TournamentDAO();
	
	public boolean listTournaments(){
		ArrayList<Tournament> tours = tDAO.selectAll();
		if (tours == null){
			return false;
		}
		for (Tournament t : tours){
			this.names.add(t.getName());
			this.statuses.add(t.getStatus());
		}
		return true;
	}
	
    public ArrayList<String> getTourNames() {
        return names;
    }

    public ArrayList<String> getTourStatuses() {
        return statuses;
}

}
