package main;

import javafx.stage.*;
import javafx.util.Duration;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.application.*;
import javafx.event.EventHandler;

import java.io.File;
import java.util.HashMap;

import entity.enemy.Enemy;
import entity.player.Player;
import entity.player.Player.Form;
import gamestate.MenuState.GameMenu;
import gamestate.levels.IntroLevel;
import gamestate.levels.LevelState;
import gamestate.levels.LevelTwo;

public class GamePanel extends Application{
	
	public static final int MENU = 0; public static final int GAME = 1;
	private static int state;
	
	public static final int LEFT = -1; public static final int RIGHT = 1;
	public static final int GRAVITY = 5;
	
	public static final LevelState[] lvls = {new IntroLevel(), new LevelTwo()};
	public static int currentLvl = 0;
	
	public static Stage window;
	public static Scene menuscene;
	public static Scene gamescene;
	private static Canvas canvas;
	private static GraphicsContext gc;
	public static Player toasty;
	private static Camera cam;
	
	public static MediaPlayer mediaPlayer;
	
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
		
		Image img = new Image("file:Toasty Trails/Resources/BGs/kitchenwall.png");
		
		canvas = new Canvas(lvls[currentLvl].getLevelWidth(), 3840);
		Rectangle bg = new Rectangle(0, 0, Color.BLACK);
		bg.setX(-10000);
		bg.setY(-10000);
		bg.setWidth(99999);
		bg.setHeight(99999);
		gc = canvas.getGraphicsContext2D();
		
		Pane menuRoot = new Pane();
		menuRoot.setPrefSize(550, 450);
		Pane gameRoot = new Pane();
		gameRoot.setPrefSize(800, 600);
		
		menu = new GameMenu();
		menuRoot.getChildren().addAll(menu);
		gameRoot.getChildren().addAll(bg, canvas);
		
		menuscene = new Scene(menuRoot);
		gamescene = new Scene(gameRoot);
		
		
		toasty = new Player(Form.NORMAL, lvls[currentLvl].getInitialPlayerX(), lvls[currentLvl].getInitialPlayerY(), 
				 lvls[currentLvl].getInitialPlayerXVelocity(), lvls[currentLvl].getInitialPlayerYVelocity());
		
		for(Enemy e: lvls[currentLvl].getEnemies()) {
			e.aI();
		}
		
		cam = new PerspectiveCamera(true);
		cam.setTranslateZ(-600);
		cam.setFarClip(2000);
		cam.setScaleX(1);
		cam.setScaleY(1);
		gamescene.setCamera(cam);
		
		gamescene.setOnKeyPressed(e -> {
			keys.put(e.getCode(), true);
			controls();
		});
		
		gamescene.setOnKeyReleased(e -> {
			keys.put(e.getCode(), false);
			controls();
		});
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
		    public void handle(WindowEvent we) {
		        	 
		    	System.out.println("Stage is closing");
		    	System.exit(0);
		           
		    }
	         
	    });      
		
		window.setTitle("Toasty Trails");
		window.setScene(menuscene);
		
		final long startNanoTime = System.nanoTime();
		
		new AnimationTimer() {
        	
			public void handle(long currentNanoTime) {
            	
            	gc.clearRect(0, 0, lvls[currentLvl].getLevelWidth(), 3840);
                double t = (currentNanoTime - startNanoTime) / 120000000.0;
        		
                gc.drawImage(img, cam.getTranslateX() / 4 - 2000, cam.getTranslateY() / 4 - 1000);
                
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
        
        for(Enemy e: lvls[currentLvl].getEnemies()) {
	        e.moveY();
	        e.render(t, gc);
        }
        
        for(Enemy e: lvls[currentLvl].getEnemies()) {
	        e.render(t, gc);
        }

        //movement2();
        //enemyCollision();
        //bknife.render(t, gc);
        
        camera();
		
	}

	private boolean isPressed(KeyCode key) {
		
		return keys.getOrDefault(key, false);
		
	}
	
	public void controls() {
		
		if(!(isPressed(KeyCode.RIGHT) && isPressed(KeyCode.LEFT))) {
		
			if(toasty.getXVelocity() < 10 && isPressed(KeyCode.LEFT)) {
				toasty.walkingLeft = true;
			} else {
				toasty.walkingLeft = false;
			}
			
			if(toasty.getXVelocity() < 10 && isPressed(KeyCode.RIGHT)) {
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
    				
        			if(!lvls[currentLvl].map.get(row).get(col).invisible && toasty.colliding(lvls[currentLvl].map.get(row).get(col))) {
    					
        				if(!lvls[currentLvl].map.get(row).get(col).isPermeable() && 
    					   !toasty.onTopOf(lvls[currentLvl].map.get(row).get(col))) {
    						
    						//walking into left of block
	    					if(toasty.rightBoundary() - 5 <= lvls[currentLvl].map.get(row).get(col).leftBoundary() + toasty.getXVelocity() + 3) {
	    						
	    						if(toasty.getXVelocity() > 0) {
	    							toasty.setX(lvls[currentLvl].map.get(row).get(col).leftBoundary() - toasty.getWidth() - 1);
	    						} else {
	    							toasty.setX(lvls[currentLvl].map.get(row).get(col).leftBoundary() - toasty.getWidth() - 1);
	    						}
	    						
	    						toasty.setXVelocity(0);
	    					}
	    					
	    					//walking into right of block
	    					if(toasty.leftBoundary() + 5 >= lvls[currentLvl].map.get(row).get(col).rightBoundary() + toasty.getXVelocity()) {
	    						toasty.setX(lvls[currentLvl].map.get(row).get(col).rightBoundary());
	    						toasty.setXVelocity(0);
	    					}
    					
    					}
    					
    					//standing on top of block
    					if(toasty.bottomBoundary() <= lvls[currentLvl].map.get(row).get(col).topBoundary() + toasty.getYVelocity() + 3 &&
    	    					   toasty.rightBoundary() > lvls[currentLvl].map.get(row).get(col).leftBoundary() + 3 && 
    	    					   toasty.leftBoundary() < lvls[currentLvl].map.get(row).get(col).rightBoundary() - 3) {
    						toasty.setY(lvls[currentLvl].map.get(row).get(col).y() - toasty.getHeight());
    						toasty.setYVelocity(0);
    						toasty.standing = true;
    					} else {
        					toasty.standing = false;
        				}
    									
    					//jumping into bottom of block
    					if(!lvls[currentLvl].map.get(row).get(col).isPermeable() && 
    						toasty.topBoundary() >= lvls[currentLvl].map.get(row).get(col).bottomBoundary() + toasty.getYVelocity() &&
    					   toasty.rightBoundary() > lvls[currentLvl].map.get(row).get(col).leftBoundary() && 
    					   toasty.leftBoundary() < lvls[currentLvl].map.get(row).get(col).rightBoundary()) {
    						toasty.setY(lvls[currentLvl].map.get(row).get(col).y() + toasty.getHeight() - 12);
    						toasty.setYVelocity(toasty.getMass());
    					}
    					
    					lvls[currentLvl].map.get(row).get(col).performAction(toasty);
    					
    				}
    				
    			}
            	
    		}
        	
        	for(Enemy e: lvls[currentLvl].getEnemies()) {
        	
	        	if(toasty.colliding(e)) {
					
	        		if(e.isSolid()) {
	        		
						if(!toasty.onTopOf(e)) {
							
							//walking into left of block
							if(toasty.rightBoundary() - 5 <= e.leftBoundary() + toasty.getXVelocity() + 3) {
								
								if(toasty.getXVelocity() > 0) {
									toasty.setX(e.leftBoundary() - toasty.getWidth() - 1);
								} else {
									toasty.setX(e.leftBoundary() - toasty.getWidth() - 1);
								}
								
								toasty.setXVelocity(0);
							}
							
							//walking into right of block
							if(toasty.leftBoundary() + 5 >= e.rightBoundary() + toasty.getXVelocity()) {
								toasty.setX(e.rightBoundary());
								toasty.setXVelocity(0);
							}
						
						}
						
						//standing on top of block
						if(toasty.bottomBoundary() <= e.topBoundary() + toasty.getYVelocity() + 3 &&
		    					   toasty.rightBoundary() > e.leftBoundary() + 3 && 
		    					   toasty.leftBoundary() < e.rightBoundary() - 3) {
							toasty.setY(e.y() - toasty.getHeight());
							toasty.setYVelocity(0);
							toasty.standing = true;
						} else {
							toasty.standing = false;
						}
										
						//jumping into bottom of block
						if(toasty.topBoundary() >= e.bottomBoundary() + toasty.getYVelocity() &&
						   toasty.rightBoundary() > e.leftBoundary() && 
						   toasty.leftBoundary() < e.rightBoundary()) {
							toasty.setY(e.y() + toasty.getHeight() - 12);
							toasty.setYVelocity(toasty.getMass());
						}
						
	        		}
					
	        		if(!e.attacking()) {
	        			e.attack(toasty);
	        		}
					
				}
	        	
        	}
			
		}
		
		if(toasty.leftBoundary() < 0) {
			toasty.setX(0);
			toasty.setXVelocity(0);
		} else if(toasty.rightBoundary() > canvas.getWidth() - 167) {
			toasty.setX(canvas.getWidth() - 167 - toasty.getWidth());
			toasty.setXVelocity(0);
		}
		
	}
	
	public void camera() {
		
		if(toasty.x() >= Math.round(179 * (cam.getTranslateZ() / -500)) && 
		   toasty.x() < Math.round(canvas.getWidth() - 345 * (cam.getTranslateZ() / -500) + 33)) {
			cam.setTranslateX((int)toasty.x());
		} else {
			
			if(toasty.x() < Math.round(179 * (cam.getTranslateZ() / -500))) {
				cam.setTranslateX(Math.round(179 * (cam.getTranslateZ() / -500)));
			} else {
				cam.setTranslateX(canvas.getWidth() - 345 * (cam.getTranslateZ() / -500) + 33);
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
    		}
    		
    		break;
    		
    	}
		
	}
	
	public static void spawnPlayer() {
		
		toasty = new Player(Form.NORMAL, lvls[currentLvl].getInitialPlayerX(), lvls[currentLvl].getInitialPlayerY(), 
						 lvls[currentLvl].getInitialPlayerXVelocity(), lvls[currentLvl].getInitialPlayerYVelocity());
		
		lvls[currentLvl] = new IntroLevel();
		lvls[currentLvl].initializeEnemies();
		
	}
	
	
}
