package gamestate;

import java.util.ArrayList;

import entity.enemy.ButterKnife;
import entity.enemy.Enemy;

public class LevelOne extends LevelState {
	
	//constants
	public static final double INITIAL_GRAVITY = 20;
	public static final ArrayList<Enemy> INITIAL_ENEMIES = new ArrayList<Enemy>();
	
	public LevelOne() {
		
		super(INITIAL_GRAVITY, INITIAL_ENEMIES);
		INITIAL_ENEMIES.add(new ButterKnife(0, 0, 0, 0));
		
	}
	
	
	
}
