package main;

import java.io.IOException;
import javax.swing.JFrame;
/*
 * Explanation of any changes made:
 * Most chanes were made because of the idea that this would be a bit more simple than expected or there was a 
 * better way to organize everything. The collisions class is one example where it was more simple to have 
 * the collisions in its own class so it could be utilized by both the player and box classes. The ObjectBase
 * class was made because the program specification didn't mention a floor which we later learned was needed.
 * Changes to the button and menu class were made so all of the drawing could be done in the CustomPanel class
 * rather than each class individually. The movement class was taken out because it made more sense to include
 * that information in the Player class. Finally, we scrapped the speed gamemode in favour of the high score
 * gamemode because we didn't have time to implement both; however, there is still a timer if the user wants
 * to play in that way.
 */
public class ComsciFinalProject {
	public static int rate = 16;

	public static void main(String[] args) throws IOException, InterruptedException {
		CustomPanel panel = new CustomPanel();
		boolean killswitch = false;
		int windowWidth, windowHeight;
		JFrame gameWindow = new JFrame();
		gameWindow.setTitle("block jumperino");
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Thread.sleep(2);

		gameWindow.setSize(806, 600);
		gameWindow.setLocationRelativeTo(null);
		gameWindow.setResizable(false);
		gameWindow.setVisible(true);
		Thread.sleep(2);

		panel.setSize(800, 600);
		panel.repaint();
		gameWindow.setContentPane(panel);
		panel.requestFocus();
		Thread.sleep(2);
		panel.addKeyListener(panel);
		Thread.sleep(1);
		panel.addMouseMotionListener(panel);
		Thread.sleep(1);
		panel.addMouseListener(panel);
		Thread.sleep(1);
		panel.addMouseWheelListener(panel);
		Thread.sleep(2);

		windowWidth = gameWindow.getWidth();
		windowHeight = gameWindow.getHeight();
		CustomPanel.startup();
		Thread.sleep(2);

		while (!killswitch) {
			windowWidth = gameWindow.getWidth();
			windowHeight = gameWindow.getHeight();
			panel.setSize(windowWidth, windowHeight);
			panel.run();
			Thread.sleep(rate);
			panel.repaint();
		}
	}
}