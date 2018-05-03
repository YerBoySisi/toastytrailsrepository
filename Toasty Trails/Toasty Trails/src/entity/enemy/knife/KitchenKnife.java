package entity.enemy.knife;

import javafx.scene.image.Image;

public class KitchenKnife extends Knife {
	
	//constants
	public static final int LEFT = -1;
	public static final int DAMAGE = 20;
	public static final int CHARGE_SPEED = 8;
	
	//sprite file paths
	public final String FILE_PATH = "file:Toasty Trails/Resources/Sprites/Enemies/Kitchen Knife/";
	public final String[] IDLE_SPRITE = {FILE_PATH + "kitchenknifeidle.png"};
	public final String[] WALK_SPRITE = {FILE_PATH + "kitchenknifewalk1.png", FILE_PATH + "kitchenknifewalk2.png",
										 FILE_PATH + "kitchenknifewalk3.png", FILE_PATH + "kitchenknifewalk4.png"};
	public final String[] CHARGE_SPRITE = {FILE_PATH + "kitchenknifecharge.png"};
	public final String[] HOP_SPRITE = {FILE_PATH + "kitchenknifejump.png"};
	public final String[][] SPRITE = {IDLE_SPRITE, WALK_SPRITE, CHARGE_SPRITE, HOP_SPRITE};
	
	//actions
	private static final int STANDING = 0;
	private static final int WALKING = 1;
	private static final int CHARGING = 2;
	private static final int HOPPING = 3;
	
	public KitchenKnife(int x, int y, double xVelocity, double yVelocity) {
		
		super(x, y, xVelocity, yVelocity, DAMAGE, CHARGE_SPEED);
		mass = 0.12;
		
	}
	
	public int getAction() {
		
		int action = 0;
		
		if(velocityX == 0 && velocityY == 0) {
			action = STANDING;
			setDamage(20);
		} else if(Math.abs(velocityX) < 8) {
			action = WALKING;
			setDamage(20);
		} else if(Math.abs(velocityX) >= 8) {
			action = CHARGING;
			setDamage(50);
			mass = 0;
		} else if(velocityY != 0) {
			action = HOPPING;
			setDamage(20);
		}
		
		return action;
		
	}
	
	public Image getSprite(double time) {
		
		int frame = (int)((time % (SPRITE[getAction()].length * 0.6)) / 0.6);
		setSprite(SPRITE[getAction()][frame]);
		return sprite;
		
	}

}
