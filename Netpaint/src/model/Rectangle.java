package model;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Drawable {

	public Rectangle(int x, int y, int height, int width, Color color) {
		super(x, y, height, width, color);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(color);
		g.fillRect(point.x, point.y, width, height);
	}
}
