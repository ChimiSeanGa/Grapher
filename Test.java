public class Test {
	public static void main(String[] args) {
		Equation eq = new Equation("y=3*x+4");
		System.out.println(eq.getPointSet(-3.0, 3.0));
	}
}