package gamestate.levels;

import entity.enemy.Enemy;
import entity.enemy.knife.KitchenKnife;
import entity.enemy.mold.Mold;
import entity.enemy.mold.MoldResidue;
import entity.enemy.mold.MoldSpore;

public class IntroLevel extends LevelState {
	
	//name
	public static final String NAME = "Intro Level";
		
	//initial player properties
	public static final double INITIAL_GRAVITY = 20;
	public static final int INITIAL_PLAYER_X_COORDINATE = 1480;
	public static final int INITIAL_PLAYER_Y_COORDINATE = 820;
	public static final int INITIAL_PLAYER_X_VELOCITY = 0;
	public static final int INITIAL_PLAYER_Y_VELOCITY = 0;
		
	//initial enemies
	public static final Enemy[] INITIAL_ENEMIES = {new MoldSpore(3071, 768, 0, 0, 0), /*new Mold(0, 0, 0, 0),/*
												   new Mold(0, 0, 0, 0), new Mold(0, 0, 0, 0), new Mold(0, 0, 0, 0), 
												   new Mold(0, 0, 0, 0), new KitchenKnife(0, 0, 0, 0), 
												   new KitchenKnife(0, 0, 0, 0), new KitchenKnife(0, 0, 0, 0), 
												   new KitchenKnife(0, 0, 0, 0), new KitchenKnife(0, 0, 0, 0), 
												   new KitchenKnife(0, 0, 0, 0), new KitchenKnife(0, 0, 0, 0), 
												   new KitchenKnife(0, 0, 0, 0)*/};
		
	//map properties
	public static final String MAP_FILE = "Toasty Trails/Resources/Maps/introlvmap";
	public static final int ROWS = 40;
	public static final int COLS = 168;
		
	public IntroLevel() {
			
		super(NAME, INITIAL_PLAYER_X_COORDINATE, INITIAL_PLAYER_Y_COORDINATE, INITIAL_PLAYER_X_VELOCITY, 
					    INITIAL_PLAYER_Y_VELOCITY, INITIAL_GRAVITY, INITIAL_ENEMIES, MAP_FILE, ROWS, COLS);
			
	}
	
}
