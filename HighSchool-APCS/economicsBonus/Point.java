package apcs.economicsBonus;

/**
 * Title:   Point
 * @author  Alex Byrd
 * @version August 2015
 * Creates a Point object with X coordinate (quantity) and a Y coordinate
 * (price) and can return them, and check if they are located on the same
 * coordinate by testing if they are equal. It can also send back whether
 * they are equal or not, and send back a String that shows the 
 * coordinates of the Point.
 */

public class Point extends Object
{
   /*
    * The X and Y coordinates of the point. quantity being X, and price
    * being Y. 
    */
	private int        quantity;                       
	private double     price;
	
   /*
    * A variable that is used throughout the program as a more usable 
    * price (a price that only goes down to the nearest penny. It takes
    * the price that is input, and rounds it to the nearest hundreth.
    */
	private double     roundedPrice;
	
   /*
    * Creates a tolerance variable for rounding purposes. It is final and
    * cannot be changed.
    */
	private static final double TOLERANCE = 0.01;
	
   /**
    * Default constructor method
    */
	public Point()
	{
		quantity 	 = 0;
		price   	 = 0;
		roundedPrice = 0;
	}
	
   /**
    * Constructs a Point by receiving two values (q and p) which are used
    * to instantiate both the quantity, and price of the Point to given
    * numbers. Also sets roundedPrice to the price, but rounds it to the 
    * nearest hundreth. Also makes sure that there are no negative values
    * and if there is, just make them 0's.
    */
	public Point(int q, double p) 
	{
		quantity = q;
		price    = p;
		
		if(price < 0)
		{
			price = 0;
		}
		
		if(quantity < 0)
		{
			quantity = 0;
		}
		
		roundedPrice = Math.round(this.price / TOLERANCE) * TOLERANCE;	
	}
	
   /**
    * Returns the Points quantity value 
    */
	public int getQuantity()
	{
		return quantity;
	}
	
   /**
    * Returns the Points roundedPrice value
    */
	public double getPrice()
	{
		return roundedPrice;
	}

   /**
    * Tests whether this Point, and another Point that is received by the
    * method are equal in both quantity, and price. If they are
    * equal, that means they have the same coordinate. Then it returns 
    * true if they are equal, and false if they aren't. 
    * 
    * OverLoads the Object class equals method
    */
	public boolean equals(Point other)
	{
	   /*
	    * Checks whether the quantity and roundedPrice of this Point,
	    * equal the corresponding values of another Point called "other". 
	    * If they are, then return true.
	    */
		if(this.quantity == other.getQuantity() 
				&& this.roundedPrice == other.getPrice())
		{
			return true;
		}
		
		return false;
		
	}
	
   /**
    * Equals method that compares any object to a Point by casting it
    * as a Point if it is instantiated as a Point.
    * 
    * Overrides the Object class equals method
    */
	public boolean equals(Object obj)
	{
	   /*
	    * If the object passed is instantiated as a Point, the program
	    * can compare the points quantities and roundedPrices and 
	    * determine if they are equal or not.
	    * 
	    * If the object passed is not a point, it is immediately 
	    * considered not equal to this Point.
	    */
		if(obj instanceof Point)
		{
			Point otherPoint = (Point) obj;
			if(this.quantity == otherPoint.getQuantity() 
					&& this.roundedPrice == otherPoint.getPrice())
			{
				return true;
			}
		}
		
		return false;
	}
	
   /**
    * Returns a String that shows a coordinate point for the user to see
    * to show the X value (quantity) and Y value (price) of the Point.
    */
	public String toString()
	{
	    // Creates the String that shows the coordinate point (X, Y) 
		String coordinates = "("+this.quantity +", "+this.roundedPrice+")";
		
		// Return the String
		return coordinates;
	}
	
}