package org.timadorus.webapp.client.character.ui.characterlist;

import java.util.ArrayList;
import java.util.List;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.DefaultDialog;
import org.timadorus.webapp.client.character.ui.DefaultDisplay;
import org.timadorus.webapp.client.character.ui.showcharacter.CharacterActionHandler;
import org.timadorus.webapp.client.character.ui.showcharacter.ShowCharacterDialog;
import org.timadorus.webapp.client.rpc.service.CharacterService;
import org.timadorus.webapp.client.rpc.service.CharacterServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class ShowCharacterListDialog extends DefaultDialog<ShowCharacterListDialog.Display> {

  public interface Display extends DefaultDisplay {

    public void addBackButtonHandler(DefaultActionHandler handler);

    public void addDeleteConfirmedHandler(CharacterActionHandler handler);

    public void addDetailsButtonHandler(CharacterActionHandler handler);

    public void onDeleteButtonClick();

    public void characterDeleteSuccessfull();

    public void characterDeleteFailure();

    public void setWidget(Widget widget);

  }

  private User user;

  public ShowCharacterListDialog(Display display, DefaultTimadorusWebApp entry, User user) {
    super(display, entry);
    this.user = user;
    getDisplay().addBackButtonHandler(new DefaultActionHandler() {

      @Override
      public void onAction() {
        onBackButtonClick();
      }
    });

    getDisplay().addDeleteConfirmedHandler(new CharacterActionHandler() {

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
    getDisplay().addDetailsButtonHandler(new CharacterActionHandler() {

      @Override
      public void onAction(Character character, String password) {
        getDisplay().setWidget(ShowCharacterDialog.getDetailDisplay(getEntry(), character, getUser()).getFormPanel());
      }
    });
  }

  /**
   * Deletes a character.
   * 
   * @param character
   *          the character to be deleted
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

  public void onBackButtonClick() {
    RootPanel.get("content").add(ShowCharacterListDialog.getDialog(getEntry(), getUser()).getFormPanel());
  }

  /**
   * Clears the panel and adds a widget to it.
   * 
   * @param w
   *          the widget to be added.
   */
  private void setContent(Widget w) {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(w);
  }

  public User getUser() {
    return user;
  }

  public static ShowCharacterListDialog getDialog(DefaultTimadorusWebApp entry, User user) {
    List<Character> characterList = new CharacterListSync(user).getCharacterList();
    if (characterList == null) {
      entry.showDialogBox("Fehlermeldung", "Fehler bei der Abfrage der Charactere");
      characterList = new ArrayList<Character>();
    }

    ShowCharacterListDialog.Display display = new ShowCharacterListWidget(entry, user, characterList);
    ShowCharacterListDialog dialog = new ShowCharacterListDialog(display, entry, user);
    return dialog;
  }
}
