package entity;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public abstract class Entity {
	
	//variables
	protected Image sprite;
	protected int maxHP;
	protected String name;
	protected int hp;
	protected double x;
	protected double y;
	protected double velocityX;
	protected double velocityY; 
	protected double weight; //the acceleration of the Entity falling from the air
	protected double width;
	protected double height;
	
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
	
	/**
	 * Returns the velocityX of the Entity
	 * @return
	 */
	public double getXVelocity() {
		
		return velocityX;
		
	}
	
	/**
	 * Returns the velocityY of the Entity
	 * @return
	 */
	public double getYVelocity() {
		
		return velocityY;
		
	}
	
	/**
	 * Returns the weight of the Entity
	 * @return
	 */
	public double getWeight() {
		
		return weight;
		
	}
	
	
	/**
	 * Returns the Entity's name
	 * NOTE: Whatever is returned here is what is displayed when printed
	 * @override toString
	 */
	public String toString() {
		
		return name;
		
	}
	
	
	/**
	 * Changes current x and y position based on current x and y velocity and refresh time 
	 * @param time
	 */
	public void update(double time) {
		
		x += velocityX * time;
		y += velocityY * time;
		
	}
	
	
	/**
	 * Draws Entity on canvas at position (x, y)
	 * @param gc
	 */
	public void render(GraphicsContext gc) {
		
		gc.drawImage(sprite, x, y);
		
	}
	
	
	/**
	 * Gets a rectangle of the boundaries of the Entity
	 * @return
	 */
	public Rectangle2D getBoundary() {
		
		return new Rectangle2D(x, y, width, height);
		
	}
	
	
	/**
	 * Returns true if this Entity's rectangle intersects Entity e's rectangle
	 * Returns false otherwise
	 * @param e
	 * @return
	 */
	public boolean colliding(Entity e) {
		
		return e.getBoundary().intersects(this.getBoundary());
		
	}
	
	
	/**
	 * Sets the sprite to an image with the file path of parameter path
	 * @param path
	 */
	public void setSprite(String path) {
		
		sprite = new Image(path);
		
	}
	
}
