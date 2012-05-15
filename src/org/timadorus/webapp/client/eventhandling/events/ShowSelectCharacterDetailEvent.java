package org.timadorus.webapp.client.eventhandling.events;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.eventhandling.handler.ShowSelectCharDetailHandler;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.ui.Grid;

public class ShowSelectCharacterDetailEvent extends GwtEvent<ShowSelectCharDetailHandler> {

  public static final GwtEvent.Type<ShowSelectCharDetailHandler> SHOWDIALOG =
      new GwtEvent.Type<ShowSelectCharDetailHandler>();

  private Character character;

  private User user;

  private Grid grid;

  private int row;

  private int column;

  public ShowSelectCharacterDetailEvent(Grid grid, int row, int column, User user, Character character) {
    super();
    this.character = character;
    this.user = user;
    this.grid = grid;
    this.row = row;
    this.column = column;
  }

  @Override
  public com.google.gwt.event.shared.GwtEvent.Type<ShowSelectCharDetailHandler> getAssociatedType() {
    return SHOWDIALOG;
  }

  @Override
  protected void dispatch(ShowSelectCharDetailHandler handler) {
    handler.showDetail(grid, row, column, user, character);
  }

}
