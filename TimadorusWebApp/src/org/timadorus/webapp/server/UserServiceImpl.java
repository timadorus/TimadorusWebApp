package org.timadorus.webapp.server;

import org.timadorus.webapp.client.User;
import org.timadorus.webapp.client.UserService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class UserServiceImpl extends RemoteServiceServlet implements UserService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 *	Reference to DataBase in which Userinformations are stored. 
	 */
	
	
	private final I_UserDataBaseSource dataBaseSource = new UserDataBaseSource();
		
	public Boolean loginUserWithPassword(String _username, String _password) {
		return this.dataBaseSource.isValidUserPasswordPair(_username, _password);
	}

	public User getUserInformation(String _userName) {
		return this.dataBaseSource.getUserByName(_userName);
	}

	public Boolean registerUser(User _userObj) {
		return this.dataBaseSource.registerUser(_userObj);
	}

}
