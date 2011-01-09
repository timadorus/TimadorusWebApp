package org.timadorus.webapp.tests.selenium;

// Tests deletion of a user
public class DeleteUserTest extends WebTestCase {
  
  final int sleepTime = 1000;
  
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
    // creation of user
    Thread.sleep(sleepTime);
    selenium.click("link=Einloggen");
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
