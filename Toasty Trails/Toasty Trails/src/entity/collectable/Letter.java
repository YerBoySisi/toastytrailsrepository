package entity.collectable;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import main.GamePanel;

public class Letter extends Collectible {
	
	public final static String SPRITE[] = {"file:Toasty Trails/Resources/Sprites/T.png", 
			   							   "file:Toasty Trails/Resources/Sprites/O.png", 
			   							   "file:Toasty Trails/Resources/Sprites/A.png",
			   							   "file:Toasty Trails/Resources/Sprites/S.png",
			   							   "file:Toasty Trails/Resources/Sprites/T2.png",
			   							   "file:Toasty Trails/Resources/Sprites/Y.png"};
	
	private boolean collected;
	
	public Letter(int x, int y, int ltr) {
		
		super(0, 0);
		
		this.x = x;
		this.y = y;
		
		setSprite(SPRITE[ltr]);
		
		Media sfx = new Media(new File("Toasty Trails/Resources/Sounds/toasty.mp3").toURI().toString());
		MediaPlayer mp = new MediaPlayer(sfx);
		
		
		new Thread(() -> {
			
			while(!collected) {
				
				velocityY = .2;
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				velocityY = -.2;
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		}).start();
		
		new Thread(() -> {
			
			while(!collected) {
				
				if(colliding(GamePanel.toasty)) {
					setCollected();
					mp.play();
				} else {
					System.out.println();
				}
				
			}
			
		}).start();
		
	}
	
	public void setCollected() {
		
		collected = true;
		invisible = true;
		sprite = null;
		
	}

}
