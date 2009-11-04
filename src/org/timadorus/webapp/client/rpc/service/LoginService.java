package org.timadorus.webapp.client.rpc.service;

import org.timadorus.webapp.client.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {

  String login(User user);
}