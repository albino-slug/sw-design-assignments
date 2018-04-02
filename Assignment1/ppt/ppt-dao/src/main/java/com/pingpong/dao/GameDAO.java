package com.pingpong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.pingpong.model.Game;
import com.pingpong.model.Match;
import com.pingpong.model.Score;

public class GameDAO {
	protected static final Logger LOGGER				= Logger.getLogger(GameDAO.class.getName());
	private static final String insertStatementString	= "INSERT INTO `Game` (pointsP1, pointsP2, Match_matchID)"
														+ " VALUES (?,?,?);";
	private final static String findStatementString		= "SELECT * FROM `Game` where `gameID` = ?;";
	private final static String updateStatementString   = "UPDATE `Game` SET `pointsP1` = ?, `pointsP2` = ? WHERE `gameID` = ?;";
	private final static String deleteStatementString   = "DELETE FROM `Game` WHERE `gameID` = ?;";
	private final static String selectStatementString	= "SELECT * FROM `Game`;";
	
	public static ArrayList<Game> selectAll(){
		ArrayList<Game> toReturn = new ArrayList<Game>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		try {
			selectStatement = dbConnection.prepareStatement(selectStatementString);
			rs = selectStatement.executeQuery();
			while (rs.next()){				
				
				int gameID = rs.getInt("gameID");
				Score score  = new Score(rs.getInt("pointsP1"), rs.getInt("pointsP2"));
				toReturn.add(new Game(gameID, score));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"GameDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	private static ArrayList<Game> createGames(ResultSet rs) throws SQLException {
        ArrayList<Game> games = new ArrayList<Game>();

        while (rs.next()) {
            int gameID = rs.getInt("gameID");
            Score s = new Score(rs.getInt("pointsP1"), rs.getInt("pointsP2"));

            games.add(new Game(gameID, s));
        }
        return games;
}
	
	
	/*
	 * executes the query that finds a game by searching by ID
	 */
	public static Game findById(int gameID) {
		Game toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, gameID);
			rs = findStatement.executeQuery();
			rs.next();

			Score score  = new Score(rs.getInt("pointsP1"), rs.getInt("pointsP2"));
			toReturn = new Game(gameID, score);
			
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"GameDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	/*
	 * executes the query that inserts a game into the table
	 */
	public static int insert(Game game, Match m) {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement insertStatement = null;
		int insertedID = -1;
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setInt(1, game.getScore().getScoreP1());
			insertStatement.setInt(2, game.getScore().getScoreP2());
			insertStatement.setInt(3, m.getMatchID());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedID = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "GameDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		return insertedID;
	}
	
	/*
	 * executes the query that updates an entry of the table
	 */
	public static int update(Game game){
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		int updatedId = -1;
		
		try{
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setInt(1, game.getScore().getScoreP1());
			updateStatement.setInt(2, game.getScore().getScoreP2());
			updateStatement.setInt(5, game.getGameID());
			updateStatement.executeUpdate();
			
			updatedId = game.getGameID();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "GameDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return updatedId;
	}
	
	/*
	 * executes the query that deletes an entry with a certain ID from a table 
	 */
	public static int delete(Game game){
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		int deletedId = -1;
		
		try{
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, game.getGameID());
			deleteStatement.executeUpdate();
			
			deletedId = game.getGameID();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "GameDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return deletedId;
	}
	
	public static ArrayList<Game> selectAllGames() {
		ArrayList<Game>  toReturn = new ArrayList<Game>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		
		try {
			selectStatement = dbConnection.prepareStatement(selectStatementString);
			rs = selectStatement.executeQuery();
			while (rs.next()){								

				int gameID = rs.getInt("gameID");
				Score score  = new Score(rs.getInt("pointsP1"), rs.getInt("pointsP2"));
				toReturn.add(new Game(gameID, score));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"GameDAO:selectAllGames " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return toReturn;
	}
	
	public ArrayList<Game> findAllByMatchId(int matchID) {
        ArrayList<Game> games = new ArrayList<Game>();

        String selectQuery = "SELECT * FROM `Game` WHERE `Match_matchID` = ?;";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, matchID);

            resultSet = statement.executeQuery();

            if (resultSet.next()){
                resultSet.beforeFirst();
                games = createGames(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            games = null;
        }

        return games;
}
}