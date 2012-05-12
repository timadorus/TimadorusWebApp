package org.timadorus.webapp.client.eventhandling.events;

import org.timadorus.webapp.client.eventhandling.handler.ShowLoginHandler;

import com.google.gwt.event.shared.GwtEvent;

public class ShowLoginEvent extends GwtEvent<ShowLoginHandler> {

  public static final GwtEvent.Type<ShowLoginHandler> SHOWDIALOG = new GwtEvent.Type<ShowLoginHandler>();
  
  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<ShowLoginHandler> getAssociatedType() {
    return SHOWDIALOG;
  }

  @Override
  protected void dispatch(ShowLoginHandler handler) {
    handler.showLogin();
  }

}
