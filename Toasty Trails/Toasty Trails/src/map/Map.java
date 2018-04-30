package map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.InanimateEntity;
import entity.platforms.Block;
import entity.platforms.HurtBlock;
import entity.platforms.LaunchBlock;
import entity.platforms.PermeableBlock;
import entity.platforms.TimedBlock;
import entity.platforms.TimedBlock1;
import entity.platforms.TimedBlock2;
import entity.platforms.TimedBlock3;

public class Map {
	
	//CONSTANTS
	//------Legend------
	public static final int GRASS_BLOCK = 10;
	public static final int DIRT_BLOCK = 11;
	public static final int STONE_BLOCK = 12;
	public static final int KITCHEN_TILE = 13;
	public static final int BRICK = 14;
	public static final int PLANK_TILE = 15;
	public static final int PERMEABLE_BLOCK = 2;
	public static final int TIMED_BLOCK = 30;
	public static final int TIMED_BLOCK_1 = 31;
	public static final int TIMED_BLOCK_2 = 32;
	public static final int TIMED_BLOCK_3 = 33;
	public static final int HURT_BLOCK = 4;
	public static final int LAUNCH_BLOCK = 5;
	
	//variables
	//map
	private ArrayList<List<InanimateEntity>> map;
	
	public Map(int[][] map) {
		
		InanimateEntity[][] objectMap = convertMap(map);
		this.map = new ArrayList<List<InanimateEntity>>();
		
		for (InanimateEntity[] array : objectMap) {
	        this.map.add(Arrays.asList(array));
	    }
		
	}

	public InanimateEntity[][] convertMap(int[][] map) {
		
		InanimateEntity[][] objMap = new InanimateEntity[map.length][map[0].length];
		
		for(int row = 0; row < map.length; row++) {
			
			for(int col = 0; col < map[row].length; col++) {
				
				if(map[row][col] == GRASS_BLOCK) {
					objMap[row][col] = new Block(0, 0, 0);
				}
				
				if(map[row][col] == DIRT_BLOCK) {
					objMap[row][col] = new Block(0, 0, 1);
				}
				
				if(map[row][col] == STONE_BLOCK) {
					objMap[row][col] = new Block(0, 0, 2);
				}
				
				if(map[row][col] == KITCHEN_TILE) {
					objMap[row][col] = new Block(0, 0, 3);
				}
				
				if(map[row][col] == BRICK) {
					objMap[row][col] = new Block(0, 0, 5);
				}
				
				if(map[row][col] == PLANK_TILE) {
					objMap[row][col] = new Block(0, 0, 7);
				}
				
				if(map[row][col] == PERMEABLE_BLOCK) {
					objMap[row][col] = new PermeableBlock(0, 0);
				}
				
				if(map[row][col] == TIMED_BLOCK) {
					objMap[row][col] = new TimedBlock(0, 0);
				}
				
				if(map[row][col] == TIMED_BLOCK_1) {
					objMap[row][col] = new TimedBlock1(0, 0);
				}
				
				if(map[row][col] == TIMED_BLOCK_2) {
					objMap[row][col] = new TimedBlock2(0, 0);
				}
				
				if(map[row][col] == TIMED_BLOCK_3) {
					objMap[row][col] = new TimedBlock3(0, 0);
				}
				
				if(map[row][col] == HURT_BLOCK) {
					objMap[row][col] = new HurtBlock(0, 0);
				}
				
				if(map[row][col] == LAUNCH_BLOCK) {
					objMap[row][col] = new LaunchBlock(0, 0);
				}
				
			}
			
		}
		
		return objMap;
		
	}
	
	public ArrayList<List<InanimateEntity>> getMap() {
		
		return map;
		
	}
	
}
