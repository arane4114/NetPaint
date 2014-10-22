package model;

/**
 * Line is an drawable object;
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 */
import java.awt.Color;
import java.awt.Graphics;

public class Line extends Drawable {

	/**
	 * Constructs a Line.
	 * 
	 * @param x Initial x point.
	 * @param y Initial y point.
	 * @param height Height of object.
	 * @param width Width of object.
	 * @param color Color of object.
	 */
	public Line(int x, int y, int height, int width, Color color) {
		super(x, y, height, width, color);
	}

	/**
	 * Paint Component for printing an Line object to a location.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(color);
		g.drawLine(point.x, point.y, point.x + width, point.y + height);
	}

}
