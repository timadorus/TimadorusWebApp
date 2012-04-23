package org.timadorus.webapp.client.character.ui.premadecharacter;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.createcharacter.CreateDialog;
import org.timadorus.webapp.client.character.ui.ready.ReadyDialog;
import org.timadorus.webapp.client.rpc.service.CreateCharacterService;
import org.timadorus.webapp.client.rpc.service.CreateCharacterServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * 
 * @author aaz210
 * 
 */
public class PremadeCharacterWidget extends FormPanel implements PremadeCharacterDialog.Display {

  private Button nextButton;

  private Button prevButton;

  private VerticalPanel panel; // main panel

  private FlexTable selectGrid; // grid for handling selections

  private FlexTable buttonGrid; // grid for next/prev buttons

  private Label barbarianLabel;

  private Label wizzardLabel;

  private Label hunterLabel;

  private RadioButton selectBarbarian;

  private RadioButton selectWizzard;

  private RadioButton selectHunter;

  private Image selectBarbarianImage;

  private Image selectWizzardImage;

  private Image selectHunterImage;

  public PremadeCharacterWidget() {
    super();

    // inti control Elements
    nextButton = new Button("weiter");
    prevButton = new Button("zurück");
    panel = new VerticalPanel();
    selectGrid = new FlexTable();
    buttonGrid = new FlexTable();
    barbarianLabel = new Label("Barbar");
    wizzardLabel = new Label("Zauberer");
    hunterLabel = new Label("Waldläuferin");
    selectBarbarian = new RadioButton("selectCharacter");
    selectWizzard = new RadioButton("selectCharacter");
    selectHunter = new RadioButton("selectCharacter");
    selectBarbarianImage = new Image("media/images/characterBarbarian.png");
    selectWizzardImage = new Image("media/images/characterWizzard.png");
    selectHunterImage = new Image("media/images/characterHunter.png");
    
    // headline
    HTML headline = new HTML("<h1>Charakterauswahl</h1>");

    // setting properties for selectGrid
    selectGrid.setBorderWidth(0);
    selectGrid.setStylePrimaryName("selectGrid");
    selectBarbarian.setValue(true);

    selectGrid.setWidget(0, 0, barbarianLabel);
    selectGrid.setWidget(0, 1, wizzardLabel);
    selectGrid.setWidget(0, 2, hunterLabel);

    selectGrid.setWidget(1, 0, selectBarbarianImage);
    selectGrid.setWidget(1, 1, selectWizzardImage);
    selectGrid.setWidget(1, 2, selectHunterImage);

    selectGrid.setWidget(2, 0, selectBarbarian);
    selectGrid.setWidget(2, 1, selectWizzard);
    selectGrid.setWidget(2, 2, selectHunter);

    // set properties of buttongrid
    buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    buttonGrid.setWidget(0, 0, prevButton);
    buttonGrid.setWidget(0, 1, nextButton);

    // adding widgets to the main panel
    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    panel.setWidth("100%");

    panel.add(headline);
    panel.add(selectGrid);
    panel.add(buttonGrid);

    // clearing "content" #div and adding this mainpanel to this #div
    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

    // clearing "information" #div and adding actual informations for this panel
    setInformation("<h1>Charakterauswahl</h1><p>Wähle zwischen einem von drei vorgefertigten Charakteren."
        + " Klicke auf eine der Charaktere um weitere Informationen zu bekommen");

  }

  // clear "content" #div and add Class CharacterReadyPanel to it
  public void loadCharacterReadyPanel(Character character, DefaultTimadorusWebApp entry) {
    RootPanel.get("content").clear();
    ReadyDialog readyDialog = ReadyDialog.getReadyDialog(entry, character);
    RootPanel.get("content").add(readyDialog.getFormPanel());
  }

  // clear "content" #div and add Class CharacterPanel to it
  public void loadCharacterPanel(DefaultTimadorusWebApp entry, User user) {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(CreateDialog.getCreateDialog(entry, user).getFormPanel());
  }

  @Override
  public FormPanel getFormPanel() {
    return this;
  }

  @Override
  public void addWizzardHandler(final DefaultActionHandler handler) {
    ClickHandler clickHandler = new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        selectWizzard.setValue(true);
        handler.onAction();
      }
    };

    selectWizzardImage.addClickHandler(clickHandler);
    selectWizzard.addClickHandler(clickHandler);
    wizzardLabel.addClickHandler(clickHandler);
  }

  @Override
  public void addHunterHandler(final DefaultActionHandler handler) {
    ClickHandler clickHandler = new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        selectHunter.setValue(true);
        handler.onAction();
      }
    };

    selectHunterImage.addClickHandler(clickHandler);
    selectHunter.addClickHandler(clickHandler);
    hunterLabel.addClickHandler(clickHandler);
  }

  @Override
  public void addBarbarianHandler(final DefaultActionHandler handler) {
    ClickHandler clickHandler = new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        selectBarbarian.setValue(true);
        handler.onAction();
      }
    };
    selectBarbarianImage.addClickHandler(clickHandler);
    selectBarbarian.addClickHandler(clickHandler);
    barbarianLabel.addClickHandler(clickHandler);
  }

  @Override
  public void setInformation(String text) {
    HTML information = new HTML(text);
    RootPanel.get("information").clear();
    RootPanel.get("information").add(information);
  }

  @Override
  public void addNextButtonHandler(final DefaultActionHandler handler) {
    ClickHandler clickHandler = new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        handler.onAction();
      }
    };
    nextButton.addClickHandler(clickHandler);

  }

  @Override
  public void addPrevButtonHandler(final DefaultActionHandler handler) {
    ClickHandler clickHandler = new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        handler.onAction();
      }
    };
    prevButton.addClickHandler(clickHandler);
  }

  @Override
  public void sendCharacterToServer(Character character) {
      CreateCharacterServiceAsync createCharacterServiceAsync = GWT
          .create(CreateCharacterService.class);
      AsyncCallback<String> asyncCallback = new AsyncCallback<String>() {

        public void onSuccess(String result) {
          if (result.equals("SUCCESS")) {
            History.newItem("welcome");
          } else {
            System.out.println("Character creation failed!");
            History.newItem("welcome");
          }
        }

        public void onFailure(Throwable caught) {
          System.out.println(caught);
        }
      };
      createCharacterServiceAsync.createCharacter(character, asyncCallback);
  }
}
