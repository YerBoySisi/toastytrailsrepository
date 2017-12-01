package entity.enemy;

import entity.Entity;
import entity.LivingEntity;

public abstract class Enemy extends LivingEntity {
	
	private int damage;
	
	protected Enemy(String name, int x, int y, double xVelocity, double yVelocity, int damage, int hp) {
		
		super(xVelocity, yVelocity);
		this.x = x;
		this.y = y;
		this.damage = damage;
		this.name = name;
		this.hp = hp;
		
	}

	public void attack(Entity e) {
		
		e.changeHP(-damage);
		
	}

	public int getDamage() {
		
		return damage;
		
	}

	public void setDamage(int damage) {
		
		this.damage = damage;
		
	}
	
	
	
}
