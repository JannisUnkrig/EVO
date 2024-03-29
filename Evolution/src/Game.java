import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable, MouseListener{

	private static final long serialVersionUID = 1L;

	private boolean isRunning = false;
	private Thread thread;
	private Handler handler;
	private SpriteSheet ss;
	
	private BufferedImage level = null;
	private BufferedImage sprite_sheet = null;
	private BufferedImage floor = null;
	
	public int fpsDisplay = 0;
	public int health = 10;
	
	public Game() {
		new Window(1207, 830, "EVO", this);
		
		handler = new Handler();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/teststage.png");
		sprite_sheet = loader.loadImage("/sprite_sheet.png");
		
		ss = new SpriteSheet(sprite_sheet);
		
		floor = loader.loadImage("/background_dirt.png");

		loadLevel(level);
		
		start();

	}
	
	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 600.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				delta--;
			}
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				fpsDisplay = frames;
				frames = 0;
			}
		}
		stop();
	}
	
	public void tick() {
		handler.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		for(int xx = 0; xx < 1200; xx+=50) {
			for(int yy = 0; yy < 800; yy+=50) {
				g.drawImage(floor, xx, yy, null);
			}
		}
		
		handler.render(g);

		g.setColor(Color.gray);
		g.fillRect(5, 5, 200, 32);
		g.setColor(Color.red);
		g.fillRect(5, 5, health*20, 32);	
		g.setColor(Color.black);
		g.drawRect(5, 5, 200, 32);
		g.drawString("" + health, 100, 25);
		
		g.setColor(Color.yellow);
		g.drawString(fpsDisplay + " fps", 5, 50);
		
		///////////////////////////////////
		g.dispose();
		bs.show();
	}
	
	//loading the level
	private void loadLevel(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int xx = 0; xx < w; xx++) {
			for(int yy = 0; yy < h; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;

				if(green == 100)
					handler.addBeingL2(new Tree(xx*10, yy*10, ID.Tree, ss, 100, 30, 50, (float) 0.2));
				
				if(green == 200)
					handler.addBeingL2(new Bush(xx*10, yy*10, ID.Bush, ss, 10, 20, (float) 0.1));
				
				if(blue == 100)
					handler.addBeingL1(new Grass(xx*10, yy*10, ID.Grass, ss, 30, (float) 0.33));
				
				if(red == 100)
					handler.addBeingL3(new Mammal(xx*10, yy*10, ID.Mammal, 0, 0, ss));
				
//				if(blue == 255 && green == 0)
//					handler.addObject(new PlayerCharacter(xx*32+8, yy*32+8, ID.Player, handler, this, ss));
//				
//				if(green == 255 && blue == 0)
//					handler.addObject(new Enemy(xx*32+8, yy*32+8, ID.Enemy, 2, 2, handler, ss));

			}
		}
	}
	
	
	
	public static void main(String args[]) {
		new Game();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("hi");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
