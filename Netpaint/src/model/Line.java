package model;

import java.awt.Color;
import java.awt.Graphics;

public class Line extends Drawable {

	public Line(int x, int y, int height, int width, Color color) {
		super(x, y, height, width, color);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(color);
		g.drawLine(point.x, point.y, point.x + width, point.y + height);
	}

}
