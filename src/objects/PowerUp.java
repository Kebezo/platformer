package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;

public class PowerUp extends GameObject {
	protected String type;

	public PowerUp(float x, float y, ObjectId id, String type) {
		super(x, y, id);
		this.type = type;
	}

	public void tick(LinkedList<GameObject> oject) {
		// TODO Auto-generated method stub

	}

	public void render(Graphics g) {
		Color c = new Color(0, 127, 127);
		g.setColor(c);
		g.fillRect((int) x, (int) y, 34, 34);

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
