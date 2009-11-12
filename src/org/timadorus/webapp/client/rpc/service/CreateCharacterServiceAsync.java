package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.client.character.Character;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface CreateCharacterServiceAsync {
//  void createCharacter(String input, AsyncCallback<Character> callback);

  void createCharacter(Character name, AsyncCallback<Character> callback);
}