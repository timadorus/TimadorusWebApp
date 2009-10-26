package org.timadorus.webapp.client.rpc.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ToonServiceAsync {
	void createCharacter(String input, AsyncCallback<Character> callback);
}