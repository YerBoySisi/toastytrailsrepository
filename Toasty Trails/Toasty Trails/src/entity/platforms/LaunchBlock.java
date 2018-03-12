package entity.platforms;

import entity.LivingEntity;
import entity.player.Player;
import entity.player.Player.Form;

public class LaunchBlock extends Block {
	
	public final static String SPRITE[] = {"file:Toasty Trails/Resources/Tilesets/5.png"};
	private double launchHeight;
	
	public LaunchBlock(double x, double y) {
		
		super(x, y);
		setSprite(SPRITE[0]);
		launchHeight = 30;
		
	}
	
	@Override
	public void performAction(LivingEntity e) {
		
		e.setYVelocity(-launchHeight);
		
		if(e instanceof Player) {
			((Player) e).setForm(Form.NORMAL);
		}
		
	}

}
