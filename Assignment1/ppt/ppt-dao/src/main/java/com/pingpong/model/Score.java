package com.pingpong.model;

public class Score {
	private int scorePlayer1;
    private int scorePlayer2;

    public Score(int scorePlayer1, int scorePlayer2) {
        this.scorePlayer1 = scorePlayer1;
        this.scorePlayer2 = scorePlayer2;
    }

    public Score(){
        this(0, 0);
    }

    public int getScoreP1() {
        return scorePlayer1;
    }

    public void setScoreP1(int scorePlayer1) {
        this.scorePlayer1 = scorePlayer1;
    }

    public int getScoreP2() {
        return scorePlayer2;
    }

    public void setScoreP2(int scorePlayer2) {
        this.scorePlayer2 = scorePlayer2;
    }

    public void incrementScoreP1() {
        this.scorePlayer1++;
    }

    public void incrementScoreP2() {
        this.scorePlayer2++;
    }
    
    public String toString(){
    	return "(p1: " + scorePlayer1 + ", p2: " + scorePlayer2 + ")";
    }
}
