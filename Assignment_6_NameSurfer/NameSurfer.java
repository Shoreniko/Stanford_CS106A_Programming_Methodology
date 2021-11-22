
/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants, KeyListener {
	
	/**
	 * This method has the responsibility for reading in the data base and
	 * initializing the interactors at the bottom of the window.
	 */

	// This method initialises the NameSurfer program by:
	// - creating the objects of the NameSurferDataBase and NameSurferGraph classes.
	// - creating graphical user interactors.
	// - adding specific listeners.

	public void init() {
		createFullDataBase();
		createGraph();
		createInteractors();
		addListeners();
	}

	// This method creates an object of the NameSurferDataBase class and passes it
	// the name of the file, which has the data of names and their rankings.

	private void createFullDataBase() {
		fullDataBase = new NameSurferDataBase(NAMES_DATA_FILE);
	}

	// This method creates an object of the NameSurferGraph class for the graph to
	// show proper information. In the beginning, the graph only has a background.

	private void createGraph() {
		graph = new NameSurferGraph();
		graph.update();
		add(graph);
	}

	// This method creates graphical user interactors and adds them to South
	// direction of the window.

	private void createInteractors() {
		JLabel nameLabel = new JLabel("Name ");
		JButton graphButton = new JButton("Graph");
		JButton clearButton = new JButton("Clear");
		inputTextField = new JTextField(GRAPH_MARGIN_SIZE);

		add(nameLabel, SOUTH);
		add(inputTextField, SOUTH);
		add(graphButton, SOUTH);
		add(clearButton, SOUTH);
	}

	// This method adds key and action listeners for the program to understand
	// user's actions and act accordingly.

	private void addListeners() {
		inputTextField.addKeyListener(this);
		addActionListeners();
	}

	// Whenever there is an input and the user clicks 'Enter' on keyboard, this
	// method calls another method to check if the input entry exists in data base.
	// In other words, click on 'Enter' reacts the same way as click on 'Graph'
	// button.

	public void keyTyped(KeyEvent l) {
		if (l.getKeyChar() == '\n' && inputTextField.getText().length() != 0) {
			checkIfCorrectEntry();
		}
	}

	// The job of this method is to find the name entered by the user in the data
	// base. If the entry exists in the data base, the graph updates accordingly.

	private void checkIfCorrectEntry() {
		entry = fullDataBase.findEntry(inputTextField.getText());
		if (entry != null) {
			graph.addEntry(entry);
			graph.update();
		}
	}

	/**
	 * This class is responsible for detecting when the buttons are clicked, so you
	 * will have to define a method to respond to button actions.
	 */

	// This method understands if the user clicks any buttons and acts accordingly
	// by checking which button was it.

	public void actionPerformed(ActionEvent e) {
		userClickedGraphButton(e);
		userClickedClearButton(e);
	}

	// When the user doesn't have an empty input and clicks the 'Graph' button, this
	// method calls another method to check if the input entry exists in data base.

	private void userClickedGraphButton(ActionEvent e) {
		if (e.getActionCommand().equals("Graph") && inputTextField.getText().length() != 0) {
			checkIfCorrectEntry();
		}
	}

	// When the user clicks the 'Clear' button, this method erases everything from
	// the canvas but the initial graph background.

	private void userClickedClearButton(ActionEvent e) {
		if (e.getActionCommand().equals("Clear")) {
			graph.clear();
			graph.update();
		}
	}

	// Private instance variables.

	private NameSurferDataBase fullDataBase;
	private NameSurferGraph graph;
	private JTextField inputTextField;
	private NameSurferEntry entry;

}
