package entity.enemy;

import entity.Entity;
import entity.LivingEntity;

public abstract class Enemy extends LivingEntity {
	
	private int damage;
	
	protected Enemy(int x, int y, double xVelocity, double yVelocity) {
		
		super(xVelocity, yVelocity);
		this.x = x;
		this.y = y;
		
	}

	public void attack(Entity e) {
		
		e.changeHP(damage);
		
	}
	
}
