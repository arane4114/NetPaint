package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Image extends Drawable {
	private BufferedImage doge;

	public Image(int x, int y, int height, int width, Color color) {
		super(x, y, height, width, color);
		try {
			doge = ImageIO.read(new File("Doge.jpeg"));
		} catch (Exception e) {
			System.err.println("Couldnt print a doge");
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.drawImage(doge, point.x, point.y, width, height, null);
	}

}
