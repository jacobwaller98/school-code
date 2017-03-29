/**
 *
 * @author Jacob Waller
 * @date   3/28/2017
 * @uin    673978936
 *
 */

import java.util.Scanner;

public class Jwalle9Proj5 {

	static boolean DEBUG_MODE = false;

	/**
	 * Main
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-d")) {
				DEBUG_MODE = true;
			}
		}
		System.out.print("Starting Expression Evaluation Program\n\n");
		new Jwalle9Proj5();
	}

	public Jwalle9Proj5() {
		String input = "";
		Scanner s = new Scanner(System.in);
		System.out.print("Enter Expression: ");
		input = s.nextLine();
		while (!input.equals("q")) {
			if (input.length() == 0) {
				break;
			}
			if (input.equals("?")) {
				System.out.println("Type in an infix expression with +,-,*,/,(, and ) only");
				System.out.println("Enter ? for this screen");
				System.out.println("Enter q to quit");
			} else {
				/*
				 * Fix input and add spaces between numbers and operators
				 */
				for (int i = 0; i < input.length(); i++) {
					if (Character.isDigit(input.charAt(i))) {
						int x;
						for (x = i; x < input.length(); x++) {
							if (!Character.isDigit(input.charAt(x))) {
								break;
							}
						}
						input = input.substring(0, x) + " " + input.substring(x, input.length());
						i = x;
					}

					if (input.charAt(i) == '+' || input.charAt(i) == '-' || input.charAt(i) == '*'
							|| input.charAt(i) == '/') {
						input = input.substring(0, i + 1) + " " + input.substring(i + 1, input.length());
						i++;
					}
				}
				evaluateInfix(input);
			}
			System.out.print("Enter Expression: ");
			input = s.nextLine();
		}
		s.close();
	}

	/**
	 * Evaluates a o b where a and b are integers and o is an operator Example:
	 * eval(1,'+',2) returns 3
	 *
	 * @param a
	 * @param o
	 * @param b
	 * @return
	 */
	private int eval(int a, char o, int b) {
		if (o == '+') {
			return a + b;
		} else if (o == '-') {
			return a - b;
		} else if (o == '*') {
			return a * b;
		} else if (o == '/') {
			return a / b;
		} else {
			System.out.println("ERROR: Invalid input");
			return -999;
		}
	}

	/**
	 * Returns true if a has operator presedence over b
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	private boolean isBefore(char a, char b) {
		if (b == '(' || b == ')')
			return false;
		if ((a == '*' || a == '/') && (b == '+' || b == '-'))
			return false;
		else
			return true;
	}

	/**
	 * Evaluates the infix expression
	 *
	 * @param expression
	 * @return
	 */
	public int evaluateInfix(String expression) {
		MyStack values = new MyStack();
		MyStack ops = new MyStack();
		for (int i = 0; i < expression.length(); i++) {
			if (expression.charAt(i) == ' ') {
				// Do Nothing
			} else if (Character.isDigit(expression.charAt(i))) {
				int j;
				boolean did = false;
				for (j = i; j < expression.length(); j++) {
					if (!Character.isDigit(expression.charAt(j))) {
						did = true;
						break;
					}
				}
				int num = Integer.parseInt(expression.substring(i, j));

				// Push onto Value Stack
				if (DEBUG_MODE)
					System.out.println("DEBUGGING: Pushing: " + num + " onto Values Stack");
				values.push(num);
				if (did)
					i = j;

			} else if (expression.charAt(i) == '(') {
				if (DEBUG_MODE)
					System.out.println("DEBUGGING: Pushing: ( onto Operations Stack");
				ops.push(expression.charAt(i));
			} else if (expression.charAt(i) == ')') {
				while (((char) ops.top()) != '(') {
					if (DEBUG_MODE)
						System.out.println("DEBUGGING: Encountered a: (");
					char op = (char) ops.top();
					ops.pop();
					if (values.isEmpty()) {
						System.out.println("ERROR: Invalid expression");
						System.exit(0);
					}
					int v2 = (int) values.top();
					values.pop();
					if (values.isEmpty()) {
						values.push(v2);
					} else {
						int v1 = (int) values.top();
						values.pop();
						values.push(eval(v1, op, v2));
					}
				}
				ops.pop();
			} else if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*'
					|| expression.charAt(i) == '/') {
				if (DEBUG_MODE)
					System.out.println("DEBUGGING: Encountered a " + expression.charAt(i));
				char thisOp = expression.charAt(i);
				while (!ops.isEmpty() && isBefore(thisOp, (char) ops.top())) {
					char op = (char) ops.top();
					ops.pop();
					if (values.isEmpty()) {
						System.out.println("ERROR: Invalid expression");
						System.exit(0);
					}
					int v2 = (int) values.top();
					values.pop();
					if (values.isEmpty()) {
						values.push(v2);
					} else {
						int v1 = (int) values.top();
						values.pop();
						values.push(eval(v1, op, v2));
					}
				}
				ops.push(thisOp);
			}
		}
		if (DEBUG_MODE) {
			System.out.println("DEBUGGING: Done processing input...");
			System.out.println("DEBUGGING: Finishing up mathing");
		}
		while (!ops.isEmpty()) {
			char op = (char) ops.top();
			ops.pop();
			if (values.isEmpty()) {
				System.out.println("ERROR: Invalid expression");
				System.exit(0);
			}
			int v2 = (int) values.top();
			values.pop();
			if (values.isEmpty()) {
				values.push(v2);
			} else {
				int v1 = (int) values.top();
				values.pop();
				values.push(eval(v1, op, v2));
			}
		}
		System.out.println(values.top());
		return 0;
	}

}
