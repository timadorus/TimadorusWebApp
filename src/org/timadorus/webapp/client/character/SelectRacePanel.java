package org.timadorus.webapp.client.character;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.timadorus.webapp.client.HistoryStates;
import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.register.RegisterPanel;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.SelectClassPanel;
import org.timadorus.webapp.client.character.Race;

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

//ClassPanel allows you to choosing the Classes and Races of Character via Listbox
public class SelectRacePanel extends FormPanel implements HistoryStates {

  TimadorusWebApp entry;

  Character character;

  Button nextButton = new Button("weiter");

  Button prevButton = new Button("zurück");

  VerticalPanel panel = new VerticalPanel();

  RadioButton selectMale = new RadioButton("selectGender", "männlich");

  RadioButton selectFemale = new RadioButton("selectGender", "weiblich");

  FlexTable selectGenderGrid = new FlexTable();

  FlexTable buttonGrid = new FlexTable();
  
  ListBox raceListBox = new ListBox();

  public SelectRacePanel(TimadorusWebApp entry, Character character) {
    super();
    this.entry = entry;
    this.character = character;

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {
        if (event.getSource().equals(prevButton)) {
          loadCharacterPanel();
        } else if (event.getSource().equals(nextButton)) {   
          saveSelectedRace();
          loadSelectClassPanel();
        }

      }
    }

    Image progressBar = new Image("media/images/progressbar_1.png");

    selectGenderGrid.setBorderWidth(0);
    selectGenderGrid.setStylePrimaryName("selectGrid");

    HTML genderLabel = new HTML("Geschlecht wählen");

    selectGenderGrid.setWidget(0, 0, selectMale);
    selectGenderGrid.setWidget(0, 1, selectFemale);

    selectMale.setValue(true);    

    

    ListIterator raceIterator = getTestRaces().listIterator();    
    while (raceIterator.hasNext()) {
      Race newRace = (Race)raceIterator.next();
      raceListBox.addItem(newRace.getName());
    }
    
    raceListBox.setVisibleItemCount(raceListBox.getItemCount());
         
 
    buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    buttonGrid.setWidth("350px");
    buttonGrid.setWidget(0, 0, prevButton);
    buttonGrid.setWidget(0, 1, nextButton);

    // Add it to the root panel.
    
    HTML headline = new HTML("<h1>Geschlecht und Rasse wählen</h1>");
    
    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

 
    panel.add(progressBar);
    panel.add(new Label("Schritt 1 von 6"));
    panel.add(headline);
    
    panel.add(genderLabel);
    panel.add(selectGenderGrid);

    panel.add(new Label("Rasse wählen:"));
    panel.add(raceListBox);

    panel.add(buttonGrid);

    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

    // Add Handlers
    MyHandler handler = new MyHandler();
    nextButton.addClickHandler(handler);
    prevButton.addClickHandler(handler);

  }

  public void loadCharacterPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(CharacterPanel.getCharacterPanel(entry));
  }

  public void saveSelectedRace(){
    character.setRace(getSelectedRace());    
  }
  
  public void loadSelectClassPanel() { 
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectClassPanel.getSelectClassPanel(entry, character));
  }
  
  public Race getSelectedRace(){
    Race race = getTestRaces().get(raceListBox.getSelectedIndex());    
    return race;
  }

  public static SelectRacePanel getSelectRacePanel(TimadorusWebApp entry, Character character) {

    return new SelectRacePanel(entry, character);
  }

  private static final HTML getInformation() {
    HTML information = new HTML(
                                "<h1>Rasse und Geschlecht wählen</h1><p>Wählen sie hier das Geschlecht und die Rasse ihres Charakteres. Beachten sie, dass bestimmte Rassen nur bestimmte Klassen sowie Fraktionen wählen können.</p>");

    return information;
  }
  
  public List<Race> getTestRaces(){
    List<Race> races = Race.getRaces();   
    return races;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}
