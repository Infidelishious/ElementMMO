package com.imglow.ElementMMO;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sun.media.jfxmedia.events.PlayerStateEvent.PlayerState;


public class TextureSingleton {
	private static TextureSingleton instance;
	//////////////IF YOU TOUCH THIS CODE I WILL PEE ON YOUR MOM////////////////
	public static int COP = 0, SPIKEY = 1, CHIKA = 2, NAKED_MAN = 3;
	public static int STAND = 0, FACE_UP = 1, FACE_LEFT = 2, WALK_DOWN = 3, WALK_UP = 4, WALK_LEFT_1 = 5,
						WALK_LEFT_2 = 6, WALK_RIGHT_1 = 7, WALK_RIGHT_2 = 8, FACE_RIGHT = 9;
	
			//playersprites.get(COP).get(UP);
	public ArrayList<ArrayList<TextureRegion>> playerSprites;
	
	public Texture white;
	public BitmapFont scoreFont, nameFont;
	public Texture cop, spikey, chika, nakedMan;
	public ArrayList<TextureRegion> copList, spikeyList, chikaList, nakedManList;
	public TextureRegion grass, bush, tree1, tree2;
	
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
		playerSprites = new ArrayList<ArrayList<TextureRegion>>();
		scoreFont = new BitmapFont(Gdx.files.internal("fonts/Fipps-Regular.fnt"), Gdx.files.internal("fonts/Fipps-Regular_0.tga"), false);
		nameFont = new BitmapFont(Gdx.files.internal("fonts/FlxRegular.fnt"), Gdx.files.internal("fonts/FlxRegular_0.tga"), false);
		cop = new Texture(Gdx.files.internal("images/cop.png"));
		spikey = new Texture(Gdx.files.internal("images/Spikey.png"));
		chika = new Texture(Gdx.files.internal("images/chika.png"));
		nakedMan = new Texture(Gdx.files.internal("images/nake_man.png"));
		nakedManList = new ArrayList<TextureRegion>();
		copList = new ArrayList<TextureRegion>();
		spikeyList = new ArrayList<TextureRegion>();
		chikaList = new ArrayList<TextureRegion>();
		playerSprites.add(copList);
		playerSprites.add(spikeyList);
		playerSprites.add(chikaList);	
		Texture temp = new Texture(Gdx.files.internal("images/grass.png"));		//using to initialized all the green stuff
		grass = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/bush.png"));
		bush = new TextureRegion(bush);
		temp = new Texture(Gdx.files.internal("images/tree1.png"));
		tree1 = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/tree2.png"));
		tree2 = new TextureRegion(temp);
		for(int i = 0; i < 9; i++) {
			copList.add(new TextureRegion(cop, i * 16, 0, 16, 21));
		}
		for(int i = 0; i < 9; i++) {
			spikeyList.add(new TextureRegion(spikey, i * 16, 0, 16, 21));
		}
		for(int i = 0; i < 9; i++) {
			chikaList.add(new TextureRegion(chika, i * 16, 0, 16, 21));
		}
		for(int i = 0; i < 9; i++) {
			chikaList.add(new TextureRegion(chika, i * 16, 0, 16, 21));
		}
		for(int i = 0; i < 9; i++) {
			nakedManList.add(new TextureRegion(nakedMan, i * 16, 0, 16, 21));
		}
	}
}
