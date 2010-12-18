package org.timadorus.webapp.tests.selenium;

import com.thoughtworks.selenium.*;

public class RegisterUserTest extends SeleneseTestCase {
  @Override
  public void setUp() throws Exception {
    setUp("http://127.0.0.1:8888/", "*firefox");
  }

  public void testRegisterUser() throws Exception {
    selenium.open("/TimadorusWebApp.html");
    selenium.click("link=Account registrieren");
    selenium.type("//input[@type='text']", "Torben");
    selenium.type("//div[@id='content']/form/table/tbody/tr[2]/td[2]/input", "X");
    selenium.type("//div[@id='content']/form/table/tbody/tr[3]/td[2]/input", "31.10.1988");
    selenium.type("//div[@id='content']/form/table/tbody/tr[4]/td[2]/input", "torben@home.de");
    selenium.type("//div[@id='content']/form/table/tbody/tr[5]/td[2]/input", "torben@home.de");
    selenium.type("//div[@id='content']/form/table/tbody/tr[6]/td[2]/input", "torbenx2");
    selenium.type("//input[@type='password']", "geheim");
    selenium.type("//div[@id='content']/form/table/tbody/tr[8]/td[2]/input", "geheim");
    selenium.click("//button[@type='button']");
    selenium.click("link=Einloggen");
    selenium.type("//input[@type='text']", "torbenx");
    selenium.type("//input[@type='password']", "geheim");
    selenium.click("//button[@type='button']");
    verifyTrue(selenium.isTextPresent("Du bist als torbenx angemeldet"));
    verifyTrue(selenium.isTextPresent("Liste der Charaktere"));
    verifyTrue(selenium.isTextPresent("Profil bearbeiten"));
    verifyTrue(selenium.isTextPresent("Ausloggen"));
    selenium.click("link=Ausloggen");
    verifyTrue(selenium.isTextPresent("Sie haben sich ausgeloggt."));
  }
}
