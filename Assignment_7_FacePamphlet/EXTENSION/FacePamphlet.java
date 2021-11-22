
/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;

import java.awt.Color;
import java.awt.event.*;
import java.util.Iterator;

import javax.swing.*;

public class FacePamphlet extends Program implements FacePamphletConstants {

	/**
	 * This method has the responsibility for initializing the interactors in the
	 * application, and taking care of any other initialization that needs to be
	 * performed.
	 */

	// This method initialises the database, canvas and graphical user interactors.

	public void init() {
		createDataBase();
		createCanvas();

		Color lightGreen = new Color(144, 238, 144);
		Color lightBlue = new Color(173, 216, 230);

		JLabel nameLabel = new JLabel("Name");
		nameField = new JTextField(TEXT_FIELD_SIZE);

		JButton addButton = new JButton("Add");
		addButton.setBackground(lightGreen);
		addButton.setOpaque(true);
		addButton.setBorderPainted(false);

		JButton deleteButton = new JButton("Delete");
		deleteButton.setBackground(Color.PINK);
		deleteButton.setOpaque(true);
		deleteButton.setBorderPainted(false);

		JButton lookupButton = new JButton("Lookup");
		lookupButton.setBackground(lightBlue);
		lookupButton.setOpaque(true);
		lookupButton.setBorderPainted(false);

		JButton changeStatusButton = createChangeStatusOption();
		changeStatusButton.setBackground(Color.WHITE);
		changeStatusButton.setOpaque(true);
		changeStatusButton.setBorderPainted(true);

		JButton changePictureButton = createChangePictureOption();
		changePictureButton.setBackground(Color.WHITE);
		changePictureButton.setOpaque(true);
		changePictureButton.setBorderPainted(true);

		JButton addFriendButton = createAddFriendOption();
		addFriendButton.setBackground(Color.WHITE);
		addFriendButton.setOpaque(true);
		addFriendButton.setBorderPainted(true);

		addHobbies = new JTextField(TEXT_FIELD_SIZE);
		addHobbies.addActionListener(this);
		JButton hobbiesButton = new JButton("Update Hobbies");
		hobbiesButton.setBackground(Color.WHITE);
		hobbiesButton.setOpaque(true);
		hobbiesButton.setBorderPainted(true);

		addInteractors(nameLabel, addButton, deleteButton, lookupButton, changeStatusButton, changePictureButton,
				addFriendButton, hobbiesButton);
		addActionListeners();
	}

	// This method creates an instance of the FacePamphletDatabase class which is a
	// database of profiles.

	private void createDataBase() {
		dataOfProfiles = new FacePamphletDatabase();
	}

	// This method creates an instance of the FacePamphletCanvas class. The canvas
	// is useful for displaying current information on the canvas.

	private void createCanvas() {
		canvas = new FacePamphletCanvas();
		add(canvas);
	}

	// This method creates the text field and button for the "Change Status" option.

	private JButton createChangeStatusOption() {
		changeStatusField = new JTextField(TEXT_FIELD_SIZE);
		changeStatusField.addActionListener(this);
		JButton changeStatusButton = new JButton("Change Status");
		return changeStatusButton;
	}

	// This method creates the text field and button for the "Change Picture"
	// option.

	private JButton createChangePictureOption() {
		changePictureField = new JTextField(TEXT_FIELD_SIZE);
		changePictureField.addActionListener(this);
		JButton changePictureButton = new JButton("Change Picture");
		return changePictureButton;
	}

	// This method creates the text field and button for the "Add Friend" option.

	private JButton createAddFriendOption() {
		addFriendField = new JTextField(TEXT_FIELD_SIZE);
		addFriendField.addActionListener(this);
		JButton addFriendButton = new JButton("Add Friend");
		return addFriendButton;
	}

	// This method is responsible for displaying all graphical user interactors on
	// the window.

	private void addInteractors(JLabel nameLabel, JButton addButton, JButton deleteButton, JButton lookupButton,
			JButton changeStatusButton, JButton changePictureButton, JButton addFriendButton, JButton hobbiesButton) {
		add(nameLabel, NORTH);
		add(nameField, NORTH);
		add(addButton, NORTH);
		add(deleteButton, NORTH);
		add(lookupButton, NORTH);
		add(changeStatusField, WEST);
		add(changeStatusButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(changePictureField, WEST);
		add(changePictureButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(addFriendField, WEST);
		add(addFriendButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		add(addHobbies, WEST);
		add(hobbiesButton, WEST);

	}

	/**
	 * This class is responsible for detecting when the buttons are clicked or
	 * interactors are used, so you will have to add code to respond to these
	 * actions.
	 */

	// The job of this method is to act accordingly to the user's actions.

	public void actionPerformed(ActionEvent e) {
		addButtonIsClicked(e);
		deleteButtonIsClicked(e);
		lookupButtonIsClicked(e);
		changeStatusIsCalled(e);
		changePictureIsCalled(e);
		addFriendIsCalled(e);
		
		if ((e.getSource() == addHobbies || e.getActionCommand().equals("Update Hobbies"))
				&& !addHobbies.getText().equals("")) {
			if (currentProfile != null) {
				currentProfile.setHobbies(addHobbies.getText());
				canvas.displayProfile(currentProfile);
				message = "Status updated to " + addHobbies.getText();
				canvas.showMessage(message);
			} else {
				canvas.removeAllElements();
				message = "Please, select a profile to add hobbies";
				canvas.showMessage(message);
			}
		}

	}

	// This method reacts to the situation when the user clicks the "Add" button.

	private void addButtonIsClicked(ActionEvent e) {
		if (e.getActionCommand().equals("Add") && !nameField.getText().equals("")) {
			if (!dataOfProfiles.containsProfile(nameField.getText())) {
				newProfileIsCreated();
				changeStatusField.setText("");
				changePictureField.setText("");
				addFriendField.setText("");
				addHobbies.setText("");
			} else {
				addingExistingProfile();
				changeStatusField.setText("");
				changePictureField.setText("");
				addFriendField.setText("");
				addHobbies.setText("");
			}
		}
	}

	// This method:
	// - creates a new profile.
	// - add the new profile it to the profiles' database.
	// - updates current profile to the newly created one.
	// - displays newly created profile template on the canvas.
	// - shows a message describing the recent action.

	private void newProfileIsCreated() {
		profile = new FacePamphletProfile(nameField.getText());
		dataOfProfiles.addProfile(profile);
		currentProfile = profile;
		canvas.displayProfile(currentProfile);
		message = "New profile created";
		canvas.showMessage(message);
	}

	// This method reacts to the situation when the user tries to add an already
	// existing profile by updating the canvas to the existing profile and informing
	// the user about it.

	private void addingExistingProfile() {
		currentProfile = dataOfProfiles.getProfile(nameField.getText());
		canvas.displayProfile(currentProfile);
		message = "A profile with the name " + nameField.getText() + " already exists";
		canvas.showMessage(message);
	}

	// This method reacts to the situation when the user clicks the "Delete" button.

	private void deleteButtonIsClicked(ActionEvent e) {
		if (e.getActionCommand().equals("Delete") && !nameField.getText().equals("")) {
			currentProfile = null;
			if (dataOfProfiles.containsProfile(nameField.getText())) {
				profileIsDeleted();
				changeStatusField.setText("");
				changePictureField.setText("");
				addFriendField.setText("");
				addHobbies.setText("");
			} else {
				deletingNonexistentProfile();
				changeStatusField.setText("");
				changePictureField.setText("");
				addFriendField.setText("");
				addHobbies.setText("");
			}
		}
	}

	// This method deletes the profile name entered by the user from the database,
	// clears up the canvas and informs the user about the recent action.

	private void profileIsDeleted() {
		dataOfProfiles.deleteProfile(nameField.getText());
		canvas.removeAllElements();
		message = "Profile of " + nameField.getText() + " deleted";
		canvas.showMessage(message);
	}

	// This method reacts to the situation when the user tries to delete a
	// nonexistent profile by clearing up the canvas and informing them about the
	// situation.

	private void deletingNonexistentProfile() {
		canvas.removeAllElements();
		message = "A profile with the name " + nameField.getText() + " does not exist";
		canvas.showMessage(message);
	}

	// This method reacts to the situation when the user clicks the "Lookup" button.

	private void lookupButtonIsClicked(ActionEvent e) {
		if (e.getActionCommand().equals("Lookup") && !nameField.getText().equals("")) {
			if (dataOfProfiles.containsProfile(nameField.getText())) {
				displayingProperProfile();
				changeStatusField.setText("");
				changePictureField.setText("");
				addFriendField.setText("");
				addHobbies.setText("");
			} else {
				searchingNonexistentProfile();
				changeStatusField.setText("");
				changePictureField.setText("");
				addFriendField.setText("");
				addHobbies.setText("");
			}
		}
	}

	// This method displays the profile on canvas entered by the user and informs
	// them about the recent action.

	private void displayingProperProfile() {
		currentProfile = dataOfProfiles.getProfile(nameField.getText());
		canvas.displayProfile(currentProfile);
		message = "Displaying " + nameField.getText();
		canvas.showMessage(message);
	}

	// This method reacts to the situation when the user tries to look for a
	// nonexistent profile by clearing up the canvas and informing them about the
	// situation.

	private void searchingNonexistentProfile() {
		currentProfile = null;
		canvas.removeAllElements();
		message = "A profile you are looking for with the name " + nameField.getText() + " does not exist";
		canvas.showMessage(message);
	}

	// This method is called when the user enters a non-empty text in the field and
	// clicks on the "Change Status" button or hits the enter key.

	private void changeStatusIsCalled(ActionEvent e) {
		if ((e.getSource() == changeStatusField || e.getActionCommand().equals("Change Status"))
				&& !changeStatusField.getText().equals("")) {
			if (currentProfile != null) {
				updatingProfileStatus();
			} else {
				profileNotChosenForStatus();
			}
		}
	}

	// The job of this method is to update the current status of the current
	// profile. The update is seen on the canvas at once. Also, a message of recent
	// action is shown.

	private void updatingProfileStatus() {
		currentProfile.setStatus(changeStatusField.getText());
		canvas.displayProfile(currentProfile);
		message = "Status updated to " + changeStatusField.getText();
		canvas.showMessage(message);
	}

	// This method reacts to the situation when the user tries to change the status
	// without choosing a current profile by clearing up the canvas and informing
	// them about the situation.

	private void profileNotChosenForStatus() {
		canvas.removeAllElements();
		message = "Please, select a profile to change status";
		canvas.showMessage(message);
	}

	// This method is called when the user enters a non-empty text in the field and
	// clicks on the "Change Picture" button or hits the enter key.

	private void changePictureIsCalled(ActionEvent e) {
		if ((e.getSource() == changePictureField || e.getActionCommand().equals("Change Picture"))
				&& !changePictureField.getText().equals("")) {
			if (currentProfile != null) {
				currentProfileIsChosenForPicture();
			} else {
				profileNotChosenForImage();
			}
		}
	}

	// This method reacts to the situation when the user has chosen a current
	// profile for which they want to change the picture.

	private void currentProfileIsChosenForPicture() {
		GImage image = updateTheImage();
		if (image != null) {
			updateProfilePicture(image);
		} else {
			imageFileNotFound();
		}
	}

	// The job of this method is to find the image file entered by the user.

	private GImage updateTheImage() {
		GImage image = null;
		try {
			image = new GImage(changePictureField.getText());
		} catch (ErrorException ex) {
			ex.printStackTrace();
		}
		return image;
	}

	// The job of this method is to update the picture for the profile. The update
	// is seen on the canvas at once. Also, a message of recent action is shown.

	private void updateProfilePicture(GImage image) {
		currentProfile.setImage(image);
		canvas.displayProfile(currentProfile);
		message = "Picture updated";
		canvas.showMessage(message);
	}

	// This method reacts to the situation when the image name entered by the user
	// could not be found by informing them about the situation.

	private void imageFileNotFound() {
		canvas.displayProfile(currentProfile);
		message = "Unable to open image file: " + changePictureField.getText();
		canvas.showMessage(message);
	}

	// This method reacts to the situation when the user has not chosen the current
	// profile for image update by clearing up the canvas and informing them about
	// the situation.

	private void profileNotChosenForImage() {
		canvas.removeAllElements();
		message = "Please, select a profile to change picture";
		canvas.showMessage(message);
	}

	// This method is called when the user enters a non-empty text in the field and
	// clicks on the "Add Friend" button or hits the enter key.

	private void addFriendIsCalled(ActionEvent e) {
		if ((e.getSource() == addFriendField || e.getActionCommand().equals("Add Friend"))
				&& !addFriendField.getText().equals("")) {
			if (currentProfile != null) {
				currentProfileIsChosenForAddingFriend();
			} else {
				profileNotChosenForAddFriend();
			}
		}
	}

	// This method reacts properly to the situation when the user has chosen a
	// current profile and wants to add a friend to that current profile.

	private void currentProfileIsChosenForAddingFriend() {
		if (dataOfProfiles.containsProfile(addFriendField.getText())) {
			if (!(currentProfile.getName().equals(addFriendField.getText())) && dataOfProfiles.containsProfile(addFriendField.getText()) && currentProfile.contains(addFriendField.getText())) {
				friendAddedToProfile();
			} else {
				addingSameFriendAgain();
			}
		} else {
			friendNotFound();
		}
	}

	// This method reacts to the situation when the user entered an existing profile
	// name in the database by updating the friends' list of both: the "friend
	// request sender" profile and "unasked acceptor" profile. Also, the canvas is
	// updated and a message describing the recent action is shown.

	private void friendAddedToProfile() {
		String currentRelation = "";
		String[] relationTypes = {"Parent", "Sibling", "Friend", "Partner", "Best Friend", "Child"};
		JComboBox askRelation = new JComboBox(relationTypes);
		askRelation.setEditable(true);
		JOptionPane.showMessageDialog(null, askRelation, addFriendField.getText() + " is my...", JOptionPane.QUESTION_MESSAGE);
		currentRelation = (String) askRelation.getSelectedItem();
		dataOfProfiles.getProfile(currentProfile.getRelation(currentRelation));
		dataOfProfiles.getProfile(currentProfile.getName()).addFriend(addFriendField.getText());
		
		if(currentRelation.equals("Parent")) {
			currentRelation = "Child";
		} else if(currentRelation.equals("Sibling")) {
			currentRelation = "Sibling";
		} else if(currentRelation.equals("Friend")) {
			currentRelation = "Friend";
		} else if(currentRelation.equals("Partner")) {
			currentRelation = "Partner";
		} else if(currentRelation.equals("Best Friend")) {
			currentRelation = "Best Friend";
		} else if(currentRelation.equals("Child")) {
			currentRelation = "Parent";
		}
		dataOfProfiles.getProfile(addFriendField.getText()).getRelation(currentRelation);
		dataOfProfiles.getProfile(addFriendField.getText()).addFriend(currentProfile.getName());
		currentRelation ="";
		canvas.displayProfile(currentProfile);
		message = addFriendField.getText() + " added as a friend";
		canvas.showMessage(message);
	}

	// This method reacts to the situation when the user tries to add a friend and
	// that friend is already in the current profile's friends' list or the user
	// tries to add current profile as a friend to themselves.

	private void addingSameFriendAgain() {
		canvas.displayProfile(currentProfile);
		message = currentProfile.getName() + " already has " + addFriendField.getText() + " added as a friend";
		canvas.showMessage(message);
	}

	// This method reacts to the situation when the user tries to add a nonexistent
	// profile as a friend by informing them about it.

	private void friendNotFound() {
		canvas.displayProfile(currentProfile);
		message = addFriendField.getText() + " does not exist";
		canvas.showMessage(message);
	}

	// This method reacts to the situation when the user has not chosen the current
	// profile to add a friend to by clearing up the canvas and informing them about
	// the situation.

	private void profileNotChosenForAddFriend() {
		canvas.removeAllElements();
		message = "Please, select a profile to add friend";
		canvas.showMessage(message);
	}

	// private instance variables:

	private FacePamphletDatabase dataOfProfiles;
	private FacePamphletCanvas canvas;
	private JTextField addHobbies;
	private JTextField nameField;
	private JTextField changeStatusField;
	private JTextField changePictureField;
	private JTextField addFriendField;
	private FacePamphletProfile profile;
	private FacePamphletProfile currentProfile;
	private String message;

}
