package com.imglow.ElementMMO;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CurrentPlayer extends Player{

	int money, moveDirection = NOT_MOVING;
	
	int currX, currY, usingCount;
	boolean moving = false;
	
	float dist = 0;
	
	ArrayList<Boolean> owned;
	ArrayList<Boolean> using;
	
	public CurrentPlayer()
	{
		owned = new ArrayList<Boolean>();
		using = new ArrayList<Boolean>();
		money = 1000;
		for(int i = 0; i < 12; i++)
		{
			owned.add(false);
			using.add(false);
		}
		owned.set(0,true);
		using.set(0,true);
		owned.set(1,true);
		using.set(1,true);
		owned.set(2,true);
		using.set(2,true);
	}
	
	public CurrentPlayer(int type, String name)
	{
		this();
		this.type = type;
		this.name = name;
	}
	
	public void move(int direction){
		if(moving) return;
		
		moving = true;
		System.out.println("move");
		moveDirection = direction;
		dist = SPEED;
		currX = (int) x;
		currY = (int) y;
		moveNudge();
	}
	
	private void moveNudge() {
		dist += SPEED;
		
		if(moveDirection == UP)
			y = dist + currY;
		if(moveDirection == DOWN)
			y = currY - dist;
		if(moveDirection == RIGHT)
			x = currX + dist;
		if(moveDirection == LEFT)
			x = currX - dist;
	}

	@Override
	public void draw(SpriteBatch sb) {
		updateSprite();
		
		if(moving)
		{
			moveNudge();
			if(dist >= 1.0f)
			{
				moving = false;
				dist = 0;
				y = currY;
				x = currX;
				
				if(moveDirection == UP)
					y++;
				if(moveDirection == DOWN)
					y--;
				if(moveDirection == RIGHT)
					x++;
				if(moveDirection == LEFT)
					x--;
			}
		}
			
		Game.getInstance().dX = x;
		Game.getInstance().dY = y;
		
		if(team1)
			sb.setColor(0.5f,0.5f,1f,1f);
		else
			sb.setColor(1f,0.5f,0.5f,1f);
		
		sb.draw(spr, - WIDTH / 2.0f, - HEIGHT / 2.0f, WIDTH, HEIGHT);
		
		sb.setColor(Color.WHITE);
		
		sendPosToServer();
	}

	private void sendPosToServer() {
		MovmentMessage mm = new MovmentMessage();
		mm.direction = moveDirection;
		mm.x = x;
		mm.y = y;
		mm.moving = moving;
		mm.from = name;
		MessageManager.getInstance().sendMessageToServer(mm);
		
	}
}
