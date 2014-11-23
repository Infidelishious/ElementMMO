package com.imglow.ElementMMO.server;

import java.util.*;
import java.net.*;
import java.io.*;

import com.imglow.ElementMMO.BattleMessage;
import com.imglow.ElementMMO.EventMessage;
import com.imglow.ElementMMO.Message;
import com.imglow.ElementMMO.MovmentMessage;
import com.imglow.ElementMMO.StatusMessage;
import com.imglow.ElementMMO.TextMessage;

public class ServerLauncher {
	
	Vector<Message> queue;
	Vector<TextMessage> textMessages;
	Vector<MovmentMessage> movementMessages;
	Vector<EventMessage> eventMessages ;
	Vector<BattleMessage> battleMessages;
	Vector<ServerThread> serverThreads;

	Runnable think, output;
	
	//This is for you josh!
	public ServerLauncher (final int port)
	{
		final ServerLauncher thiss = this;
		queue = new Vector<Message>();
		textMessages = new Vector<TextMessage>();
		movementMessages = new Vector<MovmentMessage>();
		eventMessages = new Vector<EventMessage>() ;
		battleMessages = new Vector<BattleMessage>();
		serverThreads = new Vector<ServerThread>();
		
		output = new Runnable(){
			public void run() {
				try {
					ObjectOutputStream br = new ObjectOutputStream(s.getOutputStream());
					int x = 2;
					while(x == 2)
					{
						if(queue.size() > 0)
						{
							br.writeObject(queue.get(0));
							queue.remove(0);
						}
						else
						{
							synchronized(msgLock)
							{
								try {
									msgLock.wait();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
					br.close();
					s.close();
				} catch (IOException ioe) {
					System.out.println( "IOExceptionin Client constructor: " + ioe.getMessage() );
				}
			}
		};
		new Thread(output).start();
		
		think = new Runnable(){
			@Override
			public void run() {
				while(true)
				{
					synchronized(this){
						try {
							this.wait(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					
				}
				
			}
		};
		new Thread(think).start();
		
		new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					ServerSocket ss = new ServerSocket(port);
					while (true)
					{
						Socket s = ss.accept();
						ServerThread st = new ServerThread(s, thiss);
						st.start();
					}
				}
				catch (IOException ioe) { System.out.println("IOException in ServerLauncher Constructor: " + ioe.getMessage()); }
			}
		}).start();
	}
	
	public static void main (String[] args)
	{
		ServerLauncher gameServer = new ServerLauncher(1337);
	}
}

class ServerThread extends Thread {
	
	Socket mySocket;
	ServerLauncher sl;
	Vector<Message> queue;
	private Runnable output, input;
	
	Object msgLock = new Object();
	
	public ServerThread (Socket initSocket, ServerLauncher sl)
	{
		mySocket = initSocket;
		this.sl = sl;
		queue = new Vector<Message>();
	}
	
	public void run()
	{		
		output= new Runnable(){
			public void run() {
				try {
					ObjectOutputStream br = new ObjectOutputStream(mySocket.getOutputStream());
					int x = 2;
					while(x == 2)
					{
						if(queue.size() > 0)
						{
							br.writeObject(queue.get(0));
							queue.remove(0);
						}
						else
						{
							synchronized(msgLock)
							{
								try {
									msgLock.wait();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
					br.close();
				} catch (IOException ioe) {
					System.out.println( "IOExceptionin Client constructor: " + ioe.getMessage() );
				}
			}
		};
		new Thread(output).start();
		
		input = new Runnable(){

			@Override
			public void run() {
				try
				{
					ObjectInputStream is = new ObjectInputStream(mySocket.getInputStream());
					
					while (true)
					{
						Message msg;
						try {
							msg = (Message) is.readObject();
//							System.out.println("Message from: " + msg.from); 
//							if(msg instanceof MovmentMessage)
//							{
//								MovmentMessage mm = (MovmentMessage) msg;
//								System.out.println("Position: " + mm.x + "," + mm.y);
//							}
							
							if(msg instanceof MovmentMessage)
								sl.movementMessages.add((MovmentMessage) msg);
							else if(msg instanceof EventMessage)
								sl.eventMessages.add((EventMessage) msg);
							else if(msg instanceof BattleMessage)
								sl.battleMessages.add((BattleMessage) msg);
							else
								sl.textMessages.add((TextMessage) msg);
								
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				catch (IOException ioe) { System.out.println("IOException in ServerGameplayThread run(): " + ioe.getMessage()); }
				//catch (InterruptedException ie) { System.out.println("InterruptedException: " + ie.getMessage()); }
				
			}
		};
		new Thread(input).start();
	}
	
	public void SendMessage(Message msg)
	{
		queue.add(msg);
		synchronized(msgLock)
		{
			msgLock.notifyAll();
		}
	}
	
	
}


















