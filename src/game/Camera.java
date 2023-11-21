package game;

import framework.GameObject;

public class Camera {

	private float x, y;

	public Camera(float x, float y) {
		this.x = x;
		// this.y = y;
	}

	public void tick(GameObject player) {
		if (-player.getX() + Game3.width / 2 < 0) {
			x = -player.getX() + Game3.width / 2;
		} else {
			x = 0;
		}
		// y = -player.getY() + Game3.height / 2;
		if (player.getY() > 588) {
			y = -588 + 12;
		} else
			y = 0;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

}
