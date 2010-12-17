package org.timadorus.webapp.client.rpc.service;

import java.util.List;

import org.timadorus.webapp.client.User;
import org.timadorus.webapp.client.character.Character;

import com.google.gwt.user.client.rpc.AsyncCallback;

//Interface for Asyncronous "character"-Method-Calls (RPC-calls) between client and server
public interface CharacterServiceAsync {

  void getCharacterList(User user, AsyncCallback<List<Character>> asyncCallback);
  
  void deleteCharacter(Character character, AsyncCallback<String> asyncCallback);

}