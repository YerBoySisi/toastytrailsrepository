package entity.player;

import entity.LivingEntity;

public class Player extends LivingEntity {
	
	//constants
	public static final int MAX_HP = 100;
	
	public enum Form {
		NORMAL, TOASTED, SOGGY
	}
	
	//variables
	private Form form;
	
	public Player(Form form, int x, int y, double xVelocity, double yVelocity) {
		
		super(xVelocity, yVelocity);
		this.form = form;
		setStats();
		hp = maxHP;
		alive = true;
		this.x = x;
		this.y = y;
		
	}
	
	private void setStats() {
		
		maxHP = MAX_HP;
		
		switch(form) {
		
		case NORMAL :
			name = "Toasty";
			weight = 0.125;
			maxSpeed = 2;
			break;
		case TOASTED :
			name = "Burnt Toasty";
			weight = 0.100;
			maxSpeed = 3;
			break;
		case SOGGY :
			name = "Soggy Toasty";
			weight = 0.150;
			maxSpeed = 1;
			break;
			
		}
		
	}
	
	public void setForm(Form form) {
		
		this.form = form;
		
	}
	
	public Form getForm() {
		
		return form;
		
	}

}
