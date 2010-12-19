package org.timadorus.webapp.tests.selenium;

import junit.framework.Test;
import junit.framework.TestSuite;

public final class SeleniumTestSuite {
  
  private SeleniumTestSuite() {
    
  }

  public static Test suite() {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(RegisterUserTest.class);
    suite.addTestSuite(CreateDeleteViewCharacterTest.class);
    suite.addTestSuite(DeleteUserTest.class);
    return suite;
  }

  public static void main(String[] args) {
    junit.textui.TestRunner.run(suite());
  }
}
