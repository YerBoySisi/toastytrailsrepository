package entity.enemy;

import entity.Entity;
import entity.LivingEntity;

public abstract class Enemy extends LivingEntity {
	
	private int damage;
	private int force;
	
	protected Enemy(String name, int x, int y, double xVelocity, double yVelocity, int damage, int hp) {
		
		super(xVelocity, yVelocity);
		this.x = x;
		this.y = y;
		this.damage = damage;
		force = 5;
		this.name = name;
		this.hp = hp;
		
	}

	public void attack(Entity e) {
		
		if(!e.invincible) {
			
			e.changeHP(-damage);
			
			if(e.bottomBoundary() <= this.topBoundary() + e.getYVelocity()) { 
				e.setXVelocity(force * lastDirection);
			} else {
				
				if(this.rightBoundary() <= e.leftBoundary() + this.getXVelocity()) {
					e.setXVelocity(force);
				} else {
					e.setXVelocity(-force);
				}
				
			}
			
			e.activateInvincibility();
		
		}
		
	}

	public int getDamage() {
		
		return damage;
		
	}

	public void setDamage(int damage) {
		
		this.damage = damage;
		
	}
	
	
	
}
