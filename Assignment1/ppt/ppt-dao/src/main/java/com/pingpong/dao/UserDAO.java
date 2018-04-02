package com.pingpong.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.pingpong.model.User;

public class UserDAO {
	protected static final Logger LOGGER				= Logger.getLogger(UserDAO.class.getName());
	private static final String insertStatementString	= "INSERT INTO `User` (email, username, password, isadmin)"
														+ " VALUES (?,?,?,?);";
	private final static String findStatementString		= "SELECT * FROM `User` where `userID` = ?;";
	private final static String updateStatementString   = "UPDATE `User` SET `email` = ?, `username` = ?, `password` = ?, `isadmin` = ? WHERE `userID` = ?;";
	private final static String deleteStatementString   = "DELETE FROM `User` WHERE `userID` = ?;";
	private final static String selectStatementString	= "SELECT * FROM `User`;";
	
	public static ArrayList<User> selectAll(){
		ArrayList<User> toReturn = new ArrayList<User>();

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		try {
			selectStatement = dbConnection.prepareStatement(selectStatementString);
			rs = selectStatement.executeQuery();
			while (rs.next()){
				int id = rs.getInt("userID");
				
				String email = rs.getString("email");
				String username = rs.getString("username");
				String password = rs.getString("password");
				Boolean isadmin = rs.getBoolean("isadmin");
				
				toReturn.add(new User(id, email, username, password, isadmin));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"UserDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
	private static ArrayList<User> createUsers(ResultSet resultSet) throws SQLException {
        ArrayList<User> users = new ArrayList<User>();

        while (resultSet.next()){
            int userID = resultSet.getInt("UserID");
            boolean isadmin = resultSet.getInt("isadmin") > 0;
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");

            // add new user to list
            users.add(new User(userID, username, email, password, isadmin));
        }
        return users;
}
	
	public static User findById(int userID) {
		User toReturn = null;

		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement findStatement = null;
		ResultSet rs = null;
		try {
			findStatement = dbConnection.prepareStatement(findStatementString);
			findStatement.setLong(1, userID);
			rs = findStatement.executeQuery();
			rs.next();

			String username = rs.getString("username");
			String password = rs.getString("password");
			String email = rs.getString("email");
			Boolean isadmin = rs.getBoolean("isadmin");
			toReturn = new User(userID, email, username, password, isadmin);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"UserDAO:findById " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findStatement);
			ConnectionFactory.close(dbConnection);
		}
		return toReturn;
	}
	
    private User findByStringField(String stringFieldName, String stringFieldValue){
        User user = null;

        String selectQuery = "SELECT * FROM User WHERE " + stringFieldName + " = ?";

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(selectQuery);
            statement.setString(1, stringFieldValue);

            resultSet = statement.executeQuery();

            if (resultSet.next()){
                resultSet.beforeFirst();
                user = createUsers(resultSet).get(0);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
            user = null;
        }finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        return user;
    }
    
    public User findByUsername(String username) {
        return findByStringField("username", username);
    }

    public User findByEmail(String email) {
        return findByStringField("email", email);
}
	
	public static int insert(User user) {
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement insertStatement = null;
		int insertedID = -1;
		
		try {
			insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
			insertStatement.setString(1, user.getEmail());
			insertStatement.setString(2, user.getUsername());
			insertStatement.setString(3, user.getPass());
			insertStatement.setBoolean(4, user.getIsAdmin());
			insertStatement.executeUpdate();

			ResultSet rs = insertStatement.getGeneratedKeys();
			if (rs.next()) {
				insertedID = rs.getInt(1);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UserDAO:insert " + e.getMessage());
		} finally {
			ConnectionFactory.close(insertStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return insertedID;
	}
	
	public static int update(User user){
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement updateStatement = null;
		int updatedID = -1;
		
		try{
			updateStatement = dbConnection.prepareStatement(updateStatementString);
			updateStatement.setString(1, user.getEmail());
			updateStatement.setString(2, user.getUsername());
			updateStatement.setString(3, user.getPass());
			updateStatement.setBoolean(4, user.getIsAdmin());
			updateStatement.setInt(5, user.getUserID());
			updateStatement.executeUpdate();
			
			updatedID = user.getUserID();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "UserDAO:update " + e.getMessage());
		} finally {
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return updatedID;
	}
	
	public static int delete(User user){
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement deleteStatement = null;
		int deletedID = -1;
		
		try{
			deleteStatement = dbConnection.prepareStatement(deleteStatementString);
			deleteStatement.setInt(1, user.getUserID());
			deleteStatement.executeUpdate();
			
			deletedID = user.getUserID();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Cannot delete user - they have an existing match","Program issue",JOptionPane.WARNING_MESSAGE);
			LOGGER.log(Level.WARNING, "UserDAO:delete " + e.getMessage());
		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return deletedID;
	}
	
	public static ArrayList<User> selectAllUsers() {
		ArrayList<User>  toReturn = new ArrayList<User>();
		Connection dbConnection = ConnectionFactory.getConnection();
		PreparedStatement selectStatement = null;
		ResultSet rs = null;
		
		try {
			selectStatement = dbConnection.prepareStatement(selectStatementString);
			rs = selectStatement.executeQuery();
			while (rs.next()){				
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				Boolean isadmin = rs.getBoolean("isadmin");
				int userID = rs.getInt("userID");
				toReturn.add(new User(userID, email, username, password, isadmin));
			}
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING,"UserDAO:selectAllUsers " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(selectStatement);
			ConnectionFactory.close(dbConnection);
		}
		
		return toReturn;
	}
	
}