package map;

import java.util.ArrayList;
import java.util.Arrays;

import entity.Block;
import entity.InanimateEntity;

public class Map {
	
	//CONSTANTS
	/*------Legend------
	 * blank space = 0
	 * grass block = 1*/
	
	//map elements
	private static final InanimateEntity[] TILES = {new Block()};
	
	//variables
	//map
	private ArrayList<ArrayList<InanimateEntity>> map;
	
	public Map(int[][] map) {
		
		InanimateEntity[][] objectMap = convertMap(map);
		this.map = new ArrayList<ArrayList<InanimateEntity>>();
		
		for(InanimateEntity[] col : objectMap){
			ArrayList<InanimateEntity> o = new ArrayList<InanimateEntity>(Arrays.asList(col));
			this.map.add(o);
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
	
	public ArrayList<ArrayList<InanimateEntity>> getMap() {
		
		return map;
		
	}
	
}
