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

public class Menu {
	Font f = null;
	Font f1 = null;
	public Rectangle playButton = new Rectangle(300, 180, 200, 50);
	public Rectangle helpButton = new Rectangle(300, 280, 200, 50);
	public Rectangle quitButton = new Rectangle(300, 380, 200, 50);
	public Rectangle highScore = new Rectangle(300, 480, 200, 50);

	public Menu() {

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

	}

	public void render(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString("Late For Boxing", (Game3.width / 6) + 10, 130);

		g.setFont(f1);
		g.drawString("Play", playButton.x + 65, playButton.y + 45);
		g2.draw(playButton);
		g.drawString("Tips", playButton.x + 65, helpButton.y + 45);
		g2.draw(helpButton);
		g.drawString("Quit", playButton.x + 65, quitButton.y + 45);
		g2.draw(quitButton);
		g.drawString("High scores", playButton.x + 10, highScore.y + 45);
		g2.draw(highScore);
	}

}
