package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.beans.Character;

import com.google.gwt.user.client.rpc.AsyncCallback;

//Interface for Asyncronous "createCharacter"-Method-Calls (RPC-calls) between client and server
public interface CreateCharacterServiceAsync {
//  void createCharacter(String input, AsyncCallback<Character> callback);

  void createCharacter(Character name, AsyncCallback<String> callback);

  void makePotStat(int temp, AsyncCallback<Integer> callback);
}