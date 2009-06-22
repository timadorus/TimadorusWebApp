package org.timadorus.webapp.client.services.registerUser;


import org.timadorus.webapp.entities.User;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("register")
public interface RegisterService extends RemoteService {

	String register(User data);
}