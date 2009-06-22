package org.timadorus.webapp.client.services.session;

import org.timadorus.webapp.client.ui.SessionId;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("session")
public interface SessionService extends RemoteService{
	SessionId session(SessionId sessionId);
}