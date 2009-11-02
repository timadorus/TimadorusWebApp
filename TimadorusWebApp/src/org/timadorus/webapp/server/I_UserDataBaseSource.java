package org.timadorus.webapp.server;

import org.timadorus.webapp.client.User;

public interface I_UserDataBaseSource {
	Boolean isValidUserPasswordPair(String _username, String _password);	
	Boolean registerUser(User _userObj);
	User	getUserByName(String _userName);
}
