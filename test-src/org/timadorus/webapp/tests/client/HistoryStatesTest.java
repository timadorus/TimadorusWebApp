package org.timadorus.webapp.tests.client;

import junit.framework.Assert;

import org.junit.Test;
import org.timadorus.webapp.client.HistoryStates;

public class HistoryStatesTest {

  @Test
  public void testHistoryStates() {
    Assert.assertEquals("This should be the LOGIN_STATE", HistoryStates.LOGIN_STATE, 
                        HistoryStates.findByStringRepresentation("login"));
    
    Assert.assertNull("This should be null", HistoryStates.findByStringRepresentation("foo.bar"));
  }
  
}
