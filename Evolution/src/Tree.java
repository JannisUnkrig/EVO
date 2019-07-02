import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Tree extends TallPlant {
	
	private BufferedImage tree_image;

	
	public Tree(int x, int y, ID id, SpriteSheet ss, int sizeInDm, int leaveHeightInDm , float avgFoodPerDm, float variance) throws IllegalArgumentException {
		
		super(x, y, id, ss);
		
		if (sizeInDm <= 0 || leaveHeightInDm < 0) throw new IllegalArgumentException();
		
		tree_image = ss.grabImage(5, 2, 10, 10);
		
		this.foodPerDm = new float[sizeInDm];
		for(int i = 0; i < leaveHeightInDm; i++) {
			foodPerDm[i] = 0;
		}
		for(int i = leaveHeightInDm; i < sizeInDm; i++) {
			foodPerDm[i] = avgFoodPerDm + ((((float) Math.random()) * 2) - 1) * variance;
		}
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(tree_image, x-5, y-5, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x-3, y-3, 3, 3);
	}
}
