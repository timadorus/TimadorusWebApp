package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.client.character.Character;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

//Service for create-Character-Progress

@RemoteServiceRelativePath("createCharacter")
public interface CreateCharacterService extends RemoteService {
  String createCharacter(Character name);
}
