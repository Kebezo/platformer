package objects;


import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;

public class ElevatorStop extends GameObject {

	private int elStopId;

	public ElevatorStop(float x, float y, ObjectId id, int elStopId) {
		super(x, y, id);
		this.elStopId = elStopId;
	}

	public void tick(LinkedList<GameObject> object) {

	}

	public void render(Graphics g) {
		/*g.setColor(Color.gray);
		g.fillRect((int) x, (int) y, 64, 32);*/
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
}
