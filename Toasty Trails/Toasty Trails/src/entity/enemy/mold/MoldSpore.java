package entity.enemy.mold;


import entity.Damager;
import entity.Entity;
import entity.LivingEntity;
import entity.enemy.Enemy;
import main.GamePanel;

public class MoldSpore extends Enemy implements Damager {
	
	public final String FILE_PATH = "file:Toasty Trails/Resources/Sprites/Enemies/Mold Spore/";
	public final String[] SPRITE = {FILE_PATH + "gmoldspore.png", FILE_PATH + "ymoldspore.png", FILE_PATH + "bmoldspore.png",
									FILE_PATH + "rmoldspore.png", FILE_PATH + "bkmoldspore.png"};
	
	public static final int GREEN = 0; public static final int YELLOW = 1; public static final int BLUE = 2;
	public static final int RED = 3; public static final int BLACK = 4;
	
	public MoldSpore(int x, int y, double xVelocity, double yVelocity, int type) {
		
		super("Mold Spore", x, y, xVelocity, yVelocity, 0, 0);
		setStats(type);
		setSprite(SPRITE[type]);
		solid = true;
		
	}

	protected void setStats(int type) {

		switch(type) {
		
		case GREEN :
			hp = 1;
			damage = 10;
			force = 5;
			break;
		case YELLOW :
			hp = 30;
			damage = 10;
			force = 5;
			break;
		case BLUE :
			hp = 5;
			damage = 10;
			force = 5;
			break;
		case RED :
			hp = 2;
			damage = 5;
			force = 3;
			break;
		case BLACK :
			hp = 99;
			damage = 25;
			force = 7;
			break;
			
		}
		
	}
	
	@Override
	public void attack(LivingEntity e) {
		
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
			
			if(GamePanel.toasty.getSporeCount() < 5) {
				GamePanel.lvls[GamePanel.currentLvl].getEnemies().add(new MoldResidue((int)GamePanel.toasty.x(), 
																						 (int)GamePanel.toasty.y(),
																						  0, 0, 0));
			}
			
			
			
			attacking = false;
			
		}
		
	}

	@Override
	public void aI() {
		
		new Thread(() -> {
			
			while(true) {
				
				velocityY = .03;
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				velocityY = -.03;
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		}).start();
		
	}

	@Override
	public boolean inVision(Entity e) {
		return false;
	}

}
