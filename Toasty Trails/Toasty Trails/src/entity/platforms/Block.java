package entity.platforms;

import entity.InanimateEntity;

public class Block extends InanimateEntity {
	
	public final static String SPRITE[] = {"file:Toasty Trails/Resources/Tilesets/grassblock.png", //0
										   "file:Toasty Trails/Resources/Tilesets/dirtblock.png",  //1
										   "file:Toasty Trails/Resources/Tilesets/stoneblock.png", //2
										   "file:Toasty Trails/Resources/Tilesets/whitetile.png",  //3
										   "file:Toasty Trails/Resources/Tilesets/blacktile.png",  //4
										   "file:Toasty Trails/Resources/Tilesets/brick.png",      //5
										   "file:Toasty Trails/Resources/Tilesets/brick2.png",     //6
										   "file:Toasty Trails/Resources/Tilesets/planktile.png",  //7
										   "file:Toasty Trails/Resources/Tilesets/planktile2.png", //8
										   "file:Toasty Trails/Resources/Tilesets/brokenconcreteblock.png",    //9
										   "file:Toasty Trails/Resources/Tilesets/brokenconcreteblockh.png",   //10
										   "file:Toasty Trails/Resources/Tilesets/brokenconcreteblockh2.png",  //11
										   "file:Toasty Trails/Resources/Tilesets/brokenconcreteblockv.png",   //12
										   "file:Toasty Trails/Resources/Tilesets/brokenconcreteblockv2.png"}; //13
	
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
