import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;

public class Equation {
	private ArrayList<Object> eq;
	private ArrayList<String> ops;

	public Equation(String equation) {
		ops = new ArrayList<String>();
		ops.add("+");
		ops.add("-");
		ops.add("*");
		ops.add("/");
		ops.add("^");
		ops.add("sqrt");
		ops.add("sin");
		ops.add("cos");
		ops.add("tan");
		ops.add("arcsin");
		ops.add("arccos");
		ops.add("arctan");
		ops.add("ln");
		ops.add("log");

		eq = parseEquation(equation);
	}

	private ArrayList<Object> parseEquation(String equation) {
		ArrayList<Object> parsed = new ArrayList<Object>();

		for (int i = 0; i < equation.length(); i++) {
			switch(equation.charAt(i)) {
				case '+':
					parsed.add("+");
					break;
				case '-':
					parsed.add("-");
					break;
				case '*':
					parsed.add("*");
					break;
				case '/':
					parsed.add("/");
					break;
				case '(':
					parsed.add("(");
					break;
				case ')':
					parsed.add(")");
					break;
				case '^':
					parsed.add("^");
					break;
				case 'e':
					parsed.add(Math.E);
					break;
				case 'p':
					parsed.add(Math.PI);
					i++;
					break;
				case 's':
					if (equation.charAt(i+1) == 'q') {
						parsed.add("sqrt");
						i+=3;
					}
					else {
						parsed.add("sin");
						i+=2;
					}
					break;
				case 'c':
					parsed.add("cos");
					i+=2;
					break;
				case 't':
					parsed.add("tan");
					i+=2;
					break;
				case 'a':
					switch(equation.charAt(i+3)) {
						case 's':
							parsed.add("arcsin");
						case 'c':
							parsed.add("arccos");
						case 't':
							parsed.add("arctan");
						default: break;
					}
					i+=5;
					break;
				case 'l':
					if (equation.charAt(i+1) == 'n') {
						parsed.add("ln");
						i++;
					}
					else {
						parsed.add("log");
						i+=2;
					}
					break;
				case 'x':
					parsed.add("x");
					break;
				case 'y':
					break;
				case '=':
					break;
				default:
					if (Character.isDigit(equation.charAt(i))) {
						int j = i;
						while (j < equation.length() && 
							(Character.isDigit(equation.charAt(j)) 
								|| equation.charAt(j) == '.')) {
							j++;
						}
						parsed.add(Double.parseDouble(equation.substring(i, j)));
						i = j-1;
					}
					break;
			}
		}

		return infixToPostfix(parsed);
	}

	private ArrayList<Object> infixToPostfix(ArrayList<Object> equation) {
		ArrayList<Object> postfix = new ArrayList<Object>();
		Stack<Object> stack = new Stack<Object>();
		LinkedList<Object> queue = new LinkedList<Object>();

		for (Object token : equation) {
			if (token instanceof Double || token.equals("x")) {
				queue.addFirst(token);
			}
			else if (ops.contains(token)) {
				String stackOp = stack.isEmpty() ? null : (String) stack.peek();
				while (ops.contains(stackOp)) {
					if (token.equals("+") || token.equals("-")) {
						queue.addFirst(stack.pop());
					}
					else if ((token.equals("*") || token.equals("/")) && 
						(!stackOp.equals("+") && !stackOp.equals("-"))) {
						queue.addFirst(stack.pop());
					}
					else {
						break;
					}
					stackOp = stack.isEmpty() ? null : (String) stack.peek();
				}
				stack.push(token);
			}
			else if (token.equals("(")) {
				stack.push(token);
			}
			else if (token.equals(")")) {
				while (!stack.peek().equals("(")) {
					queue.addFirst(stack.pop());
				}
				stack.pop();
			}
			else {
				stack.push(token);
			}
		}

		while (!stack.isEmpty()) {
			queue.addFirst(stack.pop());
		}

		while (!queue.isEmpty()) {
			postfix.add(queue.removeLast());
		}

		return postfix;
	}

	private Double evalAtNum(Double x) {
		Stack<Double> stack = new Stack<Double>();
		Double arg1, arg2;

		for (Object token : eq) {
			if (token instanceof Double) {
				stack.push((Double) token);
			}
			else if (token.equals("x")) {
				stack.push(x);
			}
			else {
				switch ((String) token) {
					case "+":
						arg2 = stack.pop();
						arg1 = stack.pop();
						stack.push(arg1 + arg2);
						break;
					case "-":
						arg2 = stack.pop();
						arg1 = stack.pop();
						stack.push(arg1 - arg2);
						break;
					case "*":
						arg2 = stack.pop();
						arg1 = stack.pop();
						stack.push(arg1 * arg2);
						break;
					case "/":
						arg2 = stack.pop();
						arg1 = stack.pop();
						stack.push(arg1 / arg2);
						break;
					case "^":
						arg2 = stack.pop();
						arg1 = stack.pop();
						stack.push(Math.pow(arg1, arg2));
						break;
					case "sqrt":
						arg1 = stack.pop();
						stack.push(Math.sqrt(arg1));
						break;
					case "sin":
						arg1 = stack.pop();
						stack.push(Math.sin(arg1));
						break;
					case "cos":
						arg1 = stack.pop();
						stack.push(Math.cos(arg1));
						break;
					case "tan":
						arg1 = stack.pop();
						stack.push(Math.tan(arg1));
						break;
					case "arcsin":
						arg1 = stack.pop();
						stack.push(Math.asin(arg1));
						break;
					case "arccos":
						arg1 = stack.pop();
						stack.push(Math.acos(arg1));
						break;
					case "arctan":
						arg1 = stack.pop();
						stack.push(Math.atan(arg1));
						break;
					case "ln":
						arg1 = stack.pop();
						stack.push(Math.log(arg1));
						break;
					case "log":
						arg1 = stack.pop();
						stack.push(Math.log10(arg1));
						break;
					default: break;
				}
			}
		}

		return stack.pop();
	}

	public ArrayList<Point> getPointSet(Double a, Double b) {
		ArrayList<Point> set = new ArrayList<Point>();

		for (Double i = a; i < b; i += 0.02) {
			set.add(new Point(i, evalAtNum(i)));
		}

		return set;
	}

	public static ArrayList<Point> getTransform(ArrayList<Point> set, Matrix m) {
		ArrayList<Point> transform = new ArrayList<Point>();

		for (Point p : set) {
			transform.add(Matrix.multVector(m, new Vector(p)).getPoint());
		}

		return transform;
	}

	public void printEq() {
		for (Object s : eq) {
			System.out.print(s + " ");
		}
	}
}