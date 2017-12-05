package main;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.application.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import entity.Block;
import entity.Entity;
import entity.player.Player;
import entity.player.Player.Form;
import gamestate.MenuState.GameMenu;

public class GamePanel extends Application{
	
	public static final int LEFT = -1; public static final int RIGHT = 1;
	public static final int GRAVITY = 5;
	
	private Stage window;
	private Scene menuscene, gamescene;
	private Canvas canvas;
	private GraphicsContext gc;
	private Player toasty;
	private Block[] blocks = new Block[4];
	
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	
	private GameMenu menu;
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
	
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		
		Rectangle bg = new Rectangle(550, 450);
		bg.setFill(Color.WHITE);
		canvas = new Canvas(550, 450);
		gc = canvas.getGraphicsContext2D();
		
		Pane menuRoot = new Pane();
		menuRoot.setPrefSize(550, 450);
		Pane gameRoot = new Pane();
		menuRoot.setPrefSize(550, 450);
		
		menu = new GameMenu();
		menuRoot.getChildren().addAll(menu, bg, canvas);
		
		menuscene = new Scene(menuRoot);
		gamescene = new Scene(gameRoot);
		
		toasty = new Player(Form.NORMAL, 250, 250, 0, 0);
		
		
		blocks[3] = new Block(250, 300);
		blocks[2] = new Block(214, 250);
		blocks[1] = new Block(214, 200);
		blocks[0] = new Block(286, 250);
		
		menuscene.setOnKeyPressed(e -> keys.put(e.getCode(), true));
		menuscene.setOnKeyReleased(e -> keys.put(e.getCode(), false));
		
		window.setTitle("Toasty Trails");
		window.setScene(menuscene);
		
		
		
		final long startNanoTime = System.nanoTime();
		
		new AnimationTimer() {
        	
            public void handle(long currentNanoTime) {
            	
            	gc.clearRect(0, 0, 550, 450);
                double t = (currentNanoTime - startNanoTime) / 120000000.0;
                
                
                movement();
                collision();
                controls();
                
                
                for(int i = 0; i < blocks.length; i++) {
                	gc.drawImage(blocks[i].getSprite(t), blocks[i].x(), blocks[i].y());
                }
                
                sprites(t);
                System.out.println(toasty.getYVelocity());
                
            }
            
        }.start();
        
		window.show();
		
	}

	private boolean isPressed(KeyCode key) {
		
		return keys.getOrDefault(key, false);
		
	}
	
	public void controls() {
		
		if(!(isPressed(KeyCode.RIGHT) && isPressed(KeyCode.LEFT))) {
		
			if(isPressed(KeyCode.LEFT)) {
				toasty.walkingLeft = true;
			} else {
				toasty.walkingLeft = false;
			}
			
			if(isPressed(KeyCode.RIGHT)) {
				toasty.walkingRight = true;
			} else {
				toasty.walkingRight = false;
			}
		
		} else {
			toasty.walkingLeft = false;
			toasty.walkingRight = false;
		}
		
		if(isPressed(KeyCode.D) && !toasty.jumping) {
			toasty.jump();
		}
		
	}
	
	
	public void movement() {
		
		if(!toasty.standing) {
			toasty.jumping = true;
		} else {
			toasty.jumping = false;
		}
		
		if(!toasty.walkingLeft && !toasty.walkingRight) {
			
			if(toasty.getXVelocity() > 0) {
				
				toasty.moveX(LEFT);
				
				if(toasty.getXVelocity() < 0) {
					toasty.setXVelocity(0);
				}
				
			}
			
			if(toasty.getXVelocity() < 0) {
				
				toasty.moveX(RIGHT);
				
				if(toasty.getXVelocity() > 0) {
					toasty.setXVelocity(0);
				}
				
			}
			
		}
		
		if(toasty.walkingLeft) {
			toasty.moveX(LEFT);
			toasty.lastDirection = LEFT;
		}
		
		if(toasty.walkingRight) {
			toasty.moveX(RIGHT);
			toasty.lastDirection = RIGHT;
		}
		
		if(toasty.standing) {
			toasty.jumping = false;
		} else {
			toasty.accerlateY(GRAVITY);
		}
		
		toasty.moveY();
		
		if(toasty.y() > canvas.getHeight() + 500) {
			toasty.setX(250);
			toasty.setY(-250);
			toasty.setYVelocity(0);
		}
		
	}
	
	public void collision() {
		
		for(Block block: blocks) {
		
			if(toasty.colliding(block)) {
				
				 if(!toasty.standingOn(block)) {
					 
					if(toasty.rightBoundary() <= block.leftBoundary() + toasty.getXVelocity()) {
						toasty.setX(block.leftBoundary() - toasty.getWidth());
						toasty.setXVelocity(0);
					}
							
					if(toasty.leftBoundary() >= block.rightBoundary() + toasty.getXVelocity()) {
						toasty.setX(block.rightBoundary());
						toasty.setXVelocity(0);
					}
					
				}
				
				if(toasty.rightBoundary() != block.leftBoundary() && toasty.leftBoundary() != block.rightBoundary()) {
					
					if(toasty.bottomBoundary() <= block.topBoundary() + toasty.getYVelocity()) {
						toasty.setY(block.y() - toasty.getHeight());
						toasty.setYVelocity(0);
						toasty.standing = true;
					}
								
					if(toasty.topBoundary() >= block.bottomBoundary() + toasty.getYVelocity()) {
						toasty.setY(block.y() + toasty.getHeight());
						toasty.setYVelocity(toasty.getMass());
					}
					
				}
				
			} else {
				toasty.standing = false;
			}
		
		}
		
	}
	
	public void sprites(double t) {
		
		if(!toasty.walkingLeft && !toasty.walkingRight) {

			if(toasty.lastDirection == LEFT) {
				gc.drawImage(toasty.getSprite(t), (int)toasty.x() + toasty.getWidth(), (int)toasty.y(), -toasty.getWidth(), toasty.getHeight());
			} else {
				gc.drawImage(toasty.getSprite(t), (int)toasty.x(), (int)toasty.y());
			}
			
		}
		
		if(toasty.walkingLeft) {
			gc.drawImage(toasty.getSprite(t), (int)toasty.x() + toasty.getWidth(), (int)toasty.y(), -toasty.getWidth(), toasty.getHeight());
		}
		
		if(toasty.walkingRight) {
			gc.drawImage(toasty.getSprite(t), (int)toasty.x(), (int)toasty.y());
		}
		
		
	}
	
}
