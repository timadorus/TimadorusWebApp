package org.timadorus.webapp.tests.client;

import junit.framework.Assert;

import org.junit.Test;
import org.timadorus.webapp.client.SessionId;

/**
 * 
 * @author aaz214
 */
public class SessionIdTest {
  private static final String FOO = "foo";

  @Test
  public void testSessionId() {
    SessionId theSessionId = new SessionId();
    Assert.assertEquals("The Session-Id should be \"\"", "", theSessionId.getSessionId());
  
    theSessionId.setSessionId(null);
    Assert.assertEquals("The Session-Id should be \"\"", "", theSessionId.getSessionId());
    
    theSessionId.setSessionId(FOO);
    Assert.assertEquals("The Session-Id should be \"foo\"", FOO, theSessionId.getSessionId());
  }
  
}
