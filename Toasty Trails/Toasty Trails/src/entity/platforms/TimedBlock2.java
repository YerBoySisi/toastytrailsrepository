package entity.platforms;

import entity.LivingEntity;

public class TimedBlock2 extends Block {
	
	public final static String SPRITE[] = {"file:Toasty Trails/Resources/Tilesets/3-2.png"};
	public boolean started;
	
	public TimedBlock2(double x, double y) {
		
		super(x, y);
		setSprite(SPRITE[0]);
		
	}
	
	@Override
	public void performAction(LivingEntity e) {
		
		if(!started) {
			
			new Thread(() -> {
				
				started = true;
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				invisible = true;
				
			}).start();
			
		}
		
	}

}
