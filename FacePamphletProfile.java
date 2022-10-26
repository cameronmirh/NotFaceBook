/*
 * File: FacePamphletProfile.java
 * ------------------------------
 * This class keeps track of all the information for one profile
 * in the FacePamphlet social network.  Each profile contains a
 * name, an image (which may not always be set), a status (what 
 * the person is currently doing, which may not always be set),
 * and a list of friends.
 */

import java.util.*;

public class FacePamphletProfile {

	/** Instance Variables*/
	private String name;
	private String status;
	private String picFileName;
	ArrayList<String> friends = new ArrayList<String>();
	
	
	
	
	public FacePamphletProfile(String name) {
		picFileName = "";
		status = "";
		this.name = name;
		
	}

	/** This method returns the name associated with the profile. */ 
	public String getName() {
		return name;
	}


	public String getImageFileName() {
		return picFileName;
	}

	/** This method sets the image associated with the profile. */ 
	public void setImageFileName(String fileName) {
		picFileName = fileName; 
	}

	/** 
	 * This method returns the status associated with the profile.
	 * If there is no status associated with the profile, the method
	 * returns the empty string ("").
	 */ 
	public String getStatus() {
		return status;
	}

	/** This method sets the status associated with the profile. */ 
	public void setStatus(String s) {
		status = s;
	}

	/** 
	 * This method adds the named friend to this profile's list of 
	 * friends.  It returns true if the friend's name was not already
	 * in the list of friends for this profile (and the name is added 
	 * to the list).  The method returns false if the given friend name
	 * was already in the list of friends for this profile (in which 
	 * case, the given friend name is not added to the list of friends 
	 * a second time.)
	 */
	public boolean addFriend(String friend) {
		if(friends.contains(friend)) {
			return false;
		} else {
			friends.add(friend);
			return true;
		}
	}

	/** 
	 * This method removes the named friend from this profile's list
	 * of friends.  It returns true if the friend's name was in the 
	 * list of friends for this profile (and the name was removed from
	 * the list).  The method returns false if the given friend name 
	 * was not in the list of friends for this profile (in which case,
	 * the given friend name could not be removed.)
	 */
	public boolean removeFriend(String friend) {
		if(friends.contains(friend)) {
			int x = friends.indexOf(friend);
			friends.remove(x);
			return true;
		} else {
			return false;
		}
	}

	/** 
	 * This method returns a list of friends 
	 * associated with the profile.
	 */ 
	public List<String> getFriends() {
		return friends;
		
	}

}
