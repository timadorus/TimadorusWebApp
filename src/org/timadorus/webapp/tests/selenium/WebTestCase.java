package org.timadorus.webapp.tests.selenium;

import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.SeleneseTestCase;
import com.thoughtworks.selenium.Wait;

// Improves the compatibility with AJAX by waiting for elements
public class WebTestCase extends SeleneseTestCase {

  private final long defaultTimeout = 30;
  private final long secondsInMilliseconds = 1000;

  protected SeleniumServer server;

  public WebTestCase() {
  }

  public WebTestCase(String name) {
    super(name);
  }

  protected void startServer() throws Exception {
    server = new SeleniumServer();
    server.start();
  }

  protected void stopServer() {
    server.stop();
  }

  public void waitForElement(final String waitingElement) {
    waitForElement(waitingElement, defaultTimeout);
  }

  public void waitForElement(final String waitingElement, long timeoutInSeconds) {
    new Wait() {
      @Override
      public boolean until() {
        return selenium.isElementPresent(waitingElement);
      }
    } .wait("Timeout while waiting for element " + waitingElement, timeoutInSeconds * secondsInMilliseconds);
  }

  public void type(String element, String text) {
    waitForElement(element);
    selenium.type(element, text);
  }

  public void typeKeys(String element, String text) {
    waitForElement(element);
    selenium.typeKeys(element, text);
  }

  public void click(String element) {
    waitForElement(element);
    selenium.click(element);
  }

  public void select(String element, String value) {
    waitForElement(element);
    selenium.select(element, value);
  }

  public void check(String element) {
    waitForElement(element);
    selenium.check(element);
  }

  public void uncheck(String element) {
    waitForElement(element);
    selenium.uncheck(element);
  }

  public void focus(String element) {
    waitForElement(element);
    selenium.focus(element);
  }

  public void checkTextPresent(final String text) {
    checkTextPresent(text, defaultTimeout);
  }

  public void checkTextPresent(final String text, long timeoutInSeconds) {
    new Wait() {
      @Override
      public boolean until() {
        return selenium.isTextPresent(text);
      }
    } .wait("Timeout while waiting for text \"" + text + "\"", timeoutInSeconds * secondsInMilliseconds);
  }

  public void checkTextNotPresent(final String text) {
    checkTextPresent(text, defaultTimeout);
  }

  public void checkTextNotPresent(final String text, long timeoutInSeconds) {
    new Wait() {
      @Override
      public boolean until() {
        return !selenium.isTextPresent(text);
      }
    } .wait("Timeout while waiting for text \"" + text + "\"", timeoutInSeconds * secondsInMilliseconds);
  }

}