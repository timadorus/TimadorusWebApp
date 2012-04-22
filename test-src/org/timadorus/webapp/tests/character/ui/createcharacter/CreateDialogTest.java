package org.timadorus.webapp.tests.character.ui.createcharacter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.TestCharacterValues;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.createcharacter.CreateDialog;

//TODO - this Test is mostly useless because there are only private Methods...
public class CreateDialogTest {

 private CreateDialog myCreateDialog;
 
 @Mock private CreateDialog.Display myDisplayMock;
 @Mock private DefaultTimadorusWebApp myTimadorusWebAppMock;
 
 private User myUser;
 
 @Before
 public void setUp() {
   MockitoAnnotations.initMocks(this);
   
   myUser = new User();
   
   myCreateDialog = new CreateDialog(myDisplayMock, myTimadorusWebAppMock, myUser);
   
   Mockito.when(myTimadorusWebAppMock.getTestValues()).thenReturn(new TestCharacterValues());
 }
 
 @Test
 public void testVerifyHandler() {
   Mockito.verify(myDisplayMock).setHandlerPremade(Mockito.isA(DefaultActionHandler.class));
   Mockito.verify(myDisplayMock).setHandlerCustom(Mockito.isA(DefaultActionHandler.class));
   Mockito.verify(myDisplayMock).setHandlerNextButton(Mockito.isA(DefaultActionHandler.class));
 }
 
 @Test
 public void testTrue() {
   Assert.assertNotNull(myCreateDialog);
 }
  
}
