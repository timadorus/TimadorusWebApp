package org.timadorus.webapp.tests.character.ui.ready;

import org.junit.Assert;
import org.junit.Test;
import org.timadorus.webapp.client.character.ui.ready.ReadyDialog;

import com.google.gwt.user.client.ui.FormPanel;

public class ReadyDialogTest {

  private final class MyDisplay implements ReadyDialog.Display {
    private String myInformationMsg;
    private String myCongratulationMsg;

    @Override
    public FormPanel getFormPanel() {
      return null;
    }

    @Override
    public void setInformationMessage(String msg) {
      myInformationMsg = msg;
    }

    public String getInformationMsg() {
      return myInformationMsg;
    }

    @Override
    public void setContragulationMessage(String msg) {
      myCongratulationMsg = msg;
    }

    public String getCongratulationMsg() {
      return myCongratulationMsg;
    }
    
  }

  @Test
  public void testReadyDialog() {
    MyDisplay theDisplay = new MyDisplay();
    new ReadyDialog(theDisplay, null);
    
    Assert.assertNotNull("The InformationMessage shouldn't be null", theDisplay.getInformationMsg());
    Assert.assertNotNull("The CongratulationMessage shouldn't be null", theDisplay.getCongratulationMsg());
  }
 
}
