package com.imglow.ElementMMO;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class TextureSingleton {
	private static TextureSingleton instance;
	
	public Texture white;
	public BitmapFont scoreFont, nameFont;
	
	protected TextureSingleton(){}

	public static TextureSingleton getInstance() {
		if(instance == null) {
			instance = new TextureSingleton();
		}
		return instance;
	}
	
	public void loadTextures()
	{
		//This is where textures are loaded
		white = new Texture(Gdx.files.internal("images/white.png"));
		
		scoreFont = new BitmapFont(Gdx.files.internal("fonts/Fipps-Regular.fnt"), Gdx.files.internal("fonts/Fipps-Regular_0.tga"), false);
		nameFont = new BitmapFont(Gdx.files.internal("fonts/FlxRegular.fnt"), Gdx.files.internal("fonts/FlxRegular_0.tga"), false);
	}
}
