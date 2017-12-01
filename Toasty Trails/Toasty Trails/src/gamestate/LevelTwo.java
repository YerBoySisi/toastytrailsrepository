package gamestate;

import entity.enemy.Knife;
import entity.enemy.Enemy;

public class LevelTwo extends LevelState {
	
	//constants
	public static final String NAME = "Level Two";
	public static final double INITIAL_GRAVITY = 20;
	public static final Enemy[] INITIAL_ENEMIES = {new Knife(0, 0, 0, 2)};
	
	public LevelTwo() {
		
		super(NAME, INITIAL_GRAVITY, INITIAL_ENEMIES);
		
	}
	
	
	
}
