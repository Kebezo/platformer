package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import framework.GameObject;
import framework.ObjectId;

public class Flag extends GameObject {

	BufferedImage image;
	InputStream is;

	public Flag(float x, float y, ObjectId id) {
		super(x, y, id);

		try {
			is = new FileInputStream("resources/flag.png");

			image = ImageIO.read(is);

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
	}

	public void tick(LinkedList<GameObject> oject) {
		// TODO Auto-generated method stub

	}

	public void render(Graphics g) {

		g.drawImage(image, (int) x, (int) y, 64, 128, null);

	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 64, 128);
	}
}
