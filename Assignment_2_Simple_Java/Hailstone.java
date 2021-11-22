
/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {

	// This program starts with an integer and decomposes it until getting the value
	// of 1.

	public void run() {
		decomposingInteger();
	}

	// The process of decomposing the integer is depicted below.
	// The program checks whether the value is even or odd.
	// Simultaneously the program calculates the number of steps ('total') it took to
	// decompose the integer.

	private void decomposingInteger() {
		int valueBefore = readInt("Enter a number: ");
		int valueAfter = 0;
		int total = 0;

		while (valueBefore != 1) {
			if (valueBefore % 2 == 0) {
				valueAfter = valueBefore / 2;
				println(valueBefore + " is even so I take half: " + valueAfter);
				valueBefore /= 2;
				total++;
			} else {
				valueAfter = valueBefore * 3 + 1;
				println(valueBefore + " is odd, so I make 3n + 1: " + valueAfter);
				valueBefore = valueBefore * 3 + 1;
				total++;
			}

		}

		println("The process took " + total + " to reach 1.");
	}
}
