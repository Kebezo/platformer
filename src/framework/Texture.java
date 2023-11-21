package framework;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class Texture {

	SpriteSheet ss;
	SpriteSheet ss1;
	private BufferedImage player_sheet = null;
	private BufferedImage player_sheet_left = null;
	InputStream is;
	InputStream is1;
	public BufferedImage[] player = new BufferedImage[12];
	public BufferedImage[] player_left = new BufferedImage[12];
	public BufferedImage[] player_jump = new BufferedImage[8];

	public Texture() {

		try {
			is = new FileInputStream("resources/player_sheet.png");
			is1 = new FileInputStream("resources/player_sheet_mirror.png");
			player_sheet = ImageIO.read(is);
			player_sheet_left = ImageIO.read(is1);

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}

		ss = new SpriteSheet(player_sheet);
		ss1 = new SpriteSheet(player_sheet_left);
		getTextures();
	}

	private void getTextures() {
		player[0] = ss.grabImage(1, 1, 128, 128); // idle 1
		player[1] = ss.grabImage(2, 1, 128, 128); // idle 2
		player[2] = ss.grabImage(3, 1, 128, 128); // idle 3
		player[3] = ss.grabImage(4, 1, 128, 128); // idle 4

		player[4] = ss.grabImage(1, 2, 128, 128); // walk 1
		player[5] = ss.grabImage(2, 2, 128, 128); // walk 2
		player[6] = ss.grabImage(3, 2, 128, 128); // walk 3
		player[7] = ss.grabImage(4, 2, 128, 128); // walk 4
		player[8] = ss.grabImage(5, 2, 128, 128); // walk 5
		player[9] = ss.grabImage(6, 2, 128, 128); // walk 6
		player[10] = ss.grabImage(7, 2, 128, 128); // walk 7
		player[11] = ss.grabImage(8, 2, 128, 128); // walk 8

		player_left[0] = ss1.grabImage(16, 1, 128, 128); // idle 1
		player_left[1] = ss1.grabImage(15, 1, 128, 128); // idle 2
		player_left[2] = ss1.grabImage(14, 1, 128, 128); // idle 3
		player_left[3] = ss1.grabImage(13, 1, 128, 128); // idle 4

		player_left[4] = ss1.grabImage(16, 2, 128, 128); // walk 1
		player_left[5] = ss1.grabImage(15, 2, 128, 128); // walk 1
		player_left[6] = ss1.grabImage(14, 2, 128, 128); // walk 1
		player_left[7] = ss1.grabImage(13, 2, 128, 128); // walk 1
		player_left[8] = ss1.grabImage(12, 2, 128, 128); // walk 1
		player_left[9] = ss1.grabImage(10, 2, 128, 128); // walk 1
		player_left[10] = ss1.grabImage(9, 2, 128, 128); // walk 1
		// player_left[11] = ss1.grabImage(8, 2, 128, 128); // walk 1

		player_jump[0] = ss1.grabImage(14, 3, 128, 128); // jump
		player_jump[1] = ss1.grabImage(11, 3, 128, 128); // jump
		player_jump[2] = ss.grabImage(3, 3, 128, 128); // jump
		player_jump[3] = ss.grabImage(4, 3, 128, 128); // jump
		player_jump[4] = ss.grabImage(5, 3, 128, 128); // jump
		player_jump[5] = ss.grabImage(6, 3, 128, 128); // jump
		player_jump[6] = ss.grabImage(7, 3, 128, 128); // jump
		player_jump[7] = ss.grabImage(8, 3, 128, 128); // jump
	}

}
