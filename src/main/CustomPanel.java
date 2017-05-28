package main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import main.Math.Collisions;
import main.Math.DoublePoint;
import main.Math.Line;
import main.Objects.Box;
import main.Objects.Player;

public class CustomPanel extends JPanel implements KeyListener, MouseMotionListener, MouseListener, MouseWheelListener {
	public static double level = 0;
	public static Random RNG = new Random();
	public static boolean tempJumpLimiter = true;
	public static Keys keys = new Keys();
	public static Player p = new Player();
	public static ArrayList<Box> boxes = new ArrayList();
	public static short milliseconds = 0, seconds = 0, minutes = 0, hours = 0;

	// mn = main, ps = pause
	BufferedImage mnMenu, gmBut, psMenu, quit, pseBut, speedBut, highBut, backBut, gameOverMenu;

	CustomPanel() throws IOException {
		URL fileURL = getClass().getResource("MainMenu.png");
		mnMenu = ImageIO.read(fileURL);
		fileURL = getClass().getResource("PauseMenu.png");
		psMenu = ImageIO.read(fileURL);
		fileURL = getClass().getResource("gmBut.png");
		gmBut = ImageIO.read(fileURL);
		fileURL = getClass().getResource("SpeedMode.png");
		speedBut = ImageIO.read(fileURL);
		fileURL = getClass().getResource("HighMode.png");
		highBut = ImageIO.read(fileURL);
		fileURL = getClass().getResource("BackButton.png");
		backBut = ImageIO.read(fileURL);
		fileURL = getClass().getResource("GameOver.png");
		gameOverMenu = ImageIO.read(fileURL);
		fileURL = getClass().getResource("PauseButton.png");
		pseBut = ImageIO.read(fileURL);
	}

	int selX, selY;
	boolean click = false;
	Menu mainMenu = new Menu(0, 0, mnMenu);
	Menu pauseMenu = new Menu(0, 0, psMenu);
	Menu gameOver = new Menu(0, 0, gameOverMenu);
	Button gmSelect = new Button(250, 250, gmBut, 300, 100);
	Button gmHigh = new Button(250, 250, highBut, 300, 100);
	Button gmFast = new Button(250, 450, speedBut, 300, 100);
	Button back = new Button(0, 0, backBut, 50, 50);
	Button pauseB = new Button(750, 0, pseBut, 50, 50);
	// Changes based on button presses
	static int menuNum = 0;

	// notes
	// boxes array goes from bottom boxes to top boxes
	public static void startup() {
		boxes.add(new Box(-1, 0, 802, 2));
		boxes.get(0).onSurface = true;
		boxes.get(0).yVel = 0;
	}

	public void run() throws IOException {
		if (menuNum == 0 && click) {
			if (gmSelect.collision(selX, selY, gmSelect)) {
				menuNum = 1;
			}
			click = false;
		} else if (menuNum == 1 && click) {
			if (gmHigh.collision(selX, selY, gmHigh) || gmFast.collision(selX, selY, gmFast)) {
				menuNum = 3;
			} else if (back.collision(selX, selY, back)) {
				menuNum = 0;
			}
			click = false;
		} else if (menuNum == 3) {
			if(pauseB.collision(selX, selY, pauseB)){
				menuNum = 5;
			}
			level += 0.03;
			milliseconds += ComsciFinalProject.rate;
			if (milliseconds >= 1000) {
				milliseconds = 0;
				seconds++;
				if (seconds >= 60) {
					seconds = 0;
					minutes++;
					if (minutes >= 60) {
						minutes = 0;
						hours++;
						if (hours >= 24) {
							hours = 0;
						}
					}
				}
				if (milliseconds % 40 < ComsciFinalProject.rate) {
					addRandomBox();
				}
			}
			if (Keys.B_up) {
				if (tempJumpLimiter) {
					p.up();
					tempJumpLimiter = false;
				}
			} else {
				tempJumpLimiter = true;
			}
			if (Keys.B_down) {
				p.down();
			}
			if (Keys.B_left && !Keys.B_right) {
				p.left();
			} else if (Keys.B_right && !Keys.B_left) {
				p.right();
			}
			for (int b1 = 0; b1 < boxes.size(); b1++) {
				Box box = boxes.get(b1);
				DoublePoint collision = Collisions.CollidesLineRect(
						new Line(p.getX(), p.getY(), p.getX() + p.getXVel(), p.getY() + p.getYVel()), box.x, box.y,
						box.x + box.width, box.y + box.height);
				if (collision != null) {
					p.setOnBox(box, collision);
				}
				// check for collisions between boxes and stop the higher box
				box.run();
				if (!box.onSurface) {
					for (int b2 = 0; b2 < boxes.size(); b2++) {
						Box box2 = boxes.get(b2);
						if (box2.onSurface && Collisions.Collides2d(box.x, box.y, box.x + box.width, box.y + box.height,
								box2.x, box2.y, box2.x + box2.width, box2.y + box2.height)) {
							box.setOnSurface();
						}
					}
				}
			}
			p.run();
			// fix view to players height if they are moving faster
			if (p.getY() < -200 - level) {
				level = -200 - p.getY();
			}
		} else if (menuNum == 4) {

		}
		else if(menuNum == 5){
			if(click){
				menuNum = 3;
			}
		}
	}

	public static void gameOver() {
		milliseconds = 0;
		seconds = 0;
		minutes = 0;
		hours = 0;
		level = 0;
		boxes.clear();
		p = new Player();
		startup();
	}

	public static void addRandomBox() {
		int width = RNG.nextInt(75) + 25;
		Box box = new Box(RNG.nextInt(800 - width), -(int) level - 600, width, RNG.nextInt(75) + 25);
		boxes.add(0, box);
	}

	@Override
	public void paintComponent(Graphics g) {
		// Menu selection
		if (menuNum == 0) {
			// draws menu
			g.drawImage(mnMenu, mainMenu.leftX, mainMenu.topY, null);
			// draws buttons for menu
			g.drawImage(gmBut, gmSelect.xPlace, gmSelect.yPlace, null);
		} else if (menuNum == 1) {
			g.drawImage(mnMenu, mainMenu.leftX, mainMenu.topY, null);
			g.drawImage(highBut, gmHigh.xPlace, gmHigh.yPlace, null);
			// g.drawImage(speedBut, gmFast.xPlace, gmFast.yPlace, null); Didn't
			// have time to add the speed mode
			g.drawImage(backBut, back.xPlace, back.yPlace, null);
		} else if (menuNum == 3) {
			Colours.hueShift();
			g.setColor(Colours.Black);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(Colours.getHueShift());
			// draw boxes
			for (Box box : boxes) {
				if (box.onSurface) {
					g.fillRect((int) box.x, (int) box.y + (int) level + 400, (int) box.width, (int) box.height);
				} else {
					g.drawRect((int) box.x, (int) box.y + (int) level + 400, (int) box.width, (int) box.height);
				}
			}
			// draw player
			g.drawRect((int) p.getX() - 10, (int) p.getY() - 10 + (int) level + 400, 20, 20);

			g.drawString("Time: " + hours + ':' + minutes + ':' + seconds + ':' + milliseconds, 10, 10);
			g.drawString("X: " + p.getX(), 10, 30);
			g.drawString("Y: " + p.getY(), 10, 50);
			g.drawString("Level: " + level, 10, 70);
			g.drawImage(pseBut, pauseB.xPlace, pauseB.yPlace, null);
		} else if (menuNum == 4) {
			g.drawImage(gameOverMenu, gameOver.leftX, gameOver.topY, null);
		}
		else if(menuNum == 5){
			g.drawImage(psMenu, pauseMenu.leftX, pauseMenu.topY, null);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 32: {
			if (!Keys.B_up) {
				Keys.B_up = true;
			}
		}
			break;
		case 65: {
			if (!Keys.B_left) {
				Keys.B_left = true;
			}
		}
			break;
		case 83: {
			if (!Keys.B_down) {
				Keys.B_down = true;
			}
		}
			break;
		case 68: {
			if (!Keys.B_right) {
				Keys.B_right = true;
			}
		}
			break;
		case 78: {
			addRandomBox();
		}
			break;
		default: {
			System.out.println("key " + e.getKeyCode() + " pressed");
		}
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case 32: {
			Keys.B_up = false;
		}
			break;
		case 65: {
			Keys.B_left = false;
		}
			break;
		case 83: {
			Keys.B_down = false;
		}
			break;
		case 68: {
			Keys.B_right = false;
		}
			break;
		case 82: {
			menuNum = 3;
			gameOver();
		}
			break;
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		selX = e.getX();
		selY = e.getY();
		System.out.println(selX + " " + selY);
		click = true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		click = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

	}

	public int getMenuNum() {
		return menuNum;
	}

	public static void setMenuNum(int setTo) {
		menuNum = setTo;
	}
}