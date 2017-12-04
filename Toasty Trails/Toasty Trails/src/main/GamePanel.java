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

import entity.Block;
import entity.player.Player;
import entity.player.Player.Form;
import gamestate.MenuState.GameMenu;

public class GamePanel extends Application {
	
	public static final int LEFT = -1; public static final int RIGHT = 1;
	public static final int GRAVITY = 5;
	
	private Stage window;
	private Scene menuscene, gamescene;
	private Canvas canvas;
	private GraphicsContext gc;
	private Player toasty;
	private Block[] blocks = new Block[10];
	
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
		
		toasty = new Player(Form.NORMAL, 250, 200, 0, 0);
		
		for(int i = 0; i < blocks.length; i++) {
			blocks[i] = new Block(32 * i + 120, 300);
		}
		
		menuscene.setOnKeyPressed(e -> keys.put(e.getCode(), true));
		menuscene.setOnKeyReleased(e -> keys.put(e.getCode(), false));
		
		window.setTitle("Toasty Trails");
		window.setScene(menuscene);
		
		final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
        	
            public void handle(long currentNanoTime) {
            	
            	gc.clearRect(0, 0, 550, 450);
                double t = (currentNanoTime - startNanoTime) / 120000000.0; 
                
                controls();
                movement();
                collision();
                
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
			toasty.setYVelocity(0);
			toasty.jumping = false;
		} else {
			toasty.accerlateY(GRAVITY);
		}
		
		toasty.moveY();
		
		if(toasty.y() > canvas.getHeight() + 500) {
			toasty.setX(250);
			toasty.setY(250);
			toasty.setYVelocity(0);
		}
		
	}
	
	public void collision() {
		
		if(toasty.colliding(blocks[0]) || toasty.colliding(blocks[1]) || toasty.colliding(blocks[2]) || toasty.colliding(blocks[3]) || toasty.colliding(blocks[4]) || toasty.colliding(blocks[5]) || toasty.colliding(blocks[6]) || toasty.colliding(blocks[7]) || toasty.colliding(blocks[8]) || toasty.colliding(blocks[9])) {
			toasty.standing = true;
		} else {
			toasty.standing = false;
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
