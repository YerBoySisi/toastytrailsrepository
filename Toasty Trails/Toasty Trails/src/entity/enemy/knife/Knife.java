package entity.enemy.knife;

import entity.enemy.Enemy;

public abstract class Knife extends Enemy {
	
	//constants
	public static final String NAME = "Knife";
	public static final int DAMAGE = 20;
	public static final int HP = 10;
	
	public Knife(int x, int y, double xVelocity, double yVelocity) {
		
		super(NAME, x, y, xVelocity, yVelocity, DAMAGE, HP);
		
	}
	
	public void hop() {
		
		velocityY -= 5;
		
	}
	
	public void charge(int direction) {
		
		velocityX += direction * 9;
		
	}
	
	public void chargeAttack(int direction) {
		
		setXVelocity(0);
		hop();
		while(velocityY != 0) {}
		charge(direction);
		
	}
	
	public void performChargeAttack(int direction) {
		
		new Thread(() -> chargeAttack(direction)).start();
		
	}
	
}
