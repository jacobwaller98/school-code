/**
 * Project: APCS
*/
package checkers;

/**
 * @author Jacob Waller
 * @date   Apr 14, 2016
 * @hour   3rd Hour
 * @class  AP Computer Science
 */
public class Move {
	
	public Move(Space before, Space after, boolean isJump) throws NotValidMoveException {
		if(isJump) {
			if(Math.abs(before.getX() - after.getX()) != 2)
				return;
		}
		else {
			if(Math.abs(before.getX() - after.getX()) != 1)
				return;
			Piece p = before.removePiece();
			if(!after.put(p)) 
				throw new NotValidMoveException();
		}
	}

}
