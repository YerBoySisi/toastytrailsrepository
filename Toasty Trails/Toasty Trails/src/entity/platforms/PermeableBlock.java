package entity.platforms;

public class PermeableBlock extends Block {
	
	public final static String SPRITE[] = {"file:Toasty Trails/Resources/Tilesets/2.png"};
	
	public PermeableBlock(double x, double y) {
		
		super(x, y, 0);
		setSprite(SPRITE[0]);
		permeable = true;
		
	}

}
