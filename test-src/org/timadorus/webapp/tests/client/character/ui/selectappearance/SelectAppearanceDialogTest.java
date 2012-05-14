package org.timadorus.webapp.tests.client.character.ui.selectappearance;

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
import org.timadorus.webapp.client.character.ui.selectappearance.SelectAppearanceDialog;

// TODO - this Test is mostly useless because there are only private Methods...
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
    
    mySelectAppearanceDialog = new SelectAppearanceDialog(myDisplayMock, myTimadorusWebAppMock, myCharacter, myUser);
    
    Mockito.when(myTimadorusWebAppMock.getTestValues()).thenReturn(new TestCharacterValues());
  }
  
  @Test
  public void testVerifyHandler() {
//    Mockito.verify(myDisplayMock, Mockito.times(2)).addBlackHairHandler(Mockito.isA(DefaultActionHandler.class));
//    Mockito.verify(myDisplayMock, Mockito.times(2)).addWhiteHairHandler(Mockito.isA(DefaultActionHandler.class));
//    Mockito.verify(myDisplayMock, Mockito.times(2)).addBrownHairHandler(Mockito.isA(DefaultActionHandler.class));
//    Mockito.verify(myDisplayMock, Mockito.times(2)).addGreenHairHandler(Mockito.isA(DefaultActionHandler.class));
//    Mockito.verify(myDisplayMock, Mockito.times(2)).addRedHairHandler(Mockito.isA(DefaultActionHandler.class));
//    Mockito.verify(myDisplayMock, Mockito.times(2)).addYellowHairHandler(Mockito.isA(DefaultActionHandler.class));
//    Mockito.verify(myDisplayMock, Mockito.times(2)).addBlueHairHandler(Mockito.isA(DefaultActionHandler.class));
//    Mockito.verify(myDisplayMock, Mockito.times(2)).addBlackSkinHandler(Mockito.isA(DefaultActionHandler.class));
//    Mockito.verify(myDisplayMock, Mockito.times(2)).addWhiteSkinHandler(Mockito.isA(DefaultActionHandler.class));
//    Mockito.verify(myDisplayMock, Mockito.times(2)).addBrownSkinHandler(Mockito.isA(DefaultActionHandler.class));
//    Mockito.verify(myDisplayMock, Mockito.times(2)).addGreenSkinHandler(Mockito.isA(DefaultActionHandler.class));
//    Mockito.verify(myDisplayMock, Mockito.times(2)).addRedSkinHandler(Mockito.isA(DefaultActionHandler.class));
//    Mockito.verify(myDisplayMock, Mockito.times(2)).addYellowSkinHandler(Mockito.isA(DefaultActionHandler.class));
//    Mockito.verify(myDisplayMock, Mockito.times(2)).addBlueSkinHandler(Mockito.isA(DefaultActionHandler.class));
//    
//    Mockito.verify(myDisplayMock).addNextButtonHandler(Mockito.isA(DefaultActionHandler.class));
//    Mockito.verify(myDisplayMock).addPrevButtonHandler(Mockito.isA(DefaultActionHandler.class));
  }
  
  @Test
  public void testTrue() {
    Assert.assertNotNull(mySelectAppearanceDialog);
  }
  
}
