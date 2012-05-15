package org.timadorus.webapp.client.eventhandling.events;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.eventhandling.handler.ShowSelectCharShortHandler;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Grid;

public class ShowSelectCharacterShortEvent extends GwtEvent<ShowSelectCharShortHandler> {

  public static final GwtEvent.Type<ShowSelectCharShortHandler> SHOWDIALOG =
      new GwtEvent.Type<ShowSelectCharShortHandler>();

  private Character character;

  private User user;

  private Grid grid;

  private int row;

  private int column;

  public ShowSelectCharacterShortEvent(Grid grid, int row, int colum, User user, Character character) {
    super();
    this.character = character;
    this.user = user;
    this.grid = grid;
    this.row = row;
    this.column = colum;
  }

  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<ShowSelectCharShortHandler> getAssociatedType() {
    return SHOWDIALOG;
  }

  @Override
  protected void dispatch(ShowSelectCharShortHandler handler) {
    handler.showShort(grid, row, column, user, character);
  }

}
