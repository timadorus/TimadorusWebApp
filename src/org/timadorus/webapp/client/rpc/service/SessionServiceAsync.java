package org.timadorus.webapp.client.rpc.service;

import com.google.gwt.user.client.rpc.AsyncCallback;
import org.timadorus.webapp.client.SessionId;

/**
 * 
 * @author sage
 *
 */
public interface SessionServiceAsync {
  
  /**
   * 
   * @param sessionId session id to set
   * @param asyncCallback the callback to use
   */
  void session(SessionId sessionId, AsyncCallback<SessionId> asyncCallback);
}