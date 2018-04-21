public class Vector {
	private Point p;
	private double mag;
	private double dir;

	public Vector() {
		this.p = new Point(0, 0);
		this.dir = 0;
		this.mag = 0;
	}

	public Vector(double mag, double dir) {
		this.p = new Point(mag * Math.cos(dir), 
			mag * Math.sin(dir));
		this.mag = mag;
		this.dir = dir;
	}

	public Vector(Point p) {
		this.p = p;
		this.dir = Math.atan2(p.getY(), p.getX());
		this.mag = Math.sqrt(Math.pow(p.getX(), 2) + Math.pow(p.getY(), 2));
	}

	public Point getPoint() {
		return p;
	}

	public double getDir() {
		return dir;
	}

	public double getMag() {
		return mag;
	}

	public String toString() {
		return p.toString();
	}

	public static Vector add(Vector v1, Vector v2) {
		return new Vector(new Point(v1.p.getX() + v2.p.getX(),
			v1.p.getY() + v2.p.getY()));
	}

	public static Vector scalarMult(Vector v, double scalar) {
		return new Vector(v.mag * scalar, v.dir);
	}
}