package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.beans.User;

import com.google.gwt.user.client.rpc.AsyncCallback;

//Interface for Asyncronous "login"-Method-Calls (RPC-calls) between client and server
public interface LoginServiceAsync {

  void login(User user, AsyncCallback<String> asyncCallback);

  void logout(User user, AsyncCallback<String> callback);

}