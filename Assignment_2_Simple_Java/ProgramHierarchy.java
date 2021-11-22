
/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {

	private static final double RECTANGLE_WIDTH = 150;
	private static final double RECTANGLE_HEIGHT = 50;
	private static final double DISTANCE_IN_WIDTH = 35;
	private static final double DISTANCE_IN_HEIGHT = 45;

	// The job of this code is to draw a hierarchy.

	public void run() {
		drawHierarchy();
	}

	// The hierarchy consists of four rectangles/boxes. The super-category and its
	// three
	// branches: sub-category positioned on the left, sub-category positioned in the
	// middle and sub-category positioned on the right.

	private void drawHierarchy() {
		superCategory();
		subCategoryLeft();
		subCategoryMiddle();
		subCategoryRight();
	}

	// This method draws the super-category.

	private void superCategory() {
		double superX = superCategoryX();
		double superY = superCategoryY();
		GRect superCategory = new GRect(superX, superY, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
		add(superCategory);

		GLabel superText = new GLabel(("Program"));
		textPosition(superX, superY, superText);
		add(superText);
	}

	// This method depicts the X coordinate of the super-category, which will be
	// used further to help calculate the positions of other objects.

	private int superCategoryX() {
		return (int) ((getWidth() - RECTANGLE_WIDTH) / 2);
	}

	// This method depicts the Y coordinate of the super-category, which will be
	// used further to help calculate the positions of other objects.

	private int superCategoryY() {
		return (int) ((getHeight() - (RECTANGLE_HEIGHT + RECTANGLE_HEIGHT + DISTANCE_IN_HEIGHT)) / 2);
	}

	// This method calculates the location (coordinates) of the super-category text.
	// Side-note: Since we want every text to be centred in its box, this location
	// calculation is general and works on other three boxes and their texts.
	// Meaning, this method positions them (texts) in the centre of the boxes which
	// they belong to.

	private void textPosition(double superX, double superY, GLabel superText) {
		superText.setLocation(superX + ((RECTANGLE_WIDTH - superText.getWidth()) / 2),
				superY + ((RECTANGLE_HEIGHT - superText.getAscent()) / 2) + superText.getAscent());
	}

	// This method draws the sub-category positioned on the left.

	private void subCategoryLeft() {
		double subLeftX = subCategoryLeftX();
		double subLeftY = subCategoryLeftY();
		GRect subCategory_1 = new GRect(subLeftX, subLeftY, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
		add(subCategory_1);

		GLabel subText_1 = new GLabel(("GraphicsProgram"));
		textPosition(subLeftX, subLeftY, subText_1);
		add(subText_1);

		GLine firstLink = linkerLocation(subLeftX, subLeftY);
		add(firstLink);
	}

	// This method depicts the X coordinate of the sub-category positioned on the
	// left.

	private int subCategoryLeftX() {
		return (int) ((getWidth() - (RECTANGLE_WIDTH * 3 + DISTANCE_IN_WIDTH * 2)) / 2);
	}

	// This method depicts the Y coordinate of the sub-category positioned on the
	// left. This coordinate will be used further to calculate the coordinates of
	// other objects.

	private int subCategoryLeftY() {
		return (int) (superCategoryY() + RECTANGLE_HEIGHT + DISTANCE_IN_HEIGHT);
	}

	// This method depicts the calculation of the first linker location.
	// This linker connects the super-category to the sub-category positioned on the
	// left. This calculation is a general formula, therefore, it will be used to
	// calculate the middle and right linker positions too.

	private GLine linkerLocation(double subLeftX, double subLeftY) {
		GLine firstLink = new GLine(getWidth() / 2, superCategoryY() + RECTANGLE_HEIGHT, subLeftX + RECTANGLE_WIDTH / 2,
				subLeftY);
		return firstLink;
	}

	// This method draws the sub-category positioned in the middle.
	// Side-note: The reason this method doesn't have its unique x and y coordinate
	// methods is because this box has the same x coordinate as the super-category
	// box and the same y coordinate as the sub-category box to the left (and to the
	// right).

	private void subCategoryMiddle() {
		double subMiddleX = superCategoryX();
		double subMiddleY = subCategoryLeftY();
		GRect subCategory_2 = new GRect(subMiddleX, subMiddleY, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
		add(subCategory_2);

		GLabel subText_2 = new GLabel(("ConsoleProgram"));
		textPosition(subMiddleX, subMiddleY, subText_2);
		add(subText_2);

		GLine middleLink = linkerLocation(subMiddleX, subMiddleY);
		add(middleLink);
	}

	// This method draws the sub-category positioned on the right.
	// Side-note: The reason this method doesn't have its unique y coordinate
	// method is because this box has the same y coordinate as the sub-category box
	// positioned in the middle (and to the left).

	private void subCategoryRight() {
		double subRightX = subCategoryRightX();
		double subRightY = subCategoryLeftY();
		GRect subCategory_3 = new GRect(subRightX, subRightY, RECTANGLE_WIDTH, RECTANGLE_HEIGHT);
		add(subCategory_3);

		GLabel subText_3 = new GLabel(("DialogProgram"));
		textPosition(subRightX, subRightY, subText_3);
		add(subText_3);

		GLine rightLink = linkerLocation(subRightX, subRightY);
		add(rightLink);
	}

	// This method depicts the X coordinate of the sub-category positioned on the
	// right.

	private int subCategoryRightX() {
		return (int) ((getWidth() - RECTANGLE_WIDTH) / 2 + RECTANGLE_WIDTH + DISTANCE_IN_WIDTH);
	}

}
