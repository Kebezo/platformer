package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import framework.GameObject;
import framework.ObjectId;
import objects.Block;
import objects.Door;
import objects.Elevator;
import objects.ElevatorStop;
import objects.Flag;
import objects.Player;
import objects.Spawn;
import objects.Spike;
import objects.Trophy;

public class Handler {

	int openId = 1;
	int openId1 = 1;

	int elID = 0;
	int elStopID = 0;

	public BufferedImage level1 = null;
	InputStream is2;
	public BufferedImage level2 = null;
	InputStream is3;
	public BufferedImage level3 = null;
	InputStream is4;
	public LinkedList<GameObject> object = new LinkedList<>();

	private GameObject temp;

	public Camera cam;

	public Handler(Camera cam) {
		this.cam = cam;

		try {

			is2 = new FileInputStream("resources/map2.png");
			level1 = ImageIO.read(is2);
			
			is3 = new FileInputStream("resources/map3.png");
			level2 = ImageIO.read(is3);
			

			is4 = new FileInputStream("resources/map4.png");
			level3 = ImageIO.read(is4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void tick() {

		for (int i = 0; i < object.size(); i++) {

			temp = object.get(i);

			temp.tick(object);
		}
	}

	public void render(Graphics g) {

		for (int i = 0; i < object.size(); i++) {

			temp = object.get(i);

			temp.render(g);
		}
	}

	public void loadLevel(BufferedImage img) {
		int w = img.getWidth();
		int h = img.getHeight();

		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				// bere rgb
				int pixel = img.getRGB(i, j);
				int r = (pixel >> 16) & 0xff;
				int g = (pixel >> 8) & 0xff;
				int b = (pixel) & 0xff;

				// bela barva
				if (r == 255 && g == 255 && b == 255) {
					addObject(new Block(i * 32, j * 32, ObjectId.Block));
				}

				// modra
				if (r == 0 && g == 0 && b == 255) {
					addObject(new Player(i * 32, j * 32, this, cam, ObjectId.Player));
				}
				// rumena (spike)
				if (r == 255 && g == 255 && b == 0) {
					addObject(new Spike(i * 32, j * 32, ObjectId.Spike));
				}
				// zeleno modra
				if (r == 0 && g == 127 && b == 127) {
					addObject(new Spawn(i * 32, j * 32, ObjectId.Spawn));
				}
				// rjava
				if (r == 140 && g == 70 && b == 20) {
					// int openId = 1;
					addObject(new Door(i * 32, j * 32, ObjectId.Door, openId));
					openId++;
				}
				// oranna
				if (r == 255 && g == 70 && b == 0) {

					addObject(new objects.Button(i * 32, j * 32, ObjectId.Button, openId1));
					openId1++;
				}
				// svetlo siva
				if (r == 169 && g == 169 && b == 169) {
					addObject(new Elevator(i * 32, j * 32, ObjectId.Elevator, true, elID));
					elID++;
				}
				// siva
				if (r == 128 && g == 128 && b == 128) {
					addObject(new ElevatorStop(i * 32, j * 32, ObjectId.ElevatorStop, elStopID));
					elStopID++;
				}
				// zelena
				if (r == 0 && g == 255 && b == 0) {
					addObject(new Trophy(i * 32, j * 32, ObjectId.Trophy));
				}
				// cyan
				if (r == 0 && g == 255 && b == 255) {
					addObject(new Flag(i * 32, j * 32, ObjectId.Flag));
				}
			}
		}
	}

	public void stwitcLvl() {
		clearLvl();
		cam.setX(0);

		switch (Game3.lvl) {
		case 1:
			loadLevel(level1);
			Game3.lvl++;
			break;
		case 2:
			loadLevel(level2);
			Game3.lvl++;
			break;
		case 3:
			loadLevel(level3);
			Game3.lvl++;
			break;
		}
	}

	public void clearLvl() {
		object.clear();
	}

	public void addObject(GameObject object) {
		this.object.add(object);
	}

	public void removeObject(GameObject object) {
		this.object.remove(object);
	}

}
