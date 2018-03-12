package entity.platforms;

import entity.LivingEntity;

public class TimedBlock1 extends Block {
	
	public final static String SPRITE[] = {"file:Toasty Trails/Resources/Tilesets/3-1.png"};
	public boolean started;
	
	public TimedBlock1(double x, double y) {
		
		super(x, y);
		setSprite(SPRITE[0]);
		
	}
	
	@Override
	public void performAction(LivingEntity e) {
		
		if(!started) {
			
			new Thread(() -> {
				
				started = true;
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				invisible = true;
				
			}).start();
			
		}
		
	}

}
