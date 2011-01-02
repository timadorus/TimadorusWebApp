package org.timadorus.webapp.server.rpc.service;

import org.timadorus.webapp.client.User;
import org.timadorus.webapp.client.rpc.service.RegisterService;
import org.timadorus.webapp.server.RegisteredUserList;
import org.timadorus.webapp.server.Util;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class RegisterServiceImpl extends RemoteServiceServlet implements RegisterService {

  private static final long serialVersionUID = 270628040929463623L;

  public String register(User dataIn) {
    System.out.println("Register aufgerufen");
    int value = isValid(dataIn);
    if (value == User.OK) {
      RegisteredUserList userList = RegisteredUserList.getInstance();
      Boolean added = userList.addUser(dataIn);
      if (!added) {
        value = User.USERNAME_FAULT;
      }
    }
    return String.valueOf(value);
  }
  
  private static int isValid(User user) {
    int out = User.OK;
    out += Util.checkBirthday(user.getGeburtstag());
    out += Util.checkUsernameFree(user.getUsername());
    out += Util.checkEmailAdresse(user);
    return out;
  }
}