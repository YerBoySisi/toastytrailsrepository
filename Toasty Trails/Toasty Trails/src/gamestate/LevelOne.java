package gamestate;

import entity.enemy.bird.Bird;
import entity.enemy.knife.Butterknife;

import entity.enemy.Enemy;

public class LevelOne extends LevelState {
	
	//constants
	public static final String NAME = "Level One";
	public static final double INITIAL_GRAVITY = 20;
	public static final Enemy[] INITIAL_ENEMIES = {new Butterknife(0, 0, 0, 0), new Bird(0, 0, 0, 0)};
	public static final String MAP_FILE = "Resources/Maps/lv1map.txt";
	public static final int ROWS = 11;
	public static final int COLS = 14;
	
	public LevelOne() {
		
		super(NAME, INITIAL_GRAVITY, INITIAL_ENEMIES, MAP_FILE, ROWS, COLS);
		
	}
	
}
