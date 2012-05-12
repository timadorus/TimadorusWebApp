package org.timadorus.webapp.client.character.ui;

import org.timadorus.webapp.client.DefaultTimadorusWebApp;

import com.google.gwt.user.client.ui.FormPanel;

public class DefaultDialog<T extends DefaultDisplay> {

  private T display;

  private DefaultTimadorusWebApp entry;

  public DefaultDialog(T display, DefaultTimadorusWebApp entry) {
    super();
    this.display = display;
    this.entry = entry;
  }

  protected T getDisplay() {
    return display;
  }

  protected void setDisplay(T display) {
    this.display = display;
  }

  public FormPanel getFormPanel() {
    return display.getFormPanel();
  }

  public DefaultTimadorusWebApp getEntry() {
    return entry;
  }
}
