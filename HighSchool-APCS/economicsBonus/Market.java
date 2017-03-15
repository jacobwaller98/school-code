package apcs.economicsBonus;

/**
 * Title: Market
 * @author AJbyrd
 * Date Updated: 10/5/2015
 *
 * Runs the market consisting of a Producer and a Consumer which have
 * given curves with specified slopes, and changes in x and y's. The
 * market prints out all transactions that occur in the market and 
 * states the equilibrium Point that the Producer and Consumer agree
 * upon, if there is one, and states that there is no equilibrium if
 * one cannot be found. 
 */
public class Market 
{
	public static void main(String[] args)
	{
	   /*
	    * Creates two different producers and consumers. One set that is
	    * created with a default curve, and one that can be set to any
	    * given curve.
	    */
		Producer producer      = new Producer();
		Consumer consumer      = new Consumer();
		Producer producer2;
		Consumer consumer2;
		
       /*
        * Two temporary points used in the bidding process.
        */
		Point    temp          = new Point();
		Point    temp2         = new Point();
		
		//final Point used to check if an error Point is sent back
	    final Point CHECKPOINT = new Point(0, 0);
	    
	   /*
	    * Variables for setting up a given curve
	    */
		int      dx            = 1;
		int      dx2           = 1;		
		double   m			   = 1;
		double   m2            = -1;
		int 	numberOfPoints1 = 10;
		int 	numberOfPoints2 = 10;
		double b1 = 1;
		double b2 = 0;
		
		final int RADIUS       = Math.abs(dx-dx2)*2;
		int      defaultradius = 0;
		
		//Initial bidding Point sent in by the Producer
		temp                = producer.getLastPoint();
		
	   /*
	    * While the responses don't equal each other, keep bidding.
	    * and print out each bid as it occurs. temp2 is set to the
	    * Consumers bid, while temp is set to the Producers bid.
	    */
		while(!temp.equals(temp2))
		{
			temp2 = consumer.respondToBid(temp, defaultradius);
			System.out.println("Consumer response: "
				+temp2.toString() +" \n");
			
			temp  = producer.respondToBid(temp2, defaultradius);
			System.out.println("Producer response: "
				+temp.toString() +" \n");
			
		}
		
		//Print out the equilibrium Point
		System.out.println("For the Default curves: "
				+ "The equilibrium point is "+temp.toString());
		
		
	   /*
	    * Instantiate values for the non default curves, and construct
	    * those two curves.
	    */
		producer2           = new Producer(numberOfPoints1, m, b1, dx);
		consumer2           = new Consumer(numberOfPoints2, m2, b2, dx2);
		
		
		
		//Initial bidding Point sent in by the Producer
		temp                = producer2.getLastPoint();
		
		System.out.println("temp: "+temp.getQuantity());
       /*
		* While the responses don't equal each other, keep bidding.
		* and print out each bid as it occurs. temp2 is set to the
		* Consumers bid, while temp is set to the Producers bid.
		*/
		while(!temp.equals(temp2))
		{
			temp2 = consumer2.respondToBid(temp, RADIUS);
			System.out.println("Consumer response: "
					+temp2.toString() +" \n");
			
			temp  = producer2.respondToBid(temp2, RADIUS);
			System.out.println("Producer response: "
					+temp.toString() +" \n");
			
		}
		
	   /*
	    * If the equilibrium Point is equal to the CHECKPOINT of (0,0)
	    * then print out that no equilibrium point could be found.
	    */
		if(temp.equals(CHECKPOINT) == true)
		{
			System.out.println("No equlibrium point could be found!");
		}
		
	   /*
	    * If an equilibrium point exists, print out its coordinates.
	    */
		else
		{
			System.out.println("The equillibrium point is "
					+temp.toString());
		}
	}

}
