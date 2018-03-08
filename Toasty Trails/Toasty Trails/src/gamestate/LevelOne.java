package gamestate;

import entity.enemy.knife.Butterknife;

import entity.enemy.Enemy;

public class LevelOne extends LevelState {
	
	//name
	public static final String NAME = "Level One";
	
	//initial player properties
	public static final double INITIAL_GRAVITY = 20;
	public static final int INITIAL_PLAYER_X_COORDINATE = 0;
	public static final int INITIAL_PLAYER_Y_COORDINATE = 700;
	public static final int INITIAL_PLAYER_X_VELOCITY = 80;
	public static final int INITIAL_PLAYER_Y_VELOCITY = 0;
	
	//initial enemies
	public static final Enemy[] INITIAL_ENEMIES = {new Butterknife(768, 0, 0, 0)};
	
	//map properties
	public static final String MAP_FILE = "Toasty Trails/Resources/Maps/lv1map.txt";
	public static final int ROWS = 36;
	public static final int COLS = 84;
	
	public LevelOne() {
		
		super(NAME, INITIAL_PLAYER_X_COORDINATE, INITIAL_PLAYER_Y_COORDINATE, INITIAL_PLAYER_X_VELOCITY, 
				    INITIAL_PLAYER_Y_VELOCITY, INITIAL_GRAVITY, INITIAL_ENEMIES, MAP_FILE, ROWS, COLS);
		
	}
	
}
