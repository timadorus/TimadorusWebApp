package org.timadorus.webapp.tests.selenium;

import com.thoughtworks.selenium.*;

public class LoginUserFailTest extends SeleneseTestCase {
  @Override
  public void setUp() throws Exception {
    setUp("http://127.0.0.1:8888/", "*firefox");
  }

  public void testRegisterUserFail() throws Exception {
    selenium.open("/TimadorusWebApp.html");
    selenium.click("link=Account registrieren");
    selenium.type("//input[@type='text']", "Test");
    selenium.type("//div[@id='content']/form/table/tbody/tr[2]/td[2]/input", "5");
    selenium.type("//div[@id='content']/form/table/tbody/tr[6]/td[2]/input", "test5");
    selenium.click("//button[@type='button']");
    selenium.click("link=Einloggen");
    selenium.type("//input[@type='text']", "abc");
    selenium.type("//input[@type='password']", "passwort");
    selenium.click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Username und/oder Passwort falsch!"));
    selenium.type("//input[@type='text']", "test5");
    selenium.type("//input[@type='password']", "abc");
    selenium.click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Username und/oder Passwort falsch!"));
    selenium.type("//input[@type='text']", "test5");
    selenium.type("//input[@type='password']", "passwort");
    selenium.click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Du bist als test5 angemeldet"));
  }
}
