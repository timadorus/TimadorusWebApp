package org.timadorus.webapp.tests.selenium;

import com.thoughtworks.selenium.*;

public class DeleteUserFailTest extends SeleneseTestCase {
  @Override
  public void setUp() throws Exception {
    setUp("http://127.0.0.1:8888/", "*firefox");
  }

  public void testDeleteUserFail() throws Exception {
    selenium.open("/TimadorusWebApp.html");
    selenium.click("link=Account registrieren");
    selenium.type("//input[@type='text']", "Test");
    selenium.type("//div[@id='content']/form/table/tbody/tr[2]/td[2]/input", "7");
    selenium.type("//div[@id='content']/form/table/tbody/tr[6]/td[2]/input", "test7");
    selenium.click("//button[@type='button']");
    selenium.click("link=Einloggen");
    selenium.type("//input[@type='text']", "test7");
    selenium.type("//input[@type='password']", "passwort");
    selenium.click("//button[@type='button']");
    selenium.click("link=Profil bearbeiten");
    selenium.click("//button[@type='button']");
    selenium.type("//input[@type='password']", "abc");
    selenium.click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Passwort falsch! Versuchen Sie es erneut!"));
    selenium.click("closeButton");
    selenium.type("//input[@type='password']", "passwort");
    selenium.click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Ihr Account wurde erfolgreich gelöscht!"));
    selenium.click("closeButton");
  }
}
