package com.imglow.ElementMMO;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Cell implements Drawable {
	
	public static int GRASS = 0, LENGTH = 64;
	int x, y, type;
	TextureRegion spr;
	
	public Cell(int x, int y, int type)
	{
		this.x = x;
		this.y = y;
		this.type = type;
		if(type == GRASS)
		{
			this.spr = TextureSingleton.getInstance().grassArray.get((int)(TextureSingleton.getInstance().grassArray.size() * Math.random()));
		}
	}

	@Override
	public void draw(SpriteBatch sb) {
		sb.draw(spr, (x - Game.getInstance().dX) * Cell.LENGTH - 0.5f * Cell.LENGTH, (y - Game.getInstance().dY) * Cell.LENGTH - 0.5f * Cell.LENGTH, LENGTH, LENGTH); 
	}

}
