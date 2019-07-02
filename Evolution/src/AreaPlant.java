
public abstract class AreaPlant extends Plant {

	protected float food;
	
	public AreaPlant(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
	}
	
	public float getFood() {
		return food;
	}

	public void setFood(float food) {
		this.food = food;
	}

	public void addFood(float amountOfFood) {
		this.food += amountOfFood;
	}

	public void reduceFood(float amountOfFood) {
		this.food -= amountOfFood;
	}

	public float eatFood(float amountOfFood) {
		this.food -= amountOfFood;
		return amountOfFood;
	}
	
	public boolean hasFood() {
		if (food >= 0) return true;
		return false;
	}
}
