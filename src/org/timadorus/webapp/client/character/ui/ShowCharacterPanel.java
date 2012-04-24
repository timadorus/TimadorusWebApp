package org.timadorus.webapp.client.character.ui;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.attributes.Skill;
import org.timadorus.webapp.client.character.attributes.Stat;
import org.timadorus.webapp.client.character.ui.characterlist.ShowCharacterlistPanel;
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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

// Panel to show information about the character
public final class ShowCharacterPanel extends FormPanel {

  public static final int SHORT_MODE = 1;

  public static final int DETAIL_MODE = 2;

  private final int rows = 3;

  private final int columns = 4;

  private final int fourthCol = 3;

  private final int detailRows = 11;

  private final int detailColumns = 2;

  DefaultTimadorusWebApp entry;

  VerticalPanel panel = new VerticalPanel();

  private Grid grid = new Grid(rows, columns);

  private Grid detailGrid = new Grid(detailRows, detailColumns);

  private Label name = new Label("Name:");

  private Label gender = new Label("Gender:");

  private Label race = new Label("Race:");

  private Label cclass = new Label("Klasse:");

  private Label faction = new Label("Faction:");

  private Button back = new Button("Zurück");

  private Button delete = new Button("Löschen");

  private Button confirm = new Button("Löschung bestätigen");

  private PasswordTextBox passbox = new PasswordTextBox();

  Button back2 = new Button("Zurück");

  private User user;

  private Character character;

  private ShowCharacterPanel(final DefaultTimadorusWebApp entryIn, final User userIn, final Character characterIn,
                             int modeIn) {
    super();

    this.entry = entryIn;
    this.user = userIn;
    this.character = characterIn;

    if (modeIn == SHORT_MODE) {
      buildShortMode();
    } else if (modeIn == DETAIL_MODE) {
      buildDetailMode();
    }

    // creates a handler for delete and back buttons
    class MyHandler implements ClickHandler {

      /**
       * Will be triggered if the the button has been clicked.
       * 
       * @param event
       *          The ClickEvent object
       */
      public void onClick(ClickEvent event) {
        if (event.getSource().equals(delete)) {
          onDeleteButtonClick();
        } else if (event.getSource().equals(back)) {
          RootPanel.get("content").clear();
          RootPanel.get("content").add(ShowCharacterlistPanel.getShowCharacterlistPanel(entryIn, userIn));
        } else if (event.getSource().equals(back2)) {
          RootPanel.get("content").clear();
          RootPanel.get("content").add(ShowCharacterPanel.getShowDetailCharacterPanel(entry, user, character));
        }
      }
    }

    // creates a handler for confirm button
    class ConfirmHandler implements ClickHandler, KeyUpHandler {
      /**
       * Will be triggered if the button was clicked.
       * 
       * @param event
       *          The click event
       */
      public void onClick(ClickEvent event) {
        System.out.println("Löschung bestätigen Button geklickt");
        handleEvent();
      }

      /**
       * Will be triggered if the "Enter" button was hit while located in an input field.
       * 
       * @param event
       *          The KeyUpEvent object
       */
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          handleEvent();
        }
      }

      /**
       * Handles the character deletion.
       */
      private void handleEvent() {
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
    }

    MyHandler handler = new MyHandler();
    delete.addClickHandler(handler);
    back.addClickHandler(handler);
    back2.addClickHandler(handler);
    ConfirmHandler handler2 = new ConfirmHandler();
    confirm.addClickHandler(handler2);
    passbox.addKeyUpHandler(handler2);
  }

  public void onDeleteButtonClick() {
    RootPanel.get("content").clear();
    final int gridRows = 4;
    grid = new Grid(gridRows, 1);
    HTML headline = new HTML("<h1>Charakter löschen</h1>");
    grid.setWidget(0, 0, new Label("Sind Sie sich sicher, dass Sie diesen Charakter löschen wollen?"));
    grid.setWidget(1, 0, passbox);
    grid.setWidget(2, 0, confirm);
    final int row = 3;
    grid.setWidget(row, 0, back2);
    panel.clear();
    panel.add(headline);
    panel.add(grid);
    RootPanel.get("content").add(panel);
  }

  /**
   * Builds the panel with only low detail of the character.
   */
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

  /**
   * Builds the panel with high detail of the character.
   */
  private void buildDetailMode() {
    detailGrid.setBorderWidth(1);

    // Display name
    detailGrid.setWidget(0, 0, new HTML("<b>Name:</b>"));
    detailGrid.setWidget(0, 1, new Label(character.getName()));

    // Display gender
    detailGrid.setWidget(1, 0, new HTML("<b>Gender:</b>"));
    detailGrid.setWidget(1, 1, new Label(character.getGender()));

    // Display race
    detailGrid.setWidget(2, 0, new HTML("<b>Race:</b>"));
    if (character.getRace() != null) {
      detailGrid.setWidget(2, 1, new Label(character.getRace().getName()));
    } else {
      detailGrid.setWidget(2, 1, new Label("--"));
    }

    // Display character class
    detailGrid.setWidget(3, 0, new HTML("<b>Class:</b>"));
    if (character.getCharClass() != null) {
      detailGrid.setWidget(3, 1, new Label(character.getCharClass().getName()));
    } else {
      detailGrid.setWidget(3, 1, new Label("--"));
    }

    // Display faction
    detailGrid.setWidget(4, 0, new HTML("<b>Faction:</b>"));
    if (character.getFaction() != null) {
      detailGrid.setWidget(4, 1, new Label(character.getFaction().getName()));
    } else {
      detailGrid.setWidget(4, 1, new Label("--"));
    }

    detailGrid.setWidget(5, 0, new HTML("<b>Hair color:</b>"));
    if (character.getHairColor() != null) {
      detailGrid.setWidget(5, 1, new Label(character.getHairColor().getValue()));
    } else {
      detailGrid.setWidget(5, 1, new Label("--"));
    }

    detailGrid.setWidget(6, 0, new HTML("<b>Skin color:</b>"));
    if (character.getSkinColor() != null) {
      detailGrid.setWidget(6, 1, new Label(character.getSkinColor().getValue()));
    } else {
      detailGrid.setWidget(6, 1, new Label("--"));
    }

    detailGrid.setWidget(7, 0, new HTML("<b>Skills L0:</b>"));
    if (character.getSkillList() != null) {
      String html = "";
      for (Skill skill : character.getSkillList()) {
        String skillDetails = skill.toString();
        html = html + skillDetails + "<p>";
      }
      html = html.substring(0, html.length() - 3);
      HTML skillsDetails = new HTML(html);
      detailGrid.setWidget(7, 1, skillsDetails);
    } else {
      detailGrid.setWidget(7, 1, new Label("--"));
    }

    detailGrid.setWidget(8, 0, new HTML("<b>Skills L1:</b>"));
    if (character.getSkillList() != null) {
      String html = "";
      for (Skill skill : character.getSkillListLevel1()) {
        String skillDetails = skill.toString();
        html = html + skillDetails + "<p>";
      }
      html = html.substring(0, html.length() - 3);
      HTML skillsDetails = new HTML(html);
      detailGrid.setWidget(8, 1, skillsDetails);
    } else {
      detailGrid.setWidget(8, 1, new Label("--"));
    }

    detailGrid.setWidget(9, 0, new HTML("<b>Temp stats:</b>"));
    if (character.getSkillList() != null) {
      String html = "";
      int position = 0;
      for (Stat stat : character.getStatList()) {
        html = html + "<b>Name:</b> " + stat.getName() + "<br><b>Beschreibung:</b> " + stat.getDescription()
            + "<br><b>Stufe:</b> " + character.getTempStats().get(position++) + "<p>";
      }
      html = html.substring(0, html.length() - 3);
      HTML tempStatsDetails = new HTML(html);
      detailGrid.setWidget(9, 1, tempStatsDetails);
    } else {
      detailGrid.setWidget(9, 1, new Label("--"));
    }

    detailGrid.setWidget(10, 0, new HTML("<b>Pot stats:</b>"));
    if (character.getSkillList() != null) {
      String html = "";
      int position = 0;
      for (Stat stat : character.getStatList()) {
        html = html + "<b>Name:</b> " + stat.getName() + "<br><b>Beschreibung:</b> " + stat.getDescription()
            + "<br><b>Stufe:</b> " + character.getPotStats().get(position++) + "<p>";
      }
      html = html.substring(0, html.length() - 3);
      HTML tempStatsDetails = new HTML(html);
      detailGrid.setWidget(10, 1, tempStatsDetails);
    } else {
      detailGrid.setWidget(10, 1, new Label("--"));
    }

    FlexTable buttonGrid = new FlexTable();
    buttonGrid.setWidget(0, 0, back);
    buttonGrid.setWidget(0, 1, delete);

    panel.add(buttonGrid);
    panel.add(detailGrid);

    setWidget(panel);
    setStyleName("formPanel");
  }

  /**
   * Gets the ShowCharacterPanel with low detail mode.
   * 
   * @param entry
   *          the entry point of the application
   * @param c
   *          the current character
   * @param user
   *          the current user
   */
  public static final ShowCharacterPanel getShowShortCharacterPanel(DefaultTimadorusWebApp e, User u, Character c) {
    return new ShowCharacterPanel(e, u, c, ShowCharacterPanel.SHORT_MODE);
  }

  /**
   * Gets the ShowCharacterPanel with high detail mode.
   * 
   * @param entry
   *          the entry point of the application
   * @param c
   *          the current character
   * @param user
   *          the current user
   * @return the ShowCharacterPanel
   */
  public static final ShowCharacterPanel getShowDetailCharacterPanel(DefaultTimadorusWebApp e, User u, Character c) {
    return new ShowCharacterPanel(e, u, c, ShowCharacterPanel.DETAIL_MODE);
  }

  /**
   * Gets the user.
   * 
   * @return the current user
   */
  public User getUser() {
    return user;
  }

  /**
   * Sets the user.
   * 
   * @param userIn
   *          the user who is supposed to be the current user
   */
  public void setUser(User userIn) {
    this.user = userIn;
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
   * Clears the panel and adds a widget
   * 
   * @param w
   *          the widget to be added
   */
  private void setContent(Widget w) {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(w);
  }

  /**
   * Shows a dialog box.
   * 
   * @param title
   *          the title of the dialog box
   * @param message
   *          the message contained in the dialog box
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
