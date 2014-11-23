package com.imglow.ElementMMO;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ChatArea implements Drawable{

	
	@Override
	public void draw(SpriteBatch sb) {
		BitmapFont chatFont = TextureSingleton.getInstance().scoreFont;
		String chatText = new String("");
		chatFont.setScale(2.0f);
		chatFont.draw(sb, chatText, -MainClient.WIDTH/2 + 50, -MainClient.HEIGHT/2 + 200);
	} 

}
