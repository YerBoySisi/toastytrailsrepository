package gamestate;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class MenuState {
	
	public static class GameMenu extends Parent {
		
		public GameMenu() throws IOException {
			
			//main menu
			VBox mainmenu = new VBox(10);
			mainmenu.setTranslateX(75);
			mainmenu.setTranslateY(75);
			
			//menu when "play" is clicked
			VBox playmenu = new VBox(10);
			playmenu.setTranslateX(600);
			playmenu.setTranslateY(75);
			
			//menu when "settings" is clicked
			VBox settingsmenu = new VBox(10);
			settingsmenu.setTranslateX(600);
			settingsmenu.setTranslateY(75);
			
			//menu when "exit" is clicked
			VBox exitmenu = new VBox(10);
			exitmenu.setTranslateX(600);
			exitmenu.setTranslateY(75);
			
			final int offset0 = 300;
			final int offset1 = 600;
			final double transtime1 = 1;
			final double transtime2 = .75;
			
			//goes to playmenu
			MenuButton play = new MenuButton("PLAY");
			play.setOnMouseClicked(e -> {
				getChildren().add(playmenu);
				
				TranslateTransition tt0 = new TranslateTransition(Duration.seconds(transtime1), mainmenu);
				tt0.setToX(mainmenu.getTranslateX() - offset0);
				

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(transtime1), playmenu);
				tt1.setToX(mainmenu.getTranslateX());
				
				tt0.play();
				tt1.play();
				
				tt0.setOnFinished(event -> {
					getChildren().remove(mainmenu);
				});
			});
			
			MenuButton resume = new MenuButton("CONTINUE");
			resume.setOnMouseClicked(e -> {
				
			});
			
			MenuButton newGame = new MenuButton("NEW GAME");
			newGame.setOnMouseClicked(e -> {
				
			});
			
			//returns to mainmenu
			MenuButton playmenuback = new MenuButton("BACK");
			playmenuback.setOnMouseClicked(e -> {
				
				getChildren().add(mainmenu);
				mainmenu.setVisible(false);
				
				TranslateTransition tt0 = new TranslateTransition(Duration.seconds(transtime2), playmenu);
				tt0.setToX(playmenu.getTranslateX() + offset1);
				

				TranslateTransition tt1 = new TranslateTransition(Duration.seconds(transtime2), mainmenu);
				tt1.setToX(playmenu.getTranslateX());
				
				tt0.play();
				tt1.play();
				
				tt0.setOnFinished(event -> {
					getChildren().remove(playmenu);
					mainmenu.setVisible(true);
				});
				
			});
			
			
			
			//goes to settingsmenu
			MenuButton settings = new MenuButton("SETTINGS");
			settings.setOnMouseClicked(e -> {
				
				getChildren().addAll(settingsmenu);
				
				TranslateTransition tt0 = new TranslateTransition(Duration.seconds(transtime1), mainmenu);
				tt0.setToX(mainmenu.getTranslateX() - offset0);
				

				TranslateTransition tt2 = new TranslateTransition(Duration.seconds(transtime1), settingsmenu);
				tt2.setToX(mainmenu.getTranslateX());
				
				tt0.play();
				tt2.play();
				
				tt0.setOnFinished(event -> {
					getChildren().remove(mainmenu);
				});
				
			});
			
			MenuButton sound = new MenuButton("SOUND");
			sound.setOnMouseClicked(e -> {
				
			});
			
			//returns to mainmenu
			MenuButton soundmenuback = new MenuButton("BACK");
			soundmenuback.setOnMouseClicked(e -> {
				
				getChildren().add(mainmenu);
				mainmenu.setVisible(false);
				
				TranslateTransition tt0 = new TranslateTransition(Duration.seconds(transtime2), settingsmenu);
				tt0.setToX(settingsmenu.getTranslateX() + offset1);
				

				TranslateTransition tt2 = new TranslateTransition(Duration.seconds(transtime2), mainmenu);
				tt2.setToX(settingsmenu.getTranslateX());
				
				tt0.play();
				tt2.play();
				
				tt0.setOnFinished(event -> {
					getChildren().remove(settingsmenu);
					mainmenu.setVisible(true);
				});
				
			});
			
			
			
			MenuButton credits = new MenuButton("CREDITS");
			credits.setOnMouseClicked(e -> {
				
			});
			
			
			
			//goes to exitmenu
			MenuButton exit = new MenuButton("EXIT");
			exit.setOnMouseClicked(e -> {

				getChildren().addAll(exitmenu);
				
				TranslateTransition tt0 = new TranslateTransition(Duration.seconds(transtime1), mainmenu);
				tt0.setToX(mainmenu.getTranslateX() - offset0);
				

				TranslateTransition tt3 = new TranslateTransition(Duration.seconds(transtime1), exitmenu);
				tt3.setToX(mainmenu.getTranslateX());
				
				tt0.play();
				tt3.play();
				
				tt0.setOnFinished(event -> {
					getChildren().remove(mainmenu);
				});

			});
			
			MenuButton exitgame = new MenuButton("EXIT GAME");
			exitgame.setOnMouseClicked(e -> {
				closeProgram();
			});
			
			//returns to mainmenu
			MenuButton exitmenuback = new MenuButton("BACK");
			exitmenuback.setOnMouseClicked(e -> {
				
				getChildren().add(mainmenu);
				mainmenu.setVisible(false);
				
				TranslateTransition tt0 = new TranslateTransition(Duration.seconds(transtime2), exitmenu);
				tt0.setToX(exitmenu.getTranslateX() + offset1);

				TranslateTransition tt3 = new TranslateTransition(Duration.seconds(transtime2), mainmenu);
				tt3.setToX(exitmenu.getTranslateX());
				
				tt0.play();
				tt3.play();
				
				tt0.setOnFinished(event -> {
					getChildren().remove(exitmenu);
					mainmenu.setVisible(true);
				});
				
			});
			
			InputStream is = Files.newInputStream(Paths.get("Resources/BGs/menubg.png"));
			Image img = new Image(is);
			is.close();
			
			ImageView bg = new ImageView(img);
			
			mainmenu.getChildren().addAll(play, settings, credits, exit);
			playmenu.getChildren().addAll(resume, newGame, playmenuback);
			settingsmenu.getChildren().addAll(sound, soundmenuback);
			exitmenu.getChildren().addAll(exitgame, exitmenuback);
			
			getChildren().addAll(bg, mainmenu);
			
		}
		
	}
	
	private static class MenuButton extends StackPane {
		
		private Text text;
		
		public MenuButton(String name) {
			
			text = new Text(name);
			text.setFont(Font.font(20));
			text.setFill(Color.WHITE);
			
			Rectangle bg = new Rectangle(200, 30);
			bg.setFill(Color.BLACK);
			
			setAlignment(Pos.CENTER_LEFT);
			setRotate(-0.5);
			getChildren().addAll(bg, text);
			
			setOnMouseEntered(e -> {
				bg.setTranslateX(5);
				text.setTranslateX(5);
				bg.setFill(Color.RED);
				text.setFill(Color.WHITE);
			});
			
			setOnMouseExited(e -> {
				bg.setTranslateX(0);
				text.setTranslateX(0);
				bg.setFill(Color.BLACK);
				text.setFill(Color.WHITE);
			});
			
		}
		
	}
	
	public static void closeProgram() {
		
		System.out.print("Progress saved");
		System.exit(0);
		
	}
	
}
