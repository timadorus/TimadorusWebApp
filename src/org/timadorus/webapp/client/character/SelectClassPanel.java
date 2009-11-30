package org.timadorus.webapp.client.character;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.timadorus.webapp.client.HistoryStates;
import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.character.Character;

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
public class SelectClassPanel extends FormPanel implements HistoryStates {

  final TimadorusWebApp entry;

  Character character;

  Button nextButton = new Button("weiter");

  Button prevButton = new Button("zurück");

  VerticalPanel panel = new VerticalPanel();

  FlexTable selectClassGrid = new FlexTable();

  FlexTable buttonGrid = new FlexTable();

  ListBox classListBox = new ListBox();

  public SelectClassPanel(final TimadorusWebApp entry, Character character) {
    super();
    this.entry = entry;
    this.character = character;

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {
        if (event.getSource().equals(prevButton)) {
          loadSelectRacePanel();
        } else if (event.getSource().equals(nextButton)) {
          saveSelectedClass();
          loadSelectFactionPanel();
        } else if (event.getSource().equals(selectClassGrid)) {
          String className = classListBox.getValue(classListBox.getSelectedIndex());
          ListIterator<Class> classIterator = entry.getTestValues().getClasses().listIterator();
          RootPanel.get("information").clear();
          while (classIterator.hasNext()) {
            Class newClass = (Class)classIterator.next();
            if (newClass.getName().equals(className)) {
              RootPanel.get("information").add(
                                               new HTML("<h1>" + newClass.getName() + "</h1><p>"
                                                   + newClass.getDescription() + "</p>"));            
            }
            //Show available Factions
            RootPanel.get("information").add(new HTML("<h2>Wählbare Fraktionen</h2>"));
            ListIterator<Faction> factionIterator = newClass.getAvailableFactions().listIterator();
            String availableFactions = new String("<ul>");
            String nextFaction = new String();
            while (factionIterator.hasNext()) {
              Faction newFaction = (Faction) factionIterator.next();
              nextFaction = newFaction.getName();
              availableFactions = availableFactions + "<li>" + nextFaction+ "</li>";
            }
            availableFactions = availableFactions + "</ul>";
            RootPanel.get("information").add(new HTML(availableFactions));
          }
        }

      }
    }

    HTML headline = new HTML("<h1>Klasse wählen</h1>");

    Image progressBar = new Image("media/images/progressbar_2.png");

    selectClassGrid.setBorderWidth(0);
    selectClassGrid.setStylePrimaryName("selectGrid");

    ListIterator<Class> classIterator = entry.getTestValues().getClasses().listIterator();
    while (classIterator.hasNext()) {
      Class newClass = (Class) classIterator.next();
      if (character.getRace().getAvailableClasses().contains(newClass)) {
        classListBox.addItem(newClass.getName());
      }      
    }

    Label classLabel = new Label("Klasse wählen: ");

    selectClassGrid.setWidget(0, 0, classLabel);
    selectClassGrid.setWidget(0, 1, classListBox);

    classListBox.setVisibleItemCount(classListBox.getItemCount());

    buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    buttonGrid.setWidth("350px");
    buttonGrid.setWidget(0, 0, prevButton);
    buttonGrid.setWidget(0, 1, nextButton);

    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

    panel.add(progressBar);
    panel.add(new Label("Schritt 2 von 6"));
    panel.add(new Label("Geschlecht: " + character.getGender() + " | Rasse: " + character.getRace().getName()));

    panel.add(headline);

    panel.add(selectClassGrid);

    panel.add(buttonGrid);

    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

    // Add Handlers
    MyHandler handler = new MyHandler();
    nextButton.addClickHandler(handler);
    prevButton.addClickHandler(handler);
    selectClassGrid.addClickHandler(handler);

  }
  
  public void saveSelectedClass(){
    character.setCharClass(getSelectedClass());
  }
  
  public Class getSelectedClass() {  
    ListIterator<Class> classIterator = entry.getTestValues().getClasses().listIterator();
    while (classIterator.hasNext()){
      Class selectedClass = classIterator.next();      
      if(selectedClass.getName().equals(classListBox.getValue(classListBox.getSelectedIndex()))){
        return selectedClass;
      }
    }
    return null;    
  }

  public void loadSelectRacePanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectRacePanel.getSelectRacePanel(entry, character));
  }

  public void loadSelectFactionPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectFactionPanel.getSelectFactionPanel(entry, character));
  }

  public static SelectClassPanel getSelectClassPanel(TimadorusWebApp entry, Character character) {
    // if (characterPanel == null) {
    // characterPanel = new CharacterPanel(entry);
    // }
    // return characterPanel;
    return new SelectClassPanel(entry, character);
  }

  private static final HTML getInformation() {
    HTML information = new HTML(
                                "<h1>Klasse wählen</h1><p>Wählen sie hier die Klasse ihres Charakteres. Die Klasse bestimmt wie gut sie bestimmte Fähigkeiten lernen können.</p><p>Beachten sie, dass bestimmte Klassen nur bestimmte Rassen sowie Fraktionen wählen können.</p>");

    return information;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}
