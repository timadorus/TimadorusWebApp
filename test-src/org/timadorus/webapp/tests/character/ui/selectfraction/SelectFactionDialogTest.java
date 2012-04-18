package org.timadorus.webapp.tests.character.ui.selectfraction;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.TestCharacterValues;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.selectfraction.SelectFactionDialog;
import org.timadorus.webapp.client.character.ui.selectfraction.SelectFactionDialog.Display;


public class SelectFactionDialogTest {
  private static final String FACTION_SKATER_STRING = "Skater";
  private static final String FACTION_SKAETER_STRING = "Skaeter";
  
  private SelectFactionDialog mySelectFactionDialog;
  private Display myDisplayMock;
  private Character myCharacter;
  private TimadorusWebApp myTimadorusWebAppMock;
  
  @Before
  public void setUp() {
    myCharacter = new Character();
    
    myDisplayMock = Mockito.mock(SelectFactionDialog.Display.class);

    myTimadorusWebAppMock = Mockito.mock(TimadorusWebApp.class);
    Mockito.when(myTimadorusWebAppMock.getTestValues()).thenReturn(new TestCharacterValues());
    
    mySelectFactionDialog = new SelectFactionDialog(myDisplayMock, myTimadorusWebAppMock, 
                                                                         myCharacter, null);
    
    Mockito.verify(myDisplayMock).addNextButtonHandler(Mockito.isA(DefaultActionHandler.class));
    Mockito.verify(myDisplayMock).addNextButtonHandler(Mockito.isA(DefaultActionHandler.class));
    Mockito.verify(myDisplayMock).addSelectFactionGridHandler(Mockito.isA(DefaultActionHandler.class));

  }
  
  @Test
  public void testSaveSelectedFaction() {
    Mockito.when(myDisplayMock.getSelectedFaction()).thenReturn(FACTION_SKATER_STRING);
    
    mySelectFactionDialog.saveSelectedFaction();
    
    Assert.assertEquals("The Faction should be " + FACTION_SKATER_STRING, FACTION_SKATER_STRING, 
                        myCharacter.getFaction().getName());
  }
  
  @Test
  public void testSaveSelectedFactionNull() {
    Mockito.when(myDisplayMock.getSelectedFaction()).thenReturn(FACTION_SKAETER_STRING);
    
    mySelectFactionDialog.saveSelectedFaction();
    
    Assert.assertNull("The Faction should be null", myCharacter.getFaction());
  }
  
}
