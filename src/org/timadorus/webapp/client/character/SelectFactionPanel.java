package org.timadorus.webapp.client.character;


import java.util.ListIterator;

import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Image;

//Panel for selecting characters Faction
public class SelectFactionPanel extends FormPanel {

  TimadorusWebApp entry;

  Character character;
  
  User user;

  Button nextButton = new Button("weiter");

  Button prevButton = new Button("zurück");

  VerticalPanel panel = new VerticalPanel();

  //grid for handling selections
  FlexTable selectFactionGrid = new FlexTable();
  
  //grid for next/prev buttons
  FlexTable buttonGrid = new FlexTable();

  //listbox for available faction
  ListBox factionListBox = new ListBox(); 

  public SelectFactionPanel(final TimadorusWebApp entryIn, Character characterIn, User user) {
    super();
    this.entry = entryIn;
    this.character = characterIn;
    this.user = user;

    // Create a handler for this panels elements
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {
        // handles prev button
        if (event.getSource().equals(prevButton)) {
          loadSelectClassPanel();
          
        // handles next button
        } else if (event.getSource().equals(nextButton)) {
          saveSelectedFaction();
          loadSelectTempStatsPanel();
          
        } else if (event.getSource().equals(selectFactionGrid)) {
          //handles changing "information" #div
          String factionName = factionListBox.getValue(factionListBox.getSelectedIndex());
          ListIterator<Faction> factionIterator = entryIn.getTestValues().getFactions().listIterator();
          RootPanel.get("information").clear();
          while (factionIterator.hasNext()) {
            Faction newFaction = (Faction) factionIterator.next();
            if (newFaction.getName().equals(factionName)) {
              RootPanel.get("information").add(new HTML("<h1>" + newFaction.getName() + "</h1><p>"
                                                        + newFaction.getDescription() + "</p>"));


            }
          }
        }
      }
    }
    
    //headline
    HTML headline = new HTML("<h1>Fraktion wählen</h1>");
    
    //progress bar picture
    Image progressBar = new Image("media/images/progressbar_3.png");

    // setting properties for selectfactiongrid
    selectFactionGrid.setBorderWidth(0);
    selectFactionGrid.setStylePrimaryName("selectGrid");
    
    //filling the grid with available factions
    ListIterator<Faction> factionIterator = entryIn.getTestValues().getFactions().listIterator();
    while (factionIterator.hasNext()) {
      Faction newFaction = (Faction) factionIterator.next();      
      if (characterIn.getCharClass().getAvailableFactions().contains(newFaction)) {
        if (characterIn.getRace().getAvailableFactions().contains(newFaction)) {
          factionListBox.addItem(newFaction.getName());
        }
      }
    }

    Label factionLabel = new Label("Fraktion wählen: ");

    selectFactionGrid.setWidget(0, 0, factionLabel);
    selectFactionGrid.setWidget(0, 1, factionListBox);

    factionListBox.setVisibleItemCount(factionListBox.getItemCount());

    //set properties of buttongrid
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
    panel.add(new Label("Schritt 3 von 9"));
    panel.add(new Label("Geschlecht: " + characterIn.getGender() + " | Rasse: " + characterIn.getRace().getName()));
    panel.add(new Label("Klasse: " + characterIn.getCharClass().getName()));

    panel.add(headline);    
    panel.add(selectFactionGrid);
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
    selectFactionGrid.addClickHandler(handler);
  }

  //save selected factions
  public void saveSelectedFaction() {
    character.setFaction(getSelectedFaction());
  }
  
  //returns currently select faction from the listbox
  public Faction getSelectedFaction() {
    ListIterator<Faction> factionIterator = entry.getTestValues().getFactions().listIterator();
    while (factionIterator.hasNext()) {
      Faction selectedClass = factionIterator.next();
      if (selectedClass.getName().equals(factionListBox.getValue(factionListBox.getSelectedIndex()))) {
        return selectedClass;
      }
    }
    return null;
  }
  
  // clear "content" #div and add Class SelectClassPanel to it
  public void loadSelectClassPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectClassPanel.getSelectClassPanel(entry, character, user));
  }
  
  //clear "content" #div and add Class SelectTempStats to it
  public void loadSelectTempStatsPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectTempStatsPanel.getSelectTempStatsPanel(entry, character, user));
  }
  
  //creates a new SelectFactionPanel instance
  public static SelectFactionPanel getSelectFactionPanel(TimadorusWebApp entry, Character character, User user) {
    return new SelectFactionPanel(entry, character, user);
  }
  
  //returns and hols current panel information
  private static final HTML getInformation() {
    HTML information = new HTML("<h1>Fraktionen wählen</h1><p>Wählen sie hier die Fraktionen ihres Charakteres. "
                                + "Beachten sie, dass bestimmte Fraktionen nur von bestimmten Rassen sowie Klassen "
                                + "gewählt werden können.</p>");
    return information;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}
