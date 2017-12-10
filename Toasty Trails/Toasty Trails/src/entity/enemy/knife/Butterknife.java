package entity.enemy.knife;

import javafx.scene.image.Image;

public class Butterknife extends Knife {
	
	//constants
	//sprite file paths
	public final String FILE_PATH = "file:Resources/Sprites/Enemies/Butterknife";
	public final String[] IDLE_SPRITE = {FILE_PATH + "butterknifeidle.png"};
	public final String[][] SPRITE = {IDLE_SPRITE};
	
	//actions
	private static final int STANDING = 0;
	
	public Butterknife(int x, int y, double xVelocity, double yVelocity) {
		
		super(x, y, xVelocity, yVelocity);
		
	}
	
	public int getAction() {
		
		int action = 0;
		
		if(velocityX == 0 && velocityY == 0) {
			action = STANDING;
		}
		
		return action;
		
	}
	
	public Image getSprite(double time) {
		
		int frame = (int)((time % (SPRITE[getAction()].length * 0.6)) / 0.6);
		setSprite(SPRITE[getAction()][frame]);
		return sprite;
		
	}


}
