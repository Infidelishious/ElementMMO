package com.imglow.ElementMMO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


// don't instantiate this class you don't need to
public class BattleLogics
{
	public static int IMAGINATION = 0;
	public static int GIGGLES = 1;
	public static int SEGFAULTS = 2;
	public static int SUNSHINE = 3;
	public static int HUNGER = 4;
	public static int MUSIC = 5;
	public static int DROUGHT = 6;
	public static int ENTROPY = 7;
	public static int TRIGONOMETRY = 8;
	public static int CHOCOLATE = 9;
	public static int SYNERGY = 10;
	public static int HUGS = 11;
	
	private BattleLogics(){}
	// returns 0 for no interaction
	// returns 1 for victory
	// returns -1 for loss
	static int battle(int first, int second)
	{
		if(first == IMAGINATION)
		{
			if(second == SEGFAULTS || second == TRIGONOMETRY || second == HUNGER)
			{
				return 1;
			}
			else if(second == SYNERGY || second == HUGS || second == GIGGLES || second == ENTROPY)
			{
				return -1;
			}
		}
		else if(first == GIGGLES)
		{
			if(second == MUSIC || second == ENTROPY || second == IMAGINATION)
			{
				return 1;
			}
			else if(second == HUGS || second == CHOCOLATE || second == SEGFAULTS)
			{
				return -1;
			}
		}
		else if(first == SEGFAULTS)
		{
			if(second == SUNSHINE || second == GIGGLES)
			{
				return 1;
			}
			else if(second == TRIGONOMETRY || second == MUSIC || second == IMAGINATION)
			{
				return -1;
			}
		}
		else if(first == SUNSHINE)
		{
			if(second == CHOCOLATE || second == DROUGHT)
			{
				return 1;
			}
			else if(second == IMAGINATION || second == SEGFAULTS || second == TRIGONOMETRY)
			{
				return -1;
			}
		}
		else if(first == HUNGER)
		{
			if(second == TRIGONOMETRY || second == HUGS)
			{
				return 1;
			}
			else if(second == DROUGHT || second == IMAGINATION || second == CHOCOLATE)
			{
				return -1;
			}
		}
		else if(first == MUSIC)
		{
			if(second == SEGFAULTS || second == TRIGONOMETRY || second == ENTROPY)
			{
				return 1;
			}
			else if(second == SYNERGY || second == HUGS || second == GIGGLES)
			{
				return -1;
			}
		}
		else if(first == DROUGHT)
		{
			if(second == SYNERGY || second == HUGS || second == HUNGER)
			{
				return 1;
			}
			else if (second == SUNSHINE || second == CHOCOLATE)
			{
				return -1;
			}
		}
		else if(first == ENTROPY)
		{
			if(second == CHOCOLATE || second == HUGS || second == IMAGINATION)
			{
				return 1;
			}
			else if(second == TRIGONOMETRY || second == GIGGLES || second == MUSIC)
			{
				return -1;
			}
		}
		else if(first == TRIGONOMETRY)
		{
			if(second == SUNSHINE || second == SEGFAULTS || second == ENTROPY)
			{
				return 1;
			}
			else if(second == MUSIC || second == HUNGER)
			{
				return -1;
			}
		}
		else if(first == CHOCOLATE)
		{
			if(second == HUNGER || second == DROUGHT || second == GIGGLES)
			{
				return 1;
			}
			else if(second == SUNSHINE || second == ENTROPY)
			{
				return -1;
			}
		}
		else if(first == SYNERGY)
		{
			if(second == IMAGINATION || second == MUSIC)
			{
				return 1;
			}
			else if(second == DROUGHT)
			{
				return -1;
			}
		}
		else if(first == HUGS)
		{
			if(second == GIGGLES || second == MUSIC || second == IMAGINATION)
			{
				return 1;
			}
			else if(second == HUNGER || second == ENTROPY || second == DROUGHT)
			{
				return -1;
			}
		}
		return 0;
	}
}
