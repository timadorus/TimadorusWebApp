package org.timadorus.webapp.client.rpc.service;

import java.util.List;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

// service for Login
@RemoteServiceRelativePath("character")
public interface CharacterService extends RemoteService {

  List<Character> getCharacterList(User user);
  
  String deleteCharacter(Character character);
  
}