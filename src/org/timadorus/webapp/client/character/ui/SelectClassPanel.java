package org.timadorus.webapp.client.character.ui;

import java.util.ListIterator;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.attributes.CClass;
import org.timadorus.webapp.client.character.attributes.Faction;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

//Panel for selecting characters Class
public class SelectClassPanel extends FormPanel {

  final TimadorusWebApp entry;

  final Character character;
  
  User user;

  Button nextButton = new Button("weiter");

  Button prevButton = new Button("zurück");

  VerticalPanel panel = new VerticalPanel();

  FlexTable selectClassGrid = new FlexTable(); // grid for handling selections

  FlexTable buttonGrid = new FlexTable(); // grid for next/prev buttons

  ListBox classListBox = new ListBox();  //listbox for available classes

  public SelectClassPanel(final TimadorusWebApp entryIn, final Character characterIn, User user) {
    super();
    this.entry = entryIn;
    this.character = characterIn;
    this.user = user;

    // Create a handler for this panels elements
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {
        //handles prev button
        if (event.getSource().equals(prevButton)) {
          loadSelectRacePanel();
          
        //handles next button
        } else if (event.getSource().equals(nextButton)) {
          saveSelectedClass();
          loadSelectFactionPanel();
          
        //handles changing "information" #div
        } else if (event.getSource().equals(selectClassGrid)) {
          String className = classListBox.getValue(classListBox.getSelectedIndex());
          ListIterator<CClass> classIterator = entryIn.getTestValues().getClasses().listIterator();
          RootPanel.get("information").clear();
          while (classIterator.hasNext()) {
            CClass newClass = (CClass) classIterator.next();
            if (newClass.getName().equals(className)) {
              RootPanel.get("information").add(
                                               new HTML("<h1>" + newClass.getName() + "</h1><p>"
                                                   + newClass.getDescription() + "</p>"));

              //Show available Factions in "information" #div
              RootPanel.get("information").add(new HTML("<h2>Wählbare Fraktionen</h2>"));
              ListIterator<Faction> factionIterator = newClass.getAvailableFactions().listIterator();
              String availableFactions3 = new String("<ul>");
              String nextFaction = new String();
              while (factionIterator.hasNext()) {
                Faction newFaction = (Faction) factionIterator.next();
                nextFaction = newFaction.getName();
                if (characterIn.getRace().getAvailableFactions().contains(newFaction)) {
                  availableFactions3 = availableFactions3 + "<li>" + nextFaction + "</li>";
                }
              }
              availableFactions3 = availableFactions3 + "</ul>";
              RootPanel.get("information").add(new HTML(availableFactions3));
            }
          }
        }
      }
    }

    // headline
    HTML headline = new HTML("<h1>Klasse wählen</h1>");
    // progress bar picture
    Image progressBar = new Image("media/images/progressbar_2.png");
    
    // setting properties for selectClassGrid
    selectClassGrid.setBorderWidth(0);
    selectClassGrid.setStylePrimaryName("selectGrid");
    // filling the list with available classes
    ListIterator<CClass> classIterator = entryIn.getTestValues().getClasses().listIterator();
    while (classIterator.hasNext()) {
      CClass newClass = (CClass) classIterator.next();
      if (characterIn.getRace().getAvailableClasses().contains(newClass)) {
        classListBox.addItem(newClass.getName());
      }
    }

    Label classLabel = new Label("Klasse wählen: ");

    selectClassGrid.setWidget(0, 0, classLabel);
    selectClassGrid.setWidget(0, 1, classListBox);

    classListBox.setVisibleItemCount(classListBox.getItemCount());

    // set properties of buttongrid
    buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    buttonGrid.setWidth("350px");
    buttonGrid.setWidget(0, 0, prevButton);
    buttonGrid.setWidget(0, 1, nextButton);
    // setting properties of the main panel
    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    panel.setWidth("100%");
    
    // adding widgets to the main panel
    panel.add(progressBar);
    panel.add(new Label("Schritt 2 von 9"));
    panel.add(new Label("Geschlecht: " + characterIn.getGender() + " | Rasse: " + characterIn.getRace().getName()));

    panel.add(headline);
    panel.add(selectClassGrid);
    panel.add(buttonGrid);
    
    // clearing "information" #div and adding actual informations for this panel
    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

    // clearing "content" #div and adding this mainpanel to this #div
    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

    // Add Handlers
    MyHandler handler = new MyHandler();
    nextButton.addClickHandler(handler);
    prevButton.addClickHandler(handler);
    selectClassGrid.addClickHandler(handler);

  }

  //saves selected character class
  public void saveSelectedClass() {
    character.setCharClass(getSelectedClass());
  }

  //returns currently select class from the listbox
  public CClass getSelectedClass() {
    ListIterator<CClass> classIterator = entry.getTestValues().getClasses().listIterator();
    while (classIterator.hasNext()) {
      CClass selectedClass = classIterator.next();
      if (selectedClass.getName().equals(classListBox.getValue(classListBox.getSelectedIndex()))) { 
        return selectedClass;
      }
    }
    return null;
  }

  //clear "content" #div and add Class SelectRacePanel to it
  public void loadSelectRacePanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectRacePanel.getSelectRacePanel(entry, character, user));
  }

  //clear "content" #div and add Class SelectFactionPanel to it
  public void loadSelectFactionPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectFactionPanel.getSelectFactionPanel(entry, character, user));
  }

  //creates a new SelectClassPanel instance
  public static SelectClassPanel getSelectClassPanel(TimadorusWebApp entry, Character character, User user) {
    return new SelectClassPanel(entry, character, user);
  }

  //returns and hols current panel information
  private static final HTML getInformation() {
    HTML information = new HTML("<h1>Klasse wählen</h1><p>Wählen sie hier die Klasse ihres Charakteres. "
                                + "Die Klasse bestimmt wie gut sie bestimmte Fähigkeiten lernen können."
                                + "</p><p>Beachten sie, dass bestimmte Klassen nur bestimmte Rassen sowie "
                                + "Fraktionen wählen können.</p>");
    return information;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}
