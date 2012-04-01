package org.timadorus.webapp.client.register;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;

public class RegisterHandler implements ClickHandler, KeyUpHandler {

  private RegisterPanel myPanel;

  public RegisterHandler(RegisterPanel panel) {

    myPanel = panel;
  }

  /**
   * Will be triggered if the button was clicked.
   * 
   * @param event
   *          The ClickEvent object
   */
  public void onClick(ClickEvent event) {
    System.out.println("Submit Button geklickt");
    handleEvent();
  }

  /**
   * Will be triggered if the "Enter" button was hit while located in an input field.
   * 
   * @param event
   *          The KeyUpEvent object
   */
  public void onKeyUp(KeyUpEvent event) {
    if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
      handleEvent();
    }
  }

  private void handleEvent() {
    System.out.println("handle Event");
    myPanel.tryRegisterUser();
  }
}
