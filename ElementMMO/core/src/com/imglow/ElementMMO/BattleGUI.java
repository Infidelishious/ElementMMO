package com.imglow.ElementMMO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BattleGUI implements Drawable
{
	SpriteBatch sb;
	
	public BattleGUI(SpriteBatch sb)
	{
		this.sb = sb;
	}
	
	@Override
	public void draw(SpriteBatch sb) 
	{
		FileHandle f = Gdx.files.internal("/images/tree1.png");
		Texture t = new Texture(f);
		sb.draw(t, 10, 10);
	}
}
