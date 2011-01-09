package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.client.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

// service for getting and deleting from the database and updating users in it
@RemoteServiceRelativePath("user")
public interface UserService extends RemoteService {

  User getUser(User user);
  String delete(User user);
  int update(long id, User user);
  String verifyMail(String activitionCode, User user);
  
}
