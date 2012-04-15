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

 
  // @Test
  // public void testusernameAvailable() {
  //
  // // Since the user "testUser" hasn't been added it should be available
  // Assert.assertTrue(RegisteredUserList.getInstance().usernameAvailable("testUser"));
  // User testUser = new User("testUser", "testUser", "11.11.11", "testuser@testmail.com", "testUser", "testpw");
  // RegisteredUserList.getInstance().addUser(testUser);
  // Assert.assertFalse(RegisteredUserList.getInstance().usernameAvailable("testUser"));
  // RegisteredUserList.getInstance().deleteUser(testUser);
  //
  //
  // }
  //
  // @Test
  // public void testisValid() {
  // User testUser = new User("testUser", "testUser", "11.11.11", "testuser@testmail.com", "testUser", "testpw");
  // RegisteredUserList.getInstance().addUser(testUser);
  // Assert.assertTrue(RegisteredUserList.getInstance().isValid(testUser));
  // User testUser2 = new User("testUser", "testUser", "11.11.11", "testuser@testmail.com", "testUser", "wrongpw");
  // Assert.assertFalse(RegisteredUserList.getInstance().isValid(testUser2));
  // RegisteredUserList.getInstance().deleteUser(testUser);
  //
  // }
  //
  // @Test
  // public void testisActive() {
  // User testUser = new User("testUser", "testUser", "11.11.11", "testuser@testmail.com", "testUser", "testpw");
  // RegisteredUserList.getInstance().addUser(testUser);
  // Assert.assertFalse(RegisteredUserList.getInstance().isActive(testUser));
  // testUser.setActive(true);
  // Assert.assertTrue(RegisteredUserList.getInstance().isActive(testUser));
  // RegisteredUserList.getInstance().deleteUser(testUser);
  //
  //
  // }
  
//  @Test
//  public void testgetUser() {
//    User testUser = new User("testUser", "testUser", "11.11.11", "testuser@testmail.com", "testUser", "testpw");
//    Assert.assertEquals(testUser, RegisteredUserList.getInstance().getUser("testUser"));
//  }
}
