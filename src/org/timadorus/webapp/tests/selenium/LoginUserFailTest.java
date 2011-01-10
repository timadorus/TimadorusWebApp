package org.timadorus.webapp.tests.selenium;

// Tests logging in of a user with wrong password
public class LoginUserFailTest extends WebTestCase {
  
  @Override
  public void setUp() throws Exception {
    setUp("http://127.0.0.1:8888/", "*firefox");
    selenium.setSpeed(SeleniumTestSuite.CMD_DELAY);
  }

  public void testRegisterUserFail() throws Exception {
    selenium.open("/TimadorusWebApp.html");
    click("link=Account registrieren");
    type("//input[@type='text']", "Test");
    type("//div[@id='content']/form/table/tbody/tr[2]/td[2]/input", "5");
    type("//div[@id='content']/form/table/tbody/tr[6]/td[2]/input", "test5");
    click("//button[@type='button']");
    
    // activation link
    String activationLink = selenium.getText("//b");
    selenium.open(activationLink);
    selenium.type("//input[@type='text']", "test5");
    selenium.type("//input[@type='password']", "passwort");
    selenium.click("//button[@type='button']");
    
    type("//input[@type='text']", "abc");
    type("//input[@type='password']", "passwort");
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Username und/oder Passwort falsch!"));
    type("//input[@type='text']", "test5");
    type("//input[@type='password']", "abc");
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Username und/oder Passwort falsch!"));
    type("//input[@type='text']", "test5");
    type("//input[@type='password']", "passwort");
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Du bist als test5 angemeldet"));
  }
}
