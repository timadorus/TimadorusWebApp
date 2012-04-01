package org.timadorus.webapp.tests.server;

import junit.framework.Assert;

import org.junit.Test;
import org.timadorus.webapp.server.RegisteredUserList;

/**
 * Test-Class for RegisteredUserList
 * 
 * @author aaw602
 * 
 */

public class RegisteredUserListTest {

  @Test
  public void testgetInstance() {
    Assert.assertNotNull(RegisteredUserList.getInstance());
    Assert.assertTrue(RegisteredUserList.getInstance() instanceof RegisteredUserList);

  }
  
  @Test
  public void testusernameAvailable() {
       
   // Since the user "testUser" hasn't been added it should be available
    Assert.assertTrue(RegisteredUserList.getInstance().usernameAvailable("testUser"));
    
    //TODO Add User and check availability again
    // Problem? How to bring the Database back to a certain status e.G. After adding a user
    
  
  }
}
