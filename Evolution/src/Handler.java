import java.awt.Graphics;
import java.util.ArrayList;

public class Handler {

	ArrayList<Being> beingsL1 = new ArrayList<Being>();
	ArrayList<Being> beingsL2 = new ArrayList<Being>();
	ArrayList<Being> beingsL3 = new ArrayList<Being>();
	
	
	public void tick( ) {
		for(int i = 0; i < beingsL1.size(); i++) {
			Being tempObject = beingsL1.get(i);
			tempObject.tick();
		}
		
		for(int i = 0; i < beingsL2.size(); i++) {
			Being tempObject = beingsL2.get(i);
			tempObject.tick();
		}
		
		for(int i = 0; i < beingsL3.size(); i++) {
			Being tempObject = beingsL3.get(i);
			tempObject.tick();
		}
	}
	
	
	public void render(Graphics g) {
		for(int i = 0; i < beingsL1.size(); i++) {
			Being tempObject = beingsL1.get(i);
			tempObject.render(g);
		}
		
		for(int i = 0; i < beingsL2.size(); i++) {
			Being tempObject = beingsL2.get(i);
			tempObject.render(g);
		}
		
		for(int i = 0; i < beingsL3.size(); i++) {
			Being tempObject = beingsL3.get(i);
			tempObject.render(g);
		}
	}
	
	
	public void addBeingL1(Being tempObject) {
		beingsL1.add(tempObject);
		tempObject.setHandler(this);
	}
	
	public void addBeingL2(Being tempObject) {
		beingsL2.add(tempObject);
		tempObject.setHandler(this);
	}
	
	public void addBeingL3(Being tempObject) {
		beingsL3.add(tempObject);
		tempObject.setHandler(this);
	}
	
	
	public void removeBeingL1(Being tempObject) {
		beingsL1.remove(tempObject);
	}
	
	public void removeBeingL2(Being tempObject) {
		beingsL2.remove(tempObject);
	}
	
	public void removeBeingL3(Being tempObject) {
		beingsL3.remove(tempObject);
	}

}
