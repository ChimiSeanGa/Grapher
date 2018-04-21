import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.*;

public class Plot {
	public static void drawPoint(Graphics g, Grid grid, Point p, Color color) {
		Graphics2D g2 = (Graphics2D) g;
		int width = grid.getWidth();
		int height = grid.getHeight();
		int spacing = grid.spacing;

		int x = (int) (p.getX() * spacing + width/2);
		int y = (int) (-1 * p.getY() * spacing + height/2);
		int r = spacing / 6;
		g2.setColor(color);
		g2.fillOval(x-r/2, y-r/2, r, r);
	}

	public static void drawVector(Graphics g, Grid grid, Vector v, Color color) {
		Graphics2D g2 = (Graphics2D) g;
		int width = grid.getWidth();
		int height = grid.getHeight();
		int spacing = grid.spacing;

		double x = v.getPoint().getX() * spacing + width/2;
		double y = -1 * v.getPoint().getY() * spacing + height/2;

		Line2D vector = new Line2D.Double(width/2, height/2, x, y);

		Stroke axis = new BasicStroke(3f);
		g2.setStroke(axis);
		g2.setColor(color);
		g2.draw(vector);

		int sign = (int) Math.signum(v.getMag());

		double l_arrow_x = x - sign * (10 * Math.cos(v.getDir() - Math.PI/4));
		double l_arrow_y = y + sign * (10 * Math.sin(v.getDir() - Math.PI/4));
		double r_arrow_x = x - sign * (10 * Math.cos(v.getDir() + Math.PI/4));
		double r_arrow_y = y + sign * (10 * Math.sin(v.getDir() + Math.PI/4));

		Line2D l_arrow = new Line2D.Double(x, y, l_arrow_x, l_arrow_y);
		Line2D r_arrow = new Line2D.Double(x, y, r_arrow_x, r_arrow_y);

		g2.draw(l_arrow);
		g2.draw(r_arrow);
	}

	public static void drawPointSet(Graphics g, Grid grid, ArrayList<Point> points, Color color) {
		for (Point p : points) {
			drawPoint(g, grid, p, color);
		}
	}
}