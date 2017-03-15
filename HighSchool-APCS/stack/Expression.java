package apcs.stack;
import java.util.Arrays;
import java.util.Scanner;

public class Expression {
	
	StackList sl = new StackList();

	public static void main(String ar[]) {
		new Expression();
	}

	public Expression() {
		Scanner s = new Scanner(System.in);
		String exp = s.nextLine();
		String[] a = exp.split(" ");
		for(int x = 0; x < a.length; x++) {
			sl.push(a[x]);
		}
		System.out.println(performOp());
	}

	

	public int performOp() {
		char oper = sl.pop().toString().toCharArray()[0];
		
		int b = (Integer) Integer.parseInt(sl.pop().toString());
		int a = (Integer) Integer.parseInt(sl.pop().toString());
		
		sl.push(a);
		sl.push(b);
		
		if (oper == '+')
			return ((Integer) sl.pop()).intValue() + ((Integer) sl.pop()).intValue();
		if (oper == '-')
			return ((Integer) sl.pop()).intValue() - ((Integer) sl.pop()).intValue();
		if (oper == '*')
			return ((Integer) sl.pop()).intValue() * ((Integer) sl.pop()).intValue();
		if (oper == '/')
			return ((Integer) sl.pop()).intValue() / ((Integer) sl.pop()).intValue();
		return -1;
	}

	public boolean isChar(String a) {
		return a.equals("*") || a.equals("/") || a.equals("+") || a.equals("-");
	}

}
