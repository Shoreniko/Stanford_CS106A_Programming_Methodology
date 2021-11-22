
/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	// In this code Karel's job is to fix towers that are missing bricks/beepers.

	public void run() {
		fillTowersWithBricks();
	}

	// Precondition: Karel is standing on the first street of the first avenue
	// facing east. There are towers lacking bricks.
	// Postcondition: Karel is standing on the last street of the first avenue
	// facing east. All the towers have been filled with bricks.

	private void fillTowersWithBricks() {
		fixOneTower();
		while (frontIsClear()) {
			approachNextTower();
			fixOneTower();
		}
	}

	// Precondition: Karel is standing at the bottom of the Tower facing East. The
	// tower is missing some bricks.
	// Postcondition: Karel is standing at the bottom of the Tower facing East. The
	// tower is filled with bricks.

	private void fixOneTower() {
		ascendTower();
		descendTower();
	}

	// Precondition: Karel is standing at the bottom of the Tower facing east.
	// Postcondition: Karel is positioned on the top of the Tower facing east.

	private void ascendTower() {
		turnLeft();
		if (noBeepersPresent()) {
			putBeeper();
		}
		while (frontIsClear()) {
			move();
			if (noBeepersPresent()) {
				putBeeper();
			}
		}
		turnRight();
	}

	// Precondition: Karel is positioned on the top of the Tower facing east.
	// Postcondition: Karel is standing at the bottom of the Tower facing east.

	private void descendTower() {
		turnRight();
		while (frontIsClear()) {
			move();
		}
		turnLeft();
	}

	// Precondition: Karel is standing at the bottom of the Tower facing east.
	// Postcondition: Karel is standing at the bottom of the next Tower facing east.

	private void approachNextTower() {
		for (int i = 0; i < 4; i++) {
			move();
		}
	}

}
