package entity.enemy.mold;

import entity.Damager;
import entity.Entity;
import entity.enemy.Enemy;

public class Mold extends Enemy implements Damager {

	public Mold(int x, int y, double xVelocity, double yVelocity) {
		
		super("Mold", x, y, xVelocity, yVelocity, 0, 0);
		
	}

	@Override
	public void aI() {
		

	}

	@Override
	public boolean inVision(Entity e) {
		
		return false;
		
	}

}
