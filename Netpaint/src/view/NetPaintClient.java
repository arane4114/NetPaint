package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.Drawable;
import model.Image;
import model.Line;
import model.Oval;
import model.Rectangle;

public class NetPaintClient extends JFrame {

	private JColorChooser jcc;
	private Color color;
	private String currentString = "Line";
	private DrawPanel drawPanel;
	private boolean firstremove;
	private boolean mousePressed;

	private int initialXLocation;
	private int initialYLocation;

	private static String lineString = "Line";
	private static String ovalString = "Oval";
	private static String rectangleString = "Rectangle";
	private static String imageString = "Image";

	public NetPaintClient() {
		setTitle("Net Paint Client");
		setSize(1000, 1000);
		setMinimumSize(new Dimension(1000, 1000));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		initialXLocation = 0;
		initialYLocation = 0;
		firstremove = false;
		mousePressed = false;

		drawPanel = new DrawPanel();
		drawPanel.addMouseListener(new mouseListener());
		drawPanel.addMouseMotionListener(new mouseListener());
		JScrollPane scrollPane = new JScrollPane(drawPanel);
		scrollPane.setPreferredSize(new Dimension(1000, 500));

		ButtonGroup group = new ButtonGroup();
		JPanel buttonPanel = new JPanel(new GridLayout(0, 4));

		JRadioButton lineButton = new JRadioButton(lineString);
		lineButton.setActionCommand(lineString);
		lineButton.setSelected(true);
		lineButton.addActionListener(new buttonListener());
		group.add(lineButton);
		buttonPanel.add(lineButton);

		JRadioButton rectangleButton = new JRadioButton(rectangleString);
		rectangleButton.setActionCommand(rectangleString);
		rectangleButton.addActionListener(new buttonListener());
		group.add(rectangleButton);
		buttonPanel.add(rectangleButton);

		JRadioButton ovalButton = new JRadioButton(ovalString);
		ovalButton.setActionCommand(ovalString);
		ovalButton.addActionListener(new buttonListener());
		group.add(ovalButton);
		buttonPanel.add(ovalButton);

		JRadioButton imageButton = new JRadioButton(imageString);
		imageButton.setActionCommand(imageString);
		imageButton.addActionListener(new buttonListener());
		group.add(imageButton);
		buttonPanel.add(imageButton);

		color = Color.white;
		jcc = new JColorChooser();
		jcc.getSelectionModel().addChangeListener(new changeListener());

		jcc.setBorder(BorderFactory.createTitledBorder("Choose Text Color"));
		buttonPanel.setBorder(BorderFactory
				.createTitledBorder("Choose Shape Type"));
		scrollPane.setBorder(BorderFactory.createTitledBorder("Canvas"));

		this.add(scrollPane, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
		this.add(jcc, BorderLayout.SOUTH);
		setVisible(true);
	}

	public static void main(String[] args) {
		new NetPaintClient();
	}

	private class mouseListener implements MouseListener, MouseMotionListener {

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (!mousePressed) {
				initialXLocation = e.getX();
				initialYLocation = e.getY();
				mousePressed = true;
			} else {
				if (initialXLocation != e.getX()
						&& initialYLocation != e.getY()) {
					drawPanel.remove();
				}

				if (currentString.equals(lineString)) {
					Line line = new Line(initialXLocation, initialYLocation,
							e.getY() - initialYLocation, e.getX()
									- initialXLocation, color);
					drawPanel.addObject((Drawable) line);
				} else if (currentString.equals(rectangleString)) {
					Rectangle rect = new Rectangle(initialXLocation,
							initialYLocation, e.getY() - initialYLocation,
							e.getX() - initialXLocation, color);
					drawPanel.addObject((Drawable) rect);
				} else if (currentString.equals(ovalString)) {
					Oval oval = new Oval(initialXLocation, initialYLocation,
							e.getY() - initialYLocation, e.getX()
									- initialXLocation, color);
					drawPanel.addObject((Drawable) oval);
				} else if (currentString.equals(imageString)) {
					Image image = new Image(initialXLocation, initialYLocation,
							e.getY() - initialYLocation, e.getX()
									- initialXLocation, color);
					drawPanel.addObject((Drawable) image);
				}
				mousePressed = false;
				firstremove = false;
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

		@Override
		public void mouseDragged(MouseEvent e) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			if (mousePressed) {
				Color tempColor = new Color(color.getRed(), color.getGreen(),
						color.getBlue(), color.getAlpha() / 2);
				if (firstremove == false) {
					firstremove = true;
				} else {
					drawPanel.remove();
				}
				if (currentString.equals(lineString)) {
					Line line = new Line(initialXLocation, initialYLocation,
							e.getY() - initialYLocation, e.getX()
									- initialXLocation, tempColor);
					drawPanel.addObject((Drawable) line);
				} else if (currentString.equals(rectangleString)) {
					Rectangle rect = new Rectangle(initialXLocation,
							initialYLocation, e.getY() - initialYLocation,
							e.getX() - initialXLocation, tempColor);
					drawPanel.addObject((Drawable) rect);
				} else if (currentString.equals(ovalString)) {
					Oval oval = new Oval(initialXLocation, initialYLocation,
							e.getY() - initialYLocation, e.getX()
									- initialXLocation, tempColor);
					drawPanel.addObject((Drawable) oval);
				} else if (currentString.equals(imageString)) {
					Image image = new Image(initialXLocation, initialYLocation,
							e.getY() - initialYLocation, e.getX()
									- initialXLocation, tempColor);
					drawPanel.addObject((Drawable) image);
				}
			}
		}
	}

	private class buttonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			currentString = e.getActionCommand();
		}
	}

	private class changeListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			color = jcc.getColor();
		}
	}
}
