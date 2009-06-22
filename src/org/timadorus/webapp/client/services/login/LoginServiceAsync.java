package org.timadorus.webapp.client.services.login;



import org.timadorus.webapp.entities.User;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface LoginServiceAsync {

	void login(User user, AsyncCallback<String> asyncCallback);
	
}