
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import acm.program.*;

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	private int offsetOfLetters = 0;
	private int tracker = 8;
	private double theSeaHeight;
	private GRect theSea;

	// The job of this code is to put canvas in its initial position. Meaning, add
	// the water (which we won't be able to see at the beginning) and add the proper
	// image.

	public void categoriesImage() {
		GImage categoriesImage = new GImage("this.jpg");
		add(categoriesImage, 0, 0);
	}
	
	public void reset(String userChoice) {
		removeAll();
		theSeaInitialisation();
		chosingImageByCategory(userChoice);
	}

	// The job of this code is to create the initial version of the rectangle
	// (water).

	private void theSeaInitialisation() {
		theSea = new GRect(0, 0, 0, 0);
		theSea.setFilled(true);
		theSea.setColor(Color.BLUE);
		theSea.setFillColor(Color.BLUE);
		add(theSea);
	}

	// The job of this code is to choose a proper image that will be displayed on
	// the canvas depending on the user's choice.

	private void chosingImageByCategory(String userChoice) {
		ifAnimals(userChoice);
		ifBodyParts(userChoice);
		ifClothes(userChoice);
		ifAvengers(userChoice);
		ifFamilyMembers(userChoice);
		ifFood(userChoice);
		ifProfessions(userChoice);
		ifSports(userChoice);
		ifTransportation(userChoice);
		ifStudentsThatDeservePlusPlus(userChoice);
	}

	// Category: Animals.

	private void ifAnimals(String userChoice) {
		if (userChoice.equals("ANIMALS")) {
			GImage animals = new GImage("animals.jpg");
			add(animals, 60, 40);
		}
	}

	// Category: Body parts.

	private void ifBodyParts(String userChoice) {
		if (userChoice.equals("BODY PARTS")) {
			GImage bodyParts = new GImage("body parts.jpg");
			add(bodyParts, 100, 10);
		}
	}

	// Category: Clothes.

	private void ifClothes(String userChoice) {
		if (userChoice.equals("CLOTHES")) {
			GImage clothes = new GImage("clothes.jpg");
			add(clothes, 100, 10);
		}
	}

	// Category: Avengers (Marvel).

	private void ifAvengers(String userChoice) {
		if (userChoice.equals("AVENGERS")) {
			GImage avengers = new GImage("avengers.jpg");
			add(avengers, 100, 10);
		}
	}

	// Category: Family members.

	private void ifFamilyMembers(String userChoice) {
		if (userChoice.equals("FAMILY MEMBERS")) {
			GImage familyMembers = new GImage("family members.jpg");
			add(familyMembers, 70, 10);
		}
	}

	// Category: Food.

	private void ifFood(String userChoice) {
		if (userChoice.equals("FOOD")) {
			GImage food = new GImage("food.jpg");
			add(food, 50, 10);
		}
	}

	// Category: Professions.

	private void ifProfessions(String userChoice) {
		if (userChoice.equals("PROFESSIONS")) {
			GImage professions = new GImage("professions.jpg");
			add(professions, 50, 10);
		}
	}

	// Category: Sports.

	private void ifSports(String userChoice) {
		if (userChoice.equals("SPORTS")) {
			GImage sports = new GImage("sports.jpg");
			add(sports, 70, 10);
		}
	}

	// Category: Transportation.

	private void ifTransportation(String userChoice) {
		if (userChoice.equals("TRANSPORTATION")) {
			GImage transportation = new GImage("transportation.jpg");
			add(transportation, 70, 10);
		}
	}

	// Category: Students that deserve plus plus.

	private void ifStudentsThatDeservePlusPlus(String userChoice) {
		if (userChoice.equals("STUDENTS THAT DESERVE PLUS PLUS")) {
			GImage studentsPlusPlus = new GImage("students.jpg");
			add(studentsPlusPlus, 70, 10);
		}
	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */

	// The job of this code is to display the current progress in decoding the word
	// on the canvas.

	public void displayWord(String word) {
		GObject gobj = getElementAt(30, 350);
		if (gobj != null) {
			remove(gobj);
		}
		GLabel theWord = new GLabel(word, 30, 350);
		theWord.setFont("Helvetica-40");
		add(theWord);
	}

	/**
	 * Updates the display to correspond to an incorrect guess by the user. Calling
	 * this method causes the next body part to appear on the scaffold and adds the
	 * letter to the list of incorrect guesses that appears at the bottom of the
	 * window.
	 */

	// This code acts only if the guess made by the user was incorrect, in which
	// case:
	// - New body part is added to the canvas.
	// - New incorrectly guessed character is displayed on the canvas (If and only
	// if it hadn't been displayed yet).

	public void noteIncorrectGuess(char letter, boolean bool, int canvasHeight) {
		drawTheDrownman(canvasHeight);
		if (bool) {
			GLabel incorrectGuesses = new GLabel(String.valueOf(letter), 30 + offsetOfLetters, 380);
			incorrectGuesses.setFont("Helvetica-24");
			add(incorrectGuesses);
			offsetOfLetters += 24;
		}
		if (tracker == 8) {
			offsetOfLetters = 0;
		}
	}

	// The job of this code is to control the amount of guesses using the tracker.
	// On every mistaken turn the rectangle (water) moves up.

	private void drawTheDrownman(int canvasHeight) {
		tracker--;
		theSeaHeight = (int) (canvasHeight / 8);
		switch (tracker) {
		case 7:
			theSea.setLocation(0, canvasHeight);
			theSea.setSize(500, canvasHeight);
			theSea.move(0, -theSeaHeight);
			add(theSea);
			break;
		case 6:
			theSea.move(0, -theSeaHeight);
			break;
		case 5:
			theSea.move(0, -theSeaHeight);
			break;
		case 4:
			theSea.move(0, -theSeaHeight);
			break;
		case 3:
			theSea.move(0, -theSeaHeight);
			break;
		case 2:
			theSea.move(0, -theSeaHeight);
			break;
		case 1:
			theSea.move(0, -theSeaHeight);
			break;
		case 0:
			theSea.move(0, -theSeaHeight);
			tracker = 8;
			break;
		}

	}

}
