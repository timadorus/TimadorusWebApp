package org.timadorus.webapp.client.character;

import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.User;

import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;

public final class ShowCharacterPanel extends FormPanel {

  public static final int SHORT_MODE = 1;
  public static final int DETAIL_MODE = 2;
  
  private final int rows = 3;
  private final int columns = 4;
  private final int fourthCol = 3;
  
  private Grid grid = new Grid(rows, columns);
  private Label name = new Label("Name:");
  private Label gender = new Label("Gender:");
  private Label race = new Label("Race:");
  private Label cclass = new Label("Klasse:");
  private Label faction = new Label("Faction:");

  private User user;
  private Character character;

  private ShowCharacterPanel(TimadorusWebApp entryIn, User userIn, Character characterIn, int modeIn) {
    super();

    this.user = userIn;
    this.character = characterIn;

    if (modeIn == SHORT_MODE) {
      buildShortMode();
    } else if (modeIn == DETAIL_MODE) {
      buildDetailMode();
    }
  }
  
  private void buildShortMode() {
    grid.setBorderWidth(1);
    
    // Display name
    grid.setWidget(0, 0, name);
    grid.setWidget(0, 1, new Label(character.getName()));
    
    // Display gender
    grid.setWidget(0, 2, gender);
    grid.setWidget(0, fourthCol, new Label(character.getGender()));
    
    // Display race
    grid.setWidget(1, 0, race);
    if (character.getRace() != null) {
      grid.setWidget(1, 1, new Label(character.getRace().getName()));
    } else {
      grid.setWidget(1, 1, new Label("--"));
    }
    
    // Display character class
    grid.setWidget(1, 2, cclass);
    if (character.getCharClass() != null) {
      grid.setWidget(1, fourthCol, new Label(character.getCharClass().getName()));
    } else {
      grid.setWidget(1, fourthCol, new Label("--"));
    }
    
    // Display faction
    grid.setWidget(2, 0, faction);
    if (character.getFaction() != null) {
      grid.setWidget(2, 1, new Label(character.getFaction().getName()));
    } else {
      grid.setWidget(2, 1, new Label("--"));
    }

    setWidget(grid);
    setStyleName("formPanel");
  }
  
  private void buildDetailMode() {
    // TODO: fill
  }

  public static final ShowCharacterPanel getShowShortCharacterPanel(TimadorusWebApp e, User u, Character c) {
    return new ShowCharacterPanel(e, u, c, ShowCharacterPanel.SHORT_MODE);
  }

  public static final ShowCharacterPanel getShowDetailCharacterPanel(TimadorusWebApp e, User u, Character c) {
    return new ShowCharacterPanel(e, u, c, ShowCharacterPanel.DETAIL_MODE);
  }

  public User getUser() {
    return user;
  }

  public void setUser(User userIn) {
    this.user = userIn;
  }
}
