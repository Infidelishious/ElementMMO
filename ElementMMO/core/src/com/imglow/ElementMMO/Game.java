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
	public ChatArea chat;
	private static Game instance;
	
	private Battle bg;

	protected Game(){}
	
	public void init(MainClient client) {
		this.client = client;
		grid = new Cell[WIDTH][HEIGHT];
		//bg = new Battle();
		
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
		
		//if battle draw battle
		
		//bg.draw(sb);
		if(chat == null)
		{
			if(player !=null) {
				if(Gdx.input.isKeyPressed(Input.Keys.W))
					player.move(Player.UP);
				else 	if(Gdx.input.isKeyPressed(Input.Keys.S))
					player.move(Player.DOWN);
				else if(Gdx.input.isKeyPressed(Input.Keys.D))
					player.move(Player.RIGHT);
				else if(Gdx.input.isKeyPressed(Input.Keys.A))
					player.move(Player.LEFT);
			}
			if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER))
				chat = new ChatArea();
		}
		if(player != null)
			player.draw(sb);
		if(chat != null) chat.draw(sb);
	}
}
