package org.timadorus.webapp.server;

import java.util.HashSet;
import java.util.Set;

import org.timadorus.webapp.client.User;


public class UserDataBaseSource implements I_UserDataBaseSource {
	private Set<User> userSet = null;
	
	public UserDataBaseSource() {
		userSet = new HashSet<User>();	
		registerUser(new User("test", "Michael", "Pawlik", "05.09.1985", "dachund@gmail.com", "123"));
//		registerUser(new User("test", "Hans", "Jockel", "06.06.1966", "schmied@ruuth.de", "321"));
		
	}
	
	public Boolean isValidUserPasswordPair(String _userName, String _password) {
		for (User tmpUser : this.userSet) {
			System.out.println(tmpUser.getUserName()+" "+tmpUser.getPassword());
			if (tmpUser.getUserName().equals(_userName) && tmpUser.getPassword().equals(_password))
				return true;
		}
		
		return false;
	}

	public Boolean registerUser(User _userObj) {
		if (this.userSet.contains(_userObj))
			return false;
		
		this.userSet.add(_userObj);
		
		return true;
	}

	public User getUserByName(String _userName) {	
		for (User tmpUser : this.userSet)
			if (tmpUser.getUserName().equals(_userName))
				return tmpUser;
		
		return null;
	}
}
