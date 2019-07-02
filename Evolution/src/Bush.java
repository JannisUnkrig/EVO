import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Bush extends TallPlant {
	
	private BufferedImage bush_image;

	
	public Bush(int x, int y, ID id, SpriteSheet ss, int sizeInDm, int avgFoodPerDm, float variance) throws IllegalArgumentException {
		
		super(x, y, id, ss);
		
		if (sizeInDm <= 0) throw new IllegalArgumentException();
		
		bush_image = ss.grabImage(6, 2, 8, 8);
		
		this.foodPerDm = new float[sizeInDm];
		for(int i = 0; i < sizeInDm; i++) {
			foodPerDm[i] = avgFoodPerDm + ((((float) Math.random()) * 2) - 1) * variance;
		}
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(bush_image, x-4, y-4, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x-2, y-2, 2, 2);
	}
	
}
