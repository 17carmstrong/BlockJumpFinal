package main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

// Changed class a bit and changed the actual part where it is output to the screen to the custom panel
public class Button {
	int xPlace;
	int yPlace;
	int length;
	int height;
	BufferedImage buttonPic;
	// A constructor which takes an image, an x coordinate, and a y coordinate as well as the height and length to be
	// used for the collision detection
	public Button(int xPos, int yPos, BufferedImage buttonImg, int length, int height) {
		this.xPlace = xPos;
		this.yPlace = yPos;
		this.length = length;
		this.height = height;
		this.buttonPic = buttonImg;
	}
	// A method to check if the selected x and y values are inside the button, if they are the method returns true
	// If they aren't the method returns false
	public boolean collision(int selX, int selY, Button check) {
		if (selX <= (check.xPlace + check.length) && selX >= check.xPlace && selY >= check.yPlace
				&& selY <= check.yPlace + check.height) {
			return true;
		}
		else{
			return false;
		}
	}
}