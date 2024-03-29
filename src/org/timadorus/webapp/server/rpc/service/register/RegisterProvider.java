package org.timadorus.webapp.server.rpc.service.register;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.server.RegisteredUserList;
import org.timadorus.webapp.server.Util;

public final class RegisterProvider {

  private RegisterProvider() {
  }

  /**
   * Registers a user supplied as dataIn parameter.
   * 
   * @param dataIn
   *          The user which shall be registered
   * @return Return value of this.isValid(dataIn)
   */
  public static String register(User dataIn) {
    System.out.println("Register aufgerufen");
    int value = isValid(dataIn);
    String activationCode = null;
    if (value == User.OK) {
      RegisteredUserList userList = RegisteredUserList.getInstance();
      activationCode = userList.addUser(dataIn);
      if (activationCode == null) {
        value = User.USERNAME_FAULT;
      }
    }
    if (activationCode == null) {
      return String.valueOf(value);
    } else {
      return String.valueOf(value) + "_" + activationCode;
    }
  }

  /**
   * Checks if a user is valid in registration content.
   * 
   * @param user
   *          The supplied user data
   * @return An error code on failure, User.OK otherwise
   */
  private static int isValid(User user) {
    int out = User.OK;
    out += Util.checkBirthday(user.getGeburtstag());
    out += Util.checkUsernameFree(user.getUsername());
    out += Util.checkEmailAdresse(user);
    return out;
  }
}
