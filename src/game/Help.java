package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Help {
	Font f = null;
	Font f1 = null;
	Font f2 = null;
	public Rectangle next = new Rectangle(670, 540, 100, 50);
	public Rectangle back = new Rectangle(45, 540, 100, 50);
	
	public static int menuNum = 0;
	public static ArrayList<String> text = new ArrayList<>();

	public Help() {

		try {
			f = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("resources/font.otf")))
					.deriveFont(Font.BOLD, 130);
			f1 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("resources/font.otf")))
					.deriveFont(Font.BOLD, 60);
			f2 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("resources/font.otf")))
					.deriveFont(Font.BOLD, 50);
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

		text.add("Move with arrow keys");
		text.add("Press P to pause");
		text.add("Press esc to exit");
		text.add("Walk on white blocks");
		text.add("Avoid red blocks");
		text.add("Jump mid-air if you haven't jumped yet");
		text.add("Open a door by collecting its key");
		text.add("Blue blocks are checkpoints");
		text.add("You are a cat");
		text.add("Gray blocks are elevators");
		text.add("Stick to the wall so you don't jump that high");
		text.add("Flags= new levels");
		text.add("Get to the trophy");
		text.add("You only have so much time");

	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		String s = menuNum + 1 + "/" + text.size();
		g.setFont(f2);
		g.drawString(s, 730, 50);
		Graphics2D g2 = (Graphics2D) g;
		g.setFont(f);

		g.drawString("Tips", (Game3.width / 3)+80, 100);

		g.setFont(f1);
		
		
		g.drawString(text.get(menuNum), 40, 400);
		g2.draw(next);
		g.drawString("Next", 685, 588);
		g2.draw(back);
		g.drawString("Back", 55, 588);
	}

}
