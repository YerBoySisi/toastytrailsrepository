package entity.enemy;

public class Knife extends Enemy {
	
	//constants
	public static final String NAME = "Knife";
	public static final int DAMAGE = 20;
	public static final int HP = 10;
	
	public Knife(int x, int y, double xVelocity, double yVelocity) {
		
		super(NAME, x, y, xVelocity, yVelocity, DAMAGE, HP);
		
	}
	
	
}
