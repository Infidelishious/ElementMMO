package com.imglow.ElementMMO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Battle implements Drawable
{
	
	TextureRegion currentPlayerImage;
	TextureRegion otherPlayerImage;
	
	Texture[] currentPlayerHealthImages;
	Texture[] otherPlayerHealthImages;
	
	Texture[] playerInventoryImages;
	
	Texture currentPlayerBattleElementImage;
	Texture otherPlayerBattleElementImage;
	
	public Battle()
	{
		//load the players information into the GUI.
		
		//client-event of choosing an element to use is sent to the server.
		
		//other player's message is taken from the server
		
		//update client's information based on other player's message
		
		//conditionals 
	}
	
	@Override
	public void draw(SpriteBatch sb) 
	{
		//draw window
		
		sb.draw(TextureSingleton.getInstance().white, -MainClient.WIDTH/4, -MainClient.HEIGHT/4, MainClient.WIDTH/2, MainClient.HEIGHT/2);		
		
		//draw BATTLE!
		
		Texture tempBattleTexture = new Texture(Gdx.files.internal("battle_header.jpg"));
		sb.draw(tempBattleTexture, -50, 150);
		
		//draw player sprites
		
		//this player
		sb.draw(TextureSingleton.getInstance().playerSprites.get(TextureSingleton.COP).get(TextureSingleton.STAND), -MainClient.HEIGHT/2+50, 0, Player.WIDTH, Player.HEIGHT);
		//other player
		sb.draw(TextureSingleton.getInstance().playerSprites.get(TextureSingleton.COP).get(TextureSingleton.STAND), MainClient.HEIGHT/2-125, 0, Player.WIDTH, Player.HEIGHT);

		//draw health amounts
		Texture tempFullHeartTexture = new Texture(Gdx.files.internal("full_heart.jpg"));
		Texture tempHalfHeartTexture = new Texture(Gdx.files.internal("half_heart.jpg"));
		Texture tempNoHeartTexture = new Texture(Gdx.files.internal("no_heart.jpg"));
		//this player
		sb.draw(tempFullHeartTexture, -MainClient.WIDTH/4+10, 100, 16, 16);
		sb.draw(tempFullHeartTexture, -MainClient.WIDTH/4+30, 100, 16, 16);
		sb.draw(tempFullHeartTexture, -MainClient.WIDTH/4+50, 100, 16, 16);
		//other player
		sb.draw(tempFullHeartTexture, MainClient.WIDTH/4-45, 100, 16, 16);
		sb.draw(tempFullHeartTexture, MainClient.WIDTH/4-65, 100, 16, 16);
		sb.draw(tempFullHeartTexture, MainClient.WIDTH/4-85, 100, 16, 16);

		//draw space for element slots
		sb.draw(new Texture(Gdx.files.internal("grey.jpg")), -MainClient.WIDTH/4+150, -MainClient.HEIGHT/4+10, MainClient.WIDTH/4, MainClient.HEIGHT/4-50);
		
		//draw element slots
		sb.draw(TextureSingleton.getInstance().imagination, -MainClient.WIDTH/4+180, -80);
		sb.draw(TextureSingleton.getInstance().imagination, -MainClient.WIDTH/4+180, -120);
		sb.draw(TextureSingleton.getInstance().imagination, -MainClient.WIDTH/4+180, -160);
		sb.draw(TextureSingleton.getInstance().imagination, -MainClient.WIDTH/4+340, -80);
		sb.draw(TextureSingleton.getInstance().imagination, -MainClient.WIDTH/4+340, -120);
		sb.draw(TextureSingleton.getInstance().imagination, -MainClient.WIDTH/4+340, -160);

		//draw battle element slots
		sb.draw(TextureSingleton.getInstance().imagination, -200, 0);
		sb.draw(new Texture(Gdx.files.internal("vs.jpg")), -25, 0, 30, 30);
		sb.draw(TextureSingleton.getInstance().imagination, 100, 0);
	}

	public void assignTextures()
	{
		//assign player sprites
		
		//assign health amounts
				
		//assign element textures
		
		//assign battle element slots
	}
}
