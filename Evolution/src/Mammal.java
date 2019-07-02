import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Mammal extends Animal{

	private BufferedImage mammal_image;

	
	public Mammal(int x, int y, ID id, float velX, float velY, SpriteSheet ss) {
		super(x, y, id, velX, velY, ss);
		
		mammal_image = ss.grabImage(6, 1, 24, 24);
		
		speciesName = "Cow";
		movementSpeed = (float) 0.1;
		saturation = 400;
		minWantedSaturation = 900;
		maxSaturation = 1000;
		eatingSpeed = (float) 0.5;
		saturationLoss = (float) 0.1;
		heightInDm = 10;
		age = 0;
		ageGrownUp = 600;
		growth = (float) 0.015;
		isCarnivore = false;
		isHerbivore = true;
		isScavenger = false;
		eats.add(ID.Grass);
		eats.add(ID.Bush);
		status = Status.Resting;
		

	}

	@Override
	public void tick() {
		
		super.tick();
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(mammal_image, x-12, y-12, null);
		
	}

}
