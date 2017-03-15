/**
 * Author Jacob Waller
 * 10/6/15
 */
package apcs.economics;

public class Market {
	Consumer a = new Consumer();
	Producer b = new Producer();

	public static void main(String a[]) {
		new Market();
	}

	public Market() {
		Point p = new Point(1,1);
		boolean agreed = false;
		while (!agreed) {
			if (p == a.respondToBid(p)) {
				agreed = true;
			} else {
				System.out.println("Consumer Says: How about Point: " + p.toString() + "?");
				p = a.respondToBid(p);
			}
			if (p == b.respondToBid(p)) {
				agreed = true;
			}
			else {
				System.out.println("Producer Says: How about Point: " + p.toString() + "?");
				p = b.respondToBid(p);
			}
		}
		System.out.println("Agreement at: " + p);
	}

}
