package gamestate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.InanimateEntity;
import entity.enemy.Enemy;
import map.Map;
import utility.StringFromFileLoader;

public abstract class LevelState {
	
	private static final String MAP_FILE = "lv1map.txt";
	
	protected double gravity;
	protected ArrayList<Enemy> enemies;
	protected String name;
	protected ArrayList<List<InanimateEntity>> map;
	protected int width;

	public LevelState(String name, double grav, Enemy[] enemies) {
		
		this.name = name;
		this.gravity = grav;
		this.enemies = new ArrayList<Enemy>(Arrays.asList(enemies));
		this.width = getLevelWidth();
		this.map = new Map(fileToIntArray(MAP_FILE)).getMap();
		
	}
	
	public static int[][] fileToIntArray(String file) {
		
		String textFile = new StringFromFileLoader(file).getFileContent();
		String[] text = textFile.split(" ");
		
		int[][] mapFile = new int[7][12];
		int idx = 0;
		
		for(int row = 0; row < mapFile.length; row++){
			
			for(int col = 0; col < mapFile[row].length; col++){
				mapFile[row][col] = Integer.parseInt(text[idx]);
				idx++;
			}
			
		}
		
		return mapFile;
		
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
	
	public int getLevelWidth() {
		
		int width = 0;
		
		for(int i = 0; i < map.get(0).size(); i++) {
			width += map.get(0).get(i).getWidth();
		}
		
		return width;
		
	}
	
}
