package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.beans.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

// service for Login
@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {

  String login(User user);
  
  String logout (User user);
}