package org.timadorus.webapp.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
	void loginUserWithPassword(String _userName, String _password, AsyncCallback<Boolean> callback);
	void getUserInformation(String _userName, AsyncCallback<User> callback);
	void registerUser(User _userObj, AsyncCallback<Boolean> callback);
	
}
