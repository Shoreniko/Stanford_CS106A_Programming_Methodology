
/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;

	// The job of this code is to draw a pyramid in the GraphicsProgram.

	public void run() {
		drawPyramid();
	}

	private void drawPyramid() {
		double x = getStartingX();
		double y = getStartingY();
		int n = BRICKS_IN_BASE;
		buildingUpBlocks(x, y, n);
	}

	// This is the coordinate x for the first block of the first row.

	private int getStartingX() {
		return (getWidth() - (BRICK_WIDTH * BRICKS_IN_BASE)) / 2;
	}

	// This is the coordinate y for the first block of the first row.

	private int getStartingY() {
		return getHeight() - BRICK_HEIGHT;
	}

	// This method is intended to build up blocks up to the top. The number of
	// blocks in each row decreases by 1 as we move higher. The top consists of only
	// one block.

	private void buildingUpBlocks(double x, double y, int n) {
		for (int j = 0; j < BRICKS_IN_BASE; j++) {
			fillOneRow(x, y, n);
			x = recalculateX(n);
			y -= BRICK_HEIGHT;
			n = n - 1;
		}
	}

	// This method fills the first row with the number of bricks that are to be in
	// the base.

	private void fillOneRow(double x, double y, int n) {
		for (int i = 0; i < n; i++) {
			GRect rect = oneBlock(x, y);
			add(rect);
			x += BRICK_WIDTH;
		}
	}

	// The job of this method is to create one block.

	private GRect oneBlock(double x, double y) {
		GRect oneBlock = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
		return oneBlock;
	}

	// This method helps recalculate the x coordinate for every next new block.

	private double recalculateX(int n) {
		return (getWidth() - BRICK_WIDTH * (n - 1)) / 2;
	}
	
}
