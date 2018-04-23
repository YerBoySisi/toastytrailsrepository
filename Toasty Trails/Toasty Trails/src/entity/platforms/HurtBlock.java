package entity.platforms;

import entity.Damager;
import entity.LivingEntity;

public class HurtBlock extends Block implements Damager {
	
	public final static String SPRITE[] = {"file:Toasty Trails/Resources/Tilesets/4.png"};
	private int dmg;
	
	public HurtBlock(double x, double y) {
		
		super(x, y, 0);
		setSprite(SPRITE[0]);
		setDamage(10);
		
	}
	
	public void performAction(LivingEntity e) {
		
		attack(e);
		
	}

	@Override
	public void attack(LivingEntity e) {
		
		if(!e.invincible) {
			
			e.changeHP(-dmg);
			e.setXVelocity(-e.lastDirection * 5);
			e.setYVelocity(-3);
			e.activateInvincibility();
		
		}
		
	}

	@Override
	public int getDamage() {
		
		return dmg;
		
	}

	@Override
	public void setDamage(int damage) {

		dmg = damage;
		
	}
	
}
