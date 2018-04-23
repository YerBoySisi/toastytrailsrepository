package entity.enemy.knife;

import javafx.scene.image.Image;

public class Butterknife extends Knife {
	
	//constants
	public static final int LEFT = -1;
	
	//sprite file paths
	public final String FILE_PATH = "file:Toasty Trails/Resources/Sprites/Enemies/Butterknife/";
	public final String[] IDLE_SPRITE = {FILE_PATH + "butterknifeidle.png"};
	public final String[] WALK_SPRITE = {FILE_PATH + "butterknifewalk1.png", FILE_PATH + "butterknifewalk2.png",
										 FILE_PATH + "butterknifewalk3.png", FILE_PATH + "butterknifewalk4.png"};
	public final String[] CHARGE_SPRITE = {FILE_PATH + "butterknifecharge.png"};
	public final String[] HOP_SPRITE = {FILE_PATH + "butterknifejump.png"};
	public final String[][] SPRITE = {IDLE_SPRITE, WALK_SPRITE, CHARGE_SPRITE, HOP_SPRITE};
	
	//actions
	private static final int STANDING = 0;
	private static final int WALKING = 1;
	private static final int CHARGING = 2;
	private static final int HOPPING = 3;
	
	public Butterknife(int x, int y, double xVelocity, double yVelocity) {
		
		super(x, y, xVelocity, yVelocity);
		mass = 0.125;
		
	}
	
	public int getAction() {
		
		int action = 0;
		
		if(velocityX == 0 && velocityY == 0) {
			action = STANDING;
		} else if(Math.abs(velocityX) < 6) {
			action = WALKING;
		} else if(Math.abs(velocityX) >= 6) {
			action = CHARGING;
			mass = 0;
		} else if(velocityY != 0) {
			action = HOPPING;
		}
		
		return action;
		
	}
	
	public Image getSprite(double time) {
		
		int frame = (int)((time % (SPRITE[getAction()].length * 0.6)) / 0.6);
		setSprite(SPRITE[getAction()][frame]);
		return sprite;
		
	}

}
