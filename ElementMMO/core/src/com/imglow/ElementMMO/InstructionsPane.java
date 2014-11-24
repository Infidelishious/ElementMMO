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
		Button closeThisIsh = new Button(TextureSingleton.getInstance());
		sb.draw(TextureSingleton.getInstance().whiteRegion, -200, 0, 400, 200);
		INSTRUCTION_FONT.draw(sb, "Welcome to our game!", -190, 190);
		INSTRUCTION_FONT.draw(sb, "Here's how to play:", -190, 170);
		INSTRUCTION_FONT.draw(sb, "1. Run around and explore the world!", -190, 150);
		INSTRUCTION_FONT.draw(sb, "2. Run around and explore the world!", -190, 150);
	}
	
	public void close()
	{
		Game.getInstance().instructions = null;
	}
}
