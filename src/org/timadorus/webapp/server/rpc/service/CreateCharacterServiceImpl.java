package org.timadorus.webapp.server.rpc.service;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.rpc.service.CreateCharacterService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class CreateCharacterServiceImpl extends RemoteServiceServlet implements CreateCharacterService {

  /**
   * 
   */
  private static final long serialVersionUID = 2463839761006931303L;
  /**
   * character ff charakter
   */
  
  private static final PersistenceManagerFactory PMF =
    JDOHelper.getPersistenceManagerFactory("transactions-optional");



  @Override
  public Character createCharacter(Character name) {
    Character char1 = Character.getInstance();
    return char1;
  }
}