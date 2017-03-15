package apcs.economicsBonus;

import java.util.ArrayList;

/**
 * Title: AbstractCurve
 * @author AJbyrd
 * Date Updated: 9/24/15
 *
 * Constructs a new AbstractCurve in which Points can be added,
 * deleted, checked to see if they are on the curve, sorting the
 * curve, getting the curve, and replacing Points on the curve with other
 * Points.
 */
public abstract class AbstractCurve 
{
	//Consturcts a new ArrayList of Points called myCurve
	private ArrayList <Point> myCurve;
	
   /**
	* The AbstractCurve constructor, that is sent in a curveSize,
	* a slope, a y intercept, and a change in x variable, in order to
	* instantiate the myCurve array list of a given curveSize.
	* 
	* @param int curveSize, double m, double b, int dx
    */
	public AbstractCurve(int curveSize, double m, double b, int dx)
	{		
	   /*
	    * Instantiates a new myCurve array list of Points 
		*/
		myCurve = new ArrayList<Point>();
					
	   /*
		* Sets the default y value for the first Point on the curve to
		* be equal to the y intercept, and then sets b (the y-int) equal
		* to it as well, to work in the equation y = mx + b;
		*/
		double y  = b;
					
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
			myCurve.add(new Point(x, y));
			x += dx;
			y  = (m * x) + b;
		}
	}
	
   /**
	* Removes a Point on the curve that is equal to the Point sent in if
	* such Point exists on the curve. If the Point does not exist, just
	* return false for being able to remove the Point.
	* 
	* @param p
	* @return
	*/
	public boolean remove(Point p)
	{
	   /*
	    * Temporary boolean used to determine whether the Point could be 
	    * deleted or not
	    */
		boolean temp = false;
				
	   /*
		* Checks through the entire array list looking for a Point that
		* is equal to the Point sent in, and when its found, remove that
		* Point from the array list.
		*/
		if(this.contains(p) == true)
		{
			for(int i = 0; i < myCurve.size(); i++)
			{
				if(myCurve.get(i).equals(p))
				{
					myCurve.remove(i);
					temp = true;
					i = myCurve.size();
				}
			}
		}
			
		//Return whether this method worked
		return temp;
	}
	
	
   /**
	* Add a point to the myCurve array list if the Point does not already 
	* exist on the curve. If the Point isn't on the curve, but has the
	* same X coordinate as a Point on the curve, then it will replace the
	* Point originally on the curve with the new Point.
	* 
	* After that go to the sort method to order the points
	* on the curve, and return whether the Point could be added or not
	* by a boolean.
	* 
	* @param p
	* @return
	*/
	public boolean add(Point p)
	{
	   /*
	    * Temporary boolean used to determine whether the Point could be
	    * added or not.
	    */
		boolean temp =  false;
		
	   /*
		* If the Point isn't on the array, but has the same x coordinates
		* as a Point on the array, then replace that Point with the new
		* Point passed in.
		* 
		* Then set temp to true, as the Point was successfully added
		*/
		if(this.searchByX(p) != -1)
		{
			myCurve.set(this.searchByX(p), p);
			
			//Set temp equal to true
			temp = true;
		}
		
	   /*
		* If the Point is not on the curve, and it has not already been 
		* replaced, then add the new Point to the array list, and set 
		* temp to true, as the Point was successfully added.
		*/
		if(this.contains(p) == false)
		{
			myCurve.add(p);
			temp = true;
		}
				
	   
				
		//Order the new array by quantity (the x coordinates)
		this.sort();
			
		return temp;
	}
	
   /**
    * Determines whether a point sent in is on the curve or not, and if
    * it is, return true, if it isn't, send back the default response of
    * false.
    * 
    * @param p
    * @return
    */
	public boolean contains(Point p)
	{
			
	   /*
	    * Used to determine whether a Point is on the curve or not.
	    * Defaultly set to false.
	    */
		boolean   isPointOnSlope = false;
			
	   /*
		* Go through the entire myCurve array list checking for 
		* whether any Points on it are equal to the Point being checked
	    * and if there is, set isPointOnCurve to true.
		*/
		for(int i = 0; i < myCurve.size(); i++)
		{
			if(myCurve.get(i).equals(p))
			{
				isPointOnSlope = true;
				i = myCurve.size();
			}
		}
					
		//Return status on whether the Point is on the slope or not
		return isPointOnSlope;
	}
	
   /**
	* Returns a String that is compiled by adding all the Strings
	* of all the coordinates of the Points in the myCurve
	* array list.
	*/
	public String toString()
	{
		String temp = "";
		
		for(int i = 0; i < myCurve.size(); i++)
		{
			temp += myCurve.get(i).toString();
		}
		
		return temp;
	}
	
    //Calls the abstract method of sort
	public abstract void sort();
	
   /**
    * Checks to see if there is a Point with the same X coordinates as
    * the Point passed in, if there is not already a Point that is equal
    * to the Point passed in on the curve. If there is a Point with the 
    * same X coordinate, then send back the slot number of that Point on
    * the curve. If there is not, keep sameXPoint as -1 and send that 
    * back since no Point on a Consumer or Producer curve should have 
    * negative Points.
    * 
    * @param p
    * @return
    */
	private int searchByX(Point p)
	{
		//Integer sent back that is defaultly set to -1
		int sameXPoint = -1;
		
	   /*
	    * If the Point passed in is not on the curve (meaning it
	    * definitly does not have the same Y as any Point on the curve), 
	    * then loop through the entire curve and check to make sure there
	    * are no  coordinates with the same X. If they have the same
	    * X, then set sameXPoint to the slot in the Array List to be
	    * sent back.
	    */
		if(myCurve.contains(p) == false)
		{
			for(int i = 0; i < myCurve.size(); i++)
			{
				if(myCurve.get(i).getQuantity() 
							== p.getQuantity())
				{
					sameXPoint = i;
					i = myCurve.size();
				}
			}
		}
		
		//Return the value of sameXPoint
		return sameXPoint;
	}
	
   /**
    * Returns the myCurve Array List to be used
    * 
    * @return
    */
	public ArrayList<Point> getCurve()
	{
		return myCurve;
	}
}
