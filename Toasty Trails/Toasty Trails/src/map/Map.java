package map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entity.Block;
import entity.InanimateEntity;

public class Map {
	
	//CONSTANTS
	/*------Legend------
	 * blank space = 0
	 * grass block = 1*/
	
	//map elements
	private static final InanimateEntity[] TILES = {null, new Block(0, 0)};
	
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

	private InanimateEntity[][] convertMap(int[][] map) {
		
		InanimateEntity[][] objMap = new InanimateEntity[map.length][map[0].length];
		
		for(int row = 0; row < map.length; row++) {
			
			for(int col = 0; col < map[row].length; col++) {
				objMap[row][col] = TILES[map[row][col]];
			}
			
		}
		
		return objMap;
		
	}
	
	public ArrayList<List<InanimateEntity>> getMap() {
		
		return map;
		
	}
	
}
