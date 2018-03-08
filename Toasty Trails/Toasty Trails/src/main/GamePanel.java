package main;

import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.application.*;

import java.io.File;
import java.net.URL;
import java.util.HashMap;

import entity.enemy.knife.Butterknife;
import entity.player.Player;
import entity.player.Player.Form;
import gamestate.LevelOne;
import gamestate.LevelState;
import gamestate.MenuState.GameMenu;

public class GamePanel extends Application{
	
	public static final int MENU = 0; public static final int GAME = 1;
	private static int state;
	
	public static final int LEFT = -1; public static final int RIGHT = 1;
	public static final int GRAVITY = 5;
	
	public static final LevelState[] lvls = {new LevelOne()};
	public static int currentLvl = 0;
	
	public static Stage window;
	public static Scene menuscene;
	public static Scene gamescene;
	private static Canvas canvas;
	private static GraphicsContext gc;
	public static Player toasty;
	private static Butterknife bknife;
	private static Camera cam;
	
	public static MediaPlayer mediaPlayer;
	public static MediaPlayer mp;
	public static Media hit;
	
	private static HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	
	private static GameMenu menu;
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
	
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		
		Media bgm = new Media(new File("Toasty Trails/Resources/Music/bgm.mp3").toURI().toString());
		mediaPlayer = new MediaPlayer(bgm);
		mediaPlayer.setCycleCount((int)Duration.INDEFINITE.toSeconds());
		
		//URL url = getClass().getResource("Toasty Trails/Resources/Sounds/hit.mp3");
		//hit = new AudioClip(url.toString());
		
		Rectangle bg = new Rectangle(lvls[currentLvl].getLevelWidth(), 450);
		bg.setFill(Color.CYAN);
		canvas = new Canvas(lvls[currentLvl].getLevelWidth(), 500);
		gc = canvas.getGraphicsContext2D();
		
		Pane menuRoot = new Pane();
		menuRoot.setPrefSize(500, 450);
		Pane gameRoot = new Pane();
		gameRoot.setPrefSize(500, 450);
		
		menu = new GameMenu();
		menuRoot.getChildren().addAll(menu);
		gameRoot.getChildren().addAll(bg, canvas);
		
		menuscene = new Scene(menuRoot);
		gamescene = new Scene(gameRoot);
		
		toasty = new Player(Form.NORMAL, -999, -999, 0, 0);
		bknife = new Butterknife(72, 0, 0, 0);
		
		cam = new PerspectiveCamera(true);
		cam.setTranslateZ(-220);
		cam.setFarClip(500);
		cam.setScaleX(2.5);
		cam.setScaleY(2.5);
		gamescene.setCamera(cam);
		
		gamescene.setOnKeyPressed(e -> {
			keys.put(e.getCode(), true);
			controls();
		});
		
		gamescene.setOnKeyReleased(e -> {
			keys.put(e.getCode(), false);
			controls();
		});
		
		window.setTitle("Toasty Trails");
		window.setScene(menuscene);
		
		final long startNanoTime = System.nanoTime();
		
		new AnimationTimer() {
        	
            public void handle(long currentNanoTime) {
            	
            	gc.clearRect(0, 0, lvls[currentLvl].getLevelWidth(), 450);
                double t = (currentNanoTime - startNanoTime) / 120000000.0;
                
                for(int row = 0; row < lvls[currentLvl].map.size(); row++) {
        			
                	for(int col = 0; col < lvls[currentLvl].map.get(row).size(); col++) {
            			
                		if(lvls[currentLvl].map.get(row).get(col) != null) {
                			lvls[currentLvl].map.get(row).get(col).render(gc);
                		}
                    	
            		}
                	
        		}
                
                updateGame(t);
                
            }
            
        }.start();
        
		window.show();
		
	}
	
	private void updateGame(double t) {
		
		 movement();
         collision();
         toasty.render(t, gc);
         movement2();
         enemyCollision();
         bknife.render(t, gc);
         camera();
		
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
		
		toasty.accerlateY(GRAVITY);
		
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
		
		if(toasty.y() > canvas.getHeight() + 500 || toasty.getHP() == 0) {
			spawnPlayer();
		}
		
	}
	
	public void collision() {
		
		for(int row = 0; row < lvls[currentLvl].map.size(); row++) {
			
        	for(int col = 0; col < lvls[currentLvl].map.get(row).size(); col++) {
    			
        		if(lvls[currentLvl].map.get(row).get(col) != null) {
    				
        			if(toasty.colliding(lvls[currentLvl].map.get(row).get(col))) {
    					
    					if(!toasty.onTopOf(lvls[currentLvl].map.get(row).get(col))) {
    						
    						//walking into left of block
	    					if(toasty.rightBoundary() - 5 <= lvls[currentLvl].map.get(row).get(col).leftBoundary() + toasty.getXVelocity()) {
	    						toasty.setX(lvls[currentLvl].map.get(row).get(col).leftBoundary() - toasty.getWidth());
	    						toasty.setXVelocity(0);
	    					}
	    					
	    					//walking into right of block
	    					if(toasty.leftBoundary() + 5 >= lvls[currentLvl].map.get(row).get(col).rightBoundary() + toasty.getXVelocity()) {
	    						toasty.setX(lvls[currentLvl].map.get(row).get(col).rightBoundary());
	    						toasty.setXVelocity(0);
	    					}
    					
    					}
    					
    					//standing on top of block
    					if(toasty.bottomBoundary() <= lvls[currentLvl].map.get(row).get(col).topBoundary() + toasty.getYVelocity() &&
    	    					   toasty.rightBoundary() > lvls[currentLvl].map.get(row).get(col).leftBoundary() + 3 && 
    	    					   toasty.leftBoundary() < lvls[currentLvl].map.get(row).get(col).rightBoundary() - 3) {
    						toasty.setY(lvls[currentLvl].map.get(row).get(col).y() - toasty.getHeight());
    						toasty.setYVelocity(0);
    						toasty.standing = true;
    					}
    									
    					//jumping into bottom of block
    					if(toasty.topBoundary() >= lvls[currentLvl].map.get(row).get(col).bottomBoundary() + toasty.getYVelocity() &&
    					   toasty.rightBoundary() > lvls[currentLvl].map.get(row).get(col).leftBoundary() && 
    					   toasty.leftBoundary() < lvls[currentLvl].map.get(row).get(col).rightBoundary()) {
    						toasty.setY(lvls[currentLvl].map.get(row).get(col).y() + toasty.getHeight() - 12);
    						toasty.setYVelocity(toasty.getMass());
    					}
    					
    				} else {
    					toasty.standing = false;
    				}
    				
    			}
            	
    		}
        	
		}
		
		if(toasty.leftBoundary() < 0) {
			toasty.setX(0);
			toasty.setXVelocity(0);
		} else if(toasty.rightBoundary() > canvas.getWidth()) {
			toasty.setX(canvas.getWidth() - toasty.getWidth());
			toasty.setXVelocity(0);
		}
		
	}
	
	public void camera() {
		
		if(toasty.x() >= 164 && toasty.x() < canvas.getWidth() - 164) {
			cam.setTranslateX((int)toasty.x());
		} else {
			
			if(toasty.x() < 164) {
				cam.setTranslateX(164);
			} else {
				cam.setTranslateX(canvas.getWidth() - 164);
			}
			
		}
		
		if(toasty.y() < canvas.getHeight() - 196 && toasty.y() >= 148) {
			cam.setTranslateY((int)toasty.y());
		} else {
			
			if(toasty.y() < 148) {
				cam.setTranslateY(148);
			} else {
				cam.setTranslateY(canvas.getHeight() - 198);
			}
			
		}
		
	}
	
	public static void setState(int n) {
		
		state = n;
		
		switch(state) {
    	
    	case MENU:
    		
    		if(!window.getScene().equals(menuscene)) {
    			window.setScene(menuscene);
    		}
    		
    		break;
    	case GAME:
    		
    		if(!window.getScene().equals(gamescene)) {
    			window.setTitle(lvls[currentLvl].getName());
    			window.setScene(gamescene);
    			mediaPlayer.setRate(1.13);
    			mediaPlayer.play();
    			spawnPlayer();
    			bknife = new Butterknife(400, 0, 0, 0);
    			bknife.aI();
    			//bknife.chargeAttack(LEFT);
    		}
    		
    		break;
    		
    	}
		
	}
	
	public static void spawnPlayer() {
		
		toasty = new Player(Form.NORMAL, lvls[currentLvl].getInitialPlayerX(), lvls[currentLvl].getInitialPlayerY(), 
						 lvls[currentLvl].getInitialPlayerXVelocity(), lvls[currentLvl].getInitialPlayerYVelocity());
		
	}
	
	public void movement2() {
		
		bknife.accerlateY(GRAVITY);
		bknife.moveY();
		
		if(bknife.getXVelocity() < 0) {
			bknife.lastDirection = LEFT;
		}
		
		if(bknife.getXVelocity() > 0) {
			bknife.lastDirection = RIGHT;
		}
		
		bknife.moveX();
		
	}
	
	public void enemyCollision() {
		
		for(int row = 0; row < lvls[currentLvl].map.size(); row++) {
			
        	for(int col = 0; col < lvls[currentLvl].map.get(row).size(); col++) {
    			
        		if(lvls[currentLvl].map.get(row).get(col) != null) {
    				
        			if(bknife.colliding(lvls[currentLvl].map.get(row).get(col))) {
    					
    					if(!bknife.onTopOf(lvls[currentLvl].map.get(row).get(col))) {
    						
    						//walking into left of block
	    					if(bknife.rightBoundary() <= lvls[currentLvl].map.get(row).get(col).leftBoundary() + bknife.getXVelocity()) {
	    						bknife.setX(lvls[currentLvl].map.get(row).get(col).leftBoundary() - bknife.getWidth());
	    						bknife.setXVelocity(0);
	    					}
	    					
	    					//walking into right of block
	    					if(bknife.leftBoundary() >= lvls[currentLvl].map.get(row).get(col).rightBoundary() + bknife.getXVelocity()) {
	    						bknife.setX(lvls[currentLvl].map.get(row).get(col).rightBoundary());
	    						bknife.setXVelocity(0);
	    					}
    					
    					}
    					
    					//standing on top of block
    					if(bknife.bottomBoundary() <= lvls[currentLvl].map.get(row).get(col).topBoundary() + bknife.getYVelocity() &&
    	    					   bknife.rightBoundary() > lvls[currentLvl].map.get(row).get(col).leftBoundary() && 
    	    					   bknife.leftBoundary() < lvls[currentLvl].map.get(row).get(col).rightBoundary()) {
    						bknife.setY(lvls[currentLvl].map.get(row).get(col).y() - bknife.getHeight());
    						bknife.setYVelocity(0);
    						bknife.standing = true;
    					}
    									
    					//jumping into bottom of block
    					if(bknife.topBoundary() >= lvls[currentLvl].map.get(row).get(col).bottomBoundary() + bknife.getYVelocity() &&
    					   bknife.rightBoundary() < lvls[currentLvl].map.get(row).get(col).leftBoundary() || 
    					   bknife.leftBoundary() > lvls[currentLvl].map.get(row).get(col).rightBoundary()) {
    						bknife.setY(lvls[currentLvl].map.get(row).get(col).y() + bknife.getHeight() - 12);
    						bknife.setYVelocity(bknife.getMass());
    					}
    					
    				} else {
    					bknife.standing = false;
    				}
    				
    			}
            	
    		}
        	
		}
		
		if(bknife.colliding(toasty)) {
			
			if(!toasty.invincible) {
				playSoundFromURL("Toasty Trails/Resources/Sounds/hit.mp3");
				bknife.attack(toasty);
			}
			
		}
		
	}
	
	public static void playSoundFromURL(String path) {
		
		hit = new Media(new File(path).toURI().toString());
		mp = new MediaPlayer(hit);
		mp.play();
		
	}
	
}
