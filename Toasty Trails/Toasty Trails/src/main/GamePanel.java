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

import java.util.HashMap;

import entity.player.Player;
import entity.player.Player.Form;
import gamestate.LevelOne;
import gamestate.MenuState.GameMenu;

public class GamePanel extends Application{
	
	public static final int LEFT = -1; public static final int RIGHT = 1;
	public static final int GRAVITY = 5;
	
	private Stage window;
	private Scene menuscene, gamescene;
	private Canvas canvas;
	private GraphicsContext gc;
	private Player toasty;
	private LevelOne lv1;
	
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
		
		lv1 = new LevelOne();
		
		menuscene.setOnKeyPressed(e -> {
			keys.put(e.getCode(), true);
			controls();
		});
		
		menuscene.setOnKeyReleased(e -> {
			keys.put(e.getCode(), false);
			controls();
		});
		
		window.setTitle("Toasty Trails");
		window.setScene(menuscene);
		
		
		final long startNanoTime = System.nanoTime();
		
		new AnimationTimer() {
        	
            public void handle(long currentNanoTime) {
            	
            	gc.clearRect(0, 0, 550, 450);
                double t = (currentNanoTime - startNanoTime) / 120000000.0;
                
                movement();
                collision();
                
                
                for(int row = 0; row < lv1.map.size(); row++) {
        			
                	for(int col = 0; col < lv1.map.get(row).size(); col++) {
            			
                		if(lv1.map.get(row).get(col) != null) {
                			gc.drawImage(lv1.map.get(row).get(col).getSprite(t), lv1.map.get(row).get(col).x(), lv1.map.get(row).get(col).y());
                		}
                    	
            		}
                	
        		}
                
                sprites(t);
                
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
		
		if(isPressed(KeyCode.D) && toasty.getYVelocity() == 0) {
			toasty.jump();
		}
		
	}
	
	
	public void movement() {
		
		if(!toasty.standing) {
			toasty.accerlateY(GRAVITY);
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
		
		toasty.moveY();
		
		if(toasty.y() > canvas.getHeight() + 500) {
			toasty.setX(250);
			toasty.setY(-250);
			toasty.setYVelocity(0);
		}
		
	}
	
	public void collision() {
		
		for(int row = 0; row < lv1.map.size(); row++) {
			
        	for(int col = 0; col < lv1.map.get(row).size(); col++) {
    			
        		if(lv1.map.get(row).get(col) != null) {
    				
        			if(toasty.colliding(lv1.map.get(row).get(col))) {
    					
    					if(!toasty.onTopOf(lv1.map.get(row).get(col))) {
    						
	    					if(toasty.rightBoundary() <= lv1.map.get(row).get(col).leftBoundary() + toasty.getXVelocity()) {
	    						toasty.setX(lv1.map.get(row).get(col).leftBoundary() - toasty.getWidth());
	    						toasty.setXVelocity(0);
	    					}
	    						
	    					if(toasty.leftBoundary() >= lv1.map.get(row).get(col).rightBoundary() + toasty.getXVelocity()) {
	    						toasty.setX(lv1.map.get(row).get(col).rightBoundary());
	    						toasty.setXVelocity(0);
	    					}
    					
    					}
    						
    					if(toasty.bottomBoundary() <= lv1.map.get(row).get(col).topBoundary() + toasty.getYVelocity()) {
    						toasty.setY(lv1.map.get(row).get(col).y() - toasty.getHeight());
    						toasty.setYVelocity(0);
    						toasty.standing = true;
    					}
    									
    					if(toasty.topBoundary() >= lv1.map.get(row).get(col).bottomBoundary() + toasty.getYVelocity()) {
    						toasty.setY(lv1.map.get(row).get(col).y() + toasty.getHeight());
    						toasty.setYVelocity(toasty.getMass());
    					}
    					
    				} else {
    					toasty.standing = false;
    				}
    				
    			}
            	
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
