package com.imglow.ElementMMO.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import com.imglow.ElementMMO.BattleMessage;
import com.imglow.ElementMMO.EventMessage;
import com.imglow.ElementMMO.Message;
import com.imglow.ElementMMO.MovmentMessage;
import com.imglow.ElementMMO.TextMessage;

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