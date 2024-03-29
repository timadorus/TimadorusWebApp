package org.timadorus.webapp.client.character.ui.showcharacter;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.Skill;
import org.timadorus.webapp.beans.Stat;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.eventhandling.events.ShowCharacterListEvent;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
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

// Panel to show information about the character
public class ShowCharacterWidget extends FormPanel implements ShowCharacterDialog.Display {

  /**
   * Indicates the simple display mode.
   */
  public static final int SHORT_MODE = 1;

  /**
   * Indicates a full detail display mode of the character.
   */
  public static final int DETAIL_MODE = 2;

  private final int rows = 3;

  private final int columns = 4;

  private final int fourthCol = 3;

  private final int detailRows = 11;

  private final int detailColumns = 2;

  private VerticalPanel panel;

  private Grid grid;

  private Grid detailGrid;

  private Label name;

  private Label gender;

  private Label race;

  private Label cclass;

  private Label faction;

  private Button back;

  private Button delete;

  private Button confirm;

  private PasswordTextBox passbox;

  private Button back2;

  private Character character;

  public ShowCharacterWidget() {
    super();

    panel = new VerticalPanel();
    grid = new Grid(rows, columns);
    detailGrid = new Grid(detailRows, detailColumns);
    name = new Label("Name:");
    gender = new Label("Gender:");
    race = new Label("Race:");
    cclass = new Label("Klasse:");
    faction = new Label("Faction:");
    back = new Button("Zurück");
    back2 = new Button("Zurück");
    delete = new Button("Löschen");
    confirm = new Button("Löschung bestätigen");
    passbox = new PasswordTextBox();
  }

  /**
   * Builds the panel with only low detail of the character.
   */
  private void buildShortMode(Character character) {
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
  private void buildDetailMode(Character character) {
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

  @Override
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

  @Override
  public FormPanel getFormPanel() {
    return this;
  }

  @Override
  public void addDeleteButtonHandler(final DefaultActionHandler handler) {
    delete.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addBackButtonHandler(final DefaultActionHandler handler) {
    back.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addBack2ButtonHandler(final DefaultActionHandler handler) {
    back2.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addCharacterDeleteHandler(final CharacterActionHandler handler) {
    confirm.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        if (character != null) {
          handler.onAction(character, passbox.getText());
        } else {
          System.err.println("ShowCharacterWidget 366: no character set");
          // TODO show error message
        }
      }
    });
    passbox.addKeyUpHandler(new KeyUpHandler() {

      @Override
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          if (character != null) {
            handler.onAction(character, passbox.getText());
          } else {
            System.err.println("ShowCharacterWidget 379: no character set");
            // TODO show error message
          }
        }
      }
    });
  }

  @Override
  public void characterDeleteSuccessfull() {
    showDialogBox("Information", "Ihr Charakter wurde erfolgreich entfernt!");
  }

  @Override
  public void characterDeleteFailure() {
    passbox.setText("");
    showDialogBox("Fehlermeldung", "Passwort falsch! Versuchen Sie es erneut!");
  }

  @Override
  public void loadShowCharacterListsDialog(DefaultTimadorusWebApp entry, User user) {
    entry.fireEvent(new ShowCharacterListEvent(user));
  }

  @Override
  public void setMode(int mode, Character character) {
    this.character = character;
    if (mode == SHORT_MODE) {
      buildShortMode(character);
    } else if (mode == DETAIL_MODE) {
      buildDetailMode(character);
    } 
  }
}
