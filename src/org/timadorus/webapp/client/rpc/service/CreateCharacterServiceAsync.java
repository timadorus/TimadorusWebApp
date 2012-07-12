package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.beans.Character;

import com.google.gwt.user.client.rpc.AsyncCallback;

/** Interface for asynchronous "createCharacter" method calls.
 * 
 * @author sage
 *
 */
public interface CreateCharacterServiceAsync {

  /**
   * 
   * @param name name of the character
   * @param callback callback to use
   */
  void createCharacter(Character name, AsyncCallback<String> callback);

  /**
   * 
   * @param temp value of the temp stat
   * @param callback callback to use
   */
  void makePotStat(int temp, AsyncCallback<Integer> callback);
}