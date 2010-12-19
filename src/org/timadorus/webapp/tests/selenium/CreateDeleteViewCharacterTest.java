package org.timadorus.webapp.tests.selenium;

public class CreateDeleteViewCharacterTest extends WebTestCase {
  
  final int sleepTime = 10000;
  final int ajaxWait = 2000;
  
  @Override
  public void setUp() throws Exception {
    setUp("http://127.0.0.1:8888/", "*firefox");
  }

  public void testCreateDeleteViewCharacter() throws Exception {
    selenium.open("/TimadorusWebApp.html");
    click("link=Account registrieren");
    type("//input[@type='text']", "Test");
    type("//div[@id='content']/form/table/tbody/tr[2]/td[2]/input", "2");
    type("//div[@id='content']/form/table/tbody/tr[3]/td[2]/input", "31.10.1988");
    type("//div[@id='content']/form/table/tbody/tr[4]/td[2]/input", "test2@home.de");
    type("//div[@id='content']/form/table/tbody/tr[5]/td[2]/input", "test2@home.de");
    type("//div[@id='content']/form/table/tbody/tr[6]/td[2]/input", "test2");
    type("//input[@type='password']", "geheim");
    type("//div[@id='content']/form/table/tbody/tr[8]/td[2]/input", "geheim");
    click("//button[@type='button']");
    click("link=Einloggen");
    type("//input[@type='text']", "test2");
    type("//input[@type='password']", "geheim");
    click("//button[@type='button']");
    click("link=Charakter erstellen");
    click("gwt-uid-2");
    click("//button[@type='button']");    
    click("//div[@id='content']/table/tbody/tr[3]/td/table/tbody/tr/td[2]/button");
    // creation of table "characters"
    Thread.sleep(sleepTime);    
    click("link=Liste der Charaktere");
    Thread.sleep(ajaxWait);
    verifyTrue(selenium.isTextPresent("Test"));
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Skill-Name: localeDescLabelIn"));
    verifyTrue(selenium.isTextPresent("Level-Bonus-Cat: lvlBonusCatIn"));
    verifyTrue(selenium.isTextPresent("Stat1: stat1In"));
    verifyTrue(selenium.isTextPresent("Stat2: stat2In"));
    verifyTrue(selenium.isTextPresent("Action-Type: actionTypeIn"));
    verifyTrue(selenium.isTextPresent("Calc-Type: calcTypeIn"));
    verifyTrue(selenium.isTextPresent("Local-Desc-Language: localeDescLanguageIn"));
    verifyTrue(selenium.isTextPresent("Local-Desc-Default: true"));
    click("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr/td[1]/form/table/tbody/"
        + "tr[1]/td/table/tbody/tr/td[2]/button");
    type("//input[@type='password']", "geheim");
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Ihr Charakter wurde erfolgreich gelöscht!"));
    click("closeButton");
    verifyTrue(selenium.isTextPresent("Es wurden keine Charaktere gefunden."));
  }
}
