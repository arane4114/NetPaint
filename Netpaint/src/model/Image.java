package model;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

import javax.imageio.ImageIO;

/**
 * Image is an {@link Drawable} object.
 * It is an image of a doge <img src="../../doge.jpeg"> that can be drawn to the scroll pane.
 * @author Abhishek Rane
 * @author Bryce Hammod
 */
public class Image extends Drawable implements Serializable {
	private transient BufferedImage doge = null;

	/**
	 * Constructs a Image. 
	 * Points can be negative for flipped image printing.
	 * @param x Initial x point.
	 * @param y Initial y point.
	 * @param height Height of object.
	 * @param width Width of object.
	 * @param color Color of object.
	 */
	public Image(int x, int y, int height, int width, Color color) {
		super(x, y, height, width, color);
		try {
			doge = ImageIO.read(new File("doge.jpeg"));
		} catch (Exception e) {
			System.err.println("Couldnt print a doge");
		}
	}

	/**
	 * Paint Component for printing an Image object to a location.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		if(doge == null){
			try {
				doge = ImageIO.read(new File("doge.jpeg"));
			} catch (Exception e) {
				System.err.println("Couldnt print a doge");
			}
		}
		g.drawImage(doge, point.x, point.y, width, height, null);
	}
}
