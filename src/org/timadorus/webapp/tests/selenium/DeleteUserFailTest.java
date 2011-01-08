package org.timadorus.webapp.tests.selenium;

public class DeleteUserFailTest extends WebTestCase {
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
    click("link=Einloggen");
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
