package entity;

public class Block extends InanimateEntity {
	
	public final String SPRITE[] = {"file:Resources/Tilesets/grassblock.png"};
	
	public Block(double x, double y) {
		
		this.x = x;
		this.y = y;
		name = "Block";
		setSprite(SPRITE[0]);
		
	}

}
