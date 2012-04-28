package org.timadorus.webapp.tests.client.character.ui.selectclass;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.TestCharacterValues;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.selectclass.SelectClassDialog;

// TODO - this Test is mostly useless because there are only private Methods...
public class SelectClassDialogTest {
  private SelectClassDialog mySelectClassDialog;
  
  @Mock private SelectClassDialog.Display myDisplayMock;
  @Mock private DefaultTimadorusWebApp myTimadorusWebAppMock;
  
  private Character myCharacter;
  private User myUser;
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    
    myCharacter = new Character();
    myUser = new User();
    
    mySelectClassDialog = new SelectClassDialog(myDisplayMock, myTimadorusWebAppMock, myCharacter, myUser);
    
    Mockito.when(myTimadorusWebAppMock.getTestValues()).thenReturn(new TestCharacterValues());
  }
  
  @Test
  public void testVerifyHandler() {
    Mockito.verify(myDisplayMock).setClassGridButtonHandler(Mockito.isA(DefaultActionHandler.class));
    Mockito.verify(myDisplayMock).setNextButtonHandler(Mockito.isA(DefaultActionHandler.class));
    Mockito.verify(myDisplayMock).setPrevButtonHandler(Mockito.isA(DefaultActionHandler.class));
  }
  
  @Test
  public void testTrue() {
    Assert.assertNotNull(mySelectClassDialog);
  }
  
}
