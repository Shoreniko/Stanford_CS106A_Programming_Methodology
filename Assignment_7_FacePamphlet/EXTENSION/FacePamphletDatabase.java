
/*
 * File: FacePamphletDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */

import java.util.*;

public class FacePamphletDatabase implements FacePamphletConstants {

	/**
	 * Constructor This method takes care of any initialization needed for the
	 * database.
	 */

	// This method creates a new hash map for the list of all profiles.

	public FacePamphletDatabase() {
		listOfProfiles = new HashMap<String, FacePamphletProfile>();
	}

	/**
	 * This method adds the given profile to the database. If the name associated
	 * with the profile is the same as an existing name in the database, the
	 * existing profile is replaced by the new profile passed in.
	 */

	// The job of this method is to add a newly created profile to the profiles'
	// data base.

	public void addProfile(FacePamphletProfile profile) {
		listOfProfiles.put(profile.getName(), profile);
	}

	/**
	 * This method returns the profile associated with the given name in the
	 * database. If there is no profile in the database with the given name, the
	 * method returns null.
	 */

	// The job of this method is to check whether the data base contains the profile
	// with the proper name. This method returns a FacePamphletProfile class object,
	// if that profile exists in the database, otherwise, it returns a null value.

	public FacePamphletProfile getProfile(String name) {
		if (listOfProfiles.containsKey(name)) {
			return listOfProfiles.get(name);
		}
		return null;
	}

	/**
	 * This method removes the profile associated with the given name from the
	 * database. It also updates the list of friends of all other profiles in the
	 * database to make sure that this name is removed from the list of friends of
	 * any other profile.
	 * 
	 * If there is no profile in the database with the given name, then the database
	 * is unchanged after calling this method.
	 */

	// The job of this method is to delete a profile from the database. Also, this
	// method deletes the profile from the friends' list of any other profiles if
	// the deleted one was in their friends' list.
	
	public void getFriendsList (FacePamphletProfile profile) {
		ArrayList<String> friendsList = profile.giveFriendsList();
	}

	public void deleteProfile(String name) {
		int strLength = name.length();
		Iterator<Map.Entry<String, FacePamphletProfile>> iterateThroughNames = listOfProfiles.entrySet().iterator();
		if (listOfProfiles.containsKey(name)) {
			while (iterateThroughNames.hasNext()) {
				Map.Entry<String, FacePamphletProfile> profile = iterateThroughNames.next();
				if (!profile.getKey().equals(name)) {
					if(profile.getKey().substring(0, strLength - 1).equals(name)) {
						profile.getValue().removeFriend(name);
					}
				}
			}
			listOfProfiles.remove(name);
		}
	}

	/**
	 * This method returns true if there is a profile in the database that has the
	 * given name. It returns false otherwise.
	 */

	// The job of this method is to check whether the list of profiles contains a
	// certain profile with the proper name.

	public boolean containsProfile(String name) {
		if (listOfProfiles.containsKey(name)) {
			return true;
		}
		return false;
	}

	// Private instance variable:
	
	private HashMap<String, FacePamphletProfile> listOfProfiles;

}
