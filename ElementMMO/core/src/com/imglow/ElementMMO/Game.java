package com.imglow.ElementMMO;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game implements Drawable{
	public static int WIDTH = 100, HEIGHT = 200;
	
	float dX = 0, dY = 0;
	Cell[][] grid;
	private MainClient client;
	
	private static Game instance;

	protected Game(){}
	
	public void init(MainClient client) {
		this.client = client;
		grid = new Cell[WIDTH][HEIGHT];
		
		for(int y = 0; y < HEIGHT; y++)
		{
			for(int x = 0; x < WIDTH; x++)
			{
				grid[x][y] = new Cell(x,y,Cell.GRASS);
			}
		}
	}
	
	public static Game getInstance() {
		if(instance == null) {
			instance = new Game();
		}
		return instance;
	}

	@Override
	public void draw(SpriteBatch sb) {
		for(int y = 0; y < HEIGHT; y++)
		{
			for(int x = 0; x < WIDTH; x++)
			{
				grid[x][y].draw(sb);
			}
		}
		
	}
	
	

}
