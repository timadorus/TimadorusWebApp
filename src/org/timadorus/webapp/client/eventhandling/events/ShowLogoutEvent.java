package org.timadorus.webapp.client.eventhandling.events;

import org.timadorus.webapp.client.eventhandling.handler.ShowLogoutHandler;

import com.google.gwt.event.shared.GwtEvent;

public class ShowLogoutEvent extends GwtEvent<ShowLogoutHandler> {

  public static final GwtEvent.Type<ShowLogoutHandler> SHOWLOGOUT = new GwtEvent.Type<ShowLogoutHandler>();
  
  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<ShowLogoutHandler> getAssociatedType() {
    return SHOWLOGOUT;
  }

  @Override
  protected void dispatch(ShowLogoutHandler handler) {
    handler.showLogout();
  }

}
