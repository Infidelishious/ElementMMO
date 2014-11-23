package com.imglow.ElementMMO.server;

import java.util.*;
import java.net.*;
import java.io.*;

import com.imglow.ElementMMO.Message;
import com.imglow.ElementMMO.MovmentMessage;

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
			ObjectInputStream is = new ObjectInputStream(mySocket.getInputStream());
			
			while (true)
			{
				Message msg;
				try {
					msg = (Message) is.readObject();
					System.out.println("Message from: " + msg.from); 
					if(msg instanceof MovmentMessage)
					{
						MovmentMessage mm = (MovmentMessage) msg;
						System.out.println("Position: " + mm.x + "," + mm.y);
					}
						
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		catch (IOException ioe) { System.out.println("IOException in ServerGameplayThread run(): " + ioe.getMessage()); }
		//catch (InterruptedException ie) { System.out.println("InterruptedException: " + ie.getMessage()); }
	}
	
}


















