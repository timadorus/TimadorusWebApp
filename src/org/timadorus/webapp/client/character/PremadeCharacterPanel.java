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

//Panel for showing available premade characters
public class PremadeCharacterPanel extends FormPanel implements HistoryStates {

  TimadorusWebApp entry;

  Button nextButton = new Button("weiter");

  Button prevButton = new Button("zurück");

  VerticalPanel panel = new VerticalPanel(); // main panel

  FlexTable selectGrid = new FlexTable(); // grid for handling selections

  FlexTable buttonGrid = new FlexTable(); // grid for next/prev buttons

  Label barbarianLabel = new Label("Barbar");

  Label wizzardLabel = new Label("Zauberer");

  Label hunterLabel = new Label("Waldläuferin");

  RadioButton selectBarbarian = new RadioButton("selectCharacter");

  RadioButton selectWizzard = new RadioButton("selectCharacter");

  RadioButton selectHunter = new RadioButton("selectCharacter");

  Image selectBarbarianImage = new Image("media/images/characterBarbarian.png");

  Image selectWizzardImage = new Image("media/images/characterWizzard.png");

  Image selectHunterImage = new Image("media/images/characterHunter.png");

  Character character;

  private static PremadeCharacterPanel characterPanel;

  public PremadeCharacterPanel(TimadorusWebApp entry) {
    super();
    this.entry = entry;

    // Create a handler for this panels elements
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {
        // showing information for barbarian
        if (event.getSource().equals(selectBarbarianImage)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getBarbarianInformation());
          selectBarbarian.setValue(true);
          // showing information for wizzard
        } else if (event.getSource().equals(selectWizzardImage)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getWizzardInformation());
          selectWizzard.setValue(true);
          // showing information for hunter
        } else if (event.getSource().equals(selectHunterImage)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getHunterInformation());
          selectHunter.setValue(true);
          // showing information for barbarian
        } else if (event.getSource().equals(selectBarbarian)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getBarbarianInformation());
          selectBarbarian.setValue(true);
          // showing information for wizzard
        } else if (event.getSource().equals(selectWizzard)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getWizzardInformation());
          selectWizzard.setValue(true);
          // showing information for hunter
        } else if (event.getSource().equals(selectHunter)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getHunterInformation());
          selectHunter.setValue(true);
          // showing information for barbarian
        } else if (event.getSource().equals(barbarianLabel)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getBarbarianInformation());
          selectBarbarian.setValue(true);
          // showing information for wizzard
        } else if (event.getSource().equals(wizzardLabel)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getWizzardInformation());
          selectWizzard.setValue(true);
          // showing information for hunter
        } else if (event.getSource().equals(hunterLabel)) {
          RootPanel.get("information").clear();
          RootPanel.get("information").add(getHunterInformation());
          selectHunter.setValue(true);
          // handles next button
        } else if (event.getSource().equals(nextButton)) {          
          if (selectHunter.getValue()) {
            character = Character.getHunter();
          } else if (selectWizzard.getValue()) {
            character = Character.getWizzard();
          } else if (selectBarbarian.getValue()) {
            character = Character.getBarbarian();
          }
          loadCharacterReadyPanel(character);
          // handles prev button
        } else if (event.getSource().equals(prevButton)) {
          loadCharacterPanel();
        }

      }
    }

    
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
    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

    // Add Handlers
    MyHandler handler = new MyHandler();

    nextButton.addClickHandler(handler);
    prevButton.addClickHandler(handler);

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
  
  //clear "content" #div and add Class CharacterReadyPanel to it
  public void loadCharacterReadyPanel(Character character) {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(CharacterReadyPanel.getCharacterReadyPanel(entry, character));
  }
  //clear "content" #div and add Class CharacterPanel to it  
  public void loadCharacterPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(CharacterPanel.getCharacterPanel(entry));
  }

  //return a new instance of PremadeCharacterPanel
  public static PremadeCharacterPanel getPremadeCharacterPanel(TimadorusWebApp entry) {
    return new PremadeCharacterPanel(entry);
  }

  //return and stores information for this panel
  private static final HTML getBarbarianInformation() {
    HTML information = new HTML(
                                "<h1>Der Barbar</h1><p>Die Wilden Lande sind eine raue Welt der Entbehrungen und Gefahren, aber auch der Wunder und der Schönheit. Schroffe Felsen, schnee­bedeckte Berge, reißende Flüsse, tiefe Wälder, feuerspuckende Vulkane, brodelnde Geysire, geheimnisvolle Tempel und furchtein­flößende Raubtiere wirst du hier finden. Drachen, urtümliche Bestien von grausamer Kraft. Zurückgezogene Sippen wunderschöner, tödlicher Amazonen. Und natürlich die Stämme der Barbaren, ihre Bündnisse und Fehden, ihre Bruderkriege und Feldzüge in fremde Lande, ihre Fruchtbarkeitsfeste und ihre große Drachenjagd.</p>Quelle: http://www.wildelande.de/spiel.html");

    return information;
  }
  //returns and stores information about class wizzard
  private static final HTML getWizzardInformation() {
    HTML information = new HTML(
                                "<h1>Der Zauberer</h1><p>Du bist ein echter MANN. Deine Arme sind dick wie junge Bäume, dein Schwert kriegen die meisten nicht mal vom Boden hoch, und dein Schwanz ist 25 cm lang. Kein Scheiß! Du durchschwimmst reißende Flüsse, erkletterst Berge und jagst Tiger und Bären in den tiefen Wäldern deiner Heimat. Du metzelst deine Feinde nieder wie Vieh und watest knöcheltief in ihrem Blut, und du fürchtest keinen Gegner. Nach gewonnener Schlacht lässt du dich mit Met vollaufen und suchst dir ein Weib, das dir einen bläst. Kurz: Du bist ein BARBAR!</p> Quelle: http://www.wildelande.de/spiel.html");
    return information;
  }
  //returns and stores information about class hunter
  private static final HTML getHunterInformation() {
    HTML information = new HTML(
                                "<h1>Der Waldläufer</h1><p>Du bist der Waldläufer. blablub. dsdggggggggggggggggggggdsgfwragewargearhbaerhaerher gearg g ewrgewr geragh erhgre gerg werg earghrea erhg ergher hergh aegdf gdfbher ghwer gerahg dfdf bbnetgwer age");
    return information;
  }
  //returns and stores information about class barbarian
  private static final HTML getInformation() {
    HTML information = new HTML(
                                "<h1>Charakterauswahl</h1><p>Wähle zwischen einem von drei vorgefertigten Charakteren. Klicke auf eine der Charaktere um weitere Informationen zu bekommen");
    return information;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}
