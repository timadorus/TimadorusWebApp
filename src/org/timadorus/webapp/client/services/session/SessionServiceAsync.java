package org.timadorus.webapp.client.services.session;

import org.timadorus.webapp.client.ui.SessionId;

import com.google.gwt.user.client.rpc.AsyncCallback;


public interface SessionServiceAsync {
	void session(SessionId sessionId, AsyncCallback<SessionId> asyncCallback);
}