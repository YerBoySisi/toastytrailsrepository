package entity;

public abstract class Entity {
	
	//variables
	protected int maxHP;
	protected String name;
	protected int hp;
	protected double x;
	protected double y;
	
	/**
	 * Returns the Entity's name
	 * @return
	 */
	public String getName() {
		
		return name;
		
	}
	
	/**
	 * Sets the entity's name to the value of the parameter 'name'
	 * @param name
	 */
	public void setName(String name) {
		
		this.name = name;
		
	}
	
	
	/**
	 * Returns the Entity's maxHP
	 * @return
	 */
	public int getMaxHP() {
		
		return maxHP;
		
	}
	
	
	/**
	 * Returns the Entity's hp
	 * @return
	 */
	public int getHP() {
		
		return hp;
		
	}
	
	/**
	 * Sets the Entity's hp to hp + diff
	 * @param diff
	 */
	public void changeHP(int diff) {
		
		if(hp + diff < 0) {
			hp = 0;
		} else {
			hp += diff;
		}
		
	}
	
	/**
	 * Returns the Entity's x coordinate
	 * @return
	 */
	public double x() {
		
		return x;
		
	}
	
	/**
	 * Returns the Entity's y coordinate
	 * @return
	 */
	public double y() {
		
		return y;
		
	}
	
}
