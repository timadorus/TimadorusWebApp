package org.timadorus.webapp.tests.client.character.ui.ready;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.ready.ReadyDialog;

import com.google.gwt.user.client.ui.FormPanel;

public class ReadyDialogTest {
  private ReadyDialog myReadyDialog;
  
  @Mock ReadyDialog.Display myDisplayMock;
  @Mock DefaultTimadorusWebApp myDefaultTimadorusWebApp;
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    
    when(myDisplayMock.getFormPanel()).thenReturn(null);
    
    myReadyDialog = new ReadyDialog(myDisplayMock, myDefaultTimadorusWebApp);
  }
  
  @Test
  public void testReadyDialog() {
    Mockito.verify(myDisplayMock).setContragulationMessage(Mockito.anyString());
    Mockito.verify(myDisplayMock).setInformationMessage(Mockito.anyString());
  }
  
  @Test 
  public void testShow() {
    myReadyDialog.show(myDefaultTimadorusWebApp, null, null);
    
    verify(myDisplayMock).addToRootPanel((FormPanel) eq(null));
  }
 
}
