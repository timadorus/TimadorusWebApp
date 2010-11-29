package org.timadorus.webapp.client.character;

import org.timadorus.webapp.client.HistoryStates;
import org.timadorus.webapp.client.TimadorusWebApp;

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

public class CharacterPanel extends FormPanel implements HistoryStates {

  TimadorusWebApp entry;

  Button nextButton = new Button("weiter");

  VerticalPanel panel = new VerticalPanel();

  FlexTable selectGrid = new FlexTable();

  Label customLabel = new Label("eigenen Charakter");

  Label premadeLabel = new Label("vorgefertigten Charakter");

  RadioButton selectCustom = new RadioButton("selectCharacter");

  RadioButton selectPremade = new RadioButton("selectCharacter");

  Image selectCustomImage = new Image("media/images/characterCustom.png");

  Image selectPremadeImage = new Image("media/images/characterPremade.png");

  public CharacterPanel(TimadorusWebApp entryIn) {
    super();
    this.entry = entryIn;

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {

        if (event.getSource().equals(selectPremadeImage)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getPremadeInformation());
          selectPremade.setValue(true);
          
        } else if (event.getSource().equals(selectCustomImage)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getCustomInformation());
          selectCustom.setValue(true);
          
        } else if (event.getSource().equals(selectPremade)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getPremadeInformation());
          selectPremade.setValue(true);
          
        } else if (event.getSource().equals(selectCustom)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getCustomInformation());
          selectCustom.setValue(true);
          
        } else if (event.getSource().equals(premadeLabel)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getPremadeInformation());
          selectPremade.setValue(true);
          
        } else if (event.getSource().equals(customLabel)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getCustomInformation());
          selectCustom.setValue(true);
          
        } else if (event.getSource().equals(nextButton)) {
          if (selectPremade.getValue()) {
            loadPremadeCreateCharacterPanel();
          } else if (selectCustom.getValue()) {
            loadSelectRacePanel();         
          }
        }
      }
    }

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

    // Add Handlers
    MyHandler handler = new MyHandler();

    nextButton.addClickHandler(handler);

    selectCustom.addClickHandler(handler);
    selectPremade.addClickHandler(handler);

    selectPremadeImage.addClickHandler(handler);
    selectCustomImage.addClickHandler(handler);

    customLabel.addClickHandler(handler);
    premadeLabel.addClickHandler(handler);

  }

  public void loadPremadeCreateCharacterPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(PremadeCharacterPanel.getPremadeCharacterPanel(entry));
  }
  
  public void loadSelectRacePanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectRacePanel.getSelectRacePanel(entry, (Character.getInstance())));
  }

  public static CharacterPanel getCharacterPanel(TimadorusWebApp entry) {
//    if (characterPanel == null) {
//      characterPanel = new CharacterPanel(entry);
//    }
//    return characterPanel;
    return new CharacterPanel(entry);
  }

  private static final HTML getCustomInformation() {
    HTML information = new HTML("<h1>Eigener Charakter</h1><p>Die Wilden Lande sind eine raue Welt der Entbehrungen und"
                                + "Gefahren, aber auch der Wunder und der Schönheit. Schroffe Felsen, schnee­bedeckte "
                                + "Berge, reißende Flüsse, tiefe Wälder, feuerspuckende Vulkane, brodelnde Geysire, " 
                                + "geheimnisvolle Tempel und furchtein­flößende Raubtiere wirst du hier finden. "
                                + "Drachen, urtümliche Bestien von grausamer Kraft. Zurückgezogene Sippen wunderschöner"
                                + ", tödlicher Amazonen. Und natürlich die Stämme der Barbaren, ihre Bündnisse und "
                                + "Fehden, ihre Bruderkriege und Feldzüge in fremde Lande, ihre Fruchtbarkeitsfeste und"
                                + " ihre große Drachenjagd.</p>Quelle: http://www.wildelande.de/spiel.html");
    return information;
  }

  private static final HTML getPremadeInformation() {
    HTML information = new HTML("<h1>Vorgefertigter Charakter</h1><p>Du bist ein echter MANN. Deine Arme sind dick wie "
                                + "junge Bäume, dein Schwert kriegen die meisten nicht mal vom Boden hoch, und dein "
                                + "Schwanz ist 25 cm lang. Kein Scheiß! Du durchschwimmst reißende Flüsse, erkletterst "
                                + "Berge und jagst Tiger und Bären in den tiefen Wäldern deiner Heimat. Du metzelst "
                                + "deine Feinde nieder wie Vieh und watest knöcheltief in ihrem Blut, und du fürchtest "
                                + "keinen Gegner. Nach gewonnener Schlacht lässt du dich mit Met vollaufen und suchst "
                                + "dir ein Weib, das dir einen bläst. Kurz: Du bist ein BARBAR!</p> Quelle: "
                                + "http://www.wildelande.de/spiel.html");
    return information;
  }

  private static final HTML getInformation() {
    HTML information = new HTML("<h1>Charaktervorauswahl</h1><p>Wähle zwischen einem von drei vorgefertigten "
                                + "Charakteren oder wage das Abenteuer und erstelle deinen eigenen Charakter!</p><p>"
                                + "Erstellen dir deinen eigenen Charkter. Wähle aus über 10 Rassen, 50 Klassen und über"
                                + " 100 Fähigkeiten diejenigen aus, die deinen Charakter am besten stehen.</p><p>Oder "
                                + "wähle einen der vorgefertigten Charaktere und beginne dein Abenteuer sofort</p><p>Es"
                                + " ist deine Entscheidung!</p>");
    return information;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}
