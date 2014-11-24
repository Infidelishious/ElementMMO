package com.imglow.ElementMMO;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

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
		Button closeThisIsh = new Button(TextureSingleton.getInstance().nothing, -MainClient.WIDTH/2, -MainClient.HEIGHT/2, MainClient.WIDTH, MainClient.HEIGHT, 
		new OnClickListener()
		{

			@Override
			public void onClick(Button source, Vector3 pos) 
			{
				if((pos.x < -200 || pos.x > 200) &&(pos.y < 0))
				{
					System.out.println("clicked.");
				}
			}

		});
		
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
