package org.timadorus.webapp.tests.character.ui.selectrace;

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
import org.timadorus.webapp.client.character.ui.selectrace.SelectRaceDialog;

public class SelectRaceDialogTest {
  private static final String RACE_WITZBOLD = "Witzbold";
  private static final String RACE_WITZBOLDT = "Witzboldt";
  private static final String GENDER = "Gender";
  
  private SelectRaceDialog mySelectRaceDialog;
  
  @Mock private SelectRaceDialog.Display myDisplayMock;
  @Mock private DefaultTimadorusWebApp myTimadorusWebAppMock;
  
  private Character myCharacter;
  private User myUser;
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    
    myCharacter = new Character();
    myUser = new User();
    
    mySelectRaceDialog = new SelectRaceDialog(myDisplayMock, myTimadorusWebAppMock, myCharacter, myUser);
    
    Mockito.when(myTimadorusWebAppMock.getTestValues()).thenReturn(new TestCharacterValues());
  }
  
  @Test
  public void testVerifyHandler() {
    Mockito.verify(myDisplayMock).addNextButtonHandler(Mockito.isA(DefaultActionHandler.class));
    Mockito.verify(myDisplayMock).addPrevButtonHandler(Mockito.isA(DefaultActionHandler.class));
    Mockito.verify(myDisplayMock).addRaceSelectionHanlder(Mockito.isA(DefaultActionHandler.class));
  }
  
  @Test
  public void testSaveSelectedRace() {
    Mockito.when(myDisplayMock.getSelectedRace()).thenReturn(RACE_WITZBOLD);
    
    mySelectRaceDialog.saveSelectedRace();
    
    Assert.assertNotNull("Race should have been set.", myCharacter.getRace());
    Assert.assertEquals("The name of the Race should be " + RACE_WITZBOLD, 
                        RACE_WITZBOLD, myCharacter.getRace().getName());
  }
  
  @Test
  public void testSaveSelectedRaceNull() {
    Mockito.when(myDisplayMock.getSelectedRace()).thenReturn(RACE_WITZBOLDT);
    
    mySelectRaceDialog.saveSelectedRace();
    
    Assert.assertNull("Race shouldn't have been set.", myCharacter.getRace());
  }
  
  @Test
  public void testSaveSelectedGender() {
    Mockito.when(myDisplayMock.getSelectedGender()).thenReturn(GENDER);
    
    mySelectRaceDialog.saveSelectedGender();
    
    Assert.assertEquals("Gender should be " + GENDER,  GENDER, myCharacter.getGender());
  }
  
  @Test
  public void testGetCharacter() {
    Assert.assertEquals("Character should be equal", myCharacter, mySelectRaceDialog.getCharacter());
  }
  
  @Test
  public void testGetUer() {
    Assert.assertEquals("User should be equal", myUser, mySelectRaceDialog.getUser());
  }
}
