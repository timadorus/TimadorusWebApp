package org.timadorus.webapp.server;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.jdo.Extent;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.timadorus.webapp.client.User;

public class RegisteredUserList {

	private static final PersistenceManagerFactory PMF =
	      JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	private HashMap<String, User> users = new HashMap<String, User>();
	private static RegisteredUserList userList = null;
	
	/**
	 * Konstruktor muss PRIVATE bleiben -> Singelton-Pattern
	 */
	private RegisteredUserList() {}

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
		if(!users.containsKey(user.getUsername())) {
			// User im Datastore suchen
			User found = getUser(user.getUsername());
			if(found.isValid()) {
				users.put(found.getUsername(), found);
			} else {
				System.out.println("Datastore: keine Treffer für '" + user.getDisplayname() + "'");
				return false;
			}
		}
		if(users.get(user.getUsername()).getPassword().equals(user.getPassword())) {
			return true;
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
		if(!users.containsKey(username.toLowerCase())) {
			// User im Datastore suchen
			if(!getUser(username).isValid()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Die Methode versucht einen User anhand eines Usernamens aus dem Datastore zu laden.
	 * Ist kein User mit dem angegebenen Namen vorhanden, wird ein "leerer" User zurückgegeben.
	 * Somit kann mit Methode "isValid" des User-Objekts geprüft werden, ob der User vorhanden ist.
	 * Somit entfällt die Prüfung auf "null". 
	 * 
	 * @param username
	 * @return User aus Datastore, wenn vorhanden; "leeren" User sonst
	 */
	@SuppressWarnings("unchecked")
	private User getUser(String username) {
		username = username.toLowerCase();
		PersistenceManager pm = PMF.getPersistenceManager();
		Extent<User> extent = pm.getExtent(User.class, true);

		Query query = pm.newQuery(extent, "username == name");
		query.declareParameters("String name");
		
		Iterator<User> iterator = ((Collection<User>) query.execute(username)).iterator();
		if(iterator.hasNext()) {
			User found = iterator.next();
			System.out.println("Datastore: '" + found.getDisplayname() + "' wurde geladen...");
			return found;
		}
		return new User();// "leerer" User; isValid liefert "false";
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
			PersistenceManager pm = PMF.getPersistenceManager();
			pm.makePersistent(user);
			System.out.println("Datastore: '" + user.getDisplayname() + "' hinzugefügt...");
			users.put(user.getUsername(), user);
			return true;
		}
		return false;
	}

	public void print() {
		System.out.println("Derzeit geladene User:");
		for (String user : users.keySet()) {
			System.out.println(user);
		}
	}
}
