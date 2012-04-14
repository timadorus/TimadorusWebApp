package org.timadorus.webapp.client.character.ui.createcharacter;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.PremadeCharacterPanel;
import org.timadorus.webapp.client.character.ui.SelectRacePanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Image;

public class CreateCharacterWidget extends FormPanel implements CreateDialog.Display {

  private Button nextButton;

  private VerticalPanel panel;

  private FlexTable selectGrid;

  private Label customLabel;

  private Label premadeLabel;

  private RadioButton selectCustom;

  private RadioButton selectPremade;

  private Image selectCustomImage;

  private Image selectPremadeImage;

  public CreateCharacterWidget() {
    super();

    //init controls
    nextButton = new Button("weiter");
    panel = new VerticalPanel();
    selectGrid = new FlexTable();
    customLabel = new Label("eigenen Charakter");
    premadeLabel = new Label("vorgefertigten Charakter");
    selectCustom = new RadioButton("selectCharacter");
    selectPremade = new RadioButton("selectCharacter");
    selectCustomImage = new Image("media/images/characterCustom.png");
    selectPremadeImage = new Image("media/images/characterPremade.png");

    // Style Components
    nextButton.setStylePrimaryName("nextButton");

    selectGrid.setBorderWidth(0);
    selectGrid.setStylePrimaryName("selectGrid");

    selectGrid.setWidget(0, 0, customLabel);
    selectGrid.setWidget(0, 1, premadeLabel);

    selectGrid.setWidget(1, 0, selectCustomImage);
    selectGrid.setWidget(1, 1, selectPremadeImage);

    selectGrid.setWidget(2, 0, selectCustom);
    selectGrid.setWidget(2, 1, selectPremade);
    selectCustom.setValue(true);

    HTML headline = new HTML("<h1>Charaktervorauswahl</h1>");
    HTML infotext = new HTML("<p>Wähle zwischen einem von drei vorgefertigten Charakteren oder wage das Abenteuer und "
        + "erstelle deinen eigenen Charakter!</p>");

    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    panel.setWidth("100%");

    panel.add(headline);
    panel.add(infotext);

    panel.add(selectGrid);
    panel.add(nextButton);

    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

  }

  private static final HTML getInformation() {
    HTML information = new HTML("<h1>Charaktervorauswahl</h1><p>Wähle zwischen einem von drei "
        + "vorgefertigten Charakteren oder wage das Abenteuer und erstelle deinen "
        + "eigenen Charakter!</p><p> Erstellen dir deinen eigenen Charkter. Wähle "
        + "aus über 10 Rassen, 50 Klassen und über 100 Fähigkeiten diejenigen aus, "
        + "die deinen Charakter am besten stehen.</p><p>Oder wähle einen der "
        + "vorgefertigten Charaktere und beginne dein Abenteuer sofort</p><p>Es" + " ist deine Entscheidung!</p>");
    return information;
  }

  @Override
  public FormPanel getFormPanel() {
    return this;
  }

  @Override
  public void setHandlerPremade(final DefaultActionHandler handler) {
    ClickHandler clickhandler = new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        handler.onAction();

      }
    };
    this.selectPremadeImage.addClickHandler(clickhandler);
    this.selectPremade.addClickHandler(clickhandler);
    this.premadeLabel.addClickHandler(clickhandler);
  }

  @Override
  public void setHandlerCustom(final DefaultActionHandler handler) {
    ClickHandler clickhandler = new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        handler.onAction();

      }
    };
    this.selectCustomImage.addClickHandler(clickhandler);
    this.selectCustom.addClickHandler(clickhandler);
    this.customLabel.addClickHandler(clickhandler);
  }

  @Override
  public void setHandlerNextButton(final DefaultActionHandler handler) {
    ClickHandler clickhandler = new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        handler.onAction();

      }
    };
    this.nextButton.addClickHandler(clickhandler);
  }

  @Override
  public void setPremadeInformation(String text) {
    HTML html = new HTML(text);
    RootPanel.get("information").clear();
    RootPanel.get("information").add(html);
    selectPremade.setValue(true);
  }

  @Override
  public void setCustomInformation(String text) {
    HTML html = new HTML(text);
    RootPanel.get("information").clear();
    RootPanel.get("information").add(html);
    selectCustom.setValue(true);
  }

  @Override
  public boolean isPremade() {
    return selectPremade.getValue();
  }

  @Override
  public boolean isCustom() {
    return selectCustom.getValue();
  }

  @Override
  public void loadPremadeCharacter(User user, TimadorusWebApp entry) {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(PremadeCharacterPanel.getPremadeCharacterPanel(entry, user));
  }

  @Override
  public void loadCustomCharacter(User user, TimadorusWebApp entry) {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectRacePanel.getSelectRacePanel(entry, (Character.getInstance()), user));
  }
}
