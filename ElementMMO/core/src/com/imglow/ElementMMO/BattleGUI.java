package com.imglow.ElementMMO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BattleGUI implements Drawable
{
	
	public BattleGUI()
	{
	}
	
	@Override
	public void draw(SpriteBatch sb) 
	{
		sb.draw(TextureSingleton.getInstance().tree1, 0, 0);
	}
}
