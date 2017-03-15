/**
 * Project: APCS
*/
package checkers;

/**
 * @author Jacob Waller
 * @date Apr 11, 2016
 * @hour 3rd Hour
 * @class AP Computer Science
 */
public class Piece {
	public boolean isKinged = false;
	public boolean isRed;
	public int x;
	public int y;

	public Piece(boolean red, int x, int y) {
		this.x = x;
		this.y = y;
		this.isRed = red;
	}

	public boolean kingMe() {
		if (isKinged) {
			return false;
		} else
			isKinged = true;
		return true;
	}

	public boolean move(int dx, int dy) {
		return false;
	}

	public boolean isRed() {
		return isRed;
	}

	public boolean isKinged() {
		return isKinged;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
