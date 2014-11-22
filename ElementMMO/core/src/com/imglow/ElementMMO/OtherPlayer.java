package com.imglow.ElementMMO;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class OtherPlayer extends Player{

	@Override
	public void draw(SpriteBatch sb) {
		this.updateSprite();
		
		sb.draw(spr, (x - Game.getInstance().dX) * Cell.LENGTH, (y - Game.getInstance().dY) * Cell.LENGTH, WIDTH, HEIGHT); 	
	}

}
