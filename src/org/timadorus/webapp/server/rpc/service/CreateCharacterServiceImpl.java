package org.timadorus.webapp.server.rpc.service;

import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.rpc.service.CreateCharacterService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CreateCharacterServiceImpl extends RemoteServiceServlet implements CreateCharacterService {

  /**
   * character ff charakter
   */
  private static final long serialVersionUID = 1L;

  @Override
  public Character createCharacter(String name) {
    Character char1 = new Character();
    return char1;
  }
}