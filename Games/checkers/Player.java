/**
 * Project: APCS
*/
package checkers;

/**
 * @author Jacob Waller
 * @date Apr 14, 2016
 * @hour 3rd Hour
 * @class AP Computer Science
 */
public class Player {

	boolean isComputerControlled;

	public Player() {
		isComputerControlled = false;
	}

	public Player(int difficulty) {
		isComputerControlled = true;
	}

	public Move takeTurn() {
		if (isComputerControlled) {
			return makeBestMove();
		} else {

		}
		return null;
	}

	public Move makeBestMove() {
		return null;
	}

}
