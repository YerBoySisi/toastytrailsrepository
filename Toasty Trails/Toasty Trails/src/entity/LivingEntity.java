package entity;

public abstract class LivingEntity extends Entity {
	
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
	 * Sets 'alive' to false
	 */
	public void kill() {
		
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
	
}
