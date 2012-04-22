package org.timadorus.webapp.tests.character.ui.premadecharacter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.premadecharacter.PremadeCharacterDialog;

//TODO - this Test is mostly useless because there are only private Methods...
public class PremadeCharacterDialogTest {
  private PremadeCharacterDialog myPremadeCharacterDialog;
  
  @Mock PremadeCharacterDialog.Display myDisplyMock;
  @Mock DefaultTimadorusWebApp myDefaultTimadorusWebAppMock;
  
  private User myUser;
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    
    myUser = new User();
    
    myPremadeCharacterDialog = new PremadeCharacterDialog(myDisplyMock, myDefaultTimadorusWebAppMock, myUser);
  }
  
  @Test
  public void testVerifyHandler() {
    Mockito.verify(myDisplyMock).addBarbarianHandler(Mockito.isA(DefaultActionHandler.class));
    Mockito.verify(myDisplyMock).addHunterHandler(Mockito.isA(DefaultActionHandler.class));
    Mockito.verify(myDisplyMock).addNextButtonHandler(Mockito.isA(DefaultActionHandler.class));
    Mockito.verify(myDisplyMock).addPrevButtonHandler(Mockito.isA(DefaultActionHandler.class));
    Mockito.verify(myDisplyMock).addWizzardHandler(Mockito.isA(DefaultActionHandler.class));
  }

  @Test
  public void testTrue() {
    Assert.assertNotNull(myPremadeCharacterDialog);
  }
}
