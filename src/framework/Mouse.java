package framework;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.Game3;
import game.Game3.State;
import game.Help;
import game.Story;
import game.Window;

public class Mouse implements MouseListener {
	int i = 1;

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (Game3.state == State.MENU) {
			// play
			if (mx >= 300 && mx <= 500) {
				if (my >= 180 && my <= 230) {
					if (i == 1) {
						Game3.state = Game3.State.STORY;
						i++;
					} else
						Game3.state = Game3.State.GAME;

				}
			}

			// quit
			if (mx >= 300 && mx <= 500) {
				if (my >= 380 && my <= 430) {
					System.exit(1);
				}
			}
			// help
			if (mx >= 300 && mx <= 500) {
				if (my >= 280 && my <= 330) {
					Game3.state = Game3.State.HELP;
				}
			}

			// quit
			if (mx >= 300 && mx <= 500) {
				if (my >= 480 && my <= 530) {
					Game3.state = Game3.State.HIGHSCORE;
				}
			}
		}

		if (Game3.state == State.HELP) {
			// next
			if (mx >= 670 && mx <= 770) {
				if (my >= 540 && my <= 580) {
					if (Help.menuNum >= Help.text.size() - 1) {
						Help.menuNum = 0;
					} else
						Help.menuNum++;

				}
			}
			// back
			if (mx >= 45 && mx <= 145) {
				if (my >= 540 && my <= 580) {
					Game3.state = State.MENU;
				}
			}

		}
		if (Game3.state == State.STORY) {
			// next
			if (mx >= 670 && mx <= 770) {
				if (my >= 540 && my <= 590) {
					if (Story.menuNum + 3 >= Story.story.size() - 1) {
						System.out.println("dasd");
						Game3.state = Game3.State.GAME;

					} else {
						Story.menuNum += 3;

					}

				}
			}

		}
		if (Game3.state == State.GAMEOVER) {

			// highscore
			if (mx >= 300 && mx <= 500) {
				if (my >= 380 && my <= 430) {
					Game3.state = Game3.State.HIGHSCORE;
				}
			}
		}

		if (Game3.state == State.HIGHSCORE) {

			// back
			if (mx >= 300 && mx <= 500) {
				if (my >= 380 && my <= 430) {
					Game3.state = Game3.State.MENU;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
