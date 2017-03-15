package apcs.economicsBonus;
import java.util.ArrayList;

/**
 * Title:   ConsumerCurve
 * @author  Alex Byrd
 * @version September 2015
 * 
 * Creates a new AbstractCurve, with either default values or values
 * sent in to the non default ConsumerCurve constructor sent to it to
 * instantiate the AbstractCurve. 
 * 
 * It also contains the sort method, which is called by the AbstractCurve
 * class, used to sort the Points in the myCurve array list by quantity.
 */

public class ConsumerCurve extends AbstractCurve
{  
	
   /**
	* Default Constructor method that sets the curveSize to 10,
	* slope to -1, yint to 10, and change in x to 1.
	*/
	public ConsumerCurve() 
	{
	  /*
	   * Calls the parent classes (AbstractCurve) constructor method and
	   * passes it the given values (curveSize, slope, yint, and dx)
	   */
	   super(10, -1, 10, 1);
	}
	
   /**
    * Constructor method that calls the parent classes (AbstractCurve)
    * constructor method, and passes it the values sent in to this
    * constructor method. Those values being (curveSize, slope, yint,
    * and change in x).
    * 
    * @param curveSize
    * @param m
    * @param b
    * @param dx
    */
	public ConsumerCurve(int curveSize, double m, double b, int dx)
	{
		super(curveSize, m, b, dx);
	}
	
  
		
   /**
	* Order the Points on the myCurve array list (which is sent in)
	* by their quantity values to make it more orderly.
	*/
	public void sort()
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
		for(int i = 0; i < getCurve().size(); i++)
		{
			for(int j = 0; j < (getCurve().size() - 1); j++)
			{
				if(getCurve().get(j).getQuantity() >
					   getCurve().get(j + 1).getQuantity())
				{
					temp = getCurve().get(j);
					getCurve().set(j, getCurve().get(j + 1));
					getCurve().set(j + 1, temp);
				}
			}
		}
	}
}

