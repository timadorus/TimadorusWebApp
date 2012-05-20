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
import org.timadorus.webapp.client.eventhandling.events.ShowCharacterListEvent;
import org.timadorus.webapp.client.eventhandling.handler.ShowDialogHandler;
import org.timadorus.webapp.client.service.Service;
import org.timadorus.webapp.client.service.ServiceAsync;
import org.timadorus.webapp.client.service.ServiceType;
import org.timadorus.webapp.shared.Action;
import org.timadorus.webapp.shared.Response;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class ShowCharacterListDialog extends DefaultDialog<ShowCharacterListDialog.Display> implements
    ShowDialogHandler {

  public interface Display extends DefaultDisplay {

    public void addBackButtonHandler(DefaultActionHandler handler);

    public void addDeleteConfirmedHandler(CharacterActionHandler handler);

    public void addDetailsButtonHandler(CharacterActionHandler handler);

    public void onDeleteButtonClick();

    public void characterDeleteSuccessfull();

    public void characterDeleteFailure();

    public void setWidget(Widget widget);

    public void loadShowCharacterDialog(DefaultTimadorusWebApp entry, Character character, User user);

    public void loadShowCharacterListDialog(DefaultTimadorusWebApp entry, User user);

  }

  private User user;
  
  private final ServiceAsync<Character, String> delService = GWT.create(Service.class);

  public ShowCharacterListDialog(Display display, DefaultTimadorusWebApp entry, User user) {
    super(display, entry);
    this.user = user;
    entry.addHandler(ShowCharacterListEvent.SHOWDIALOG, this);
    if (display != null) {
      setDisplay(display);
    }

  }

  @Override
  protected void setDisplay(Display display) {
    super.setDisplay(display);
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
        getDisplay().loadShowCharacterDialog(getEntry(), character, getUser());
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
    
    
    Action<Character> action = new Action<Character>(ServiceType.DELCHARACTER, character);
    AsyncCallback<Response<String>> response = new AsyncCallback<Response<String>>() {

      @Override
      public void onFailure(Throwable caught) {
        System.out.println(caught);
      }

      @Override
      public void onSuccess(Response<String> result) {
        if (result.getResult() != null) {
          if (result.getResult().equals("OK")) {
            System.out.println("Successfully deleted");
          } else {
            System.out.println("Unsuccessfully deleted");
          }
          setContent(ShowCharacterListDialog.getDialog(getEntry(), getUser()).getFormPanel());
        }
      }
    };
    
    delService.execute(action, response);
    
    
    /*CharacterServiceAsync characterServiceAsync = GWT.create(CharacterService.class);
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
    */
  }

  public void onBackButtonClick() {
    getDisplay().loadShowCharacterListDialog(getEntry(), getUser());
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
    // List<Character> characterList = new CharacterListSync(user).getCharacterList();
    // if (characterList == null) {
    // entry.showDialogBox("Fehlermeldung", "Fehler bei der Abfrage der Charactere");
    // characterList = new ArrayList<Character>();
    // }
    //
    // ShowCharacterListDialog.Display display = new ShowCharacterListWidget(entry, user, characterList);
    ShowCharacterListDialog dialog = new ShowCharacterListDialog(null, entry, user);
    return dialog;
  }

  @Override
  public void show(DefaultTimadorusWebApp entry, Character character, User user) {
    // loading widget
    List<Character> characterList = new CharacterListSync(user).getCharacterList();
    if (characterList == null) {
      entry.showDialogBox("Fehlermeldung", "Fehler bei der Abfrage der Charactere");
      characterList = new ArrayList<Character>();
    }

    ShowCharacterListDialog.Display display = new ShowCharacterListWidget(entry, user, characterList);
    setDisplay(display);

    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Liste der registrierten Charaktere"));
    RootPanel.get("content").add(this.getFormPanel());
  }
}
