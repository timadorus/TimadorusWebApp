package org.timadorus.webapp.client.character.ui.selectname;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.eventhandling.events.ShowReadyDialogEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowSelectAppearanceEvent;
import org.timadorus.webapp.client.rpc.service.CreateCharacterService;
import org.timadorus.webapp.client.rpc.service.CreateCharacterServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
 * 
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

  private Label myGenderRaceLabel;

  private Label myClassFactionLabel;

  private Label mySkillLevel0Label;

  private Label mySkillLevel1Label;

  public SelectNameWidget() {
    super();

    // inti controls
    nextButton = new Button("weiter");
    prevButton = new Button("zur&uuml;ck");
    panel = new VerticalPanel();
    selectNameGrid = new FlexTable();
    buttonGrid = new FlexTable();
    nameTextBox = new TextBox();

    myClassFactionLabel = new Label("Klasse: | Fraktion:");
    myGenderRaceLabel = new Label("Geschlecht: | Rasse: ");
    mySkillLevel0Label = new Label("Skills_L0:");
    mySkillLevel1Label = new Label("Skills_L1:");

    // arrange controls
    HTML headline = new HTML("<h1>Namen w&auml;hlen</h1>");

    Image progressBar = new Image("media/images/progressbar_7.png");

    selectNameGrid.setBorderWidth(0);
    selectNameGrid.setStylePrimaryName("selectGrid");

    Label nameLabel = new Label("Namen w&auml;hlen");

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
    panel.add(myGenderRaceLabel);
    panel.add(myClassFactionLabel);
    panel.add(mySkillLevel0Label);
    panel.add(mySkillLevel1Label);
    panel.add(new Label("Hautfarbe: " + "..." + " | Haarfarbe: " + "..."));

    panel.add(headline);

    panel.add(selectNameGrid);

    panel.add(buttonGrid);

    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

  }

  public void loadSelectAppearancePanel(DefaultTimadorusWebApp entry, Character character, User user) {
    entry.fireEvent(new ShowSelectAppearanceEvent(user, character));
  }

  public void loadSelectCharacterReadyPanel(DefaultTimadorusWebApp entry, Character character) {
    entry.fireEvent(new ShowReadyDialogEvent(character));
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

  public void sendCharacterToServerToSave(Character aCharacter) {
    CreateCharacterServiceAsync createServiceAsync = GWT.create(CreateCharacterService.class);

    AsyncCallback<String> asyncCallback = new AsyncCallback<String>() {

      @Override
      public void onFailure(Throwable caught) {
        // TODO Auto-generated method stub
        System.out.println("Client/Server Create Character Service Failure!");
      }

      @Override
      public void onSuccess(String result) {
        // TODO Auto-generated method stub
        System.out.println(result);
      }
    };

    createServiceAsync.createCharacter(aCharacter, asyncCallback);
  }

  @Override
  public void setCharacter(Character character) {
    myGenderRaceLabel.setText("Geschlecht: " + character.getGender() + " | Rasse: " + character.getRace().getName());
    myClassFactionLabel.setText("Klasse: " + character.getCharClass().getName() + " | Faction: "
        + character.getFaction().getName());
    mySkillLevel0Label.setText("Skills_L0: " + character.getSkillListNames());
    mySkillLevel1Label.setText("Skills_L1: " + character.getSkillLevel1ListNames());
  }
}
