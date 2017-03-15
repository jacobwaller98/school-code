package apcs.economicsBonus;

/**
 * Title:   TestCurves
 * @author  Alex Byrd
 * @version September 2015
 * Used to print out the points on each of the curves, what the point of
 * equilibrium is on, and test adding, deleting, replacing and checking 
 * points on a curve. 
 */

public class TestCurves
{
   /*
    * The declaration and instantiation of a consumer and producer curve
    * to be used for testing in the main method. It also declares another
    * producer curve, used for testing the non-default constructor method
    */
	private static ProducerCurve producer  = new ProducerCurve();
	private static ProducerCurve producer2;
	private static ConsumerCurve consumer2;
	private static ConsumerCurve consumer  = new ConsumerCurve();
	
	public static void main(String[] args)
	{
	   /*
	    * Declares and instantiates a change in y, and change in x
	    * variable (both are doubles) and uses them to find the slope
	    * variable (double m) which is then used to construct a
	    * Producer Curve of a given curve.
	    */
		double yChange = 5;
		int    xChange = 3;
		double m       = (yChange / (double) xChange);
		
	   /*
	    * Instantiates a second producer curve that does not use the
	    * default constructor. It sends in the (curveSize, slope
	    * of the curve, yint, and the change in x). 
	    */
		producer2   = new ProducerCurve(20, m, 8, xChange);
		
	   /*
	    * Instantiates a second consumer curve that does not use the
	    * default constructor. It sends in the (curveSize, yint, slope
	    * of the curve, and the change in x).
	    */
		consumer2   = new ConsumerCurve(20, m, 0, xChange);
		
		//A test Point used to add and delete and such
		Point test1 = new Point (4, 10);
	
	   /*
	    * Print out both the consumer and producer curves
	    */
		System.out.println("Producer Curve: "+producer.toString());	
		System.out.println("Consumer Curve: "+consumer.toString());	
		
		  
	   /*
		* Print out whether the test Point is on the curve.
		* 
		* 1 if on the curve
		* 
		* 0 if not on, but has same x coordinate as a
		* Point on the curve
		* 
		* -1 if not on the curve, and not having the same
		* x coordinate as any point on the curve.
		*/
	    System.out.println("\n\nIs "+test1.toString() 
			   +" on the Producer curve?: "
			   +producer.contains(test1));
	    
	    /*
		* Print out whether the test Point is on the curve.
		* 
		* 1 if on the curve
		* 
		* 0 if not on, but has same x coordinate as a
		* Point on the curve
		* 
		* -1 if not on the curve, and not having the same
		* x coordinate as any point on the curve.
		*/
	    System.out.println("\n\nIs "+test1.toString() 
			   +" on the Consumer curve?: "
			   +consumer.contains(test1));
			
	   /*
	    * Try to delete a Point off the ProducerCurve and be ready to
		* catch an exception if the Point does not exist on the curve
		* to delete.
		*/
	    System.out.println("\nPoint could be deleted from producer: "
				+producer.remove(test1));
		
		producer.remove(test1);
		
	   /*
		* Try to delete a Point off the ConsumerCurve and be ready to
	    * catch an exception if the Point does not exist on the curve
	    * to delete.
	    */
		System.out.println("Point could be deleted from consumer: "
				+consumer.remove(test1));
		
		consumer.remove(test1);

	   /*
		* Print out again whether the test Point is on the 
		* ProducerCurve, and output on the screen the same
		* parameters as the last check.
		*/
		System.out.println("\n\nIs "+test1.toString() 
				+" on the Producer curve?: "
				+producer.contains(test1));
		
	   /*
		* Print out again whether the test Point is on the 
		* ConsumerCurve, and output on the screen the same
		* parameters as the last check.
		*/
		System.out.println("\n\nIs "+test1.toString() 
				+" on the Consumer curve?: "
				+consumer.contains(test1));
			
	   /*
		* Print out both the consumer and producer curves
		*/
		System.out.println("Producer Curve: "+producer.toString());		
		System.out.println("Consumer Curve: "+consumer.toString());		
			
	   /*
		* Try to add a Point to the curve, and be ready to catch
		* an exception if the Point is already on the curve.
		*/
		System.out.println("\nPoint could be added to producer: "
				+producer.add(test1));
		
		producer.add(test1);
		
		
		

	   /*
		* Try to add a Point to the curve, and be ready to catch
		* an exception if the Point is already on the curve.
		*/
		System.out.println("Point could be added to consumer: "
				+consumer.add(test1));
		
		consumer.add(test1);
		
	   /*
		* Check again to see if the test Point is on the curve. 
		* With the same parameters as the last two checks.
		*/
		System.out.println("\n\nIs "+test1.toString() 
				+" on the Producer curve?: "
				+producer.contains(test1));
		
	   /*
		* Check again to see if the test Point is on the curve. 
		* With the same parameters as the last two checks.
		*/
		System.out.println("\n\nIs "+test1.toString() 
				+" on the Consumer curve?: "
				+consumer.contains(test1));
			
	   /*
		* Print out both the consumer and producer curves
		*/
		System.out.println("Producer Curve: "+producer.toString());		
		System.out.println("Consumer Curve: "+consumer.toString());		
		
		System.out.println("------------------------------------\n\n");
		
	   /*
		* Print out both the consumer2 and producer2 curves
		*/
	  	System.out.println("Producer Curve: "+producer2.toString());	
		System.out.println("Consumer Curve: "+consumer2.toString());	
		
		
	}

}



