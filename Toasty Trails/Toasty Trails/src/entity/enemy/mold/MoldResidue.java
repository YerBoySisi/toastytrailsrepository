package entity.enemy.mold;

import entity.Damager;
import entity.Entity;
import entity.LivingEntity;
import main.GamePanel;

public class MoldResidue extends MoldSpore implements Damager {
	
	public final String FILE_PATH = "file:Toasty Trails/Resources/Sprites/Other/";
	public final String[] SPRITE = {FILE_PATH + "gmoldresidue.png", FILE_PATH + "gmoldresidue.png", 
									FILE_PATH + "gmoldresidue.png", FILE_PATH + "gmoldresidue.png", 
									FILE_PATH + "gmoldresidue.png"};
	
	public static final int GREEN = 0; public static final int YELLOW = 1; public static final int BLUE = 2;
	public static final int RED = 3; public static final int BLACK = 4;
	
	public MoldResidue(int x, int y, double xVelocity, double yVelocity, int type) {
		
		super(x, y, xVelocity, yVelocity, 0);
		setStats(type);
		damage = 1;
		
		if(type == BLACK) {
			damage = 2;
		}
		
		setSprite(SPRITE[type]);
		solid = false;
		aI();
		
	}
	
	@Override
	public void attack(LivingEntity e) {
		
		Thread attack = new Thread(() -> { 
			
			attacking = true;
			
			for(int i = 0; i < 5; i++) {
				
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e1) {
					break;
				}
				
				if(!e.invincible) {
					
					e.activateInvincibility();
					e.changeHP(-damage * GamePanel.toasty.getSporeCount());
				
				}
				
			}
			
			attacking = false;
			
		
		});
		
		attack.start();
		
		new Thread (() -> {
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
			
			attack.interrupt();
			
			GamePanel.toasty.changeSporeCount(-1);
			GamePanel.lvls[GamePanel.currentLvl].getEnemies().remove(this);
			
		}).start();
		
	}

	@Override
	public void aI() {
		
		new Thread(() -> {
			
			while(!colliding(GamePanel.toasty)) {
				System.out.print("");
			}
			
			if(colliding(GamePanel.toasty) && GamePanel.toasty.getSporeCount() < 5) {
				
				int offsetX = (int)(Math.random() * 32);
				int offsetY = (int)(Math.random() * 44);
				GamePanel.toasty.changeSporeCount(1);
				
				while(true) {
						
					setX(GamePanel.toasty.x() + offsetX);
					setY(GamePanel.toasty.y() + offsetY);
						
				}
				
			}
			
		}).start();

	}

	@Override
	public boolean inVision(Entity e) {
		
		return false;
		
	}

}
