package org.timadorus.webapp.tests.acceptance;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.timadorus.webapp.tests.acceptance.framework.SeleniumTest;

public class HelloWorldTest extends SeleniumTest {
  
  public HelloWorldTest() throws Exception {
    super();
  }

  @Test
  public void testFoo() throws Exception {
    getSelenium().open("TimadorusWebApp.html");
    getSelenium().waitForPageToLoad("30000");
    assertEquals("Timadorus WebApplication", getSelenium().getTitle());
  }
}
