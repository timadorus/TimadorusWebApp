package org.timadorus.webapp.server.rpc.service;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.rpc.service.CreateCharacterService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CreateCharacterServiceImpl extends RemoteServiceServlet implements CreateCharacterService {

  /**
   * character ff charakter
   */
  private static final long serialVersionUID = 1L;
  
  private static final PersistenceManagerFactory PMF =
    JDOHelper.getPersistenceManagerFactory("transactions-optional");



  @Override
  public Character createCharacter(Character name) {
    Character char1 = new Character();
    return char1;
  }
}