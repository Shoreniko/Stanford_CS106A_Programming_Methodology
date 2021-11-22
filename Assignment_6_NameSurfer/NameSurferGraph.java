
/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas implements NameSurferConstants, ComponentListener {
	
	/**
	 * Creates a new NameSurferGraph object that displays the data.
	 */

	// This method creates a NameSurferGraph class object and a new list that will
	// keep all names entered by the user that exist in the data base.

	public NameSurferGraph() {
		addComponentListener(this);
		entriesList = new ArrayList<>();
	}

	/**
	 * Clears the list of name surfer entries stored inside this class.
	 */

	// The job of this method is to clear the list whenever the button 'Clear' is
	// clicked.

	public void clear() {
		entriesList.clear();
	}

	/**
	 * Adds a new NameSurferEntry to the list of entries on the display. Note that
	 * this method does not actually draw the graph, but simply stores the entry;
	 * the graph is drawn by calling update.
	 */

	// The job of this method is to check whether the entry already exists in the
	// list of past entries or not.

	public void addEntry(NameSurferEntry entry) {
		if (!entriesList.isEmpty()) {
			for (int i = 0; i < entriesList.size(); i++) {
				if (entriesList.get(i).getName().equals(entry.getName())) {
					notSameEntry = false;
					break;
				}
			}
		}
		if (!entriesList.isEmpty() && notSameEntry) {
			entriesList.add(entry);
		}
		if (entriesList.isEmpty()) {
			entriesList.add(entry);
		}
	}

	/**
	 * Updates the display image by deleting all the graphical objects from the
	 * canvas and then reassembling the display according to the list of entries.
	 * Your application must call update after calling either clear or addEntry;
	 * update is also called whenever the size of the canvas changes.
	 */

	// The job of this method is to update the graph according to the situation.

	public void update() {
		removeAll();
		createGraphBackground();
		addDataToCanvas();
	}

	// The job of this method is to create the graph background containing vertical
	// and horizontal lines, along with decade labels.

	private void createGraphBackground() {
		int distanceBetweenVerticalLines = getWidth() / NDECADES;
		int decadeLabelOffset = 5;

		addVerticalLines(distanceBetweenVerticalLines);
		addDecadeLabels(distanceBetweenVerticalLines, decadeLabelOffset);
		addHorizontalLines();
	}

	// This method creates vertical lines for the graph background.

	private void addVerticalLines(int distance) {
		for (int i = 0; i < NDECADES; i++) {
			GLine verticalLine = new GLine(distance * i, 0, distance * i, getHeight());
			add(verticalLine);
		}
	}

	// This method creates decade labels for the graph background.

	private void addDecadeLabels(int distance, int offset) {
		for (int i = 0; i < NDECADES; i++) {
			String decadeInString = Integer.toString(START_DECADE + i * 10);
			GLabel decadeLabel = new GLabel(decadeInString);
			decadeLabel.setLocation(distance * i + offset, getHeight() - offset);
			add(decadeLabel);
		}
	}

	// This method creates horizontal lines for the graph background.

	private void addHorizontalLines() {
		GLine horizontalUp = new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE);
		GLine horizontalDown = new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(),
				getHeight() - GRAPH_MARGIN_SIZE);
		add(horizontalUp);
		add(horizontalDown);
	}

	// The job of this method is to add needed data to the canvas.

	private void addDataToCanvas() {
		if (!entriesList.isEmpty()) {
			addDataForEachName();
		}
		notSameEntry = true;
	}

	// This method goes through every member (name) of the list and for each name
	// depicts their rankings by decades on the graph.

	private void addDataForEachName() {
		for (int i = 0; i < entriesList.size(); i++) {
			addDataForEachDecade(i);
		}
	}

	// This method draws lines and lables according to the rank a certain name had
	// in each decade.

	private void addDataForEachDecade(int i) {
		for (int j = 0; j < NDECADES - 1; j++) {
			int nameOffsetFromPoint = 2;
			double startY = calculateYCoordinate(i, j);
			double endY = calculateYCoordinate(i, j + 1);
			GPoint startPoint = new GPoint(getWidth() / NDECADES * j, startY);
			GPoint endPoint = new GPoint(getWidth() / NDECADES * (j + 1), endY);
			addOneLine(i, startPoint, endPoint);
			addFullLabel(i, j, nameOffsetFromPoint, startPoint, endPoint);
		}
	}

	// The job of this method is to calculate the Y axis coordinate according to the
	// ranking position of a certain name in a certain decade.

	private double calculateYCoordinate(int i, int j) {
		double coordinateY = (((entriesList.get(i).getRank(START_DECADE + j * 10)
				* (getHeight() - GRAPH_MARGIN_SIZE * 2))) / MAX_RANK) + GRAPH_MARGIN_SIZE;
		if (entriesList.get(i).getRank(START_DECADE + j * 10) == 0) {
			coordinateY = getHeight() - GRAPH_MARGIN_SIZE;
		}
		return coordinateY;
	}

	// This method adds a line that depicts the ranking data to its proper position.

	private void addOneLine(int i, GPoint startPoint, GPoint endPoint) {
		GLine line = new GLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
		line.setColor(whichColor(i));
		add(line);
	}

	// This method controls what color the objects should be.

	private Color whichColor(int i) {
		switch (i % 4) {
		case 0:
			return Color.BLACK;
		case 1:
			return Color.RED;
		case 2:
			return Color.BLUE;
		case 3:
			return Color.YELLOW;
		}
		return null;
	}

	// The job of this method is to add the name and the ranking number to their
	// proper positions on the graph.

	private void addFullLabel(int i, int j, int nameOffsetFromPoint, GPoint startPoint, GPoint endPoint) {
		String rankingNumber = rankingNumberString(i, j);
		GLabel name = new GLabel("");
		addNameLabel(name, i, nameOffsetFromPoint, startPoint, rankingNumber);

		if (j == NDECADES - 2) {
			rankingNumber = rankingNumberString(i, j + 1);
			addNameLabel(name, i, nameOffsetFromPoint, endPoint, rankingNumber);
		}
	}

	// The job of this method is to convert ranking data from integers to strings.
	// However, if the name isn't in the top 1000 at all, an asterisk is written.

	private String rankingNumberString(int i, int j) {
		String rankingNumber;
		if (entriesList.get(i).getRank(START_DECADE + j * 10) != 0) {
			rankingNumber = Integer.toString(entriesList.get(i).getRank(START_DECADE + j * 10));
		} else {
			rankingNumber = "*";
		}
		return rankingNumber;
	}

	// The job of this method is to add the name label to its appropriate position
	// on the graph.

	private void addNameLabel(GLabel name, int i, int offset, GPoint point, String rankingNumber) {
		name = new GLabel(entriesList.get(i).getName() + " " + rankingNumber);
		name.setLocation(point.getX() + offset, point.getY() + offset);
		name.setColor(whichColor(i));
		add(name);
	}

	// Implementation of the ComponentListener interface.

	public void componentHidden(ComponentEvent e) {
	}

	public void componentMoved(ComponentEvent e) {
	}

	public void componentResized(ComponentEvent e) {
		update();
	}

	public void componentShown(ComponentEvent e) {
	}

	// Private instance variables.

	private ArrayList<NameSurferEntry> entriesList;
	private boolean notSameEntry = true;

}
