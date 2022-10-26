/*
 * File: FacePamphletServer.java
 * ------------------------------
 * This program runs a server which hosts the data for a 
 * FacePamphlet internet application. The server stores all 
 * of the data and contains the logic for creating, deleting 
 * profiles and getting and setting profile properties. When 
 * the server receives a requests (which often come from the 
 * client), it updates its internal data, and sends back a string. 
 */

import java.util.*;
import acm.program.*;

public class FacePamphletServer extends ConsoleProgram 
implements SimpleServerListener {

	/* The internet port to listen to requests on */
	private static final int PORT = 8000;

	/* The server object. All you need to do is start it */
	private SimpleServer server = new SimpleServer(this, PORT);


	HashMap<String, FacePamphletProfile> database = new HashMap <String, FacePamphletProfile>();

	/**
	 * Starts the server running so that when a program sends
	 * a request to this computer, the method requestMade is
	 * called.
	 */
	public void run() {
		println("Starting server on port " + PORT);
		server.start();

	}

	/**
	 * When a request is sent to this computer, this method is
	 * called. It must return a String.
	 */
	public String requestMade(Request request) {
		String cmd = request.getCommand();
		println(request.toString());
		

		switch (cmd) {
		case "ping" :
			return "Hello, internet";

		case "addProfile": 
			String name = request.getParam("name");
			String str = addProfile(name);
			return str;
			


		case "containsProfile" : 
			String name2 = request.getParam("name");
			String str1 = containsProfile(name2);
			return str1;
			


		case "deleteProfile" :
			String name3 = request.getParam("name");
			String str2 = deleteProfile(name3);
			return str2;


		case "getStatus" :
			String name4 = request.getParam("name");
			String str3 = getStatus(name4);
			return str3;


		case "setStatus" :
			String name5 = request.getParam("name");
			String status = request.getParam("status");
			String str4 = setStatus(name5, status);
			return str4;
			
		case "getImgFileName" :
			String name6 = request.getParam("name");
			String str5 = getImgFileName(name6);
			return str5;
			
			
		case "setImgFileName" :
			String picName = request.getParam("fileName");
			String name7 = request.getParam("name");
			String str6 = setImgFileName(name7, picName);
			return str6;
			
		case "getFriends" :
			String name8 = request.getParam("name");
			String str7 = getFriends(name8);
			return str7;

			
		case "addFriend":
			String friend1 = request.getParam("name1");
			String friend2 = request.getParam("name2");
			String str8 = addFriend(friend1, friend2);
			return str8;
		
		default: return "Error: Unknown command " + cmd + ".";
		
		}


	}
	
	private String addFriend(String friend1, String friend2) {
		if(!database.containsKey(friend1) || !database.containsKey(friend2)) return "Error: Profile does not exist";
	
		if(friend1.equals(friend2)) return "Error: You can't friend yourself :/";
		
		FacePamphletProfile profile1 = database.get(friend1);
		FacePamphletProfile profile2 = database.get(friend2);
		
		if(areAlreadyFriends(friend1, friend2, profile1, profile2)) return "Error: These users are already Friends";
		
		profile1.addFriend(friend2);
		profile2.addFriend(friend1);
		return "success";
		
	}

	private boolean areAlreadyFriends(String friend1, String friend2, FacePamphletProfile profile1, FacePamphletProfile profile2) {
		List<String> list1 = profile1.getFriends();
		List<String> list2 = profile2.getFriends();
		if(list1.contains(friend2) && list2.contains(friend1)) return true;
		return false;
	}

	private String getFriends(String name) {
		if(database.containsKey(name)) {
			FacePamphletProfile profile = database.get(name);
			List<String> allBudz = profile.getFriends();
			return allBudz.toString();
		
		} else return "Error: Profile does not exist";
		
	}

	private String setImgFileName(String name7, String picName) {
		if(database.containsKey(name7)) {
			FacePamphletProfile profile = database.get(name7);
			profile.setImageFileName(picName);
			return "success";
		} else return "Error: Profile does not exist";
	}
	
	private String getImgFileName(String name6) {
		if(database.containsKey(name6)) {
			FacePamphletProfile profile = database.get(name6);
			return profile.getImageFileName();
			
		} else return "Error: Profile does not exist";
		
	}

	private String setStatus(String name4, String status) {
		if(database.containsKey(name4)) {
			FacePamphletProfile profile = database.get(name4);
			profile.setStatus(status);
			return "success";
		}
		else return "Error: Profile does not exist";

	}

	private String getStatus(String name4) {
		if(database.containsKey(name4)) {
			FacePamphletProfile profile = database.get(name4);
			return profile.getStatus();
		} else return "Error: Profile does not exist";

	}


	private String deleteProfile(String name2) {
		if (!database.containsKey(name2)) {
			return "Error: Profile Does not exist";
		} else {
			FacePamphletProfile profile = database.get(name2);
			List<String> friendsList = profile.getFriends();
			for(int i = 0; i < friendsList.size(); i++) {
				String friendName = friendsList.get(i);
				FacePamphletProfile profile2 = database.get(friendName);
				profile2.removeFriend(name2);
			}
			
			database.remove(name2);
			
			return "success";
		}

	}

	private String containsProfile(String name1) {
		if (database.containsKey(name1)) return "true";
		else return "false";

	}

	private String addProfile(String name) {
		if(!database.containsKey(name)) {
			FacePamphletProfile profile = new FacePamphletProfile(name);
			database.put(name, profile);
			return "success";
		} else return "Error: This profile already exists";

	}

}
