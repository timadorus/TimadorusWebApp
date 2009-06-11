package org.timadorus.webapp.server;

import java.util.HashMap;

import org.timadorus.webapp.client.User;

public class RegisteredUserList {

	private HashMap<String, String> users = new HashMap<String, String>();
	private static RegisteredUserList userList = null;
	
	private RegisteredUserList() {
		addUser(new User("test", "test", "test", "test", "test", "test"));
	}

	/**
	 * Singelton-Pattern
	 * 
	 * @return Single-Instance
	 */
	public static RegisteredUserList getInstance() {
		if(userList == null) {
			userList = new RegisteredUserList();
		}
		return userList;
	}
	
	/**
	 * Diese Methode überprüft Username und Passwort des übergebenen Users.
	 * 
	 * @param user
	 * @return true, wenn Username und Passwort gültig, false sonst
	 */
	public Boolean isValid(User user) {
		for (String entry : users.keySet()) {
			if(entry.equals(user.getUsername().toLowerCase())) {
				if(users.get(entry).equals(user.getPassword())) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Diese Methode prüft, ob ein Username bereits vergeben ist.
	 * 
	 * @param username
	 * @return false, wenn Username bereits vergeben, true sonst
	 */
	public Boolean usernameAvailable(String username) {
		if(users.containsKey(username.toLowerCase())) {
			return false;
		}
		return true;
	}
	
	/**
	 * Die Methode fügt einen User zu der Liste der registrierten User hinzu.
	 * Dabei wird der Username zwecks verbesserter Vergleichsmöglichkeit als
	 * "lowerCase" gespeichert.
	 * 
	 * @param user
	 * @return false, wenn Username bereits vergeben, true sonst
	 */
	public Boolean addUser(User user) {
		if(usernameAvailable(user.getUsername())) {
			users.put(user.getUsername().toLowerCase(), user.getPassword());
			return true;
		}
		return false;
	}

	public void print() {
		for (String user : users.keySet()) {
			System.out.println(user);
		}
	}
}
