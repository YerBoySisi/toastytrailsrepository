package entity.platforms;

import entity.LivingEntity;

public class TimedBlock extends Block {
	
	public final static String SPRITE[] = {"file:Toasty Trails/Resources/Tilesets/3.png"};
	public boolean started;
	
	public TimedBlock(double x, double y) {
		
		super(x, y, 0);
		setSprite(SPRITE[0]);
		
	}
	
	@Override
	public void performAction(LivingEntity e) {
		
		if(!started) {
			
			new Thread(() -> {
				
				started = true;
				
				try {
					Thread.sleep(200);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
				sprite = null;
				invisible = true;
				
			}).start();
			
		}
		
	}

}
