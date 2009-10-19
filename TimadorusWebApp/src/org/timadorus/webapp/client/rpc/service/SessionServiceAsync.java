package org.timadorus.webapp.client.rpc.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.timadorus.webapp.client.SessionId;

public interface SessionServiceAsync {
	void session(SessionId sessionId, AsyncCallback<SessionId> asyncCallback);
}