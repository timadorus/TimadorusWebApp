package org.timadorus.webapp.client.rpc.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

public interface CreateCharacterServiceAsync  {
	void createCharacter(String input, AsyncCallback<Character> callback);
}