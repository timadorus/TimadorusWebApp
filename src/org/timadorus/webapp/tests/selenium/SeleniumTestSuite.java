package org.timadorus.webapp.tests.selenium;

import junit.framework.Test;
import junit.framework.TestSuite;

// Test suite to run all the Selenium tests in the right order
public final class SeleniumTestSuite {
  
  public static final String CMD_DELAY = "200";
  
  private SeleniumTestSuite() {
    
  }

  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(RegisterUserTest.class);
    suite.addTestSuite(CreateDeleteViewCharacterTest.class);
    suite.addTestSuite(DeleteUserTest.class);
    suite.addTestSuite(CreateCustomCharacterTest.class);
    suite.addTestSuite(LoginUserFailTest.class);
    suite.addTestSuite(DeleteCharacterFailTest.class);
    suite.addTestSuite(DeleteUserFailTest.class);
    return suite;
  }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
  }
}
