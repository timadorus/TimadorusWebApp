package org.timadorus.webapp.client;

import org.timadorus.webapp.client.character.TestCharacterValues;

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

}