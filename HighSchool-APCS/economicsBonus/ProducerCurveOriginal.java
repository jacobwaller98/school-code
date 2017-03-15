package apcs.economicsBonus;

/**
 * Title:   ProducerCurve
 * @author  Alex Byrd
 * @version September 2015
 * Creates a ProducerCurve object that contains an array of 10 points
 * called producerPoints. These points are defaultly set in this method
 * to start at (0, 10.0) and have a slope of -1 from point to point.
 * 
 * It can then use this information to send back the coordinates of a
 * point in the array in String form, and also send back a Point in the
 * array to be used for other purposes.
 * 
 * This object also contains methods to add a Point to the curve, 
 * delete a Point, check if a Point is on the curve, replace a Point on
 * the curve, and sort Points on the curve by their quantities 
 * (x coordinates)
 */

public class ProducerCurveOriginal 
{
	//Creates an array of Points called producerPoints
	private Point[] producerPoints;
	
	//Creates an integer that states the size of the producerPoints array
	private int     curveSize;
	
   /**
    * Default Constructor method that constructs a new ProducerCurve that
    * instantiates a new array of 10 Points, and sets the coordinates
    * (quantity, price) of each Point in the array. The default 
    * coordinates for the first Point created in the array is (0, 10.0)
    * and each consecutive one goes down by a slope of -1 from there.
    * 
    * The curveSize is defaultly set to 10
    */
	public ProducerCurveOriginal() 
	{
		curveSize      = 10;
		
		//Instantiates producerPoints as a new array of 10 Points
		producerPoints = new Point[curveSize];
		
	   /*
	    * Sets the x and y values (quantity, price) of each Point in the
	    * producerPoints array. Every new Point instantiated along the 
	    * array will have a y value that goes down by 1, and an x value 
	    * that goes up by 1 every single point.
	    */
		for(int i = 0; i < 10; i++)
		{
			producerPoints[i] = new Point(i, i);
					
		}
	}
	
   /**
    * Another ProducerCurve constructor, that is sent in a curveSize,
    * y intercept, a slope, and a change in x variable, in order to
    * instantiate the producerPoints array of a given curveSize.
    */
	public ProducerCurveOriginal(int size, double m, double yint, int dx)
	{
		//Sets default curveSize equal to the size passed in
		curveSize = size;
		
	   /*
	    * Instantiates a new producerPoints array of points equal to
	    * an array of points of a given curveSize.
	    */
		producerPoints = new Point[curveSize];
		
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
	    * Goes through the entire array and instantiates all the Points
	    * and their coordinates for the array by adding the change in
	    * x onto the x variable, and then uses the slope, x, and yint
	    * values to find y for each x value using the equation
	    * y = mx + b.
	    */
		for(int i = 0; i < curveSize; i++)
		{
			producerPoints[i] = new Point(x, y);
			x += dx;
			y  = (m * x) + b;
		}
	}
	
   /**
    * Returns a String that is compiled by adding all the Strings
    * of all the coordinates of the Points in the producerPoints
    * array.
    */
	public String toString()
	{
		String temp = "";
		
		for(int i = 0; i < curveSize; i++)
		{
			temp += producerPoints[i].toString();
		}
		
		return temp;
	}
	
   /**
    * Returns a Point on the curve to be compared or negotiated over
    */
	public Point getPointOnCurve(int i)
	{
		Point temp  = producerPoints[i];
		
		return temp;
	}
	
   /**
    * Add a point to the producerPoints array by first checking to make
    * sure that the point does not already exist (and if it does, throw
    * a Runtime Exception to stop this methods execution), and that the
    * point doesn't have the same x coordinate (Which is the quantity of
    * the point, in which if it was the same, the point would need to be
    * replaced, and the curve would need to be shifted), and if both of 
    * those prove to be false, set a new temp array equal to the 
    * producerPoints array, add the new point to the temp array, and 
    * make curveSize 1 slot larger. Then set the producerPoints array
    * equal to the temp array to reset what the curve looks like.
    * 
    * After that go to the orderByQuantity method to order the points
    * on the curve.
    */
	public void add(Point p)
	{
	   /*
	    * Create a temporary array to store the old array in that is one
	    * size bigger, so that the new Point can be added to it.
	    */
		Point[] temp = new Point[curveSize + 1];
		
	   /*
	    * Create a temporary Point set equal to the Point passed in that
	    * is used in checking whether a Point is already on a curve or not
	    * and whether if it isn't on the curve, does it have the same
	    * x coordinate as a Point on the curve so that it can be replaced
	    */
		Point   p1   = p;

	   /*
	    * If the Point p1 is already on the curve, throw a new Runtime
	    * Exception that states that the Point is on the curve, and
	    * halt execution of this method.
	    */
		if(this.searchPoint(p1) == 1)
		{
			throw new RuntimeException
				("There is already a Point at that coordinate!");
		}
		
	   /*
	    * If the Point isn't on the curve, and it doesn't have the
	    * same x coordinates as a Point on the curve, loop through
	    * the for loop the number of times that there is slots in
	    * the producerPoints array, and set all those Points equal
	    * to the corresponding slots in the new temporary array. 
	    * 
	    * Then make curveSize one bigger for the new array, set the last
	    * Point in the temporary array equal to the new Point that
	    * was passed in, and set the producerPoints array equal to the
	    * temporary array that was one size bigger.
	    */
		else if(this.searchPoint(p1) == -1)
		{
			for(int i = 0; i < curveSize; i++)
			{
				temp[i] = producerPoints[i];
			}
		
			curveSize++;
			temp[curveSize - 1] = p1;
			producerPoints = temp;
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
    * Deletes a Point on the producerPoints array equal to the Point 
    * passed into the method. It then shrinks the size of the array
    * by 1. It does this by setting the Point being deleted to the
    * Point in the slot in front of it, and then continues on setting
    * each slot in front of it to the slot ahead of it until the very end
    * in which the very last slot does not get switched and is instead 
    * just deleted by being set to null.
    * 
    * If there is no Point on the curve equal to the Point sent in to
    * be deleted, it throws a Runtime Exception stating that the point
    * requested to be deleted, does not exist.
    */
	public void delete(Point p)
	{
		//Temporary Point set equal to the point sent in
		Point p1 = p;
		
	   /*
	    * Tells how many slots are left to rearrange after the point 
	    * that was deleted.
	    */
		int   slotsLeft;
		
	   /*
	    * Check to see if the Point being requested to be deleted is
	    * on the curve, then find that point, find how many Point slots
	    * in the producerPoints array are left after the Point, and then
	    * set the Point being deleted to the Point in the slot after it,
	    * and continue doing the same for every slot after the Point
	    * until the last slot, which will be deleted since there is no
	    * slot after it. Therefore deleting the Point requested to be
	    * deleted.
	    */
		if(this.searchPoint(p1) == 1)
		{
			for(int i = 0; i < curveSize; i++)
			{
				if(producerPoints[i].equals(p1))
				{
					slotsLeft = (curveSize - 1) - i;
				
					for(int j = 0; j < slotsLeft; j++)
					{
						producerPoints[i] = producerPoints[i + 1];
						i++;
					}
				
					producerPoints[curveSize - 1] = null;
					curveSize--;
					i = curveSize;
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
	    * Go through the entire producerPoints array checking for 
	    * whether any Points on it are equal to the Point being checked
	    * and if there is, set isPointOnCurve to 1.
	    */
		for(int i = 0; i < curveSize; i++)
		{
			if(producerPoints[i].equals(p1))
			{
				isPointOnSlope = 1;
				i = curveSize;
			}
		}
		
	   /*
	    * If isPointOnSlope is still -1 (Meaning no Points on curve were
	    * equal to the Point sent in) then go through the entire 
	    * producerPoints array again to at least see if there is a Point
	    * on it with the same quantity value as the Point being checked,
	    * and if there is, set isPointOnSlope equal to 0.
	    */
		if(isPointOnSlope == -1)
		{
			for(int i = 0; i < curveSize; i++)
			{
				if(producerPoints[i].getQuantity() == p1.getQuantity())
				{
					isPointOnSlope = 0;
					i = curveSize;
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
		for(int i = 0; i < curveSize; i++)
		{
			if(producerPoints[i].getQuantity() == p1.getQuantity()
					&& producerPoints[i].getPrice() 
					!= p1.getPrice())
			{
				producerPoints[i] = p1;
				i = curveSize;
			}
		}
	}
	
   /**
    * Order the Points on the producerPoints array by their quantity
    * values to make it more orderly.
    */
	public void orderByQuantity()
	{
		//A temporary Point used for swaping
		Point temp;
		
	   /*
	    * Bubble sort method that goes through the array the number of
	    * times equal to the size of the array, and each time, going
	    * through the entire array, and switching every Point which
	    * has a higher quantity value than the Point in the slot in
	    * front of it on the array, every time it goes through the 
	    * array.
	    */
		for(int i = 0; i < curveSize; i++)
		{
			for(int j = 0; j < (curveSize - 1); j++)
			{
				if(producerPoints[j].getQuantity() >
				   producerPoints[j + 1].getQuantity())
				{
					temp                  = producerPoints[j];
					producerPoints[j]     = producerPoints[j + 1];
					producerPoints[j + 1] = temp;
				}
			}
		}
	}
	
   /**
    * Returns the integer value of how big the curve is (curveSize)
    */
	public int getCurveSize()
	{
		return curveSize;
	}
	
}


