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
import java.io.FileReader;
import java.io.IOException;

import com.sun.javafx.font.Disposer;

import objects.Player;

public class HighScore {
	Font f = null;
	Font f1 = null;
	BufferedReader br;
	BufferedReader br1;
	String time;
	String death;
	public Rectangle retry = new Rectangle(300, 400, 200, 50);
	public Rectangle quitButton = new Rectangle(300, 380, 200, 50);
	Handler handler;

	public HighScore() {

		try {
			br = new BufferedReader(new FileReader("resources/best_time.txt"));
			br1 = new BufferedReader(new FileReader("resources/best_death.txt"));
			while (br.ready()) {
				time = br.readLine();
			}
			while (br1.ready()) {
				death = br1.readLine();
			}
			f = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("resources/font.otf")))
					.deriveFont(Font.BOLD, 130);
			f1 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("resources/font.otf")))
					.deriveFont(Font.BOLD, 60);
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

	}

	public void render(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString("High scores", (Game3.width / 3) - 70, 100);

		g.setFont(f1);
		g.drawString("Best time: " + time, (Game3.width / 3) - 30, 200);
		g.drawString("Most deaths: " + death, (Game3.width / 3) - 30, 300);

		g.drawString("Menu", quitButton.x + 65, quitButton.y + 45);
		g2.draw(quitButton);

	}
}
