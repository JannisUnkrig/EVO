import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class Animal extends Being{

	protected String speciesName;
	protected float movementSpeed;
	protected float saturation;
	protected float minWantedSaturation;
	protected float maxSaturation;
	protected float eatingSpeed;
	protected float saturationLoss;
	protected float heightInDm;
	protected float age;
	protected float ageGrownUp;
	protected float growth;
	protected boolean isCarnivore;
	protected boolean isHerbivore;
	protected boolean isScavenger;
	protected ArrayList<ID> eats = new ArrayList<ID>();
	protected Status status;
	protected Being eatingTarget = null;

	
	
	public Animal(int x, int y, ID id, float velX, float velY, SpriteSheet ss) {
		super(x, y, id, velX, velY, ss);
	}
	
	public void tick() {
		
		//resting: if hungry -> fin food
		if (status == Status.Resting) {
			if (saturation < minWantedSaturation) findFood();
		}
		
		//going for food: if gone -> find new one, else if you hit it -> eat it
		if (status == Status.GoingForFood) {
			//TODO geht nur weil gegessenes entfernt wird
			if (!(handler.beingsL1.contains(eatingTarget) || handler.beingsL2.contains(eatingTarget) || handler.beingsL3.contains(eatingTarget))) findFood();
			else if (getBounds().intersects(eatingTarget.getBounds())) status = Status.Eating;
		}
		
		//eating: if gone -> find new one, else eat it
		if (status == Status.Eating) {
			//TODO geht nur weil gegessenes entfernt wird
			if(!(handler.beingsL1.contains(eatingTarget) || handler.beingsL2.contains(eatingTarget) || handler.beingsL3.contains(eatingTarget))) findFood();
			else eatFood();
		}
		
		
		//lose saturation
		saturation -= saturationLoss;
		
		//starving
		if (saturation <= 0) {
			handler.beingsL3.remove(this);
		}
		
		//move
		velXProgress += velX;
		velYProgress += velY;
		if (velXProgress >= 1) {
			x++;
			velXProgress--;
		}
		if (velYProgress >= 1) {
			y++;
			velYProgress--;
		}
		if (velXProgress <= -1) {
			x--;
			velXProgress++;
		}
		if (velYProgress <= -1) {
			y--;
			velYProgress++;
		}
	}

	protected void findFood() {
		
		if(isHerbivore) {
			
			double minRange = 50000;
			Being closest = null;
			double minRange2 = 60000;
			Being closest2 = null;
			double minRange3 = 70000;
			Being closest3 = null;
			double minRange4 = 80000;
			Being closest4 = null;
			ArrayList<Being> bl1 = handler.beingsL1;
			ArrayList<Being> bl2 = handler.beingsL2;
			
			// find closest edible being
			for (int j = 0; j < eats.size(); j++) {
				
				ID wantedBeingID = eats.get(j);
				
				for (int i = 0; i < bl1.size(); i++) {
					
					Being otherBeing = bl1.get(i);
					ID otherBeingID = otherBeing.id;
					float distance = (float) (Math.sqrt( (double) ((otherBeing.x - x)*(otherBeing.x - x) + (otherBeing.y - y)*(otherBeing.y - y))));
					
					if (wantedBeingID.equals(otherBeingID) && distance < minRange) {
						closest = otherBeing;
						minRange = distance;
					}
					else if (wantedBeingID.equals(otherBeingID) && distance < minRange2) {
						closest2 = otherBeing;
						minRange2 = distance;
					}
					else if (wantedBeingID.equals(otherBeingID) && distance < minRange3) {
						closest3 = otherBeing;
						minRange3 = distance;
					}
					else if (wantedBeingID.equals(otherBeingID) && distance < minRange4) {
						closest4 = otherBeing;
						minRange4 = distance;
					}
				}
				for (int i = 0; i < bl2.size(); i++) {
					
					Being otherBeing = bl2.get(i);
					ID otherBeingID = otherBeing.id;
					float distance = (float) (Math.sqrt( (double) ((otherBeing.x - x)*(otherBeing.x - x) + (otherBeing.y - y)*(otherBeing.y - y))));
					
					if (wantedBeingID.equals(otherBeingID) && distance < minRange) {
						closest = otherBeing;
						minRange = distance;
					}
					else if (wantedBeingID.equals(otherBeingID) && distance < minRange2) {
						closest2 = otherBeing;
						minRange2 = distance;
					}
					else if (wantedBeingID.equals(otherBeingID) && distance < minRange3) {
						closest3 = otherBeing;
						minRange3 = distance;
					}
					else if (wantedBeingID.equals(otherBeingID) && distance < minRange4) {
						closest4 = otherBeing;
						minRange4 = distance;
					}
				}
			}

			
			//kein essen da -> nix machen
			if (closest == null) {
				status = Status.Resting;
				return;
			}
			if (closest2 == null || (minRange2 - minRange) > 50) {
				closest2 = closest;
			}
			if (closest3 == null || (minRange3 - minRange) > 50) {
				closest3 = closest;
			}
			if (closest4 == null || (minRange4 - minRange) > 50) {
				closest4 = closest;
			}
			
			
			//drauf zu gehen
			double r = Math.random();
			
			if (r <= 0.3) {
				double toGo = (Math.sqrt( (double) ((closest.x - x)*(closest.x - x) + (closest.y - y)*(closest.y - y))));
				velX = (float) ((closest.x - x) / toGo * movementSpeed);
				velY = (float) ((closest.y - y) / toGo * movementSpeed);
				eatingTarget = closest;
			}
			else if (r <= 0.55) {
				double toGo = (Math.sqrt( (double) ((closest2.x - x)*(closest2.x - x) + (closest2.y - y)*(closest2.y - y))));
				velX = (float) ((closest2.x - x) / toGo * movementSpeed);
				velY = (float) ((closest2.y - y) / toGo * movementSpeed);
				eatingTarget = closest2;
			}
			else if (r <= 0.8) {
				double toGo = (Math.sqrt( (double) ((closest3.x - x)*(closest3.x - x) + (closest3.y - y)*(closest3.y - y))));
				velX = (float) ((closest3.x - x) / toGo * movementSpeed);
				velY = (float) ((closest3.y - y) / toGo * movementSpeed);
				eatingTarget = closest3;
			}
			else {
				double toGo = (Math.sqrt( (double) ((closest4.x - x)*(closest4.x - x) + (closest4.y - y)*(closest4.y - y))));
				velX = (float) ((closest4.x - x) / toGo * movementSpeed);
				velY = (float) ((closest4.y - y) / toGo * movementSpeed);
				eatingTarget = closest4;
			}
			
			//statusupdate
			status = Status.GoingForFood;
			
		}
		
		if (isCarnivore) {
			//TODO
		}
		
		if (isScavenger) {
			//TODO
		}
	}
	
	
	public void eatFood() {
		
		if(isHerbivore) {
			
			//stehenbleiben
			velX = 0;
			velY = 0;
			
			//essen
			if (eatingTarget.getClass().getGenericSuperclass().equals(TallPlant.class)) {
				saturation += ((TallPlant) eatingTarget).eatLowestFood(eatingSpeed);
				//TODO aktuell wächs nix nach
				if (!((TallPlant) eatingTarget).hasFood()) {
					getHandler().removeBeingL2(eatingTarget);
					status = Status.Resting;
				}
			}
			if (eatingTarget.getClass().getGenericSuperclass().equals(AreaPlant.class)) {
				saturation += ((AreaPlant) eatingTarget).eatFood(eatingSpeed);
				//TODO aktuell wächs nix nach
				if (!((AreaPlant) eatingTarget).hasFood()) {
					getHandler().removeBeingL1(eatingTarget);
					status = Status.Resting;
				}
			}
		}
		
		if (isCarnivore) {
			//TODO
		}
		
		if (isScavenger) {
			//TODO
		}
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x-5, y-5, 5, 5);
	}
	
}
