package entity;

public abstract class LivingEntity extends Entity {
	
	protected boolean alive = true;
	protected double velocityX;
	protected double velocityY;
	protected double weight;
	protected double maxSpeed;
	
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
	
	public double getXVelocity() {
		
		return velocityX;
		
	}
	
	public double getYVelocity() {
		
		return velocityY;
		
	}
	
	public double getWeight() {
		
		return weight;
		
	}
	
	public double getMaxSpeed() {
		
		return maxSpeed;
		
	}
	
}
