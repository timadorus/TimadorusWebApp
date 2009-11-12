package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.client.character.Character;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("createCharacter")
public interface CreateCharacterService extends RemoteService {
  Character createCharacter(Character name);
}
