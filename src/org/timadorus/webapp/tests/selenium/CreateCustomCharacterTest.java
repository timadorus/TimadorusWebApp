package org.timadorus.webapp.tests.selenium;

import com.thoughtworks.selenium.*;

public class CreateCustomCharacterTest extends SeleneseTestCase {
  @Override
  public void setUp() throws Exception {
    setUp("http://127.0.0.1:8888/", "*firefox");
  }

  public void testCreateCustomCharacter() throws Exception {
    selenium.open("/TimadorusWebApp.html?gwt.codesvr=127.0.0.1:9997");
    selenium.click("link=Account registrieren");
    selenium.click("//button[@type='button']");
    selenium.click("link=Einloggen");
    selenium.type("//input[@type='text']", "username");
    selenium.type("//input[@type='password']", "passwort");
    selenium.click("//button[@type='button']");
    selenium.click("link=Charakter erstellen");
    selenium.click("//button[@type='button']");
    selenium.select("//div[@id='content']/table/tbody/tr[5]/td/table/tbody/tr/td[2]/select", "label=Terrorgnom");
    selenium.click("//option[@value='Terrorgnom']");
    selenium.click("gwt-uid-4");
    selenium.click("//div[@id='content']/table/tbody/tr[6]/td/table/tbody/tr/td[2]/button");
    selenium.select("//div[@id='content']/table/tbody/tr[5]/td/table/tbody/tr/td[2]/select", "label=Informatiker");
    selenium.click("//option[@value='Informatiker']");
    selenium.click("//div[@id='content']/table/tbody/tr[6]/td/table/tbody/tr/td[2]/button");
    selenium.select("//div[@id='content']/table/tbody/tr[6]/td/table/tbody/tr/td[2]/select", "label=Nooobs");
    selenium.click("//option[@value='Nooobs']");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr/td[2]/button");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[3]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[3]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[3]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[3]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[3]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[3]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[3]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[3]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[4]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[4]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[4]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[4]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[4]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[4]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[4]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[5]/td[8]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[5]/td[8]/img");
    verifyTrue(selenium.isTextPresent("Sie haben ihre gesamten Punkte Verteilt und können fortfahren"));
    selenium.click("//div[@id='content']/table/tbody/tr[8]/td/table/tbody/tr/td[2]/button");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr/td[2]/button");
    selenium.click("//button[@type='button']");
    selenium.select("//div[@id='content']/table/tbody/tr[6]/td/table/tbody/tr[2]/td[1]/select", "label=Acting");
    selenium.click("//option[@value='Acting']");
    selenium.click("//button[@type='button']");
    selenium.click("//div[@id='content']/table/tbody/tr[10]/td/table/tbody/tr/td[2]/button");
    selenium.click("//button[@type='button']");
    selenium.select("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[1]/select",
                    "label=Rüstungsschmiede");
    selenium.click("//option[@value='Rüstungsschmiede']");
    selenium.click("//button[@type='button']");
    selenium.click("//div[@id='content']/table/tbody/tr[11]/td/table/tbody/tr/td[2]/button");
    verifyTrue(selenium.isTextPresent("Klicke auf eine Farbe um deine Hautfarbe zu bestimmen!"));
    selenium.click("//div[@id='content']/table/tbody/tr[9]/td/table/tbody/tr/td[4]/img");
    verifyTrue(selenium.isTextPresent("Grüne Hautfarbe ausgewählt!"));
    verifyTrue(selenium.isTextPresent("Klicke auf eine Farbe um deine Haarfarbe zu bestimmen!"));
    selenium.click("//div[@id='content']/table/tbody/tr[12]/td/table/tbody/tr/td[6]/img");
    verifyTrue(selenium.isTextPresent("Rote Haarfarbe ausgewählt!"));
    selenium.click("//div[@id='content']/table/tbody/tr[14]/td/table/tbody/tr/td[2]/button");
    selenium.type("//input[@type='text']", "Testchar");
    selenium.click("//div[@id='content']/table/tbody/tr[10]/td/table/tbody/tr/td[2]/button");
    verifyTrue(selenium.isTextPresent("Herzlichen Glückwunsch.\r\n\r\nIhr Charakter wurde gespeichert. "
        + "Loggen sie sich auf dem Client mit ihren Accountdaten ein"));
    selenium.click("link=Liste der Charaktere");
    verifyTrue(selenium.isTextPresent("Testchar"));
    verifyTrue(selenium.isTextPresent("Terrorgnom"));
    verifyTrue(selenium.isTextPresent("Nooobs"));
    verifyTrue(selenium.isTextPresent("weiblich"));
    verifyTrue(selenium.isTextPresent("Informatiker"));
    selenium.click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Testchar"));
    verifyTrue(selenium.isTextPresent("weiblich"));
    verifyTrue(selenium.isTextPresent("Terrorgnom"));
    verifyTrue(selenium.isTextPresent("Informatiker"));
    verifyTrue(selenium.isTextPresent("Nooobs"));
    verifyTrue(selenium.isTextPresent("#CF0000"));
    verifyTrue(selenium.isTextPresent("#003000"));
    verifyTrue(selenium.isTextPresent("Skill-Name: Acting\r\nLevel-Bonus-Cat: SUB\r\nStat1: PR\r\nStat2: "
        + "EM\r\nAction-Type: SA\r\nCalc-Type: Std\r\nLocal-Desc-Language: en-US\r\nLocal-Desc-Default: "
        + "true\r\n\r\nSkill-Name: Acrobatics\r\nLevel-Bonus-Cat: ATH\r\nStat1: AG\r\nStat2: "
        + "QU\r\nAction-Type: MM\r\nCalc-Type: Std\r\nLocal-Desc-Language: " + "en-US\r\nLocal-Desc-Default: true"));
    verifyTrue(selenium.isTextPresent("Skill-Name: Rüstungsschmiede\r\nLevel-Bonus-Cat: SUB\r\nStat1: PR\r\nStat2: "
        + "EM\r\nAction-Type: SA\r\nCalc-Type: Std\r\nLocal-Desc-Language: de\r\nLocal-Desc-Default: "
        + "true\r\n\r\nSkill-Name: Swordsmanship\r\nLevel-Bonus-Cat: SUB\r\nStat1: PR\r\nStat2: "
        + "EM\r\nAction-Type: SA\r\nCalc-Type: Std\r\nLocal-Desc-Language: " + "en-US\r\nLocal-Desc-Default: true"));
    verifyTrue(selenium.isTextPresent("Name: Konstitution\r\nBeschreibung: Konsti\r\nStufe: 100\r\n\r\nName: "
        + "Agilität\r\nBeschreibung: Agi\r\nStufe: 100\r\n\r\nName: Selbstdisziplin\r\nBeschreibung: "
        + "Diszi\r\nStufe: 94\r\n\r\nName: Schlußfolgern\r\nBeschreibung: Schlußfolgern\r\nStufe: "
        + "30\r\n\r\nName: Erinnerungsvermögen\r\nBeschreibung: Memory\r\nStufe: 30\r\n\r\nName: "
        + "Glück\r\nBeschreibung: Lucky luck\r\nStufe: 30\r\n\r\nName: Stärke\r\nBeschreibung: "
        + "Strength\r\nStufe: 30\r\n\r\nName: Schnelligkeit\r\nBeschreibung: Schnelligkeit\r\nStufe: "
        + "30\r\n\r\nName: Intuition\r\nBeschreibung: Intuition\r\nStufe: 30\r\n\r\nName: "
        + "Präsenz\r\nBeschreibung: Präsenz\r\nStufe: 30\r\n\r\nName: Empathie\r\nBeschreibung: "
        + "Emp\r\nStufe: 30\r\n\r\nName: Aussehen\r\nBeschreibung: aussehen\r\nStufe: 91"));
  }
}
