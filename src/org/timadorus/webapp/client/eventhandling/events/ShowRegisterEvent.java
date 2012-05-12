package org.timadorus.webapp.client.eventhandling.events;

import org.timadorus.webapp.client.eventhandling.handler.ShowHandler;

import com.google.gwt.event.shared.GwtEvent;

public class ShowRegisterEvent extends GwtEvent<ShowHandler> {

  public static final GwtEvent.Type<ShowHandler> SHOWDIALOG = new GwtEvent.Type<ShowHandler>();
  
  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<ShowHandler> getAssociatedType() {
    return SHOWDIALOG;
  }

  @Override
  protected void dispatch(ShowHandler handler) {
    handler.show();
  }

}
