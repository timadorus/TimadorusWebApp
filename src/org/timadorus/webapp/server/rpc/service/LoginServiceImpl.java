package org.timadorus.webapp.server.rpc.service;

import javax.servlet.http.HttpSession;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.rpc.service.LoginService;
import org.timadorus.webapp.server.RegisteredUserList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * Implementing LoginService according to GWT-RPC-Plumbing-diagram.
 */
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

  private static final long serialVersionUID = 270628040929463623L;

  private static RegisteredUserList userList = RegisteredUserList.getInstance();

  /**
   * Logs a certain user in.
   * 
   * @param tmpUser The user which shall be logged in
   * @return User.USER_INVALID  if the username password combination was invalid,
   *         User.USER_INACTIVE if the user wasn't verified up to now,
   *         The current session id otherwise on success.   
   */
  public String login(User tmpUser) {
    System.out.println("Login aufgerufen");
    if (tmpUser == null) { return null; }
    if (userList.isValid(tmpUser)) {
      if (userList.isActive(tmpUser)) {
        HttpSession httpSession = getThreadLocalRequest().getSession();
        return httpSession.getId();
      }
      return User.USER_INACTIVE;
    }
    return User.USER_INVALID;
  }

  /**
   * Logges a certain user out.
   * 
   * @param tmpUser The user which shall be logged out
   * @return null if the supplied user was null, "logout" otherwise
   */
  public String logout(User tmpUser) {

    if (tmpUser == null) { return null; }
    System.out.println("Logout aufgerufen für Session ==> " + tmpUser.getId());

    return "logout";

  }
}