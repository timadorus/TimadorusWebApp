package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.client.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DeleteUserServiceAsync {

  void delete(User user, AsyncCallback<String> callback);  

}
