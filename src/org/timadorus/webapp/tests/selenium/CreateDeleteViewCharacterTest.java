package org.timadorus.webapp.tests.selenium;

import com.thoughtworks.selenium.*;

// Tests the creation, deletion and viewing of a character
public class CreateDeleteViewCharacterTest extends SeleneseTestCase {
  
  final int sleepTime = 15000;
  final int ajaxWait = 2000;
  
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
    selenium.click("link=Charakter erstellen");
    selenium.click("gwt-uid-2");
    selenium.click("//button[@type='button']");    
    selenium.click("//div[@id='content']/table/tbody/tr[3]/td/table/tbody/tr/td[2]/button");
    // creation of table "characters"
    Thread.sleep(sleepTime);    
    selenium.click("link=Liste der Charaktere");
    Thread.sleep(ajaxWait);
    verifyTrue(selenium.isTextPresent("Test"));
    selenium.click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Skill-Name: localeDescLabelIn"));
    verifyTrue(selenium.isTextPresent("Level-Bonus-Cat: lvlBonusCatIn"));
    verifyTrue(selenium.isTextPresent("Stat1: stat1In"));
    verifyTrue(selenium.isTextPresent("Stat2: stat2In"));
    verifyTrue(selenium.isTextPresent("Action-Type: actionTypeIn"));
    verifyTrue(selenium.isTextPresent("Calc-Type: calcTypeIn"));
    verifyTrue(selenium.isTextPresent("Local-Desc-Language: localeDescLanguageIn"));
    verifyTrue(selenium.isTextPresent("Local-Desc-Default: true"));
    selenium.click("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr/td[1]/form/table/tbody/"
        + "tr[1]/td/table/tbody/tr/td[2]/button");
    selenium.type("//input[@type='password']", "geheim");
    selenium.click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Ihr Charakter wurde erfolgreich gelöscht!"));
    selenium.click("closeButton");
    Thread.sleep(sleepTime);
    verifyTrue(selenium.isTextPresent("Es wurden keine Charaktere gefunden."));
  }
}
