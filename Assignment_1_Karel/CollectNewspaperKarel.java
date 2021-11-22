
/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {

	// In this code Karel's job is to collect the newspaper/beeper located outside
	// of his home.

	public void run() {
		newspaperCollectingKarel();
	}

	// Precondition: Karel is positioned in the top left corner of his home facing
	// east. The newspaper is positioned outside Karel's home.
	// Postcondition: Karel is positioned in the top left corner of his home facing
	// east. Karel has taken the newspaper/beeper.

	private void newspaperCollectingKarel() {
		approachNewspaper();
		takeNewspaper();
		returnToInitialPosition();
	}

	// Precondition: Karel is positioned in the top left corner of his home facing
	// east.
	// Postcondition: Karel is facing east and standing at the door in front of the
	// newspaper.

	private void approachNewspaper() {
		move();
		move();
		turnRight();
		move();
		turnLeft();
	}

	// Precondition: Karel is facing east and standing at the door in front of the
	// newspaper.
	// Postcondition: Karel took the newspaper and now is standing at the door
	// facing west.

	private void takeNewspaper() {
		move();
		pickBeeper();
		turnAround();
		move();
	}

	// Precondition: Karel is standing at the door facing west.
	// Postcondition: Karel is positioned in the top left corner of his home facing
	// east.

	private void returnToInitialPosition() {
		turnRight();
		move();
		turnLeft();
		move();
		move();
		turnAround();
	}

}
