package com.pingpong.model;

public class Game {
	private int		gameID;
	private Score	score;
	private int		mid;
	
	public Game(int gameID, Score score) {
		super();
		this.gameID	= gameID;
		this.score	= score;
	}

	public Game(int gameID) {
		super();
		this.gameID	= gameID;
	}
	
	public Game(Score score) {
		super();
		this.score	= score;
	}

	@Override
	public String toString() {
		return "Game [gameID=" + gameID + ", Score=" + score.toString() + "]";
	}

	public int getGameID() {
		return gameID;
	}


	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	
    public Score getScore() {
        return score;
    }
    
    public void setScore(Score score) {
        this.score = score;
    }
    
    public void incrementScoreP1(){
        this.score.incrementScoreP1();
    }

    public void incrementScorePlayer2(){
        this.score.incrementScoreP2();
}
}