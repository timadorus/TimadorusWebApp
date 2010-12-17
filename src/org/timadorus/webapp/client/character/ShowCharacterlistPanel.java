package org.timadorus.webapp.client.character;

import java.util.List;

import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.User;
import org.timadorus.webapp.client.rpc.service.CharacterService;
import org.timadorus.webapp.client.rpc.service.CharacterServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public final class ShowCharacterlistPanel extends FormPanel {
  
  private Grid grid;

  private User user;
  private TimadorusWebApp entry;

  VerticalPanel panel       = new VerticalPanel();
  HTML headline             = new HTML("<h1>Liste der registrierten Charaktere</h1>");

  private ShowCharacterlistPanel(TimadorusWebApp entryIn, User userIn) {
    super();

    this.entry = entryIn;
    this.user  = userIn;
    
    
    
    getCharactersFromServer();
    
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
      final int columns = 3;
      grid = new Grid(result.size(), columns);
      grid.setBorderWidth(0);
    
      int i = 0;
      for (final Character character : result) {
        final Button delete = new Button("Löschen");
        final Button details = new Button("Details");
        class MyHandler implements ClickHandler {
          public void onClick(ClickEvent event) {
            if (event.getSource().equals(delete)) {
              deleteCharacter(character);
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
        MyHandler handler = new MyHandler();
        delete.addClickHandler(handler);
        details.addClickHandler(handler);
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
}
