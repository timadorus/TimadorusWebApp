package org.timadorus.webapp.tests.client.character.ui.selectrace;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.anyListOf;
import static org.mockito.Mockito.eq;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
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

  @Mock
  private SelectRaceDialog.Display myDisplayMock;

  @Mock
  private DefaultTimadorusWebApp myTimadorusWebAppMock;

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
  public void testPrevButtonHandler() {
    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addPrevButtonHandler(theArgumentCaptor.capture());

    theArgumentCaptor.getValue().onAction();

    verify(myDisplayMock).loadCharacterPanel(myUser, myTimadorusWebAppMock);
  }

  @Test
  public void testNextButtonHandler() {
    when(myDisplayMock.getSelectedRace()).thenReturn(RACE_WITZBOLD);
    when(myDisplayMock.getSelectedGender()).thenReturn(GENDER);

    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addNextButtonHandler(theArgumentCaptor.capture());
    
    theArgumentCaptor.getValue().onAction();

    Assert.assertEquals("Character Race should be " + RACE_WITZBOLD, RACE_WITZBOLD, myCharacter.getRace().getName());
    Assert.assertEquals("Character Gender should be " + GENDER, GENDER, myCharacter.getGender());
    
    verify(myDisplayMock).loadSelectClassPanel(myTimadorusWebAppMock, myUser, myCharacter);
  }

  @Test
  public void testRaceSelectionHandler() {
    when(myDisplayMock.getSelectedRace()).thenReturn(RACE_WITZBOLD);

    ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
    verify(myDisplayMock).addRaceSelectionHandler(theArgumentCaptor.capture());
    
    theArgumentCaptor.getValue().onAction();
    
    verify(myDisplayMock).showRaceSelection(eq(RACE_WITZBOLD), anyString(), 
                                            anyListOf(String.class), anyListOf(String.class));
  }

}
