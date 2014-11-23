package com.imglow.ElementMMO;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import java.util.*;

public class Store implements Drawable, ClickListener
{
	static int buttonWidth = 80;
	static int buttonHeight = 80;
	static int storeWidth = buttonWidth*3;
	static int storeHeight = buttonHeight*5;
	
	private static TextureSingleton textures;
	private static ClickController clicker;
	private static Game game;
	// the player that knows his money and abilities
	CurrentPlayer currentPlayer;
	
	// this arraylist stores if it is selected or not
	private ArrayList <Boolean> selected;
	
	// these arraylists store all of the images
	// that we pulled from the texturesingleton
	
	private ArrayList <TextureRegion> storeUnselectedImages;
	
	// these ones are grayscale
	private ArrayList <TextureRegion> storeSelectedImages;
	
	public Store()
	{
		textures = TextureSingleton.getInstance();
		clicker = ClickController.getInstance();
		
		game = Game.getInstance();
		
		currentPlayer = game.player;
		// initialize all the selected to false
		selected = currentPlayer.owned;
		
		// read in the store selected and unselected images
		
		// the first three types are automatically
		// selected
		// add each
		// replace white with the appropriate picture
		storeSelectedImages.add(new TextureRegion(TextureSingleton.getInstance().white,0,0,60,20) );
		
		
	}
	
	
	@Override
	public void draw(SpriteBatch sb)
	{
		
		// there is also a banner on top that has width 80*3, height 144
		// sb.draw(imagetodraw, xcoordinate, coordinate, width, height)
		for(int i = 0; i < selected.size(); i++)
		{
			//
		}
	}

	@Override
	public void onClick(Vector3 clickPos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isInside(Vector3 clickPos) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getDepth() {
		return 0;
	}
	
}