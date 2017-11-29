package entity;

public class LivingEntity extends Entity {
	
	private boolean alive;
	private int velocityX;
	private int velocityY;
	private int weight;
	private int maxSpeed;
	
	public LivingEntity(boolean alive, int velX, int velY, int wt, int spd) {
		
		this.alive = alive;
		velocityX = velX;
		velocityY = velY;
		weight = wt;
		maxSpeed = spd;
		
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
	
}
