package com.imglow.ElementMMO;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CurrentPlayer extends Player{

	int money, moveDirection = NOT_MOVING;
	
	int currX, currY;
	boolean moving;
	
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
	
	public void move(int direction){
		if(moving) return;
		
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
		
		sb.draw(spr, Game.WIDTH - WIDTH / 2.0f, Game.HEIGHT - HEIGHT / 2.0f, WIDTH, HEIGHT);
	}
}
