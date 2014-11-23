package com.imglow.ElementMMO.server;

import java.util.*;
import java.net.*;
import java.io.*;



public class ServerLauncher {
	
	// Constructor
	public ServerLauncher (int port)
	{
		try {
			ServerSocket ss = new ServerSocket(port);
			while (true)
			{
				Socket s = ss.accept();
				ServerThread st = new ServerThread(s);
				st.start();
			}
		}
		catch (IOException ioe) { System.out.println("IOException in ServerLauncher Constructor: " + ioe.getMessage()); }
	}
	
	
	
	// Main
	public static void main (String[] args)
	{
		ServerLauncher gameServer = new ServerLauncher(1337);
	}

}



class ServerThread extends Thread {
	
	Socket mySocket;
	
	
	
	// Constructor
	public ServerThread (Socket initSocket)
	{
		mySocket = initSocket;
	}
	
	
	
	// Continuous run behavior
	public void run ()
	{		
		try
		{
			PrintWriter myPW = new PrintWriter(mySocket.getOutputStream());
			
			while (true)
			{
				
			}
		}
		catch (IOException ioe) { System.out.println("IOException in ServerGameplayThread run(): " + ioe.getMessage()); }
		//catch (InterruptedException ie) { System.out.println("InterruptedException: " + ie.getMessage()); }
	}
	
}


















