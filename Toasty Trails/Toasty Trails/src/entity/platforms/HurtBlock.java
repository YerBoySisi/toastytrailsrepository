package entity.platforms;

import java.io.File;

import entity.Damager;
import entity.LivingEntity;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class HurtBlock extends Block implements Damager {
	
	public final static String SPRITE[] = {"file:Toasty Trails/Resources/Tilesets/4.png"};
	private int dmg;
	
	public HurtBlock(double x, double y) {
		
		super(x, y, 0);
		setSprite(SPRITE[0]);
		setDamage(40);
		
	}
	
	public void performAction(LivingEntity e) {
		
		attack(e);
		
	}

	@Override
	public void attack(LivingEntity e) {
		
		Media sfx = new Media(new File("Toasty Trails/Resources/Sounds/hit.mp3").toURI().toString());
		MediaPlayer mp = new MediaPlayer(sfx);
		
		if(!e.invincible) {
			
			mp.play();
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
