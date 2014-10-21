package model;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Drawable {

	public Rectangle(int x, int y, int height, int width, Color color) {
		super(xChange(x, width), yChange(y, height), heightChange(height), widthChange(width), color);
	}
	
	public static int xChange(int x, int width){
		if(width < 0){
			return x + width;
		}
		return x;
	}
	
	public static int yChange(int y, int height){
		if(height < 0){
			return y + height;
		}
		return y;
	}
	
	public static int heightChange(int height){
		return Math.abs(height);
	}
	
	public static int widthChange(int width){
		return Math.abs(width);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.setColor(color);
		g.fillRect(point.x, point.y, width, height);
	}
}
