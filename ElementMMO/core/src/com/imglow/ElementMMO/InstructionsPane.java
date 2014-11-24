package com.imglow.ElementMMO;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InstructionsPane implements Drawable
{
	static final BitmapFont INSTRUCTION_FONT = TextureSingleton.getInstance().scoreFont;
		
	public InstructionsPane()
	{
		INSTRUCTION_FONT.setColor(Color.BLACK);
	}

	@Override
	public void draw(SpriteBatch sb) 
	{
		INSTRUCTION_FONT.draw(sb, "hello world", -150, 0);
		sb.draw(TextureSingleton.getInstance().whiteRegion, -150, 0, 200, 200);
	}
	
	public void close()
	{
		Game.getInstance().instructions = null;
	}
}
