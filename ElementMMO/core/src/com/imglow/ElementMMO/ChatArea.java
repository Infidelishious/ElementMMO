package com.imglow.ElementMMO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ChatArea implements Drawable{
	protected String chatText;
	boolean first;
	
	ChatArea() {
		chatText = new String("> ");
		first = true;
	}
	
	
	@Override
	public void draw(SpriteBatch sb) {
		BitmapFont chatFont = TextureSingleton.getInstance().scoreFont;
		chatFont.setScale(2.0f);
		chatFont.draw(sb, chatText, -MainClient.WIDTH/2 + 50, -MainClient.HEIGHT/2 + 200);
		if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER) && first != true) sendMessage(chatText);
		if(Gdx.input.isKeyJustPressed(Input.Keys.BACKSPACE) && !chatText.equals("> ")) chatText = chatText.replace(chatText.substring(chatText.length() - 1), "");
		if(chatText.length() < 50) {
			first = false;
			if(Gdx.input.isKeyJustPressed(Input.Keys.A)) chatText += "A";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.B)) chatText += "B";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.C)) chatText += "C";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.D)) chatText += "D";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.E)) chatText += "E";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.F)) chatText += "F";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.G)) chatText += "G";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.H)) chatText += "H";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.I)) chatText += "I";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.J)) chatText += "J";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.K)) chatText += "K";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.L)) chatText += "L";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.M)) chatText += "M";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.N)) chatText += "N";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.O)) chatText += "O";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.P)) chatText += "P";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.Q)) chatText += "Q";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.R)) chatText += "R";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.S)) chatText += "S";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.T)) chatText += "T";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.U)) chatText += "U";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.V)) chatText += "V";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.W)) chatText += "W";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.X)) chatText += "X";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.Y)) chatText += "Y";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.Z)) chatText += "Z";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) chatText += "1";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) chatText += "2";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_3)) chatText += "3";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_4)) chatText += "4";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_5)) chatText += "5";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_6)) chatText += "6";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_7)) chatText += "7";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_8)) chatText += "8";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_9)) chatText += "9";
			else if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_0)) chatText += "0";
		}
	}


	private void sendMessage(String chatText2) {
		Game.getInstance().chat = null;
		
	} 

}
