package org.timadorus.webapp.tests.client.character.ui.selectname;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.selectname.SelectNameDialog;

public class SelectNameDialogTest {
  private SelectNameDialog mySelectNameDialog;
  
  @Mock private SelectNameDialog.Display myDisplayMock;
  @Mock private DefaultTimadorusWebApp myDefaultTimadorusWebApp;
  
  private Character myCharacter;
  private User myUser;
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    
    myCharacter = new Character();
    myUser = new User();
    
    mySelectNameDialog = new SelectNameDialog(myDisplayMock, myDefaultTimadorusWebApp, myCharacter, myUser);
  }

  @Test
  public void testNextButtonHandler() {
    final String theCharacterName = "blablabla";
    final String theUserName = "blablablubb";
    myUser.setUsername(theUserName);
    when(myDisplayMock.getCharacterName()).thenReturn(theCharacterName);
    
    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addNextButtonHandler(theArgumentCaptor.capture());
    
    theArgumentCaptor.getValue().onAction();
    
    verify(myDisplayMock).sendCharacterToServerToSave(myCharacter);
    verify(myDisplayMock).loadSelectCharacterReadyPanel(myDefaultTimadorusWebApp, myCharacter);
    
    Assert.assertEquals("The UserName of the Character should be " + theUserName, 
                        theUserName, myCharacter.getUsername());
    Assert.assertEquals("The CharacterName of the Character should be " + theCharacterName, 
                        theCharacterName, myCharacter.getName());
  }
  
  @Test
  public void testPrevButtonHandler() {
    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addPrevButtonHandler(theArgumentCaptor.capture());
    
    theArgumentCaptor.getValue().onAction();
    
    verify(myDisplayMock).loadSelectAppearancePanel(myDefaultTimadorusWebApp, myCharacter, myUser);
  }
 
}
