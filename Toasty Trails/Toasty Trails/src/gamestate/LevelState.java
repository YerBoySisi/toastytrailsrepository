package gamestate;

import java.util.ArrayList;

import entity.enemy.Enemy;

public abstract class LevelState {
	
	protected double gravity;
	protected ArrayList<Enemy> enemies;
	
	public LevelState(double grav, ArrayList<Enemy> enemies) {
		
		this.gravity = grav;
		this.enemies = enemies;
		
	}
	
}
