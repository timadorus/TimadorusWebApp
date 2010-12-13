package org.timadorus.webapp.client.character;

import java.util.List;

import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.User;
import org.timadorus.webapp.client.rpc.service.CharacterService;
import org.timadorus.webapp.client.rpc.service.CharacterServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
  HTML headline             = new HTML("<h1>Lister der registrierten Charaktere</h1>");

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
      grid = new Grid(result.size(), 1);
      grid.setBorderWidth(0);
    
      int i = 0;
      for (Character character : result) {
        grid.setWidget(i++, 0, ShowCharacterPanel.getShowShortCharacterPanel(entry, user, character));
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
