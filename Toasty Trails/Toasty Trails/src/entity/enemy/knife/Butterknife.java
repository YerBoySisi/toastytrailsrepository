package entity.enemy.knife;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Butterknife extends Knife {
	
	//constants
	public static final int LEFT = -1;
	
	//sprite file paths
	public final String FILE_PATH = "file:Toasty Trails/Resources/Sprites/Enemies/Butterknife/";
	public final String[] IDLE_SPRITE = {FILE_PATH + "butterknifeidle.png"};
	public final String[] WALK_SPRITE = {FILE_PATH + "butterknifeidle.png"};
	public final String[] CHARGE_SPRITE = {FILE_PATH + "butterknifeidle.png"};
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
		} else if(velocityX > 0 && velocityX < 10) {
			action = WALKING;
		} else if(velocityX > 10) {
			action = CHARGING;
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

	public void render(double t, GraphicsContext gc) {

		
		if(!walkingLeft && !walkingRight) {

			if(lastDirection == LEFT) {
				gc.drawImage(getSprite(t), (int)x() + getWidth(), (int)y(), -getWidth(), getHeight());
			} else {
				gc.drawImage(getSprite(t), (int)x(), (int)y());
			}
			
		}
		
		if(walkingLeft) {
			gc.drawImage(getSprite(t), (int)x() + getWidth(), (int)y(), -getWidth(), getHeight());
		}
		
		if(walkingRight) {
			gc.drawImage(getSprite(t), (int)x(), (int)y());
		}
		
		
	}

}
