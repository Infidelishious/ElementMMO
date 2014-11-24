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
		INSTRUCTION_FONT.setScale(1);
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
				if((pos.x < -200 || pos.x > 200) || (pos.y < 100 || pos.y > 300))
				{
					close();
				}
			}

		});
		
		sb.draw(TextureSingleton.getInstance().whiteRegion, -250, 100, 525, 200);
		INSTRUCTION_FONT.draw(sb, "Welcome to our game!", -225, 290);
		INSTRUCTION_FONT.draw(sb, "Here's how to play:", -220, 260);
		INSTRUCTION_FONT.draw(sb, "1. Run around and explore the world! ( use WASD )", -220, 230);
		INSTRUCTION_FONT.draw(sb, "2. Find people on the other team and battle!", -220, 200);
		INSTRUCTION_FONT.draw(sb, "3. Buy battle elements at the stores!", -220, 170);
		INSTRUCTION_FONT.draw(sb, "Close this by clicking anywhere outside the window", -240, 120);
	}
	
	public void close()
	{
		Game.getInstance().instructions = null;
	}
}
