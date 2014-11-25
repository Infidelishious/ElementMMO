package com.imglow.ElementMMO;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class InstructionsPane implements Drawable
{
	static final BitmapFont INSTRUCTION_FONT = TextureSingleton.getInstance().nameFont;
		
	public InstructionsPane()
	{
		
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
				if((pos.x < -250 || pos.x > 150) || (pos.y < 100 || pos.y > 300))
				{
					close();
				}
			}

		});
		
		sb.draw(TextureSingleton.getInstance().msgBox, -275, 30, 550, 220);
		sb.setColor(Color.WHITE);
		INSTRUCTION_FONT.setColor(Color.BLACK);
		INSTRUCTION_FONT.setScale(1f);
		INSTRUCTION_FONT.draw(sb, "Welcome to our game!", -260, 240);
		INSTRUCTION_FONT.draw(sb, "Here's how to play:", -260, 210);
		INSTRUCTION_FONT.draw(sb, "1. Run around and explore the world! ( WASD )", -210, 180);
		INSTRUCTION_FONT.draw(sb, "2. Find people on the other team and battle!", -210, 150);
		INSTRUCTION_FONT.draw(sb, "3. Buy battle elements at the stores!", -210, 120);
		INSTRUCTION_FONT.draw(sb, "Close this by clicking anywhere outside the window", -260, 70);
	}
	
	public void close()
	{
		Game.getInstance().instructions = null;
	}
}
