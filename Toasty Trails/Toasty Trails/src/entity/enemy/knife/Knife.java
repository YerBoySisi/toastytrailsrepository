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
	
	public void chargeAttackHop(int direction) {
		
		setXVelocity(0);
		hop();
		
	}
	
	public void windUp(int direction) {
		
		for(int i = 0; i < 5; i++) {
			velocityX -= direction * .05;
		}
		
	}
	
	public void charge(int direction) {
		
		velocityX = direction * 6;
		
	}
	
	public void chargeAttack(int direction) {
		
		new Thread(() -> {
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			chargeAttackHop(direction);
			
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			windUp(direction);
			
			try {
				Thread.sleep(550);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			charge(direction);
			
		}).start();
		
	}
	
}
