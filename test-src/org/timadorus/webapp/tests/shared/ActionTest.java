package org.timadorus.webapp.tests.shared;

import junit.framework.Assert;

import org.junit.Test;
import org.timadorus.webapp.client.service.ServiceType;
import org.timadorus.webapp.shared.Action;

public class ActionTest {

  @Test
  public void testAction() {
    final String theContent = "Hallo";
    Action<String> theAction = new Action<String>(ServiceType.REGISTER, theContent);
    
    Assert.assertEquals("Type should be " + ServiceType.REGISTER, ServiceType.REGISTER, theAction.getType());
    Assert.assertEquals("Content should be " + theContent, theContent, theAction.getContent());
  }
  
}
