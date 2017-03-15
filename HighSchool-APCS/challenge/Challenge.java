/**
 * Project: APCS
*/
package apcs.challenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author Jacob Waller
 * @date Mar 14, 2016
 * @hour 3rd Hour
 * @class AP Computer Science
 */
public class Challenge {

	public static void main(String arfg[]) {
		new Challenge();
	}

	public Challenge() {
		PrintWriter out;
		try {
			out = new PrintWriter("challenge.txt");
			out.println("First challenge");
			out.println("1: " + numOne(1));
			out.println("15: " + numOne(15));
			out.println("27: " + numOne(27));
			out.println("57: " + numOne(57));
			out.println();
			out.println("Second challenge");
			out.println(numTwo(2, 1024));
			out.println();
			out.println("Third Challenge");
			out.println(numThree());
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("I dun reached en error");
		}
	}

	/**
	 * Does the first challenge
	 * 
	 * @param x
	 * @return
	 */
	public int numOne(int x) {
		if (x <= 1)
			return 1;
		if (isPrime(x)) {
			return (int) (Math.pow(x, 2) + x);
		} else {
			return 2 * numOne(x - 3) - 4;
		}

	}

	/**
	 * Does the second challenge
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public String numTwo(int x, int y) {
		int num = 0;
		for (int i = x; i < y; i++) {
			if (containsOnes(i)) {
				num++;
			}
		}
		return Integer.toString(num);
	}

	/**
	 * does the third challenge
	 * 
	 * @return
	 */
	public String numThree() {

		Scanner s;
		try {
			s = new Scanner(new File("poem.txt"));
		} catch (FileNotFoundException e) {
			s = new Scanner("ERROR");
			e.printStackTrace();
		}
		TreeMap m = new TreeMap();
		while (s.hasNext()) {
			String word = s.next();
			Integer i = (Integer) m.get(word);
			if (i == null) // if new word
			{
				// put new word in the map since we�ve seen it once
				m.put(word, new Integer(1));
			} else {
				// add 1 to the number of times we�ve seen it
				m.put(word, new Integer(i.intValue() + 1));
			}
		}

		s.close();
		return m.toString();
	}

	/**
	 * determines whether the given int contains 8 ones in it's binary
	 * representation
	 * 
	 * @param x
	 * @return
	 */
	public boolean containsOnes(int x) {
		String a = Integer.toBinaryString(x);
		char[] b = a.toCharArray();
		int num = 0;
		for (int u = 0; u < b.length; u++) {
			if (b[u] == '1') {
				num++;
			}
			if (num >= 8)
				return true;
		}

		return false;
	}

	/**
	 * Returns true if the number is prime
	 * @param n
	 * @return
	 */
	public boolean isPrime(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}

}
