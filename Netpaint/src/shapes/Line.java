package shapes;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Line is an {@link Drawable} object;
 * @author Abhishek Rane
 * @author Bryce Hammod
 */
public class Line extends Drawable {

	/**
	 * Constructs a Line. 
	 * @param x1 Initial x point.
	 * @param y1 Initial y point.
	 * @param x2 Height of object.
	 * @param y2 Width of object.
	 * @param color Color of object.
	 */
	public Line(int x1, int y1, int x2, int y2, Color color) {
		super(x1, y1, x2, y2, color);
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
