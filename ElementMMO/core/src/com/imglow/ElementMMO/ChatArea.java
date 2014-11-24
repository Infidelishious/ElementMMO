package com.imglow.ElementMMO;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ChatArea implements Drawable{
	protected String chatText;
	boolean first, visible = false;
	String recievedMessage;
	String str1, str2, str3, str4;
	int count = 0;
	ChatArea() {
		chatText = new String("");
		first = true;
		str1 = new String (""); 
		str2 = new String (""); 
		str3 = new String (""); 
		str4 = new String("");
	}


	@Override
	public void draw(SpriteBatch sb)
	{

		BitmapFont chatFont = TextureSingleton.getInstance().scoreFont;
		chatFont.setColor(0.0f,0.0f,0.0f,1.0f);
		chatFont.setScale(2.0f);
		if(visible)
		{
			chatFont.draw(sb, chatText, -MainClient.WIDTH/2 + 30, -MainClient.HEIGHT/2 + 75);
			if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) sendMessage(chatText.substring(2));
			if(Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE) && chatText.length() > 2)
				chatText = chatText.substring(0, chatText.length() - 1);
			if(chatText.length() < 50)
			{
				first = false;
				if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {
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
					else if(Gdx.input.isKeyJustPressed(Input.Keys.SLASH)) chatText += "?";
				}
				else {
					if(Gdx.input.isKeyJustPressed(Input.Keys.A)) chatText += "a";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.B)) chatText += "b";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.C)) chatText += "c";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.D)) chatText += "d";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.E)) chatText += "e";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.F)) chatText += "f";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.G)) chatText += "g";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.H)) chatText += "h";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.I)) chatText += "i";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.J)) chatText += "j";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.K)) chatText += "k";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.L)) chatText += "l";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.M)) chatText += "m";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.N)) chatText += "n";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.O)) chatText += "o";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.P)) chatText += "p";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.Q)) chatText += "q";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.R)) chatText += "r";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.S)) chatText += "s";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.T)) chatText += "t";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.U)) chatText += "u";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.V)) chatText += "v";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.W)) chatText += "w";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.X)) chatText += "x";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.Y)) chatText += "y";
					else if(Gdx.input.isKeyJustPressed(Input.Keys.Z)) chatText += "z";
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
					else if(Gdx.input.isKeyJustPressed(Input.Keys.COMMA)) chatText += ",";
				}
			}
		}
		else
		{
			if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
			{
				visible = true;
				chatText = "> "; 
			}
		}
		showMessage(sb);

	}

	public void showMessage(SpriteBatch sb)
	{
		TextMessage msg;
		BitmapFont messageFont = TextureSingleton.getInstance().scoreFont;
		if(count < 1){
			while(MessageManager.getInstance().hasTextMessage())
			{
				msg = MessageManager.getInstance().getTextMessage();
				str1 = msg.from + ": " + msg.msg;
				System.out.println(str1);
				count++;
			}
		}
		else if(count == 1){
			while(MessageManager.getInstance().hasTextMessage()) {
				msg = MessageManager.getInstance().getTextMessage();
				str2 = str1;
				str1 = msg.from + ": " + msg.msg;
				count++;
			}
		}
		else if(count == 2){
			while(MessageManager.getInstance().hasTextMessage()) {
				msg = MessageManager.getInstance().getTextMessage();
				str3 = str2;
				str2 = str1;
				str1 = msg.from + ": " + msg.msg;
				count++;
			}
		}
		else if(count == 3){
			while(MessageManager.getInstance().hasTextMessage()) {
				msg = MessageManager.getInstance().getTextMessage();
				str4 = str3;
				str3 = str2;
				str2 = str1;
				str1 = msg.from + ": " + msg.msg;
			}
		}
		messageFont.draw(sb, str1, -MainClient.WIDTH/2 + 30, -MainClient.HEIGHT/2 + 150);
		messageFont.draw(sb, str2, -MainClient.WIDTH/2 + 30, -MainClient.HEIGHT/2 + 200);
		messageFont.draw(sb, str3, -MainClient.WIDTH/2 + 30, -MainClient.HEIGHT/2 + 250);
		messageFont.draw(sb, str4, -MainClient.WIDTH/2 + 30, -MainClient.HEIGHT/2 + 300);

		System.out.println(str1);

	}


	private void sendMessage(String chatText2)
	{
		TextMessage msg = new TextMessage();
		CurrentPlayer temp = Game.getInstance().player;
		msg.from = temp.name;
		if(!chatText2.isEmpty()){
			if(!chatText2.substring(0,1).equals("/")){
				if(temp.team1)
					msg.to = "team1";
				else
					msg.to = "team2";
				MessageManager.getInstance().sendMessageToServer(msg);
			}
			// now parse it, to see who to send it to
			// options are
			// all, team, user/group

			//team has no specific heading
			// all and user/group both start with
			if(chatText2.length() > 1)
			{	
				if(chatText2.substring(0,1).equals("/"))
				{
					// this means they were chatting specifically to people!!

					if(chatText2.length() > 4)
					{	
						// all starts with all

						if(chatText2.substring(1,4).equals("ALL") || chatText2.substring(1,4).equals("all"))
						{
							System.out.println("reached here");
							msg.msg = chatText2.substring(4);
							msg.to = "all";
							MessageManager.getInstance().sendMessageToServer(msg);
						}
						// user/group starts with msg
						else if(chatText2.substring(1,5).equals("MSG ") || chatText2.substring(1,5).equals("msg "))
						{

							// we need the second slash,
							// to mark where we stop looking for users
							// to read in
							// everything after the second slash is the message
							msg.msg = chatText2.substring(chatText2.indexOf("/")+1);
							String recipents = new String ("");
							recipents = msg.msg;
							System.out.println(msg.msg);
							msg.msg = msg.msg.substring(msg.msg.indexOf("/")+1);
							String name = "";
							int indexHold = 4;
							// boolean correctFormat = false;
							ArrayList<String> peopleToMessage = new ArrayList<String>();
							recipents = chatText2.substring(0, chatText2.indexOf("/"));
							while(recipents != null)
							{
								String sendTo = new String("");
								if(!recipents.contains(",")) {
									sendTo = recipents.substring(0, recipents.indexOf("/") - 1);
									recipents = null;
								}
								else {
									sendTo = recipents.substring(0, recipents.indexOf(",") -1);
									if(recipents.contains(",")) recipents = recipents.substring(recipents.indexOf(",") + 1);
									else recipents = recipents.substring(0, recipents.indexOf("/") -1);
									sendTo.replaceAll("\\s+","");
									msg.to = sendTo;
								}
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

			visible = false;
		} 
	}
}
