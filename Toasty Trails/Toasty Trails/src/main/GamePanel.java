package main;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.animation.AnimationTimer;
import javafx.application.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import entity.player.Player;
import entity.player.Player.Form;
import gamestate.MenuState.GameMenu;

public class GamePanel extends Application {
	
	private Stage window;
	private Scene menuscene, gamescene;
	private Canvas canvas;
	private GraphicsContext gc;
	
	private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();
	
	private GameMenu menu;
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
	
	public void start(Stage primaryStage) throws Exception {
		
		window = primaryStage;
		
		canvas = new Canvas(550, 450);
		gc = canvas.getGraphicsContext2D();
		
		Pane menuRoot = new Pane();
		menuRoot.setPrefSize(550, 450);
		
		menu = new GameMenu();
		menuRoot.getChildren().addAll(menu);
		
		menuscene = new Scene(menuRoot);
		
		Player toasty = new Player(Form.NORMAL, 250, 200, 0, 0);
		
		menuscene.setOnKeyPressed(e -> keys.put(e.getCode(), true));
		menuscene.setOnKeyReleased(e -> keys.put(e.getCode(), false));
		
		window.setTitle("Toasty Trails");
		window.setScene(menuscene);
		
		final long startNanoTime = System.nanoTime();

        new AnimationTimer() {
        	
            public void handle(long currentNanoTime) {
            	
            	gc.clearRect(0, 0, 550, 450);
                double t = (currentNanoTime - startNanoTime) / 120000000.0; 
                gc.drawImage(toasty.getSprite(t), toasty.x(), toasty.y());
                
            }
            
        }.start();
        
		window.show();
		
	}
	
}
