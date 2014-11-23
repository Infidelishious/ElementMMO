package com.imglow.ElementMMO.desktop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;



public class SQLManager {
	
	// VARIABLES START --------------------------------------------------------
	
	public static final String DB_ADDRESS = "jdbc:mysql://localhost/";
	public static final String DB_NAME = "elementmmo";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String USER = "root";
	public static final String PASSWORD = "rootPassword";
	
	Connection myConnection;
	PreparedStatement myPS;
	
	protected ReentrantLock queryLock;
	
	// VARIABLES END ----------------------------------------------------------
	
	
	
	public SQLManager ()
	{
		queryLock = new ReentrantLock();
		
		// establish connection with sql database
		try {
			Class.forName(DRIVER);
			myConnection = DriverManager.getConnection(DB_ADDRESS + DB_NAME, USER, PASSWORD);
		}
		catch (SQLException sqle) { System.out.println("SQLException: " + sqle.getMessage()); }
		catch (Exception e) { System.out.println("Exception: " + e.getMessage()); }
	}

	
	
	// Get whether user exists
	public boolean userExists (String user)
	{
		
	}
	
	// Get whether login credentials are valid
	public boolean isValidLogin (String user, String pw, int charID)
	{
		
	}
	
	// Get user entry
	public ResultSet getUserEntry (String user)
	{
		// abort if user does not exist
		if (!userExists(user)) return null;
		
		
	}
	
	// Enter new user
	public void createUser (String user, String pw)
	{
		
	}
	
	// Increment kill count of specified user
	public void addKill (String user)
	{
		
	}
	
	// Increment death count of specified user
	public void addDeath (String user)
	{
		
	}
	
}




















