package org.timadorus.webapp.server.servicesimpl.session;

import javax.servlet.http.HttpSession;

import org.timadorus.webapp.client.services.session.SessionService;
import org.timadorus.webapp.client.ui.SessionId;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class SessionServiceImpl extends RemoteServiceServlet implements SessionService{

	private static final long serialVersionUID = -6274876845484737659L;

	public SessionId session(SessionId sessionId) {
		HttpSession httpSession = getThreadLocalRequest().getSession(false);
		
		
		
		if(httpSession != null){
			try {
				System.out.println("httpsession: " + httpSession.getCreationTime() + " " + httpSession.getId());
				sessionId.setSessionId(httpSession.getId());
			} catch (IllegalStateException e) {
				System.out.println("httpsession: catch" + httpSession.getCreationTime() + " " + httpSession.getId());
				sessionId.setSessionId("");
			}
			return sessionId;
		}//end if(result == null)
		return null;
	}//end session
}