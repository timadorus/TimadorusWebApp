package org.timadorus.webapp.tests.character.ui.ready;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.ready.ReadyDialog;

public class ReadyDialogTest {
  private ReadyDialog myReadyDialog;
  
  @Mock ReadyDialog.Display myDisplayMock;
  @Mock DefaultTimadorusWebApp myDefaultTimadorusWebApp;
  
  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    
    myReadyDialog = new ReadyDialog(myDisplayMock, myDefaultTimadorusWebApp);
  }
  
  @Test
  public void testReadyDialog() {
    Mockito.verify(myDisplayMock).setContragulationMessage(Mockito.anyString());
    Mockito.verify(myDisplayMock).setInformationMessage(Mockito.anyString());
  }
 
}
