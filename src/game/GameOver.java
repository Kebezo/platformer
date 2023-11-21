package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import objects.Player;

public class GameOver {
	Font f = null;
	Font f1 = null;
	public Rectangle highScore = new Rectangle(300, 400, 200, 50);
	BufferedReader br;
	BufferedReader br1;
	BufferedWriter bw;
	BufferedWriter bw1;

	public GameOver() {

		try {
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

		try {
			br = new BufferedReader(new FileReader("resources/best_time.txt"));
			br1 = new BufferedReader(new FileReader("resources/best_death.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void render(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g.setFont(f);
		g.setColor(Color.WHITE);
		if (Player.gameOver)
			g.drawString("You Win", (Game3.width / 3) - 20, 100);
		else
			g.drawString("You lose", (Game3.width / 3) - 20, 100);

		g.setFont(f1);

		if (Player.gameOver)
			g.drawString("You had " + Game3.returnTime() + " time Left", (Game3.width / 3) - 50, 200);
		else
			g.drawString("Better luck next time", (Game3.width / 3) - 50, 200);
		g.drawString("you died " + Game3.death + " times", (Game3.width / 3) - 50, 300);

		g.drawString("High Scores", highScore.x + 5, highScore.y + 45);
		g2.draw(highScore);

		if (Player.gameOver) {
			try {
				while (br.ready()) {
					bw = new BufferedWriter(new FileWriter("resources/best_time.txt"));
					if (Integer.parseInt(br.readLine()) > 900 - Game3.time) {
						bw.write(900 - Game3.time);
					}
				}
				while (br1.ready()) {
					bw1 = new BufferedWriter(new FileWriter("resources/best_death.txt"));
					if (Integer.parseInt(br1.readLine()) < Game3.death) {
						bw.write(Game3.death);
					}
				}
			} catch (Exception e) {

			}
		}
	}

}
