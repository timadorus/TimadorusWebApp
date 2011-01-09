package org.timadorus.webapp.tests.selenium;

// Tests deleting a user with a wrong password
public class DeleteUserFailTest extends WebTestCase {

  final int sleepTime = 1000;
  final int ajaxWait = 2000;
  
  @Override
  public void setUp() throws Exception {
    setUp("http://127.0.0.1:8888/", "*firefox");
  }

  public void testDeleteUserFail() throws Exception {
    selenium.open("/TimadorusWebApp.html");
    click("link=Account registrieren");
    type("//input[@type='text']", "Test");
    type("//div[@id='content']/form/table/tbody/tr[2]/td[2]/input", "7");
    type("//div[@id='content']/form/table/tbody/tr[6]/td[2]/input", "test7");
    click("//button[@type='button']");
    
    // creation of database and table "users"    
    Thread.sleep(sleepTime);
    
    // activation link
    String activationLink = selenium.getText("//b");
    selenium.open(activationLink);
    selenium.type("//input[@type='text']", "test7");
    selenium.type("//input[@type='password']", "passwort");
    selenium.click("//button[@type='button']");
    Thread.sleep(ajaxWait);
    
    type("//input[@type='text']", "test7");
    type("//input[@type='password']", "passwort");
    click("//button[@type='button']");
    click("link=Profil bearbeiten");
    click("//button[@type='button']");
    type("//input[@type='password']", "abc");
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Passwort falsch! Versuchen Sie es erneut!"));
    click("closeButton");
    type("//input[@type='password']", "passwort");
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Ihr Account wurde erfolgreich gelöscht!"));
    click("closeButton");
  }
}
