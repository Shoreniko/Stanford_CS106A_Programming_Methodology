
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {

	private static final double COEFFICIENT_OF_PROPORTION = 0.75;
	private double bodyY1 = getHeight() / 2 - BODY_LENGTH / 2;
	private double bodyY2 = getHeight() / 2 + BODY_LENGTH / 2;
	private int fullHangmanOffset = -180;
	private int offsetOfLetters = 0;
	private int tracker = 8;
	// Side note: I saw a one pixel offset between the head and the body of the
	// Hangman. 'additionalOffsetOfObjects' fixes this problem.
	private int additionalOffsetOfObjects = 1;

	/** Resets the display so that only the scaffold appears */

	// The job of this code is to put canvas in its initial position: clean canvas
	// only with the rope, beam, scaffold and the encoded word displayed on top.

	public void reset() {
		removeAll();
		GLine rope = new GLine(getWidth() / 2,
				bodyY1 - fullHangmanOffset - HEAD_RADIUS * 2 - additionalOffsetOfObjects - ROPE_LENGTH, getWidth() / 2,
				bodyY1 - fullHangmanOffset - HEAD_RADIUS * 2 - additionalOffsetOfObjects);
		GLine beam = new GLine(getWidth() / 2 - BEAM_LENGTH,
				bodyY1 - fullHangmanOffset - HEAD_RADIUS * 2 - additionalOffsetOfObjects - ROPE_LENGTH, getWidth() / 2,
				bodyY1 - fullHangmanOffset - HEAD_RADIUS * 2 - additionalOffsetOfObjects - ROPE_LENGTH);
		GLine scaffold = new GLine(getWidth() / 2 - BEAM_LENGTH,
				bodyY1 - fullHangmanOffset - HEAD_RADIUS * 2 - additionalOffsetOfObjects - ROPE_LENGTH,
				getWidth() / 2 - BEAM_LENGTH, bodyY1 - fullHangmanOffset - HEAD_RADIUS * 2 - additionalOffsetOfObjects
						- ROPE_LENGTH + SCAFFOLD_HEIGHT);
		add(scaffold);
		add(beam);
		add(rope);
	}

	/**
	 * Updates the word on the screen to correspond to the current state of the
	 * game. The argument string shows what letters have been guessed so far;
	 * unguessed letters are indicated by hyphens.
	 */

	// The job of this code is to display the current progress in decoding the word
	// on the canvas.

	public void displayWord(String word) {
		GObject gobj = getElementAt(getWidth() / 2 - BEAM_LENGTH - 40, bodyY1 - fullHangmanOffset - HEAD_RADIUS * 2
				- additionalOffsetOfObjects - ROPE_LENGTH + SCAFFOLD_HEIGHT + 50);
		if (gobj != null) {
			remove(gobj);
		}
		GLabel theWord = new GLabel(word, getWidth() / 2 - BEAM_LENGTH - 40, bodyY1 - fullHangmanOffset
				- HEAD_RADIUS * 2 - additionalOffsetOfObjects - ROPE_LENGTH + SCAFFOLD_HEIGHT + 50);
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

	public void noteIncorrectGuess(char letter, boolean bool) {
		drawTheHangman();
		if (bool) {
			GLabel incorrectGuesses = new GLabel(String.valueOf(letter),
					getWidth() / 2 - BEAM_LENGTH - 40 + offsetOfLetters, (int) (bodyY1 - fullHangmanOffset
							- HEAD_RADIUS * 2 - additionalOffsetOfObjects - ROPE_LENGTH + SCAFFOLD_HEIGHT + 80));
			incorrectGuesses.setFont("Helvetica-24");
			add(incorrectGuesses);
			offsetOfLetters += 24;
		}
		if (tracker == 8) {
			offsetOfLetters = 0;
		}
	}

	// The job of this code is to draw the Hangman mistake by mistake. As the number
	// of mistakes grows, new parts of the body are being added to the canvas.
	// Ultimately, when full Hangman is drawn, we can guess that the game is over.

	private void drawTheHangman() {
		tracker--;
		switch (tracker) {
		case 7:
			GOval head = new GOval(getWidth() / 2 - HEAD_RADIUS,
					bodyY1 - fullHangmanOffset - HEAD_RADIUS * 2 - additionalOffsetOfObjects, HEAD_RADIUS * 2,
					HEAD_RADIUS * 2);
			add(head);
			break;
		case 6:
			GLine body = new GLine(getWidth() / 2, bodyY1 - fullHangmanOffset, getWidth() / 2,
					bodyY2 - fullHangmanOffset);
			add(body);
			break;
		case 5:
			GLine upperLeftArm = new GLine(getWidth() / 2 - UPPER_ARM_LENGTH,
					bodyY1 + ARM_OFFSET_FROM_HEAD - fullHangmanOffset, getWidth() / 2,
					bodyY1 + ARM_OFFSET_FROM_HEAD - fullHangmanOffset);
			GLine lowerLeftArm = new GLine(getWidth() / 2 - UPPER_ARM_LENGTH,
					bodyY1 - fullHangmanOffset + ARM_OFFSET_FROM_HEAD, getWidth() / 2 - UPPER_ARM_LENGTH,
					bodyY1 - fullHangmanOffset + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
			add(upperLeftArm);
			add(lowerLeftArm);
			break;
		case 4:
			GLine upperRightArm = new GLine(getWidth() / 2, bodyY1 - fullHangmanOffset + ARM_OFFSET_FROM_HEAD,
					getWidth() / 2 + UPPER_ARM_LENGTH, bodyY1 - fullHangmanOffset + ARM_OFFSET_FROM_HEAD);
			GLine lowerRightArm = new GLine(getWidth() / 2 + UPPER_ARM_LENGTH,
					bodyY1 - fullHangmanOffset + ARM_OFFSET_FROM_HEAD, getWidth() / 2 + UPPER_ARM_LENGTH,
					bodyY1 - fullHangmanOffset + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
			add(upperRightArm);
			add(lowerRightArm);
			break;
		case 3:
			GLine leftHip = new GLine(getWidth() / 2 - HIP_WIDTH, bodyY2 - fullHangmanOffset, getWidth() / 2,
					bodyY2 - fullHangmanOffset);
			GLine leftLeg = new GLine(getWidth() / 2 - HIP_WIDTH, bodyY2 - fullHangmanOffset,
					getWidth() / 2 - HIP_WIDTH, bodyY2 - fullHangmanOffset + LEG_LENGTH);
			add(leftHip);
			add(leftLeg);
			break;
		case 2:
			GLine rightHip = new GLine(getWidth() / 2, bodyY2 - fullHangmanOffset, getWidth() / 2 + HIP_WIDTH,
					bodyY2 - fullHangmanOffset);
			GLine rightLeg = new GLine(getWidth() / 2 + HIP_WIDTH, bodyY2 - fullHangmanOffset,
					getWidth() / 2 + HIP_WIDTH, bodyY2 - fullHangmanOffset + LEG_LENGTH);
			add(rightHip);
			add(rightLeg);
			break;
		case 1:
			GLine leftFoot = new GLine(getWidth() / 2 - HIP_WIDTH - FOOT_LENGTH,
					bodyY2 - fullHangmanOffset + LEG_LENGTH, getWidth() / 2 - HIP_WIDTH,
					bodyY2 - fullHangmanOffset + LEG_LENGTH);
			add(leftFoot);
			break;
		case 0:
			GLine rightFoot = new GLine(getWidth() / 2 + HIP_WIDTH, bodyY2 - fullHangmanOffset + LEG_LENGTH,
					getWidth() / 2 + HIP_WIDTH + FOOT_LENGTH, bodyY2 - fullHangmanOffset + LEG_LENGTH);
			add(rightFoot);
			tracker = 8;
			break;
		}

	}

	// Constants for the simple version of the picture (in pixels).

	private static final int SCAFFOLD_HEIGHT = (int) (360 * COEFFICIENT_OF_PROPORTION);
	private static final int BEAM_LENGTH = (int) (144 * COEFFICIENT_OF_PROPORTION);
	private static final int ROPE_LENGTH = (int) (18 * COEFFICIENT_OF_PROPORTION);
	private static final int HEAD_RADIUS = (int) (36 * COEFFICIENT_OF_PROPORTION);
	private static final int BODY_LENGTH = (int) (144 * COEFFICIENT_OF_PROPORTION);
	private static final int ARM_OFFSET_FROM_HEAD = (int) (28 * COEFFICIENT_OF_PROPORTION);
	private static final int UPPER_ARM_LENGTH = (int) (72 * COEFFICIENT_OF_PROPORTION);
	private static final int LOWER_ARM_LENGTH = (int) (44 * COEFFICIENT_OF_PROPORTION);
	private static final int HIP_WIDTH = (int) (36 * COEFFICIENT_OF_PROPORTION);
	private static final int LEG_LENGTH = (int) (108 * COEFFICIENT_OF_PROPORTION);
	private static final int FOOT_LENGTH = (int) (28 * COEFFICIENT_OF_PROPORTION);

}
