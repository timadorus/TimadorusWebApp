package org.timadorus.webapp.client.services.createcharacter;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CreateCharacterServiceAsync {
	void createCharacter(String input, AsyncCallback<String> callback);
}