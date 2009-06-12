package org.timadorus.webapp.server.rpc.service;

import javax.servlet.http.HttpSession;

import org.timadorus.webapp.client.User;
import org.timadorus.webapp.client.rpc.service.LoginService;
import org.timadorus.webapp.server.RegisteredUserList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {

	private static final long serialVersionUID = 270628040929463623L;

	private static RegisteredUserList userList = RegisteredUserList.getInstance();
	
	public String login(User user) {
		System.out.println("Login aufgerufen");
		if(user != null && userList.isValid(user)){
			HttpSession httpSession = getThreadLocalRequest().getSession();
			httpSession.setMaxInactiveInterval(1000 * 60 * 2);
			return httpSession.getId();
		}
		return null;
	}
}