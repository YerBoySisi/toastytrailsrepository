package entity.enemy;

import entity.Damager;
import entity.Entity;
import entity.LivingEntity;

public abstract class Enemy extends LivingEntity implements Damager {
	
	protected int damage;
	protected int force;
	protected boolean solid;
	protected boolean attacking;
	
	protected Enemy(String name, int x, int y, double xVelocity, double yVelocity, int damage, int hp) {
		
		super(xVelocity, yVelocity);
		this.x = x;
		this.y = y;
		this.damage = damage;
		force = 5;
		this.name = name;
		this.hp = hp;
		
	}

	public void attack(LivingEntity e) {
		
		System.out.println(e.getHP());
		
		if(!e.invincible) {
			
			attacking = true;
			
			e.activateInvincibility();
			e.changeHP(-damage);
			
			if(e.bottomBoundary() <= this.topBoundary() + e.getYVelocity()) { 
				e.setXVelocity(force * lastDirection);
			} else {
				
				if(this.rightBoundary() <= e.leftBoundary() + (e.getWidth() / 2) + 5) {
					e.setXVelocity(force);
				} else {
					e.setXVelocity(-force);
				}
				
			}
			
			if(force < 6) {
				e.setYVelocity(-force);
			} else {
				e.setYVelocity(-6);
			}
			
			attacking = false;
		
		}
		
	}

	public int getDamage() {
		
		return damage;
		
	}

	public void setDamage(int damage) {
		
		this.damage = damage;
		
	}
	
	public abstract void aI();
	
	public abstract boolean inVision(Entity e);

	public boolean isSolid() {
		
		return solid;
		
	}

	public boolean attacking() {
		
		return attacking;
		
	}
	
}
