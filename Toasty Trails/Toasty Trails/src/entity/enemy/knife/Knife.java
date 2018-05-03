package entity.enemy.knife;

import entity.Damager;
import entity.Entity;
import entity.enemy.Enemy;
import main.GamePanel;

public abstract class Knife extends Enemy implements Damager {
	
	//constants
	public static final String NAME = "Knife";
	public static final int HP = 10;

	protected int chargeSpeed;
	
	public Knife(int x, int y, double xVelocity, double yVelocity, int dmg, int spd) {
		
		super(NAME, x, y, xVelocity, yVelocity, dmg, HP);
		chargeSpeed = spd;
		
	}
	
	public void walk(int direction) {
		
		setXVelocity(2 * direction);
		
	}
	
	public void hop() {
		
		setYVelocity(-5);
		
	}
	
	public void chargeAttackHop(int direction) {
		
		setXVelocity(0);
		hop();
		
	}
	
	public void windUp(int direction) {
		
		for(int i = 0; i < 5; i++) {
			setXVelocity(-direction * .25);
		}
		
	}
	
	public void charge(int direction) {
		
		setXVelocity(direction * chargeSpeed);
		
	}
	
	public boolean inVision(Entity e) {
		
		if(this.x() - e.rightBoundary() <= 55 && lastDirection == -1) {
			lastDirection = -1;
			return true;
		}
		
		if(e.x() - this.rightBoundary() <= 55 && e.x() - this.rightBoundary() >= 0 && lastDirection == 1) {
			lastDirection = 1;
			return true;
		}
		
		return false;
		
	}
	
	public void chargeAttack(int direction) {
		
		new Thread(() -> {
			
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
	
	@Override
	public void aI() {
		
		Thread walk = new Thread(() -> {
			
			if(Math.random() < .5) {
				lastDirection = -1;
			} else {
				lastDirection = 1;
			}
			
			while(!Thread.interrupted()) {
					
				try {
					Thread.sleep((int)((Math.random() * (3000 - 1000) + 1000)));
				} catch (InterruptedException e) {
					setXVelocity(0);
					break;
				}
							
				walk(lastDirection * -1);
							
				try {
					Thread.sleep((int)((Math.random() * (1500 - 300) + 300)));
				} catch (InterruptedException e) {
					setXVelocity(0);
					break;
				}
							
				setXVelocity(0);
					
			}
			
		});
		
		Thread spotPlayer = new Thread(() -> {
			
			while(true) {
				
				if(inVision(GamePanel.toasty)) {
					walk.interrupt();
					break;
				} else {
					System.out.print("");
				}
				
			}
			
			chargeAttack(lastDirection);
			
		});
		
		walk.start();
		spotPlayer.start();
		
	}
	
}
