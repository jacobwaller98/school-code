package apcs.economicsBonus;

/**
* Title: Consumer
* @author AJbyrd
* Date Updated: 10/1/2015
*
* Creates a new Consumer object that "has a" ConsumerCurve. This object
* can be instantiated with a default constructor or constructor with 
* curve values sent in. The Consumer also can respond to a Point that
* is bid on with either a new Point to bid on, or a Point that agrees
* with the Point just bid on. 
*/
public class Consumer 
{
	//Declaration of a ConsumerCurve called conCurve
	private ConsumerCurve conCurve;
	
   /**
	* Default constructor that just creates a new default ConsumerCurve
	*/
	public Consumer()
	{
		conCurve = new ConsumerCurve();
	}
	
   /**
	* Constructor with arguments sent in to create a new ConsumerCurve
	* with the given values.
	* 
	* @param curveSize
	* @param m
	* @param b
    * @param dx
	*/
	public Consumer(int curveSize, double m, double b, int dx)
	{
		conCurve = new ConsumerCurve(curveSize, m, b, dx);
		System.out.println(conCurve);

	}
	
   /**
	* Responds to a Bid from the Producer with either a new Point to
	* bid on if the Consumer disagrees with the bidding Point, or 
	* sends back the same Point if the Consumer agrees with the
	* Point bidded.
	* 
	* @param p
	* @return
	*/
	public Point respondToBid(Point p, int radius)
	{
		//The Point sent back for bidding that is defaultly set to null
		Point p1 = null;
		
	   /*
		* Temporary int used in determining whether there was an x 
		* coordinate on the Consumer curve that could be sent back to
		* bid over, or not. If there is not, temp stays as 0 after 
		* passing through the for loop, and then is used to set p1 to
		* a value of 0, 0, which though it exists on the graph, it is
		* not efficient, and therefore shows that an equilibrium Point
		* could not be found. 
		*/
		int temp = 0;
		
	   /*
		* If the conCurve contains the Point bidded, then the Consumer
		* agrees with the bid, and sets the Point to be returned equal
		* to the Point bidded.
		*/
		if(conCurve.contains(p) == true)
		{
			p1 = p;
		}
		
	   /*
		* If the conCurve does not contain the Point bidded, go through
		* the entire conCurve and find where in the curve there is a 
		* Point of the same quantity as the Point being bidded, and
		* if the Point bidded is above the curve, then set p1 to the next
		* Point on the curve so that it can be bidded over. If the Point
		* bidded is below the curve, then agree with it, as it is a 
	    * better deal for you.
		* 
		* If there is no Point with the same quantity value on the curve,
		* then send back the Point (0,0) as a error Point used to show
		* that no equilibrium or agreement can be reached.
		*/
		else
		{
			for(int i = 0; i < conCurve.getCurve().size(); i++)
			{
			   /*
			    * If they have equal quantities
			    */
				if(conCurve.getCurve().get(i).getQuantity() 
								== p.getQuantity())
				{
				   /*
					* If the Point bidded has a price less than the
					* price of the Point on the curve, then accept the
				    * deal as it is a better deal for you.
					*/
					if(conCurve.getCurve().get(i).getPrice()
							    >  p.getPrice())
					{
						p1 = p;
					}
					
				   /*
					* If Point bidded is above the curve, then send back
					* a new Point to be bidded over that you agree with.
					*/
					else
					{
						try {
						p1 = conCurve.getCurve().get(i-1);
						}
						catch(Exception e)
						{
							p1 = new Point(0,0);
						}
					}
					
				   /*
				    * States that there was a Point on the curve to be
				    *  bidded over
				    */
					temp = 1;
					
					//Exit the loop
					i = conCurve.getCurve().size();
				}
				
				else if(p.getQuantity() - radius <= conCurve.getCurve().get(i).getQuantity()
						&& conCurve.getCurve().get(i).getQuantity() != p.getQuantity())
				{
					/*
					* If the Point bidded has a price less than the
					* price of the Point on the curve, then accept the
				    * deal as it is a better deal for you.
					*/
					if(conCurve.getCurve().get(i).getPrice()
							    >  p.getPrice())
					{
						p1 = p;
					}
					
				   /*
					* If Point bidded is above the curve, then send back
					* a new Point to be bidded over that you agree with.
					*/
					else
					{
						try {
							p1 = conCurve.getCurve().get(i-1);
							}
							catch(Exception e)
							{
								p1 = new Point(0,0);
							}
					}
					
				   /*
				    * States that there was a Point on the curve to be
				    *  bidded over
				    */
					temp = 1;
					
					//Exit the loop
					i = conCurve.getCurve().size();
				}
			}
			
		   /*
			* If the Point had an x value that is not the same as any
			* x value on the curve, then set p1 to (0,0) as an error 
			* Point.
			*/
			if (temp == 0)
			{
				p1 = new Point(0, 0);
			}
		}
		
		//Return the Point for bidding
		return p1;
	}
}
