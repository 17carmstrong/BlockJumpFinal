package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class Menu {
	// Declares a blank menu image and x/y values to store where the menu would start
	BufferedImage menuImg;
	int topY, leftX;

	// Creates the menu constructor so when it is used later it will draw the image at the x and y provided, changed
	// for the same reason the button class was changed and deciding the menus didn't actually have to do anything
	public Menu(int xStart, int yStart, BufferedImage menu) {
		this.topY = yStart;
		this.leftX = xStart;
		this.menuImg = menu;
	}
}