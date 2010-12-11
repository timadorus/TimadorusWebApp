package org.timadorus.webapp.server.rpc.service;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

import org.timadorus.webapp.client.User;
import org.timadorus.webapp.client.rpc.service.DeleteUserService;
import org.timadorus.webapp.server.RegisteredUserList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class DeleteUserServiceImpl  extends RemoteServiceServlet implements DeleteUserService {

  private static final long serialVersionUID = -2215579735797066083L;
  
  public static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");

  public String delete(User user) {
    boolean status = RegisteredUserList.getInstance().deleteUser(user);
    if (status) {
      return String.valueOf(User.OK);
    }
    return User.USER_INVALID;
  }

}
