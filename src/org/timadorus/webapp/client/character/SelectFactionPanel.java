package org.timadorus.webapp.client.character;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.timadorus.webapp.client.HistoryStates;
import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.register.RegisterPanel;
import org.timadorus.webapp.client.character.SelectFactionPanel;
import org.timadorus.webapp.client.character.Class;

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
public class SelectFactionPanel extends FormPanel implements HistoryStates {

  TimadorusWebApp entry;

  Character character;

  Button nextButton = new Button("weiter");

  Button prevButton = new Button("zurück");

  VerticalPanel panel = new VerticalPanel();

  FlexTable selectFactionGrid = new FlexTable();

  FlexTable buttonGrid = new FlexTable();

  ListBox factionListBox = new ListBox();

  public SelectFactionPanel(final TimadorusWebApp entry, Character character) {
    super();
    this.entry = entry;
    this.character = character;

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {
        if (event.getSource().equals(prevButton)) {
          loadSelectClassPanel();
        } else if (event.getSource().equals(nextButton)) {
          saveSelectedFaction();
          loadSelectStatsPanelS0();
        }else if(event.getSource().equals(selectFactionGrid)){

          String factionName = factionListBox.getValue(factionListBox.getSelectedIndex());
          ListIterator<Faction> factionIterator = entry.getTestValues().getFactions().listIterator();
          RootPanel.get("information").clear();
          while (factionIterator.hasNext()) {
            Faction newFaction = (Faction) factionIterator.next();
            if (newFaction.getName().equals(factionName)) {
              RootPanel.get("information").add(
                                               new HTML("<h1>" + newFaction.getName() + "</h1><p>"
                                                   + newFaction.getDescription() + "</p>"));


            }

          }
        
        }
        
      }
    }
    Image progressBar = new Image("media/images/progressbar_3.png");

    HTML headline = new HTML("<h1>Fraktion wählen</h1>");

    selectFactionGrid.setBorderWidth(0);
    selectFactionGrid.setStylePrimaryName("selectGrid");

    ListIterator<Faction> factionIterator = entry.getTestValues().getFactions().listIterator();
    while (factionIterator.hasNext()) {
      Faction newFaction = (Faction) factionIterator.next();      
      if (character.getCharClass().getAvailableFactions().contains(newFaction)) {
        if (character.getRace().getAvailableFactions().contains(newFaction)) {
          factionListBox.addItem(newFaction.getName());
        }
      }
    }

    Label factionLabel = new Label("Fraktion wählen: ");

    selectFactionGrid.setWidget(0, 0, factionLabel);
    selectFactionGrid.setWidget(0, 1, factionListBox);

    factionListBox.setVisibleItemCount(factionListBox.getItemCount());

    buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    buttonGrid.setWidth("350px");
    buttonGrid.setWidget(0, 0, prevButton);
    buttonGrid.setWidget(0, 1, nextButton);

    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

    panel.add(progressBar);
    panel.add(new Label("Schritt 3 von 6"));
    panel.add(new Label("Geschlecht: " + character.getGender() + " | Rasse: " + character.getRace().getName()));
    panel.add(new Label("Klasse: " + character.getCharClass().getName()));

    panel.add(headline);
    
    panel.add(selectFactionGrid);

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

  public void saveSelectedFaction() {
    character.setFaction(getSelectedFaction());
  }
  
  public Faction getSelectedFaction() {
    ListIterator<Faction> factionIterator = entry.getTestValues().getFactions().listIterator();
    while (factionIterator.hasNext()) {
      Faction selectedClass = factionIterator.next();
      if (selectedClass.getName().equals(factionListBox.getValue(factionListBox.getSelectedIndex()))) { return selectedClass; }
    }
    return null;
  }
  
  
  public void loadSelectClassPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectClassPanel.getSelectClassPanel(entry, character));
  }
  
  public void loadSelectStatsPanelS0() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectTempStatsPanel.getSelectTempStatsPanel(entry, character));
  }

  public void loadSelectSkillPanel() {
    // todo: creating SelectSkillPanel
  }

  public static SelectFactionPanel getSelectFactionPanel(TimadorusWebApp entry, Character character) {
    // if (characterPanel == null) {
    // characterPanel = new CharacterPanel(entry);
    // }
    // return characterPanel;
    return new SelectFactionPanel(entry, character);
  }

  private static final HTML getInformation() {
    HTML information = new HTML(
                                "<h1>Fraktionen wählen</h1><p>Wählen sie hier die Fraktionen ihres Charakteres. Beachten sie, dass bestimmte Fraktionen nur von bestimmten Rassen sowie Klassen gewählt werden können.</p>");

    return information;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}
