
/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {

	// The job of this code is to calculate the hypotenuse of a right triangle based
	// on the values entered by the user.

	public void run() {
		hypotenuseCalculation();
	}

	private void hypotenuseCalculation() {

		println("Enter values to compute Pythagorean theorem.");
		int a = readInt("a: ");
		theOutput(a);
	}

	// This method indicates that the process of calculating the hypotenuse is split
	// into several cases.

	private void theOutput(int a) {
		case_1(a);
		case_2(a);
	}

	// Case No.1: When the user enters a negative value for the integer 'a'.
	// Aftermath: Since the value of a cathetus can not be negative, the program
	// will deliver a warning message at this point.

	private void case_1(int a) {
		if (a < 0) {
			println("The value of a cathetus can not be negative. Please, try next time.");
		}
	}

	// Case No.2 is divided into two separate sub-cases.

	private void case_2(int a) {
		if (a >= 0) {
			int b = readInt("b: ");
			subCase_1(a, b);
			subCase_2(a, b);
		}
	}

	// Sub-case No.1: When the user enters a negative value for the
	// integer 'b'.
	// Aftermath: The program delivers a warning message.

	private void subCase_1(int a, int b) {
		if (a >= 0 && b < 0) {
			println("The value of a cathetus can not be negative. Please, try next time.");
		}
	}

	// Sub-case No.2: When both 'a' and 'b' are non-negative.
	// Aftermath: The program calculates and returns the value of 'c'.

	private void subCase_2(int a, int b) {
		if (a >= 0 && b >= 0) {
			int c = a * a + b * b;
			double value = squareRoot(c);
			println("c = " + value);
		}
	}

	// This method is used to calculate the square root of 'c'.

	private double squareRoot(int c) {
		double value = Math.sqrt(c);
		return value;
	}

}
