package com.imglow.ElementMMO;

import java.net.Socket;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class MainClient extends ApplicationAdapter {
	
	public static float WIDTH = 1280, HEIGHT = 720;
	
	SpriteBatch batch;
	Texture img;
	MainClient thiss;
	OrthographicCamera camera;

	private boolean touched, click;

	private Vector3 firstTouch;
	
	//!!!!
	private BattleGUI bg;
	//!!!!
	
	//!!!!
	public MainClient(/*Socket s, int playerType, String name*/)
	//!!!!
	{
		//!!!!
//		MessageManager.getInstance().init(s);
		//!!!!
	}
	
	@Override
	public void create () {
		thiss = this; 
		ClickController.getInstance().initalize(this);
		initalizeAssets();
		
		camera = new OrthographicCamera(WIDTH, HEIGHT);
		batch = new SpriteBatch();
		//!!!!
		bg = new BattleGUI();
		//!!!!
		Game.getInstance().init(this);
	}

	private void initalizeAssets() {
		TextureSingleton.getInstance().loadTextures();
	}
	
	@Override
	public void render () { //Triggers 60x a second, were the logic goes
		touchLogic();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		Game.getInstance().draw(batch);
		batch.end();
	}
	
	private void touchLogic() {
		if(Login.getInstance().getVisable())
			return;
			
		Vector3 touchPos = null;
		//
		if(Gdx.input.isTouched()) {
			touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
		}
		
		if(Gdx.input.isTouched() && !touched)
		{
			click = true;
			firstTouch = touchPos; 
		}
		else click = false;

		touched = Gdx.input.isTouched();
		
		
		//touchCaught = clickC.clickEvent(touchPos);
		///////////////////////////////////////
		if(touchPos == null || !click || !ClickController.getInstance().clickEvent(touchPos))
		{
			//char keyboard = rightInput();
			
		}
	}
	
	
}
