package com.imglow.ElementMMO;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Cell implements Drawable {
	
	public static int GRASS = 0, LENGTH = 32;
	int x, y, type;
	TextureRegion spr;
	
	public Cell(int x, int y, int type)
	{
		this.x = x;
		this.y = y;
		this.type = type;
		if(type == GRASS)
		{
			this.spr = new TextureRegion(TextureSingleton.getInstance().white,0,0,2,2);
		}
	}

	@Override
	public void draw(SpriteBatch sb) {
		sb.draw(spr, x * LENGTH, y * LENGTH, LENGTH, LENGTH); 
	}

}
