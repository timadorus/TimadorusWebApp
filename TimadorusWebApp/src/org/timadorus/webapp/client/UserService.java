package org.timadorus.webapp.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {
	Boolean loginUserWithPassword(String _userName, String _password);
	Boolean registerUser(User _userObj);
	User getUserInformation(String _userName);
}
