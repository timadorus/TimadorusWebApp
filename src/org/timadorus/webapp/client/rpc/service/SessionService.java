package org.timadorus.webapp.client.rpc.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.timadorus.webapp.client.SessionId;

@RemoteServiceRelativePath("session")
public interface SessionService extends RemoteService {
  SessionId session(SessionId sessionId);
}