package org.timadorus.webapp.client.character;

import java.util.ArrayList;
import java.util.List;

import org.timadorus.webapp.client.HistoryStates;
import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.register.RegisterPanel;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;

public class PremadeCharacterPanel extends FormPanel implements HistoryStates {

  TimadorusWebApp entry;

  Button nextButton = new Button("weiter");

  VerticalPanel panel = new VerticalPanel();

  FlexTable selectGrid = new FlexTable();

  Label barbarianLabel = new Label("Barbar");

  Label wizzardLabel = new Label("Zauberer");

  Label hunterLabel = new Label("Waldläuferin");

  RadioButton selectBarbarian = new RadioButton("selectCharacter");

  RadioButton selectWizzard = new RadioButton("selectCharacter");

  RadioButton selectHunter = new RadioButton("selectCharacter");

  Image selectBarbarianImage = new Image("media/images/characterBarbarian.png");

  Image selectWizzardImage = new Image("media/images/characterWizzard.png");

  Image selectHunterImage = new Image("media/images/characterHunter.png");

  private static PremadeCharacterPanel characterPanel;

  public PremadeCharacterPanel(TimadorusWebApp entry) {
    super();
    this.entry = entry;

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {

        if (event.getSource().equals(selectBarbarianImage)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getBarbarianInformation());
          selectBarbarian.setValue(true);
        } else if (event.getSource().equals(selectWizzardImage)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getWizzardInformation());
          selectWizzard.setValue(true);
        } else if (event.getSource().equals(selectHunterImage)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getHunterInformation());
          selectHunter.setValue(true);
        } else if (event.getSource().equals(selectBarbarian)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getBarbarianInformation());
          selectBarbarian.setValue(true);
        } else if (event.getSource().equals(selectWizzard)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getWizzardInformation());
          selectWizzard.setValue(true);
        } else if (event.getSource().equals(selectHunter)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getHunterInformation());
          selectHunter.setValue(true);
        } else if (event.getSource().equals(barbarianLabel)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getBarbarianInformation());
          selectBarbarian.setValue(true);
        } else if (event.getSource().equals(wizzardLabel)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getWizzardInformation());
          selectWizzard.setValue(true);
        } else if (event.getSource().equals(hunterLabel)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getHunterInformation());
          selectHunter.setValue(true);
        } else if (event.getSource().equals(nextButton)) {
          System.out.println("Weiter");
        }

      }
    }

    // Style Components

    nextButton.setStylePrimaryName("nextButton");

    selectGrid.setBorderWidth(0);
    selectGrid.setStylePrimaryName("selectGrid");

    selectGrid.setWidget(0, 0, barbarianLabel);
    selectGrid.setWidget(0, 1, wizzardLabel);
    selectGrid.setWidget(0, 2, hunterLabel);

    selectGrid.setWidget(1, 0, selectBarbarianImage);
    selectGrid.setWidget(1, 1, selectWizzardImage);
    selectGrid.setWidget(1, 2, selectHunterImage);

    selectGrid.setWidget(2, 0, selectBarbarian);
    selectGrid.setWidget(2, 1, selectWizzard);
    selectGrid.setWidget(2, 2, selectHunter);

    selectBarbarian.setValue(true);

    HTML headline = new HTML("<h1>Charaktervorauswahl</h1>");
    HTML infotext = new HTML(
                             "<p>Wähle zwischen einem von drei vorgefertigten Charakteren oder wage das Abenteuer und erstelle deinen eigenen Charakter!</p>");

    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

    panel.add(headline);
    panel.add(infotext);

    panel.add(selectGrid);
    panel.add(nextButton);

    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

    // Add Handlers
    MyHandler handler = new MyHandler();

    nextButton.addClickHandler(handler);

    selectBarbarianImage.addClickHandler(handler);
    selectWizzardImage.addClickHandler(handler);
    selectHunterImage.addClickHandler(handler);
    
    selectBarbarian.addClickHandler(handler);
    selectWizzard.addClickHandler(handler);
    selectHunter.addClickHandler(handler);

    barbarianLabel.addClickHandler(handler);
    wizzardLabel.addClickHandler(handler);
    hunterLabel.addClickHandler(handler);

  }

  public static PremadeCharacterPanel getPremadeCharacterPanel(TimadorusWebApp entry) {
//    if (characterPanel == null) {
//      characterPanel = new PremadeCharacterPanel(entry);
//    }
//    return characterPanel;
    return new PremadeCharacterPanel(entry);
  }

  private static final HTML getBarbarianInformation() {
    HTML information = new HTML(
                                "<h1>Der Barbar</h1><p>Die Wilden Lande sind eine raue Welt der Entbehrungen und Gefahren, aber auch der Wunder und der Schönheit. Schroffe Felsen, schnee­bedeckte Berge, reißende Flüsse, tiefe Wälder, feuerspuckende Vulkane, brodelnde Geysire, geheimnisvolle Tempel und furchtein­flößende Raubtiere wirst du hier finden. Drachen, urtümliche Bestien von grausamer Kraft. Zurückgezogene Sippen wunderschöner, tödlicher Amazonen. Und natürlich die Stämme der Barbaren, ihre Bündnisse und Fehden, ihre Bruderkriege und Feldzüge in fremde Lande, ihre Fruchtbarkeitsfeste und ihre große Drachenjagd.</p>Quelle: http://www.wildelande.de/spiel.html");

    return information;
  }

  private static final HTML getWizzardInformation() {
    HTML information = new HTML(
                                "<h1>Der Zauberer</h1><p>Du bist ein echter MANN. Deine Arme sind dick wie junge Bäume, dein Schwert kriegen die meisten nicht mal vom Boden hoch, und dein Schwanz ist 25 cm lang. Kein Scheiß! Du durchschwimmst reißende Flüsse, erkletterst Berge und jagst Tiger und Bären in den tiefen Wäldern deiner Heimat. Du metzelst deine Feinde nieder wie Vieh und watest knöcheltief in ihrem Blut, und du fürchtest keinen Gegner. Nach gewonnener Schlacht lässt du dich mit Met vollaufen und suchst dir ein Weib, das dir einen bläst. Kurz: Du bist ein BARBAR!</p> Quelle: http://www.wildelande.de/spiel.html");
    return information;
  }

  private static final HTML getHunterInformation() {
    HTML information = new HTML(
                                "<h1>Der Waldläufer</h1><p>Du bist der Waldläufer. blablub. dsdggggggggggggggggggggdsgfwragewargearhbaerhaerher gearg g ewrgewr geragh erhgre gerg werg earghrea erhg ergher hergh aegdf gdfbher ghwer gerahg dfdf bbnetgwer age");
    return information;
  }
  
  private static final HTML getInformation() {
    HTML information = new HTML(
                                "<h1>Charaktervorauswahl</h1><p>Wähle zwischen einem von drei vorgefertigten Charakteren oder wage das Abenteuer und erstelle deinen eigenen Charakter!</p><p>Erstellen dir deinen eigenen Charkter. Wähle aus über 10 Rassen, 50 Klassen und über 100 Fähigkeiten diejenigen aus, die deinen Charakter am besten stehen.</p><p>Oder wähle einen der vorgefertigten Charaktere und beginne dein Abenteuer sofort</p><p>Es ist deine Entscheidung!</p>");
    return information;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}
