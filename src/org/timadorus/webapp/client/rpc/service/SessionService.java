package org.timadorus.webapp.client.rpc.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import org.timadorus.webapp.client.SessionId;

/**
 * 
 * @author sage
 *
 */
@RemoteServiceRelativePath("session")
public interface SessionService extends RemoteService {
  
  /**
   * 
   * @param sessionId the sessionID to set
   * @return the newly created session id
   */
  SessionId session(SessionId sessionId);
}