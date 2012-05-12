package org.timadorus.webapp.client.eventhandling.events;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.eventhandling.handler.ShowDialogHandler;

import com.google.gwt.event.shared.GwtEvent;

public class ShowProfileEvent extends GwtEvent<ShowDialogHandler> {

  public static final GwtEvent.Type<ShowDialogHandler> SHOWDIALOG = new GwtEvent.Type<ShowDialogHandler>();
  
  private DefaultTimadorusWebApp entry;
  private Character character;
  private User user;
  
  public ShowProfileEvent(User user) {
    super();
    this.entry = null;
    this.character = null;
    this.user = user;
  }

  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<ShowDialogHandler> getAssociatedType() {
    return SHOWDIALOG;
  }

  @Override
  protected void dispatch(ShowDialogHandler handler) {
    handler.show(entry, character, user);
  }

}
