package org.timadorus.webapp.tests.selenium;

// Tests deletion of a user
public class DeleteUserTest extends WebTestCase {
  
  @Override
  public void setUp() throws Exception {
    setUp("http://127.0.0.1:8888/", "*firefox");
    selenium.setSpeed(SeleniumTestSuite.CMD_DELAY);
  }

  public void testDeleteUser() throws Exception {
    selenium.open("/TimadorusWebApp.html");
    selenium.click("link=Account registrieren");
    selenium.type("//input[@type='text']", "Test");
    selenium.type("//div[@id='content']/form/table/tbody/tr[2]/td[2]/input", "3");
    selenium.type("//div[@id='content']/form/table/tbody/tr[6]/td[2]/input", "test3");
    selenium.type("//input[@type='password']", "geheim");
    selenium.type("//div[@id='content']/form/table/tbody/tr[8]/td[2]/input", "geheim");
    selenium.click("//button[@type='button']");
    
    // activation link
    String activationLink = selenium.getText("//b");
    selenium.open(activationLink);
    selenium.type("//input[@type='text']", "test3");
    selenium.type("//input[@type='password']", "geheim");
    selenium.click("//button[@type='button']");
    
    selenium.type("//input[@type='text']", "test3");
    selenium.type("//input[@type='password']", "geheim");
    selenium.click("//button[@type='button']");
    
    selenium.click("link=Profil bearbeiten");
    selenium.click("//div[@id='content']/table/tbody/tr[4]/td/table/tbody/tr[1]/td[1]/button");
    
    selenium.type("//div[@id='content']/table/tbody/tr[4]/td/table/tbody/tr[2]/td[1]/input", "geheim");
    selenium.click("//div[@id='content']/table/tbody/tr[4]/td/table/tbody/tr[3]/td[1]/button");
    
    verifyTrue(selenium.isTextPresent("Ihr Account wurde erfolgreich entfernt!"));
    selenium.click("closeButton");
    
    verifyTrue(selenium.isTextPresent("Sie haben sich ausgeloggt."));
  }
}
