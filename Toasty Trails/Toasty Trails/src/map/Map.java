package map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.InanimateEntity;
import entity.platforms.Block;

public class Map {
	
	//CONSTANTS
	//------Legend------
	public static final int GRASS_BLOCK = 1;
	
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
					objMap[row][col] = new Block(0, 0);
				}
				
			}
			
		}
		
		return objMap;
		
	}
	
	public ArrayList<List<InanimateEntity>> getMap() {
		
		return map;
		
	}
	
}
