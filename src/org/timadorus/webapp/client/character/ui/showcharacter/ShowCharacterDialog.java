package org.timadorus.webapp.client.character.ui.showcharacter;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;
import org.timadorus.webapp.client.character.ui.characterlist.ShowCharacterListDialog;
import org.timadorus.webapp.client.rpc.service.CharacterService;
import org.timadorus.webapp.client.rpc.service.CharacterServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;

public class ShowCharacterDialog extends DefaultDialog<ShowCharacterDialog.Display> {

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

    public void loadShowCharacterDialog(DefaultTimadorusWebApp entry, Character character, User user);

    public void loadShowCharacterListsDialog(DefaultTimadorusWebApp entry, User user);

    public void setContent(Widget w);
  }

  private Character character;

  private User user;

  public ShowCharacterDialog(Display display, DefaultTimadorusWebApp entry, Character character, User user) {
    super(display, entry);
    this.character = character;
    this.user = user;
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
    getDisplay().loadShowCharacterDialog(getEntry(), character, getUser());
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
          setContent(ShowCharacterListDialog.getDialog(getEntry(), getUser()).getFormPanel());
        }
      }

      public void onFailure(Throwable caught) {
        System.out.println(caught);
      }
    };
    characterServiceAsync.deleteCharacter(character, asyncCallback);
  }

  /**
   * Clears the panel and adds a widget
   * 
   * @param w
   *          the widget to be added
   */
  private void setContent(Widget w) {
    getDisplay().setContent(w);
  }

  public static ShowCharacterDialog getShortDisplay(DefaultTimadorusWebApp entry, Character character, User user) {
    ShowCharacterDialog.Display display = new ShowCharacterWidget(character, ShowCharacterWidget.SHORT_MODE);
    ShowCharacterDialog dialog = new ShowCharacterDialog(display, entry, character, user);
    return dialog;
  }

  public static ShowCharacterDialog getDetailDisplay(DefaultTimadorusWebApp entry, Character character, User user) {
    ShowCharacterDialog.Display display = new ShowCharacterWidget(character, ShowCharacterWidget.DETAIL_MODE);
    ShowCharacterDialog dialog = new ShowCharacterDialog(display, entry, character, user);
    return dialog;
  }
}
