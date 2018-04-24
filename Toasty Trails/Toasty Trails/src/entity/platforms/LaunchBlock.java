package entity.platforms;

import java.io.File;

import entity.LivingEntity;
import entity.player.Player;
import entity.player.Player.Form;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class LaunchBlock extends Block {
	
	public final static String SPRITE[] = {"file:Toasty Trails/Resources/Tilesets/5.png"};
	private double launchHeight;
	
	public LaunchBlock(double x, double y) {
		
		super(x, y, 0);
		setSprite(SPRITE[0]);
		launchHeight = 30;
		
	}
	
	@Override
	public void performAction(LivingEntity e) {
		
		Media sfx = new Media(new File("Toasty Trails/Resources/Sounds/bounce.mp3").toURI().toString());
		MediaPlayer mp = new MediaPlayer(sfx);
		mp.play();
		
		e.setYVelocity(-launchHeight);
		
		if(e instanceof Player) {
			((Player) e).setForm(Form.NORMAL);
		}
		
	}

}
