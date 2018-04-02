package com.pingpong.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.pingpong.model.Match;
import com.pingpong.model.Tournament;
import com.pingpong.model.User;

public class TournamentDAO {
	protected static final Logger LOGGER				= Logger.getLogger(TournamentDAO.class.getName());
	private static final String insertStatementString	= "INSERT INTO `Tournament` (`name`, `status`)"
														+ " VALUES (?, ?);";
	private final static String findStatementString		= "SELECT * FROM `Tournament` where `tourID` = ?;";
	private final static String updateStatementString   = "UPDATE `Tournament` SET `name` = ?, `status` = ? WHERE `tourID` = ?;";
	private final static String deleteStatementString   = "DELETE FROM `Tournament` WHERE `tourID` = ?;";
	private final static String selectStatementString	= "SELECT * FROM `Tournament`;";
	
	public static ArrayList<Tournament> selectAll(){
		ArrayList<Tournament> toReturn = new ArrayList<Tournament>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		try {
			selectStatement = dbConnection.prepareStatement(selectStatementString);
			rs = selectStatement.executeQuery();
			while (rs.next()){
				
				int tourID = rs.getInt("tourID");
				String name = rs.getString("name");
				String status = rs.getString("status");
				
				toReturn.add(new Tournament(tourID, name, status));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"TournamentDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	public static Tournament findById(int tourID) {
		Tournament toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, tourID);
			rs = findStatement.executeQuery();
			rs.next();

			String name = rs.getString("name");
			String status = rs.getString("status");
			
			toReturn = new Tournament(tourID, name, status);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"TournamentDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	public int insert(Tournament tour) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		int insertedID = -1;
		
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, tour.getName());
			insertStatement.setString(2, tour.getStatus());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedID = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "TournamentDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return insertedID;
	}
	
	public static int update(Tournament tour){
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		int updatedID = -1;
		
		try{
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setString(1, tour.getName());		
			updateStatement.setInt(3, tour.getTourID());
			updateStatement.setString(2, tour.getStatus());
			
			updateStatement.executeUpdate();
			
			updatedID = tour.getTourID();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "TournamentDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return updatedID;
	}
	
	public static int delete(Tournament tour){
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		int deletedID = -1;
		
		try{
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, tour.getTourID());
			deleteStatement.executeUpdate();
			
			deletedID = tour.getTourID();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Cannot delete tournament - they have an existing match","Program issue",JOptionPane.WARNING_MESSAGE);
			LOGGER.log(Level.WARNING, "TournamentDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return deletedID;
	}
	
	public static ArrayList<Tournament> selectAllTournaments() {
		ArrayList<Tournament>  toReturn = new ArrayList<Tournament>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		
		try {
			selectStatement = dbConnection.prepareStatement(selectStatementString);
			rs = selectStatement.executeQuery();
			while (rs.next()){				
				String name = rs.getString("name");
				String status = rs.getString("status");

				int tourID = rs.getInt("tourID");
				toReturn.add(new Tournament(tourID, name, status));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"TournamentDAO:selectAllTournaments " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return toReturn;
	}
	
	public Tournament findByName(String name) {
        Tournament tournament = null;

        String selectQuery = "SELECT * FROM Tournament WHERE name = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // prepare query
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(selectQuery);
            statement.setString(1, name);

            // execute query
            resultSet = statement.executeQuery();

            // check result
            if (resultSet.next()){
                resultSet.beforeFirst();
                tournament = createTournaments(resultSet).get(0);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            tournament = null;
        }finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return tournament;
    }
	
	private static ArrayList<Tournament> createTournaments(ResultSet resultSet) throws SQLException {
        ArrayList<Tournament> tournaments = new ArrayList<Tournament>();

        MatchDAO matchDAO = new MatchDAO();

        while (resultSet.next()){
            int tourID = resultSet.getInt("tourID");
            String name = (String) resultSet.getObject("name");
            String status = (String) resultSet.getObject("status");
            ArrayList<Match> matches = matchDAO.findAllByTournamentId(tourID);
            tournaments.add(new Tournament(tourID, name, status, matches));
        }
        return tournaments;
    }

	
}