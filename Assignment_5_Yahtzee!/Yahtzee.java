
/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.*;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	// This program generates widely known game of "Yahtzee!".

	public void run() {
		askTheUserForPlayers();
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		initialiseTheScoreBoard();
		playGame();
	}

	// This method asks the user to enter the number of players and their names.

	private void askTheUserForPlayers() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
	}

	// This method initialises the score board, which will later be used to store
	// current progress (scores) of the players.
	// Categories that can be chosen by the user are initialised to -1.
	// Categories that can not be chosen by the user are initialised to 0.

	private void initialiseTheScoreBoard() {
		scoreBoard = new int[nPlayers][N_CATEGORIES];
		for (int initialPlayer = 0; initialPlayer < nPlayers; initialPlayer++) {
			for (int category = 0; category < N_CATEGORIES; category++) {
				scoreBoard[initialPlayer][category] = -1;
				if (category == UPPER_SCORE - 1 || category == UPPER_BONUS - 1 || category == LOWER_SCORE - 1
						|| category == TOTAL - 1) {
					scoreBoard[initialPlayer][category] = 0;
				}
			}
		}
	}

	// This method calls the gaming process.

	private void playGame() {
		theGamingProcess();
		revealFinalScores();
		winnerMessage();
	}

	// The gaming process is going to be repeated only 13 times, because there are
	// only 13 scoring categories.

	private void theGamingProcess() {
		for (int i = 0; i < N_SCORING_CATEGORIES; i++) {
			oneRound();
		}
	}

	// Since every player has to make their move on every round, this method is
	// going to be repeated as many times as there are players.

	private void oneRound() {
		for (int gamePlayer = 1; gamePlayer <= nPlayers; gamePlayer++) {
			veryFirstRoll(gamePlayer);
			numberOfClicksOnRoll = theRollingProcess(dice, maxAmountOfPermittedRolls, numberOfClicksOnRoll);
			categorySelectionProcess(gamePlayer);
			isSuitableCategory = checkCategory(dice, category);
			properScoreForCategory(gamePlayer);
			numberOfClicksOnRoll = 1;
			immediateScoreReveal(gamePlayer);
		}
	}

	// This method informs the players who's turn is it and waits for the proper
	// player to make their turn (roll the dice).

	private void veryFirstRoll(int gamePlayer) {
		display.printMessage(playerNames[gamePlayer - 1] + "'s turn! Click \"Roll Dice\" button to roll the dice.");
		display.waitForPlayerToClickRoll(gamePlayer);
	}

	// The job of this method is to display the results after the very first roll.
	// After the very first roll, the program gives the user two more chances to
	// roll again.

	private int theRollingProcess(int[] dice, int maxAmountOfPermittedRolls, int numberOfClicksOnRoll) {
		while (numberOfClicksOnRoll <= maxAmountOfPermittedRolls) {
			randomisingTheDice(dice, numberOfClicksOnRoll);
			numberOfClicksOnRoll++;
			display.displayDice(dice);
			rollAgain(maxAmountOfPermittedRolls, numberOfClicksOnRoll);
		}
		return numberOfClicksOnRoll;
	}

	// The job of this method is to randomise the dice.

	private void randomisingTheDice(int[] dice, int numberOfClicksOnRoll) {
		for (int k = 0; k < N_DICE; k++) {
			if (numberOfClicksOnRoll == 1 || display.isDieSelected(k)) {
				dice[k] = rgen.nextInt(1, 6);
			}
		}
	}

	// This method gives the user the chance to roll selected dice two more times.

	private void rollAgain(int maxAmountOfPermittedRolls, int numberOfClicksOnRoll) {
		if (numberOfClicksOnRoll <= maxAmountOfPermittedRolls) {
			display.printMessage("Select the dice you wish to re-roll and click \"Roll Again\".");
			display.waitForPlayerToSelectDice();
		}
	}

	// This method describes the category selection process.

	private void categorySelectionProcess(int gamePlayer) {
		display.printMessage("Select a category for this roll.");
		category = display.waitForPlayerToSelectCategory();
		sameCategorySelectedAgain(gamePlayer);
	}

	// This code informs the user if they chose previously chosen category once
	// again and asks the user to choose another category.

	private void sameCategorySelectedAgain(int gamePlayer) {
		while (scoreBoard[gamePlayer - 1][category - 1] >= 0) {
			display.printMessage("You can't choose the same category again. Please, choose another category.");
			category = display.waitForPlayerToSelectCategory();
		}
	}

	// The job of this code is to check whether the category chosen by the user
	// correlates with the rolled dice.

	private boolean checkCategory(int[] dice, int category) {

		if (category <= SIXES || category == CHANCE) {
			return true;
		}

		if (category == THREE_OF_A_KIND) {
			Arrays.sort(dice);
			if ((dice[2] == dice[1] && dice[1] == dice[0]) || (dice[2] == dice[3] && dice[3] == dice[4])) {
				return true;
			}
		}

		if (category == FOUR_OF_A_KIND) {
			Arrays.sort(dice);
			if ((dice[0] == dice[1] && dice[1] == dice[2] && dice[2] == dice[3])
					|| (dice[1] == dice[2] && dice[2] == dice[3] && dice[3] == dice[4])) {
				return true;
			}
		}

		if (category == FULL_HOUSE) {
			Arrays.sort(dice);
			if (dice[0] == dice[1] && dice[1] != dice[2] && dice[2] == dice[3] && dice[3] == dice[4]) {
				return true;
			}
			if (dice[0] == dice[1] && dice[1] == dice[2] && dice[2] != dice[3] && dice[3] == dice[4]) {
				return true;
			}
		}

		if (category == SMALL_STRAIGHT) {
			Arrays.sort(dice);
			if ((dice[0] == 1 || dice[1] == 1) && (dice[1] == 2 || dice[2] == 2) && (dice[2] == 3 || dice[3] == 3)
					&& (dice[3] == 4 || dice[4] == 4)) {
				return true;
			}
			if ((dice[0] == 2 || dice[1] == 2) && (dice[1] == 3 || dice[2] == 3) && (dice[2] == 4 || dice[3] == 4)
					&& (dice[3] == 5 || dice[4] == 5)) {
				return true;
			}
			if ((dice[0] == 3 || dice[1] == 3) && (dice[1] == 4 || dice[2] == 4) && (dice[2] == 5 || dice[3] == 5)
					&& (dice[3] == 6 || dice[4] == 6)) {
				return true;
			}
		}

		if (category == LARGE_STRAIGHT) {
			Arrays.sort(dice);
			if (dice[0] == 1 && dice[1] == 2 && dice[2] == 3 && dice[3] == 4 && dice[4] == 5) {
				return true;
			}
			if (dice[0] == 2 && dice[1] == 3 && dice[2] == 4 && dice[3] == 5 && dice[4] == 6) {
				return true;
			}
		}

		if (category == YAHTZEE) {
			for (int i = 0; i < dice.length - 1; i++) {
				if (dice[i] != dice[i + 1]) {
					return false;
				}
			}
			return true;
		}

		return false;
	}

	// The job of this code is to execute the score calculation process if the user
	// chose the right category (suitable for the user's dice) and otherwise give
	// the user the score of zero.
	// Along with calculating the proper category score, this code calculates the
	// total score.

	private void properScoreForCategory(int gamePlayer) {
		if (isSuitableCategory || category == 15) {
			scoreBoard[gamePlayer - 1][category - 1] = properScore(dice, category);
			scoreBoard[gamePlayer - 1][TOTAL - 1] += properScore(dice, category);
		} else {
			scoreBoard[gamePlayer - 1][category - 1] = 0;
		}
	}

	// The job of this code is to calculate the proper score for the proper category.
	
	private int properScore(int[] dice, int category) {

		switch (category) {
		case ONES:
			int ones = 0;
			for (int k = 0; k < N_DICE; k++) {
				if (dice[k] == 1) {
					ones = ones + 1;
				}
			}
			return ones;

		case TWOS:
			int twos = 0;
			for (int k = 0; k < N_DICE; k++) {
				if (dice[k] == 2) {
					twos = twos + 2;
				}
			}
			return twos;

		case THREES:
			int threes = 0;
			for (int k = 0; k < N_DICE; k++) {
				if (dice[k] == 3) {
					threes = threes + 3;
				}
			}
			return threes;

		case FOURS:
			int fours = 0;
			for (int k = 0; k < N_DICE; k++) {
				if (dice[k] == 4) {
					fours = fours + 4;
				}
			}
			return fours;

		case FIVES:
			int fives = 0;
			for (int k = 0; k < N_DICE; k++) {
				if (dice[k] == 5) {
					fives = fives + 5;
				}
			}
			return fives;

		case SIXES:
			int sixes = 0;
			for (int k = 0; k < N_DICE; k++) {
				if (dice[k] == 6) {
					sixes = sixes + 6;
				}
			}
			return sixes;

		case THREE_OF_A_KIND:
			int threeOfAKind = 0;
			for (int k = 0; k < N_DICE; k++) {
				threeOfAKind = threeOfAKind + dice[k];
			}
			return threeOfAKind;

		case FOUR_OF_A_KIND:
			int fourOfAKind = 0;
			for (int k = 0; k < N_DICE; k++) {
				fourOfAKind = fourOfAKind + dice[k];
			}
			return fourOfAKind;

		case FULL_HOUSE:
			return 25;

		case SMALL_STRAIGHT:
			return 30;

		case LARGE_STRAIGHT:
			return 40;

		case YAHTZEE:
			return 50;

		case CHANCE:
			int chance = 0;
			for (int k = 0; k < N_DICE; k++) {
				chance += dice[k];
			}
			return chance;
		}

		return 0;
	}

	// The job of this code is to update the score board. This code updates recently
	// chosen category cell and the total score cell.

	private void immediateScoreReveal(int gamePlayer) {
		display.updateScorecard(category, gamePlayer, scoreBoard[gamePlayer - 1][category - 1]);
		display.updateScorecard(TOTAL, gamePlayer, scoreBoard[gamePlayer - 1][TOTAL - 1]);
	}

	// The job of this code is to reveal the final scores. Meaning, the upper score,
	// upper bonus, lower score. This code updates the total score amount one final
	// time.

	private void revealFinalScores() {
		for (int scoreOfPlayer = 1; scoreOfPlayer <= nPlayers; scoreOfPlayer++) {
			upperScoreCalculation(scoreOfPlayer);
			upperBonusCalculation(scoreOfPlayer);
			lowerScoreCalculation(scoreOfPlayer);
			theScoreCardUpdate(scoreOfPlayer);
		}
	}

	// The job of this code is to calculate the upper score.

	private void upperScoreCalculation(int scoreOfPlayer) {
		scoreBoard[scoreOfPlayer - 1][UPPER_SCORE - 1] = scoreBoard[scoreOfPlayer - 1][ONES - 1]
				+ scoreBoard[scoreOfPlayer - 1][TWOS - 1] + scoreBoard[scoreOfPlayer - 1][THREES - 1]
				+ scoreBoard[scoreOfPlayer - 1][FOURS - 1] + scoreBoard[scoreOfPlayer - 1][FIVES - 1]
				+ scoreBoard[scoreOfPlayer - 1][SIXES - 1];
	}

	// The job of this code is to calculate the upper bonus.

	private void upperBonusCalculation(int scoreOfPlayer) {
		if (scoreBoard[scoreOfPlayer - 1][UPPER_SCORE - 1] >= 63) {
			scoreBoard[scoreOfPlayer - 1][UPPER_BONUS - 1] = 35;
			scoreBoard[scoreOfPlayer - 1][TOTAL - 1] += 35;
		} else {
			scoreBoard[scoreOfPlayer - 1][UPPER_BONUS - 1] = 0;
		}
	}

	// The job of this code is to calculate the lower score.

	private void lowerScoreCalculation(int scoreOfPlayer) {
		scoreBoard[scoreOfPlayer - 1][LOWER_SCORE - 1] = scoreBoard[scoreOfPlayer - 1][THREE_OF_A_KIND - 1]
				+ scoreBoard[scoreOfPlayer - 1][FOUR_OF_A_KIND - 1] + scoreBoard[scoreOfPlayer - 1][FULL_HOUSE - 1]
				+ scoreBoard[scoreOfPlayer - 1][SMALL_STRAIGHT - 1] + scoreBoard[scoreOfPlayer - 1][LARGE_STRAIGHT - 1]
				+ scoreBoard[scoreOfPlayer - 1][YAHTZEE - 1] + scoreBoard[scoreOfPlayer - 1][CHANCE - 1];
	}

	// The job of this code is to update the score card with final scores.

	private void theScoreCardUpdate(int scoreOfPlayer) {
		display.updateScorecard(UPPER_SCORE, scoreOfPlayer, scoreBoard[scoreOfPlayer - 1][UPPER_SCORE - 1]);
		display.updateScorecard(TOTAL, scoreOfPlayer, scoreBoard[scoreOfPlayer - 1][TOTAL - 1]);
		display.updateScorecard(UPPER_BONUS, scoreOfPlayer, scoreBoard[scoreOfPlayer - 1][UPPER_BONUS - 1]);
		display.updateScorecard(LOWER_SCORE, scoreOfPlayer, scoreBoard[scoreOfPlayer - 1][LOWER_SCORE - 1]);
	}

	// The job of this code is to inform the players who won and what was the
	// winning (highest) score.

	private void winnerMessage() {
		int winnerPlayer = 1;
		int winningScore = scoreBoard[0][TOTAL - 1];
		for (winnerPlayer = 1; winnerPlayer < nPlayers; winnerPlayer++) {
			if (scoreBoard[winnerPlayer][TOTAL - 1] > winningScore) {
				winningScore = scoreBoard[winnerPlayer][TOTAL - 1];
			}
		}
		display.printMessage("Congratulations, " + playerNames[winnerPlayer - 1]
				+ ", you're the winner with a total score of " + winningScore + "!");
	}

	/* Private instance variables */

	private int category;
	private boolean isSuitableCategory;
	private int[] dice = new int[N_DICE];
	private int[][] scoreBoard;
	private int maxAmountOfPermittedRolls = 3;
	private int numberOfClicksOnRoll = 1;
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();

}
