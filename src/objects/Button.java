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

public class Button extends GameObject {

	private int buttonId;
	BufferedImage image;
	InputStream is;
	
	public Button(float x, float y, ObjectId id, int buttonId) {
		super(x, y, id);
		this.buttonId = buttonId;
		try {
			 is = new FileInputStream("resources/key.png");
		
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
		if (buttonId != 0) {
			//g.setColor(Color.decode("#FF4500"));
			//g.fillRect((int) x, (int) y, 32, 32);
			g.drawImage(image, (int) x, (int) y, 32, 32, null);
		}
	}

	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, 32, 18);
	}

	public int getButtonId() {
		return buttonId;
	}

	public void setButtonId(int buttonId) {
		this.buttonId = buttonId;
	}

}
