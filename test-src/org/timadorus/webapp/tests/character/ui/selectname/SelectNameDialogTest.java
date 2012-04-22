package org.timadorus.webapp.tests.character.ui.selectname;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.selectname.SelectNameDialog;

public class SelectNameDialogTest {
  private static final String NAME = "name";
  
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
  public void testVerifyHandler() {
    Mockito.verify(myDisplayMock).addNextButtonHandler(Mockito.isA(DefaultActionHandler.class));
    Mockito.verify(myDisplayMock).addPrevButtonHandler(Mockito.isA(DefaultActionHandler.class));
  }
  
  @Test
  public void testSaveSelectedName() {
    mySelectNameDialog.saveSelectedName(NAME);
    
    Assert.assertEquals("Name should be " + NAME, NAME, myCharacter.getName());
  }
  
  @Test
  public void testGetCharacter() {
    Assert.assertEquals("Character should be equal", myCharacter, mySelectNameDialog.getCharacter());
  }
  
  @Test
  public void testGetUer() {
    Assert.assertEquals("User should be equal", myUser, mySelectNameDialog.getUser());
  }
  
  
}
