
/*
 * File: FacePamphletProfile.java
 * ------------------------------
 * This class keeps track of all the information for one profile
 * in the FacePamphlet social network.  Each profile contains a
 * name, an image (which may not always be set), a status (what 
 * the person is currently doing, which may not always be set),
 * and a list of friends.
 */

import acm.graphics.*;
import java.util.*;

public class FacePamphletProfile implements FacePamphletConstants {

	/**
	 * Constructor This method takes care of any initialization needed for the
	 * profile.
	 */

	// This method creates a new list of friends for the current profile.

	public FacePamphletProfile(String name) {
		friendsList = new ArrayList<String>();
		profileName = name;
	}

	/** This method returns the name associated with the profile. */

	// This method returns the name of the current profile.

	public String getName() {
		return profileName;
	}

	/**
	 * This method returns the image associated with the profile. If there is no
	 * image associated with the profile, the method returns null.
	 */

	// This method returns the image of the current profile.

	public GImage getImage() {
		return profileImage;
	}

	/** This method sets the image associated with the profile. */

	// The job of this method is to set the image of the current profile.

	public void setImage(GImage image) {
		profileImage = image;
	}

	/**
	 * This method returns the status associated with the profile. If there is no
	 * status associated with the profile, the method returns the empty string ("").
	 */

	// This method returns the status of the current profile.

	public String getStatus() {
		return profileStatus;
	}

	/** This method sets the status associated with the profile. */

	// The job of this method is to set the status of the current profile.

	public void setStatus(String status) {
		profileStatus = status;
	}

	/**
	 * This method adds the named friend to this profile's list of friends. It
	 * returns true if the friend's name was not already in the list of friends for
	 * this profile (and the name is added to the list). The method returns false if
	 * the given friend name was already in the list of friends for this profile (in
	 * which case, the given friend name is not added to the list of friends a
	 * second time.)
	 */

	// The job of this method is to add a friend to the current profile if they are
	// not friends yet. If so, this method return the value of true, else - false.

	public boolean addFriend(String friend) {
		if (friendsList.contains(friend) || profileName.equals(friend)) {
			return false;
		} else {
			friendsList.add(friend + relation);
		}
		return true;
	}

	public String getRelation(String currentRelation) {
		if (!currentRelation.equals("")) {
			relation = " (" + currentRelation + ")";
		} else {
			relation = currentRelation;
		}
		return relation;
	}

	/**
	 * This method removes the named friend from this profile's list of friends. It
	 * returns true if the friend's name was in the list of friends for this profile
	 * (and the name was removed from the list). The method returns false if the
	 * given friend name was not in the list of friends for this profile (in which
	 * case, the given friend name could not be removed.)
	 */

	// The job of this method is to remove a friend of the current profile from
	// their friends' list. This method returns the defauls value of false.

	public boolean removeFriend(String friend) {
		for(int i = 0; i < friendsList.size(); i++) {
			int strLength = friend.length();
			if(friendsList.get(i).substring(0, strLength - 1).equals(friend)) {
				friendsList.remove(friend);
			}
		}
		return false;
	}

	public ArrayList<String> giveFriendsList() {
		// TODO Auto-generated method stub
		return friendsList;
	}
	
	/**
	 * This method returns an iterator over the list of friends associated with the
	 * profile.
	 */

	// The job of this method is to iterate through the list of all the friends of
	// the current profile.

	public Iterator<String> getFriends() {
		return friendsList.iterator();
	}

	/**
	 * This method returns a string representation of the profile. This string is of
	 * the form: "name (status): list of friends", where name and status are set
	 * accordingly and the list of friends is a comma separated list of the names of
	 * all of the friends in this profile.
	 * 
	 * For example, in a profile with name "Alice" whose status is "coding" and who
	 * has friends Don, Chelsea, and Bob, this method would return the string:
	 * "Alice (coding): Don, Chelsea, Bob"
	 */

	// This method is not being used, however it is practical for checking whether
	// the program works properly or not. This method displays all the information
	// about the current user in a user-friendly (i.e. string) form.

	public boolean contains(String nameOfPossibleFriend) {
		if(friendsList.contains(nameOfPossibleFriend)) {
			return false;
		} else {
			return true;
		}
	}

	public String toString() {
		String friendsToString = "";
		for (int i = 0; i < friendsList.size(); i++) {
			if (i != friendsList.size() - 1) {
				friendsToString += friendsList.get(i) + " ";
			} else {
				friendsToString += friendsList.get(i);
			}
		}
		return profileName + " (" + profileStatus + "): " + friendsToString;
	}

	public String setHobbies(String hobbies) {
		hobbiesList = hobbies;
		return hobbiesList;
	}

	public String getHobbies() {
		return hobbiesList;
	}

	// Private instance variables:

	private String relation = "";
	private String hobbiesList = "";
	private Iterator<String> allFriends;
	private ArrayList<String> friendsList;
	private GImage profileImage = null;
	private String profileName;
	private String profileStatus = "";


}
