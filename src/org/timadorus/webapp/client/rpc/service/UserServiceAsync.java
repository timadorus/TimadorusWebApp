package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.client.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * UserServiceAsync interface for GWT-RPC.
 */
//Interface for Asyncronous user related method calls (RPC-calls) between client and server
public interface UserServiceAsync {

  /**
   * Wrapper method for RPC communication.
   * Returns a User object containing the current available informations of this user to the callback.
   * 
   * @param user which shall be returned with current state
   * @param callback The callback class which will be triggered if the service has finished
   */
  void getUser(User user, AsyncCallback<User> callback);
  
  /**
   * Wrapper method for RPC communication.
   * Deletes the user provided as parameter for this method as RPC Service.
   * 
   * @param user The user which shall be deleted
   * @param callback The callback class which will be triggered if the service has finished
   */
  void delete(User user, AsyncCallback<String> callback);
  
  /**
   * Wrapper method for RPC communication.
   * Updates the user data of a certain user profile.
   * 
   * @param id The id of the user
   * @param user The supplied user data
   * @param callback The callback class which will be triggered if the service has finished
   */
  void update(long id, User user, AsyncCallback<Integer> callback);
  
  /**
   * Wrapper method for RPC communication.
   * Activates a user using the activation code, which was send as link to the users e-mail address.
   * 
   * @param activationCode The activation code which shall be checked
   * @param user which shall be verified
   * @param callback The callback class which will be triggered if the service has finished
   */
  void verifyMail(String activationCode, User user, AsyncCallback<String> callback);
}
