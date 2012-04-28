package org.timadorus.webapp.tests.client.character.ui.premadecharacter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.TestCharacterValues;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.premadecharacter.PremadeCharacterDialog;

public class PremadeCharacterDialogTest {
  private PremadeCharacterDialog myPremadeCharacterDialog;
  
  @Mock PremadeCharacterDialog.Display myDisplayMock;
  @Mock DefaultTimadorusWebApp myDefaultTimadorusWebAppMock;
  
  private User myUser;
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    
    myUser = new User();
    
    when(myDefaultTimadorusWebAppMock.getTestValues()).thenReturn(new TestCharacterValues());

    myPremadeCharacterDialog = new PremadeCharacterDialog(myDisplayMock, myDefaultTimadorusWebAppMock, myUser);
  }
  
  @Test
  public void testBarbarianHandler() {
    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addBarbarianHandler(theArgumentCaptor.capture());

    theArgumentCaptor.getValue().onAction();
    
    verify(myDisplayMock).setInformation(anyString());
  }
  
  @Test
  public void testHunterHandler() {
    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addHunterHandler(theArgumentCaptor.capture());

    theArgumentCaptor.getValue().onAction();
    
    verify(myDisplayMock).setInformation(anyString());
  }

  
  @Test
  public void testWizzardHandler() {
    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addWizzardHandler(theArgumentCaptor.capture());

    theArgumentCaptor.getValue().onAction();
    
    verify(myDisplayMock).setInformation(anyString());
  }
  
  @Test
  public void testNextButtonHandler() {
    final String aUserName = "ein username";
    myUser.setUsername(aUserName);
    
    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addNextButtonHandler(theArgumentCaptor.capture());

    theArgumentCaptor.getValue().onAction();
    
    ArgumentCaptor<Character> theCharacterArgumentCaptor = ArgumentCaptor.forClass(Character.class);
    verify(myDisplayMock).sendCharacterToServer(theCharacterArgumentCaptor.capture());
    Assert.assertEquals("UserName should be " + aUserName, aUserName, 
                        theCharacterArgumentCaptor.getValue().getUsername());
    verify(myDisplayMock).loadCharacterReadyPanel(theCharacterArgumentCaptor.getValue(), myDefaultTimadorusWebAppMock);
  }
  
  @Test
  public void testPrevButtonHandler() {
    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addPrevButtonHandler(theArgumentCaptor.capture());

    theArgumentCaptor.getValue().onAction();
    
    verify(myDisplayMock).loadCharacterPanel(myDefaultTimadorusWebAppMock, myUser);
  }

  @Test
  public void testTrue() {
    Assert.assertNotNull(myPremadeCharacterDialog);
  }
}
