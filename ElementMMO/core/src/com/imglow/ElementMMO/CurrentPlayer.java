package com.imglow.ElementMMO;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CurrentPlayer extends Player{

	@Override
	public void draw(SpriteBatch sb) {
		updateSprite();
		
		sb.draw(spr, Game.WIDTH - WIDTH / 2.0f, Game.HEIGHT - HEIGHT / 2.0f, WIDTH, HEIGHT);
	}
}
