package org.timadorus.webapp.client.character;

import java.util.List;

import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.User;
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

public final class ShowCharacterlistPanel extends FormPanel {
  
  private Grid grid;

  private User user;
  private TimadorusWebApp entry;

  VerticalPanel panel       = new VerticalPanel();
  HTML headline             = new HTML("<h1>Liste der registrierten Charaktere</h1>");
  PasswordTextBox passbox   = new PasswordTextBox();
  Button confirm            = new Button("Löschen bestätigen");
  Button back               = new Button("Zurück");

  private ShowCharacterlistPanel(TimadorusWebApp entryIn, User userIn) {
    super();

    this.entry = entryIn;
    this.user  = userIn;
    
    getCharactersFromServer();
    
    class BackHandler implements ClickHandler {
      
      public void onClick(ClickEvent event) {
        RootPanel.get("content").add(ShowCharacterlistPanel.getShowCharacterlistPanel(entry, user));
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
  
  private void getCharactersFromServer() {
    CharacterServiceAsync characterServiceAsync = GWT.create(CharacterService.class);

    AsyncCallback<List<Character>> asyncCallback = new AsyncCallback<List<Character>>() {
      public void onSuccess(List<Character> result) {
        if (result != null) {
          updateCharacterList(result);
        }
      }

      public void onFailure(Throwable caught) {
        getTimadorus().showDialogBox("Fehlermeldung", "Fehler bei der Abfrage der Charactere");
        System.out.println(caught);
      }
    };
    
    characterServiceAsync.getCharacterList(user, asyncCallback);
  }
  
  private void updateCharacterList(List<Character> result) {
    if (result.size() > 0) {
      final int columns = 4;
      grid = new Grid(result.size(), columns);
      grid.setBorderWidth(0);
    
      int i = 0;
      for (final Character character : result) {
        final Button delete = new Button("Löschen");
        final Button details = new Button("Details");        
        
        class MyHandler implements ClickHandler {
          public void onClick(ClickEvent event) {
            if (event.getSource().equals(delete)) {
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
            } else if (event.getSource().equals(details)) {
              grid.clear();
              panel.clear();
              headline = new HTML("<h1>Charakter-Details</h1>");
              grid.setWidget(0, 0, ShowCharacterPanel.getShowDetailCharacterPanel(entry, user, character));
              panel.add(headline);
              panel.add(grid);
            }      
          }      
        }
        
        class ConfirmHandler implements ClickHandler, KeyUpHandler {
          /**
           * Will be triggered if the button was clicked.
           * 
           * @param event The click event
           */
          public void onClick(ClickEvent event) {
            System.out.println("Löschung bestätigen Button geklickt");
            handleEvent();
          }

          /**
           * Will be triggered if the "Enter" button was hit while located in an input field
           * 
           * @param event The KeyUpEvent
           */
          public void onKeyUp(KeyUpEvent event) {
            if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
              handleEvent();
            }
          }

          private void handleEvent() {
            System.out.println("handle Event");
            if (passbox.getText().equals(user.getPassword())) {
              System.out.println("Deleting " + character.getName());
              deleteCharacter(character);
              showDialogBox("Information", "Ihr Charakter wurde erfolgreich gelöscht!");
            } else {
              passbox.setText("");
              showDialogBox("Fehlermeldung", "Passwort falsch! Versuchen Sie es erneut!");
            }
          }
        }
        
        MyHandler handler = new MyHandler();
        delete.addClickHandler(handler);
        details.addClickHandler(handler);
        
        ConfirmHandler handler2 = new ConfirmHandler();
        confirm.addClickHandler(handler2);
        passbox.addKeyUpHandler(handler2);
        
        grid.setWidget(i, 0, ShowCharacterPanel.getShowShortCharacterPanel(entry, user, character));
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
  
  private void setContent(Widget w) {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(w);
  }

  public static final ShowCharacterlistPanel getShowCharacterlistPanel(TimadorusWebApp entry, User user) {
    return new ShowCharacterlistPanel(entry, user);
  }

  public void setTimadorusWebApp(TimadorusWebApp webapp) {
    this.entry = webapp;
  }
  
  public TimadorusWebApp getTimadorus() {
    return this.entry;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User userIn) {
    this.user = userIn;
  }
  
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
      public void onClick(ClickEvent event) {
        dialogBox.hide();
      }
    });
  }
}
