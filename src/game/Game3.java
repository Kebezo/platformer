package game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.sun.prism.Texture;

import framework.Key;
import framework.Mouse;
import framework.ObjectId;

public class Game3 extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4728216012042886240L;
	public static int width, height;
	private boolean run = false;
	private Thread thread;
	public BufferedImage level = null;
	public BufferedImage level1 = null;
	public BufferedImage icon = null;
	public long timer = 0;
	int playerId;
	AudioInputStream ais;
	Clip myClip;
	File file = new File("resources/song1.wav");
	String music = "resources/song1.wav3";
	InputStream is;
	InputStream is2;
	InputStream is1;
	InputStream iconIs;
	Font f1 = null;
	public static int time = 900;
	public static int death = 0;

	public enum State {
		MENU, GAME, GAMEOVER, HELP, STORY, HIGHSCORE
	};

	public static State state = State.MENU;

	// object
	Handler handler;

	Camera cam;

	static framework.Texture tex;

	private Menu menu;

	private Help help;

	private Story story;

	private GameOver gameOver;

	private HighScore highScore;

	public static int lvl = 1;

	private void init() {
		width = getWidth();
		height = getHeight();

		tex = new framework.Texture();

		try {
			is = new FileInputStream("resources/map1.png");
			iconIs = new FileInputStream("resources/icon.png");
			level = ImageIO.read(is);

			f1 = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File("resources/font.otf")))
					.deriveFont(Font.BOLD, 40);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FontFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		cam = new Camera(0, 0);

		handler = new Handler(cam);

		handler.loadLevel(level);
		// handler.addObject(new Player(100, 100, handler, ObjectId.Player));

		// handler.createLevel();

		for (int i = 0; i < handler.object.size(); i++) {
			if (handler.object.get(i).getId() == ObjectId.Player) {
				playerId = i;
				break;
			}
		}

		this.addKeyListener(new Key(handler, cam));
		this.addMouseListener(new Mouse());
	}

	public synchronized void start() {
		if (run)
			return;
		run = true;

		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		playSound();
		menu = new Menu();
		gameOver = new GameOver();
		help = new Help();
		story = new Story();
		highScore = new HighScore();

		while (run) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				updates++;
				delta--;

			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				if (state == State.GAME)
					time--;
				if (time == 0) {
					if (state == State.GAME)
						state = State.GAMEOVER;
				}
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void render() {

		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {

			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		Graphics2D g2 = (Graphics2D) g;

		////////////////////////////////
		if (!myClip.isActive()) {
			playSound();
		}

		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());

		g2.translate(cam.getX(), cam.getY());
		if (state == State.GAME) {
			handler.render(g);
		} else if (state == State.MENU) {
			menu.render(g);
		} else if (state == State.GAMEOVER) {
			gameOver.render(g);
		} else if (state == State.HELP) {
			help.render(g);
		} else if (state == State.STORY) {
			story.render(g);
		} else if (state == State.HIGHSCORE) {
			highScore.render(g);
		}
		g2.translate(-cam.getX(), -cam.getY());

		drawTime(g);

		////////////////////////////////
		g.dispose();
		bs.show();
	}

	private void tick() {
		if (state == State.GAME) {
			handler.tick();
			for (int i = 0; i < handler.object.size(); i++) {
				if (handler.object.get(i).getId() == ObjectId.Player) {
					cam.tick(handler.object.get(i));
				}
			}
			// cam.tick(handler.object.get(playerId));
		} else {

			cam.setX(0);
		}

	}

	public void playSound() {

		try {

			System.out.println(file.exists());
			if (file.exists()) {
				myClip = AudioSystem.getClip();
				ais = AudioSystem.getAudioInputStream(file.toURI().toURL());
				myClip.open(ais);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		myClip.start();
	}

	public static framework.Texture getInstance() {
		return tex;
	}

	public void drawTime(Graphics g) {
		g.setFont(f1);
		g.setColor(Color.DARK_GRAY);
		if (state == State.GAME) {
			g.drawString(returnTime(), 40, 31);
			g.drawString(Integer.toString(lvl) + "/4", 700, 31);
		}
	}

	public static String returnTime() {
		String s;
		int minutes = (time % 3600) / 60;
		int seconds = (time % 3600) % 60;
		s = Integer.toString(minutes) + ":" + Integer.toString(seconds);
		return s;
	}

	public static void main(String[] args) {
		new Window(800, 598, "game", new Game3());

	}

}
