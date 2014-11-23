package com.imglow.ElementMMO;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game implements Drawable{
	public static int WIDTH = 100, HEIGHT = 200;
	
	float dX = 0, dY = 0;
	Cell[][] grid;
	MainClient client;
	
	public CurrentPlayer player;
	
	public ArrayList<OtherPlayer> otherPlayers;
	
	private static Game instance;
	
	private Battle bg;

	protected Game(){}
	
	public void init(MainClient client) {
		this.client = client;
		grid = new Cell[WIDTH][HEIGHT];
//		bg = new Battle();
		
		for(int y = 0; y < HEIGHT; y++)
		{
			for(int x = 0; x < WIDTH; x++)
			{
				grid[x][y] = new Cell(x,y,Cell.GRASS);
			}
		}
		
		player = new CurrentPlayer();
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
		
		//if battle draw battle
		
		//bg.draw(sb);
		
		if(Gdx.input.isKeyPressed(Input.Keys.W))
			player.move(Player.UP);
		if(Gdx.input.isKeyPressed(Input.Keys.S))
			player.move(Player.DOWN);
		if(Gdx.input.isKeyPressed(Input.Keys.D))
			player.move(Player.RIGHT);
		if(Gdx.input.isKeyPressed(Input.Keys.A))
			player.move(Player.LEFT);
			
		player.draw(sb);
	}
}
