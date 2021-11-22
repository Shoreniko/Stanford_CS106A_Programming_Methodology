
/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import acm.util.*;
import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	/* Constructor: NameSurferEntry(line) */
	/**
	 * Creates a new NameSurferEntry from a data line as it appears in the data
	 * file. Each line begins with the name, which is followed by integers giving
	 * the rank of that name for each decade.
	 */

	// The job of this method is to create an object froma single piece of line. The
	// object is characterized by its name and ranking position of a specific
	// decade.

	public NameSurferEntry(String line) {
		int charPosition = extractCharFromText(line);
		String[] rankingInStrings = new String[NDECADES];
		initialisingRankingInStrings(rankingInStrings);
		fillingRankingInStrings(line, charPosition, rankingInStrings);
		convertingStringsToIntegers(rankingInStrings);
	}

	// By separating the name this method detects the character position from which
	// the ranking starts in the line string.

	private int extractCharFromText(String line) {
		int charPosition = 0;
		while (line.charAt(charPosition) != ' ') {
			theName += line.charAt(charPosition);
			charPosition++;
		}
		return charPosition;
	}

	// The array is initialised itself, however, we need zero space in each array
	// element. Therefore, the job of this method is to initialise the array in
	// different fashion.

	private void initialisingRankingInStrings(String[] rankingInStrings) {
		for (int i = 0; i < 11; i++) {
			rankingInStrings[i] = "";
		}
	}

	// This method puts ranking values as strings in the array.

	private void fillingRankingInStrings(String line, int charPosition, String[] rankingInStrings) {
		int j = 0;
		for (int i = charPosition + 1; i < line.length(); i++) {
			if (line.charAt(i) != ' ') {
				rankingInStrings[j] += line.charAt(i);
			} else {
				j++;
			}
		}
	}

	// This method creates a new array of integers and transforms the rankings from
	// strings to integers.

	private void convertingStringsToIntegers(String[] rankingInStrings) {
		rankingInIntegers = new int[NDECADES];
		for (int i = 0; i < NDECADES; i++) {
			rankingInIntegers[i] = Integer.parseInt(rankingInStrings[i]);
		}
	}

	/**
	 * Returns the name associated with this entry.
	 */
	
	// This method returns the name of the current entry object.

	public String getName() {
		return theName;
	}

	/**
	 * Returns the rank associated with an entry for a particular
	 * decade.  The decade value is an integer indicating how many
	 * decades have passed since the first year in the database,
	 * which is given by the constant START_DECADE.  If a name does
	 * not appear in a decade, the rank value is 0.
	 */
	
	// This method returns a specific name ranking at a specific decade.

	public int getRank(int decade) {
		return rankingInIntegers[(decade - START_DECADE) / 10];
	}

	/**
	 * Returns a string that makes it easy to see the value of a
	 * NameSurferEntry.
	 */
	
	// Even though this method is not used, it is practical for checking whether the
	// program works correctly, because its job is to print out the proper name and
	// its ranking data over the decades.

	public String toString() {
		return theName + " [" + rankingInIntegers[0] + " " + rankingInIntegers[1] + " " + rankingInIntegers[2] + " "
				+ rankingInIntegers[3] + " " + rankingInIntegers[4] + " " + rankingInIntegers[5] + " "
				+ rankingInIntegers[6] + " " + rankingInIntegers[7] + " " + rankingInIntegers[8] + " "
				+ rankingInIntegers[9] + " " + rankingInIntegers[10] + "]";
	}

	// Private instance variables.

	private String theName = "";
	private int[] rankingInIntegers;

}
