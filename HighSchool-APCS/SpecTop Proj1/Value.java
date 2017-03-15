import java.awt.List;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Value {

	private ArrayList<Integer> numbers = new ArrayList<Integer>();

	// Variable used to store the number of digits in the large number
	private int digits;
	// Variable used to store how many decimal points there are

	public Value(String startNum) {
		for (int i = startNum.length() - 1; i >= 0; i--) {
			numbers.add(Character.getNumericValue(startNum.charAt(i)));
		}
	}

	public Value(int startNum, int accuracy) {
		// finds the amount of digits
		digits = (int) (Math.floor(Math.log10(startNum)) + 1);
		int someNum = startNum;
		// Saves all the digits of startNum into an ArrayList
		// To be used with math later
		for (int x = 0; x < digits; x++) {
			numbers.add(someNum % 10);
			someNum = someNum / 10;
		}
	}

	/**
	 * This method is used to add a Value to your value
	 * 
	 * @param addend
	 */
	public void add(Value addend) {
		ArrayList<Integer> adder = addend.translate();
		for (int x = 0; x < numbers.size() && x < adder.size(); x++) {
			numbers.set(x, numbers.get(x) + adder.get(x));
		}

		for (int x = 0; x < digits; x++) {
			// This will basically make sure that
			// if you have a number like 5 and 5
			// it will add an extra digit so you can
			// get 10 rather than 1, getting an out of bounds exception
			// and 2, getting a 0
			if (numbers.get(x) > 9) {

				try {
					numbers.set(x, numbers.get(x) - 10);
					numbers.set(x + 1, numbers.get(x + 1) + 1);
				} catch (Exception e) {
					numbers.add(x + 1, 1);
					digits++;
				}
			}
		}

	}

	/**
	 * This is used to compare the Value to an integer. It can Either be,
	 * Greater than, Less than, or equal to. It returns a string with one of
	 * those 3 options
	 * 
	 * @param comparable
	 * @return
	 */
	public String compare(int comparable) {
		if (comparable < 0) {
			comparable = 1 / 0;
		}
		ArrayList<Integer> compNum = new ArrayList<Integer>();
		int d = (int) (Math.floor(Math.log10(comparable)) + 1);

		// Put's the whole number (comparable) into the ArrayList
		// One digit per cell
		int someNum = comparable;
		for (int x = 0; x < d; x++) {
			compNum.add(someNum % 10);
			someNum = someNum / 10;
		}

		// go through Array List and check if the given int is greater than the
		// value of
		// the numbers Array List
		try {
			compNum.get(numbers.size() - 1);
		} catch (Exception e) {
			return "Less Than";
		}
		try {
			numbers.get(compNum.size() - 1);
		} catch (Exception e) {
			return "Greater Than";
		}

		for (int x = digits - 1; x > -1; x--) {
			if (numbers.get(x) == compNum.get(x)) {

			} else {
				if (numbers.get(x) > compNum.get(x)) {
					return "Less Than";
				} else {
					return "Greater Than";
				}

			}
		}
		return "Equal To";
	}

	/**
	 * This takes your Value and divides the divisor by subtracting the divisor
	 * over and over and counting how many times it does that. The only problem
	 * with this way of doing things is the speed
	 * 
	 * @param divisor
	 */
	public void divide(int divisor) {
		/**
		 * if (divisor <= 0) { divisor = 1 / 0; } if (compare(divisor) ==
		 * "Greater Than") { decimalNums = decimalNums.divide(new
		 * BigDecimal(divisor)); }
		 * 
		 * // divides by subtracting the divisor multiple times // And counting
		 * how many times it does that int counter = 0; if (compare(divisor) !=
		 * "Greater Than") { String bigger =
		 * "Ha, you're reading part of my useless code!!"; while (bigger !=
		 * "Greater Than") {
		 * 
		 * 
		 * subtract(new Value(divisor,0));
		 * 
		 * 
		 * bigger = compare(divisor); counter++; } }
		 * 
		 * int remainder = 0; for (int x = 0; x < numbers.size(); x++) {
		 * remainder += numbers.get(x) * (Math.pow(10, x)); } for (int x =
		 * numbers.size() - 1; x > -1; x--) { numbers.remove(x); } digits =
		 * (int) (Math.floor(Math.log10(counter)) + 1); int newNum = counter;
		 * for (int x = 0; x < digits; x++) { numbers.add(newNum % 10); newNum =
		 * newNum / 10; } // System.out.println("Remainder of " + remainder);
		 * 
		 * // decimalNums = decimalNums.divide(new BigDecimal(divisor));
		 * BigDecimal numeratorDec = new BigDecimal(remainder); //
		 * System.out.println(numeratorDec); BigDecimal denDec = new
		 * BigDecimal(divisor); // System.out.println(denDec);
		 * 
		 * decimalNums = decimalNums.add(numeratorDec.divide(denDec, acc, 2));
		 * // System.out.println("The decimal should equal: " + decimalNums);
		 * 
		 */
	}

	/**
	 * This method is used to multiply the Value by a multiplier Again, this is
	 * limited to only integers
	 * 
	 * @param multiplier
	 */
	public void multiply(Value multiplier) {
		
	}

	/**
	 * This method is used to subtract the subtractor from your Value
	 * 
	 * One of the limitations of my program is that it cannot go negative
	 * 
	 * @param subtractor
	 */
	public void subtract(Value subtractor) {

		ArrayList<Integer> sub = subtractor.translate();
		for (int x = 0; x < numbers.size() && x < sub.size(); x++) {
			numbers.set(x, numbers.get(x) - sub.get(x));
		}

		// Now, do basically the opposite of rounding
		for (int x = 0; x < sub.size() || x < digits; x++) {
			if (numbers.get(x) < 0) {
				try {
					numbers.set(x, 10 - Math.abs(numbers.get(x)));
					numbers.set(x + 1, numbers.get(x + 1) - 1);

				} catch (Exception e) {

				}

			}
		}

		// get rid of any empty cells that may have been subtracted out
		// Example...
		// 10 - 5
		// without this loop would read 05 for the answer
		// with will read 5
		for (int x = numbers.size() - 1; x > -1; x--) {
			if (numbers.get(x) == 0 && x != 0) {
				numbers.remove(x);
				digits--;
			} else {
				x = -2;
			}

		}

	}

	private ArrayList<Integer> translate() {
		ArrayList<Integer> translated = new ArrayList<Integer>();

		for (int x = 0; x < numbers.size(); x++) {
			translated.add(numbers.get(x));
		}

		return translated;
	}

	private void doubleThis()
	{
		for(int x = 0; x < numbers.size(); x++)
		{
			numbers.set(x, numbers.get(x)*2);
		}
		
		for(int x = 0; x < numbers.size(); x++)
		{
			if(numbers.get(x) >= 10)
			{
				numbers.set(x, numbers.get(x)-10);
				try {
					numbers.set(x+1, numbers.get(x+1)+1);
				}
				catch(Exception e) {
					numbers.add(1);
				}
			}
		}	
	}
	
	public String toString()
	{
		String val = "";
		for(int x = 0; x<numbers.size();x++)
		{
			val +=  numbers.get(x).toString();
		}
		return val;
	}
}

/*
 * 
 * .add(number), adds a number to the end .add(number, index) adds a number to a
 * specific index .remove(index) removes a number at the specific index
 * .set(number, index) changes an index in a place .get(1) gets the value
 * starting at
 */