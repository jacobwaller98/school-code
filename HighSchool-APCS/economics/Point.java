/**
 * Name: Jacob Waller
 * Date: 8/24/2015
 * Hour: 3rd Hour APCS
 */

package apcs.economics;

public class Point {
	// Declare global variables for Point
	private int quantity;
	private double price;

	/**
	 * This is the number of digits past the decimal you need to round to
	 * 
	 * Set to 2 because there are 2 useful digits after the dollar in USD Cents
	 */
	public static final double TOLERANCE = .001;

	/**
	 * Creates the Point object and sets both the quantity and price variables
	 * 
	 * @param quantity
	 * @param price
	 */
	public Point(int quantity, double price) {
		if (quantity < 0)
			throw new IllegalArgumentException();
		if (price < 0)
			throw new IllegalArgumentException();

		this.quantity = quantity;
		this.price = price;
	}

	/**
	 * returns whether or not the given Point object is equal to this Point
	 * object. This is where the TOLERANCE double is used
	 * 
	 * @param other
	 * @return
	 */
	public boolean equals(Point other) {
		if (((other.getQuantity() - this.getQuantity() < TOLERANCE && other.getQuantity() - this.getQuantity() >= 0)
				|| (this.getQuantity() - other.getQuantity() < TOLERANCE && this.getQuantity() - other.getQuantity() >= 0))
				&& ((other.getPrice() - this.getPrice() < TOLERANCE && other.getPrice() - this.getPrice() >= 0) 
				|| (this.getPrice() - other.getPrice() < TOLERANCE && this.getPrice() - other.getPrice() >= 0)))  
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	/**
	 * Determines if this Point is equal to the given object
	 */
	@Override
	public boolean equals(Object other) {
		Point temp = ((Point) other);
		if (((temp.getQuantity() - this.getQuantity() < TOLERANCE && temp.getQuantity() - this.getQuantity() > 0)
				|| (this.getQuantity() - temp.getQuantity() < TOLERANCE && this.getQuantity() - temp.getQuantity() > 0))
				&& ((temp.getPrice() - this.getPrice() < TOLERANCE && temp.getPrice() - this.getPrice() > 0) 
				|| (this.getPrice() - temp.getPrice() < TOLERANCE && this.getPrice() - temp.getPrice() > 0))) 
		{
			return true;
		}
		else
		{
			return false;
		}

	}

	/**
	 * Will print out the coordinate of the point when used in: System.out.print
	 */
	@Override
	public String toString() {
		return "(" + quantity + ", " + price + ")";
	}

	/**
	 * Returns the quantity variable
	 * 
	 * @return
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * returns the price variable
	 * 
	 * @return
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * This method is used to round doubles to the "places" specified
	 * 
	 * @param value
	 * @param places
	 * @return
	 */
	private double round(double value, int places) {
		// You can't round to negative places so throw an exception if there is
		// one
		if (places < 0)
			throw new IllegalArgumentException();
		// Determine what to multiply and then later divide by
		long factor = (long) Math.pow(10, places);
		// Multiply by that factor
		value = value * factor;
		// then round to the nearest whole number
		long tmp = Math.round(value);
		// then at the end, divide by the factor and cast to double
		return (double) tmp / factor;
	}

}
