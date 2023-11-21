package framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import game.Camera;
import game.Game3;
import game.Game3.State;
import game.Handler;
import objects.Player;

public class Key extends KeyAdapter {

	Handler handler;
	Camera cam;

	public Key(Handler handler, Camera cam) {
		this.handler = handler;
		this.cam = cam;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {

			GameObject temp = handler.object.get(i);

			if (temp.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_RIGHT) {
					temp.setVelX(5);
					Player.facing = 1;
				}
				if (key == KeyEvent.VK_LEFT) {
					temp.setVelX(-5);
					Player.facing = -1;
				}
				if (key == KeyEvent.VK_UP && !temp.isJump()) {
					temp.setJump(true);
					temp.setVelY(-7);
				}
			}
		}
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(1);

		if (key == KeyEvent.VK_P) {
			Game3.state = State.MENU;
			cam.setX(0);
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {

			GameObject temp = handler.object.get(i);

			if (temp.getId() == ObjectId.Player) {
				if (key == KeyEvent.VK_RIGHT)
					temp.setVelX(0);
				if (key == KeyEvent.VK_LEFT)
					temp.setVelX(0);
			}
		}
	}

}
