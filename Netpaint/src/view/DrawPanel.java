package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import shapes.Drawable;

/**
 * DrawPanel is a panel that the {@link Drawable} objects are drawn on.
 * 
 * @author Abhishek Rane
 * @author Bryce Hammod
 */
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
	 * 
	 * @param object
	 *            Drawable object.
	 */
	public void addObject(Drawable object) {
		items.add(object);
		repaint();
	}

	/**
	 * Removes last drawable object added to the drawn object list. Useful for
	 * ghosting,
	 */
	public void remove() {
		if (items.size() > 0)
			items.remove(items.size() - 1);
		repaint();
	}

	/**
	 * Loops through all drawn objects and calls the Paint component for that
	 * object.
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

	/**
	 * Called when a {@link controller.Server} sends an
	 * {@link command.UpdateClientCommand} to the {@link controller.Client} that
	 * contains the {@link view.NetPaintClient} that contains this drawpanel.
	 * 
	 * @param items
	 *            The new list of {@link shapes.Drawable} items.
	 */
	public void update(List<Drawable> items) {
		this.items = items;
		repaint();
	}
}
