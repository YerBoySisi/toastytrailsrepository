package main;

import entity.player.Player;
import entity.player.Player.Form;
import gamestate.LevelOne;

public class TestGame {

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
		System.out.println("Weight: " + toasty.getMass());
		
		System.out.println();
		
		LevelOne lv1 = new LevelOne();
		System.out.println(lv1.getName());
		System.out.println("Gravity: " + lv1.getGravity());
		System.out.println("Enemies: " + lv1.getEnemies());
		
		System.out.println();
		lv1.getEnemies().get(0).attack(toasty);
		System.out.println("Ouch! A " + lv1.getEnemies().get(0) + " attacked " + toasty.getName() + " for " + lv1.getEnemies().get(0).getDamage() + " damage!");
		System.out.println();
		System.out.println("Name: " + toasty.getName());
		System.out.println("Form: " + toasty.getForm());
		System.out.println("HP: " + toasty.getHP() + " / " + toasty.getMaxHP());
		System.out.println("Alive: " + toasty.isAlive());
		System.out.println("Coordinates: (" + toasty.x() + ", " + toasty.y() + ")");
		System.out.println("X Velocity: " + toasty.getXVelocity());
		System.out.println("Y Velocity: " + toasty.getYVelocity());
		System.out.println("Maximum Speed: " + toasty.getMaxSpeed());
		System.out.println("Weight: " + toasty.getMass());
		
		System.out.println();
		System.out.println(lv1.getMap().get(0));
		System.out.println(lv1.getMap().get(1));
		System.out.println(lv1.getMap().get(2));
		System.out.println(lv1.getMap().get(3));
		System.out.println(lv1.getMap().get(4));
		System.out.println(lv1.getMap().get(5));
		System.out.println(lv1.getMap().get(6));
		
	}

}
