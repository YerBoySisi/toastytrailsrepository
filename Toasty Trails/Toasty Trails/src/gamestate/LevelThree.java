package gamestate;

import map.Map;

import entity.enemy.Enemy;

public class LevelThree extends LevelState {
	
	//constants
	public static final String NAME = "Level Three";
	public static final double INITIAL_GRAVITY = 20;
	public static final Enemy[] INITIAL_ENEMIES = {};
	public static final String mapFile = "Resources/Maps/lv3map.txt";
	public static final int[][] intMap = fileToIntArray(mapFile);
	
	public LevelThree() {
		
		super(NAME, INITIAL_GRAVITY, INITIAL_ENEMIES);
		map = new Map(intMap);
		
	}
	
}
