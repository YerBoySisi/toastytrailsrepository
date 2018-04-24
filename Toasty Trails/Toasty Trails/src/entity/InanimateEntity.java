package entity;

public abstract class InanimateEntity extends Entity {
	
	//private boolean broken;
	
	protected boolean permeable;
	public boolean invisible;

	public boolean isPermeable() {
		
		return permeable;
		
	}
	
	public void performAction(LivingEntity e) {}
	
}
