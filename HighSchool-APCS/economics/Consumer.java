/**
 * Author Jacob Waller
 * 10/6/15
 */

package apcs.economics;

public class Consumer {
	private ConsumerCurve a = new ConsumerCurve(10,.5,0,1);

	public Point respondToBid(Point p) {
		if (a.contains(p)) {
			return p;
		}
		if (a.pointInRadius(a.getPointAfter(p),p))
		{
			return p;
		}
		return a.getPointAfter(p);
	}
}