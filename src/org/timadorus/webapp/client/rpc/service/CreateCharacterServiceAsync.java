package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.client.Character;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CreateCharacterServiceAsync {
  void createCharacter(String input, AsyncCallback<Character> callback);
}