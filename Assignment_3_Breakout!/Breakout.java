
/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	private GRect thePaddle;
	private GOval theBall;
	private GObject gobj;
	private double vx, vy;
	private int bricksAmount = NBRICKS_PER_ROW * NBRICK_ROWS;
	private RandomGenerator rgen = RandomGenerator.getInstance();

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;

	/** Runs the Breakout program. */

	// The job of this code is to create a prototype of a widely known game
	// "Breakout". This method is divided into two parts: the initialisation phase
	// and the actual process of playing the game.

	public void init() {
		addMouseListeners();
		theSetup();
		gamingProcess();
	}

	// This method provides the creation of the initial phase of the game, which
	// consists of creating the bricks and a paddle.

	private void theSetup() {
		theBricks();
		thePaddle();
	}

	// This method creates specific amount of bricks and distributes them as
	// required for the initial stage of the game.

	private void theBricks() {
		double x = (WIDTH - (NBRICKS_PER_ROW * BRICK_WIDTH + (NBRICKS_PER_ROW - 1) * BRICK_SEP)) / 2;
		double y = BRICK_Y_OFFSET;
		generateAllBrickRows(x, y);
	}

	// This method creates the whole table of bricks.

	private void generateAllBrickRows(double x, double y) {
		for (int i = 0; i < NBRICK_ROWS; i++) {
			fillOneRow(i, x, y);
			x = (WIDTH - (NBRICKS_PER_ROW * BRICK_WIDTH + (NBRICKS_PER_ROW - 1) * BRICK_SEP)) / 2;
			y += BRICK_HEIGHT + BRICK_SEP;
		}
	}

	// This method fills one row with specific amount of bricks.

	private void fillOneRow(int i, double x, double y) {
		for (int j = 0; j < NBRICKS_PER_ROW; j++) {
			GRect oneBrick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
			add(oneBrick);
			oneBrick.setFilled(true);
			brickColors(oneBrick, i);
			x += BRICK_WIDTH + BRICK_SEP;
		}
	}

	// This method changes the colour of bricks according to their row number.

	private void brickColors(GRect oneBrick, int i) {
		Color c = Color.CYAN;
		if (i <= 1) {
			c = Color.RED;
		} else if (i <= 3) {
			c = Color.ORANGE;
		} else if (i <= 5) {
			c = Color.YELLOW;
		} else if (i <= 7) {
			c = Color.GREEN;
		} else if (i <= 9) {
			c = Color.CYAN;
		}
		oneBrick.setColor(c);
	}

	// This method creates a paddle which is initially aligned in the bottom centre
	// of the window.

	private void thePaddle() {
		thePaddle = new GRect((WIDTH - PADDLE_WIDTH) / 2, HEIGHT - (PADDLE_HEIGHT + PADDLE_Y_OFFSET), PADDLE_WIDTH,
				PADDLE_HEIGHT);
		thePaddle.setFilled(true);
		add(thePaddle);
	}

	// This method is responsible for the movement of the paddle according to the
	// mouse move controlled by the user.

	public void mouseMoved(MouseEvent e) {
		if (e.getX() - PADDLE_WIDTH / 2 >= 0 && (e.getX() + PADDLE_WIDTH / 2) < WIDTH) {
			thePaddle.setLocation(e.getX() - PADDLE_WIDTH / 2, thePaddle.getY());
		}
	}

	// The second part of the game is the gaming process itself.

	private void gamingProcess() {
		howManyTries();
		loserMessage();
	}

	// This method is intended to control how many tries the user has to play the
	// game.

	private void howManyTries() {
		for (int i = 0; i < NTURNS; i++) {
			if (bricksAmount != 0) {
				movingBall();
				pause(1000);
			}
		}
	}

	// This method is intended to specify the behaviour of the ball. It is divided
	// into three parts: initialising the ball object, describing the ball's
	// velocity properties and controlling ball movement according to the
	// environment.

	private void movingBall() {
		add(theBall());
		ballVelocities();
		ballMovement();
	}

	// This method is the initial phase of creating the ball.

	private GOval theBall() {
		theBall = new GOval(BALL_RADIUS * 2, BALL_RADIUS * 2);
		theBall.setLocation(WIDTH / 2 - BALL_RADIUS, HEIGHT / 2 - BALL_RADIUS);
		theBall.setFilled(true);
		return theBall;
	}

	// This method specifies the velocity properties of the ball.

	private void ballVelocities() {
		vx = rgen.nextDouble(1.0, 3.0);
		if (rgen.nextBoolean(0.5)) {
			vx = -vx;
		}
		vy = 3;
	}

	// This method controls ball movement and its feedback.
	// If the user misses a ball catch with a paddle, the ball will be replaced with
	// a new one (however, this replacement process is limited by the number of
	// turns).
	// If the user removes all bricks, the game ends with a winning
	// message.

	private void ballMovement() {
		while (true) {
			theBall.move(vx, vy);
			pause(10);
			checkForEnvironment();
			if (theBall.getY() + BALL_RADIUS * 2 >= HEIGHT) {
				remove(theBall);
				break;
			}
			if (bricksAmount == 0) {
				winnerMessage();
				break;
			}
		}
	}

	// This method checks whether the ball has touched a brick, a paddle or a wall.

	private void checkForEnvironment() {
		brickOrPaddle();
		checkForWalls();
	}

	// This method is intended to check whether the ball touched the paddle or a
	// brick.

	private void brickOrPaddle() {
		GObject collider = getCollidingObject();
		colliderIsPaddle(collider);
		colliderIsBrick(collider);
	}

	// This code is intended to check whether there is any object positioned on one
	// of the four points/angles of the rectangle of the ball.

	private GObject getCollidingObject() {
		gobj = getElementAt(theBall.getX(), theBall.getY());
		if (gobj == null) {
			gobj = getElementAt(theBall.getX() + BALL_RADIUS * 2, theBall.getY());
		}
		if (gobj == null) {
			gobj = getElementAt(theBall.getX() + BALL_RADIUS * 2, theBall.getY() + BALL_RADIUS * 2);
		}
		if (gobj == null) {
			gobj = getElementAt(theBall.getX(), theBall.getY() + BALL_RADIUS * 2);
		}
		return gobj;
	}

	// This method checks if the ball touched the paddle, in which case the ball
	// will start moving in an opposite direction.

	private void colliderIsPaddle(GObject collider) {
		if (collider == thePaddle) {
			if (rgen.nextBoolean(0.5)) {
				vx = -vx;
			}
			if (vy > 0) {
				vy = -vy;
			}
		}
	}

	// This method checks if the ball touched the brick, in which case the brick
	// will be removed.

	private void colliderIsBrick(GObject collider) {
		if (collider != thePaddle && collider != null) {
			remove(collider);
			bricksAmount--;
			if (rgen.nextBoolean(0.5)) {
				vx = -vx;
			}
			vy = -vy;
		}
	}

	// This method is intended to check whether the ball has touched a wall in the
	// north, west, or east. If the ball has touched one of the three walls, it will
	// react accordingly by moving in the opposite direction.

	private void checkForWalls() {
		if (theBall.getX() <= 0 || theBall.getX() + BALL_RADIUS * 2 >= WIDTH) {
			vx = -vx;
		}
		if (theBall.getY() <= 0) {
			vy = -vy;
		}
	}

	// This method is intended to inform the user that they've won. That is they
	// were able to remove all bricks in the permitted amount of game tries.

	private void winnerMessage() {
		GLabel winnerText = new GLabel("YOU WON!");
		winnerText.setLocation((WIDTH - winnerText.getWidth()) / 2, (HEIGHT - winnerText.getHeight()) / 2);
		add(winnerText);
		remove(theBall);
	}

	// This method is intended to inform the user that they've lost. That is even
	// after three tries the user was not able to remove all bricks.

	private void loserMessage() {
		if (bricksAmount != 0) {
			GLabel loserText = new GLabel("YOU LOST!");
			loserText.setLocation((WIDTH - loserText.getWidth()) / 2, (HEIGHT - loserText.getHeight()) / 2);
			add(loserText);
		}
	}

}
