package org.timadorus.webapp.client.rpc.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CreateCharakterServiceAsync {
	void createCharakter(String input, AsyncCallback<String> callback);
}