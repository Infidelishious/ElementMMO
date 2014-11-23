package com.imglow.ElementMMO;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

import java.util.*;

public class Store implements Drawable, ClickListener
{
	// the coordinates of where the topleft of the thing is
	float x, y;
	static float buttonWidth = 80;
	static float buttonHeight = 80;
	static float storeWidth = buttonWidth*3;
	static float storeHeight = buttonHeight*5;
	
	static int[] moneyValues = {0,0,0,100,100,300,300,300,500,500,500,1000};
	
	private static TextureSingleton textures;
	private static ClickController clicker;
	private static Game game;
	// the player that knows his money and abilities
	CurrentPlayer currentPlayer;
	Sound negatory;
	
	// this arraylist stores if it is selected or not
	private ArrayList <Boolean> owned;
	int ownedCount;
	private ArrayList <Boolean> using;
	int usingCount;
	
	// these arraylists store all of the images
	// that we pulled from the texturesingleton
	
	// these ones are grayscale
	private ArrayList <TextureRegion> abilities;
	
	private TextureRegion usingHighlight;
	private TextureRegion notOwnedHighlight;
	public Store(float x, float y)
	{
		// set up the coordinates
		this.x = x;
		this.y = y;
		
		
		// set up the data
		textures = TextureSingleton.getInstance();
		clicker = ClickController.getInstance();
		game = Game.getInstance();
		
		currentPlayer = game.player;
		// initialize all the selected to false
		owned = currentPlayer.owned;
		using = currentPlayer.using;
		usingCount = 3;
		updateUsingCount();
		
		// get the arraylist from textureRegion for the appropriate things
		abilities = textures.shopElements;
		
		// get the negation sound
		// negatory = new Sound(TextureSingleton.getInstance().negatory);
	}
	
	
	@Override
	public void draw(SpriteBatch sb)
	{
		
		// there is also a banner on top that has width 80*3, height 144
		// sb.draw(imagetodraw, xcoordinate, coordinate, width, height)
		// draw the banner
		
		
		// draw each button
		for(int row = 1; row < 5; row++)
		{
			for(int col = 0; col < 3; col++)
			{
				if(row*3 + col < abilities.size())
				{
					sb.draw(abilities.get(row*3 + col),
							(x - Game.getInstance().dX) + buttonWidth* col,
							(y - Game.getInstance().dY) + buttonHeight*row,
							buttonWidth,
							buttonHeight); 
				}
			}
		}
	}
	
	public void updateUsingCount()
	{
		usingCount = 0; 
		for(int i = 0; i < using.size(); i++)
		{
			if(using.get(i))
				usingCount++;
		}
	}
	public void calculateClick(int buttonClicked)
	{
		if(owned.get(buttonClicked))
		{
			if(using.get(buttonClicked))
			{
				// if there is only 1 using left
				// then we can't get rid of it
				if(usingCount > 1)
				{
					// get rid of it
					using.set(buttonClicked,false);
				}
			}
			else
			{
				// can't add to using
				// if there are already 7 in using
				if(usingCount < 7)
				{
					using.set(buttonClicked,true);
				}
				else
					negatory.play();
			}
		}
		else // !owned.get(buttonClicked)
		{
			// if you don't have enuf moneys
			if(currentPlayer.money < moneyValues[buttonClicked])
			{
				negatory.play();
			}
			else // if you have enuf moneys
			{
				owned.set(buttonClicked,true);
				if(usingCount < 7)
				{
					using.set(buttonClicked, true);
				}
				currentPlayer.money -= moneyValues[buttonClicked];
			}
		}
		// update the values
		// based upon what transpired
		updateUsingCount();
		currentPlayer.using = using;
		currentPlayer.owned = owned;
	}
	@Override
	public void onClick(Vector3 clickPos) {
		
		float xclick = clickPos.x;
		float yclick = clickPos.y;
		if(yclick < y)
		{
			// ignore
		}
		else if(yclick < y + buttonHeight)
		{
			// top banner
		}
		else if(yclick < y + buttonHeight*2)
		{
			// first row of buttons
			if(xclick < x)
			{
				// ignore
			}
			else if(xclick < x + buttonWidth)
			{
				// button 1
				calculateClick(0);
				
				
			}
			else if(xclick < x + buttonWidth*2)
			{
				// button 2
				calculateClick(1);
			}
			else if(xclick < x + buttonWidth*3)
			{
				// button 3
				calculateClick(2);
			}
		}
		else if(yclick < y + buttonHeight*3)
		{
			// second row of buttons
			if(xclick < x)
			{
				// ignore
				
			}
			else if(xclick < x + buttonWidth)
			{
				// button 4
				calculateClick(3);
			}
			else if(xclick < x + buttonWidth*2)
			{
				// button 5
				calculateClick(4);
			}
			else if(xclick < x + buttonWidth*3)
			{
				// button 6
				calculateClick(5);
			}
		}
		else if(yclick < y + buttonHeight*4)
		{
			// third row of buttons
			if(xclick < x)
			{
				// ignore
			}
			else if(xclick < x + buttonWidth)
			{
				// button 7
				calculateClick(6);
			}
			else if(xclick < x + buttonWidth*2)
			{
				// button 8
				calculateClick(7);
			}
			else if(xclick < x + buttonWidth*3)
			{
				// button 9
				calculateClick(8);
			}
		}
		else if(yclick < y + buttonHeight*5)
		{
			// last row of buttons
			if(xclick < x)
			{
				// ignore
			}
			else if(xclick < x + buttonWidth)
			{
				// button 10
				calculateClick(9);
			}
			else if(xclick < x + buttonWidth*2)
			{
				// button 11
				calculateClick(10);
			}
			else if(xclick < x + buttonWidth*3)
			{
				// button 12
				calculateClick(11);
			}
		}
	}

	@Override
	public boolean isInside(Vector3 clickPos) {
		
		// click exists inside the store!!!
		
		float xclick = clickPos.x;
		float yclick = clickPos.y;
		if(xclick > x && xclick < x + storeWidth && yclick > y && yclick < y + storeHeight)
		{
			return true;
		}
		return false;
	}

	@Override
	public int getDepth() {
		return 0;
	}
	
}