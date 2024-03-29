package org.timadorus.webapp.tests.selenium;

// Tests the creation of a custom character
public class CreateCustomCharacterTest extends WebTestCase {

  @Override
  public void setUp() throws Exception {
    setUp("http://127.0.0.1:8888/", "*firefox");
    selenium.setSpeed(SeleniumTestSuite.CMD_DELAY);
  }

  public void testCreateCustomCharacter() throws Exception {
    selenium.open("/TimadorusWebApp.html");
    click("link=Account registrieren");
    type("//input[@type='text']", "Test");
    type("//div[@id='content']/form/table/tbody/tr[2]/td[2]/input", "4");
    type("//div[@id='content']/form/table/tbody/tr[6]/td[2]/input", "test4");
    type("//input[@type='password']", "geheim");
    type("//div[@id='content']/form/table/tbody/tr[8]/td[2]/input", "geheim");
    click("//button[@type='button']");

    // activation link
    String activationLink = selenium.getText("//b");
    selenium.open(activationLink);
    selenium.type("//input[@type='text']", "test4");
    selenium.type("//input[@type='password']", "geheim");
    selenium.click("//button[@type='button']");
    
    type("//input[@type='text']", "test4");
    type("//input[@type='password']", "geheim");
    click("//button[@type='button']");
    
    click("link=Charakter erstellen");
    click("//button[@type='button']");
    selenium.select("//div[@id='content']/table/tbody/tr[5]/td/table/tbody/tr/td[2]/select", "label=Terrorgnom");
    click("//option[@value='Terrorgnom']");
    click("gwt-uid-4");
    click("//div[@id='content']/table/tbody/tr[6]/td/table/tbody/tr/td[2]/button");
    selenium.select("//div[@id='content']/table/tbody/tr[5]/td/table/tbody/tr/td[2]/select", "label=Informatiker");
    click("//option[@value='Informatiker']");
    click("//div[@id='content']/table/tbody/tr[6]/td/table/tbody/tr/td[2]/button");
    selenium.select("//div[@id='content']/table/tbody/tr[6]/td/table/tbody/tr/td[2]/select", "label=Nooobs");
    click("//option[@value='Nooobs']");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr/td[2]/button");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[3]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[3]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[3]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[3]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[3]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[3]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[3]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[3]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[4]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[4]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[4]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[4]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[4]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[4]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[4]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[5]/td[8]/img");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[5]/td[8]/img");
    verifyTrue(selenium.isTextPresent("Sie haben ihre gesamten Punkte Verteilt"));
    click("//div[@id='content']/table/tbody/tr[8]/td/table/tbody/tr/td[2]/button");
    click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr/td[2]/button");
    click("//button[@type='button']");
    selenium.select("//div[@id='content']/table/tbody/tr[6]/td/table/tbody/tr[2]/td[1]/select", "label=Acting");
    click("//option[@value='Acting']");
    click("//button[@type='button']");
    click("//div[@id='content']/table/tbody/tr[10]/td/table/tbody/tr/td[2]/button");
    click("//button[@type='button']");
    selenium.select("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[1]/select",
                    "label=Armorsmithing");
    click("//option[@value='Armorsmithing']");
    click("//button[@type='button']");
    click("//div[@id='content']/table/tbody/tr[11]/td/table/tbody/tr/td[2]/button");
    verifyTrue(selenium.isTextPresent("Klicke auf eine Farbe um deine Hautfarbe zu bestimmen!"));
    click("//div[@id='content']/table/tbody/tr[9]/td/table/tbody/tr/td[4]/img");
    verifyTrue(selenium.isTextPresent("Hautfarbe ausgew"));
    verifyTrue(selenium.isTextPresent("Klicke auf eine Farbe um deine Haarfarbe zu bestimmen!"));
    click("//div[@id='content']/table/tbody/tr[12]/td/table/tbody/tr/td[6]/img");
    verifyTrue(selenium.isTextPresent("Rote Haarfarbe ausgew"));
    click("//div[@id='content']/table/tbody/tr[14]/td/table/tbody/tr/td[2]/button");
    type("//input[@type='text']", "Testchar");
    click("//div[@id='content']/table/tbody/tr[10]/td/table/tbody/tr/td[2]/button");
    verifyTrue(selenium.isTextPresent("Herzlichen"));
    verifyTrue(selenium.isTextPresent("Ihr Charakter wurde gespeichert."));
    verifyTrue(selenium.isTextPresent("Loggen sie sich auf dem Client mit ihren Accountdaten ein"));
    click("link=Liste der Charaktere");
    verifyTrue(selenium.isTextPresent("Testchar"));
    verifyTrue(selenium.isTextPresent("Terrorgnom"));
    verifyTrue(selenium.isTextPresent("Nooobs"));
    verifyTrue(selenium.isTextPresent("weiblich"));
    verifyTrue(selenium.isTextPresent("Informatiker"));
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Testchar"));
    verifyTrue(selenium.isTextPresent("weiblich"));
    verifyTrue(selenium.isTextPresent("Terrorgnom"));
    verifyTrue(selenium.isTextPresent("Informatiker"));
    verifyTrue(selenium.isTextPresent("Nooobs"));
    verifyTrue(selenium.isTextPresent("#CF0000"));
    verifyTrue(selenium.isTextPresent("#003000"));
    verifyTrue(selenium.isTextPresent("Skill-Name: Acting"));
    verifyTrue(selenium.isTextPresent("Level-Bonus-Cat: SUB"));
    verifyTrue(selenium.isTextPresent("Stat1: PR"));
    verifyTrue(selenium.isTextPresent("Stat2: EM"));
    verifyTrue(selenium.isTextPresent("Action-Type: SA"));
    verifyTrue(selenium.isTextPresent("Calc-Type: Std"));
    verifyTrue(selenium.isTextPresent("Local-Desc-Language: en-US"));
    verifyTrue(selenium.isTextPresent("Local-Desc-Default: true"));
    verifyTrue(selenium.isTextPresent("Skill-Name: Acrobatics"));
    verifyTrue(selenium.isTextPresent("Level-Bonus-Cat: ATH"));
    verifyTrue(selenium.isTextPresent("Stat1: AG"));
    verifyTrue(selenium.isTextPresent("Stat2: QU"));
    verifyTrue(selenium.isTextPresent("Action-Type: MM"));
    verifyTrue(selenium.isTextPresent("Calc-Type: Std"));
    verifyTrue(selenium.isTextPresent("Local-Desc-Language: en-US"));
    verifyTrue(selenium.isTextPresent("Local-Desc-Default: true"));
    verifyTrue(selenium.isTextPresent("Skill-Name: Armorsmithing"));
    verifyTrue(selenium.isTextPresent("Level-Bonus-Cat: SUB"));
    verifyTrue(selenium.isTextPresent("Stat1: PR"));
    verifyTrue(selenium.isTextPresent("Stat2: EM"));
    verifyTrue(selenium.isTextPresent("Action-Type: SA"));
    verifyTrue(selenium.isTextPresent("Calc-Type: Std"));
    verifyTrue(selenium.isTextPresent("Local-Desc-Language: de"));
    verifyTrue(selenium.isTextPresent("Local-Desc-Default: true"));
    verifyTrue(selenium.isTextPresent("Skill-Name: Swordsmanship"));
    verifyTrue(selenium.isTextPresent("Level-Bonus-Cat: SUB"));
    verifyTrue(selenium.isTextPresent("Stat1: PR"));
    verifyTrue(selenium.isTextPresent("Stat2: EM"));
    verifyTrue(selenium.isTextPresent("Action-Type: SA"));
    verifyTrue(selenium.isTextPresent("Calc-Type: Std"));
    verifyTrue(selenium.isTextPresent("Local-Desc-Language: en-US"));
    verifyTrue(selenium.isTextPresent("Local-Desc-Default: true"));
    verifyTrue(selenium.isTextPresent("Name: Konstitution"));
    verifyTrue(selenium.isTextPresent("Beschreibung: Konsti"));
    verifyTrue(selenium.isTextPresent("Stufe: 100"));
    verifyTrue(selenium.isTextPresent("Name: Agi"));
    verifyTrue(selenium.isTextPresent("Beschreibung: Agi"));
    verifyTrue(selenium.isTextPresent("Stufe: 100"));
    verifyTrue(selenium.isTextPresent("Name: Selbstdisziplin"));
    verifyTrue(selenium.isTextPresent("Beschreibung: Diszi"));
    verifyTrue(selenium.isTextPresent("Stufe: 94"));
    verifyTrue(selenium.isTextPresent("Name: Schlu"));
    verifyTrue(selenium.isTextPresent("Beschreibung: Schlu"));
    verifyTrue(selenium.isTextPresent("Stufe: 30"));
    verifyTrue(selenium.isTextPresent("Name: Erinnerungsverm"));
    verifyTrue(selenium.isTextPresent("Beschreibung: Memory"));
    verifyTrue(selenium.isTextPresent("Stufe: 30"));
    verifyTrue(selenium.isTextPresent("Name: Gl"));
    verifyTrue(selenium.isTextPresent("Beschreibung: Lucky luck"));
    verifyTrue(selenium.isTextPresent("Stufe: 30"));
    verifyTrue(selenium.isTextPresent("Name: St"));
    verifyTrue(selenium.isTextPresent("Beschreibung: Strength"));
    verifyTrue(selenium.isTextPresent("Stufe: 30"));
    verifyTrue(selenium.isTextPresent("Name: Schnelligkeit"));
    verifyTrue(selenium.isTextPresent("Beschreibung: Schnelligkeit"));
    verifyTrue(selenium.isTextPresent("Stufe: 30"));
    verifyTrue(selenium.isTextPresent("Name: Intuition"));
    verifyTrue(selenium.isTextPresent("Beschreibung: Intuition"));
    verifyTrue(selenium.isTextPresent("Stufe: 30"));
    verifyTrue(selenium.isTextPresent("Name: Pr"));
    verifyTrue(selenium.isTextPresent("Beschreibung: Pr"));
    verifyTrue(selenium.isTextPresent("Stufe: 30"));
    verifyTrue(selenium.isTextPresent("Name: Empathie"));
    verifyTrue(selenium.isTextPresent("Beschreibung: Emp"));
    verifyTrue(selenium.isTextPresent("Stufe: 30"));
  }

}
