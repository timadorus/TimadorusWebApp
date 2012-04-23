package org.timadorus.webapp.tests.client.character.ui.selectskilllevel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.TestCharacterValues;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.selectskilllevel.SelectSkillLevelDialog;

public class SelectSkillLevelDialogTest {
  private SelectSkillLevelDialog mySelectSkillLevelDialog;
  
  @Mock SelectSkillLevelDialog.Display myDisplayMock;
  @Mock DefaultTimadorusWebApp myDefaultTimadorusWebApp;
  
  private Character myCharacter;
  private User myUser;
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    
    myCharacter = new Character();
    myUser = new User();
    
    mySelectSkillLevelDialog = new SelectSkillLevelDialog(myDisplayMock, myDefaultTimadorusWebApp, myCharacter, myUser);
    
    Mockito.when(myDefaultTimadorusWebApp.getTestValues()).thenReturn(new TestCharacterValues());
  }
  
  @Test
  public void testVerifyHandler() {
    Mockito.verify(myDisplayMock).addAddButtonHandler(Mockito.isA(DefaultActionHandler.class));
    Mockito.verify(myDisplayMock).addNextButtonHandler(Mockito.isA(DefaultActionHandler.class));
    Mockito.verify(myDisplayMock).addPrevButtonHandler(Mockito.isA(DefaultActionHandler.class));
    Mockito.verify(myDisplayMock).addRemoveButtonHandler(Mockito.isA(DefaultActionHandler.class));
    Mockito.verify(myDisplayMock).addResetButtonHandler(Mockito.isA(DefaultActionHandler.class));
    Mockito.verify(myDisplayMock).addSkillListBoxHandler(Mockito.isA(DefaultActionHandler.class));
  }
  
  @Test
  public void testGetCharacter() {
    Assert.assertEquals("Character should be equal", myCharacter, mySelectSkillLevelDialog.getCharacter());
  }
  
  @Test
  public void testGetUer() {
    Assert.assertEquals("User should be equal", myUser, mySelectSkillLevelDialog.getUser());
  }
  
}
