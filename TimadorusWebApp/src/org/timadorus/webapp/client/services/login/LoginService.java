package org.timadorus.webapp.client.services.login;



import org.timadorus.webapp.entities.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService{

	String login(User user);
}