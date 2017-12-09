package entity.player;

import entity.Entity;
import entity.LivingEntity;
import javafx.geometry.Rectangle2D;
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
	public static final double JUMP_HEIGHT = 11.5; //maximum jump velocity achievable by Player
	public static final double X_ELERATION = 0.22; //acceleration of Player when moving on own
	public static final int LEFT_STANDING_BOUND = 3;
	public static final int RIGHT_STANDING_BOUND = 26;
	
	
	public enum Form {
		NORMAL, TOASTED, SOGGY
	}
	
	//variables
	private Form form;
	private double accelerationX;
	
	public Player(Form form, int x, int y, double xVelocity, double yVelocity) {
		
		super(xVelocity, yVelocity);
		this.form = form;
		accelerationX = X_ELERATION;
		maxJumpHeight = JUMP_HEIGHT;
		setStats();
		hp = maxHP;
		this.x = x;
		this.y = y;
		
	}
	
	public void accelerateX(int direction) {
		
		velocityX += direction * accelerationX;
		
	}
	
	public void jump() {
		
		velocityY += -maxJumpHeight;
		
	}
	
	public void moveX(int direction) {
		
		if(direction > 0) {
			
			if(velocityX <= maxSpeed) {
				accelerateX(direction);
			} else {
				velocityX = maxSpeed;
			}
			
		}
		
		if(direction < 0) {
			
			if(velocityX >= -maxSpeed) {
				accelerateX(direction);
			} else {
				velocityX = -maxSpeed;
			}
			
		}
		
		x += velocityX;
		
	}
	
	private void setStats() {
		
		maxHP = MAX_HP;
		
		switch(form) {
		
		case NORMAL :
			name = "Mr. Rye";
			mass = 0.125;
			maxSpeed = 3;
			break;
		case TOASTED :
			name = "Toasty";
			mass = 0.100;
			maxSpeed = 4;
			break;
		case SOGGY :
			name = "Soggy";
			mass = 0.150;
			maxSpeed = 2;
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
	
	/**
	 * Returns true if the bottom boundary of Player is the same as the top boundary of Block
	 * Returns false otherwise
	 * @param e
	 * @return
	 */
	public boolean onTopOf(Entity e) {
		
		return (int)bottomBoundary() == (int)e.topBoundary();
		
	}
	
	
	/**
	 * Gets a rectangle of the boundaries of the Entity
	 * @return
	 */
	public Rectangle2D getStandingBoundary() {
		
		return new Rectangle2D(x + LEFT_STANDING_BOUND, y, RIGHT_STANDING_BOUND, height);
		
	}

}
