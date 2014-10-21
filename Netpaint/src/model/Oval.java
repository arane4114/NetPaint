package model;

import java.awt.Color;
import java.awt.Graphics;

public class Oval extends Drawable {

	public Oval(int x, int y, int height, int width, Color color) {
		super(x, y, height, width, color);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(color);
		g.fillOval(point.x, point.y, width, height);
	}

}
