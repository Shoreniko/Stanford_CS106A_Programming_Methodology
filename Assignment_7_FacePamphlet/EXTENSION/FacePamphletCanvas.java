
/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants {

	/**
	 * Constructor This method takes care of any initialization needed for the
	 * display
	 */
	public FacePamphletCanvas() {
		background = new GImage("background.jpg");
		background.setBounds(0, 0, APPLICATION_WIDTH, APPLICATION_HEIGHT);
		add(background);
	}
	
	public void removeAllElements() {
		removeAll();
		background = new GImage("background.jpg");
		background.setBounds(0, 0, APPLICATION_WIDTH, APPLICATION_HEIGHT);
		add(background);
	}

	/**
	 * This method displays a message string near the bottom of the canvas. Every
	 * time this method is called, the previously displayed message (if any) is
	 * replaced by the new message text passed in.
	 */

	// This method displays the current message (which describes the recent action
	// performed by the user) on the canvas.

	public void showMessage(String msg) {
		messageLabel = new GLabel(msg);
		messageLabel.setFont(MESSAGE_FONT);
		messageLabel.setLocation((getWidth() - (messageLabel.getWidth())) / 2, getHeight() - BOTTOM_MESSAGE_MARGIN);
		add(messageLabel);
	}

	/**
	 * This method displays the given profile on the canvas. The canvas is first
	 * cleared of all existing items (including messages displayed near the bottom
	 * of the screen) and then the given profile is displayed. The profile display
	 * includes the name of the user from the profile, the corresponding image (or
	 * an indication that an image does not exist), the status of the user, and a
	 * list of the user's friends in the social network.
	 */

	// The job of this method is to display the current profile on the canvas.

	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		add(background);
		GLabel nameLabel = addNameLabel(profile);
		displayingTheImage(profile, nameLabel);
		displayingTheStatus(profile, nameLabel);
		
		if (profile.getHobbies().equals("")) {
			GLabel emptyHobbyLabel = new GLabel("No hobbies yet");
			emptyHobbyLabel.setFont(PROFILE_STATUS_FONT);
			emptyHobbyLabel.setLocation(LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN + nameLabel.getAscent() + IMAGE_HEIGHT
					+ STATUS_MARGIN + emptyHobbyLabel.getAscent() + 20);
			add(emptyHobbyLabel);
		} else {
			GLabel hobbyLabel = new GLabel(profile.getName() + "'s hobbies are: " + profile.getHobbies());
			hobbyLabel.setFont(PROFILE_STATUS_FONT);
			hobbyLabel.setLocation(LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN + nameLabel.getAscent() + IMAGE_HEIGHT
					+ STATUS_MARGIN + hobbyLabel.getAscent() + 20);
			add(hobbyLabel);
		}
		
		GLabel friendsLabel = addFriendsLabel(nameLabel);
		displayingFriendsList(profile, nameLabel, friendsLabel);
	}

	// This method adds the profile name to the canvas.

	private GLabel addNameLabel(FacePamphletProfile profile) {
		GLabel nameLabel = new GLabel(profile.getName());
		nameLabel.setFont(PROFILE_NAME_FONT);
		nameLabel.setColor(Color.BLUE);
		nameLabel.setLocation(LEFT_MARGIN, TOP_MARGIN + nameLabel.getAscent());
		add(nameLabel);
		return nameLabel;
	}

	// This method displays the image on the canvas.

	private void displayingTheImage(FacePamphletProfile profile, GLabel nameLabel) {
		if (profile.getImage() == null) {
			GImage defaultImage = new GImage("default_user.jpg");
			defaultImage.setBounds(LEFT_MARGIN, TOP_MARGIN + nameLabel.getAscent() + IMAGE_MARGIN, IMAGE_WIDTH,
				IMAGE_HEIGHT);
			add(defaultImage);
		} else {
			addProfileImage(profile, nameLabel);
		}
	}

	// This method adds a real image to the canvas.

	private void addProfileImage(FacePamphletProfile profile, GLabel nameLabel) {
		GImage profileImage = profile.getImage();
		profileImage.setBounds(LEFT_MARGIN, TOP_MARGIN + nameLabel.getAscent() + IMAGE_MARGIN, IMAGE_WIDTH,
				IMAGE_HEIGHT);
		add(profileImage);
	}

	// This method displays the status bar on the canvas.

	private void displayingTheStatus(FacePamphletProfile profile, GLabel nameLabel) {
		if (profile.getStatus().equals("")) {
			addEmptyStatusLabel(nameLabel);
		} else {
			addStatusLabel(profile, nameLabel);
		}
	}

	// This method adds an empty status label when there is no current status to the
	// canvas.

	private void addEmptyStatusLabel(GLabel nameLabel) {
		GLabel emptyStatusLabel = new GLabel("No current status");
		emptyStatusLabel.setFont(PROFILE_STATUS_FONT);
		emptyStatusLabel.setLocation(LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN + nameLabel.getAscent() + IMAGE_HEIGHT
				+ STATUS_MARGIN + emptyStatusLabel.getAscent());
		add(emptyStatusLabel);
	}

	// This method adds a real status label to the canvas.

	private void addStatusLabel(FacePamphletProfile profile, GLabel nameLabel) {
		GLabel statusLabel = new GLabel(profile.getName() + " is " + profile.getStatus());
		statusLabel.setFont(PROFILE_STATUS_FONT);
		statusLabel.setLocation(LEFT_MARGIN, TOP_MARGIN + IMAGE_MARGIN + nameLabel.getAscent() + IMAGE_HEIGHT
				+ STATUS_MARGIN + statusLabel.getAscent());
		add(statusLabel);
	}

	// This method adds a label of friends to the canvas.

	private GLabel addFriendsLabel(GLabel nameLabel) {
		GLabel friendsLabel = new GLabel("Friends:");
		friendsLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
		friendsLabel.setLocation(getWidth() / 2, TOP_MARGIN + IMAGE_MARGIN + nameLabel.getAscent());
		add(friendsLabel);
		return friendsLabel;
	}

	// This method adds the list of current profile's friends to the canvas.

	private void displayingFriendsList(FacePamphletProfile profile, GLabel nameLabel, GLabel friendsLabel) {
		int tracker = 1;
		Iterator<String> iterateThroughFriends = profile.getFriends();
		if (!iterateThroughFriends.equals(null)) {
			while (iterateThroughFriends.hasNext()) {
				String friendName = iterateThroughFriends.next();
				GLabel friendNameLabel = new GLabel(friendName);
				friendNameLabel.setFont(PROFILE_FRIEND_FONT);
				friendNameLabel.setLocation(getWidth() / 2,
						TOP_MARGIN + IMAGE_MARGIN + nameLabel.getAscent() + tracker * (friendsLabel.getAscent()));
				add(friendNameLabel);
				tracker++;
			}
		}
	}

	// private instance variable:

	private GImage background;
	private GLabel messageLabel;

}
