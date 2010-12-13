package org.timadorus.webapp.client.rpc.service;

import java.util.List;

import org.timadorus.webapp.client.User;
import org.timadorus.webapp.client.character.Character;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

// service for Login
@RemoteServiceRelativePath("character")
public interface CharacterService extends RemoteService {

  List<Character> getCharacterList(User user);
  
}