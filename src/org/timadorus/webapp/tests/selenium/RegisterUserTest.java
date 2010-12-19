package org.timadorus.webapp.tests.selenium;

public class RegisterUserTest extends WebTestCase {
  
  final int sleepTime = 20000;
  
  @Override
  public void setUp() throws Exception {
    setUp("http://127.0.0.1:8888/", "*firefox");
  }

  public void testRegisterUser() throws Exception {
    selenium.open("/TimadorusWebApp.html");
    click("link=Account registrieren");
    type("//input[@type='text']", "Test");
    type("//div[@id='content']/form/table/tbody/tr[2]/td[2]/input", "1");
    type("//div[@id='content']/form/table/tbody/tr[3]/td[2]/input", "31.10.1988");
    type("//div[@id='content']/form/table/tbody/tr[4]/td[2]/input", "test1@home.de");
    type("//div[@id='content']/form/table/tbody/tr[5]/td[2]/input", "test1@home.de");
    type("//div[@id='content']/form/table/tbody/tr[6]/td[2]/input", "test1");
    type("//input[@type='password']", "geheim");
    type("//div[@id='content']/form/table/tbody/tr[8]/td[2]/input", "geheim");    
    click("//button[@type='button']");
    // creation of database and table "users"    
    Thread.sleep(sleepTime);
    click("link=Einloggen");
    type("//input[@type='text']", "test1");
    type("//input[@type='password']", "geheim");
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Du bist als test1 angemeldet"));
    verifyTrue(selenium.isTextPresent("Liste der Charaktere"));
    verifyTrue(selenium.isTextPresent("Profil bearbeiten"));
    verifyTrue(selenium.isTextPresent("Ausloggen"));
    click("link=Ausloggen");
    verifyTrue(selenium.isTextPresent("Sie haben sich ausgeloggt."));
  }
}
