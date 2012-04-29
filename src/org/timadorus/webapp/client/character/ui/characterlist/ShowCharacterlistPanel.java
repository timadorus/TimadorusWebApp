package org.timadorus.webapp.client.character.ui.characterlist;

import java.util.List;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.showcharacter.ShowCharacterDialog;
import org.timadorus.webapp.client.rpc.service.CharacterService;
import org.timadorus.webapp.client.rpc.service.CharacterServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Panel to show a list of characters
 * 
 * @author aaz210
 * 
 */
public final class ShowCharacterlistPanel extends FormPanel {

  private Grid grid;

  private User user;

  private DefaultTimadorusWebApp entry;

  private VerticalPanel panel;

  private HTML headline;

  private PasswordTextBox passbox;

  private Button confirm;

  private Button back;

  private ShowCharacterlistPanel(DefaultTimadorusWebApp entryIn, User userIn) {
    super();

    this.entry = entryIn;
    this.user = userIn;

    panel = new VerticalPanel();
    headline = new HTML("<h1>Liste der registrierten Charaktere</h1>");
    passbox = new PasswordTextBox();
    confirm = new Button("Loeschen bestaetigen");
    back = new Button("Zurueck");

    getCharactersFromServer();

    // creates a handler for the back button
    class BackHandler implements ClickHandler {

      public void onClick(ClickEvent event) {
        onBackButtonClick();
      }
    }

    BackHandler bh = new BackHandler();
    back.addClickHandler(bh);

    grid = new Grid(1, 1);
    grid.setWidget(0, 0, new Label("Waiting for ajax response..."));

    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    panel.setWidth("100%");

    panel.add(headline);
    panel.add(grid);

    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

    RootPanel.get("information").clear();
  }

  public void onBackButtonClick() {
    RootPanel.get("content").add(ShowCharacterlistPanel.getShowCharacterlistPanel(entry, user));
  }

  /**
   * Gets all the characters of the current user from the server.
   */
  private void getCharactersFromServer() {
    CharacterServiceAsync characterServiceAsync = GWT.create(CharacterService.class);

    AsyncCallback<List<Character>> asyncCallback = new AsyncCallback<List<Character>>() {
      /**
       * Updates the character list.
       * 
       * @param result
       *          the list of all the current user's characters
       */
      public void onSuccess(List<Character> result) {
        if (result != null) {
          updateCharacterList(result);
        }
      }

      // Shows a dialog box with the error message.
      public void onFailure(Throwable caught) {
        getTimadorus().showDialogBox("Fehlermeldung", "Fehler bei der Abfrage der Charactere");
        System.out.println(caught);
      }
    };

    characterServiceAsync.getCharacterList(user, asyncCallback);
  }

  /**
   * Updates the character list.
   * 
   * @param characterList
   *          The characters of the user
   */
  private void updateCharacterList(List<Character> characterList) {
    if (characterList.size() > 0) {
      final int columns = 4;
      grid = new Grid(characterList.size(), columns);
      grid.setBorderWidth(0);

      int i = 0;
      for (final Character character : characterList) {
        final Button delete = new Button("Loeschen");
        final Button details = new Button("Details");

        class MyHandler implements ClickHandler {
          public void onClick(ClickEvent event) {
            if (event.getSource().equals(delete)) {
              onDeleteButtonClick();
            } else if (event.getSource().equals(details)) {
              onDetailsButtonClick(character);
            }
          }
        }

        // creates a click and keyboard handler for the confirm button
        class ConfirmHandler implements ClickHandler, KeyUpHandler {
          /**
           * Will be triggered if the button was clicked.
           * 
           * @param event
           *          The click event
           */
          public void onClick(ClickEvent event) {
            onConfirmDeleteCharacter(character);
          }

          /**
           * Will be triggered if the "Enter" button was hit while located in an input field
           * 
           * @param event
           *          The KeyUpEvent
           */
          public void onKeyUp(KeyUpEvent event) {
            if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
              onConfirmDeleteCharacter(character);
            }
          }
        }

        MyHandler handler = new MyHandler();
        delete.addClickHandler(handler);
        details.addClickHandler(handler);

        ConfirmHandler confirmHandler = new ConfirmHandler();
        confirm.addClickHandler(confirmHandler);
        passbox.addKeyUpHandler(confirmHandler);

        grid.setWidget(i, 0, ShowCharacterDialog.getShortDisplay(entry, character, getUser()).getFormPanel());
        grid.setWidget(i, 1, details);
        grid.setWidget(i, 2, delete);
        i++;
      }
    } else {
      grid = new Grid(1, 1);
      grid.setBorderWidth(0);
      grid.setWidget(0, 0, new Label("Es wurden keine Charaktere gefunden."));
    }
    panel.clear();
    panel.add(headline);
    panel.add(grid);
    setContent(panel);
  }

  public void onDetailsButtonClick(Character character) {
    grid.clear();
    panel.clear();
    headline = new HTML("<h1>Charakter-Details</h1>");
    grid.setWidget(0, 0, ShowCharacterDialog.getDetailDisplay(entry, character, user).getFormPanel());
    panel.add(headline);
    panel.add(grid);
  }

  public void onDeleteButtonClick() {
    grid.clear();
    panel.clear();
    final int rows = 4;
    grid = new Grid(rows, 1);
    headline = new HTML("<h1>Charakter löschen</h1>");
    grid.setWidget(0, 0, new Label("Sind Sie sich sicher, dass Sie diesen Charakter löschen wollen?"));
    grid.setWidget(1, 0, passbox);
    grid.setWidget(2, 0, confirm);
    final int row = 3;
    grid.setWidget(row, 0, back);
    panel.add(headline);
    panel.add(grid);
  }

  /**
   * Handles the character deletion.
   */
  private void onConfirmDeleteCharacter(Character character) {
    System.out.println("handle Event");
    if (passbox.getText().equals(user.getPassword())) {
      System.out.println("Deleting " + character.getName());
      deleteCharacter(character);
      showDialogBox("Information", "Ihr Charakter wurde erfolgreich entfernt!");
    } else {
      passbox.setText("");
      showDialogBox("Fehlermeldung", "Passwort falsch! Versuchen Sie es erneut!");
    }
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
          setContent(ShowCharacterlistPanel.getShowCharacterlistPanel(entry, user));
        }
      }

      public void onFailure(Throwable caught) {
        System.out.println(caught);
      }
    };
    characterServiceAsync.deleteCharacter(character, asyncCallback);
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

  /**
   * Gets the ShowCharacterlistPanel.
   * 
   * @param entry
   *          the entry point of the application
   * @param user
   *          the current user
   * @return the ShowCharacterlistPanel
   */
  public static final ShowCharacterlistPanel getShowCharacterlistPanel(DefaultTimadorusWebApp entry, User user) {
    return new ShowCharacterlistPanel(entry, user);
  }

  /**
   * Gets the entry point of the application.
   * 
   * @return the entry point of the application.
   */
  public DefaultTimadorusWebApp getTimadorus() {
    return this.entry;
  }

  /**
   * Gets the current user.
   * 
   * @return the current user.
   */
  public User getUser() {
    return user;
  }

  /**
   * Sets the current user.
   * 
   * @param userIn
   *          the user to be set as current user
   */
  public void setUser(User userIn) {
    this.user = userIn;
  }

  /**
   * Shows a dialog box with a title and message.
   * 
   * @param title
   *          the title of the dialog box
   * @param message
   *          the message in the dialog box
   */
  public void showDialogBox(String title, String message) {
    // Create the popup dialog box
    final DialogBox dialogBox = new DialogBox();

    dialogBox.setText(title);
    dialogBox.setAnimationEnabled(true);
    final Button closeButton = new Button("Close");

    // We can set the id of a widget by accessing its Element
    closeButton.getElement().setId("closeButton");

    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.addStyleName("dialogVPanel");

    dialogVPanel.add(new HTML((new StringBuffer().append("<b>").append(message).append("</b>")).toString()));
    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
    dialogVPanel.add(closeButton);
    dialogBox.setWidget(dialogVPanel);

    dialogBox.center();

    // Add a handler to close the DialogBox
    closeButton.addClickHandler(new ClickHandler() {
      /**
       * Will be triggered if the the button has been clicked.
       * 
       * @param event
       *          The ClickEvent object
       */
      public void onClick(ClickEvent event) {
        dialogBox.hide();
      }
    });
  }
}
