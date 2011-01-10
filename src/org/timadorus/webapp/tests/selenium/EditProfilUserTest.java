package org.timadorus.webapp.tests.selenium;

// Tests deletion of a user
public class EditProfilUserTest extends WebTestCase {
  
  private static final int TEST_NUMBER = 8;
  
  @Override
  public void setUp() throws Exception {
    setUp("http://127.0.0.1:8888/", "*firefox");
    selenium.setSpeed(SeleniumTestSuite.CMD_DELAY);
  }

  // CHECKSTYLE OFF
  public void testDeleteUser() throws Exception {
    selenium.open("/TimadorusWebApp.html");
    
    // registration of dummy user to prove unique username
    click("link=Account registrieren");
    type("//div[@id='content']/form/table/tbody/tr[2]/td[2]/input", "dummy" + TEST_NUMBER);
    type("//div[@id='content']/form/table/tbody/tr[6]/td[2]/input", "dummy" + TEST_NUMBER);
    type("//input[@type='password']", "geheim");
    type("//div[@id='content']/form/table/tbody/tr[8]/td[2]/input", "geheim");
    click("//button[@type='button']");
    String activationLink = selenium.getText("//b");
    selenium.open(activationLink);
    type("//input[@type='text']", "dummy" + TEST_NUMBER);
    type("//input[@type='password']", "geheim");
    click("//button[@type='button']");
    
    // registration of the test user
    click("link=Account registrieren");
    type("//div[@id='content']/form/table/tbody/tr[2]/td[2]/input", "" + TEST_NUMBER);
    type("//div[@id='content']/form/table/tbody/tr[6]/td[2]/input", "test" + TEST_NUMBER);
    type("//input[@type='password']", "geheim");
    type("//div[@id='content']/form/table/tbody/tr[8]/td[2]/input", "geheim");
    click("//button[@type='button']");
    
    // activation link
    activationLink = selenium.getText("//b");
    selenium.open(activationLink);
    type("//input[@type='text']", "test" + TEST_NUMBER);
    type("//input[@type='password']", "geheim");
    click("//button[@type='button']");
    
    // login
    type("//input[@type='text']", "test" + TEST_NUMBER);
    type("//input[@type='password']", "geheim");
    click("//button[@type='button']");
    
    // profile test
    click("link=Profil bearbeiten");
    
    // 1. First-name or last-name is required
    type("//input[@type='text']", "");
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/input", "");
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Bitte ausf"));
    
    type("//input[@type='text']", "Vorname");
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/input", "Nachname");
    
    // 2. Birth-date
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/input", "03.10.2011");
    click("//button[@type='button']");
    assertEquals("Du bist leider zu jung!", 
                 selenium.getText("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[3]/td[3]/div/span"));
    
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/input", "03.12.1989");
    
    // 3. E-mail address not empty and format ".*@.*\..*"
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[4]/td[2]/input", "");
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[5]/td[2]/input", "");
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Bitte ausf"));
    
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[4]/td[2]/input", "test");
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[5]/td[2]/input", "test");
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Das Format ist ung"));
    
    // 4. E-mail repetition
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[4]/td[2]/input", "test@home.de");
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[5]/td[2]/input", "test2@home.de");
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Stimmen nicht"));
    
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[5]/td[2]/input", "test@home.de");
    
    // 5. Username unique and not empty
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[6]/td[2]/input", "");
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Bitte ausf"));
    
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[6]/td[2]/input", "dummy8");
    click("//button[@type='button']");
    assertEquals("Benutzername bereits vergeben!", 
                 selenium.getText("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[6]/td[3]/div/span"));

    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[6]/td[2]/input", "test9");
    
    // 6. Password not empty
    type("//input[@type='password']", "");
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[8]/td[2]/input", "");
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Bitte ausf"));

    // 7. Password repetition
    type("//input[@type='password']", "geheim");
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[8]/td[2]/input", "geheim2");
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Stimmen nicht"));
    
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[8]/td[2]/input", "geheim");
    
    // 8. Set new values an test savement
    type("//input[@type='text']", "TestVorname");
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/input", "TestNachname");
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[4]/td[2]/input", "test@neu.de");
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[5]/td[2]/input", "test@neu.de");
    type("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[6]/td[2]/input", "test8");
    
    click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Ihre Daten wurden erfolgreich ge"));
    click("closeButton");
    
    verifyEquals("TestVorname", 
                 selenium.getValue("//input[@type='text']"));
    verifyEquals("TestNachname", 
                 selenium.getValue("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/input"));
    verifyEquals("03.12.1989", 
                 selenium.getValue("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[3]/td[2]/input"));
    verifyEquals("test@neu.de", 
                 selenium.getValue("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[4]/td[2]/input"));
    verifyEquals("test@neu.de", 
                 selenium.getValue("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr[5]/td[2]/input"));
    
    // logout
    click("link=Ausloggen");
  }
  // CHECKSTYLE ON
}
