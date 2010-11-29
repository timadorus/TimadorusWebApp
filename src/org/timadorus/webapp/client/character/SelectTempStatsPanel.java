package org.timadorus.webapp.client.character;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
import com.google.gwt.user.client.ui.Image;

// Panel for selecting TempStats
public class SelectTempStatsPanel extends FormPanel implements HistoryStates {

  final TimadorusWebApp entry;

  final Character character;

  HTML statPointLabel = new HTML("<h2>Verteilbare Punkte:</h2>");

  HTML statPointViewLabel = new HTML();

  HTML readyLabel = new HTML(
                             "Bevor sie ihre Charaktererstellung fortsetzen können, vergeben sie ihre freien "
                             + "Attributspunkte");

  Button nextButton = new Button("weiter");

  Button prevButton = new Button("zurück");

  VerticalPanel panel = new VerticalPanel();

  FlexTable selectStatGrid = new FlexTable(); // grid for handling selections

  FlexTable buttonGrid = new FlexTable(); // grid for next/prev buttons

  FlexTable statPointGrid = new FlexTable(); // grid for available statpoints

  int statCosts = 1;

  int statPoints = 420;

  // list for handling tempstats
  LinkedList<Integer> tempStats = new LinkedList<Integer>();

  // lists for buttons
  List<Image> incStatButtons = new ArrayList<Image>();

  List<Image> decStatButtons = new ArrayList<Image>();

  List<Image> incTenStatButtons = new ArrayList<Image>();

  List<Image> decTenStatButtons = new ArrayList<Image>();

  List<HTML> statCostHTML = new ArrayList<HTML>();

 

  int incremented;

  boolean isReady = false;

  public SelectTempStatsPanel(final TimadorusWebApp entryIn, final Character characterIn) {
    super();
    this.entry = entryIn;
    this.character = characterIn;

    // Create a handler for this panels elements
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {
        // handles prev button
        if (event.getSource().equals(prevButton)) {
          loadSelectFactionPanel();
          // handles next button
        } else if (event.getSource().equals(nextButton)) {
          saveTempStats();
          loadGetPotStatsPanel();
        } else {
          // handles inc/dec statbuttons
          for (incremented = 0; incremented < decStatButtons.size(); incremented++) {
            if (event.getSource().equals(decStatButtons.get(incremented))) {
              decStat(incremented);
            } else if (event.getSource().equals(incStatButtons.get(incremented))) {
              incStat(incremented);
            } else if (event.getSource().equals(decTenStatButtons.get(incremented))) {
              for (int j = 0; j < 10; j++) {
                decStat(incremented);
              }
            } else if (event.getSource().equals(incTenStatButtons.get(incremented))) {
              for (int j = 0; j < 10; j++) {
                incStat(incremented);
              }
            }
            // print stat changes to panel
            statPointViewLabel.setHTML("<h2>" + String.valueOf(statPoints) + "</h2>");
            selectStatGrid.setText(incremented + 1, 1, String.valueOf(tempStats.get(incremented)));
            statCostHTML.get(incremented).setHTML(String.valueOf(getStatCosts(tempStats.get(incremented) + 1)));
            // check if nextbutton is enabled after stat changes, alter colors of available statpoint and set helptext +
            // color
            setReadyStatus();
          }
          // handle StatCostsLabelColor for each increase/decrease event
          for (incremented = 0; incremented < decStatButtons.size(); incremented++) {
            int nextCost = getStatCosts(tempStats.get(incremented) + 1);
            if ((nextCost > statPoints) || ((tempStats.get(incremented)) == 100)) {
              statCostHTML.get(incremented).setStyleName("labelColorRed");
            } else {
              statCostHTML.get(incremented).setStyleName("labelColorGreen");
            }
          }

        }

      }

    }
    // headline
    HTML headline = new HTML("<h1>Temporäre Attribute verteilen</h1>");
    // progressbar picure
    Image progressBar = new Image("media/images/progressbar_4.png");

    // setting properties for statpointgrid
    statPointGrid.setBorderWidth(0);

    statPointViewLabel.setHTML("<h2>" + String.valueOf(statPoints) + "<h2>");
    statPointViewLabel.setStyleName("labelColorGreen");
    statPointLabel.setStyleName("labelColorGreen");

    statPointGrid.setWidget(0, 0, statPointLabel);
    statPointGrid.setWidget(0, 1, statPointViewLabel);

    // setting properties for selectstatgrid
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
    // change css class for selectstatgrid to "underlinedText"
    for (int i = 0; i < 8; i++) {
      selectStatGrid.getWidget(0, i).setStyleName("underlinedText");
    }

    // filling the selectstatgrid
    for (int i = 0; i < 11; i++) {
      tempStats.add(characterIn.getStatList().get(i).getTempStat());
      selectStatGrid.setWidget(i + 1, 0, new Label(characterIn.getStatList().get(i).getName()));
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
    tempStats.add(characterIn.getStatList().get(11).getTempStat());
    selectStatGrid.setWidget(12, 0, new Label(characterIn.getStatList().get(11).getName()));
    selectStatGrid.setWidget(12, 1, new Label(String.valueOf(tempStats.get(11))));

    // setting properties of readylabel
    readyLabel.setStyleName("labelColorRed");

    // setting properties of buttongrid
    buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    buttonGrid.setWidth("350px");
    buttonGrid.setWidget(0, 0, prevButton);
    buttonGrid.setWidget(0, 1, nextButton);
    // disable nextbutton
    nextButton.setEnabled(false);

    // setting properties of the main panel
    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    panel.setWidth("100%");

    // adding widgets to the main panel
    panel.add(progressBar);
    panel.add(new Label("Schritt 4 von 7"));
    panel.add(new Label("Geschlecht: " + characterIn.getGender() + " | Rasse: " + characterIn.getRace().getName()));
    panel.add(new Label("Klasse: " + characterIn.getCharClass().getName() + " | Faction: "
        + characterIn.getFaction().getName()));

    panel.add(headline);

    panel.add(statPointGrid);

    panel.add(selectStatGrid);

    panel.add(buttonGrid);

    panel.add(readyLabel);

    // clearing "information" #div and adding actual informations for this panel
    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

    // clearing "content" #div and adding this mainpanel to this #div
    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

    // add handlers
    MyHandler handler = new MyHandler();
    nextButton.addClickHandler(handler);
    prevButton.addClickHandler(handler);

    for (incremented = 0; incremented < incStatButtons.size(); incremented++) {
      incStatButtons.get(incremented).addClickHandler(handler);
      decStatButtons.get(incremented).addClickHandler(handler);
      incTenStatButtons.get(incremented).addClickHandler(handler);
      decTenStatButtons.get(incremented).addClickHandler(handler);
    }

  }

  // saving tempstats
  public void saveTempStats() {
    if (!tempStats.isEmpty()) {
      character.setTempStat(tempStats);
    } else {
      System.out.println("tempStats is empty");
    }
  }

  // calculates current statcost for stat i
  public int getStatCosts(int currentStat) {
    int cost = 1;
    if (currentStat > 90) {
      cost = 10;
    }
    return cost;
  }

  // increases the stat i
  public void incStat(int i) {
    if (((tempStats.get(i)) < 100) && (statPoints >= (getStatCosts(tempStats.get(i) + 1)))) {
      decStatPoints(getStatCosts(tempStats.get(i) + 1));
      tempStats.set(i, (tempStats.get(i) + 1));
    } else {
      System.out.print("maximal 100");
    }
  }

  // decreases the stat i
  public void decStat(int i) {
    if (((tempStats.get(i)) > 30) && (statPoints < 750)) {
      incStatPoints(getStatCosts(tempStats.get(i)));
      tempStats.set(i, (tempStats.get(i) - 1));
    } else {
      System.out.print("minimal 30");
    }
  }

  // increase available stat points
  public void incStatPoints(int costs) {
    if (statPoints < 420) {
      statPoints = statPoints + costs;
    } else {
      System.out.print("maximal 420");
    }
  }

  // decreases available stat points
  public void decStatPoints(int costs) {
    if (statPoints > 0) {
      statPoints = statPoints - costs;
    } else {
      System.out.print("minimal 0");
    }
  }

  // clear "content" #div and add Class SelectFactionPanel to it
  public void loadSelectFactionPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectFactionPanel.getSelectFactionPanel(entry, character));
  }

  // clear "content" #div and add Class GetPotStatsPanel to it
  public void loadGetPotStatsPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(GetPotStatsPanel.getGetPotStatsPanel(entry, character));
  }

  // creates a new SelectTempStats instance
  public static SelectTempStatsPanel getSelectTempStatsPanel(TimadorusWebApp entry, Character character) {
    return new SelectTempStatsPanel(entry, character);
  }

  // return current panel information
  private static final HTML getInformation() {
    HTML information = new HTML(
                                "<h1>Attribute Verteilen</h1><p>Hier können sie die Attribute ihres Charakteres "
                                + "anpassen. Ihnen stehen dafür 420 freie Punkte zur Verfügung.</p><p>Jeder "
                                + "Attributspunkt bis einschließlich Stufe 90 kostet sie einen freien Punkt. Ab Stufe "
                                + "90 zahlen sie 10 freie Punkte");

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
