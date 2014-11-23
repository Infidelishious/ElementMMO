package com.imglow.ElementMMO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Battle implements Drawable
{
	
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
		sb.draw(TextureSingleton.getInstance().playerSprites.get(TextureSingleton.COP).get(TextureSingleton.STAND), -MainClient.HEIGHT/2+50, 0, Player.WIDTH*2, Player.HEIGHT*2);
		//other player
		sb.draw(TextureSingleton.getInstance().playerSprites.get(TextureSingleton.COP).get(TextureSingleton.STAND), MainClient.HEIGHT/2-125, 0, Player.WIDTH*2, Player.HEIGHT*2);

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

		
		//draw element slots
		
		
		//draw battle element slots
		
		//draw run button
	}
}
