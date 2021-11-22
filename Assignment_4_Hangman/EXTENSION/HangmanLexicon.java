
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

	// The job of this code is to read words from specific files. The file it will
	// be reading from depends on the category chosen by the user.

	public HangmanLexicon(String userChoice) {
		choiceAnimals(userChoice);
		choiceBodyParts(userChoice);
		choiceClothes(userChoice);
		choiceAvengers(userChoice);
		choiceFamilyMembers(userChoice);
		choiceFood(userChoice);
		choiceProfessions(userChoice);
		choiceSports(userChoice);
		choiceTransportation(userChoice);
		choiceStudentsThatDeservePlusPlus(userChoice);
	}

	private void choiceAnimals(String userChoice) {
		if (userChoice.equals("ANIMALS")) {
			try {
				BufferedReader rd = new BufferedReader(new FileReader("ANIMALS.txt"));
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
	}

	private void choiceBodyParts(String userChoice) {
		if (userChoice.equals("BODY PARTS")) {
			try {
				BufferedReader rd = new BufferedReader(new FileReader("BODY PARTS.txt"));
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
	}

	private void choiceClothes(String userChoice) {
		if (userChoice.equals("CLOTHES")) {
			try {
				BufferedReader rd = new BufferedReader(new FileReader("CLOTHES.txt"));
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
	}

	private void choiceAvengers(String userChoice) {
		if (userChoice.equals("AVENGERS")) {
			try {
				BufferedReader rd = new BufferedReader(new FileReader("AVENGERS.txt"));
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
	}

	private void choiceFamilyMembers(String userChoice) {
		if (userChoice.equals("FAMILY MEMBERS")) {
			try {
				BufferedReader rd = new BufferedReader(new FileReader("FAMILY MEMBERS.txt"));
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
	}

	private void choiceFood(String userChoice) {
		if (userChoice.equals("FOOD")) {
			try {
				BufferedReader rd = new BufferedReader(new FileReader("FOOD.txt"));
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
	}

	private void choiceProfessions(String userChoice) {
		if (userChoice.equals("PROFESSIONS")) {
			try {
				BufferedReader rd = new BufferedReader(new FileReader("PROFESSIONS.txt"));
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
	}

	private void choiceSports(String userChoice) {
		if (userChoice.equals("SPORTS")) {
			try {
				BufferedReader rd = new BufferedReader(new FileReader("SPORTS.txt"));
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
	}

	private void choiceTransportation(String userChoice) {
		if (userChoice.equals("TRANSPORTATION")) {
			try {
				BufferedReader rd = new BufferedReader(new FileReader("TRANSPORTATION.txt"));
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
	}

	private void choiceStudentsThatDeservePlusPlus(String userChoice) {
		if (userChoice.equals("STUDENTS THAT DESERVE PLUS PLUS")) {
			try {
				BufferedReader rd = new BufferedReader(new FileReader("STUDENTS THAT DESERVE PLUS PLUS.txt"));
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
