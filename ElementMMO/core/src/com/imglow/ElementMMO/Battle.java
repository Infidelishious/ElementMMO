package com.imglow.ElementMMO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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
	
	Button goButton;
	
	TextureRegion currentPlayerBattleElementImage;
	TextureRegion otherPlayerBattleElementImage;
	
	int currentPlayerBattleElementNum;
	int otherPlayerBattleElementNum;
	
	CurrentPlayer currentPlayer;
	OtherPlayer otherPlayer;
	
	// SpriteBatch sb;
	String messageToSend;
	
	static int TEN_SECONDS = 600;
	int timeRemaining = TEN_SECONDS;
	
	public Battle(final CurrentPlayer currentPlayer, final OtherPlayer otherPlayer)
	{
		// this.sb = sb;
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
		//draw window
		
		BattleMessage fromEnemy = MessageManager.getInstance().getBattleMessage();
		if(fromEnemy != null)
		{
			// we've made contact!!!
		}
		sb.draw(TextureSingleton.getInstance().white, -MainClient.WIDTH/4, -MainClient.HEIGHT/4, MainClient.WIDTH/2, MainClient.HEIGHT/2);		
		
		//draw BATTLE!
		
		 sb.draw(TextureSingleton.getInstance().battle, -100, 100, 200, 60);
		 
		 sb.draw(goButton.spr, -40, 0, 60, 60);
		 
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
		// sb.draw(new Texture(Gdx.files.internal("vs.jpg")), -25, 0, 30, 30);
		sb.draw(TextureSingleton.getInstance().whiteRegion, 100, 0);
	}
	
	
	public void assignHealth()
	{
		switch(currentPlayer.health)
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
		
		switch(otherPlayer.health)
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
				currentPlayerBattleElementNum = i;
				//add that item to their inventory display at the next available spot
				currentPlayerInventoryImages[inventoryElementNum] = TextureSingleton.getInstance().elements.get(i);
				//indicate that one should now move to the next available spot
				currentPlayerInventoryButtons[inventoryElementNum] = new Button(currentPlayerInventoryImages[inventoryElementNum], 0, 0, 0, 0, 
				new OnClickListener()
				{
					@Override
					public void onClick(Button source, Vector3 pos) 
					{
						assignHealth();
						currentPlayerBattleElementImage = source.spr;
						source.spr = TextureSingleton.getInstance().goGrayed;
						BattleMessage startBattleMessage = new BattleMessage();
						startBattleMessage.to = otherPlayer.name;
						startBattleMessage.event = "" + currentPlayerBattleElementNum;
						messageToSend = startBattleMessage.event;
						MessageManager.getInstance().sendMessageToServer(startBattleMessage);
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
		Game.getInstance().bg = null;
	}
}
