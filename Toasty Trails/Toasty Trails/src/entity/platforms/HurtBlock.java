package entity.platforms;

import audio.Sound;
import entity.Damager;
import entity.LivingEntity;

public class HurtBlock extends Block implements Damager {
	
	public static final String SPRITE[] = {"file:Toasty Trails/Resources/Tilesets/4.png"};
	public static final int HURT_SOUND = 0;
	
	private int dmg;
	
	public HurtBlock(double x, double y) {
		
		super(x, y, 0);
		setSprite(SPRITE[0]);
		setDamage(20);
		
		sounds.add(HURT_SOUND, new Sound("Toasty Trails/Resources/Sounds/hit.wav"));
		
	}
	
	public void performAction(LivingEntity e) {
		
		attack(e);
		
	}

	@Override
	public void attack(LivingEntity e) {
		
		if(!e.invincible) {
			
			sounds.get(HURT_SOUND).play();
			e.changeHP(-dmg);
			
			if(e.leftBoundary() <= width / 2) {
				e.setXVelocity(-8.5);
			} else {
				e.setXVelocity(8.5);
			}
			
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
