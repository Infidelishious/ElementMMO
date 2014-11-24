package com.imglow.ElementMMO;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ChatArea implements Drawable{
	protected String chatText;
	boolean first;
	String recievedMessage;
	ArrayList<String> messages;
	ChatArea() {
		chatText = new String("");
		first = true;
		messages = new ArrayList<String>();
	}


	@Override
	public void draw(SpriteBatch sb)
	{
		
		
		if(first) chatText += "> "; 
		BitmapFont chatFont = TextureSingleton.getInstance().scoreFont;
		chatFont.setColor(0.0f,0.0f,0.0f,1.0f);
		chatFont.setScale(2.0f);
		chatFont.draw(sb, chatText, -MainClient.WIDTH/2 + 30, -MainClient.HEIGHT/2 + 75);
		if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && first != true) sendMessage(chatText);
		if(Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE) && chatText.length() > 2)
			chatText = chatText.substring(0, chatText.length() - 1);
		if(chatText.length() < 50)
		{
			first = false;
			if(Gdx.input.isKeyJustPressed(Input.Keys.A)) chatText += "A";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.B)) chatText += "B";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.C)) chatText += "C";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.D)) chatText += "D";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.E)) chatText += "E";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.F)) chatText += "F";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.G)) chatText += "G";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.H)) chatText += "H";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.I)) chatText += "I";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.J)) chatText += "J";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.K)) chatText += "K";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.L)) chatText += "L";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.M)) chatText += "M";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.N)) chatText += "N";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.O)) chatText += "O";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.P)) chatText += "P";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.Q)) chatText += "Q";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.R)) chatText += "R";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.S)) chatText += "S";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.T)) chatText += "T";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.U)) chatText += "U";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.V)) chatText += "V";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.W)) chatText += "W";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.X)) chatText += "X";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.Y)) chatText += "Y";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.Z)) chatText += "Z";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) chatText += "1";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) chatText += "2";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) chatText += "3";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) chatText += "4";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) chatText += "5";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)) chatText += "6";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)) chatText += "7";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)) chatText += "8";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_9)) chatText += "9";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) chatText += "0";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) chatText += " ";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.SLASH)) chatText += "/";

		}
		showMessage(sb);

	}
	
	public void showMessage(SpriteBatch sb)
	{
		while(MessageManager.getInstance().hasTextMessage())
		{
			System.out.println("player got messages!");
			if(messages.size() >= 4)
			{
				for(int i = messages.size(); i > 0; i--)
				{
					messages.set(i, messages.get(i-1));
				}
				messages.set(0, MessageManager.getInstance().getTextMessage().msg + "\\n");
			}
			else
			{
				messages.add(0, MessageManager.getInstance().getTextMessage().msg + "\\n");
			}
		}
		BitmapFont messageFont = TextureSingleton.getInstance().scoreFont;
		
		messageFont.setColor(1.0f,1.0f,1.0f,1.0f);
		// draw them
		for(int i = 0; i < messages.size(); i++)
		{
			messageFont.draw(sb, messages.get(i), MainClient.WIDTH/2, MainClient.HEIGHT/2 - 100 * i);
		}
	}


	private void sendMessage(String chatText2)
	{
		TextMessage msg = new TextMessage();
		CurrentPlayer temp = Game.getInstance().player;
		msg.from = temp.name;
		if(temp.team1)
			msg.to = "team1";
		else
			msg.to = "team2";
		
		// now parse it, to see who to send it to
		// options are
		// all, team, user/group
		
		//team has no specific heading
		// all and user/group both start with /
		if(chatText2.length() > 2)
		{
			if(chatText2.substring(2,3).equals("//"))
			{
				// this means they were chatting specifically to people!!
				
				if(chatText2.length() > 6)
				{
					// all starts with all
					if(chatText2.substring(3,6).equals("all"))
					{
						msg.msg = chatText2.substring(6);
						msg.to = "all";
						System.out.println("message sent to all");
						MessageManager.getInstance().sendMessageToServer(msg);
					}
					// user/group starts with msg
					else if(chatText2.substring(3,7).equals("msg "))
					{
						
						// we need the second slash,
						// to mark where we stop looking for users
						// to read in
						if(chatText2.indexOf("/",6) == -1)
						{
							// not proper format!!
							msg.msg = chatText2;
						}
						else // there is a second slash!!
						{
							// everything after the second slash is the message
							msg.msg = chatText2.substring(chatText2.indexOf("/",6));
							String name = "";
							int indexHold = 6;
							// boolean correctFormat = false;
							ArrayList<String> peopleToMessage = new ArrayList<String>();
							
							while(indexHold != -1 && chatText2.indexOf(",", indexHold) != -1)
							{
								// the name is everything from indexHold to the comma
								for(int i = indexHold; i < chatText2.indexOf(indexHold,chatText2.indexOf(",",indexHold) ); i++)
								{
									if(!Character.isWhitespace(chatText2.charAt(i)))
									{
										name += chatText2.substring(i,i+1);
									}
								}
								peopleToMessage.add(name);
								// name = "";
								
								if(chatText2.indexOf(",",indexHold+1) == -1)
								{
									// do the /
									// go get it
									// it is guaranteed
									// to exist
									
									for(int i = indexHold; i < chatText2.indexOf(indexHold,chatText2.indexOf("//",indexHold) ); i++)
									{
										if(!Character.isWhitespace(chatText2.charAt(i)))
										{
											name+=chatText2.substring(i,i+1);
										}
									}
									// correctFormat = true;
									peopleToMessage.add(name);
									// name = "";
									// name = chatText2.substring(indexHold, chatText2.indexOf("/",indexHold));
								}
								System.out.println("message sent to " + name);
								msg.to = name;
								// msg.msg = messageText;
								MessageManager.getInstance().sendMessageToServer(msg);
								// refresh for looping
								name = "";
								indexHold = chatText2.indexOf(",",indexHold + 1);
							}
							
						}
					}
				}
				else
				{
					// we are dealing with a string of length > 0 and <= 4
					// this is valid, but useless
					msg.msg = chatText2;
				}
			}
			else
			{
				msg.msg = chatText2;
			}
		}
		else
			msg.msg = chatText2;
		
		System.out.println("message is " + msg.msg);
		
		Game.getInstance().chat = null;

	} 
}
