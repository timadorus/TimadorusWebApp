package org.timadorus.webapp.tests.character.ui.selectrace;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.TestCharacterValues;
import org.timadorus.webapp.client.character.ui.selectrace.SelectRaceDialog;

public class SelectRaceDialogTest {
  private static final String RACE_WITZBOLD = "Witzbold";
  
  private SelectRaceDialog mySelectRaceDialog;
  
  @Mock private SelectRaceDialog.Display myDisplayMock;
  @Mock private TimadorusWebApp myTimadorusWebAppMock;
  
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
  public void testSaveSelectedGender() {
    Mockito.when(myDisplayMock.getSelectedRace()).thenReturn(RACE_WITZBOLD);
    
    mySelectRaceDialog.saveSelectedRace();
    
    Assert.assertNotNull("Race should have been set.", myCharacter.getRace());
    Assert.assertEquals("The name of the Race should be " + RACE_WITZBOLD, 
                        RACE_WITZBOLD, myCharacter.getRace().getName());
  }
}
