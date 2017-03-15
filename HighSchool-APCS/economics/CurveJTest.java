package apcs.economics;
/*
 * Author: Jacob Waller
 * Date: 9/24/15
 * 
 */

import static org.junit.Assert.*;

import java.lang.annotation.Repeatable;

import org.junit.Rule;
import org.junit.Test;

public class CurveJTest {
	ProducerCurve a = new ProducerCurve();
	ConsumerCurve b = new ConsumerCurve();
	@Rule
	public RepeatRule repeatRule = new RepeatRule();


	@Test
	@Repeat(times = 1000)
	public void testAdd() {
		// Create two random numbers for price and quantity
		double price = Math.random() * 10;
		int quantity = (int) Math.random() * 10;
		// makes sure it can add the points once
		assertTrue(b.add(new Point(quantity, price)));
		assertTrue(a.add(new Point(quantity, price)));
		// Makes sure it cannot add the points twice
		assertFalse(b.add(new Point(quantity, price)));
		assertFalse(a.add(new Point(quantity, price)));
	}

	@Test
	@Repeat(times = 1000)
	public void testContains() {
		// Create random numbers for the price and quantity variables
		double price = Math.random() * 10;
		int quantity = (int) Math.random() * 10;
		// Add the random points to the curve
		a.add(new Point(quantity, price));
		b.add(new Point(quantity, price));
		// Check if they're on the curve
		assertTrue(a.contains(new Point(quantity, price)));
		assertTrue(b.contains(new Point(quantity, price)));
		// Checks to make sure that the curve is a function and that it won't
		// return true if only the quantity is there
		assertFalse(a.contains(new Point(quantity, price + 1)));
		assertFalse(b.contains(new Point(quantity, price + 1)));
	}

	@Test
	@Repeat(times = 1000)
	public void testDelete() {
		// Create random numbers for the price and quantity variables
		double price = Math.random() * 15;
		int quantity = (int) Math.random() * 155;
		// Adds the random points
		a.add(new Point(quantity, price));
		b.add(new Point(quantity, price));
		// Tries to delete the points, makes sure it can
		assertTrue(a.delete(new Point(quantity, price)));
		assertTrue(b.delete(new Point(quantity, price)));
		// tries to delete points again makes sure it cannot
		assertFalse(a.delete(new Point(quantity, price)));
		assertFalse(b.delete(new Point(quantity, price)));
	}

}
