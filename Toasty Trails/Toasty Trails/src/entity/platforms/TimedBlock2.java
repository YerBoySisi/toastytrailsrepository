package entity.platforms;

import entity.LivingEntity;

public class TimedBlock2 extends Block {
	
	public final static String SPRITE[] = {"file:Toasty Trails/Resources/Tilesets/3-3.png", 
										   "file:Toasty Trails/Resources/Tilesets/3-2.png",
										   "file:Toasty Trails/Resources/Tilesets/3-1.png", 
										   "file:Toasty Trails/Resources/Tilesets/3.png"};
	public boolean started;
	
	public TimedBlock2(double x, double y) {
		
		super(x, y, 0);
		setSprite(SPRITE[0]);
		
	}
	
	@Override
	public void performAction(LivingEntity e) {
		
		if(!started) {
			
			new Thread(() -> {
				
				started = true;
				setSprite(SPRITE[1]);
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				setSprite(SPRITE[2]);
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				setSprite(SPRITE[3]);
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				sprite = null;
				invisible = true;
				
			}).start();
			
		}
		
	}

}
