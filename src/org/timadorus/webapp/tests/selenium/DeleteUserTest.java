package org.timadorus.webapp.tests.selenium;

// Tests deletion of a user
public class DeleteUserTest extends WebTestCase {

  final int sleepTime = 30000;
  final int ajaxWait = 2000;
  
  @Override
  public void setUp() throws Exception {
    setUp("http://127.0.0.1:8888/", "*firefox");
  }

  public void testDeleteUser() throws Exception {
    selenium.open("/TimadorusWebApp.html");
    selenium.click("link=Account registrieren");
    selenium.type("//input[@type='text']", "Test");
    selenium.type("//div[@id='content']/form/table/tbody/tr[2]/td[2]/input", "3");
    selenium.type("//div[@id='content']/form/table/tbody/tr[3]/td[2]/input", "31.10.1988");
    selenium.type("//div[@id='content']/form/table/tbody/tr[4]/td[2]/input", "test3@home.de");
    selenium.type("//div[@id='content']/form/table/tbody/tr[5]/td[2]/input", "test3@home.de");
    selenium.type("//div[@id='content']/form/table/tbody/tr[6]/td[2]/input", "test3");
    selenium.type("//input[@type='password']", "geheim");
    selenium.type("//div[@id='content']/form/table/tbody/tr[8]/td[2]/input", "geheim");
    selenium.click("//button[@type='button']");
    
    // creation of database and table "users"    
    Thread.sleep(sleepTime);
    
    // activation link
    String activationLink = selenium.getText("//b");
    selenium.open(activationLink);
    selenium.type("//input[@type='text']", "test3");
    selenium.type("//input[@type='password']", "geheim");
    selenium.click("//button[@type='button']");
    Thread.sleep(ajaxWait);
    
    selenium.type("//input[@type='text']", "test3");
    selenium.type("//input[@type='password']", "geheim");
    selenium.click("//button[@type='button']");
    
    selenium.click("link=Profil bearbeiten");
    selenium.click("//button[@type='button']");
    
    selenium.type("//input[@type='password']", "geheim");
    selenium.click("//button[@type='button']");
    
    verifyTrue(selenium.isTextPresent("Ihr Account wurde erfolgreich gelöscht!"));
    selenium.click("closeButton");
    
    Thread.sleep(sleepTime);
    verifyTrue(selenium.isTextPresent("Sie haben sich ausgeloggt."));
  }
}
