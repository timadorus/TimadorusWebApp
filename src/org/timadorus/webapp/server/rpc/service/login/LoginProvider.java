package org.timadorus.webapp.server.rpc.service.login;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.server.RegisteredUserList;

public final class LoginProvider {
  
  private LoginProvider() {    
  }
  
//TODO
  public static String login(User tmpUser) {
    RegisteredUserList userList = RegisteredUserList.getInstance();
    
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
