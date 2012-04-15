package org.timadorus.webapp.client.character.ui.selectname;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.SelectAppearancePanel;
import org.timadorus.webapp.client.character.ui.ready.ReadyDialog;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * FormPanel for setting name of Character-Object
 * @author aaz210
 *
 */
public class SelectNameWidget extends FormPanel implements SelectNameDialog.Display {

  private Button nextButton;

  private Button prevButton;

  private VerticalPanel panel;

  private FlexTable selectNameGrid;

  private FlexTable buttonGrid;

  private TextBox nameTextBox;

  public SelectNameWidget(Character character) {
    super();

    // inti controls
    nextButton = new Button("weiter");
    prevButton = new Button("zurück");
    panel = new VerticalPanel();
    selectNameGrid = new FlexTable();
    buttonGrid = new FlexTable();
    nameTextBox = new TextBox();

    // arrange controls
    HTML headline = new HTML("<h1>Namen wählen</h1>");

    Image progressBar = new Image("media/images/progressbar_7.png");

    selectNameGrid.setBorderWidth(0);
    selectNameGrid.setStylePrimaryName("selectGrid");

    Label nameLabel = new Label("Namen wählen");

    selectNameGrid.setWidget(0, 0, nameLabel);
    selectNameGrid.setWidget(0, 1, nameTextBox);

    buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    buttonGrid.setWidth("350px");
    buttonGrid.setWidget(0, 0, prevButton);
    buttonGrid.setWidget(0, 1, nextButton);

    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    panel.setWidth("100%");

    panel.add(progressBar);
    panel.add(new Label("Schritt 9 von 9"));
    panel.add(new Label("Geschlecht: " + character.getGender() + " | Rasse: "
        + character.getRace().getName()));
    panel.add(new Label("Klasse: " + character.getCharClass().getName() + " | Faction: "
        + character.getFaction().getName()));
    panel.add(new Label("Skills_L0: " + character.getSkillListNames()));
    panel.add(new Label("Skills_L1: " + character.getSkillLevel1ListNames()));
    panel.add(new Label("Hautfarbe: " + "..." + " | Haarfarbe: " + "..."));

    panel.add(headline);

    panel.add(selectNameGrid);

    panel.add(buttonGrid);

    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

  }

  public void loadSelectAppearancePanel(TimadorusWebApp entry, Character character, User user) {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectAppearancePanel.getSelectAppearancePanel(entry, character,
                                                                                user));
  }

  public void loadSelectCharacterReadyPanel(TimadorusWebApp entry, Character character) {
    RootPanel.get("content").clear();
    ReadyDialog readyDialog = ReadyDialog.getReadyDialog(entry, character);
    RootPanel.get("content").add(readyDialog.getFormPanel());
  }

  private static final HTML getInformation() {
    HTML information = new HTML(
                                "<h1>Namen wählen</h1><p>Wählen sie hier den Namen ihres Charakters. Erlaubt ist was "
                                    + "gefällt</p>");
    return information;
  }

  @Override
  public FormPanel getFormPanel() {
    return this;
  }

  @Override
  public String getCharacterName() {
    return nameTextBox.getText();
  }

  @Override
  public void addPrevButtonHandler(final DefaultActionHandler handler) {

    prevButton.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        handler.onAction();

      }
    });
  }

  @Override
  public void addNextButtonHandler(final DefaultActionHandler handler) {
    nextButton.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        handler.onAction();

      }
    });
  }
}
