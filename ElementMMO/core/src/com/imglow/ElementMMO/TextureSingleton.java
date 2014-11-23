package com.imglow.ElementMMO;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.sun.media.jfxmedia.events.PlayerStateEvent.PlayerState;


public class TextureSingleton {
	private static TextureSingleton instance;
	//////////////IF YOU TOUCH THIS CODE I WILL PEE ON YOUR MOM////////////////
	public static int COP = 0, SPIKEY = 1, CHIKA = 2, NAKED_MAN = 3;
	public static int STAND = 0, FACE_UP = 1, FACE_LEFT = 2, WALK_DOWN = 3, WALK_UP = 4, WALK_LEFT_1 = 5,
						WALK_LEFT_2 = 6, WALK_RIGHT_1 = 7, WALK_RIGHT_2 = 8, FACE_RIGHT = 9;
	
			//playersprites.get(COP).get(UP);
	public ArrayList<ArrayList<TextureRegion>> playerSprites;
	
	public Texture white, gray;
	public BitmapFont scoreFont, nameFont;
	public Texture cop, spikey, chika, nakedMan;
	public ArrayList<TextureRegion> copList, spikeyList, chikaList, nakedManList, shopElements, elements;
	public TextureRegion grass, bush, tree1, tree2, imagination, hunger, music, giggles, segFaults, sunshine, drought,
							entropy, trig, choco, synergy, hugs;
	
	public Music mainMusic;
	public Music battleMusic;
	
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
		gray = new Texture(Gdx.files.internal("images/gray.png"));
		playerSprites = new ArrayList<ArrayList<TextureRegion>>();
		scoreFont = new BitmapFont(Gdx.files.internal("fonts/Fipps-Regular.fnt"), Gdx.files.internal("fonts/Fipps-Regular_0.tga"), false);
		nameFont = new BitmapFont(Gdx.files.internal("fonts/FlxRegular.fnt"), Gdx.files.internal("fonts/FlxRegular_0.tga"), false);
		
		//This one is for the player.. #PS4 reference
		cop = new Texture(Gdx.files.internal("images/cop.png"));
		spikey = new Texture(Gdx.files.internal("images/Spikey.png"));
		chika = new Texture(Gdx.files.internal("images/chika.png"));
		nakedMan = new Texture(Gdx.files.internal("images/naked_man.png"));
		nakedManList = new ArrayList<TextureRegion>();	
		copList = new ArrayList<TextureRegion>();
		spikeyList = new ArrayList<TextureRegion>();
		chikaList = new ArrayList<TextureRegion>();
		
		playerSprites.add(copList);
		playerSprites.add(spikeyList);
		playerSprites.add(chikaList);	
		playerSprites.add(nakedManList);
		
		Texture temp = new Texture(Gdx.files.internal("images/grass.png"));		//using to initialized all the green stuff
		grass = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/bush.png"));
		bush = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/tree1.png"));
		tree1 = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/tree2.png"));
		tree2 = new TextureRegion(temp);
		for(int i = 0; i < 10; i++) {
			copList.add(new TextureRegion(cop, i * 16, 0, 16, 21));
		}
		for(int i = 0; i < 10; i++) {
			spikeyList.add(new TextureRegion(spikey, i * 16, 0, 16, 21));
		}
		for(int i = 0; i < 10; i++) {
			chikaList.add(new TextureRegion(chika, i * 16, 0, 16, 21));
		}
		for(int i = 0; i < 10; i++) {
			nakedManList.add(new TextureRegion(nakedMan, i * 16, 0, 16, 21));
		}
		
		////Background Music shit///
		mainMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/background_music.mp3"));
		mainMusic.setLooping(true);
		mainMusic.play();
		
		////Battle music shit////
		battleMusic = Gdx.audio.newMusic(Gdx.files.internal("sound/battle.mp3"));
		
		// Adding elements to shop ArrayList//
		temp = new Texture(Gdx.files.internal("images/Shop/Imagination.png"));
		imagination = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/Shop/Giggles.png"));
		giggles = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/Shop/SegFaults.png"));
		segFaults = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/Shop/Sunshine.png"));
		sunshine = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/Shop/Music.png"));
		music = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/Shop/Hunger.png"));
		hunger = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/Shop/Drought.png"));
		drought = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/Shop/Entropy.png"));
		entropy = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/Shop/Trigonometry.png"));
		trig = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/Shop/Chocolate.png"));
		choco = new TextureRegion(temp);
		
		shopElements = new ArrayList<TextureRegion>();
		shopElements.add(imagination);
		shopElements.add(giggles);
		shopElements.add(segFaults);
		shopElements.add(sunshine);
		shopElements.add(music);
		shopElements.add(hunger);
		shopElements.add(drought);
		shopElements.add(entropy);
		shopElements.add(trig);
		shopElements.add(choco);
		
		//Adding elements in main Arraylist//
		temp =  new Texture(Gdx.files.internal("images/Elements/Imagination.png"));
		imagination = new TextureRegion(temp);
		temp =  new Texture(Gdx.files.internal("images/Elements/giggles.png"));
		giggles = new TextureRegion(temp);
		temp =  new Texture(Gdx.files.internal("images/Elements/SegFaults.png"));
		segFaults = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/Elements/Hunger.png"));
		hunger = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/Elements/Music.png"));
		music = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/Elements/Sunshine.png"));
		sunshine = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/Elements/Drought.png"));
		drought = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/Elements/Entropy.png"));
		entropy = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/Elements/Trigonometry.png"));
		trig = new TextureRegion(temp);
		temp = new Texture(Gdx.files.internal("images/Elements/Chocolate.png"));
		choco = new TextureRegion(temp);
		
		
		elements = new ArrayList<TextureRegion>();
		elements.add(imagination);
		elements.add(giggles);
		elements.add(segFaults);
		elements.add(hunger);
		elements.add(sunshine);
		elements.add(drought);
		elements.add(entropy);
		elements.add(music);
		elements.add(trig);
		elements.add(choco);
	}
	
	public void EnterBattle() {			//Changing music when entering the battle.
		mainMusic.pause();
		battleMusic.setLooping(true);
		battleMusic.play();
	}
	
	public void ExitBattle() {			//Changing music when exiting the battle.
		battleMusic.pause();
		mainMusic.play();
	}
}
