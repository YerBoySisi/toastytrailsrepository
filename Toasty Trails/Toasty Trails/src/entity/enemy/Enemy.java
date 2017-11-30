package entity.enemy;

import entity.LivingEntity;

public abstract class Enemy extends LivingEntity {

	protected Enemy(int x, int y, double xVelocity, double yVelocity) {
		
		super(xVelocity, yVelocity);
		this.x = x;
		this.y = y;
		
	}

	
	
}
