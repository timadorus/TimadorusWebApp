package org.timadorus.webapp.tests.client.character.ui.selectfraction;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.TestCharacterValues;
import org.timadorus.webapp.client.character.ui.selectfraction.SelectFactionDialog;
import org.timadorus.webapp.client.character.ui.selectfraction.SelectFactionDialog.Display;


public class SelectFactionDialogTest {
  private static final String FACTION_SKATER_STRING = "Skater";
  private static final String FACTION_SKAETER_STRING = "Skaeter";
  
  private SelectFactionDialog mySelectFactionDialog;
  private Display myDisplayMock;
  private Character myCharacter;
  private User myUser;
  private DefaultTimadorusWebApp myTimadorusWebAppMock;
  
  @Before
  public void setUp() {
    myCharacter = new Character();
    myUser = new User();
    
    myDisplayMock = Mockito.mock(SelectFactionDialog.Display.class);

    myTimadorusWebAppMock = Mockito.mock(DefaultTimadorusWebApp.class);
    Mockito.when(myTimadorusWebAppMock.getTestValues()).thenReturn(new TestCharacterValues());
    
    mySelectFactionDialog = new SelectFactionDialog(myDisplayMock, myTimadorusWebAppMock);
  }

  @Test
  public void testVerifyHandler() {
//    Mockito.verify(myDisplayMock).addNextButtonHandler(Mockito.isA(DefaultActionHandler.class));
//    Mockito.verify(myDisplayMock).addPrevButtonHandler(Mockito.isA(DefaultActionHandler.class));
//    Mockito.verify(myDisplayMock).addSelectFactionGridHandler(Mockito.isA(DefaultActionHandler.class));
  }
  
  @Test
  public void testSaveSelectedFaction() {
    
    Mockito.when(myDisplayMock.getSelectedFaction()).thenReturn(FACTION_SKATER_STRING);
    
//  mySelectFactionDialog.saveSelectedFaction();
    
//  Assert.assertEquals("The Faction should be " + FACTION_SKATER_STRING, FACTION_SKATER_STRING, 
//                        myCharacter.getFaction().getName());
  }
  
  @Test
  public void testSaveSelectedFactionNull() {
    Mockito.when(myDisplayMock.getSelectedFaction()).thenReturn(FACTION_SKAETER_STRING);
    
//    mySelectFactionDialog.saveSelectedFaction();
    
//    Assert.assertNull("The Faction should be null", myCharacter.getFaction());
  }
  
}
