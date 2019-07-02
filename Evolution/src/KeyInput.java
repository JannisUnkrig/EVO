import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	Handler handler;
	private SpriteSheet ss;
	
	public KeyInput(Handler handler, SpriteSheet ss) {
		this.handler = handler;
		this.ss = ss;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) handler.setUp(true);
				if(key == KeyEvent.VK_S) handler.setDown(true);
				if(key == KeyEvent.VK_A) handler.setLeft(true);
				if(key == KeyEvent.VK_D) handler.setRight(true);
				
				//summon bullets with shotspeed x
				if(key == KeyEvent.VK_UP) handler.addObject(new Bullet(tempObject.getX()+24, tempObject.getY()+16, ID.Bullet, handler, 0, -10, ss));
				if(key == KeyEvent.VK_DOWN) handler.addObject(new Bullet(tempObject.getX()+24, tempObject.getY()+16, ID.Bullet, handler, 0, 10, ss));
				if(key == KeyEvent.VK_LEFT) handler.addObject(new Bullet(tempObject.getX()+24, tempObject.getY()+16, ID.Bullet, handler, -10, 0, ss));
				if(key == KeyEvent.VK_RIGHT) handler.addObject(new Bullet(tempObject.getX()+24, tempObject.getY()+16, ID.Bullet, handler, 10, 0, ss));

			}
		}
		
		
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_W) handler.setUp(false);
				if(key == KeyEvent.VK_S) handler.setDown(false);
				if(key == KeyEvent.VK_A) handler.setLeft(false);
				if(key == KeyEvent.VK_D) handler.setRight(false);
			}
		}
		
	}
	
}
