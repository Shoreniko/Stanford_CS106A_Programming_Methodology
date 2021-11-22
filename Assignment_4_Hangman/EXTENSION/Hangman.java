
/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.applet.*;
import java.awt.*;

public class Hangman extends ConsoleProgram {

	RandomGenerator rgen = new RandomGenerator().getInstance();
	private int numberOfGuesses = 8;
	private String userChoice = "";
	private String encodedWord = "";
	private String guessFromUser = "";
	private String wrongGuessString = "";
	private String correctGuesses = "";
	private boolean correctnessController = false;
	private boolean sameGuessOrNot = false;
	private HangmanCanvas canvas;

	// The job of the initialisation phase is to create a new canvas for the
	// graphical part of the game.

	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}

	// The run method consists of a single method call to the game of 'Drownman'.

	public void run() {
		playingTheDrownman();
	}

	// This is the full game, which consists of parts:
	// - The introduction.
	// - The gaming process.
	// - The question of playing again or not.

	private void playingTheDrownman() {
		canvas.categoriesImage();
	
		userChoice = readLine("Your choice: ");
		userChoice = userChoice.toUpperCase();
		
		println("Welcome to Drownman!");
		while (true) {
			canvas.reset(userChoice);
			int canvasHeight = canvas.getHeight();
			theGame(canvasHeight);
			println("Do you want to play again? (If not, just type in: no)");
			String playAgainQuestion = readLine("Your answer: ");
			if (playAgainQuestion.equals("no")) {
				break;
			}
			encodedWord = "";
			wrongGuessString = "";
			correctGuesses = "";
			numberOfGuesses = 8;
		}
	}

	// This method depicts the most part of the gaming process itself.

	private void theGame(int canvasHeight) {
		String chosenWord = choosingTheWord();
		dashesForEncodedWord(chosenWord);
		initialMessage();
		theGuessingProcess(chosenWord, canvasHeight);
		winnerMessage(chosenWord);
		loserMessage(chosenWord);
	}

	// This method depicts the process of choosing a random word from the list of
	// words provided by the HangmanLexicon class.

	private String choosingTheWord() {
		
		HangmanLexicon wordsList = new HangmanLexicon(userChoice);
		String chosenWord = wordsList.getWord(rgen.nextInt(0, wordsList.getWordCount() - 1));
		return chosenWord;
	}

	// The job of this method is to create a dashed/encoded version of the chosen
	// word (the chosen word - the word that has to be guessed).

	private void dashesForEncodedWord(String chosenWord) {
		for (int i = 0; i < chosenWord.length(); i++) {
			encodedWord += "-";
		}
	}

	// This method displays the initial message for the user.

	private void initialMessage() {
		println("The word now looks like this: " + encodedWord);
		println("You have " + numberOfGuesses + " guesses left.");
		canvas.displayWord(encodedWord);
		guessFromUser = readLine("Your guess: ");
	}

	// This is the main part of the game: the actual guessing process, which lasts
	// until either the number of guesses becomes zero, or the user guesses the
	// word.

	private void theGuessingProcess(String chosenWord, int canvasHeight) {
		while (numberOfGuesses != 0 && !(encodedWord.equals(chosenWord))) {
			char guessOfChar = Character.MIN_VALUE;
			userClickedEnter();
			guessOfChar = userEnteredACharacter(guessOfChar);
			guessOfChar = userEnteredALetter(chosenWord, guessOfChar, canvasHeight);
			userEnteredInvalidCharacter(guessOfChar);
			if (numberOfGuesses != 0 && !(encodedWord.equals(chosenWord))) {
				guessFromUser = readLine("Your guess: ");
			}
		}
	}

	// This method deals with the situation when the user clicks 'Enter' on keyboard
	// instead of guessing a letter. In this case, the user gets a warning message,
	// however, the number of permitted guesses stays the same.

	private void userClickedEnter() {
		if (guessFromUser.length() == 0) {
			if (numberOfGuesses != 0) {
				println("You omitted a guess. Please, try again.");
				println("You have " + numberOfGuesses + " guesses left.");
			}
		}
	}

	// This method deals with the situation when the user entered some character
	// (doesn't matter if it's a letter or not - yet). This code creates a new
	// character (in the name of 'guessOfChar') and assigns to it the first
	// character of user's guess.

	private char userEnteredACharacter(char guessOfChar) {
		if (guessFromUser.length() != 0) {
			guessOfChar = guessFromUser.charAt(0);
		}
		return guessOfChar;
	}

	// This method deals with the situation when the user entered a letter.

	private char userEnteredALetter(String chosenWord, char guessOfChar, int canvasHeight) {
		if ((guessOfChar >= 'a' && guessOfChar <= 'z') || (guessOfChar >= 'A' && guessOfChar <= 'Z')) {
			guessOfChar = Character.toUpperCase(guessOfChar);
			correctGuessOrNot(chosenWord, guessOfChar);
			repeatedCorrectGuessOrNot(guessOfChar);
			differentGuess(guessOfChar, canvasHeight);
			correctnessController = false;
			encodedWordRenovation(chosenWord, guessOfChar);
			currentInformationForUser(chosenWord);
			sameGuessOrNot = false;
		}
		return guessOfChar;
	}

	// The detection of the guess being correct or not is being controlled by a
	// boolean called 'correctnessController'. Its initial value is 'false'. The job
	// of this method is to check whether the encoded word contains the guessed
	// character or not. If it is contained, the value of the boolean is changed to
	// 'true' at once.

	private void correctGuessOrNot(String chosenWord, char guessOfChar) {
		for (int i = 0; i < chosenWord.length(); i++) {
			if (guessOfChar == chosenWord.charAt(i)) {
				correctnessController = true;
			}
		}
	}

	// This method checks whether the user repeated a correct guess or not.

	private void repeatedCorrectGuessOrNot(char guessOfChar) {
		for (int i = 0; i < correctGuesses.length(); i++) {
			if (guessOfChar == Character.toUpperCase(correctGuesses.charAt(i))) {
				sameGuessOrNot = true;
			}
		}
	}

	// This method checks whether the user made the same guess or not.

	private void differentGuess(char guessOfChar, int canvasHeight) {
		if (!sameGuessOrNot) {
			guessMessage(guessOfChar, canvasHeight);
		}
	}

	// This method tries to detect whether the user's guess was correct or not.

	private void guessMessage(char guessOfChar, int canvasHeight) {
		if (correctnessController) {
			correctGuess();
		} else {
			incorrectGuess(guessOfChar, canvasHeight);
		}
	}

	// This method does couple of things:
	// - Informs the user that their guess was correct.
	// - Sets the boolean value to 'false', meaning 'prepares' it for use for the
	// next guess.

	private void correctGuess() {
		println("That guess is correct.");
		AudioClip correct = getAudioClip(getCodeBase(), "CORRECT.au");
		correct.play();
		correctGuesses += guessFromUser;
		correctnessController = false;
	}

	// This method does couple of things:
	// - Informs the user that their guess was incorrect.
	// - Checks whether the user made same wrong guess again. This information is
	// controlled by a boolean called 'sameIncorrectGuessOrNot', which tells
	// whether the wrong guess should be displayed on the canvas or not.

	private void incorrectGuess(char guessOfChar, int canvasHeight) {
		correctnessController = false;
		AudioClip incorrect = getAudioClip(getCodeBase(), "INCORRECT.au");
		incorrect.play();
		if (guessFromUser.length() != 0) {
			println("There are no " + guessOfChar + "'s in the word.");
		}
		numberOfGuesses--;
		String ifRepeatedMistake = String.valueOf(guessOfChar);
		boolean sameIncorrectGuessOrNot = !(wrongGuessString.contains(ifRepeatedMistake));
		canvas.noteIncorrectGuess(guessOfChar, sameIncorrectGuessOrNot, canvasHeight);
		wrongGuessString += guessOfChar;
	}

	// This code updates the dashed word. If the user's guess was
	// correct, the dashed/encoded word will be updated. Meaning, correctly guessed
	// letters will appear in their specific positions they had in the original
	// word.

	private void encodedWordRenovation(String chosenWord, char guessOfChar) {
		for (int j = 0; j < chosenWord.length(); j++) {
			if (guessOfChar == chosenWord.charAt(j)) {
				encodedWord = encodedWord.substring(0, j) + chosenWord.charAt(j) + encodedWord.substring(j + 1);
			}
		}
		canvas.displayWord(encodedWord);
	}

	// This method informs the user about:
	// - The current progress in decoding the word.
	// - The number of guesses left.

	private void currentInformationForUser(String chosenWord) {
		if (numberOfGuesses != 0 && !(encodedWord.equals(chosenWord))) {
			println("The word now looks like this: " + encodedWord);
			println("You have " + numberOfGuesses + " guesses left.");
		}
	}

	// This method deals with the situation when the user entered a character that
	// is neither a letter nor an 'enter' (That rhymed by the way :)). In this case
	// the program informs the user properly.

	private void userEnteredInvalidCharacter(char guessOfChar) {
		if (guessFromUser.length() != 0 && !(guessOfChar >= 'a' && guessOfChar <= 'z')
				&& !(guessOfChar >= 'A' && guessOfChar <= 'Z')) {
			println("You have entered an illegal character. Please, try again.");
		}
	}

	// This method informs the user that they've won.

	private void winnerMessage(String chosenWord) {
		if (encodedWord.equals(chosenWord)) {
			println("You guessed the word: " + chosenWord);
			println("You win.");
			AudioClip winner = getAudioClip(getCodeBase(), "WINNER.au");
			winner.play();
			canvas.displayWord(chosenWord);
		}
	}

	// This method informs the user that they've lost.

	private void loserMessage(String chosenWord) {
		if (numberOfGuesses == 0) {
			println("You're completely hung.");
			println("The word was: " + chosenWord);
			println("You lose.");
			AudioClip loser = getAudioClip(getCodeBase(), "LOSER.au");
			loser.play();
			canvas.displayWord(chosenWord);
		}
	}

}

