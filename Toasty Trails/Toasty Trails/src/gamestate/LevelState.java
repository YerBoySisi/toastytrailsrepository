package gamestate;

import java.util.ArrayList;
import java.util.Arrays;

import entity.InanimateEntity;
import entity.enemy.Enemy;
import map.Map;

public abstract class LevelState {
	
	protected double gravity;
	protected ArrayList<Enemy> enemies;
	protected String name;
	protected Map map;

	public LevelState(String name, double grav, Enemy[] enemies) {
		
		this.name = name;
		this.gravity = grav;
		this.enemies = new ArrayList<Enemy>(Arrays.asList(enemies));
		
	}

	public double getGravity() {
		
		return gravity;
		
	}

	public void setGravity(double gravity) {
		
		this.gravity = gravity;
		
	}

	public ArrayList<Enemy> getEnemies() {
		
		return enemies;
		
	}
	
	public void addEnemy(Enemy e) {
		
		enemies.add(e);
		
	}
	
	public void removeEnemy(Enemy e) {
		
		enemies.remove(e);
		
	}

	public String getName() {
		
		return name;
		
	}

	public void setName(String name) {
		
		this.name = name;
		
	}
	
	public ArrayList<ArrayList<InanimateEntity>> getMap() {
		
		return map.getMap();
		
	}
	
}
