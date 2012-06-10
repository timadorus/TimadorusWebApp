package org.timadorus.webapp.tests.client.character.ui.selectappearance;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.TestCharacterValues;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.selectappearance.SelectAppearanceDialog;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectNameEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectSkill1Event;

import com.google.gwt.user.client.ui.FormPanel;

public class SelectAppearanceDialogTest {
  private SelectAppearanceDialog mySelectAppearanceDialog;
  
  @Mock private SelectAppearanceDialog.Display myDisplayMock;
  @Mock private DefaultTimadorusWebApp myTimadorusWebAppMock;
  
  private Character myCharacter;
  private User myUser;
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    
    myCharacter = new Character();
    myUser = new User();
    
    mySelectAppearanceDialog = new SelectAppearanceDialog(myDisplayMock, myTimadorusWebAppMock);
    
    Mockito.when(myTimadorusWebAppMock.getTestValues()).thenReturn(new TestCharacterValues());
    when(myDisplayMock.getFormPanel()).thenReturn(null);
  }
  
  @Test
  public void testVerifyHandler() {
    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    
    Mockito.verify(myDisplayMock, Mockito.times(2)).addBlackHairHandler(theArgumentCaptor.capture());
    Mockito.verify(myDisplayMock, Mockito.times(2)).addWhiteHairHandler(theArgumentCaptor.capture());
    Mockito.verify(myDisplayMock, Mockito.times(2)).addBrownHairHandler(theArgumentCaptor.capture());
    Mockito.verify(myDisplayMock, Mockito.times(2)).addGreenHairHandler(theArgumentCaptor.capture());
    Mockito.verify(myDisplayMock, Mockito.times(2)).addRedHairHandler(theArgumentCaptor.capture());
    Mockito.verify(myDisplayMock, Mockito.times(2)).addYellowHairHandler(theArgumentCaptor.capture());
    Mockito.verify(myDisplayMock, Mockito.times(2)).addBlueHairHandler(theArgumentCaptor.capture());
    Mockito.verify(myDisplayMock, Mockito.times(2)).addBlackSkinHandler(theArgumentCaptor.capture());
    Mockito.verify(myDisplayMock, Mockito.times(2)).addWhiteSkinHandler(theArgumentCaptor.capture());
    Mockito.verify(myDisplayMock, Mockito.times(2)).addBrownSkinHandler(theArgumentCaptor.capture());
    Mockito.verify(myDisplayMock, Mockito.times(2)).addGreenSkinHandler(theArgumentCaptor.capture());
    Mockito.verify(myDisplayMock, Mockito.times(2)).addRedSkinHandler(theArgumentCaptor.capture());
    Mockito.verify(myDisplayMock, Mockito.times(2)).addYellowSkinHandler(theArgumentCaptor.capture());
    Mockito.verify(myDisplayMock, Mockito.times(2)).addBlueSkinHandler(theArgumentCaptor.capture());
    
    for (DefaultActionHandler theDefaultActionHandler : theArgumentCaptor.getAllValues()) { 
      theDefaultActionHandler.onAction();
    }
    
    final int wantedNumberOfInvocations = 7;
    verify(myDisplayMock, Mockito.times(wantedNumberOfInvocations)).setHairColorText(Mockito.anyString());
    verify(myDisplayMock, Mockito.times(wantedNumberOfInvocations)).setSkinColorText(Mockito.anyString());
  }
  
  @Test
  public void testNextButtonHandler() {
    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addNextButtonHandler(theArgumentCaptor.capture());
    
    theArgumentCaptor.getValue().onAction();
    
    verify(myTimadorusWebAppMock).fireEvent(Mockito.isA(ShowSelectNameEvent.class));
  }
  
  @Test
  public void testPrevButtonHandler() {
    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addPrevButtonHandler(theArgumentCaptor.capture());
    
    theArgumentCaptor.getValue().onAction();
    
    verify(myTimadorusWebAppMock).fireEvent(Mockito.isA(ShowSelectSkill1Event.class));
  }
  
  @Test
  public void testShow() {
    mySelectAppearanceDialog.show(myTimadorusWebAppMock, myCharacter, myUser);
    
    Mockito.verify(myDisplayMock).setCharacter(Mockito.eq(myCharacter));
    Mockito.verify(myDisplayMock).addToRootPanel(Mockito.eq((FormPanel) null));
  }
  
}
