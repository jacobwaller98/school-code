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

/**
 * Project: APCS
*/
package checkers;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author Jacob Waller
 * @date Apr 12, 2016
 * @hour 3rd Hour
 * @class AP Computer Science
 */
public class Space {
	Piece myPiece;
	public final int x;
	public final int y;
	public boolean isBlackSpace;
	private BufferedImage blackWithBlack;
	private BufferedImage redEmpty;
	private BufferedImage blackWithRed;
	private BufferedImage blackEmpty;

	public Space(Piece p, int x, int y) {
		try {
			blackWithBlack = ImageIO.read(getClass().getResource("blackSpaceBlack.png"));
			redEmpty = ImageIO.read(getClass().getResource("redSpaceEmpty.png"));
			blackWithRed = ImageIO.read(getClass().getResource("blackSpaceRed.png"));
			blackEmpty = ImageIO.read(getClass().getResource("blackSpaceEmpty.png"));
		} catch (IOException e) {
			System.out.println("Oh darn");
		}
		myPiece = p;
		this.x = x;
		this.y = y;
		if ((x % 2) == (y % 2)) {
			isBlackSpace = true;
		} else
			isBlackSpace = false;

	}

	public boolean put(Piece p) {
		if (myPiece == null) {
			myPiece = p;
			myPiece.x = x;
			myPiece.y = y;
		} else
			return false;
		return true;
	}

	public boolean hasPiece() {
		return !(myPiece == null);
	}

	public Piece removePiece() {
		Piece temp = myPiece;
		myPiece = null;
		return temp;
	}

	public Piece getPiece() {
		return myPiece;
	}

	public String toString() {
		if (myPiece == null)
			return "Unoccupied at " + x + ", " + y;
		else if (myPiece.isRed())
			return "Red piece on at " + x + ", " + y;
		else
			return "Black piece on at " + x + ", " + y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public BufferedImage getImage() {
		if (isBlackSpace) {
			if (myPiece == null) {
				return blackEmpty;
			} else {
				if (myPiece.isRed()) {
					return blackWithRed;
				} else {
					return blackWithBlack;
				}
			}
		} else {
			return redEmpty;
		}
	}
}

/**
 * Project: APCS
*/
package checkers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * @author Jacob Waller
 * @date Apr 11, 2016
 * @hour 3rd Hour
 * @class AP Computer Science
 */
public class Board extends JPanel {

	private static final long serialVersionUID = 1L;
	static JFrame frame;
	private static final int SPACE_SIZE = 50;

	int rows = 8;
	int columns = 8;
	int clicksLeft = 2;
	int startX = -1;
	int startY = -1;
	int endX = -1;
	int endY = -1;
	String whoLoses = "";

	Space[][] spaces = new Space[rows][columns];

	Piece[] reds = new Piece[12];
	Piece[] blacks = new Piece[12];
	ArrayList<Piece> deadReds = new ArrayList<Piece>();
	ArrayList<Piece> deadBlacks = new ArrayList<Piece>();
	boolean won = false;

	AI myAI = new AI();

	public static void main(String arg[]) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame = new JFrame("Checkers");
				frame.setContentPane(new Board());
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack(); // "this" JFrame packs its components
				frame.setSize(new Dimension(SPACE_SIZE * 8, SPACE_SIZE * 8 + (SPACE_SIZE / 2)));
				frame.setLocationRelativeTo(null); // center the application
				frame.setResizable(false);
				frame.setVisible(true); // show it

			}

		});
	}

	@Override
	public void paintComponent(Graphics g) {
		if (!won) {
			for (int x = 0; x < spaces.length; x++) {
				for (int y = 0; y < spaces[0].length; y++) {
					g.drawImage(spaces[x][y].getImage(), x * SPACE_SIZE, y * SPACE_SIZE, SPACE_SIZE, SPACE_SIZE, null);
				}
			}
		}
		else {
			g.setFont(new Font("TimesRoman", Font.BOLD, 50));
			setBackground(Color.RED);
			super.paintComponent(g);
			g.drawString(whoLoses + " loses!",50,50);
		}
	}

	public Board() {
		addMouseListener(listener);
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				spaces[x][y] = new Space(null, x, y);
			}
		}
		reds[0] = new Piece(true, 0, 0);
		reds[1] = new Piece(true, 2, 0);
		reds[2] = new Piece(true, 4, 0);
		reds[3] = new Piece(true, 6, 0);
		reds[4] = new Piece(true, 1, 1);
		reds[5] = new Piece(true, 3, 1);
		reds[6] = new Piece(true, 5, 1);
		reds[7] = new Piece(true, 7, 1);
		reds[8] = new Piece(true, 0, 2);
		reds[9] = new Piece(true, 2, 2);
		reds[10] = new Piece(true, 4, 2);
		reds[11] = new Piece(true, 6, 2);

		blacks[0] = new Piece(false, 1, 7);
		blacks[1] = new Piece(false, 3, 7);
		blacks[2] = new Piece(false, 5, 7);
		blacks[3] = new Piece(false, 7, 7);
		blacks[4] = new Piece(false, 0, 6);
		blacks[5] = new Piece(false, 2, 6);
		blacks[6] = new Piece(false, 4, 6);
		blacks[7] = new Piece(false, 6, 6);
		blacks[8] = new Piece(false, 1, 5);
		blacks[9] = new Piece(false, 3, 5);
		blacks[10] = new Piece(false, 5, 5);
		blacks[11] = new Piece(false, 7, 5);
		for (int i = 0; i < reds.length; i++) {
			int x = reds[i].getX();
			int y = reds[i].getY();
			spaces[x][y] = new Space(reds[i], x, y);
		}
		for (int i = 0; i < blacks.length; i++) {
			int x = blacks[i].getX();
			int y = blacks[i].getY();
			spaces[x][y] = new Space(blacks[i], x, y);
		}
	}

	public int getNumRows() {
		return rows;
	}

	public int getNumCols() {
		return columns;
	}

	public boolean isLegalMove(Piece p, Space after) {
		return getMoveLocations(p).contains(after);

	}

	public boolean move(Piece p, Space after) {
		int bx = p.getX();
		int by = p.getY();
		int ax = after.getX();
		int ay = after.getY();
		if (containsSpace(getMoveLocations(p), after)) {
			if (isJump(p, after)) {
				Piece temp = spaces[average(bx, ax)][average(by, ay)].removePiece();
				if (temp.isRed()) {
					deadReds.add(temp);
					if (deadReds.size() >= 12) {
						won = true;
						whoLoses = "Red";
						Graphics g = getGraphics();
						paintComponent(g);
						System.out.println("painting");
					}
				} else {
					deadBlacks.add(temp);
					if (deadBlacks.size() >= 12) {
						won = true;
						whoLoses = "Black";
						Graphics g = getGraphics();
						paintComponent(g);
						System.out.println("painting");
					}
				}
				after.put(spaces[bx][by].removePiece());
				after.getPiece().x = ax;
				after.getPiece().y = ay;
				if (getMoveLocations(p).size() != 0 && Math.abs(getMoveLocations(p).get(0).getX() - p.getX()) == 2) {
					move(p, getMoveLocations(p).get(0));
				}
			} else {
				after.put(spaces[bx][by].removePiece());
				after.getPiece().x = ax;
				after.getPiece().y = ay;

			}
			if (after.getPiece() != null && after.getPiece().isRed()) {
				// System.out.println("Checking if I should king " +
				// after.getPiece().getY());
				if (after.getPiece().getY() == 7) {
					after.getPiece().kingMe();
					// System.out.println("KINGING " + after);
				}
			} else {
				if (after.getPiece() != null && after.getPiece().getY() == 0)
					after.getPiece().kingMe();
			}

			return true;
		} else {
			return false;
		}
	}

	private boolean containsSpace(ArrayList<Space> spacesList, Space space) {
		// System.out.println("AFTER IS: " + space);
		for (int x = 0; x < spacesList.size(); x++) {
			// System.out.println(spacesList.get(x));
			if (spacesList.get(x).getX() == space.getX() && spacesList.get(x).getY() == space.getY())
				return true;
		}
		return false;
	}

	public boolean isJump(Piece p, Space after) {
		int bx = p.getX();
		int by = p.getY();
		int ax = after.getX();
		int ay = after.getY();
		return (Math.abs(bx - ax) == 2) && (Math.abs(by - ay) == 2)
				&& (p.isRed() != spaces[average(bx, ax)][average(by, ay)].getPiece().isRed());
	}

	private int average(int one, int two) {
		return (one + two) / 2;
	}

	public void removePiece(Space s) {
		s.removePiece();
	}

	public Object getPiece(Space s) {
		return s.getPiece();
	}

	public ArrayList<Space> getMoveLocations(Piece p) {
		int x = p.getX();
		int y = p.getY();
		ArrayList<Space> ret = new ArrayList<Space>();
		if (p.isKinged()) {
			// System.out.println("Checking for things if kinged");
			if (p.isRed()) {
				try {
					if (spaces[x - 1][y + 1].getPiece() != null && !spaces[x - 1][y + 1].getPiece().isRed()
							&& spaces[x - 2][y + 2].getPiece() == null) {
						ret.add(spaces[x - 2][y + 2]);
					}
				} catch (Exception e) {

				}
				try {
					if (spaces[x - 1][y - 1].getPiece() != null && !spaces[x - 1][y - 1].getPiece().isRed()
							&& spaces[x - 2][y - 2].getPiece() == null) {
						ret.add(spaces[x - 2][y - 2]);
					}
				} catch (Exception e) {

				}
				try {
					if (spaces[x + 1][y + 1].getPiece() != null && !spaces[x + 1][y + 1].getPiece().isRed()
							&& spaces[x + 2][y + 2].getPiece() == null) {
						ret.add(spaces[x + 2][y + 2]);
					}
				} catch (Exception e) {

				}
				try {
					if (spaces[x + 1][y - 1].getPiece() != null && !spaces[x + 1][y - 1].getPiece().isRed()
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
					if (spaces[x + 1][y - 1].getPiece() == null) {
						ret.add(spaces[x + 1][y - 1]);
					}
				} catch (Exception e) {

				}
			}
		} else {
			// System.out.println("Checking for things if not kinged");
			if (p.isRed()) {
				// System.out.println("Piece is red");
				try {
					if (spaces[x - 1][y + 1].getPiece() != null && spaces[x - 2][y + 2].getPiece() == null
							&& !spaces[x - 1][y + 1].getPiece().isRed()) {
						// System.out.println("Jump Adding: " + spaces[x - 2][y
						// + 2]);
						ret.add(spaces[x - 2][y + 2]);
					}
				} catch (Exception e) {
					// System.out.println("Whoah I caught myselft ;)");
				}
				try {
					if (spaces[x + 1][y + 1].getPiece() != null && spaces[x + 2][y + 2].getPiece() == null
							&& !spaces[x + 1][y + 1].getPiece().isRed()) {
						// System.out.println("Jump Adding: " + spaces[x + 2][y
						// + 2]);
						ret.add(spaces[x + 2][y + 2]);
					}
				} catch (Exception e) {
					// System.out.println("Whoah I caught myselft ;)");
				}
				if (ret.isEmpty()) {
					try {
						if (spaces[x - 1][y + 1].getPiece() == null) {
							// System.out.println("1Adding: " + spaces[x - 1][y
							// + 1]);
							ret.add(spaces[x - 1][y + 1]);
						}
					} catch (Exception e) {
						// System.out.println("Whoah I caught myselft ;)");
					}
					try {
						if (spaces[x + 1][y + 1].getPiece() == null) {
							// System.out.println("2Adding: " + spaces[x + 1][y
							// + 1]);
							ret.add(spaces[x + 1][y + 1]);
						}
					} catch (Exception e) {
						// System.out.println("Whoah I caught myselft ;)");
					}
				}
			} else {
				// System.out.println("Piece is black");
				try {
					if (spaces[x - 1][y - 1].getPiece() != null && spaces[x - 2][y - 2].getPiece() == null
							&& spaces[x - 1][y - 1].getPiece().isRed()) {
						// System.out.println("Can jump to " + spaces[x - 2][y -
						// 2]);
						ret.add(spaces[x - 2][y - 2]);
					}
				} catch (Exception e) {
					// System.out.println("Whoah I caught myselft 1;)");
				}
				try {
					if (spaces[x + 1][y - 1].getPiece() != null && spaces[x + 2][y - 2].getPiece() == null
							&& spaces[x + 1][y - 1].getPiece().isRed()) {
						// System.out.println("Can jump to " + spaces[x + 2][y -
						// 2]);
						ret.add(spaces[x + 2][y - 2]);
					}
				} catch (Exception e) {
					// System.out.println("Whoah I caught myselft 2;)");
				}
				if (ret.isEmpty()) {
					try {
						if (spaces[x - 1][y - 1].getPiece() == null) {
							// System.out.println("1Can move to " + spaces[x -
							// 1][y - 1]);
							ret.add(spaces[x - 1][y - 1]);
						}
					} catch (Exception e) {
						// System.out.println("Whoah I caught myselft 3;)");
					}
					try {
						if (spaces[x + 1][y - 1].getPiece() == null) {
							// System.out.println("2Can move to " + spaces[x +
							// 1][y - 1]);
							ret.add(spaces[x + 1][y - 1]);
						}
					} catch (Exception e) {
						// System.out.println("Whoah I caught myselft 4;)");
					}
				}
			}
		}
		return ret;

	}

	public Space getSpace(int x, int y) {
		return spaces[x][y];
	}

	private MouseListener listener = new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			int x = e.getX();
			int y = e.getY();
			// System.out.println(x / SPACE_SIZE + ", " + y / SPACE_SIZE);
			if (clicksLeft == 2) {
				// System.out.println("FIRST SELECTED is " + x / SPACE_SIZE + ",
				// " + y / SPACE_SIZE);
				startX = x / SPACE_SIZE;
				startY = y / SPACE_SIZE;
				if (spaces[startX][startY].getPiece() != null && spaces[startX][startY].getPiece().isRed())
					clicksLeft--;
				else
					return;
			} else if (clicksLeft == 1) {
				// System.out.println("SECOND SELECTEDis " + x / SPACE_SIZE + ",
				// " + y / SPACE_SIZE);
				endX = x / SPACE_SIZE;
				endY = y / SPACE_SIZE;
				// System.out.println(spaces[startX][startY]);
				if (spaces[endX][endY].getPiece() == null) {
					clicksLeft = 2;
					boolean moved = move(spaces[startX][startY].getPiece(), spaces[endX][endY]);
					Graphics g;
					g = getGraphics();
					paint(g);
					if (moved) {
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						myAI.makeMove(spaces);
					}
					g = getGraphics();
					paint(g);
				} else
					clicksLeft = 2;

			}

		}
	};
}

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
