package org.timadorus.webapp.client;

import java.io.Serializable;

/**
 * Class for the Session-Id.
 * 
 * @author aaz214
 */
public class SessionId implements Serializable {

  private static final long serialVersionUID = 4637358200152132934L;
  
  private String sessionId = "";
  
  /**
   * default constructor - sets the sessionId to ""
   */
  public SessionId() {
    this.sessionId = "";
  }

  /**
   * Sets the Session-Id - "" if null
   * @param sessionIdIn
   */
  public void setSessionId(String sessionIdIn) {
    if (sessionIdIn == null) {
      this.sessionId = "";
    } else {
      this.sessionId = sessionIdIn;
    }
  }

  /**
   * Returns the given Session-Id
   * @return
   */
  public String getSessionId() {
    return sessionId;
  }

}