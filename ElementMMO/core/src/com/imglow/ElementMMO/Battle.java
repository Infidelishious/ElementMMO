package com.imglow.ElementMMO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class Battle implements Drawable
{	
	TextureRegion currentPlayerImage;
	TextureRegion otherPlayerImage;

	TextureRegion halfHeartTexture;
	TextureRegion fullHeartTexture;
	TextureRegion noHeartTexture;

	TextureRegion currentPlayerHeartImage1;
	TextureRegion currentPlayerHeartImage2;
	TextureRegion currentPlayerHeartImage3;

	TextureRegion otherPlayerHeartImage1;
	TextureRegion otherPlayerHeartImage2;
	TextureRegion otherPlayerHeartImage3;

	TextureRegion[] currentPlayerInventoryImages;
	Button[] currentPlayerInventoryButtons;

	// Button goButton;

	TextureRegion currentPlayerBattleElementImage;
	TextureRegion otherPlayerBattleElementImage;

	int currentPlayerBattleElementNum;
	int otherPlayerBattleElementNum;

	// don't have to worry about references
	// and modify the game health
	// only the current battle health
	int currentPlayerHealth;
	int otherPlayerHealth;
	
	CurrentPlayer currentPlayer;
	OtherPlayer otherPlayer;

	int TDMcount = 0;
	// SpriteBatch sb;
	String messageToSend;
	String messageReceived;

	static int MAX_TIME = 60*10; // 60 times per second * 10
	static int PAUSED_TIME = 60;
	int pausedRemaining = 0;
	int timeRemaining = MAX_TIME;

	public Battle(final CurrentPlayer currentPlayer, final OtherPlayer otherPlayer)
	{
		messageToSend = "";
		messageReceived = "";
		// this.sb = sb;
		this.currentPlayerHealth = currentPlayer.health;
		this.otherPlayerHealth = otherPlayer.health;
		
		this.currentPlayer = currentPlayer;
		this.otherPlayer = otherPlayer;

		currentPlayerInventoryImages = new TextureRegion[6];
		currentPlayerInventoryButtons = new Button[6];

		fullHeartTexture = TextureSingleton.getInstance().fullHeart;
		halfHeartTexture = TextureSingleton.getInstance().halfHeart;
		noHeartTexture = TextureSingleton.getInstance().noHeart;

		currentPlayerBattleElementNum = -1;
		/*
		goButton = new Button(TextureSingleton.getInstance().go, -40, 0, 60, 60, new OnClickListener()
		{
			@Override
			public void onClick(Button source, Vector3 pos) 
			{
				source.spr = TextureSingleton.getInstance().goGrayed;
				BattleMessage startBattleMessage = new BattleMessage();
				startBattleMessage.to = otherPlayer.name;
				startBattleMessage.event = "" + currentPlayerBattleElementNum;
				MessageManager.getInstance().sendMessageToServer(startBattleMessage);
			}
		});
		 */
		TextureSingleton.getInstance().EnterBattle();
		//load the players information into the GUI.
		assignTextures();

		//client-event of choosing an element to use is sent to the server.

		//other player's message is taken from the server

		//update client's information based on other player's message

		//conditionals 
	}

	@Override
	public void draw(SpriteBatch sb) 
	{

		// when doing the battle, only calculate your own shit
		// don't worry about your opponent's stuff.
		// trust his to work
		if(pausedRemaining == 1)
		{
			// right before pause,
			// reset the images
			otherPlayerBattleElementImage = null;
			currentPlayerBattleElementImage = null;
			timeRemaining = MAX_TIME;
		}
		if(pausedRemaining > 0)
		{
			pausedRemaining--;
		}
		else
		{
			// we can't see the other player's image after
			// the pause

			//draw window
			// reset the timer
			timeRemaining--;
			if(timeRemaining <= 0)
			{
				// if MAX_TIME seconds elapses
				// if we have not yet received a message from enemy
				// then damage them
				if(messageReceived.equals(""))
				{
					otherPlayerHealth--;
					BattleMessage toSend = new BattleMessage();
					toSend.from = currentPlayer.name;
					toSend.to = otherPlayer.name;
					toSend.event = "TDM";
					MessageManager.getInstance().sendMessageToServer(toSend);
				}

				// reset the timer
				timeRemaining = MAX_TIME;
			}
			
			if(TDMcount > 2)
			{
				Game.getInstance().player.sendToSpawn();
				// you died!!
				Game.getInstance().player.health = 6;
				currentPlayer.health = 6;
				dispose();
			}
			// if u have no health left
			// game over man
			else if(otherPlayerHealth <= 0 || currentPlayerHealth <= 0)
			{
				
				// game over man
				// each player shud calc it themselves
				
				
				if(currentPlayerHealth > otherPlayerHealth)
				{
					
					// if they both have 1 health and then get hit
					// at the same time
					// then the most dmg they could take
					// is 1
					// so currentPlayerHealth will never be < 0 at this point
					
					Game.getInstance().player.money+= 200;
					/*
					for(int i = 0; i < Game.getInstance().otherPlayers.size(); i++)
					{
						if(otherPlayer.name.equals(Game.getInstance().otherPlayers.get(i).name))
						{
							Game.getInstance().otherPlayers.get(i).health = 6;
						}
						
					}
					otherPlayerHealth = 6;
					*/
					
				}
				else
				{
					Game.getInstance().player.sendToSpawn();
					// you died!!
					Game.getInstance().player.health = 6;
					currentPlayer.health = 6;
				}
				// we stay where we are
				// we do not go back to spawn
				
				/*BattleMessage toSend = new BattleMessage();
				toSend.to = otherPlayer.name;
				toSend.from = currentPlayer.name;
				toSend.event = "GPW"; // Game Player Win 
				MessageManager.getInstance().sendMessageToServer(toSend);*/
				dispose();

			}
			/*
			else if(currentPlayer.health <= 0)
			{
				BattleMessage toSend = new BattleMessage();
				toSend.to = otherPlayer.name;
				toSend.from = currentPlayer.name;
				toSend.event = "GOW"; // Game Opponent Win (opponent wins)
				MessageManager.getInstance().sendMessageToServer(toSend);
				
				// close the battle thing

				dispose();
			}
			*/
			else
			{
				// get a message
				// get the latest update from the front
				BattleMessage fromEnemy = MessageManager.getInstance().getBattleMessage();
				if(fromEnemy != null)
				{
					// is it to us
					System.out.println("fromEnemy.to is " + fromEnemy.to);
					System.out.println("fromEnemy.event is " + fromEnemy.event);
					if(fromEnemy.to.equals(currentPlayer.name))
					{
						// enemy has sent us something!!
						messageReceived = fromEnemy.event;
					}
				}
			}
			
			/*
			// now check the messageReceived stuff
			if(messageReceived.equals("GPW")) // game over
			{
				// we lost boys
				BattleMessage toSend = new BattleMessage();
				toSend.to = otherPlayer.name;
				toSend.from = currentPlayer.name;
				toSend.event = "GOW"; // Game Opponent Win (opponent wins)
				MessageManager.getInstance().sendMessageToServer(toSend);
				Game.getInstance().player.sendToSpawn();
				// you died!!
				Game.getInstance().player.health = 6;
				// close the battle thing
				dispose();

			}
			else if(messageReceived.equals("GOW"))
			{
				// VICTORY !111!!!!1!1!
				// they ran out of health
				Game.getInstance().player.money+= 200;
				// we stay where we are
				// we do not go back to spawn
				BattleMessage toSend = new BattleMessage();
				toSend.to = otherPlayer.name;
				toSend.from = currentPlayer.name;
				toSend.event = "GPW"; // Game Player Win 
				MessageManager.getInstance().sendMessageToServer(toSend);
				dispose();
			}
			*/
			if(messageReceived.equals("TDM"))
			{
				// the enemy waited 10 seconds
				// and we did not send a message
				// we are punished for our transgression
				timeRemaining = MAX_TIME;
				// make sure everytime we do dis

				currentPlayerHealth--;
				otherPlayerBattleElementImage = null;
				currentPlayerBattleElementImage = null;
				// reset messagereceived
				messageReceived = "";
				messageToSend = "";
				TDMcount++;
			}
			else if(!messageReceived.equals(""))
			{
				// messageRecieved = RA#
				if(!messageToSend.equals(""))
				{
					// messageToSend = RA#
					// DANNG
					// compare the messageReceived
					// with our messageToSend
					int playerAttack = Integer.parseInt(messageToSend.substring(2));
					int enemyAttack = Integer.parseInt(messageReceived.substring(2));
					
					System.out.println("messageToSend being parsed is " + messageToSend.substring(2) +
							" and playerAttack is " + playerAttack);
					System.out.println("messageReceived being parsed is " + messageReceived.substring(2) +
							" and enemyAttack is " + enemyAttack);
					int battleResult = BattleLogics.battle(playerAttack, enemyAttack);

					// only calculate the battle result for your own self!!!
					// do not calculate for opponent
					if(battleResult == -1)
					{
						// we lost boys
						currentPlayerHealth-= 2;

					}
					else if(battleResult == 0)
					{
						// a tie is acceptable
						// i guess
						currentPlayerHealth--;
						otherPlayerHealth--;
					}
					else // battleResult == 1
					{
						// WE WON!!!
						// enemy damages himself pew pew
						otherPlayerHealth-= 2;
					}
					// reset our messagelistener things
					messageToSend = "";
					messageReceived = "";
					timeRemaining = MAX_TIME;
					currentPlayerBattleElementImage = TextureSingleton.getInstance().elements.get(playerAttack);
					otherPlayerBattleElementImage = TextureSingleton.getInstance().elements.get(enemyAttack);
					pausedRemaining = PAUSED_TIME;
				}
				else
				{
					// still waiting for the currentPlayer to send a msg
				}
			}
						
		}
		assignHealth();
		sb.draw(TextureSingleton.getInstance().white, -MainClient.WIDTH/4, -MainClient.HEIGHT/4, MainClient.WIDTH/2, MainClient.HEIGHT/2);		

		//draw BATTLE!

		sb.draw(TextureSingleton.getInstance().battle, -100, 100, 200, 60);

		// sb.draw(goButton.spr, -40, 0, 60, 60);

		//draw player sprites

		sb.draw(currentPlayerImage, -MainClient.HEIGHT/2+50, 0, Player.WIDTH, Player.HEIGHT);
		sb.draw(otherPlayerImage, MainClient.HEIGHT/2-125, 0, Player.WIDTH, Player.HEIGHT);

		//draw health amounts
		//this player
		sb.draw(currentPlayerHeartImage1, -MainClient.WIDTH/4+10, 100, 16, 16);
		sb.draw(currentPlayerHeartImage2, -MainClient.WIDTH/4+30, 100, 16, 16);
		sb.draw(currentPlayerHeartImage3, -MainClient.WIDTH/4+50, 100, 16, 16);
		//other player
		sb.draw(otherPlayerHeartImage3, MainClient.WIDTH/4-45, 100, 16, 16);
		sb.draw(otherPlayerHeartImage2, MainClient.WIDTH/4-65, 100, 16, 16);
		sb.draw(otherPlayerHeartImage1, MainClient.WIDTH/4-85, 100, 16, 16);

		//draw space for element slots
		sb.draw(new Texture(Gdx.files.internal("images/gray.png")), -MainClient.WIDTH/4+150, -MainClient.HEIGHT/4+10, MainClient.WIDTH/4, MainClient.HEIGHT/4-50);

		//draw element slots
		currentPlayerInventoryButtons[0].width = 100;
		currentPlayerInventoryButtons[0].height = 30;
		currentPlayerInventoryButtons[0].x = -MainClient.WIDTH/4+180;
		currentPlayerInventoryButtons[0].y = -80;
		sb.draw(currentPlayerInventoryImages[0], -MainClient.WIDTH/4+180, -80, 100, 30);

		currentPlayerInventoryButtons[1].width = 100;
		currentPlayerInventoryButtons[1].height = 30;
		currentPlayerInventoryButtons[1].x = -MainClient.WIDTH/4+180;
		currentPlayerInventoryButtons[1].y = -120;
		sb.draw(currentPlayerInventoryImages[1], -MainClient.WIDTH/4+180, -120, 100, 30);

		currentPlayerInventoryButtons[2].width = 100;
		currentPlayerInventoryButtons[2].height = 30;
		currentPlayerInventoryButtons[2].x = -MainClient.WIDTH/4+180;
		currentPlayerInventoryButtons[2].y = -160;
		sb.draw(currentPlayerInventoryImages[2], -MainClient.WIDTH/4+180, -160, 100, 30);

		currentPlayerInventoryButtons[3].width = 100;
		currentPlayerInventoryButtons[3].height = 30;
		currentPlayerInventoryButtons[3].x = -MainClient.WIDTH/4+340;
		currentPlayerInventoryButtons[3].y = -80;
		sb.draw(currentPlayerInventoryImages[3], -MainClient.WIDTH/4+340, -80, 100, 30);

		currentPlayerInventoryButtons[4].width = 100;
		currentPlayerInventoryButtons[4].height = 30;
		currentPlayerInventoryButtons[4].x = -MainClient.WIDTH/4+340;
		currentPlayerInventoryButtons[4].y = -120;
		sb.draw(currentPlayerInventoryImages[4], -MainClient.WIDTH/4+340, -120, 100, 30);

		currentPlayerInventoryButtons[5].width = 100;
		currentPlayerInventoryButtons[5].height = 30;
		currentPlayerInventoryButtons[5].x = -MainClient.WIDTH/4+340;
		currentPlayerInventoryButtons[5].y = -160;
		sb.draw(currentPlayerInventoryImages[5], -MainClient.WIDTH/4+340, -160, 100, 30);

		//draw battle element slots
		if(currentPlayerBattleElementImage != null)
			sb.draw(currentPlayerBattleElementImage, -200, 0);
		else
			sb.draw(TextureSingleton.getInstance().whiteRegion,-200,0);

		if(otherPlayerBattleElementImage != null)
			sb.draw(otherPlayerBattleElementImage, 100, 0);
		else
			sb.draw(TextureSingleton.getInstance().whiteRegion,100,0);
		// sb.draw(new Texture(Gdx.files.internal("vs.jpg")), -25, 0, 30, 30);
		// sb.draw(TextureSingleton.getInstance().whiteRegion, 75, 0);

		if(pausedRemaining <= 0)
		{
			BitmapFont timerDraw = TextureSingleton.getInstance().scoreFont;
			sb.setColor(Color.BLACK);
			timerDraw.setColor(Color.BLACK);
			int timeRemainingDigit = (timeRemaining+1)/60;
			timerDraw.draw(sb , "" + timeRemainingDigit, -30, 0);
			sb.setColor(Color.WHITE);
		}

		//sb.setColor(Color.WHITE);

		// scoreFont.draw
	}


	public void assignHealth()
	{
		switch(currentPlayerHealth)
		{
		case 0:
			currentPlayerHeartImage1 = noHeartTexture;
			currentPlayerHeartImage2 = noHeartTexture;
			currentPlayerHeartImage3 = noHeartTexture;
			break;
		case 1:
			currentPlayerHeartImage1 = halfHeartTexture;
			currentPlayerHeartImage2 = noHeartTexture;
			currentPlayerHeartImage3 = noHeartTexture;
			break;
		case 2:
			currentPlayerHeartImage1 = fullHeartTexture;
			currentPlayerHeartImage2 = noHeartTexture;
			currentPlayerHeartImage3 = noHeartTexture;
			break;
		case 3:
			currentPlayerHeartImage1 = fullHeartTexture;
			currentPlayerHeartImage2 = halfHeartTexture;
			currentPlayerHeartImage3 = noHeartTexture;
			break;
		case 4:
			currentPlayerHeartImage1 = fullHeartTexture;
			currentPlayerHeartImage2 = fullHeartTexture;
			currentPlayerHeartImage3 = noHeartTexture;
			break;
		case 5: 
			currentPlayerHeartImage1 = fullHeartTexture;
			currentPlayerHeartImage2 = fullHeartTexture;
			currentPlayerHeartImage3 = halfHeartTexture;
			break;
		case 6:
			currentPlayerHeartImage1 = fullHeartTexture;
			currentPlayerHeartImage2 = fullHeartTexture;
			currentPlayerHeartImage3 = fullHeartTexture;
			break;
		}

		switch(otherPlayerHealth)
		{
		case 0:
			otherPlayerHeartImage1 = noHeartTexture;
			otherPlayerHeartImage2 = noHeartTexture;
			otherPlayerHeartImage3 = noHeartTexture;
			break;
		case 1:
			otherPlayerHeartImage1 = halfHeartTexture;
			otherPlayerHeartImage2 = noHeartTexture;
			otherPlayerHeartImage3 = noHeartTexture;
			break;
		case 2:
			otherPlayerHeartImage1 = fullHeartTexture;
			otherPlayerHeartImage2 = noHeartTexture;
			otherPlayerHeartImage3 = noHeartTexture;
			break;
		case 3:
			otherPlayerHeartImage1 = fullHeartTexture;
			otherPlayerHeartImage2 = halfHeartTexture;
			otherPlayerHeartImage3 = noHeartTexture;
			break;
		case 4:
			otherPlayerHeartImage1 = fullHeartTexture;
			otherPlayerHeartImage2 = fullHeartTexture;
			otherPlayerHeartImage3 = noHeartTexture;
			break;
		case 5: 
			otherPlayerHeartImage1 = fullHeartTexture;
			otherPlayerHeartImage2 = fullHeartTexture;
			otherPlayerHeartImage3 = halfHeartTexture;
			break;
		case 6:
			otherPlayerHeartImage1 = fullHeartTexture;
			otherPlayerHeartImage2 = fullHeartTexture;
			otherPlayerHeartImage3 = fullHeartTexture;
			break;
		}
	}
	public void assignTextures()
	{
		//assign player sprites
		currentPlayerImage = TextureSingleton.getInstance().playerSprites.get(currentPlayer.type).get(TextureSingleton.STAND);
		otherPlayerImage = TextureSingleton.getInstance().playerSprites.get(otherPlayer.type).get(TextureSingleton.STAND);

		//assign health amounts

		assignHealth();
		//assign element textures

		int inventoryElementNum = 0;
		for(int i = 0; i < 12; i++)
		{
			//if all inventory spots have been exhausted
			if(inventoryElementNum >= 6)
			{
				//stop checking whether further elements are owned by current player
				break;
			}

			//if the player owns a given item
			if(currentPlayer.using.get(i))
			{
				//add that item to their inventory display at the next available spot
				currentPlayerInventoryImages[inventoryElementNum] = TextureSingleton.getInstance().elements.get(i);
				//indicate that one should now move to the next available spot
				currentPlayerInventoryButtons[inventoryElementNum] = new Button(currentPlayerInventoryImages[inventoryElementNum], 0, 0, 0, 0, 
						new OnClickListener()
				{
					@Override
					public void onClick(Button source, Vector3 pos) 
					{
						// assignHealth();
						// if not paused, then do stuff
						if(pausedRemaining <= 0)
						{
							
							// figure out which one is selected
							int selected = 0;
							for(int i = 0; i < currentPlayerInventoryButtons.length; i++)
							{
								if(source == currentPlayerInventoryButtons[i])
									selected = i;
							}
							// now we have a selected
							for(int i = 0; i < 12; i++)
							{
								if(currentPlayerInventoryImages[selected] == TextureSingleton.getInstance().elements.get(i))
									currentPlayerBattleElementNum = i;
							}
							// set up the thing to send
							
							// now we know the proper value to send to the bad guy
							// let him know what's comin to him
							currentPlayerBattleElementImage = source.spr;
							// source.spr = TextureSingleton.getInstance().goGrayed;
							messageToSend = "RA" + currentPlayerBattleElementNum;
							BattleMessage battleMessage = new BattleMessage();
							
							battleMessage.to = otherPlayer.name;
							battleMessage.from = currentPlayer.name;
							battleMessage.event = messageToSend;
							
							MessageManager.getInstance().sendMessageToServer(battleMessage);
							// messages to send
							// RA# means that you are doing a regular attack of type #
							// TDM take damage man, opp has stalled, so opp takes damage
							// otherwise end the battle
							// based upon health
							
						}
					}
				}
						);
				inventoryElementNum++;
			}
		}

		//if not all inventory spots have been exhausted
		while(inventoryElementNum < 6)
		{
			//fill remaining inventory elements with white space
			currentPlayerInventoryImages[inventoryElementNum] = TextureSingleton.getInstance().whiteRegion;
			currentPlayerInventoryButtons[inventoryElementNum] = new Button(currentPlayerInventoryImages[inventoryElementNum], 0, 0, 0, 0, 
					new OnClickListener()
			{
				@Override
				public void onClick(Button source, Vector3 pos) 
				{
					// does jack shit
					System.out.println("White space clicked");
				}
			}
					);
			inventoryElementNum++;
		}

		//assign battle element slots as whitespace until event causes them to be otherwise
		// currentPlayerBattleElementImage = TextureSingleton.getInstance().whiteGrass;
		// otherPlayerBattleElementImage = TextureSingleton.getInstance().whiteGrass;
	}

	public void dispose()
	{
		for(int i = 0; i < 6; i++)
		{
			currentPlayerInventoryButtons[i].dispose();
		}
		TextureSingleton.getInstance().ExitBattle();
		Game.getInstance().bg = null;
		Game.getInstance().StatusUpdate();
	}
}
