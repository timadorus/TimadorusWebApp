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
public class SelectStatsPanelS0 extends FormPanel implements HistoryStates {

  final TimadorusWebApp entry;

  final Character character;

  Button nextButton = new Button("weiter");

  Button prevButton = new Button("zurück");

  VerticalPanel panel = new VerticalPanel();

  FlexTable selectStatGrid = new FlexTable();

  FlexTable buttonGrid = new FlexTable();

  ListBox classListBox = new ListBox();

  public SelectStatsPanelS0(final TimadorusWebApp entry, final Character character) {
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
        } 

      }
    }

    HTML headline = new HTML("<h1>Stats verteilen</h1>");

    Image progressBar = new Image("media/images/progressbar_4.png");

    selectStatGrid.setBorderWidth(0);
    selectStatGrid.setStylePrimaryName("selectGrid");

    

    Label statLabel = new Label("Statpunkte verteilen: ");

    selectStatGrid.setWidget(0, 0, new Label("Stat"));
    selectStatGrid.setWidget(0, 1, new Label("Wert"));
    selectStatGrid.setWidget(0, 2, new Label("Wert verringern"));
    selectStatGrid.setWidget(0, 3, new Label("Wert erhöhen"));
    selectStatGrid.setWidget(0, 4, new Label("Kosten pro Punkt"));
    
    //show stats
    for (int i=0;i<12;i++){
      selectStatGrid.setWidget(i+1, 0, new Label(character.getStatList().get(i).getName()));
      selectStatGrid.setWidget(i+1, 1, new Label(character.getStatList().get(i).getTempStat().toString()));      
    }
    
    

    buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    buttonGrid.setWidth("350px");
    buttonGrid.setWidget(0, 0, prevButton);
    buttonGrid.setWidget(0, 1, nextButton);

    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

    panel.add(progressBar);
    panel.add(new Label("Schritt 4 von 6"));
    panel.add(new Label("Geschlecht: " + character.getGender() + " | Rasse: " + character.getRace().getName()));
    panel.add(new Label("Klasse: " + character.getCharClass().getName() + " | Faction: " + character.getFaction().getName()));

    panel.add(headline);

    panel.add(selectStatGrid);

    panel.add(buttonGrid);

    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

    // Add Handlers
    MyHandler handler = new MyHandler();
    nextButton.addClickHandler(handler);
    prevButton.addClickHandler(handler);
    selectStatGrid.addClickHandler(handler);

  }

  public void saveSelectedClass() {
    character.setCharClass(getSelectedClass());
  }

  public Class getSelectedClass() {
    ListIterator<Class> classIterator = entry.getTestValues().getClasses().listIterator();
    while (classIterator.hasNext()) {
      Class selectedClass = classIterator.next();
      if (selectedClass.getName().equals(classListBox.getValue(classListBox.getSelectedIndex()))) { return selectedClass; }
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

  public static SelectStatsPanelS0 getSelectClassPanel(TimadorusWebApp entry, Character character) {
    // if (characterPanel == null) {
    // characterPanel = new CharacterPanel(entry);
    // }
    // return characterPanel;
    return new SelectStatsPanelS0(entry, character);
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
