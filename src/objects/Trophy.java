package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import framework.GameObject;
import framework.ObjectId;

public class Trophy extends GameObject {

	BufferedImage image;
	InputStream is;
	public Trophy(float x, float y, ObjectId id) {
		super(x, y, id);

		try {
			 is = new FileInputStream("resources/trophy.png");
		
				image = ImageIO.read(is);
			

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch ( IOException e1 ) {
			// TODO: handle exception
			e1.printStackTrace();
		}

	}

	public void tick(LinkedList<GameObject> object) {
	}

	public void render(Graphics g) {

		// g.setColor(Color.yellow);
		// g.fillRect((int) x, (int) y, 32, 64);
		g.drawImage(image, (int) x, (int) y, 64, 64, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 64, 64);
	}

}
