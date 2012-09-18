package org.timadorus.webapp.tests.client.character.ui.potstat;

import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.potstat.PotStatsDialog;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectSkill0Event;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectTempStatsEvent;

import com.google.gwt.user.client.ui.FormPanel;

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
    
    when(myDisplayMock.calculatePotStats((ArrayList<Integer>) Mockito.anyListOf(Integer.class))).
                          thenReturn(new ArrayList<Integer>());
    
    when(myDisplayMock.getFormPanel()).thenReturn(null);
    
    myPotStatsDialog = new PotStatsDialog(myDisplayMock, myDefaultTimadorusWebApp);
    
  }
  
  @Test
  public void testNextButtonHandler() {
    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addNextButtonHandler(theArgumentCaptor.capture());
    
    theArgumentCaptor.getValue().onAction();
    
    verify(myDefaultTimadorusWebApp).fireEvent(isA(ShowSelectSkill0Event.class));
  }
  
  @Test
  public void testPrevButtonHandler() {
     ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
     verify(myDisplayMock).addPrevButtonHandler(theArgumentCaptor.capture());
    
     theArgumentCaptor.getValue().onAction();
    
     verify(myDefaultTimadorusWebApp).fireEvent(isA(ShowSelectTempStatsEvent.class));
  }
  
  @Test 
  public void testShow() {
    myPotStatsDialog.show(myDefaultTimadorusWebApp, myCharacter, myUser);
    
    verify(myDisplayMock).setCharacter(eq(myCharacter));
    Assert.assertTrue("PotStats should be empty", 
                      myCharacter.getPotStats() != null && myCharacter.getPotStats().size() == 0);
    
    verify(myDisplayMock).addToRootPanel((FormPanel) eq(null));
  }

 
}
