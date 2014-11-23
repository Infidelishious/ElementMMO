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
		System.out.println("file loaded");
		FileHandle f = Gdx.files.internal("images/tree1.png");
		Texture t = new Texture(f);
		sb.draw(t, 0, 0);
	}
}
