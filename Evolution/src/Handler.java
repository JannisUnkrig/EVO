import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {

	ArrayList<Being> beings = new ArrayList<Being>();
	
	public void tick( ) {
		for(int i = 0; i < beings.size(); i++) {
			Being tempObject = beings.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < beings.size(); i++) {
			Being tempObject = beings.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(Being tempObject) {
		beings.add(tempObject);
	}
	
	public void removeObject(Being tempObject) {
		beings.remove(tempObject);
	}

}
