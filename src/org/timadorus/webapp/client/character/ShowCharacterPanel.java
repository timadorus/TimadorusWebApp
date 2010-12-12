package org.timadorus.webapp.client.character;

import java.util.HashMap;
import java.util.Map;

import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.User;

import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;

public final class ShowCharacterPanel extends FormPanel {

  private final int rows = 3;
  private final int columns = 3;
  
  private Grid grid = new Grid(rows, columns);
  private Label test1 = new Label("Name:");
  private Label test3 = new Label("test3");
  private Label test4 = new Label("test4");
  private Label test5 = new Label("test5");
  private Label test6 = new Label("test6");
  private Label test7 = new Label("test7");
  private Label test8 = new Label("test8");
  private Label test9 = new Label("test9");

  private User user;
  private Character character;

  private static Map<Character, ShowCharacterPanel> showCharacterPanel = new HashMap<Character, ShowCharacterPanel>();

  private ShowCharacterPanel(TimadorusWebApp entryIn, User userIn, Character characterIn) {
    super();

    this.user = userIn;
    this.character = characterIn;

    // CHECKSTYLE OFF
    grid.setWidget(0, 0, test1);
    grid.setWidget(0, 1, new Label(character.getName()));
    grid.setWidget(0, 2, test3);
    grid.setWidget(1, 0, test4);
    grid.setWidget(1, 1, test5);
    grid.setWidget(1, 2, test6);
    grid.setWidget(2, 0, test7);
    grid.setWidget(2, 1, test8);
    grid.setWidget(2, 2, test9);
    // CHECKSTYLE ON

    setWidget(grid);
    setStyleName("formPanel");
  }

  public static final ShowCharacterPanel getShowCharacterPanel(TimadorusWebApp e, User u, Character c) {
    if (!ShowCharacterPanel.showCharacterPanel.containsKey(c)) {
      ShowCharacterPanel.showCharacterPanel.put(c, new ShowCharacterPanel(e, u, c));
    }
    return ShowCharacterPanel.showCharacterPanel.get(c);
  }

  public User getUser() {
    return user;
  }

  public void setUser(User userIn) {
    this.user = userIn;
  }
}
