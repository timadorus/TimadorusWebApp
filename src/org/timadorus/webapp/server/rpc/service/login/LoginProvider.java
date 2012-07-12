package org.timadorus.webapp.server.rpc.service.login;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.server.RegisteredUserList;

/**
 * 
 * @author sage
 *
 */
public final class LoginProvider {
  
  private final RegisteredUserList userList;

  /**
   * 
   * @param userList the user list instance to use
   */
  public LoginProvider(RegisteredUserList userList) {
    this.userList = userList;
  }
  
  /**
   * 
   * @param tmpUser user to log in
   * @return USER_VERIFIED if the user was successfully logged in, USER_INACTIVE if the credentials 
   *         where correct, but the account is inactive, USER_INVALID otherwise
   */
  public String login(User tmpUser) {
    
    System.out.println("Login aufgerufen");
    if (tmpUser == null) { return null; }
    if (userList.isValid(tmpUser)) {
      if (userList.isActive(tmpUser)) {         
         return User.USER_VERIFIED; 
      }
      return User.USER_INACTIVE;
    }
    return User.USER_INVALID;    
  }
}
