package main;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.application.*;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import gamestate.MenuState.GameMenu;

public class GamePanel extends Application {
	
	private Stage window;
	private Scene scene;
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
		menuRoot.getChildren().add(menu);
		
		scene = new Scene(menuRoot);
		
		scene.setOnKeyPressed(e -> keys.put(e.getCode(), true));
		scene.setOnKeyReleased(e -> keys.put(e.getCode(), false));
		
		window.setTitle("Toasty Trails");
		window.setScene(scene);
		window.show();
		
	}
	
}
