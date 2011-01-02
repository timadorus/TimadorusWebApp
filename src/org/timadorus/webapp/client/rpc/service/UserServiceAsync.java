package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.client.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {

  void getUser(User user, AsyncCallback<User> callback);
  void delete(User user, AsyncCallback<String> callback);
  void update(long id, User user, AsyncCallback<Integer> callback);
  void verifyMail(String activitionCode, User user, AsyncCallback<String> callback);

}
