package apcs.economicsBonus;

import java.util.ArrayList;

/**
 * Title:   ConsumerCurve
 * @author  Alex Byrd
 * @version September 2015
 * Creates a ConsumerCurve object that contains an ArrayList of Points
 * called consumerPoints. These points are defaultly set in this method
 * to start at (0, 0) and have a slope of 1 from point to point, and to
 * have 10 Points in the array list.
 * 
 * It can then use this information to send back the coordinates of a
 * point in the ArrayList in String form, and also send back a Point in 
 * the ArrayList to be used for other purposes.
 * 
 * It can also have Points added to the array list, deleted from it, 
 * replaced on it, ordered in terms of quantity, and checked to see
 * if they are equal to a certain Point being sent in.
 */

public class ConsumerCurveOriginal 
{  
   /*
    * Creates an ArrayList containing objects of type Point called
    * curvePoints
    */
	private ArrayList<Point>  consumerPoints;
	
   /**
	* Default Constructor method that constructs a new ConsumerCurve that
	* instantiates a new array list of 10 Points, and sets the 
	* coordinates (quantity, price) of each Point in the array list. 
	* The default coordinates for the first Point created in the array
	* is (0, 0) and each consecutive one up by a slope of 1 from there.
	*/
	public ConsumerCurveOriginal() 
	{
	   /*
	    * Default Coordinate Points
	    */
		int x = 0;
		int y = 10;
		
	   /*
	    * Instantiates consumerPoints to a new ArrayList containing
	    *  objects of type Point
	    */
		consumerPoints = new ArrayList<Point>();
		
	   /*
	    * Loop 10 times, each time adding a new point of coordinates
	    * that has an x value of 1 larger, and a y that is 1 shorter. 
	    * 
	    * After exiting the loop, we now have 10 points with given
	    * coordinates in the consumerPoints array list.
	    */
		for(int i = 0; i < 10; i++)
		{
			consumerPoints.add(new Point(x, y));
			x++;
			y--;
		}
	}
	
   /**
	* Another ConsumerCurve constructor, that is sent in a curveSize,
	* y intercept, a slope, and a change in x variable, in order to
	* instantiate the consumerPoints array list of a given curveSize.
	*/
	public ConsumerCurveOriginal(int size, double m, double yint, int dx)
	{
		//Sets default curveSize equal to the size passed in
		int curveSize  = size;
			
	   /*
		* Instantiates a new consumerPoints array list of Points 
		*/
		consumerPoints = new ArrayList<Point>();
			
	   /*
		* Sets the default y value for the first Point on the curve to
		* be equal to the y intercept, and then sets b (the y-int) equal
		* to it as well, to work in the equation y = mx + b;
		*/
		double y  = yint;
		double b  = yint;
			
		//The default starting value for x.
		int x     = 0;
			
	   /*
		* Goes through the entire array and instantiates all the Points in
		* and their coordinates for the array list by adding the change
		* x onto the x variable, and then uses the slope, x, and yint
		* values to find y for each x value using the equation
		* y = mx + b.
		*/
		for(int i = 0; i < curveSize; i++)
		{
			consumerPoints.add(new Point(x, y));
			x += dx;
			y  = (m * x) + b;
		}
	}
	
   /**
	* Returns a String that is compiled by adding all the Strings
	* of all the coordinates of the Points in the consumerPoints
	* array list.
	*/
	public String toString()
	{
		String temp = "";
		
		for(int i = 0; i < consumerPoints.size(); i++)
		{
			temp += consumerPoints.get(i).toString();
		}
		
		return temp;
	}
	
   /**
	* Returns a Point on the curve to be compared or negotiated over
	*/
	public Point getPointOnCurve(int i)
	{
		Point temp = consumerPoints.get(i);
		
		return temp;
	}
	
   /**
	* Add a point to the consumerPoints array list by first checking to 
	* make sure that the point does not already exist (and if it does,
	* throw a Runtime Exception to stop this methods execution), and that 
	* the point doesn't have the same x coordinate (Which is the quantity 
	* of the point, in which if it was the same, the point would need to 
	* be replaced, and the curve would need to be shifted), and if both  
	* of those prove to be false, add the Point to the array.
	* 
	* After that go to the orderByQuantity method to order the points
	* on the curve.
	*/
	public void add(Point p)
	{
		//Temporary Point set equal to the Point sent in 
		Point p1 = p;

	   /*
	    * If the Point sent in is equal to a Point on the curve, then
	    * throw a new Runtime Exception stating that there is already
	    * a Point with that coordinate on the array list.
	    */
		if(this.searchPoint(p1) == 1)
		{
			throw new RuntimeException
					("There is already a Point at that coordinate!");
		}
			
	   /*
	    * If the Point is not on the curve, then add the new Point to the
	    * array list.
	    */
		else if(this.searchPoint(p1) == -1)
		{
			consumerPoints.add(p1);
		}
			
	   /*
		* If the Point isn't on the array, but has the same x coordinates
		* then call the replace Point method, and replace that Point.
		*/
	    else if(this.searchPoint(p1) == 0)
		{
			this.replacePoint(p1);
		}
			
		//Order the new array by quantity (the x coordinates)
		this.orderByQuantity();
		
	}
		
   /**
    * Deletes a Point on the curve that is equal to the Point sent in if
    * such Point exists on the curve. If the Point does not exist, throw
    * a new Runtime Exception stating that there exists no Point like
    * that to delete.
    */
	public void delete(Point p)
	{
		//Temporary Point set equal to the point sent in
		Point p1 = p;
			
	   /*
	    * Checks through the entire array list looking for a Point that
	    * is equal to the Point sent in, and when its found, remove that
	    * Point from the array list.
	    */
		if(this.searchPoint(p1) == 1)
		{
			for(int i = 0; i < consumerPoints.size(); i++)
			{
				if(consumerPoints.get(i).equals(p1))
				{
					consumerPoints.remove(i);
					i = consumerPoints.size();
				}
			}
		}
		
	   /*
		* If the Point is not on the curve, throw a Runtime Exception
		* stating that the Point does not exist on the curve.
		*/
		else
		{
			throw new RuntimeException
			("Point does not exist to delete!");
		}
	}
		
   /**
	* Checks whether a Point sent in is equal to any Point on the curve.
	* If the Point is on the curve, isPointOnSlope (Defaultly set to -1)
	* is set to 1. If the Point is not on the curve, it then checks 
	* whether the Point has an quantity value that corresponds to a Point
	* on the curve, and if it does, set isPointOnSlope equal to 0. If 
	* all of these options prove to be false, keep isPointOnSlope at -1 
	* and return the value of isPointOnSlope.
	* 
	*/
	public int searchPoint(Point p)
	{
		//Temporary Point set to the Point being sent in
		Point p1             = p;
			
		//Used to determine whether a Point is on the curve or not
		int   isPointOnSlope = -1;
			
	   /*
		* Go through the entire consumerPoints array list checking for 
		* whether any Points on it are equal to the Point being checked
	    * and if there is, set isPointOnCurve to 1.
		*/
		for(int i = 0; i < consumerPoints.size(); i++)
		{
			if(consumerPoints.get(i).equals(p1))
			{
				isPointOnSlope = 1;
				i = consumerPoints.size();
			}
		}
			
	   /*
		* If isPointOnSlope is still -1 (Meaning no Points on curve were
		* equal to the Point sent in) then go through the entire 
		* consumerPoints array again to at least see if there is a Point
		* on it with the same quantity value as the Point being checked,
	    * and if there is, set isPointOnSlope equal to 0.
		*/
		if(isPointOnSlope == -1)
		{
			for(int i = 0; i < consumerPoints.size(); i++)
			{
				if(consumerPoints.get(i).getQuantity() 
						== p1.getQuantity())
				{
					isPointOnSlope = 0;
					i = consumerPoints.size();
				}
			}
		}
			
		//Return status on whether the Point is on the slope or not
		return isPointOnSlope;
	}
		
   /**
	* Replaces a Point on the curve with a requested Point by finding
	* the Point with the same x value (quantity) as the Point replacing
	* it, and then setting it equal to the new Point.
	*/
	public void replacePoint(Point p)
	{
		//Temporary Point set to the Point being sent in
		Point p1 = p;
			
	   /*
		* Go through the entire curve until it finds the Point with the
		* same quantity as Point p1, and then replace that Point with
		* p1. 
		*/
		for(int i = 0; i < consumerPoints.size(); i++)
		{
			if(consumerPoints.get(i).getQuantity() == p1.getQuantity()
					&& consumerPoints.get(i).getPrice() 
					!= p1.getPrice())
			{
				consumerPoints.set(i, p1);
				i = consumerPoints.size();
			}
		}
	}
		
   /**
	* Order the Points on the consumerPoints array list by their quantity
	* values to make it more orderly.
	*/
	public void orderByQuantity()
	{
		//A temporary Point used for swaping
		Point temp;
			
	   /*
		* Bubble sort method that goes through the array list the number 
		* of times equal to the size of the array list, and each time, 
		* going through the entire array list, and switching every Point 
		* which has a higher quantity value than the Point in the slot in
		* front of it on the array list, every time it goes through the 
		* array list.
		*/
		for(int i = 0; i < consumerPoints.size(); i++)
		{
			for(int j = 0; j < (consumerPoints.size() - 1); j++)
			{
				if(consumerPoints.get(j).getQuantity() >
					   consumerPoints.get(j + 1).getQuantity())
				{
					temp = consumerPoints.get(j);
					consumerPoints.set(j, consumerPoints.get(j + 1));
					consumerPoints.set(j + 1, temp);
				}
			}
		}
	}
		
   /**
	* Returns the integer value of how big the curve is 
	*/
	public int getCurveSize()
	{
		return consumerPoints.size();
	}

}

