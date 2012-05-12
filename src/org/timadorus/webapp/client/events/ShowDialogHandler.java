package org.timadorus.webapp.client.events;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;

import com.google.gwt.event.shared.EventHandler;

public interface ShowDialogHandler extends EventHandler {

  public void show(DefaultTimadorusWebApp entry, Character character, User user);
  
}
