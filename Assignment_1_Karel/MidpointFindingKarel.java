
/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {

	// In this code Karel's job is to put a beeper in the centre of the first
	// street.

	public void run() {
		MidpointFinderKarel();
	}

	// Precondition: Karel is positioned on the first street of the first avenue.
	// Karel is facing East. The world is empty.
	// Postcondition: Karel is standing on a beeper in the centre of the first
	// street.

	private void MidpointFinderKarel() {
		lowDimensionsWorld();
		highDimensionsWorld();
	}

	// In this method Karel's job is to check whether the world's dimensions are 1x1
	// or 2x2.
	// Precondition: Karel is standing on the first street of the first avenue
	// facing east.
	// Postcondition: If the world's dimensions are 1x1 or 2x2 Karel fulfils its
	// job. If the world's dimensions are higher Karel is positioned on the first
	// street of the first avenue facing east.

	private void lowDimensionsWorld() {
		if (frontIsClear()) {
			move();
			if (frontIsBlocked()) {
				putBeeper();
			} else {
				turnAround();
				move();
				turnAround();
			}
		} else {
			putBeeper();
		}
	}

	// Precondition: Karel is positioned on the first street of the first avenue
	// facing east.
	// Postcondition: Karel is standing on a beeper in the centre of the first
	// street.

	private void highDimensionsWorld() {
		if (noBeepersPresent()) {
			firstBeeperPlacement();
			balanceInTheCentre();
			pickAdditionalBeepers();
		}
	}

	// Precondition: Karel is positioned on the first street of the first avenue
	// facing east.
	// Postcondition: Karel is positioned in the end of the first street facing
	// west.

	private void firstBeeperPlacement() {
		putBeeper();
		move();
		while (frontIsClear()) {
			move();
		}
		turnAround();
	}

	// Precondition: Karel is positioned in the end of the first street facing
	// west.
	// Postcondition: Karel is standing on a beeper in the centre of the first
	// street.

	private void balanceInTheCentre() {
		while (noBeepersPresent()) {
			putBeeper();
			move();
			while (noBeepersPresent()) {
				move();
			}
			turnAround();
			move();
		}
	}

	// Precondition: Karel is positioned in the centre of the first street.
	// Postcondition: Karel is standing on a beeper in the centre of the first
	// street.
	
	private void pickAdditionalBeepers() {
		for (int i = 0; i < 2; i++) {
			move();
			pickBeeper();
			while (frontIsClear()) {
				move();
				pickBeeper();
			}
			turnAround();
			while (noBeepersPresent()) {
				move();
			}
		}
	}
}
