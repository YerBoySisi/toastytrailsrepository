package entity.player;

import entity.LivingEntity;
import javafx.scene.image.Image;

public class Player extends LivingEntity {
	
	//constants
	//sprite file paths
	public final String FILE_PATH = "file:Resources/Sprites/Player/";
	public final String[] IDLE_SPRITE = {FILE_PATH + "toastyidle.png"};
	public final String[] RUN_SPRITE = {FILE_PATH + "toastyrun1.png", FILE_PATH + "toastyrun2.png",
							   FILE_PATH + "toastyrun3.png", FILE_PATH + "toastyrun4.png",
							   FILE_PATH + "toastyrun5.png", FILE_PATH + "toastyrun6.png"};
	public final String[] JUMP_SPRITE = {FILE_PATH + "toastyjump.png"};
	public final String[] FALL_SPRITE = {FILE_PATH + "toastyfall.png"};
	public final String[][] SPRITE = {IDLE_SPRITE, RUN_SPRITE, JUMP_SPRITE, FALL_SPRITE};
	
	//player actions
	public static final int STANDING = 0;
	public static final int RUNNING = 1;
	public static final int JUMPING = 2;
	public static final int FALLING = 3;
	
	public static final int MAX_HP = 100;
	
	
	public enum Form {
		NORMAL, TOASTED, SOGGY
	}
	
	//variables
	private Form form;
	
	public Player(Form form, int x, int y, double xVelocity, double yVelocity) {
		
		super(xVelocity, yVelocity);
		this.form = form;
		setStats();
		hp = maxHP;
		this.x = x;
		this.y = y;
		
	}
	
	private void setStats() {
		
		maxHP = MAX_HP;
		
		switch(form) {
		
		case NORMAL :
			name = "Toasty";
			weight = 0.125;
			maxSpeed = 2;
			break;
		case TOASTED :
			name = "Burnt Toasty";
			weight = 0.100;
			maxSpeed = 3;
			break;
		case SOGGY :
			name = "Soggy Toasty";
			weight = 0.150;
			maxSpeed = 1;
			break;
			
		}
		
	}
	
	public void setForm(Form form) {
		
		this.form = form;
		
	}
	
	public Form getForm() {
		
		return form;
		
	}
	
	public int getAction() {
		
		int action = 0;
		
		if(velocityX == 0 && velocityY == 0) {
			action = STANDING;
		} else if(velocityX != 0 && velocityY == 0) {
			action = RUNNING;
		} else if(velocityY < 0) {
			action = JUMPING;
		} else if(velocityY > 0) {
			action = FALLING;
		}
		
		return action;
		
	}
	
	public Image getSprite(double time) {
		
		int frame = (int)((time % (SPRITE[getAction()].length * 0.6)) / 0.6);
		setSprite(SPRITE[getAction()][frame]);
		return sprite;
		
	}

}
