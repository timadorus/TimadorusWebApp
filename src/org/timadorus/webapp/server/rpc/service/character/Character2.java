package org.timadorus.webapp.server.rpc.service.character;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.server.RegisteredUserList;

public final class Character2 {
  
  private Character2() {
    
  }

  private static final PersistenceManagerFactory PMF = RegisteredUserList.PMF;

  public static List<Character> getCharacterList(User user) {
    // Get character objects from db
    List<Character> chars = getCharacters(user);
    
    return chars;
  }
  
  @SuppressWarnings("unchecked")
  public static List<Character> getCharacters(User user) {

    try {
      PersistenceManager pm = PMF.getPersistenceManager();

      Extent<Character> extent = pm.getExtent(Character.class, true);
      Query query = pm.newQuery(extent, "username == '" + user.getUsername() + "'");

      List<Character> ret = new ArrayList<Character>();
      for (Character character : (List<Character>) query.execute()) {
        pm.retrieve(character);
        ret.add(pm.detachCopy(character));
      }
      return ret;
    } catch (Exception e) {
      e.printStackTrace();
    }
    
    return null;
  }
  
  @SuppressWarnings("unchecked")
  public static String deleteCharacter(Character character) {
    PersistenceManager pm = PMF.getPersistenceManager();
    Extent<Character> extent = pm.getExtent(Character.class, true);
    Query query = pm.newQuery(extent, "name == '" + character.getName() + "'");
    List<Character> characters = (List<Character>) query.execute();
    Character found = null;
    if (!characters.isEmpty()) {
      found = characters.get(0);
      pm.deletePersistent(found);
      return "OK";
    }    
    return "FAIL";
  }
}
