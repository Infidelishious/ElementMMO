package com.imglow.ElementMMO;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Game implements Drawable{
	public static int WIDTH = 40, HEIGHT = 80;
	
	float dX = 1, dY = 1;
	Cell[][] grid;
	Cell[][] border;
	MainClient client;
	Timer timer;
	TimerTask timerTask;
	
	public CurrentPlayer player;
	public Vector<OtherPlayer> otherPlayers;
	public ChatArea chat;
	private static Game instance;
	private Battle bg;
	public Store store;
	public HUD hud;
	public StatusUpdater statusUpdater;

	protected Game(){
		
	}
	
	public void init(final MainClient client) {
		this.client = client;
		grid = new Cell[WIDTH][HEIGHT];
		
		timer = new Timer();
		
		timerTask = new TimerTask(){
			@Override
			public void run() {
				statusUpdater.interrupt();
				client.sql.disconnected();
				Gdx.app.exit();
			}};
		
		// make the stores
		for(int x = (WIDTH / 10)*4; x < (WIDTH / 10)*6; x++)
		{
			for(int y = (HEIGHT/10); y < (HEIGHT/10)*2; y++)
			{
				grid[x][y] = new Cell(x,y,Cell.STORE);
			}
		}
		
		for(int x = (WIDTH/10)*4; x < WIDTH/10*6; x++)
		{
			for(int y = HEIGHT/10*8; y < (HEIGHT/10)*9; y++)
			{
				grid[x][y] = new Cell(x,y,Cell.STORE);
			}
		}
		for(int y = 0; y < HEIGHT; y++)
		{
			// create the left and right trees
			if(grid[0][y] == null)
				grid[0][y] = new Cell(0,y,Cell.TREE);
			if(grid[WIDTH-1][y] == null)
				grid[WIDTH-1][y] = new Cell(WIDTH-1,y,Cell.TREE);
		}
		for(int x = 1; x < WIDTH-1; x++)
		{
			// create the top and bottom trees
			if(grid[x][0] == null)
				grid[x][0] = new Cell(x,0,Cell.TREE);
			if(grid[x][HEIGHT-1] == null)
				grid[x][HEIGHT-1] = new Cell(x, HEIGHT-1, Cell.TREE);
			
		}
		
		for(int x = 1; x < WIDTH - 1; x++)
		{
			if(x < WIDTH/4 || x > 3*WIDTH/4)
			{
				if(grid[x][HEIGHT/4] == null)
					grid[x][HEIGHT/4] = new Cell(x,HEIGHT/4,Cell.BUSH);
				if(grid[x][3*HEIGHT/4] == null)
					grid[x][3*HEIGHT/4] = new Cell(x, 3*HEIGHT/4,Cell.BUSH);
			}
			else
			{
				if(grid[x][HEIGHT/4] == null)
					grid[x][HEIGHT/4] = new Cell(x,HEIGHT/4,Cell.GRASS);
				if(grid[x][3*HEIGHT/4] == null)
					grid[x][3*HEIGHT/4] = new Cell(x, 3*HEIGHT/4,Cell.GRASS);
			}
		}
		
		// fill in the rest with grass
		for(int y = 1; y < HEIGHT - 1; y++)
		{
			for(int x = 1; x < WIDTH - 1; x++)
			{
				if(grid[x][y] == null)
				{
					grid[x][y] = new Cell(x,y,Cell.GRASS);
				}
			}
		}
		
		player = new CurrentPlayer(3, "Diglit", true);
		
		// otherPlayers = new Vector<OtherPlayer>();
		hud = new HUD();
		// OtherPlayer temp = new OtherPlayer();
		// bg = new Battle(player,temp);
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
		
		// bg.draw(sb);
		
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
		
		/*
		for(OtherPlayer i : otherPlayers)
		{
			i.draw(sb);
		}
		*/
		if(player != null)
			player.draw(sb);
		
		if(chat != null)
			chat.draw(sb);
		
		if(store != null)
			store.draw(sb);
		
		if(hud != null)
			hud.draw(sb);
			
			
	}

	private class StatusUpdater extends Thread
	{
		Game game;
		
		public StatusUpdater(Game game)
		{	
			super(new Runnable(){

				@Override
				public void run() {
					while (true)
					{
						
						synchronized(this)
						{
							try {
								wait(10);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
						Message msg;
						
						if(MessageManager.getInstance().hasStatusMessage())
						{
							timer.cancel();
							timer = new Timer();
							timer.schedule(timerTask, 1000);
							
							StatusMessage ms = MessageManager.getInstance().getLastStatusMessage();
							
							otherPlayers.removeAllElements();
							
							for(MovmentMessage i : ms.playerPosition)
							{
								if(i.from.equals(player.name)) continue;
								
								OtherPlayer op = new OtherPlayer();
								op.team1 = i.team1;
								op.type = i.type;
								op.x = i.x;
								op.y = i.y;
								op.moving = i.moving;
								op.moveDirection = i.direction;
								
								otherPlayers.add(op);
							}
						}

					}
					
				}
				
			});
		}
		
	}
}
