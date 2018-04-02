package com.pingpong;

import java.util.ArrayList;
import java.util.List;

import com.pingpong.dao.TournamentDAO;
import com.pingpong.model.Score;
import com.pingpong.model.Tournament;

public class TournamentDisplay {
    private TournamentDAO tournamentDAO = new TournamentDAO();
    private String tournamentName;
    private Tournament tournament;

    public TournamentDisplay(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public boolean getTournament() {
        tournament = tournamentDAO.findByName(tournamentName);
        if (tournament == null) {
            return false;
        }
        return true;
    }

    public ArrayList<String> getP1Names(String level) {
        ArrayList<String> names = new ArrayList<String>();

        if (tournament.getMatches() == null) {
            return names;
        }

        for (int i = 0; i < tournament.getMatches().size(); i++) {
        	if (tournament.getMatches().get(i).getLevel().equals(level)){
                names.add(tournament.getMatches().get(i).getPlayer1().getUsername());
        	}
        }

        return names;
    }

    public ArrayList<String> getP2Names(String level) {
        ArrayList<String> names = new ArrayList<String>();

        if (tournament.getMatches() == null) {
            return names;
        }

        for (int i = 0; i < tournament.getMatches().size(); i++) {
        	if (tournament.getMatches().get(i).getLevel().equals(level)){
                names.add(tournament.getMatches().get(i).getPlayer2().getUsername());
        	}
        }

        return names;
    }

    public ArrayList<Integer> getP1Scores(String level) {
        ArrayList<Integer> scores = new ArrayList<Integer>();

        if (tournament.getMatches() == null) {
            return scores;
        }

        for (int i = 0; i < tournament.getMatches().size(); i++) {
        	if (tournament.getMatches().get(i).getLevel().equals(level)){
        		Score matchScore = tournament.getMatches().get(i).getScore();
                scores.add(matchScore.getScoreP1());
        	}                
        }
        return scores;
    }

    public ArrayList<Integer> getP2Scores(String level) {
        ArrayList<Integer> scores = new ArrayList<Integer>();

        if (tournament.getMatches() == null) {
            return scores;
        }

        for (int i = 0; i < tournament.getMatches().size(); i++) {
        	if (tournament.getMatches().get(i).getLevel().equals(level)){
        		Score matchScore = tournament.getMatches().get(i).getScore();
                scores.add(matchScore.getScoreP2());
        	} 
    }

        return scores;
    }

    public String getTournamentName() {
        return tournamentName;
    }
    
    public Tournament getTour(){
    	return this.tournament;
    }
}