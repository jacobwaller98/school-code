package apcs.economics;

public class Market2 
{

	public static void main(String[] args)
	{
		Producer producer   = new Producer();
		Consumer consumer   = new Consumer();
		Point    testPoint1 = new Point(2,9);
		Point    testPoint2 = new Point(2,1);
		Point    testPoint3 = new Point(5,5);
		
		System.out.println("When Point "+testPoint1.toString() 
			+" was bidded, producer responded with "
				+producer.respondToBid(testPoint1).toString());
		
		System.out.println("When Point "+testPoint2.toString() 
			+" was bidded, producer responded with "
				+producer.respondToBid(testPoint2).toString());
		
		System.out.println("When Point "+testPoint3.toString() 
			+" was bidded, producer responded with "
				+producer.respondToBid(testPoint3).toString());
		
		System.out.println("When Point "+testPoint1.toString() 
			+" was bidded, consumer responded with "
				+consumer.respondToBid(testPoint1).toString());
		
		System.out.println("When Point "+testPoint2.toString() 
			+" was bidded, consumer responded with "
				+consumer.respondToBid(testPoint2).toString());
		
		System.out.println("When Point "+testPoint3.toString() 
			+" was bidded, consumer responded with "
				+consumer.respondToBid(testPoint3).toString());

	}

}
