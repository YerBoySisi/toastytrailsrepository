package gamestate.levels;

import entity.enemy.Enemy;

public class LevelOneBonus extends LevelState {
	
	//name
	public static final String NAME = "Level One";
		
	//initial player properties
	public static final double INITIAL_GRAVITY = 20;
	public static final int INITIAL_PLAYER_X_COORDINATE = 0;
	public static final int INITIAL_PLAYER_Y_COORDINATE = 1600;
	public static final int INITIAL_PLAYER_X_VELOCITY = 26;
	public static final int INITIAL_PLAYER_Y_VELOCITY = 0;
		
	//initial enemies
	public static final Enemy[] INITIAL_ENEMIES = {};
		
	//map properties
	public static final String MAP_FILE = "Toasty Trails/Resources/Maps/lv1bonusmap";
	public static final int ROWS = 120;
	public static final int COLS = 168;
		
	public LevelOneBonus() {
			
		super(NAME, INITIAL_PLAYER_X_COORDINATE, INITIAL_PLAYER_Y_COORDINATE, INITIAL_PLAYER_X_VELOCITY, 
					    INITIAL_PLAYER_Y_VELOCITY, INITIAL_GRAVITY, INITIAL_ENEMIES, MAP_FILE, ROWS, COLS);
		
		//ltrs[0] = new Letter(3883, 1546, 1);
		//ltrs[1] = new Letter(1372, 710, 2);
		
		//LEVEL ONE BONUS LEVEL CAMERA
		/*
		if(toasty.y() < 1300) {
        	
        	if(toasty.y() < 1100) {
        		
        		if(cam.getTranslateZ() > -1000) {
            		cam.setTranslateZ(cam.getTranslateZ() - 50);
            	} else {
            		cam.setTranslateZ(-1000);
            	}
        		
        	} else {
        		
        		if(cam.getTranslateZ() < -600) {
            		cam.setTranslateZ(cam.getTranslateZ() + 50);
            	} else {
            		cam.setTranslateZ(-600);
            	}
        		
        	}
        	
        }  else {
        	
            if(toasty.x() > 1472 && toasty.x() < lvls[currentLvl].getLevelWidth()) {
            	
            	if(cam.getTranslateZ() > -1200) {
            		cam.setTranslateZ(cam.getTranslateZ() - 50);
            	} else {
            		cam.setTranslateZ(-1200);
            	}
            	
            } else {
            	
            	if(cam.getTranslateZ() < -600) {
            		cam.setTranslateZ(cam.getTranslateZ() + 50);
            	} else {
            		cam.setTranslateZ(-600);
            	}
            	
            }
            
        }
		*/
			
	}
	
}
