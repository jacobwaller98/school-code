package apcs.economics;

/**
 * Title:   NewPoint
 * @author  Alex Byrd
 * @version August 2015
 * Tests the Point class by creating two new Points (p1 and p2) of given
 * coordinates, and calls the toString() and equals() methods of both
 * points to output what coordinates the points are, and whether they
 * are at the same coordinate or not.
 */

public class NewPoint 
{
	public static void main(String[] args) 
	{
	   /*
	    * Declare two new Points and instantiate them with given
	    * coordinates (x, y). X relates to the quantity part of
	    * graph while Y relates to the price part of the graph.
	    */
		Point p1   = new Point(4, 4.0);
		Point p2   = new Point(4, 4.005);
		Object p3  = new Object();
		
	   /*
	    * Display the coordinates of both points, and whether they are
	    * in the same position or not (being equal) onto the screen.
	    */
		System.out.println("Point 1: "+p1.toString());
		System.out.println("Point 2: "+p2.toString());
		System.out.println("p1 and p3 equal: "+p1.equals(p3));
		System.out.println("Are they equal? "+p1.equals(p2));
		
	}

}
