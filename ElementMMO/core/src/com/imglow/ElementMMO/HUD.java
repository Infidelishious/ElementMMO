package com.imglow.ElementMMO;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class HUD implements Drawable {
	
	// VARS START ----------------------------
	
	// position & dimension data
	static final float screenW = 1280, screenH = 100;
	//static final float moneyW = 0, moneyH = 0;	// TODO - ADJUST MONEY DRAW POSITION SO UP IN TOP LEFT CORNER OF SCREEN
	
	// references
	private static TextureSingleton textures;
	private static Game game;
	CurrentPlayer currentPlayer;
	
	// visual data
	int team1Score, team2Score;
	//int money;
	BitmapFont HUDFont;
	
	// VARS END ------------------------------
	
	
	
	public HUD ()
	{
		// initialize singleton references
		textures = TextureSingleton.getInstance();
		game = Game.getInstance();
		currentPlayer = game.player;
		HUDFont = TextureSingleton.getInstance().scoreFont;
		
		// get hud values
		// TODO - SCORE IS NOT YET BEING KEPT IN GAME TO BE GRABBED HERE FOR THE HUD DISPLAY
		//money = game.player.money;
	}
	
	
	
	@Override
	public void draw (SpriteBatch sb)
	{
		// draw the money at money coordinates
		HUDFont.setColor(0.0f,0.0f,0.0f,1.0f);
		HUDFont.draw(sb , "" + currentPlayer.money , -(screenW/2) + 20, -(screenH/2) + 20);
		sb.setColor(1.0f,1.0f,1.0f,1.0f);
	}
	
	
	
	// Update HUD
	public void updateHUD ()
	{
		
	}
	
	
	
	// Update HUD scores
	public void updateHUDScores (int newScore1, int newScore2)
	{
		
	}

}

























