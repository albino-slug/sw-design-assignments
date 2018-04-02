package com.pingpong.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.pingpong.model.Game;
import com.pingpong.model.Match;
import com.pingpong.model.Tournament;
import com.pingpong.model.User;

public class MatchDAO {
	protected static final Logger LOGGER				= Logger.getLogger(MatchDAO.class.getName());
	private static final String insertStatementString	= "INSERT INTO `Match` (`level`, `User_userID`,`User_userID1`, `Tournament_tourID`) VALUES(?, ?, ?, ?);";
	private final static String findStatementString		= "SELECT * FROM `Match` WHERE `matchID` = ?;";
	private final static String updateStatementString   = "UPDATE `Match` SET `level` = ?, `User_userID` = ?, `User_userID1` = ? WHERE `matchID` = ?;";
	private final static String deleteStatementString   = "DELETE FROM `Match` WHERE `matchID` = ?;";
	private final static String selectStatementString	= "SELECT * FROM `Match`;";
	
	static UserDAO udao = new UserDAO();
	static GameDAO gdao = new GameDAO();
	
	public static ArrayList<Match> selectAll(){
		ArrayList<Match> toReturn = new ArrayList<Match>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		try {
			selectStatement = dbConnection.prepareStatement(selectStatementString);
			rs = selectStatement.executeQuery();
			while (rs.next()){
				
				int matchID = rs.getInt("matchID");
				String level = rs.getString("level");
				int p1 = rs.getInt("User_userID");
				int p2 = rs.getInt("User_userID1");
//				Date start = rs.getDate("start");
//				Date end = rs.getDate("end");
				
				toReturn.add(new Match(matchID, level, udao.findById(p1), udao.findById(p2), gdao.findAllByMatchId(matchID)));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"MatchDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	public static Match findById(int matchID) {
		Match toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, matchID);
			rs = findStatement.executeQuery();
			rs.next();

			String level = rs.getString("level");
			int p1 = rs.getInt("User_userID");
			int p2 = rs.getInt("User_userID1");
//			Date start = rs.getDate("start");
//			Date end = rs.getDate("end");
			
			toReturn = new Match(matchID, level, udao.findById(p1), udao.findById(p2), gdao.findAllByMatchId(matchID));
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"MatchDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	public int insert(Match match, Tournament tournament) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		
		
		int insertedID = -1;
		
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, match.getLevel());
//			insertStatement.setDate(2, (Date) match.getStart());
//			insertStatement.setDate(3, (Date) match.getEnd());
			insertStatement.setInt(2,  match.getPlayer1().getUserID());
			insertStatement.setInt(3,  match.getPlayer2().getUserID());
			insertStatement.setInt(4,  tournament.getTourID());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedID = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "MatchDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return insertedID;
	}
	
	public static int update(Match match){
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		int updatedID = -1;
		
		try{
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setString(1, match.getLevel());
//			updateStatement.setDate(2, (Date) match.getStart());
//			updateStatement.setDate(3, (Date) match.getEnd());		
			updateStatement.setInt(4, match.getMatchID());
			updateStatement.setInt(2, match.getPlayer1().getUserID());
			updateStatement.setInt(3, match.getPlayer2().getUserID());
			
			updateStatement.executeUpdate();
			
			updatedID = match.getMatchID();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "MatchDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return updatedID;
	}
	
	public static int delete(Match match){
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		int deletedID = -1;
		
		try{
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, match.getMatchID());
			deleteStatement.executeUpdate();
			
			deletedID = match.getMatchID();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Cannot delete match - they have an existing game","Program issue",JOptionPane.WARNING_MESSAGE);
			LOGGER.log(Level.WARNING, "MatchDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return deletedID;
	}
	
	public static ArrayList<Match> selectAllMatches() {
		ArrayList<Match>  toReturn = new ArrayList<Match>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		
		try {
			selectStatement = dbConnection.prepareStatement(selectStatementString);
			rs = selectStatement.executeQuery();
			while (rs.next()){				
				String level = rs.getString("level");
//				Date start = rs.getDate("start");
//				Date end = rs.getDate("end");
				int matchID = rs.getInt("matchID");
				int p1 = rs.getInt("User_userID");
				int p2 = rs.getInt("User_userID1");
				toReturn.add(new Match(matchID, level, udao.findById(p1), udao.findById(p2), gdao.findAllByMatchId(matchID)));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"MatchDAO:selectAllMatchs " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return toReturn;
	}
	
	public ArrayList<Match> findAllByTournamentId(int idTournament) {
        ArrayList<Match> matches = null;

        String selectQuery = "SELECT * FROM `Match` WHERE Tournament_tourID = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // prepare query
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(selectQuery);
            statement.setInt(1, idTournament);

            // execute query
            resultSet = statement.executeQuery();

            // check result
            if (resultSet.next()){
                resultSet.beforeFirst();
                matches = createMatches(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            matches = null;
        }

        return matches;
	}
	
	private static ArrayList<Match> createMatches(ResultSet resultSet) throws SQLException {
        UserDAO userDAO = new UserDAO();
        GameDAO gameDAO = new GameDAO();

        ArrayList<Match> matches = new ArrayList<Match>();

        while (resultSet.next()){
            int matchID = resultSet.getInt("matchID");
            //Date start  = resultSet.getDate("start");
            //Date end    = resultSet.getDate("end");
            int p1ID 	= resultSet.getInt("User_userID");
            int p2ID 	= resultSet.getInt("User_userID1");
            String level	= resultSet.getString("level");

            User player1 = userDAO.findById(p1ID);
            User player2 = userDAO.findById(p2ID);

            // add new match to list
            matches.add(new Match(matchID, level, player1, player2, gdao.findAllByMatchId(matchID)));
        }

        return matches;
	}
	
}