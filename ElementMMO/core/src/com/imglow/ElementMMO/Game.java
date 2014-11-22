package com.imglow.ElementMMO;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game implements Drawable{
	
	Cell[][] grid;
	private MainClient client;
	
	private static Game instance;

	protected Game(){}
	
	public void init(MainClient client) {
		this.client = client;
		
		//make map and attempt to log in
	}
	
	public static Game getInstance() {
		if(instance == null) {
			instance = new Game();
		}
		return instance;
	}

	@Override
	public void draw(SpriteBatch sb) {
		//Draw Cells
		//Draw Players
		//Draw Battle
		
	}
	
	

}
