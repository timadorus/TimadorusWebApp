package org.timadorus.webapp.client.character;

import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.User;

import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public final class ShowCharacterlistPanel extends FormPanel {
  
  private Grid grid;

  private User user;
  private TimadorusWebApp entry;

  VerticalPanel panel       = new VerticalPanel();

  private ShowCharacterlistPanel(TimadorusWebApp entryIn, User userIn) {
    super();

    this.entry = entryIn;
    this.user  = userIn;

    HTML headline = new HTML("<h1>Charaktervorauswahl</h1>");
    
    // CHECKSTYLE OFF
    grid = new Grid(10, 1);
    grid.setBorderWidth(0);
    
    grid.setWidget(0,0,new Label("=== foo ==="));    
    for(int i = 1; i < 9; i++){
      Character character = new Character();
      character.setName("char "+(i-1));
      grid.setWidget(i, 0, ShowCharacterPanel.getShowCharacterPanel(entry, user, character));
    }
    grid.setWidget(9,0,new Label("=== bar ==="));
    // CHECKSTYLE ON    
    
    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    panel.setWidth("100%");

    panel.add(headline);
    panel.add(grid);

    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

    RootPanel.get("information").clear();
    //RootPanel.get("information").add(getInformation());
  }

  public static final ShowCharacterlistPanel getShowCharacterlistPanel(TimadorusWebApp entry, User user) {
    return new ShowCharacterlistPanel(entry, user);
  }

  public void setTimadorusWebApp(TimadorusWebApp webapp) {
    this.entry = webapp;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User userIn) {
    this.user = userIn;
  }
}
