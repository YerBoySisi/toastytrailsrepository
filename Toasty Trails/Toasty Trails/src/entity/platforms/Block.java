package entity.platforms;

import entity.InanimateEntity;

public class Block extends InanimateEntity {
	
	public final static String SPRITE[] = {"file:Toasty Trails/Resources/Tilesets/stoneblock.png"};
	
	public Block(double x, double y) {
		
		this.x = x;
		this.y = y;
		name = "Block";
		setSprite(SPRITE[0]);
		
	}

}
