package entity.platforms;

public class PermeableBlock extends Block {
	
	public final static String SPRITE[] = {"file:Toasty Trails/Resources/Tilesets/grassblock.png"};
	
	public PermeableBlock(double x, double y) {
		
		super(x, y);
		setSprite(SPRITE[0]);
		permeable = true;
		
	}

}
