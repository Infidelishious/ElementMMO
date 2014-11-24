package com.imglow.ElementMMO;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

public class HUD implements Drawable {
	
	// VARS START ----------------------------
	
	// position & dimension data
	static final float w = 1280, h = 100;
	
	// references
	private static TextureSingleton textures;
	private static Game game;
	
	// visual data
	int team1Score, team2Score;
	int money;
	BitmapFont HUDFont;
	
	// VARS END ------------------------------
	
	
	
	public HUD ()
	{
		// initialize singleton references
		textures = TextureSingleton.getInstance();
		game = Game.getInstance();
		HUDFont = TextureSingleton.getInstance().scoreFont;
	}
	
	
	
	@Override
	public void draw (SpriteBatch sb)
	{
		
	}
	
	
	
	// Update HUD
	public void updateHUD ()
	{
		
	}

}

























