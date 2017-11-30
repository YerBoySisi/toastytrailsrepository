package main;

import entity.player.Player;
import entity.player.Player.Form;

public class Game {

	public static void main(String[] args) {
		
		Player toasty = new Player(Form.NORMAL, 300, -30, 0, 0);
		System.out.println("Name: " + toasty.getName());
		System.out.println("Form: " + toasty.getForm());
		System.out.println("HP: " + toasty.getHP() + " / " + toasty.getMaxHP());
		System.out.println("Alive: " + toasty.isAlive());
		System.out.println("Coordinates: (" + toasty.x() + ", " + toasty.y() + ")");
		System.out.println("X Velocity: " + toasty.getXVelocity());
		System.out.println("Y Velocity: " + toasty.getYVelocity());
		System.out.println("Maximum Speed: " + toasty.getMaxSpeed());
		System.out.println("Weight: " + toasty.getWeight());
		
	}

}
