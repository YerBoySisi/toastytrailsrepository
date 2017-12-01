package entity.enemy;

public class Bird extends Enemy {
	
	//constants
	public static final String NAME = "Bird";
	public static final int DAMAGE = 10;
	public static final int HP = 5;
	
	public Bird(int x, int y, double xVelocity, double yVelocity) {
		
		super(NAME, x, y, xVelocity, yVelocity, DAMAGE, HP);
		
	}
	
}
