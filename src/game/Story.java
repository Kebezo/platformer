package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class Story {
	Font f = null;
	Font f1 = null;
	public Rectangle next = new Rectangle(670, 540, 100, 50);
	public static int menuNum = 0;
	public static boolean b = true;
	BufferedReader br = null;
	InputStream is = null;
	public static ArrayList<String> story = new ArrayList<>();

	public Story() {

		try {
			is = new FileInputStream("resources/story.txt");

			f = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("resources/font.otf")))
					.deriveFont(Font.BOLD, 130);
			f1 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("resources/font.otf")))
					.deriveFont(Font.BOLD, 65);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStreamReader r = new InputStreamReader(is);
		br = new BufferedReader(r);
		String line;
		try {
			while ((line = br.readLine()) != null) {
				story.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void render(Graphics g) {

		int y = 100;
		Graphics2D g2 = (Graphics2D) g;
		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString("Story", (Game3.width / 3) + 80, 100);
		g.setFont(f1);
		g.drawString(story.get(menuNum), 40, 250);
		g.drawString(story.get(menuNum + 1), 40, 350);
		g.drawString(story.get(menuNum + 2), 40, 450);
		g2.draw(next);
		if (menuNum + 3 >= story.size() - 1) {
			g.drawString("Play", 680, 588);
		} else
			g.drawString("Next", 680, 588);
	}
}
