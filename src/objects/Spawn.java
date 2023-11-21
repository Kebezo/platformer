package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import framework.GameObject;
import framework.ObjectId;

public class Spawn extends GameObject{

	public Spawn(float x, float y, ObjectId id) {
		super(x, y, id);
		
	}

	
	public void tick(LinkedList<GameObject> oject) {
		
	}


	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int) x, (int) y, 32, 32);
	}


	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 32);
	}

}
