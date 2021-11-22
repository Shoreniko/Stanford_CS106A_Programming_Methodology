
/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.util.*;

public class HangmanLexicon {

	private ArrayList<String> wordList = new ArrayList<String>();
	private int wordsCount = wordList.size();

	// The job of this code is to read words from indicated file.

	public HangmanLexicon() {
		try {
			BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			String word = rd.readLine();
			while (word != null) {
				word = rd.readLine();
				wordList.add(word);
				wordsCount = wordList.size();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/** Returns the number of words in the lexicon. */
	public int getWordCount() {
		int numerosity = wordsCount;
		return numerosity;
	}

	/** Returns the word at the specified index. */
	public String getWord(int index) {
		String chosenOne = (String) wordList.get(index);
		return chosenOne;
	};
}
