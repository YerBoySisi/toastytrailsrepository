package gamestate.levels;

import entity.enemy.knife.Butterknife;

import entity.enemy.Enemy;

public class LevelTwo extends LevelState {
	
	//name
	public static final String NAME = "Level One";
	
	//initial player properties
	public static final double INITIAL_GRAVITY = 20;
	public static final int INITIAL_PLAYER_X_COORDINATE = 300;
	public static final int INITIAL_PLAYER_Y_COORDINATE = -100;
	public static final int INITIAL_PLAYER_X_VELOCITY = 0;
	public static final int INITIAL_PLAYER_Y_VELOCITY = 0;
	
	//initial enemies
	public static final Enemy[] INITIAL_ENEMIES = {new Butterknife(500, 0, 0, 0)};
	
	//map properties
	public static final String MAP_FILE = "Toasty Trails/Resources/Maps/lv2map";
	public static final int ROWS = 40;
	public static final int COLS = 168;
	
	public LevelTwo() {
		
		super(NAME, INITIAL_PLAYER_X_COORDINATE, INITIAL_PLAYER_Y_COORDINATE, INITIAL_PLAYER_X_VELOCITY, 
				    INITIAL_PLAYER_Y_VELOCITY, INITIAL_GRAVITY, INITIAL_ENEMIES, MAP_FILE, ROWS, COLS);
		
	}
	
}
