package org.timadorus.webapp.client;

import org.timadorus.webapp.client.character.TestCharacterValues;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public interface DefaultTimadorusWebApp {

  /**
   * @param loggedinIn
   *          the loggedin to set
   */
  public abstract void setLoggedin(boolean loggedinIn);

  /**
   * @return the loggedin
   */
  public abstract boolean isLoggedin();

  public abstract void showDialogBox(String title, String message);

  public abstract TestCharacterValues getTestValues();
  
  public <T extends EventHandler> void addHandler(GwtEvent.Type<T> type, final T handler);
  
  public void fireEvent(GwtEvent<? extends EventHandler> event);
}