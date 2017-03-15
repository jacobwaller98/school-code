/**
 * Author: Jacob Waller
 * Date:   11/13/15
 */

package apcs.divisibility;

import java.io.BufferedReader;
import java.io.FileReader;

public class Divisible {

	private static int[] nums = new int[4];

	public static void main(String a[]) throws Exception {
		getNums();
		System.out.println("The GCF of: " + nums[0] + " and " + nums[1] 
				+ " is: " + findGCF(nums[0], nums[1]));
		System.out.println("The LCM of: " + nums[2] + " and " + nums[3] 
				+ " is: " + findLCM(nums[2], nums[3]));
	}

	/**
	 * Will save the numbers in the text file to the array nums
	 * 
	 * @throws Exception
	 */
	public static void getNums() throws Exception {
		String fileName = "data.txt";
		String myNum = "";
		String line = null;
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		int x = 0;
		while ((line = bufferedReader.readLine()) != null) {
			myNum = line;
			nums[x] = Integer.parseInt(myNum);
			x++;
		}
		bufferedReader.close();
	}

	/**
	 * Uses Euclid method for finding the GCF of two numbers
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public static int findGCF(int x, int y) {
		if (y == 0)
			return x;
		return findGCF(y, x % y);
	}

	/**
	 * Uses the easiest one line method for finding the LCM
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int findLCM(int a, int b) {
		return Math.abs(a * b) / findGCF(a, b);
	}
}
