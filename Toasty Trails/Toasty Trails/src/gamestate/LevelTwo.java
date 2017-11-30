package gamestate;

import java.util.ArrayList;

import entity.enemy.Enemy;

public class LevelTwo extends LevelState {
	
	//constants
	public static final double INITIAL_GRAVITY = 20;
	public static final ArrayList<Enemy> INITIAL_ENEMIES = new ArrayList<Enemy>();
	
	public LevelTwo() {
		
		super(INITIAL_GRAVITY, INITIAL_ENEMIES);
		
	}
	
	
	
}
