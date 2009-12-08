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
public class SelectTempStatsPanel extends FormPanel implements HistoryStates {

  final TimadorusWebApp entry;

  final Character character;

  Button nextButton = new Button("weiter");

  Button prevButton = new Button("zurück");

  VerticalPanel panel = new VerticalPanel();

  FlexTable selectStatGrid = new FlexTable();

  FlexTable buttonGrid = new FlexTable();

  FlexTable statPointGrid = new FlexTable();

  ListBox classListBox = new ListBox();

  int statCosts = 1;

  int statPoints = 420;

  List<Integer> tempStats = new ArrayList<Integer>();

  List<Button> incStatButtons = new ArrayList<Button>();

  List<Button> decStatButtons = new ArrayList<Button>();

  List<Button> incTenStatButtons = new ArrayList<Button>();

  List<Button> decTenStatButtons = new ArrayList<Button>();

  int i;

  boolean isReady = false;

  public SelectTempStatsPanel(final TimadorusWebApp entry, final Character character) {
    super();
    this.entry = entry;
    this.character = character;

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {
        if (event.getSource().equals(prevButton)) {
          loadSelectFactionPanel();
        } else if (event.getSource().equals(nextButton)) {
        } else {
          for (i = 0; i < decStatButtons.size(); i++) {
            if (event.getSource().equals(decStatButtons.get(i))) {
              decStat(i);
              selectStatGrid.setText(i + 1, 1, String.valueOf(tempStats.get(i)));
              selectStatGrid.setText(i + 1, 6, String.valueOf(getStatCosts(tempStats.get(i) + 1)));
              statPointGrid.setText(0, 1, String.valueOf(statPoints));
            } else if (event.getSource().equals(incStatButtons.get(i))) {
              incStat(i);
              selectStatGrid.setText(i + 1, 1, String.valueOf(tempStats.get(i)));
              selectStatGrid.setText(i + 1, 6, String.valueOf(getStatCosts(tempStats.get(i) + 1)));
              statPointGrid.setText(0, 1, String.valueOf(statPoints));
            } else if (event.getSource().equals(decTenStatButtons.get(i))) {
              for (int j = 0; j < 10; j++) {
                decStat(i);
              }
              selectStatGrid.setText(i + 1, 1, String.valueOf(tempStats.get(i)));
              selectStatGrid.setText(i + 1, 6, String.valueOf(getStatCosts(tempStats.get(i) + 1)));
              statPointGrid.setText(0, 1, String.valueOf(statPoints));
            } else if (event.getSource().equals(incTenStatButtons.get(i))) {
              for (int j = 0; j < 10; j++) {
                incStat(i);
              }
              selectStatGrid.setText(i + 1, 1, String.valueOf(tempStats.get(i)));
              selectStatGrid.setText(i + 1, 6, String.valueOf(getStatCosts(tempStats.get(i) + 1)));
              statPointGrid.setText(0, 1, String.valueOf(statPoints));
            }

          }

        }
        setReadyStatus();
      }
    }

    HTML headline = new HTML("<h1>Stats verteilen</h1>");

    Image progressBar = new Image("media/images/progressbar_4.png");

    statPointGrid.setBorderWidth(0);
    statPointGrid.setStylePrimaryName("selectGrid");

    statPointGrid.setWidget(0, 0, new Label("Verbleibende Punkte: "));
    statPointGrid.setWidget(0, 1, new Label(String.valueOf(statPoints)));

    selectStatGrid.setBorderWidth(1);
    selectStatGrid.setStylePrimaryName("selectGrid");

    selectStatGrid.setWidget(0, 0, new Label("Stat"));
    selectStatGrid.setWidget(0, 1, new Label("Wert"));
    selectStatGrid.setWidget(0, 2, new Label("-1"));
    selectStatGrid.setWidget(0, 3, new Label("+1"));
    selectStatGrid.setWidget(0, 4, new Label("-10"));
    selectStatGrid.setWidget(0, 5, new Label("+10"));
    selectStatGrid.setWidget(0, 6, new Label("Kosten für nächsten Punkt"));

    // show stats
    for (int i = 0; i < 11; i++) {
      tempStats.add(character.getStatList().get(i).getTempStat());
      selectStatGrid.setWidget(i + 1, 0, new Label(character.getStatList().get(i).getName()));
      selectStatGrid.setWidget(i + 1, 1, new Label(String.valueOf(tempStats.get(i))));      
      decStatButtons.add(new Button("-1"));
      selectStatGrid.setWidget(i + 1, 2, decStatButtons.get(i));
      incStatButtons.add(new Button("+1"));
      selectStatGrid.setWidget(i + 1, 3, incStatButtons.get(i));
      decTenStatButtons.add(new Button("-10"));
      selectStatGrid.setWidget(i + 1, 4, decTenStatButtons.get(i));
      incTenStatButtons.add(new Button("+10"));
      selectStatGrid.setWidget(i + 1, 5, incTenStatButtons.get(i));
      selectStatGrid.setText(i + 1, 6, String.valueOf(statCosts));
    }
    tempStats.add(character.getStatList().get(11).getTempStat());
    selectStatGrid.setWidget(12, 0, new Label(character.getStatList().get(11).getName()));
    selectStatGrid.setWidget(12, 1, new Label(String.valueOf(tempStats.get(11))));

    buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    buttonGrid.setWidth("350px");
    buttonGrid.setWidget(0, 0, prevButton);
    buttonGrid.setWidget(0, 1, nextButton);
    nextButton.setEnabled(false);

    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

    panel.add(progressBar);
    panel.add(new Label("Schritt 4 von 6"));
    panel.add(new Label("Geschlecht: " + character.getGender() + " | Rasse: " + character.getRace().getName()));
    panel.add(new Label("Klasse: " + character.getCharClass().getName() + " | Faction: "
        + character.getFaction().getName()));

    panel.add(headline);

    panel.add(statPointGrid);

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

    for (i = 0; i < incStatButtons.size(); i++) {
      incStatButtons.get(i).addClickHandler(handler);
      decStatButtons.get(i).addClickHandler(handler);
      incTenStatButtons.get(i).addClickHandler(handler);
      decTenStatButtons.get(i).addClickHandler(handler);
    }

  }

  public int getStatCosts(int currentStat) {
    int cost = 1;
    if (currentStat > 90)
      cost = 10;
    return cost;
  }

  public void incStat(int i) {
    if (((tempStats.get(i)) < 100) && (statPoints > 0)) {
      decStatPoints(getStatCosts(tempStats.get(i) + 1));
      tempStats.set(i, (tempStats.get(i) + 1));
    } else
      System.out.print("maximal 100");
  }

  public void decStat(int i) {
    if (((tempStats.get(i)) > 30) && (statPoints < 750)) {
      incStatPoints(getStatCosts(tempStats.get(i)));
      tempStats.set(i, (tempStats.get(i) - 1));
    } else
      System.out.print("minimal 30");
  }

  public void incStatPoints(int costs) {
    if (statPoints < 420)
      statPoints = statPoints + costs;
    else
      System.out.print("maximal 420");
  }

  public void decStatPoints(int costs) {
    if (statPoints > 0) {
      statPoints = statPoints - costs;
    } else
      System.out.print("minimal 0");
  }

  public void loadSelectFactionPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectFactionPanel.getSelectFactionPanel(entry, character));
  }

  public static SelectTempStatsPanel getSelectTempStatsPanel(TimadorusWebApp entry, Character character) {
    // if (characterPanel == null) {
    // characterPanel = new CharacterPanel(entry);
    // }
    // return characterPanel;
    return new SelectTempStatsPanel(entry, character);
  }

  private static final HTML getInformation() {
    HTML information = new HTML(
                                "<h1>Klasse wählen</h1><p>Wählen sie hier die Klasse ihres Charakteres. Die Klasse bestimmt wie gut sie bestimmte Fähigkeiten lernen können.</p><p>Beachten sie, dass bestimmte Klassen nur bestimmte Rassen sowie Fraktionen wählen können.</p>");

    return information;
  }

  public void setReadyStatus() {
    if (statPoints == 0) {
      isReady = true;
    } else {
      isReady = false;
    }
    nextButton.setEnabled(isReady);
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}
