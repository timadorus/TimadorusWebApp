package org.timadorus.webapp.client.character.ui.showcharacter;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;
import org.timadorus.webapp.client.eventhandling.events.ShowCharacterEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowCharacterListEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectCharacterDetailEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectCharacterShortEvent;
import org.timadorus.webapp.client.eventhandling.handler.ShowDialogHandler;
import org.timadorus.webapp.client.eventhandling.handler.ShowSelectCharDetailHandler;
import org.timadorus.webapp.client.eventhandling.handler.ShowSelectCharShortHandler;
import org.timadorus.webapp.client.rpc.service.CharacterService;
import org.timadorus.webapp.client.rpc.service.CharacterServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Grid;

public class ShowCharacterDialog extends DefaultDialog<ShowCharacterDialog.Display> implements
    ShowSelectCharShortHandler, ShowSelectCharDetailHandler, ShowDialogHandler {

  public interface Display extends DefaultDisplay {

    /**
     * This event is called if the delete button is clicked. The deletion of the character is here not confirmed.
     * 
     * @param handler
     */
    public void addDeleteButtonHandler(DefaultActionHandler handler);

    /**
     * This event is called if the character shall be deleted and the action is confirmed.
     * 
     * @param handler
     */
    public void addCharacterDeleteHandler(CharacterActionHandler handler);

    public void addBackButtonHandler(DefaultActionHandler handler);

    public void addBack2ButtonHandler(DefaultActionHandler handler);

    public void onDeleteButtonClick();

    public void characterDeleteSuccessfull();

    public void characterDeleteFailure();

    public void loadShowCharacterListsDialog(DefaultTimadorusWebApp entry, User user);
  }

  private Character character;

  private User user;

  public ShowCharacterDialog(Display display, DefaultTimadorusWebApp entry, Character character, User user) {
    super(display, entry);
    this.character = character;
    this.user = user;
    entry.addHandler(ShowSelectCharacterShortEvent.SHOWDIALOG, this);
    entry.addHandler(ShowSelectCharacterDetailEvent.SHOWDIALOG, this);
  }

  private void initDisplay(Display display) {
    setDisplay(display);
    display.addBackButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        onBackButtonClick();
      }
    });
    display.addBack2ButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        onBackButton2Click();
      }
    });
    display.addDeleteButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        getDisplay().onDeleteButtonClick();
      }
    });

    display.addCharacterDeleteHandler(new CharacterActionHandler() {

      @Override
      public void onAction(Character character, String password) {
        System.out.println("handle Event");
        if (password.equals(getUser().getPassword())) {
          System.out.println("Deleting " + character.getName());
          deleteCharacter(character);
          getDisplay().characterDeleteSuccessfull();
        } else {
          getDisplay().characterDeleteFailure();
        }
      }
    });
  }

  public User getUser() {
    return user;
  }

  public void onBackButton2Click() {
    getEntry().fireEvent(new ShowCharacterEvent(getUser(), character));
  }

  public void onBackButtonClick() {
    getDisplay().loadShowCharacterListsDialog(getEntry(), getUser());
  }

  /**
   * Deletes the character.
   * 
   * @param character
   *          the character to delete
   */
  private void deleteCharacter(Character character) {
    CharacterServiceAsync characterServiceAsync = GWT.create(CharacterService.class);
    AsyncCallback<String> asyncCallback = new AsyncCallback<String>() {

      public void onSuccess(String result) {
        if (result != null) {
          if (result.equals("OK")) {
            System.out.println("Successfully deleted");
          } else {
            System.out.println("Unsuccessfully deleted");
          }
          // setContent(ShowCharacterListDialog.getDialog(getEntry(), getUser()).getFormPanel());
          getEntry().fireEvent(new ShowCharacterListEvent(getUser()));
        }
      }

      public void onFailure(Throwable caught) {
        System.out.println(caught);
      }
    };
    characterServiceAsync.deleteCharacter(character, asyncCallback);
  }

  // public static ShowCharacterDialog getShortDisplay(DefaultTimadorusWebApp entry, Character character, User user) {
  //
  // ShowCharacterDialog dialog = new ShowCharacterDialog(null, entry, character, user);
  // return dialog;
  // }

  public static ShowCharacterDialog getDetailDisplay(DefaultTimadorusWebApp entry, Character character, User user) {

    ShowCharacterDialog dialog = new ShowCharacterDialog(null, entry, character, user);
    return dialog;
  }

  @Override
  public void showShort(Grid grid, int row, int column, User user, Character character) {
    this.character = character;
    this.user = user;
    ShowCharacterDialog.Display display = new ShowCharacterWidget(character, ShowCharacterWidget.SHORT_MODE);
    initDisplay(display);
    grid.setWidget(row, column, getFormPanel());
  }

  @Override
  public void showDetail(Grid grid, int row, int column, User user, Character character) {
    this.character = character;
    this.user = user;
    ShowCharacterDialog.Display display = new ShowCharacterWidget(character, ShowCharacterWidget.DETAIL_MODE);
    initDisplay(display);
    grid.setWidget(row, column, getFormPanel());
  }

  @Override
  public void show(DefaultTimadorusWebApp entry, Character character, User user) {
    this.character = character;
    this.user = user;
    ShowCharacterDialog.Display display = new ShowCharacterWidget(character, ShowCharacterWidget.DETAIL_MODE);
    initDisplay(display);
  }
}
