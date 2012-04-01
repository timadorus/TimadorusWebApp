package org.timadorus.webapp.tests.client;

import junit.framework.Assert;

import org.junit.Test;
import org.timadorus.webapp.client.Role;

/**
 * @author aaw602
 * 
 */
public class RoleTest {

  private static final Role TEST_GUESTROLE = Role.GUEST;

  private static final Role TEST_USERROLE = Role.USER;

  private static final Role TEST_ADMINROLE = Role.ADMIN;

  private static final Role TEST_GMROLE = Role.GM;

  // Getter Tests

  @Test
  public void testgetDisplay_name() {
    Assert.assertEquals("guest", TEST_GUESTROLE.getDisplay_name());
  }

  @Test
  public void testgetDesc() {
    Assert.assertEquals("a user that is not currently logged in", TEST_GUESTROLE.getDesc());
  }

  @Test
  public void testisCampaignCtx() {
    Assert.assertEquals(false, TEST_GUESTROLE.isCampaignCtx());
  }

  // Tests for Role Checking

  // GUEST("guest", "a user that is not currently logged in", false)
  @Test
  public void testGuestRole() {
    Assert.assertEquals("guest", TEST_GUESTROLE.getDisplay_name());
    Assert.assertEquals("a user that is not currently logged in", TEST_GUESTROLE.getDesc());
    Assert.assertEquals(false, TEST_GUESTROLE.isCampaignCtx());

  }

  // ADMIN("administrator", "a system administrator", false)
  @Test
  public void testAdminRole() {
    Assert.assertEquals("administrator", TEST_ADMINROLE.getDisplay_name());
    Assert.assertEquals("a system administrator", TEST_ADMINROLE.getDesc());
    Assert.assertEquals(false, TEST_ADMINROLE.isCampaignCtx());

  }

  // USER("registered user", "any user that has successfully logged in", false)
  @Test
  public void testUserRole() {
    Assert.assertEquals("registered user", TEST_USERROLE.getDisplay_name());
    Assert.assertEquals("any user that has successfully logged in", TEST_USERROLE.getDesc());
    Assert.assertEquals(false, TEST_USERROLE.isCampaignCtx());

  }

  // GM("game master", "a user that may change the settings of a campaign", true)
  @Test
  public void testGMRole() {
    Assert.assertEquals("game master", TEST_GMROLE.getDisplay_name());
    Assert.assertEquals("a user that may change the settings of a campaign", TEST_GMROLE.getDesc());
    Assert.assertEquals(true, TEST_GMROLE.isCampaignCtx());

  }
}
