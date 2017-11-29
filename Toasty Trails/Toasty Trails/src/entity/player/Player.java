package entity.player;

import entity.LivingEntity;

public class Player extends LivingEntity {
	
	//constants
	private static final int WEIGHT = 3;
	private static final int MAXSPEED = 3;
	
	//variables
	private boolean alive;
	private int velocityX;
	private int velocityY;
	
	public Player() {
		
		super(true, 0, 0, WEIGHT, MAXSPEED);
		
		alive = true;
		velocityX = 0;
		velocityY = 0;
		
	}

}
