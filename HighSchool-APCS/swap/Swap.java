package apcs.swap;

import java.util.ArrayList;
import java.util.Arrays;

public class Swap {

	private static Object first = 3;
	private static Object second = 7;
	private static Object firstNum = 2;
	private static Object secondNum = 4;
	private static int[] myArray = { 1, 2, 3, 4, 5 };
	private static ArrayList<Object> y = new ArrayList<Object>(Arrays.asList(1,2,3,4,5));

	public static void main(String a[]) {
		System.out.print("Swap Static Field:: Before swap: " + first + "," + second + " After Swap: ");
		swapStaticField();
		System.out.println(first + "," + second);
		System.out.print("Swap Paramater:: Before swap: " + firstNum + "," + secondNum + " After swap: ");
		swapParam(firstNum, secondNum);
		System.out.println(firstNum + "," + secondNum);
		System.out.print("Swap From Other Method:: Before swap: " + firstNum + "," + secondNum + " After swap: ");
		swapLocalFromOtherMethod();
		System.out.println(firstNum + "," + secondNum);
		System.out.print("swapArray():: Before Swap: " + Arrays.toString(myArray) + " After Swap: ");
		swapArray(myArray, 0, 3);
		System.out.println(Arrays.toString(myArray));
		System.out.print("swapList():: Before Swap: " + asString(y) + " After Swap: ");
		swapList(y, 0, 3);
		System.out.println(asString(y));

	}

	public static void swapStaticField() {
		Object t = first;
		first = second;
		second = t;
	}

	public static void swapParam(Object x, Object y) {
		Object temp = x;
		x = y;
		y = temp;
		firstNum = x;
		secondNum = y;
	}

	public static void swapLocalFromOtherMethod() {
		Object x = firstNum;
		Object y = secondNum;
		swapThese(x, y);

	}

	private static void swapThese(Object x, Object y) {
		Object t = x;
		x = y;
		y = t;
		firstNum = x;
		secondNum = y;
	}

	public static void swapArray(int a[], int index1, int index2) {
		int[] x = a;
		int temp = x[index1];
		x[index1] = x[index2];
		x[index2] = temp;
		myArray = x;
	}

	public static void swapList(ArrayList<Object> a, int index1, int index2) {
		ArrayList<Object> x = a;
		Object temp = x.get(index1);
		x.set(index1,x.get(index2));
		x.set(index2,temp);
		y = x;
	}
	private static String asString(ArrayList<Object> l)
	{
		String a = "";
		for(int x = 0; x < l.size(); x++){
			a+=l.get(x) + ", ";
		}
		return a;
	}
}
