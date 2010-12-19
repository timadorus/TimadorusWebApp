package org.timadorus.webapp.tests.selenium;

public class CreateDeleteViewCharacterTest extends WebTestCase {
  
  final int sleepTime = 10000;
  
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
    click("link=Liste der Charaktere");
    verifyTrue(selenium.isTextPresent("Es wurden keine Charaktere gefunden."));
    click("link=Charakter erstellen");
    click("gwt-uid-2");
    click("//button[@type='button']");
    // creation of table "characters"
    Thread.sleep(sleepTime);
    click("//div[@id='content']/table/tbody/tr[3]/td/table/tbody/tr/td[2]/button");    
    click("link=Liste der Charaktere");
    verifyTrue(selenium.isTextPresent("Test"));
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Skill-Name: localeDescLabelIn\r\nLevel-Bonus-Cat: lvlBonusCatIn\r\nStat1: "
        + "stat1In\r\nStat2: stat2In\r\nAction-Type: actionTypeIn\r\nCalc-Type: "
        + "calcTypeIn\r\nLocal-Desc-Language: localeDescLanguageIn\r\nLocal-Desc-Default: true"));
    click("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr/td[1]/form/table/tbody/"
        + "tr[1]/td/table/tbody/tr/td[2]/button");
    type("//input[@type='password']", "passwort");
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Ihr Charakter wurde erfolgreich gelöscht!"));
    click("closeButton");
    verifyTrue(selenium.isTextPresent("Es wurden keine Charaktere gefunden."));
  }
}
