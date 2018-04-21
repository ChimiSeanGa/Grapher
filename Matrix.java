public class Matrix {
	private double[][] mat;
	private int rows;
	private int cols;

	public Matrix(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		this.mat = new double[rows][cols];
	}

	public Matrix(double[][] mat) {
		this.rows = mat.length;
		this.cols = mat[0].length;
		this.mat = mat;
	}

	public double get(int row, int col) {
		return mat[row-1][col-1];
	}

	public int getRows() {
		return rows;
	}

	public int getCols() {
		return cols;
	}

	public String toString() {
		String res = "";
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				res += mat[r][c] + " ";
			}
			res += "\n";
		}

		return res;
	}

	public static Matrix add(Matrix m1, Matrix m2) {
		if (m1.rows != m2.rows || m1.cols != m2.cols) {
			System.out.println("ERROR: Matrices of invalid dimensions");
			return null;
		}

		int rows = m1.rows;
		int cols = m1.cols;
		double[][] newMat = new double[rows][cols];

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				newMat[r][c] = m1.mat[r][c] + m2.mat[r][c];
			}
		}

		return new Matrix(newMat);
	}

	public static Matrix scalarMult(Matrix m, double scalar) {
		int rows = m.rows;
		int cols = m.cols;
		double[][] newMat = new double[rows][cols];

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				newMat[r][c] = m.mat[r][c] * scalar;
			}
		}

		return new Matrix(newMat);
	}

	public static Matrix transpose(Matrix m) {
		int cols = m.rows;
		int rows = m.cols;
		double[][] newMat = new double[rows][cols];

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				newMat[r][c] = m.mat[c][r];
			}
		}

		return new Matrix(newMat);
	}

	public static Matrix mult(Matrix m1, Matrix m2) {
		if (m1.cols != m2.rows) {
			System.out.println("ERROR: Matrices of invalid dimensions");
			return null;
		}

		int rows1 = m1.rows;
		int cols1 = m1.cols;
		int rows2 = m2.rows;
		int cols2 = m2.cols;
		double[][] newMat = new double[rows1][cols2];

		for (int i = 0; i < rows1; i++) {
			for (int j = 0; j < cols2; j++) {
				for (int k = 0; k < cols1; k++) {
					newMat[i][j] += m1.mat[i][k] * m2.mat[k][j];
				}
			}
		}

		return new Matrix(newMat);
	}

	public static Vector multVector(Matrix m, Vector v) {
		if (m.rows != 2 || m.cols != 2) {
			System.out.println("ERROR: Matrices of invalid dimensions");
			return null;
		}

		double x = v.getPoint().getX();
		double y = v.getPoint().getY();

		double newX = m.mat[0][0] * x + m.mat[0][1] * y;
		double newY = m.mat[1][0] * x + m.mat[1][1] * y;

		return new Vector(new Point(newX, newY));
	}
}