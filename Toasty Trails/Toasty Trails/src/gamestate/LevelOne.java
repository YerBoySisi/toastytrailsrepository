package gamestate;

import entity.enemy.Knife;
import map.Map;
import entity.enemy.Bird;
import entity.enemy.Enemy;

public class LevelOne extends LevelState {
	
	//constants
	public static final String NAME = "Level One";
	public static final double INITIAL_GRAVITY = 20;
	public static final Enemy[] INITIAL_ENEMIES = {new Knife(0, 0, 0, 0), new Bird(0, 0, 0, 0)};
	
	public LevelOne() {
		
		super(NAME, INITIAL_GRAVITY, INITIAL_ENEMIES);
		map = new Map(new int[][] {{0, 0, 0}, 
								   {0, 0, 0}, 
								   {0, 0, 0}});
		
	}
	
	
	
}
