package model;

/**
 * Drawable is an object that can be drawn on a scroll pane.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;

public abstract class Drawable extends JPanel{
	protected Point point;
	protected int height;
	protected int width;
	protected Color color;

	/**
	 * This method returns the upper leftmost point for the drawable shape. It
	 * is implemented here as it will be the same for all drawable objects.
	 * 
	 * @return The upper left point of the object.
	 */
	public Point getUpperLeft() {
		return point;
	}
	
	/**
	 * Constructs a drawable object. 
	 * 
	 * @param x Initial x point.
	 * @param y Initial y point.
	 * @param height Height of object.
	 * @param width Width of object.
	 * @param color Color of object.
	 */
	public Drawable(int x, int y, int height, int width, Color color){
		this.point = new Point(x,y);
		this.height = height;
		this.width = width;
		this.color = color;
	}
	
	public abstract void paintComponent(Graphics g);
}
