package entity.platforms;

import audio.soundfx.Crumble;
import entity.LivingEntity;

public class TimedBlock extends Block {
	
	public final static String SPRITE[] = {"file:Toasty Trails/Resources/Tilesets/3.png"};
	public boolean started;
	
	public TimedBlock(double x, double y) {
		
		super(x, y, 0);
		setSprite(SPRITE[0]);
		
		sounds.add(new Crumble());
		
	}
	
	public void crumble() {
		
		Thread crumble = new Thread(() -> {
			
			started = true;
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			sounds.get(0).play();
			sprite = null;
			invisible = true;
			
		});
		
		crumble.start();
		
	}
	
	@Override
	public void performAction(LivingEntity e) {
		
		if(!started) {
			
			crumble();
			
			new Thread(() -> { 
				
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				setSprite(SPRITE[0]);
				invisible = false;
				started = false;
				
			}).start();
			
		}
		
	}

}
