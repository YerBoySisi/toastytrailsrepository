package gamestate;

import entity.enemy.bird.Bird;
import entity.enemy.knife.Knife;
import map.Map;

import entity.enemy.Enemy;

public class LeveFive extends LevelState {
	
	//constants
	public static final String NAME = "Level One";
	public static final double INITIAL_GRAVITY = 20;
	public static final Enemy[] INITIAL_ENEMIES = {new Knife(0, 0, 0, 0), new Bird(0, 0, 0, 0)};
	public static final String mapFile = "Resources/Maps/lv1map.txt";
	public static final int[][] intMap = fileToIntArray(mapFile);
	
	public LeveFive() {
		
		super(NAME, INITIAL_GRAVITY, INITIAL_ENEMIES);
		map = new Map(intMap);
		
	}
	
}
