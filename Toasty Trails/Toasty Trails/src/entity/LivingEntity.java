package entity;

import javafx.scene.canvas.GraphicsContext;

public abstract class LivingEntity extends Entity {
	
	private static final int LEFT = -1;
	
	protected boolean alive = true;
	protected double maxSpeed; //the maximum X velocity the LivingEntity can attain by moving on its own
	protected double maxJumpHeight; //the maximum Y velocity the LivingEntity can attain by jumping on its own
	protected double accerlationY;
	public boolean standing; //determines whether or not the LivingEntity is standing on ground
	public int lastDirection;
	
	/**
	 * Constructor
	 * Sets the velocityX and velocityY of the LivingEntity
	 * @param velocityX
	 * @param velocityY
	 */
	protected LivingEntity(double velocityX, double velocityY) {
		
		this.velocityX = velocityX;
		this.velocityY = velocityY;
		
	}
	
	/**
	 * Returns true if the Entity is alive
	 * Returns false if the Entity is not alive
	 * @return
	 */
	public boolean isAlive() {
		
		return alive;
		
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
	 * Sets 'alive' to false
	 */
	public void die() {
		
		alive = false;
		
	}
	
	/**
	 * Returns the maxSpeed of the LivingEntity
	 * @return
	 */
	public double getMaxSpeed() {
		
		return maxSpeed;
		
	}
	
	public void accerlateY(double g) {
		
		velocityY += mass * g;
	
	}
	
	@Override
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
