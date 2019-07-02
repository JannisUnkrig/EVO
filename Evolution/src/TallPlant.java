
public abstract class TallPlant extends Plant {

	protected float[] foodPerDm;
	
	public TallPlant(int x, int y, ID id, SpriteSheet ss) {
		super(x, y, id, ss);
	}
	
	public float[] getFoodPerDm() {
		return foodPerDm;
	}

	public void setFood(float[] foodPerDm) {
		this.foodPerDm = foodPerDm;
	}

	public void addFood(float amountOfFood) {
		for (int i = 0; i < foodPerDm.length; i++) {
			this.foodPerDm[i] += amountOfFood;
		}
	}
	
	public void addFood(float amountOfFood, int layer) {
		
		try {
			this.foodPerDm[layer] += amountOfFood;
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public void reduceFood(float amountOfFood) {
		for (int i = 0; i < foodPerDm.length; i++) {
			this.foodPerDm[i] -= amountOfFood;
		}
	}
	
	public void reduceFood(float amountOfFood, int layer) {
		
		try {
			this.foodPerDm[layer] -= amountOfFood;
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	
	public float eatLowestFood(float amountOfFood) {
		
		for (int i = 0; i < foodPerDm.length; i++) {
			if (foodPerDm[i] > 0) {
				foodPerDm[i] -= amountOfFood;
				return amountOfFood;
			}
		}
		return 0;
	}
	
	public boolean hasFood() {
		for (int i = 0; i < foodPerDm.length; i++) {
			if (foodPerDm[i] >= 0) return true;
		}
		return false;
	}
}
