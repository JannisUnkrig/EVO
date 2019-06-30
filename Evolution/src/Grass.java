import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Grass extends AreaPlant {
	
	private BufferedImage grass_image;

	
	public Grass(int x, int y, ID id, SpriteSheet ss, float avgFood, float variance) throws IllegalArgumentException {
		
		super(x, y, id, ss);
		
		if (avgFood <= 0) throw new IllegalArgumentException();
		
		grass_image = ss.grabImage(4, 2, 10, 10);
		
		food = (((float) Math.random()) * 2) * variance * avgFood;

	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(grass_image, x, y, null);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, 10, 10);
	}
	
}
