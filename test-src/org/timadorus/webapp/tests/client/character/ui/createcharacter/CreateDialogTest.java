package org.timadorus.webapp.tests.client.character.ui.createcharacter;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.TestCharacterValues;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.createcharacter.CreateDialog;

public class CreateDialogTest {

 private CreateDialog myCreateDialog;
 
 @Mock private CreateDialog.Display myDisplayMock;
 @Mock private DefaultTimadorusWebApp myTimadorusWebAppMock;
 
 private User myUser;
 
 @Before
 public void setUp() {
   MockitoAnnotations.initMocks(this);
   
   myUser = new User();
   
   myCreateDialog = new CreateDialog(myDisplayMock, myTimadorusWebAppMock);
   
   when(myTimadorusWebAppMock.getTestValues()).thenReturn(new TestCharacterValues());
 }
 
 @Test
 public void testHandlerPremade() {
   ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
   verify(myDisplayMock).setHandlerPremade(theArgumentCaptor.capture());
   
   theArgumentCaptor.getValue().onAction();
   
   verify(myDisplayMock).setPremadeInformation(anyString());
 }
 
 @Test
 public void testHandlerCustom() {
   ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
   verify(myDisplayMock).setHandlerCustom(theArgumentCaptor.capture());
   
   theArgumentCaptor.getValue().onAction();
   
   verify(myDisplayMock).setCustomInformation(anyString());
 }
 
 @Test
 public void testHandlerNextButtonCustom() {
   when(myDisplayMock.isCustom()).thenReturn(Boolean.TRUE);
   
   ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
   verify(myDisplayMock).setHandlerNextButton(theArgumentCaptor.capture());
   
   theArgumentCaptor.getValue().onAction();
   
 }
 
 @Test
 public void testHandlerNextButtonPremade() {
   when(myDisplayMock.isCustom()).thenReturn(Boolean.FALSE);
   when(myDisplayMock.isPremade()).thenReturn(Boolean.TRUE);
   
   ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
   verify(myDisplayMock).setHandlerNextButton(theArgumentCaptor.capture());
   
   theArgumentCaptor.getValue().onAction();
   
 }
 
 @Test
 public void testHandlerNextButtonNotCustomNotPremade() {
   when(myDisplayMock.isCustom()).thenReturn(Boolean.FALSE);
   when(myDisplayMock.isPremade()).thenReturn(Boolean.FALSE);
   
   ArgumentCaptor<DefaultActionHandler> theArgumentCaptor = ArgumentCaptor.forClass(DefaultActionHandler.class);
   verify(myDisplayMock).setHandlerNextButton(theArgumentCaptor.capture());
   
   theArgumentCaptor.getValue().onAction();
   
 }
  
}
