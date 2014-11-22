package com.imglow.ElementMMO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Login extends Stage implements Drawable{

	private static Login instance;
	private Table table;
	private boolean visable = false;
	private MainClient client;
	
	private Login(){
		Actor act = new Actor();
		this.addActor(act);
		table = new Table();
	}
	
	public void init(MainClient client) {
		this.client = client;
	}
	
	public static Login getInstance() {
		if(instance == null) {
			instance = new Login();
		}
		return instance;
	}
	
	@Override
	public void draw(SpriteBatch sb) {
		if(visable)
		{
			act(Gdx.graphics.getDeltaTime());
			super.draw();
		}
		
	}

	public void showLogin() {
		visable = true;
		Gdx.input.setInputProcessor(this);
	}
	
	public void closeLogin() {
		visable = false;
	}
	
	public boolean getVisable() {
		return visable;
	}
	
}
