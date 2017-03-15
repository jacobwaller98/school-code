/**
 * Project: APCS
*/
package apcs.hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * @author Jacob Waller
 * @date Mar 6, 2016
 * @hour 3rd Hour
 * @class AP Computer Science
 */
public class Driver {
	
	/**
	 * Just calls the constructor
	 * @param arg
	 */
	public static void main(String arg[]) {
		new Driver();
	}

	/**
	 * Constructor
	 * Most of the things happen here
	 */
	public Driver() {
		TreeMap tm = new TreeMap();
		HashMap hm = new HashMap();
		HashSet hs = new HashSet();

		/*
		 * Competition where the rank of the player is the key and the name is
		 * the value
		 */
		tm.put(5, "Jason");
		tm.put(2, "Phil");
		tm.put(10, "Robert");
		tm.put(4, "Jim");
		tm.put(9, "Billy");
		tm.put(1, "John");
		tm.put(6, "Jacob");
		tm.put(3, "Miranda");
		tm.put(8, "Sarah");
		tm.put(7, "Miranda");
		System.out.println(tm);

		/*
		 * Storing fruit with the amount of calories per fruit as the key and
		 * the name of the fruit as the value
		 */
		hm.put(87, "Zucchini");
		hm.put(78, "Cherries");
		hm.put(80, "Clementine");
		hm.put(58, "Banana");
		hm.put(72, "Avocado");
		hm.put(63, "Orange");
		hm.put(65, "Kiwi");
		hm.put(67, "Watermelon");
		hm.put(71, "Apricot");
		hm.put(53, "Apple");
		System.out.println(hm);

		/*
		 * Storing car makes by the name of the company
		 */
		hs.add("Honda");
		hs.add("Hyundai");
		hs.add("Bugatti");
		hs.add("Fiat");
		hs.add("Lamborghini");
		hs.add("Jaguar");
		hs.add("Bently");
		hs.add("Mini");
		hs.add("Nissan");
		hs.add("Lexus");
		System.out.println(hs);
	}
}

/*
 {1=John, 2=Phil, 3=Miranda, 4=Jim, 5=Jason,
  6=Jacob, 7=Miranda, 8=Sarah, 9=Billy, 10=Robert}
  
{80=Clementine, 65=Kiwi, 67=Watermelon, 53=Apple,
 87=Zucchini, 71=Apricot, 72=Avocado, 58=Banana, 
 78=Cherries, 63=Orange}
 
[Lamborghini, Mini, Lexus, Bently, Bugatti, Fiat,
 Jaguar, Hyundai, Honda, Nissan]
*/
