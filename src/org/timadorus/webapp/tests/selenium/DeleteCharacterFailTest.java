package org.timadorus.webapp.tests.selenium;

import com.thoughtworks.selenium.*;

public class DeleteCharacterFailTest extends SeleneseTestCase {
  @Override
  public void setUp() throws Exception {
    setUp("http://127.0.0.1:8888/", "*firefox");
  }

  public void testDeleteCharacterFail() throws Exception {
    selenium.open("/TimadorusWebApp.html");
    selenium.click("link=Account registrieren");
    selenium.type("//input[@type='text']", "Test");
    selenium.type("//div[@id='content']/form/table/tbody/tr[2]/td[2]/input", "8");
    selenium.type("//div[@id='content']/form/table/tbody/tr[6]/td[2]/input", "test8");
    selenium.click("//button[@type='button']");
    selenium.click("link=Einloggen");
    selenium.type("//input[@type='text']", "test8");
    selenium.type("//input[@type='password']", "passwort");
    selenium.click("//button[@type='button']");
    selenium.click("link=Charakter erstellen");
    selenium.click("//div[@id='content']/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/img");
    selenium.click("//button[@type='button']");
    selenium.click("//div[@id='content']/table/tbody/tr[3]/td/table/tbody/tr/td[2]/button");
    selenium.click("link=Liste der Charaktere");
    selenium.click("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr/td[3]/button");
    selenium.type("//input[@type='password']", "abc");
    selenium.click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Passwort falsch! Versuchen Sie es erneut!"));
    selenium.click("closeButton");
    selenium.type("//input[@type='password']", "passwort");
    selenium.click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Ihr Charakter wurde erfolgreich gelöscht!"));
    selenium.click("closeButton");
    selenium.click("link=Charakter erstellen");
    selenium.click("//div[@id='content']/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/img");
    selenium.click("//button[@type='button']");
    selenium.click("//div[@id='content']/table/tbody/tr[3]/td/table/tbody/tr/td[2]/button");
    selenium.click("link=Liste der Charaktere");
    selenium.click("//button[@type='button']");
    selenium.click("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr/td[1]/form/table/tbody/"
        + "tr[1]/td/table/tbody/tr/td[2]/button");
    selenium.type("//input[@type='password']", "abc");
    selenium.click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Passwort falsch! Versuchen Sie es erneut!\r\nClose"));
    selenium.click("closeButton");
    selenium.type("//input[@type='password']", "passwort");
    selenium.click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Ihr Charakter wurde erfolgreich gelöscht!"));
    selenium.click("closeButton");
  }
}
