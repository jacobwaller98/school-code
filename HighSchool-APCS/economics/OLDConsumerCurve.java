package apcs.economics;

/*
 * Author: Jacob Waller
 * Date 8/15/2015
 * 
 */

public class OLDConsumerCurve {
	
	private Point[] myPoints;

	/**
	 * This creates an object that also creates an array of points using the
	 * default values
	 * 
	 */
	public OLDConsumerCurve() {
		myPoints = new Point[10];
		for (int x = 1; x < 11; x++) {
			myPoints[x] = new Point(x + 1,x + 10);
		}
	}

	/**
	 * This constructor will create a consumer curve with the given number of
	 * points slope and Y-Intercept
	 * 
	 * @param number
	 *            = Number of points in the curve
	 * @param m
	 *            = Slope of the line
	 * @param b
	 *            = Y-intercept of the line
	 */
	public OLDConsumerCurve(int number, double m, double b) {
		if (number < 0)
			throw new IllegalArgumentException();
		myPoints = new Point[number];
		for (int x = 0; x < number; x++) {
			myPoints[x] = new Point(x + 1, (m * x) + b);
		}
	}

	/**
	 * This will create a linear curve using the given number of points, slope,
	 * y intercept, and delta x
	 * 
	 * @param number
	 *            = Number of points in the curve
	 * @param m
	 *            = Slope of the line
	 * @param b
	 *            = Y-intercept of the line
	 * @param dx
	 *            = The distance between x's
	 */
	public OLDConsumerCurve(int number, double m, double b, int dx) {
		if (number < 0)
			throw new IllegalArgumentException();
		myPoints = new Point[number];
		for (int x = 0; x < number; x++) {
			myPoints[x] = new Point((x + 1) * dx, (m * x) + b);
		}
	}

	/**
	 * This returns a string of the coordinates of all the points in the curve
	 */
	public String toString() {
		String curve = "";
		for (int x = 0; x < myPoints.length; x++) {
			curve += myPoints[x] + "\n";
		}

		return curve;
	}

	/**
	 * returns a boolean if the given point is on the curve
	 * 
	 * @param p
	 *            = The point you're comparing to the rest on the curve
	 * @return
	 */
	public boolean onCurve(Point p) {
		for (int x = 0; x < myPoints.length; x++) {
			if (myPoints[x].equals(p)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method adds a point to the curve returns false if the point is
	 * already on the line adds the point if not, also removes other points with
	 * the same quantity
	 * 
	 * @param p
	 *            = The Point you are adding to the curve
	 * @return = a boolean that is false if the point is already on the curve
	 *         and true if it successfully puts the point on the curve
	 */
	public boolean add(Point p) {
		if (onCurve(p)) {
			return false;
		}
		Point temp[] = new Point[myPoints.length + 1];
		for (int x = 0; x < myPoints.length; x++) {
			temp[x] = myPoints[x];
		}
		temp[temp.length - 1] = p;
		myPoints = temp;
		deleteOthers(p);
		sortByQuantity();
		return true;
	}

	/**
	 * This method will delete a specific cell in an array and shift over the
	 * rest of the array to make it one smaller
	 * 
	 * @param num = The index of the array that will be deleted
	 */
	public void delete(int num) {
		myPoints[num] = null;
		for (int x = num; x < myPoints.length - 1; x++) {
			myPoints[x] = myPoints[x + 1];
		}

		Point temp[] = new Point[myPoints.length - 1];
		for (int x = 0; x < temp.length; x++) {
			temp[x] = myPoints[x];
		}
		myPoints = temp;
	}

	/**
	 * This will go through the curve and delete the points that are the same
	 * 
	 * @param p = The point that will be removed
	 */
	public void delete(Point p) {
		int nullNum = 0;
		boolean found = false;
		for (int x = 0; x < myPoints.length; x++) {
			if (myPoints[x].equals(p)) {
				myPoints[x] = null;
				nullNum = x;
				found = true;
				x = myPoints.length;
			}
		}
		if(!found)
			return;
		for (int x = nullNum; x < myPoints.length - 1; x++) {
			myPoints[x] = myPoints[x + 1];
		}

		Point temp[] = new Point[myPoints.length - 1];
		for (int x = 0; x < temp.length; x++) {
			temp[x] = myPoints[x];
		}
		myPoints = temp;

	}

	/**
	 * This is a private method that uses the bubble sort algorithm to sort all
	 * the points by quantity
	 */
	private void sortByQuantity() {
		for (int x = 0; x < myPoints.length; x++) {
			for (int y = 0; y < myPoints.length - 1; y++) {
				if (myPoints[y].getQuantity() > myPoints[y + 1].getQuantity()) {
					Point temp = myPoints[y];
					myPoints[y] = myPoints[y + 1];
					myPoints[y + 1] = temp;
				}
			}
		}
	}

	/**
	 * This is a private method that will delete all other points that have the
	 * same quantity
	 * 
	 * @param p
	 */
	private void deleteOthers(Point p) {
		int nullNum = 0;
		int loopNum = 0;
		while (loopNum < myPoints.length - 1) {
			if (myPoints[loopNum].getQuantity() == p.getQuantity()
					&& !myPoints[loopNum].equals(p)) {
				myPoints[loopNum] = null;
				nullNum = loopNum;
				loopNum = myPoints.length;
			}
			if (loopNum == myPoints.length - 2)
				return;
			loopNum++;
		}

		for (int x = nullNum; x < myPoints.length - 1; x++) {
			myPoints[x] = myPoints[x + 1];
		}

		Point temp[] = new Point[myPoints.length - 1];
		for (int x = 0; x < temp.length; x++) {
			temp[x] = myPoints[x];
		}
		myPoints = temp;
	}

}
