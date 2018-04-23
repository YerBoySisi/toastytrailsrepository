package entity.platforms;

import entity.InanimateEntity;

public class Block extends InanimateEntity {
	
	public final static String SPRITE[] = {"file:Toasty Trails/Resources/Tilesets/grassblock.png", 
										   "file:Toasty Trails/Resources/Tilesets/dirtblock.png", 
										   "file:Toasty Trails/Resources/Tilesets/stoneblock.png"};
	
	public Block(double x, double y, int type) {
		
		this.x = x;
		this.y = y;
		name = "Block";
		setSprite(SPRITE[type]);
		
	}

}
