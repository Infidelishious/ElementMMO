package com.imglow.ElementMMO;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CurrentPlayer extends Player{

	int money;
	
	ArrayList<Boolean> owned;
	ArrayList<Boolean> using;
	public CurrentPlayer()
	{
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
	@Override
	public void draw(SpriteBatch sb) {
		updateSprite();
		
		sb.draw(spr, Game.WIDTH - WIDTH / 2.0f, Game.HEIGHT - HEIGHT / 2.0f, WIDTH, HEIGHT);
	}
}
