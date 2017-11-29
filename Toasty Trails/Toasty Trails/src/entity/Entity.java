package entity;

public class Entity {
	
	private String name;
	private int hp;
	
	public Entity() {
		
	}
	
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
	
}
