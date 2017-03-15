package apcs.economics;

/*
 * Author: Jacob Waller
 * Date 8/15/2015
 * 
 */

public class CurveTester {

	public static void main(String a[]) {
		new CurveTester();
	}

	public CurveTester() {
		ConsumerCurve a = new ConsumerCurve();
		System.out.println(a.pointInRadius(new Point(1,1), new Point(1,1.9)));
	}
}
