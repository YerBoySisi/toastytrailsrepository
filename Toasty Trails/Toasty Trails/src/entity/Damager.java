package entity;

public interface Damager {

	public void attack(LivingEntity e);

	public int getDamage();

	public void setDamage(int damage);
	
}
