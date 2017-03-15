package apcs.economicsBonus;

/**
 * Title: Producer
 * @author AJbyrd
 * Date Updated: 10/1/2015
 *
 * Creates a new Producer object that "has a" ProducerCurve. This object
 * can be instantiated with a default constructor or constructor with 
 * curve values sent in. The Producer also can respond to a Point that
 * is bid on with either a new Point to bid on, or a Point that agrees
 * with the Point just bid on. It also begins the bidding process by
 * having a getLastPoint method to send back the ProducerCurves last
 * Point.
 */
public class Producer
{
	//Declaration of a ProducerCurve called proCurve
	private ProducerCurve proCurve;
	
   /**
    * Default constructor that just creates a new default ProducerCurve
    */
	public Producer()
	{
		proCurve = new ProducerCurve();
	}
	
   /**
    * Constructor with arguments sent in to create a new ProducerCurve
    * with the given values.
    * 
    * @param curveSize
    * @param m
    * @param b
    * @param dx
    */
	public Producer(int curveSize, double m, double b, int dx)
	{
		proCurve = new ProducerCurve(curveSize, m, b, dx);
		System.out.println(proCurve);
	}
	
   /**
    * Responds to a Bid from the Consumer with either a new Point to
    * bid on if the Producer disagrees with the bidding Point, or 
    * sends back the same Point if the Producer agrees with the
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
	    * coordinate on the Producer curve that could be sent back to
	    * bid over, or not. If there is not, temp stays as 0 after 
	    * passing through the for loop, and then is used to set p1 to
	    * a value of 0, 0, which though it exists on the graph, it is
	    * not efficient, and therefore shows that an equilibrium Point
	    * could not be found. 
	    */
		int temp = 0;
		
	   /*
	    * If the proCurve contains the Point bidded, then the Producer
	    * agrees with the bid, and sets the Point to be returned equal
	    * to the Point bidded.
	    */
		if(proCurve.contains(p) == true)
		{
			p1 = p;
		}
		
	   /*
	    * If the proCurve does not contain the Point bidded, go through
	    * the entire proCurve and find where in the curve there is a 
	    * Point of the same quantity as the Point being bidded, and
	    * if the Point bidded is below the curve, then set p1 to the next
	    * Point on the curve so that it can be bidded over. If the Point
	    * bidded is above the curve, then agree with it, as it is a 
	    * better deal for you.
	    * 
	    * If there is no Point with the same quantity value on the curve,
	    * then send back the Point (0,0) as a error Point used to show
	    * that no equilibrium or agreement can be reached.
	    */
		else
		{
			for(int i = 0; i < proCurve.getCurve().size(); i++)
			{
			   /*
			    * If they have equal quantities
			    */
				if(proCurve.getCurve().get(i).getQuantity() 
								== p.getQuantity())
				{
				   /*
				    * If the Point bidded has a price greater than the
				    * price of the Point on the curve, then accept the
				    * deal as it is a better deal for you.
				    */
					if(proCurve.getCurve().get(i).getPrice()
							    <  p.getPrice())
					{
						p1 = p;
					}
					
				   /*
				    * If Point bidded is below the curve, then send back
				    * a new Point to be bidded over that you agree with.
				    */
					else
					{
						try {
							p1 = proCurve.getCurve().get(i-1);
							}
							catch(Exception e)
							{
								p1 = new Point(0,0);
							}
					}
					
					//There was a Point on the curve to be bidded over
					temp = 1;
					
					//Set the LCV to the curves size to exit the loop
					i = proCurve.getCurve().size();
				}
				
				else if(p.getQuantity() - radius <= proCurve.getCurve().get(i).getQuantity()
						&& proCurve.getCurve().get(i).getQuantity() != p.getQuantity())
				{
					/*
					 * If the Point bidded has a price greater than the
					 * price of the Point on the curve, then accept the
					 * deal as it is a better deal for you.
					 */
					if(proCurve.getCurve().get(i).getPrice()
								    <  p.getPrice())
					{
						p1 = p;
					}
						
				   /*
					* If Point bidded is below the curve, then send back
					* a new Point to be bidded over that you agree with.
					*/
					else
					{
						try {
							p1 = proCurve.getCurve().get(i-1);
							}
							catch(Exception e)
							{
								p1 = new Point(0,0);
							}
					}
						
					//There was a Point on the curve to be bidded over
					temp = 1;
						
					//Set the LCV to the curves size to exit the loop
					i = proCurve.getCurve().size();
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
	
   /**
    * Return the last Point on the proCurve to start off the bidding.
    * 
    * @return
    */
	public Point getLastPoint()
	{
		return proCurve.getCurve().get(proCurve.getCurve().size() - 1);
	}
}
