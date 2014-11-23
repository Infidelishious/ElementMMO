package com.imglow.ElementMMO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Battle implements Drawable
{	
	TextureRegion currentPlayerImage;
	TextureRegion otherPlayerImage;
	
	Texture currentPlayerHeartImage1;
	Texture currentPlayerHeartImage2;
	Texture currentPlayerHeartImage3;

	Texture otherPlayerHeartImage1;
	Texture otherPlayerHeartImage2;
	Texture otherPlayerHeartImage3;
	
	TextureRegion[] currentPlayerInventoryImages;
	
	TextureRegion currentPlayerBattleElementImage;
	TextureRegion otherPlayerBattleElementImage;
	
	CurrentPlayer currentPlayer;
	OtherPlayer otherPlayer;
	
	SpriteBatch sb;
	
	public Battle(CurrentPlayer currentPlayer, OtherPlayer otherPlayer)
	{
		this.sb = sb;
		this.currentPlayer = currentPlayer;
		this.otherPlayer = otherPlayer;
		currentPlayerInventoryImages = new TextureRegion[6];
		
			
		//load the players information into the GUI.
		
		//client-event of choosing an element to use is sent to the server.
		
		//other player's message is taken from the server
		
		//update client's information based on other player's message
		
		//conditionals 
	}
	
	@Override
	public void draw(SpriteBatch sb) 
	{
		assignTextures();
		//draw window
		
		sb.draw(TextureSingleton.getInstance().white, -MainClient.WIDTH/4, -MainClient.HEIGHT/4, MainClient.WIDTH/2, MainClient.HEIGHT/2);		
		
		//draw BATTLE!
		
		Texture tempBattleTexture = new Texture(Gdx.files.internal("battle_header.jpg"));
		sb.draw(tempBattleTexture, -50, 150);
		
		//draw player sprites
		
		//this player
		sb.draw(currentPlayerImage, -MainClient.HEIGHT/2+50, 0, Player.WIDTH, Player.HEIGHT);
		//other player
		sb.draw(otherPlayerImage, MainClient.HEIGHT/2-125, 0, Player.WIDTH, Player.HEIGHT);

		//draw health amounts
		Texture tempFullHeartTexture = new Texture(Gdx.files.internal("full_heart.jpg"));
		Texture tempHalfHeartTexture = new Texture(Gdx.files.internal("half_heart.jpg"));
		Texture tempNoHeartTexture = new Texture(Gdx.files.internal("no_heart.jpg"));
		//this player
		sb.draw(currentPlayerHeartImage1, -MainClient.WIDTH/4+10, 100, 16, 16);
		sb.draw(currentPlayerHeartImage2, -MainClient.WIDTH/4+30, 100, 16, 16);
		sb.draw(currentPlayerHeartImage3, -MainClient.WIDTH/4+50, 100, 16, 16);
		//other player
		sb.draw(otherPlayerHeartImage3, MainClient.WIDTH/4-45, 100, 16, 16);
		sb.draw(otherPlayerHeartImage2, MainClient.WIDTH/4-65, 100, 16, 16);
		sb.draw(otherPlayerHeartImage1, MainClient.WIDTH/4-85, 100, 16, 16);

		//draw space for element slots
		sb.draw(new Texture(Gdx.files.internal("grey.jpg")), -MainClient.WIDTH/4+150, -MainClient.HEIGHT/4+10, MainClient.WIDTH/4, MainClient.HEIGHT/4-50);
		
		//draw element slots
		sb.draw(currentPlayerInventoryImages[0], -MainClient.WIDTH/4+180, -80);
		sb.draw(currentPlayerInventoryImages[1], -MainClient.WIDTH/4+180, -120);
		sb.draw(currentPlayerInventoryImages[2], -MainClient.WIDTH/4+180, -160);
		sb.draw(currentPlayerInventoryImages[3], -MainClient.WIDTH/4+340, -80);
		sb.draw(currentPlayerInventoryImages[4], -MainClient.WIDTH/4+340, -120);
		sb.draw(currentPlayerInventoryImages[5], -MainClient.WIDTH/4+340, -160);

		//draw battle element slots
		sb.draw(TextureSingleton.getInstance().imagination, -200, 0);
		sb.draw(new Texture(Gdx.files.internal("vs.jpg")), -25, 0, 30, 30);
		sb.draw(TextureSingleton.getInstance().imagination, 100, 0);
	}

	public void assignTextures()
	{
		//assign player sprites
		currentPlayerImage = TextureSingleton.getInstance().playerSprites.get(currentPlayer.type).get(TextureSingleton.STAND);
		otherPlayerImage = TextureSingleton.getInstance().playerSprites.get(otherPlayer.type).get(TextureSingleton.STAND);
		
		//assign health amounts
		switch(currentPlayer.health)
		{
			case 0:
				currentPlayerHeartImage1 = new Texture(Gdx.files.internal("no_heart.jpg"));
				currentPlayerHeartImage2 = new Texture(Gdx.files.internal("no_heart.jpg"));
				currentPlayerHeartImage3 = new Texture(Gdx.files.internal("no_heart.jpg"));
				break;
			case 1:
				currentPlayerHeartImage1 = new Texture(Gdx.files.internal("half_heart.jpg"));
				currentPlayerHeartImage2 = new Texture(Gdx.files.internal("no_heart.jpg"));
				currentPlayerHeartImage3 = new Texture(Gdx.files.internal("no_heart.jpg"));
				break;
			case 2:
				currentPlayerHeartImage1 = new Texture(Gdx.files.internal("full_heart.jpg"));
				currentPlayerHeartImage2 = new Texture(Gdx.files.internal("no_heart.jpg"));
				currentPlayerHeartImage3 = new Texture(Gdx.files.internal("no_heart.jpg"));
				break;
			case 3:
				currentPlayerHeartImage1 = new Texture(Gdx.files.internal("full_heart.jpg"));
				currentPlayerHeartImage2 = new Texture(Gdx.files.internal("half_heart.jpg"));
				currentPlayerHeartImage3 = new Texture(Gdx.files.internal("no_heart.jpg"));
				break;
			case 4:
				currentPlayerHeartImage1 = new Texture(Gdx.files.internal("full_heart.jpg"));
				currentPlayerHeartImage2 = new Texture(Gdx.files.internal("full_heart.jpg"));
				currentPlayerHeartImage3 = new Texture(Gdx.files.internal("no_heart.jpg"));
				break;
			case 5: 
				currentPlayerHeartImage1 = new Texture(Gdx.files.internal("full_heart.jpg"));
				currentPlayerHeartImage2 = new Texture(Gdx.files.internal("full_heart.jpg"));
				currentPlayerHeartImage3 = new Texture(Gdx.files.internal("half_heart.jpg"));
				break;
			case 6:
				currentPlayerHeartImage1 = new Texture(Gdx.files.internal("full_heart.jpg"));
				currentPlayerHeartImage2 = new Texture(Gdx.files.internal("full_heart.jpg"));
				currentPlayerHeartImage3 = new Texture(Gdx.files.internal("full_heart.jpg"));
				break;
		}
		
		switch(otherPlayer.health)
		{
			case 0:
				otherPlayerHeartImage1 = new Texture(Gdx.files.internal("no_heart.jpg"));
				otherPlayerHeartImage2 = new Texture(Gdx.files.internal("no_heart.jpg"));
				otherPlayerHeartImage3 = new Texture(Gdx.files.internal("no_heart.jpg"));
				break;
			case 1:
				otherPlayerHeartImage1 = new Texture(Gdx.files.internal("half_heart.jpg"));
				otherPlayerHeartImage2 = new Texture(Gdx.files.internal("no_heart.jpg"));
				otherPlayerHeartImage3 = new Texture(Gdx.files.internal("no_heart.jpg"));
				break;
			case 2:
				otherPlayerHeartImage1 = new Texture(Gdx.files.internal("full_heart.jpg"));
				otherPlayerHeartImage2 = new Texture(Gdx.files.internal("no_heart.jpg"));
				otherPlayerHeartImage3 = new Texture(Gdx.files.internal("no_heart.jpg"));
				break;
			case 3:
				otherPlayerHeartImage1 = new Texture(Gdx.files.internal("full_heart.jpg"));
				otherPlayerHeartImage2 = new Texture(Gdx.files.internal("half_heart.jpg"));
				otherPlayerHeartImage3 = new Texture(Gdx.files.internal("no_heart.jpg"));
				break;
			case 4:
				otherPlayerHeartImage1 = new Texture(Gdx.files.internal("full_heart.jpg"));
				otherPlayerHeartImage2 = new Texture(Gdx.files.internal("full_heart.jpg"));
				otherPlayerHeartImage3 = new Texture(Gdx.files.internal("no_heart.jpg"));
				break;
			case 5: 
				otherPlayerHeartImage1 = new Texture(Gdx.files.internal("full_heart.jpg"));
				otherPlayerHeartImage2 = new Texture(Gdx.files.internal("full_heart.jpg"));
				otherPlayerHeartImage3 = new Texture(Gdx.files.internal("half_heart.jpg"));
				break;
			case 6:
				otherPlayerHeartImage1 = new Texture(Gdx.files.internal("full_heart.jpg"));
				otherPlayerHeartImage2 = new Texture(Gdx.files.internal("full_heart.jpg"));
				otherPlayerHeartImage3 = new Texture(Gdx.files.internal("full_heart.jpg"));
				break;
		}
				
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
			if(currentPlayer.owned.get(i));
			{
				//add that item to their inventory display at the next available spot
				currentPlayerInventoryImages[inventoryElementNum] = TextureSingleton.getInstance().elements.get(i);
				//indicate that one should now move to the next available spot
				inventoryElementNum++;
			}
		}
		
		//if not all inventory spots have been exhausted
		while(inventoryElementNum < 6)
		{
			//fill remaining inventory elements with white space
			currentPlayerInventoryImages[inventoryElementNum] = TextureSingleton.getInstance().whiteGrass;
			inventoryElementNum++;
		}
		
		//assign battle element slots as whitespace until event causes them to be otherwise
		currentPlayerBattleElementImage = TextureSingleton.getInstance().whiteGrass;
		otherPlayerBattleElementImage = TextureSingleton.getInstance().whiteGrass;
	}
}
