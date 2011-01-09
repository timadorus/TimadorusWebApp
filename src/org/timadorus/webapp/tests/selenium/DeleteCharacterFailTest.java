package org.timadorus.webapp.tests.selenium;

// Tests deleting a character with a wrong password
public class DeleteCharacterFailTest extends WebTestCase {

  final int sleepTime = 30000;
  final int ajaxWait = 2000;
  
  @Override
  public void setUp() throws Exception {
    setUp("http://127.0.0.1:8888/", "*firefox");
  }

  public void testDeleteCharacterFail() throws Exception {
    selenium.open("/TimadorusWebApp.html");
    click("link=Account registrieren");
    type("//input[@type='text']", "Test");
    type("//div[@id='content']/form/table/tbody/tr[2]/td[2]/input", "6");
    type("//div[@id='content']/form/table/tbody/tr[6]/td[2]/input", "test6");
    click("//button[@type='button']");
    
    // creation of database and table "users"    
    Thread.sleep(sleepTime);
    
    // activation link
    String activationLink = selenium.getText("//b");
    selenium.open(activationLink);
    selenium.type("//input[@type='text']", "test6");
    selenium.type("//input[@type='password']", "passwort");
    selenium.click("//button[@type='button']");
    Thread.sleep(ajaxWait);
    
    type("//input[@type='text']", "test6");
    type("//input[@type='password']", "passwort");
    click("//button[@type='button']");
    click("link=Charakter erstellen");
    click("//div[@id='content']/table/tbody/tr[3]/td/table/tbody/tr[2]/td[2]/img");
    click("//button[@type='button']");
    click("//div[@id='content']/table/tbody/tr[3]/td/table/tbody/tr/td[2]/button");
    Thread.sleep(ajaxWait);
    
    click("link=Liste der Charaktere");
    click("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr/td[3]/button");
    type("//input[@type='password']", "abc");
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Passwort falsch! Versuchen Sie es erneut!"));
    click("closeButton");
    type("//input[@type='password']", "passwort");
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Ihr Charakter wurde erfolgreich gelöscht!"));
    click("closeButton");
  }
}
