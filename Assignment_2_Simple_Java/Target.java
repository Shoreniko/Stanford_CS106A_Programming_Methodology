
/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {

	private static final double OUTER_CIRCLE_RADIUS = 72;
	private static final double MIDDLE_CIRCLE_RADIUS = 46.8;
	private static final double INNER_CIRCLE_RADIUS = 21.6;

	// The job of this code is to draw a target in the GraphicsProgram.

	public void run() {
		drawTarget();
	}

	private void drawTarget() {
		outerCircle();
		middleCircle();
		innerCircle();
	}

	// This method is intended to draw the outer circle.
	
	private void outerCircle() {
		double outerDiameter = (int) (OUTER_CIRCLE_RADIUS * 2);
		double outerX = (int) (getWidth() / 2 - OUTER_CIRCLE_RADIUS);
		double outerY = (int) (getHeight() / 2 - OUTER_CIRCLE_RADIUS);
		GOval outerCircle = new GOval(outerX, outerY, outerDiameter, outerDiameter);

		add(outerCircle);
		outerCircle.setColor(Color.RED);
		outerCircle.setFilled(true);
	}

	// This method is intended to draw the middle circle.
	
	private void middleCircle() {
		double middleDiameter = (int) (MIDDLE_CIRCLE_RADIUS * 2);
		double middleX = (int) (getWidth() / 2 - MIDDLE_CIRCLE_RADIUS);
		double middleY = (int) (getHeight() / 2 - MIDDLE_CIRCLE_RADIUS);
		GOval middleCircle = new GOval(middleX, middleY, middleDiameter, middleDiameter);

		add(middleCircle);
		middleCircle.setColor(Color.WHITE);
		middleCircle.setFilled(true);
	}

	// This method is intended to draw the inner circle.
	
	private void innerCircle() {
		double innerDiameter = (int) (INNER_CIRCLE_RADIUS * 2);
		double innerX = (int) (getWidth() / 2 - INNER_CIRCLE_RADIUS);
		double innerY = (int) (getHeight() / 2 - INNER_CIRCLE_RADIUS);
		GOval innerCircle = new GOval(innerX, innerY, innerDiameter, innerDiameter);

		add(innerCircle);
		innerCircle.setColor(Color.RED);
		innerCircle.setFilled(true);
	}
	
}
