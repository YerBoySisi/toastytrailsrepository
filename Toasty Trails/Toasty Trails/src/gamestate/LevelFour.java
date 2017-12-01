package gamestate;

import entity.enemy.Knife;
import entity.enemy.Enemy;

public class LevelFour extends LevelState {
	
	//constants
	public static final String NAME = "Level Four";
	public static final double INITIAL_GRAVITY = 20;
	public static final Enemy[] INITIAL_ENEMIES = {new Knife(0, 0, 0, 4)};
	
	public LevelFour() {
		
		super(NAME, INITIAL_GRAVITY, INITIAL_ENEMIES);
		
	}
	
	
	
}
