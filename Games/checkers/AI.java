/**
 * Project: APCS
*/
package checkers;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Jacob Waller
 * @date May 6, 2016
 * @hour 3rd Hour
 * @class AP Computer Science
 */
public class AI {

	public void makeMove(Space[][] spaces) {
		ArrayList<Piece> pieces = new ArrayList<Piece>();
		for (int x = 0; x < spaces.length; x++) {
			for (int y = 0; y < spaces[0].length; y++) {
				if (spaces[x][y].getPiece() != null && !spaces[x][y].getPiece().isRed()) {
					pieces.add(spaces[x][y].getPiece());
				}
			}
		}
		int randIndex = 0;
		if (pieces.size() == 0) {
			return;
		}
		getValidPieces(pieces, spaces);
		ArrayList<Space> myAL = new ArrayList<Space>();
		boolean done = false;
		while (!done) {
			randIndex = (int) (Math.random() * pieces.size());
			myAL = getMoveLocations(pieces.get(randIndex), spaces);
			if (myAL.size() != 0) {
				done = true;
			}
		}
		//System.out.println("MOVE LOCATIONS FROM AI" + Arrays.asList(myAL));
		move(pieces.get(randIndex), myAL.get((int) (Math.random() * myAL.size())), spaces);
	}

	public void translateToJumps(Piece p, ArrayList<Space> possibleSpaces) {
		for (int x = 0; x < possibleSpaces.size(); x++) {
			int bx = p.getX();
			int by = p.getY();
			int ax = possibleSpaces.get(x).getX();
			int ay = possibleSpaces.get(x).getY();
			if (!(Math.abs(bx - ax) == 2 && Math.abs(by - ay) == 2)) {
				possibleSpaces.remove(x);
				x--;
			}
		}

	}

	public void getValidPieces(ArrayList<Piece> pieces, Space[][] spaces) {
		for (int x = 0; x < spaces.length; x++) {
			for (int y = 0; y < spaces[0].length; y++) {
				if (spaces[x][y].getPiece() != null && !spaces[x][y].getPiece().isRed()) {
					pieces.add(spaces[x][y].getPiece());
				}
			}
		}
		if (anyCanJump(pieces, spaces)) {
			for (int x = 0; x < pieces.size(); x++) {
				try {
					if (!(Math
							.abs(getMoveLocations(pieces.get(x), spaces).get(0).getX() - pieces.get(x).getX()) == 2)) {
						pieces.remove(x);
						x--;
					}
				} catch (Exception e) {
				}
			}
		}
	}

	public boolean anyCanJump(ArrayList<Piece> pieces, Space[][] spaces) {
		for (int x = 0; x < pieces.size(); x++) {
			try {
				if (Math.abs(getMoveLocations(pieces.get(x), spaces).get(0).getX() - pieces.get(x).getX()) == 2)
					return true;
			} catch (Exception e) {
			}
		}
		return false;
	}

	public ArrayList<Space> getMoveLocations(Piece p, Space[][] spaces) {
		int x = p.getX();
		int y = p.getY();
		ArrayList<Space> ret = new ArrayList<Space>();
		if (p.isKinged()) {
			//System.out.println("Checking for things if kinged");
			if (p.isRed()) {
				try {
					if (spaces[x - 1][y + 1].getPiece() != null && !spaces[x - 1][y + 1].getPiece().isRed()
							&& spaces[x - 2][y + 2].getPiece() == null) {
						ret.add(spaces[x - 2][y + 2]);
					}
				} catch (Exception e) {

				}
				try {
					if (spaces[x - 1][y - 1].getPiece() != null && !spaces[x - 1][y + 1].getPiece().isRed()
							&& spaces[x - 2][y - 2].getPiece() == null) {
						ret.add(spaces[x - 2][y + 2]);
					}
				} catch (Exception e) {

				}
				try {
					if (spaces[x + 1][y + 1].getPiece() != null && !spaces[x - 1][y + 1].getPiece().isRed()
							&& spaces[x + 2][y + 2].getPiece() == null) {
						ret.add(spaces[x + 2][y + 2]);
					}
				} catch (Exception e) {

				}
				try {
					if (spaces[x + 1][y - 1].getPiece() != null && !spaces[x - 1][y + 1].getPiece().isRed()
							&& spaces[x + 2][y - 2].getPiece() == null) {
						ret.add(spaces[x + 2][y - 2]);
					}
				} catch (Exception e) {

				}
			} else {
				try {
					if (spaces[x - 1][y + 1].getPiece() != null && spaces[x - 1][y + 1].getPiece().isRed()
							&& spaces[x - 2][y + 2].getPiece() == null) {
						ret.add(spaces[x - 2][y + 2]);
					}
				} catch (Exception e) {

				}
				try {
					if (spaces[x - 1][y - 1].getPiece() != null && spaces[x - 1][y + 1].getPiece().isRed()
							&& spaces[x - 2][y - 2].getPiece() == null) {
						ret.add(spaces[x - 2][y + 2]);
					}
				} catch (Exception e) {

				}
				try {
					if (spaces[x + 1][y + 1].getPiece() != null && spaces[x - 1][y + 1].getPiece().isRed()
							&& spaces[x + 2][y + 2].getPiece() == null) {
						ret.add(spaces[x + 2][y + 2]);
					}
				} catch (Exception e) {

				}
				try {
					if (spaces[x + 1][y - 1].getPiece() != null && spaces[x - 1][y + 1].getPiece().isRed()
							&& spaces[x + 2][y - 2].getPiece() == null) {
						ret.add(spaces[x + 2][y - 2]);
					}
				} catch (Exception e) {

				}
			}
			if (ret.isEmpty()) {
				try {
					if (spaces[x - 1][y + 1].getPiece() == null) {
						ret.add(spaces[x - 1][y + 1]);
					}
				} catch (Exception e) {

				}
				try {
					if (spaces[x + 1][y + 1].getPiece() == null) {
						ret.add(spaces[x + 1][y + 1]);
					}
				} catch (Exception e) {

				}
				try {
					if (spaces[x - 1][y - 1].getPiece() == null) {
						ret.add(spaces[x - 1][y - 1]);
					}
				} catch (Exception e) {

				}
				try {
					if (spaces[x - 1][y + 1].getPiece() == null) {
						ret.add(spaces[x + 1][y - 1]);
					}
				} catch (Exception e) {

				}
			}
		} else {
			//System.out.println("Checking for things if not kinged");
			if (p.isRed()) {
				//System.out.println("Piece is red");
				try {
					if (spaces[x - 1][y + 1].getPiece() != null && spaces[x - 2][y + 2].getPiece() == null
							&& !spaces[x - 1][y + 1].getPiece().isRed()) {
						//System.out.println("Jump Adding: " + spaces[x - 2][y + 2]);
						ret.add(spaces[x - 2][y + 2]);
					}
				} catch (Exception e) {
					//System.out.println("Whoah I caught myselft ;)");
				}
				try {
					if (spaces[x + 1][y + 1].getPiece() != null && spaces[x + 2][y + 2].getPiece() == null
							&& !spaces[x + 1][y + 1].getPiece().isRed()) {
						//System.out.println("Jump Adding: " + spaces[x + 2][y + 2]);
						ret.add(spaces[x + 2][y + 2]);
					}
				} catch (Exception e) {
					//System.out.println("Whoah I caught myselft ;)");
				}
				if (ret.isEmpty()) {
					try {
						if (spaces[x - 1][y + 1].getPiece() == null) {
							//System.out.println("1Adding: " + spaces[x - 1][y + 1]);
							ret.add(spaces[x - 1][y + 1]);
						}
					} catch (Exception e) {
						//System.out.println("Whoah I caught myselft ;)");
					}
					try {
						if (spaces[x + 1][y + 1].getPiece() == null) {
							//System.out.println("2Adding: " + spaces[x + 1][y + 1]);
							ret.add(spaces[x + 1][y + 1]);
						}
					} catch (Exception e) {
						//System.out.println("Whoah I caught myselft ;)");
					}
				}
			} else {
				//System.out.println("Piece is black");
				try {
					if (spaces[x - 1][y - 1].getPiece() != null && spaces[x - 2][y - 2].getPiece() == null
							&& spaces[x - 1][y - 1].getPiece().isRed()) {
						//System.out.println("Can jump to " + spaces[x - 2][y - 2]);
						ret.add(spaces[x - 2][y - 2]);
					}
				} catch (Exception e) {
					//System.out.println("Whoah I caught myselft 1;)");
				}
				try {
					if (spaces[x + 1][y - 1].getPiece() != null && spaces[x + 2][y - 2].getPiece() == null
							&& spaces[x + 1][y - 1].getPiece().isRed()) {
						//System.out.println("Can jump to " + spaces[x + 2][y - 2]);
						ret.add(spaces[x + 2][y - 2]);
					}
				} catch (Exception e) {
					//System.out.println("Whoah I caught myselft 2;)");
				}
				if (ret.isEmpty()) {
					try {
						if (spaces[x - 1][y - 1].getPiece() == null) {
							//System.out.println("1Can move to " + spaces[x - 1][y - 1]);
							ret.add(spaces[x - 1][y - 1]);
						}
					} catch (Exception e) {
						//System.out.println("Whoah I caught myselft 3;)");
					}
					try {
						if (spaces[x + 1][y - 1].getPiece() == null) {
							//System.out.println("2Can move to " + spaces[x + 1][y - 1]);
							ret.add(spaces[x + 1][y - 1]);
						}
					} catch (Exception e) {
						//System.out.println("Whoah I caught myselft 4;)");
					}
				}
			}
		}
		return ret;

	}

	private int average(int one, int two) {
		return (one + two) / 2;
	}

	private boolean containsSpace(ArrayList<Space> spacesList, Space space) {
		//System.out.println("AFTER IS: " + space);
		for (int x = 0; x < spacesList.size(); x++) {
			//System.out.println(spacesList.get(x));
			if (spacesList.get(x).getX() == space.getX() && spacesList.get(x).getY() == space.getY())
				return true;
		}
		return false;
	}

	public boolean isJump(Piece p, Space after, Space[][] spaces) {
		int bx = p.getX();
		int by = p.getY();
		int ax = after.getX();
		int ay = after.getY();
		return (Math.abs(bx - ax) == 2) && (Math.abs(by - ay) == 2)
				&& (p.isRed() != spaces[average(bx, ax)][average(by, ay)].getPiece().isRed());
	}

	public boolean move(Piece p, Space after, Space[][] spaces) {
		int bx = p.getX();
		int by = p.getY();
		int ax = after.getX();
		int ay = after.getY();
		if (containsSpace(getMoveLocations(p, spaces), after)) {
			if (isJump(p, after, spaces)) {
				Piece temp = spaces[average(bx, ax)][average(by, ay)].removePiece();
				after.put(spaces[bx][by].removePiece());
				after.getPiece().x = ax;
				after.getPiece().y = ay;
				if (after.getPiece().isRed()) {
					if (after.getPiece().getY() == 7)
						after.getPiece().kingMe();
				} else {
					if (after.getPiece().getY() == 0)
						after.getPiece().kingMe();
				}
			} else {
				after.put(spaces[bx][by].removePiece());
				after.getPiece().x = ax;
				after.getPiece().y = ay;

			}
			return true;
		} else {
			return false;
		}
	}

}
