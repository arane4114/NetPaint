package shapes;


import java.awt.Color;
import java.awt.Graphics;

/**
 * Rectangle is a {@link Drawable} object.
 * @author Abhishek Rane
 * @author Bryce Hammod
 */
public class Rectangle extends Drawable {

	/**
	 * Constructs a Rectangle. 
	 * Calculations are done on the arguments to find true top left point
	 * and height and width. 
	 * @param x Initial x point.
	 * @param y Initial y point.
	 * @param height Height of object.
	 * @param width Width of object.
	 * @param color Color of object.
	 */
	public Rectangle(int x, int y, int height, int width, Color color) {
		super(calculateX(x, width), calculateY(y, height), calculateHeight(height), calculateWidth(width), color);
	}
	
	/**
	 * Finds true x value for the top left of the Rectangle.
	 * @param x Starting x point from initial click.
	 * @param width Width of shape, can be negative.
	 * @return Returns the true starting x coordinate.
	 */
	public static int calculateX(int x, int width){
		if(width < 0){
			return x + width;
		}
		return x;
	}
	
	/**
	 * Finds true y value for the top left of the Rectangle. 
	 * @param y Starting y point from initial click.
	 * @param height Height of shape, can be negative.
	 * @return The true starting y coordinate.
	 */
	public static int calculateY(int y, int height){
		if(height < 0){
			return y + height;
		}
		return y;
	}
	
	/**
	 * Takes absolute value of height and returns that value. 
	 * @param height Height of shape, can be negative.
	 * @return Returns a positive height through Math.abs().
	 */
	public static int calculateHeight(int height){
		return Math.abs(height);
	}
	
	/**
	 * Takes absolute value of width and returns that value. 
	 * @param width Width of shape, can be negative.
	 * @return Returns a positive width through Math.abs().
	 */
	public static int calculateWidth(int width){
		return Math.abs(width);
	}

	/**
	 * Paint Component for printing an Rectangle object to a location.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(color);
		g.fillRect(point.x, point.y, width, height);
	}
}
