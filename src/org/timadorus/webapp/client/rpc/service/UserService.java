package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.client.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * UserService interface for GWT-RPC.
 */
// service for getting and deleting from the database and updating users in it
@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {

  /**
   * Returns a User object containing the current available informations of this user.
   * 
   * @param user which shall be returned with current state
   * @return The user in current state
   */
  User getUser(User user);
  
  /**
   * Deletes the user provided as parameter for this method as RPC Service.
   * 
   * @param user The user which shall be deleted
   * @return The status of delete-process User.OK on success, User.USER_INVALID otherwise
   */
  String delete(User user);
  
  /**
   * Updates the user data of a certain user profile.
   * 
   * @param id The id of the user
   * @param user The supplied user data
   * @return An integer value representing the state of the update attempt
   */
  int update(long id, User user);
  
  /**
   * Activates a user using the activation code, which was send as link to the users e-mail address.
   * 
   * @param activationCode The activation code which was send as link to the users e-mail address
   * @param user The supplied user data
   * @return User.USER_ALREADY_ACTIVATED    if the user is already activated, 
   *         User.USER_WRONG_CODE           if the supplied activation code has been incorrect, 
   *         User.USER_INVALID              if the username and password was wrong and
   *         User.USER_VARIFIED             if the user has been successfully verified.  
   */
  String verifyMail(String activationCode, User user);
}
