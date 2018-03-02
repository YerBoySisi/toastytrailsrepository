package gamestate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.InanimateEntity;
import entity.enemy.Enemy;
import map.Map;
import utility.StringFromFileLoader;

public abstract class LevelState {
	
	public ArrayList<List<InanimateEntity>> map;
	protected String name;
	protected double gravity;
	protected ArrayList<Enemy> enemies;
	protected int numRows;
	protected int numCols;
	protected int width;
	
	protected int initialPlayerX;
	protected int initialPlayerY;
	protected int initialPlayerXVelocity;
	protected int initialPlayerYVelocity;
	
	public LevelState(String name, int x, int y, int xVel, int yVel, double grav, Enemy[] enemies, String mapFile, int rows, int cols) {
		
		this.name = name;
		
		initialPlayerX = x;
		initialPlayerY = y;
		initialPlayerXVelocity = xVel;
		initialPlayerYVelocity = yVel;
		gravity = grav;
		
		this.enemies = new ArrayList<Enemy>(Arrays.asList(enemies));
		
		numRows = rows;
		numCols = cols;
		map = new Map(fileToIntArray(mapFile)).getMap();
		setLevelWidth();
		setCoordinates();
		
	}
	
	public int[][] fileToIntArray(String file) {
		
		String textFile = new StringFromFileLoader(file).getFileContent();
		String[] text = textFile.split(" ");
		
		int[][] intMap = new int[numRows][numCols];
		int idx = 0;
		
		for(int row = 0; row < intMap.length; row++){
			
			for(int col = 0; col < intMap[row].length; col++){
				intMap[row][col] = Integer.parseInt(text[idx]);
				idx++;
			}
			
		}
		
		return intMap;
		
	}

	/**
	 * @return the initialPlayerX
	 */
	public int getInitialPlayerX() {
		
		return initialPlayerX;
		
	}

	/**
	 * @return the initialPlayerY
	 */
	public int getInitialPlayerY() {
		
		return initialPlayerY;
		
	}

	/**
	 * @return the initialPlayerXVelocity
	 */
	public int getInitialPlayerXVelocity() {
		
		return initialPlayerXVelocity;
		
	}

	/**
	 * @return the initialPlayerYVelocity
	 */
	public int getInitialPlayerYVelocity() {
		
		return initialPlayerYVelocity;
		
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
	
	public void setLevelWidth() {
		
		for(int i = 0; i < map.get(0).size(); i++) {
			width += 32;
		}
		
	}
	
	public int getLevelWidth() {
		
		return width;
		
	}
	
	public void setCoordinates() {
		
		for(int row = 0; row < map.size(); row++) {
			
        	for(int col = 0; col < map.get(row).size(); col++) {
    			
        		if(map.get(row).get(col) != null) {
        			map.get(row).get(col).setX(32 * col);
        			map.get(row).get(col).setY(32 * row);
        		}
            	
    		}
        	
		}
		
	}
	
}
