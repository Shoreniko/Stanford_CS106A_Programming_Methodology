
/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {

	// In this code Karel's job is to make the world look like a checker board by
	// placing beepers on specific corners.

	public void run() {
		makeCheckerBoard();
	}

	// Precondition: Karel is positioned on the first street of the first avenue
	// facing east. The world is empty.
	// Postcondition: Karel is standing on the last avenue. The world resembles a
	// checker board.

	private void makeCheckerBoard() {
		oneStreetWorld();
		multipleStreetsWorld();
	}

	// This method takes into account the individual case when the world consists of
	// only one street.
	// Precondition: Karel is positioned on the first street of the first avenue
	// facing east. The world is empty.
	// Postcondition: Karel is positioned on the last avenue of the first street
	// facing north. The world resembles a checker board.

	private void oneStreetWorld() {
		if (frontIsBlocked()) {
			turnLeft();
			putBeeper();
			putBeepersWithIntervals();
		}
	}

	// This method lets Karel put beepers with the interval of one corner.
	// Precondition: Karel is standing on a beeper in the beginning of a line. There
	// are no other beepers in the line that he is facing.
	// Postcondition: Karel is standing on the opposite end. The line is now filled
	// with beepers. The interval between these beepers is one corner.

	private void putBeepersWithIntervals() {
		while (frontIsClear()) {
			move();
			if (frontIsClear()) {
				move();
				putBeeper();
			}
		}
	}

	// This method takes into account the case when the world consists of two or
	// more streets.
	// Precondition: Karel is positioned on the first street of the first corner
	// facing east. The world is empty.
	// Postcondition #1: If the world has an odd number of avenues Karel is
	// positioned on the last street of the last avenue facing east. The world
	// resembles a checker board.
	// Postcondition #2: If the world has an even number of avenues Karel is
	// positioned on the first street of the last avenue facing north. The world
	// resembles a checker board.

	private void multipleStreetsWorld() {
		while (frontIsClear()) {
			fillOddStreetsWithOddAvenues();
			fillEvenStreetsWithEvenAvenues();
		}
	}

	// This method lets Karel put beepers on corners with simultaneously odd streets
	// and avenues.
	// Precondition: Karel is positioned on the first street facing east. The line
	// in front of him has no beepers.
	// Postcondition: Karel is positioned on the last street of the line. The line
	// has now been filled with equally spaced beepers.

	private void fillOddStreetsWithOddAvenues() {
		putBeeper();
		putBeepersWithIntervals();
		turnLeft();
		if (frontIsClear()) {
			if (beepersPresent()) {
				move();
				turnLeft();
				move();
			} else {
				move();
				turnLeft();
			}
		}
	}

	// This method lets Karel put beepers on corners with simultaneously even
	// streets and avenues.
	// Precondition: Karel is positioned on the last street facing west. The line
	// in front of him has no beepers.
	// Postcondition: Karel is positioned on the first street of the line. The line
	// has now been filled with equally spaced beepers.

	private void fillEvenStreetsWithEvenAvenues() {
		if (frontIsClear()) {
			putBeeper();
		}
		putBeepersWithIntervals();
		turnRight();
		if (frontIsClear()) {
			move();
			turnRight();
		}
	}

}
