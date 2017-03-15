package apcs.economics;

import java.util.ArrayList;

public abstract class AbstractCurve {
	/*
	 * Where the Points will be stored
	 */
	public ArrayList<Point> myCurve = new ArrayList<Point>();
	private double minimumDist;

	/**
	 * This method should sort the entire curve by quantity
	 */
	public abstract void sort();


	/**
	 * This will construct a new curve with the given parameters
	 * 
	 * @param number
	 *            Number of points in the curve
	 * @param m
	 *            The slope of the curve
	 * @param b
	 *            The Y-Intercept of the curve
	 * @param dx
	 *            The distance between 2 x's
	 */
	public AbstractCurve(int number, double m, double b, int dx) {
		if (number < 0)
			throw new IllegalArgumentException();
		for (int x = 0; x < number; x++) {
			myCurve.add(new Point((dx * x), ((m * x * dx) + b)));
		}
		minimumDist = dx;
	}

	/**
	 * This will delete the given point if it is on the curve
	 * 
	 * @param p
	 *            The point to delete
	 * @return Will return true if the point was on the curve and was deleted,
	 *         will return false otherwise
	 */
	public boolean remove(Point p) {
		if (!contains(p))
			return false;
		for (int x = 0; x < myCurve.size(); x++) {
			if (myCurve.get(x).equals(p)) {
				myCurve.remove(x);
				return true;
			}
		}
		return false;
	}

	/**
	 * This will add a point to the curve
	 * 
	 * @param p
	 *            The point to add
	 * @return Will return true if the point was added and will return false if
	 *         the point was already on the curve
	 */
	public boolean add(Point p) {
		if (contains(p)) {
			return false;
		}
		myCurve.add(p);
		sort();
		for (int x = 0; x < myCurve.size(); x++) {
			if (myCurve.get(x).getQuantity() == p.getQuantity() && !myCurve.get(x).equals(p)) {
				myCurve.remove(x);
				return true;

			}
		}
		return true;
	}

	/**
	 * This method will determine whether or not the given Point is on the curve
	 * 
	 * @param p
	 *            The Point we are testing
	 * @return Will return true if the point is on the curve and false if it is
	 *         not
	 */
	public boolean contains(Point p) {
		for (int x = 0; x < myCurve.size(); x++) {
			if (myCurve.get(x).equals(p)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method returns a string of the curve
	 */
	public String toString() {
		String curve = "";
		for (int x = 0; x < myCurve.size(); x++) {
			curve += "\n" + myCurve.get(x);
		}
		return curve;
	}

	/**
	 * This will return the index that the given Point's quantity is at
	 * @param p
	 * @return
	 */
	private int indexByX(Point p) {
		for (int x = 0; x < myCurve.size(); x++) {
			if (p.getQuantity() == myCurve.get(x).getQuantity())
				return x;
		}
		return -1;
	}

	public Point getPointAfter(Point p) {
		try {
			return myCurve.get(indexByX(p)+1);
			
		}
		catch (Exception e) {
			return myCurve.get(0);
		}
	}
	
	
	
	/**
	 * This method will respond with a point after looking at the given one
	 */
	public Point lookAtPoint(Point p) {
		if (contains(p)) {
			return p;
		}
		if (getClass().toString().contains("ConsumerCurve")) {
			int i = indexByX(p);
			if (myCurve.get(i).getPrice() == p.getPrice()) {
				return p;
			} else {
				try {
					return myCurve.get(indexByX(p) + 1);
				} catch (Exception e) {
					return myCurve.get(0);
				}
			}
		}
		if (getClass().toString().contains("ProducerCurve")) {
			int i = indexByX(p);
			if (myCurve.get(i).getPrice() == p.getPrice()) {
				return p;
			} else {
				try {
					return myCurve.get(indexByX(p) + 1);
				} catch (Exception e) {
					return myCurve.get(0);
				}
			}
		}
		try {
			return myCurve.get(indexByX(p) + 1);
		} catch (Exception e) {
			return myCurve.get(0);
		}
	}
	
	/**
	 * 
	 * @param p
	 * @param o
	 * @return
	 */
	public boolean pointInRadius(Point p, Point o)
	{
		int    px = p.getQuantity();
		double py = p.getPrice();
		int    ox = o.getQuantity();
		double oy = o.getPrice();
		
		if(Math.sqrt(Math.pow(oy-py, 2) + Math.pow(ox-px,2)) <= minimumDist)
			return true;
		else;
			return false;
	}

}
