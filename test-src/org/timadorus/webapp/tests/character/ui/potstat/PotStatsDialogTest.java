package org.timadorus.webapp.tests.character.ui.potstat;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.potstat.PotStatsDialog;

public class PotStatsDialogTest {
  private PotStatsDialog myPotStatsDialog;
  
  @Mock PotStatsDialog.Display myDisplayMock;
  @Mock DefaultTimadorusWebApp myDefaultTimadorusWebApp;
  
  private Character myCharacter;
  private User myUser;
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    myCharacter = new Character();
    myUser = new User();
    
    myCharacter.setTempStat(new ArrayList<Integer>());
    
    Mockito.when(myDisplayMock.calculatePotStats(Mockito.anyListOf(Integer.class))).
                          thenReturn(new ArrayList<Integer>());
    
    myPotStatsDialog = new PotStatsDialog(myDisplayMock, myDefaultTimadorusWebApp, myCharacter, myUser);
  }
  
  @Test
  public void testNextButtonHandler() {
    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addNextButtonHandler(theArgumentCaptor.capture());
    
    theArgumentCaptor.getValue().onAction();
    
    verify(myDisplayMock).loadSelectTempStatsPanel(myDefaultTimadorusWebApp, myCharacter, myUser);
  }
  
  @Test
  public void testPrevButtonHandler() {
    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addPrevButtonHandler(theArgumentCaptor.capture());
    
    theArgumentCaptor.getValue().onAction();
    
    verify(myDisplayMock).loadSelectSkillPanel(myDefaultTimadorusWebApp, myCharacter, myUser);
  }
  
  @Test
  public void testGetPotStats() {
    Assert.assertTrue("PotStats should be empty", 
                       myCharacter.getPotStats() != null && myCharacter.getPotStats().size() == 0);
  }
  
  @Test
  public void testGetUser() {
    Assert.assertEquals("User should be equal", myUser, myPotStatsDialog.getUser());
  }
  
  @Test
  public void testGetCharacter() {
    Assert.assertEquals("Character should be equal", myCharacter, myPotStatsDialog.getCharacter());
  }
  
}
