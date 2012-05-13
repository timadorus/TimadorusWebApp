package org.timadorus.webapp.tests.acceptance.framework;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;

import com.thoughtworks.selenium.DefaultSelenium;

public abstract class SeleniumTest {
  private String myGridServerHost;
  private int myGridServerPort = -1;
  private String myBrowser;
  private String myWebAppRoot;
  
  private DefaultSelenium selenium;
  
  public SeleniumTest(String aGridServerHost, int aGridServerPort, String aBrowser, String aWebAppRoot) {
    this.myGridServerHost = aGridServerHost;
    this.myGridServerPort = aGridServerPort;
    this.myBrowser        = aBrowser;
    this.myWebAppRoot     = aWebAppRoot;
  }
  
  public SeleniumTest() throws Exception {
    Properties theProperties = new Properties();
    URL thePropertiesUrl = ClassLoader.getSystemResource("selenium.properties");
    theProperties.load(thePropertiesUrl.openStream());
    
    this.myGridServerHost = theProperties.getProperty("Selenium.GridServer.Host");
    this.myGridServerPort = Integer.parseInt(theProperties.getProperty("Selenium.GridServer.Port"));
    this.myBrowser        = theProperties.getProperty("Selenium.Browser");
    this.myWebAppRoot     = theProperties.getProperty("Selenium.WebApp.RootServer") 
        + "/" + theProperties.getProperty("Selenium.WebApp.RootSite");
  }
  
  public DefaultSelenium getSelenium() {
    assertNotNull("Selenium shouldn't be null - remember to call super.setUp() in setUp()!", selenium);
    return selenium;
  }

  @Before
  public void setUp() throws Exception {
    assertTrue("Neither GridServerHost nor GridServerPort, Browser or WebAppRoot should be null" 
              + " - remember to call a super-constructor!", 
               myGridServerHost != null && myGridServerPort != -1 && myBrowser != null && myWebAppRoot != null);
    
    selenium = new DefaultSelenium(myGridServerHost, myGridServerPort, myBrowser, myWebAppRoot);
    selenium.start();
  }
  
  @After
  public void tearDown() throws Exception {
    selenium.stop();
  }  
}
