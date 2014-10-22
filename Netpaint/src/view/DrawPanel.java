package view;

/**
 * DrawPanel is a panel that the drawable objects are drawn on.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import model.Drawable;

public class DrawPanel extends JPanel {

	private List<Drawable> items;

	/**
	 * Constructs a DrawPanel and List to hold objects drawn.
	 */
	public DrawPanel() {
		items = new LinkedList<Drawable>();
		this.setPreferredSize(new Dimension(2000, 2000));
	}

	/**
	 * Adds drawable object to objects drawn list.
	 * @param object Drawable object.
	 */
	public void addObject(Drawable object) {
		items.add(object);
		repaint();
	}

	/**
	 * Removes last drawable object added to the drawn object list.
	 * Useful for ghosting,
	 */
	public void remove() {
		if (items.size() > 0)
			items.remove(items.size() - 1);
		repaint();
	}

	/**
	 * Loops through all drawn objects and calls the Paint component for
	 * that object.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, 2000, 2000);
		for (Drawable drawItem : items) {
			drawItem.paintComponent(g);
		}
	}
}
