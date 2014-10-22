package model;

import java.awt.Color;
import java.awt.Graphics;

public class Oval extends Drawable {

	public Oval(int x, int y, int height, int width, Color color) {
		super(calculateX(x, width), calculateY(y, height), calculateHeight(height), calculateWidth(width), color);
	}

	public static int calculateX(int x, int width){
		if(width < 0){
			return x + width;
		}
		return x;
	}
	
	public static int calculateY(int y, int height){
		if(height < 0){
			return y + height;
		}
		return y;
	}
	
	public static int calculateHeight(int height){
		return Math.abs(height);
	}
	
	public static int calculateWidth(int width){
		return Math.abs(width);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(color);
		g.fillOval(point.x, point.y, width, height);
	}

}
