package org.timadorus.webapp.tests.selenium;

import com.thoughtworks.selenium.*;

public class SeleniumTest1 extends SeleneseTestCase {

  @Override
  public void setUp() throws Exception {
    setUp("http://127.0.0.1:8888/", "*firefox");
  }

  public void testSeleniumTest1() throws Exception {
    selenium.open("/TimadorusWebApp.html?gwt.codesvr=127.0.0.1:9997");
    selenium.click("link=Account registrieren");
    selenium.type("//input[@type='text']", "Selenium");
    selenium.type("//div[@id='content']/form/table/tbody/tr[2]/td[2]/input", "Test");
    selenium.type("//div[@id='content']/form/table/tbody/tr[3]/td[2]/input", "31.10.1988");
    selenium.type("//div[@id='content']/form/table/tbody/tr[4]/td[2]/input", "selenium@home.de");
    selenium.type("//div[@id='content']/form/table/tbody/tr[5]/td[2]/input", "selenium@home.de");
    selenium.type("//div[@id='content']/form/table/tbody/tr[6]/td[2]/input", "selenium");
    selenium.type("//input[@type='password']", "selenium");
    selenium.type("//div[@id='content']/form/table/tbody/tr[8]/td[2]/input", "selenium");
    selenium.click("//button[@type='button']");
    selenium.click("link=Einloggen");
    selenium.type("//input[@type='text']", "selenium");
    selenium.type("//input[@type='password']", "selenium");
    selenium.click("//button[@type='button']");
    selenium.click("link=Charakter erstellen");
    selenium.click("//button[@type='button']");
    selenium.select("//div[@id='content']/table/tbody/tr[5]/td/table/tbody/tr/td[2]/select", "label=Terrorgnom");
    selenium.click("//option[@value='Terrorgnom']");
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
    selenium.click("//div[@id='content']/table/tbody/tr[8]/td/table/tbody/tr/td[2]/button");
    selenium.click("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr/td[2]/button");
    selenium.select("//div[@id='content']/table/tbody/tr[6]/td/table/tbody" 
                    + "/tr[2]/td[1]/select", "label=Riding Animals");
    selenium.click("//option[@value='Riding Animals']");
    selenium.click("//button[@type='button']");
    selenium.click("//div[@id='content']/table/tbody/tr[10]/td/table/tbody/tr/td[2]/button");
    selenium.click("//button[@type='button']");
    selenium.select("//div[@id='content']/table/tbody/tr[7]/td/table/tbody/tr[2]/td[1]/select",
                    "label=Rüstungsschmiede");
    selenium.click("//option[@value='Rüstungsschmiede']");
    selenium.click("//button[@type='button']");
    selenium.click("//div[@id='content']/table/tbody/tr[11]/td/table/tbody/tr/td[2]/button");
    selenium.click("//div[@id='content']/table/tbody/tr[9]/td/table/tbody/tr/td[4]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[12]/td/table/tbody/tr/td[6]/img");
    selenium.click("//div[@id='content']/table/tbody/tr[14]/td/table/tbody/tr/td[2]/button");
    selenium.type("//input[@type='text']", "Mr. Selenium");
    selenium.click("//div[@id='content']/table/tbody/tr[10]/td/table/tbody/tr/td[2]/button");
    selenium.click("link=Liste der Charaktere");
    selenium.click("//button[@type='button']");
    selenium
        .click("//div[@id='content']/table/tbody/tr[2]/td/table/tbody/tr/td[1]/"
               + "form/table/tbody/tr[1]/td/table/tbody/tr/td[2]/button");
    selenium.type("//input[@type='password']", "selenium");
    selenium.click("//button[@type='button']");
    selenium.click("closeButton");
    selenium.click("link=Liste der Charaktere");
    selenium.click("link=Ausloggen");
  }
}
