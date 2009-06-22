package org.timadorus.webapp.client.services.registerUser;

import org.timadorus.webapp.entities.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface RegisterServiceAsync {

	void register(User data, AsyncCallback<String> asyncCallback);
	
}