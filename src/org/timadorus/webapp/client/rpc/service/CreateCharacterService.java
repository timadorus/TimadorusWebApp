package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.beans.Character;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/** Service for create-Character-Progres.
 * 
 * @author sage
 *
 */
@RemoteServiceRelativePath("createCharacter")
public interface CreateCharacterService extends RemoteService {
  
  /**
   * 
   * @param name name of the new character
   * @return SUCCESS or FAILURE
   */
  String createCharacter(Character name);
  
  /**
   * 
   * @param temp the temp value
   * @return a pot stat
   */
  int makePotStat(int temp);
}
