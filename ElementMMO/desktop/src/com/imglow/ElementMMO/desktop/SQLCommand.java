package com.imglow.ElementMMO.desktop;

import java.sql.ResultSet;
import java.util.concurrent.locks.ReentrantLock;



public abstract class SQLCommand implements Runnable {
	
	// VARIABLES START --------------------------------------------------------
	
	public static final String DB_ADDRESS = "jdbc:mysql://localhost/";
	public static final String DB_NAME = "elementmmo";
	public static final String DRIVER = "com.mysql.jdbc.Driver";
	public static final String USER = "root";
	public static final String PASSWORD = "rootPassword";
	
	protected ReentrantLock queryLock;
	
	// VARIABLES END ----------------------------------------------------------
	
	
	
	public SQLCommand(ReentrantLock queryLock)
	{
		this.queryLock = queryLock;
	}
	
	
	@Override
	public void run()
	{
		queryLock.lock();
		try {
			System.out.print("Executing... ");
			execute();
			System.out.println("Done");
		}
		catch (Exception e) { System.out.println(e.getMessage()); }
		finally {
			queryLock.unlock();
		}
	}
	
	public abstract boolean execute();
	
}











