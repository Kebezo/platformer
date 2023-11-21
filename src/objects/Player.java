package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;

import javax.sound.midi.SysexMessage;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import framework.GameObject;
import framework.ObjectId;
import framework.Texture;
import game.Animation;
import game.Camera;
import game.Game3;
import game.Game3.State;
import game.Handler;
import javafx.print.PageLayout;

public class Player extends GameObject {

	private float width = 32;
	private float height = 64;
	private float gravity = 0.3f;
	private boolean fall = true;
	private final float maxSpeed = 15;
	private Handler handler;
	int spawnX = 100;
	int spawnY = 100;
	public static boolean gameOver = false;
	AudioInputStream ais;
	Clip myClip;
	File file = new File("resources/oof.wav");
	Texture tex = Game3.getInstance();
	private Animation playerWalk;
	private Animation playerWalk_left;
	private Animation playerIdle;
	private Animation playerIdle_Left;
	public static int facing = 1; // 1 right -1 left
	Camera cam;
	

	public Player(float x, float y, Handler handler, Camera cam, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.cam = cam;
		playerIdle = new Animation(10, tex.player[0], tex.player[1], tex.player[2], tex.player[3]);

		playerWalk = new Animation(3, tex.player[4], tex.player[5], tex.player[6], tex.player[7], tex.player[8],
				tex.player[9], tex.player[10], tex.player[11]);

		playerWalk_left = new Animation(3, tex.player_left[4], tex.player_left[5], tex.player_left[6],
				tex.player_left[7], tex.player_left[8], tex.player_left[9],
				tex.player_left[10]/* ,tex.player_left[11] */);

		playerIdle_Left = new Animation(10, tex.player_left[0], tex.player_left[1], tex.player_left[2],
				tex.player_left[3]);


	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;

		if (fall || jump) {

			velY += gravity;

			if (velY > maxSpeed) {
				velY = maxSpeed;
			}
		}
		collision(object);
		playerWalk.runAnimation();
		playerIdle.runAnimation();
		playerWalk_left.runAnimation();
		playerIdle_Left.runAnimation();

	}

	private void collision(LinkedList<GameObject> object) {

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject temp = handler.object.get(i);
			ObjectId tempID = temp.getId();

			if (tempID == ObjectId.Block) {
				if (getBounds().intersects(temp.getBounds())) {
					y = temp.getY() - height;
					velY = 0;
					fall = false;
					jump = false;
				} else
					fall = true;
				if (getBoundsTop().intersects(temp.getBounds())) {
					y = (temp.getY() + height / 2) - 10;
					velY = 0;
				}
				if (getBoundsLeft().intersects(temp.getBounds())) {
					x = temp.getX() + 32;
				}

				if (getBoundsRight().intersects(temp.getBounds())) {
					x = temp.getX() - 32;
				}
			} else if (tempID == ObjectId.Spike) {
				if (getBoundsSpike().intersects(temp.getBounds())) {
					playSound();
					x = spawnX;
					y = spawnY;
					Game3.death++;
				}
				if (getBoundsTopSpike().intersects(temp.getBounds())) {
					playSound();
					x = spawnX;
					y = spawnY;
					Game3.death++;
				}

				if (getBoundsRightSpike().intersects(temp.getBounds())) {
					playSound();
					x = spawnX;
					y = spawnY;
					Game3.death++;
				}
				if (getBoundsLeftSpike().intersects(temp.getBounds())) {
					playSound();
					x = spawnX;
					y = spawnY;
					Game3.death++;
				}
			} else if (tempID == ObjectId.Button) {
				if (getBounds().intersects(temp.getBounds())) {

					int searchId = ((Button) temp).getButtonId();
					((Button) temp).setButtonId(0);
					GameObject tempDoor = null;
					temp.setId(null);

					int doorCounter = 0;
					for (int j = 0; j < handler.object.size(); j++) {
						GameObject tempObj = handler.object.get(j);
						if (tempObj.getId() == ObjectId.Door && ((Door) tempObj).getDoorId() == searchId) {
							tempDoor = tempObj;
						} else if (tempObj.getId() == ObjectId.Button && ((Button) tempObj).getButtonId() == searchId) {
							doorCounter++;
						}

					}

					if (tempDoor != null && doorCounter == 0) {
						((Door) tempDoor).setDoorId(0);
						tempDoor.setId(null);
					}

				}
			} else if (tempID == ObjectId.Door) {

				if (getBoundsLeft().intersects(temp.getBounds())) {
					x = temp.getX() + 32;
				}

				if (getBoundsRight().intersects(temp.getBounds())) {
					x = temp.getX() - 32;
				}
			} else if (tempID == ObjectId.Elevator) {
				if (getBounds().intersects(temp.getBounds())) {
					y = temp.getY() - height;
					if (temp.getVelY() > 0) {
						velY = 2;
					} else
						velY = 0;
					fall = false;
					jump = false;
				}

			} else if (tempID == ObjectId.Spawn) {
				if (getBounds().intersects(temp.getBounds())) {
					y = temp.getY() - height;
					velY = 0;
					fall = false;
					jump = false;
					spawnX = (int) temp.getX();
					spawnY = (int) temp.getY() - 32;
					// System.out.println("set");
				}
			} else if (tempID == ObjectId.Trophy) {
				if (getBounds().intersects(temp.getBounds())) {
					Game3.state = State.GAMEOVER;
					gameOver = true;
				}
			} else if (tempID == ObjectId.Flag) {

				if (getBoundsLeft().intersects(temp.getBounds())) {
					handler.stwitcLvl();
				}

				if (getBoundsRight().intersects(temp.getBounds())) {
					handler.stwitcLvl();
				}
				if (getBoundsTop().intersects(temp.getBounds())) {
					handler.stwitcLvl();
				}
			}
		}
	}

	public void render(Graphics g) {
		if (jump) {
			if (velY < 0 && facing == 1) {
				g.drawImage(tex.player_jump[2], (int) x - 45, (int) y - 43, null);
			} else if (velY > 0 && facing == 1) {
				g.drawImage(tex.player_jump[5], (int) x - 45, (int) y - 43, null);
			}
			if (velY < 0 && facing == -1) {
				g.drawImage(tex.player_jump[0], (int) x - 50, (int) y - 43, null);
			} else if (velY > 0 && facing == -1) {
				g.drawImage(tex.player_jump[1], (int) x - 50, (int) y - 43, null);

			}
		} else {
			if (facing == 1 && velX > 0)
				playerWalk.drawAnimation(g, (int) x - 45, (int) y - 43);
			else if (facing == -1 && velX < 0)
				playerWalk_left.drawAnimation(g, (int) x - 50, (int) y - 43);
			else if (facing == 1 && velX == 0)
				playerIdle.drawAnimation(g, (int) x - 45, (int) y - 43);
			else if (facing == -1 && velX == 0)
				playerIdle_Left.drawAnimation(g, (int) x - 50, (int) y - 43);
		}

		/*
		 * Graphics2D g2= (Graphics2D)g; g.setColor(Color.black);
		 * g2.draw(getBoundsSpike()); g2.draw(getBoundsRightSpike());
		 * g2.draw(getBoundsLeftSpike()); g2.draw(getBoundsTopSpike());
		 */

		/*
		 * Graphics2D g2 = (Graphics2D) g; g.setColor(Color.yellow);
		 * g2.draw(getBounds()); g2.draw(getBoundsRight()); g2.draw(getBoundsLeft());
		 * g2.draw(getBoundsTop());
		 */

	}

	public void playSound() {

		try {

			System.out.println(file.exists());
			if (file.exists()) {
				myClip = AudioSystem.getClip();
				ais = AudioSystem.getAudioInputStream(file.toURI().toURL());
				myClip.open(ais);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		myClip.start();
	}

	public Rectangle getBounds() {

		return new Rectangle((int) ((int) x + (width / 2) - (width / 2) / 2), (int) ((int) y + (height / 2)),
				(int) width / 2, (int) height / 2);
	}

	public Rectangle getBoundsTop() {

		return new Rectangle((int) ((int) x + (width / 2) - (width / 2) / 2), (int) y + 10, (int) width / 2,
				(int) height / 2);
	}

	public Rectangle getBoundsRight() {

		return new Rectangle((int) ((int) x + width - 5) - 1, (int) y + 15, (int) 5, (int) height - 20);
	}

	public Rectangle getBoundsLeft() {

		return new Rectangle((int) x, (int) y + 15, (int) 5, (int) height - 21);
	}

	// spike collision
	public Rectangle getBoundsSpike() {

		return new Rectangle((int) ((int) x + (width / 2) - (width / 2) / 2), (int) ((int) y + (height / 2)) - 5,
				(int) width / 2, (int) height / 2);
	}

	public Rectangle getBoundsTopSpike() {

		return new Rectangle((int) ((int) x + (width / 2) - (width / 2) / 2), (int) y + 6, (int) width / 2,
				(int) height / 2);
	}

	public Rectangle getBoundsRightSpike() {

		return new Rectangle((int) ((int) x + width - 5) - 5 - 1, (int) y + 7, (int) 5, (int) height - 12);
	}

	public Rectangle getBoundsLeftSpike() {

		return new Rectangle((int) x + 5, (int) y + 7, (int) 5, (int) height - 12);
	}
}
