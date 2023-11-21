package framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class GameObject {

	protected float x, y;
	protected ObjectId id;
	protected float velX = 0, velY = 0;
	protected boolean fall = true;
	protected boolean jump = false;

	public GameObject(float x, float y, ObjectId id) {
		this.id = id;
		this.x = x;
		this.y = y;
	}

	public abstract void tick(LinkedList<GameObject> oject);

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();

	public float getX() {

		return x;
	}

	public float getY() {

		return y;
	}

	public void setX(float x) {

		this.x = x;
	}

	public void setY(float y) {

		this.y = y;
	}

	public float getVelX() {

		return velX;
	}

	public float getVelY() {

		return velY;
	}

	public void setVelX(float velx) {

		this.velX = velx;
	}

	public void setVelY(float vely) {

		this.velY = vely;
	}

	public boolean isFall() {
		return fall;
	}

	public void setFall(boolean fall) {
		this.fall = fall;
	}

	public boolean isJump() {
		return jump;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public ObjectId getId() {

		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

}
