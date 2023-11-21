package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;

public class Elevator extends GameObject {

	private int elStopId;
	private boolean fall;
	int searchId;
	int startY;
	int count = 1;

	public Elevator(float x, float y, ObjectId id, boolean fall, int elStopId) {
		super(x, y, id);
		this.elStopId = elStopId;
		this.fall = fall;
		velY = -2;
		startY = (int) y;
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		move(object);

	}

	public boolean isFall() {
		return fall;
	}

	public void setFall(boolean fall) {
		this.fall = fall;
	}

	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int) x, (int) y, 64, 32);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 64, 32);
	}

	public int getElStopId() {
		return elStopId;
	}

	public void setElStopId(int elStopId) {
		this.elStopId = elStopId;
	}

	public void move(LinkedList<GameObject> object) {
		searchId = getElStopId();
		for (int j = 0; j < object.size(); j++) {
			GameObject tempObj = object.get(j);
			if (tempObj.getId() == ObjectId.ElevatorStop && ((ElevatorStop) tempObj).getElStopId() == searchId) {

				if (tempObj.getBounds().intersects(getBounds()) && count % 2 != 0) {
					velY = velY * -1;
					count++;
				} else if ((y > startY) && count % 2 == 0) {
					velY = velY * -1;
					count++;
				}

			}
		}
	}

}
