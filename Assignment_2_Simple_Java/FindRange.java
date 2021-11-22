
/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {

	// The job of this code is to find the largest and smallest numbers from a list
	// of integers entered by the user. When the user enters 'SENTINEL' value, the
	// listing process stops.

	private static final int SENTINEL = 0;

	public void run() {

		println("This program finds the largest and smallest numbers.");
		int a = readInt("? ");
		theCases(a);
	}

	// There are two main cases in this situation.

	private void theCases(int a) {
		case_1(a);
		case_2(a);
	}

	// Case No.1: When the value of 'a' is the same as the 'SENTINEL' value.
	// Aftermath: The program returns a warning message.

	private void case_1(int a) {
		if (a == SENTINEL) {
			println("Wrong numeric format. Please, try next time.");
		}
	}

	// Case No.2 splits in two sub-cases.

	private void case_2(int a) {

		if (a != SENTINEL) {
			int b = readInt("? ");
			subCase_1(a, b);
			subCase_2(a, b);
		}
	}

	// Sub-case No.1: When the second integer is not equal to the SENTINEL.
	// Aftermath: The program returns the largest and smallest values.

	private void subCase_1(int a, int b) {
		comparationProcess(a, b);
	}

	// In this method we start comparing with the first two integers.
	// Then we generalise this comparing process using 'smallest' and 'largest'.

	private void comparationProcess(int a, int b) {
		int largest;
		int smallest;
		if (b != SENTINEL) {
			if (a > b) {
				largest = a;
				smallest = b;
			} else {
				largest = b;
				smallest = a;
			}
			generalComparison(largest, smallest);
		}
	}

	// This method depicts the process of comparing integers in order to find the
	// smallest and largest ones.
	// Aftermath: The program prints out the smallest and largest numbers.

	private void generalComparison(int largest, int smallest) {
		int n = 0;
		while (true) {
			n = readInt("? ");
			if (n == SENTINEL)
				break;
			if (n > largest) {
				largest = n;
			} else if (n < smallest) {
				smallest = n;
			}
		}
		printAnswer(largest, smallest);
	}

	// This method deals with the process of printing out final answers.

	private void printAnswer(int largest, int smallest) {
		println("largest: " + largest);
		println("smallest: " + smallest);
	}

	// Sub-case No.2: When the second integer is equal to the SENTINEL.
	// Aftermath: The integer 'a' becomes the largest and the smallest value.

	private void subCase_2(int a, int b) {
		if (b == SENTINEL) {
			printAnswer(a, a);
		}
	}

}
