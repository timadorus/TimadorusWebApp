package org.timadorus.webapp.tests.selenium;

import com.thoughtworks.selenium.*;

public class DeleteUserTest extends SeleneseTestCase {
  @Override
  public void setUp() throws Exception {
    setUp("http://127.0.0.1:8888/", "*firefox");
  }

  public void testDeleteUser() throws Exception {
    selenium.open("/TimadorusWebApp.html?gwt.codesvr=127.0.0.1:9997");
    selenium.click("link=Account registrieren");
    selenium.click("//button[@type='button']");
    selenium.click("link=Einloggen");
    selenium.type("//input[@type='text']", "username");
    selenium.type("//input[@type='password']", "passwort");
    selenium.click("//button[@type='button']");
    selenium.click("link=Profil bearbeiten");
    selenium.click("//button[@type='button']");
    selenium.type("//input[@type='password']", "passwort");
    selenium.click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Ihr Account wurde erfolgreich gelöscht!"));
    selenium.click("closeButton");
    verifyTrue(selenium.isTextPresent("Sie haben sich ausgeloggt."));
  }
}
