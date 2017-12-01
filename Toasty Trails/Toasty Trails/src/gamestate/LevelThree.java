package gamestate;

import entity.enemy.Knife;
import entity.enemy.Enemy;

public class LevelThree extends LevelState {

	//constants
	public static final String NAME = "Level Three";
	public static final double INITIAL_GRAVITY = 20;
	public static final Enemy[] INITIAL_ENEMIES = {new Knife(0, 0, 0, 3)};
	
	public LevelThree() {
		
		super(NAME, INITIAL_GRAVITY, INITIAL_ENEMIES);
		
	}

	
	
}
