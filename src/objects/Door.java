package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;

public class Door extends GameObject {

	private int doorId;

	public Door(float x, float y, ObjectId id, int doorId) {
		super(x, y, id);
		this.doorId = doorId;
	}

	public void tick(LinkedList<GameObject> object) {
	}

	public void render(Graphics g) {
		if (doorId != 0) {
			g.setColor(Color.decode("#8B4513"));
			g.fillRect((int) x, (int) y, 32, 98);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 98);
	}

	public int getDoorId() {
		return doorId;
	}

	public void setDoorId(int doorId) {
		this.doorId = doorId;
	}
}
