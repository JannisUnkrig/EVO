
public class Bush extends TallPlant {
	
	public Bush(int sizeInDm, int avgFoodPerDm, float variance) throws IllegalArgumentException {
		
		if (sizeInDm <= 0) throw new IllegalArgumentException();
		
		this.foodPerDm = new float[sizeInDm];
		for(int i = 0; i < sizeInDm; i++) {
			foodPerDm[i] = (((float) Math.random()) * 2) * variance * avgFoodPerDm;
		}
	}

}
