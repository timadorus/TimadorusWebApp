package org.timadorus.webapp.client.character.ui;

import org.timadorus.webapp.client.TimadorusWebApp;

import com.google.gwt.user.client.ui.FormPanel;

public class DefaultWidget<T extends DefaultDisplay> {

  private T display;

  private TimadorusWebApp entry;

  public DefaultWidget(T display, TimadorusWebApp entry) {
    super();
    this.display = display;
    this.entry = entry;
  }

  protected T getDisplay() {
    return display;
  }

  public FormPanel getFormPanel() {
    return display.getFormPanel();
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}
