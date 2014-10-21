package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import model.Drawable;

public class DrawPanel extends JPanel {

	private List<Drawable> items;

	public DrawPanel() {
		items = new LinkedList<Drawable>();
		this.setPreferredSize(new Dimension(2000, 2000));
	}

	public void addObject(Drawable object) {
		items.add(object);
		repaint();
	}

	public void remove() {
		if (items.size() > 0)
			items.remove(items.size() - 1);
		repaint();
	}

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
