package org.timadorus.webapp.client.eventhandling.handler;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.user.client.ui.Grid;

public interface ShowSelectCharShortHandler extends EventHandler {

  public void showShort(Grid grid, int row, int colum, User user, Character character);
  
}
