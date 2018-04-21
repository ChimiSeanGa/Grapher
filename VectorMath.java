import java.lang.Math.*;

public class VectorMath {
	public static void main(String[] args) {
		Vector v1 = new Vector();
		Vector v2 = new Vector(new Point(1, 1));
		Vector v3 = new Vector(1.414, Math.PI / 4);

		System.out.println(v1.getPoint() + " " + v1.getDir() + " " + v1.getMag());
		System.out.println(v2.getPoint() + " " + v2.getDir() + " " + v2.getMag());
		System.out.println(v3.getPoint() + " " + v3.getDir() + " " + v3.getMag());

		Vector v4 = Vector.add(v2, v3);

		System.out.println(v4.getPoint() + " " + v4.getDir() + " " + v4.getMag());

		Vector v5 = Vector.scalarMult(v4, 2);

		System.out.println(v5.getPoint() + " " + v5.getDir() + " " + v5.getMag());

		double[][] m1 = {
			{1, 2}, 
			{3, 4}, 
			{5, 6}
		};

		double[][] m2 = {
			{6, 5}, 
			{4, 3}, 
			{2, 1}
		};

		Matrix mat1 = new Matrix(m1);
		Matrix mat2 = new Matrix(m2);
		Matrix mat3 = Matrix.add(mat1, mat2);
		Matrix mat4 = Matrix.scalarMult(mat3, 2);
		Matrix mat5 = Matrix.transpose(mat4);
		Matrix mat6 = Matrix.mult(mat4, mat5);

		System.out.println(mat3);
		System.out.println(mat4);
		System.out.println(mat5);
		System.out.println(mat6);
	}
}