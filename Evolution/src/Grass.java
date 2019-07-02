import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Grass extends AreaPlant {
	
	private BufferedImage grass_image;

	
	public Grass(int x, int y, ID id, SpriteSheet ss, float avgFood, float variance) throws IllegalArgumentException {
		
		super(x, y, id, ss);
		
		if (avgFood <= 0) throw new IllegalArgumentException();
		
		grass_image = ss.grabImage(4, 2, 10, 10);
		
		food = avgFood + ((((float) Math.random()) * 2) - 1) * variance;

	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(grass_image, x-5, y-5, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x-5, y-5, 5, 5);
	}
	
}
