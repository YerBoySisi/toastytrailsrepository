package entity.platforms;

import entity.InanimateEntity;

public class Block extends InanimateEntity {
	
	public final static String SPRITE[] = {"file:Toasty Trails/Resources/Tilesets/grassblock.png", 
										   "file:Toasty Trails/Resources/Tilesets/dirtblock.png", 
										   "file:Toasty Trails/Resources/Tilesets/stoneblock.png",
										   "file:Toasty Trails/Resources/Tilesets/whitetile.png",
										   "file:Toasty Trails/Resources/Tilesets/blacktile.png",
										   "file:Toasty Trails/Resources/Tilesets/brick.png",
										   "file:Toasty Trails/Resources/Tilesets/brick2.png",
										   "file:Toasty Trails/Resources/Tilesets/planktile.png",
										   "file:Toasty Trails/Resources/Tilesets/planktile2.png"};
	
	private int type;
	
	public Block(double x, double y, int type) {
		
		this.x = x;
		this.y = y;
		this.type = type;
		name = "Block";
		setSprite(SPRITE[type]);
		
	}
	
	public int getType() {
		
		return type;
		
	}
	
	public void setType(int type) {
		
		this.type = type;
		setSprite(SPRITE[type]);
		
	}

}
