import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Grid extends JPanel implements ActionListener {
	public int spacing = 30;

	private Equation eq;
	private ArrayList<Point> set = new ArrayList<Point>();
	private ArrayList<Point> transform = new ArrayList<Point>();
	private Timer timer;
	private final int delay = 15;
	private Vector v1 = new Vector();
	private Vector v2 = new Vector();
	private double angle = 0;
	private double radius = 0;
	private boolean drawVectors = true;
	// private ArrayList<Point> trail1 = new ArrayList<Point>();
	// private ArrayList<Point> trail2 = new ArrayList<Point>();

	private Matrix mat1 = new Matrix(new double[][] {
		{1, 1.5},
		{0, 1}
	});

	public Grid(String eq) {
		setBackground(Color.WHITE);

		this.eq = new Equation(eq);
		timer = new Timer(delay, this);
		// timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGridLines(g);
		drawAxes(g);
		set = eq.getPointSet(getWidth() * -1.0 / spacing, getWidth() * 1.0 / spacing);
		transform = Equation.getTransform(set, mat1);
		Plot.drawPointSet(g, this, set, Color.RED);
		Plot.drawPointSet(g, this, transform, Color.BLUE);

		// if (drawVectors) {
		// 	Plot.drawVector(g, this, v1, Color.RED);
		// 	Plot.drawVector(g, this, v2, Color.BLUE);
		// }

		// Plot.drawPointSet(g, this, trail1, Color.RED);
		// Plot.drawPointSet(g, this, trail2, Color.BLUE);
	}

	public void drawAxes(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		int width = getWidth();
		int height = getHeight();

		Stroke axis = new BasicStroke(2f);
		g2.setStroke(axis);
		g2.setColor(Color.BLACK);
		Line2D x_axis = new Line2D.Float(0, height/2, width, height/2);
		Line2D y_axis = new Line2D.Float(width/2, 0, width/2, height);
		g2.draw(x_axis);
		g2.draw(y_axis);
	}

	public void drawGridLines(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		int width = getWidth();
		int height = getHeight();

		Stroke gridLines = new BasicStroke(1f);
		g2.setStroke(gridLines);
		g2.setColor(Color.BLACK);

		for (int xPos = width/2; xPos < width; xPos+=spacing) {
			g2.draw(new Line2D.Float(xPos, 0, xPos, height));
		}

		for (int xPos = width/2; xPos > 0; xPos-=spacing) {
			g2.draw(new Line2D.Float(xPos, 0, xPos, height));
		}

		for (int yPos = height/2; yPos < height; yPos+=spacing) {
			g2.draw(new Line2D.Float(0, yPos, width, yPos));
		}

		for (int yPos = height/2; yPos > 0; yPos-=spacing) {
			g2.draw(new Line2D.Float(0, yPos, width, yPos));
		}
	}

	public void actionPerformed(ActionEvent e) {
		// if (angle < 2 * Math.PI) {
		// 	angle += 0.02;
		// 	radius = angle / (2 * Math.PI);
		// 	v1 = new Vector(radius, angle);
		// 	v2 = Matrix.multVector(mat1, v1);

		// 	if (!trail1.contains(v1.getPoint()))
		// 		trail1.add(v1.getPoint());

		// 	if (!trail2.contains(v2.getPoint()))
		// 		trail2.add(v2.getPoint());

		// 	if (angle + 0.02 >= 2 * Math.PI) {
		// 		drawVectors = false;
		// 	}

		// 	repaint();
		// }
		// else {
		// 	timer.stop();
		// }
	}
}