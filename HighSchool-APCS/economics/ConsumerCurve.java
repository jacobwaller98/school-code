package apcs.economics;

public class ConsumerCurve extends AbstractCurve {

	/**
	 * Will create a new abstractcurve for the producer curve
	 * 
	 * @param num
	 *            Number of points
	 * @param m
	 *            Slope
	 * @param b
	 *            Y-Intercept
	 * @param dx
	 *            Difference between the x's
	 */
	public ConsumerCurve(int num, double m, double b, int dx) {
		super(num, m, b, dx);
	}

	/**
	 * Creates the default curve. A horizontal line at y=1
	 */
	public ConsumerCurve() {
		super(10, -1, 10, 1);

	}

	/**
	 * Sorts the entire curve
	 */
	public void sort() {
		for (int x = 0; x < myCurve.size(); x++) {
			for (int y = 0; y < myCurve.size() - 1; y++) {
				if (myCurve.get(y).getQuantity() > myCurve.get(y + 1).getQuantity()) {
					Point temp = myCurve.get(y);
					myCurve.set(y, myCurve.get(y + 1));
					myCurve.set(y + 1, temp);
				}
			}
		}

	}

}
