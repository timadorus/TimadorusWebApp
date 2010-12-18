package org.timadorus.webapp.tests.selenium;

import com.thoughtworks.selenium.*;

public class CreateDeleteViewCharacterTest extends SeleneseTestCase {
  @Override
  public void setUp() throws Exception {
    setUp("http://127.0.0.1:8888/", "*firefox");
  }

  public void testCreateDeleteViewCharacter() throws Exception {
    selenium.open("/TimadorusWebApp.html");
    selenium.click("link=Account registrieren");
    selenium.type("//input[@type='text']", "Test");
    selenium.type("//div[@id='content']/form/table/tbody/tr[2]/td[2]/input", "2");
    selenium.type("//div[@id='content']/form/table/tbody/tr[3]/td[2]/input", "31.10.1988");
    selenium.type("//div[@id='content']/form/table/tbody/tr[4]/td[2]/input", "test2@home.de");
    selenium.type("//div[@id='content']/form/table/tbody/tr[5]/td[2]/input", "test2@home.de");
    selenium.type("//div[@id='content']/form/table/tbody/tr[6]/td[2]/input", "test2");
    selenium.type("//input[@type='password']", "geheim");
    selenium.type("//div[@id='content']/form/table/tbody/tr[8]/td[2]/input", "geheim");
    selenium.click("//button[@type='button']");
    selenium.click("link=Einloggen");
    selenium.type("//input[@type='text']", "test2");
    selenium.type("//input[@type='password']", "geheim");
    selenium.click("//button[@type='button']");
    selenium.click("link=Liste der Charaktere");
    verifyTrue(selenium.isTextPresent("Es wurden keine Charaktere gefunden."));
    selenium.click("link=Charakter erstellen");
    selenium.click("gwt-uid-2");
    selenium.click("//button[@type='button']");
    selenium.click("//div[@id='content']/table/tbody/tr[3]/td/table/tbody/tr/td[2]/button");
    selenium.click("link=Liste der Charaktere");
    verifyTrue(selenium.isTextPresent("Test"));
    selenium.click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Skill-Name: localeDescLabelIn\r\nLevel-Bonus-Cat: lvlBonusCatIn\r\nStat1: "
        + "stat1In\r\nStat2: stat2In\r\nAction-Type: actionTypeIn\r\nCalc-Type: "
        + "calcTypeIn\r\nLocal-Desc-Language: localeDescLanguageIn\r\nLocal-Desc-Default: true"));
    selenium.click("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr/td[1]/form/table/tbody/"
        + "tr[1]/td/table/tbody/tr/td[2]/button");
    selenium.type("//input[@type='password']", "passwort");
    selenium.click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Ihr Charakter wurde erfolgreich gelöscht!"));
    selenium.click("closeButton");
    verifyTrue(selenium.isTextPresent("Es wurden keine Charaktere gefunden."));
  }
}
