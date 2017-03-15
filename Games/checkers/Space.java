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
