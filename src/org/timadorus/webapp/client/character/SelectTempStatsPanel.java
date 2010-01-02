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

  HTML statPointLabel = new HTML("<h2>Verteilbare Punkte:</h2>");

  HTML statPointViewLabel = new HTML();

  HTML readyLabel = new HTML(
                             "Bevor sie ihre Charaktererstellung fortsetzen können, vergeben sie ihre freien Attributspunkte");

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

  List<Image> incStatButtons = new ArrayList<Image>();

  List<Image> decStatButtons = new ArrayList<Image>();

  List<Image> incTenStatButtons = new ArrayList<Image>();

  List<Image> decTenStatButtons = new ArrayList<Image>();

  List<HTML> statCostHTML = new ArrayList<HTML>();

  // for tests only
  Button testButton = new Button();

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
          saveTempStats();
          loadGetPotStatsPanel();
        } else if (event.getSource().equals(testButton)) {
          nextButton.setEnabled(true);
        } else {
          for (i = 0; i < decStatButtons.size(); i++) {
            if (event.getSource().equals(decStatButtons.get(i))) {
              decStat(i);
            } else if (event.getSource().equals(incStatButtons.get(i))) {
              incStat(i);
            } else if (event.getSource().equals(decTenStatButtons.get(i))) {
              for (int j = 0; j < 10; j++) {
                decStat(i);
              }
            } else if (event.getSource().equals(incTenStatButtons.get(i))) {
              for (int j = 0; j < 10; j++) {
                incStat(i);
              }
            }
            statPointViewLabel.setHTML("<h2>" + String.valueOf(statPoints) + "</h2>");
            selectStatGrid.setText(i + 1, 1, String.valueOf(tempStats.get(i)));
            statCostHTML.get(i).setHTML(String.valueOf(getStatCosts(tempStats.get(i) + 1)));

            setReadyStatus();
          }
          // handle StatCostsLabelColor for each increase/decrease event
          for (i = 0; i < decStatButtons.size(); i++) {
            int nextCost = getStatCosts(tempStats.get(i) + 1);
            if ((nextCost > statPoints) || ((tempStats.get(i)) == 100)) {
              statCostHTML.get(i).setStyleName("labelColorRed");
            } else {
              statCostHTML.get(i).setStyleName("labelColorGreen");
            }
          }

        }

      }

    }

    HTML headline = new HTML("<h1>Temporäre Attribute verteilen</h1>");

    Image progressBar = new Image("media/images/progressbar_4.png");

    statPointGrid.setBorderWidth(0);

    statPointViewLabel.setHTML("<h2>" + String.valueOf(statPoints) + "<h2>");
    statPointViewLabel.setStyleName("labelColorGreen");
    statPointLabel.setStyleName("labelColorGreen");

    statPointGrid.setWidget(0, 0, statPointLabel);
    statPointGrid.setWidget(0, 1, statPointViewLabel);

    selectStatGrid.setBorderWidth(0);
    selectStatGrid.setStyleName("selectGrid");

    selectStatGrid.setWidget(0, 0, new Label("Attribut"));
    selectStatGrid.setWidget(0, 1, new Label("Stufe"));
    selectStatGrid.setWidget(0, 2, new Label("Kosten"));
    selectStatGrid.setWidget(0, 3, new Label(""));
    selectStatGrid.setWidget(0, 4, new Label("-1"));
    selectStatGrid.setWidget(0, 5, new Label("+1"));
    selectStatGrid.setWidget(0, 6, new Label("-10"));
    selectStatGrid.setWidget(0, 7, new Label("+10"));
    for (int i = 0; i < 8; i++) {
      selectStatGrid.getWidget(0, i).setStyleName("underlinedText");
    }

    // show stats
    for (int i = 0; i < 11; i++) {
      tempStats.add(character.getStatList().get(i).getTempStat());
      selectStatGrid.setWidget(i + 1, 0, new Label(character.getStatList().get(i).getName()));
      selectStatGrid.setWidget(i + 1, 1, new Label(String.valueOf(tempStats.get(i))));
      statCostHTML.add(new HTML(String.valueOf(statCosts)));
      statCostHTML.get(i).setStyleName("labelColorGreen");
      selectStatGrid.setWidget(i + 1, 2, statCostHTML.get(i));
      decStatButtons.add(new Image("/media/images/minus1.png"));
      selectStatGrid.setWidget(i + 1, 4, decStatButtons.get(i));
      incStatButtons.add(new Image("/media/images/plus1.png"));
      incStatButtons.get(i).setStyleName("plus1Button");
      selectStatGrid.setWidget(i + 1, 5, incStatButtons.get(i));
      decTenStatButtons.add(new Image("/media/images/minus10.png"));
      selectStatGrid.setWidget(i + 1, 6, decTenStatButtons.get(i));
      incTenStatButtons.add(new Image("/media/images/plus10.png"));
      selectStatGrid.setWidget(i + 1, 7, incTenStatButtons.get(i));

    }
    tempStats.add(character.getStatList().get(11).getTempStat());
    selectStatGrid.setWidget(12, 0, new Label(character.getStatList().get(11).getName()));
    selectStatGrid.setWidget(12, 1, new Label(String.valueOf(tempStats.get(11))));

    readyLabel.setStyleName("labelColorRed");

    buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    buttonGrid.setWidth("350px");
    buttonGrid.setWidget(0, 0, prevButton);
    buttonGrid.setWidget(0, 1, nextButton);
    nextButton.setEnabled(false);

    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    panel.setWidth("100%");

    panel.add(progressBar);
    panel.add(new Label("Schritt 4 von 7"));
    panel.add(new Label("Geschlecht: " + character.getGender() + " | Rasse: " + character.getRace().getName()));
    panel.add(new Label("Klasse: " + character.getCharClass().getName() + " | Faction: "
        + character.getFaction().getName()));

    panel.add(headline);

    panel.add(statPointGrid);

    panel.add(selectStatGrid);

    // for tests only

    panel.add(testButton);

    panel.add(buttonGrid);

    panel.add(readyLabel);

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

  public void saveTempStats() {
    if (!tempStats.isEmpty()) {
      character.setTempStat(tempStats);
    } else {
      System.out.println("tempStats is empty");
    }
  }

  public int getStatCosts(int currentStat) {
    int cost = 1;
    if (currentStat > 90)
      cost = 10;
    return cost;
  }

  public void incStat(int i) {
    if (((tempStats.get(i)) < 100) && (statPoints >= (getStatCosts(tempStats.get(i) + 1)))) {
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

  public void loadGetPotStatsPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(GetPotStatsPanel.getGetPotStatsPanel(entry, character));
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
                                "<h1>Attribute Verteilen</h1><p>Hier können sie die Attribute ihres Charakteres anpassen. Ihnen stehen dafür 420 freie Punkte zur Verfügung.</p><p>Jeder Attributspunkt bis einschließlich Stufe 90 kostet sie einen freien Punkt. Ab Stufe 90 zahlen sie 10 freie Punkte");

    return information;
  }

  // sets isReady and colors
  public void setReadyStatus() {
    if (statPoints == 0) {
      isReady = true;
      statPointLabel.setStyleName("labelColorRed");
      statPointViewLabel.setStyleName("labelColorRed");
      readyLabel.setHTML("Sie haben ihre gesamten Punkte Verteilt und können fortfahren");
      readyLabel.setStyleName("labelColorGreen");
    } else {
      isReady = false;
      statPointLabel.setStyleName("labelColorGreen");
      statPointViewLabel.setStyleName("labelColorGreen");
      readyLabel
          .setHTML("Bevor sie ihre Charaktererstellung fortsetzen können, vergeben sie ihre freien Attributspunkte");
      readyLabel.setStyleName("labelColorRed");
    }
    nextButton.setEnabled(isReady);
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}
