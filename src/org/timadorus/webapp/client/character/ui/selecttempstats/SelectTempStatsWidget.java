package org.timadorus.webapp.client.character.ui.selecttempstats;

import java.util.ArrayList;
import java.util.List;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;
import org.timadorus.webapp.client.character.ui.potstat.PotStatsDialog;
import org.timadorus.webapp.client.character.ui.selectfraction.SelectFactionDialog;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

// Panel for selecting TempStats
public class SelectTempStatsWidget extends FormPanel implements SelectTempStatsDialog.Display {

  // final DefaultTimadorusWebApp entry;
  //
  // final Character character;
  //
  // User user;

  HTML statPointLabel = new HTML("<h2>Verteilbare Punkte:</h2>");

  HTML statPointViewLabel = new HTML();

  HTML readyLabel = new HTML("Bevor sie ihre Charaktererstellung fortsetzen können, vergeben sie ihre freien "
      + "Attributspunkte");

  Button nextButton = new Button("weiter");

  Button prevButton = new Button("zurück");

  VerticalPanel panel = new VerticalPanel();

  FlexTable selectStatGrid = new FlexTable(); // grid for handling selections

  FlexTable buttonGrid = new FlexTable(); // grid for next/prev buttons

  FlexTable statPointGrid = new FlexTable(); // grid for available statpoints

  int statCosts = 1;

  // int statPoints = 420;

  // // list for handling tempstats
  // List<Integer> tempStats = new ArrayList<Integer>();

  // lists for buttons
  List<Image> incStatButtons = new ArrayList<Image>();

  List<Image> decStatButtons = new ArrayList<Image>();

  List<Image> incTenStatButtons = new ArrayList<Image>();

  List<Image> decTenStatButtons = new ArrayList<Image>();

  List<HTML> statCostHTML = new ArrayList<HTML>();

  // int incremented;

  boolean isReady = false;

  public SelectTempStatsWidget(Character character, List<Integer> tempStats, int statPoints) {
    super();

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
    selectStatGrid.setWidget(12, 0, new Label(character.getStatList().get(11).getName()));
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
    panel.add(new Label("Schritt 4 von 9"));
    panel.add(new Label("Geschlecht: " + character.getGender() + " | Rasse: " + character.getRace().getName()));
    panel.add(new Label("Klasse: " + character.getCharClass().getName() + " | Faction: "
        + character.getFaction().getName()));

    panel.add(headline);

    panel.add(statPointGrid);

    panel.add(selectStatGrid);

    panel.add(buttonGrid);

    panel.add(readyLabel);

    // clearing "information" #div and adding actual informations for this
    // panel
    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

    // clearing "content" #div and adding this mainpanel to this #div
    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

  }

  public void setStatCostColorRed(int index) {
    statCostHTML.get(index).setStyleName("labelColorRed");
  }

  public void setStatCostColorGreen(int index) {
    statCostHTML.get(index).setStyleName("labelColorGreen");
  }

  public void doStatsChanged(int index, int value, int statPoints, int statCosts) {
    // print stat changes to panel
    statPointViewLabel.setHTML("<h2>" + String.valueOf(statPoints) + "</h2>");
    selectStatGrid.setText(index + 1, 1, String.valueOf(value));
    statCostHTML.get(index).setHTML(String.valueOf(statCosts));
    // check if nextbutton is enabled after stat changes,
    // alter colors of available statpoint and set helptext
    // + color
    setReadyStatus(statPoints);
  }

  // return current panel information
  private static final HTML getInformation() {
    HTML information = new HTML("<h1>Attribute Verteilen</h1><p>Hier können sie die Attribute ihres Charakteres "
        + "anpassen. Ihnen stehen dafür 420 freie Punkte zur Verfügung.</p><p>Jeder "
        + "Attributspunkt bis einschließlich Stufe 90 kostet sie einen freien Punkt. "
        + "Ab Stufe 90 zahlen sie 10 freie Punkte");

    return information;
  }

  // sets isReady and colors
  private void setReadyStatus(int statPoints) {
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

  @Override
  public FormPanel getFormPanel() {
    return this;
  }

  @Override
  public void addNextButtonHandler(final DefaultActionHandler handler) {
    nextButton.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addPrevButtonHandler(final DefaultActionHandler handler) {
    prevButton.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        handler.onAction();
      }
    });
  }

  @Override
  public void addIncreaseStatByOneHandler(final StatsChangedHandler handler) {
    for (int i = 0; i < incStatButtons.size(); i++) {
      incStatButtons.get(i).addClickHandler(new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
          handler.onAction(incStatButtons.size());
        }
      });
    }
  }

  @Override
  public void addIncreaseStatByTenHandler(final StatsChangedHandler handler) {
    for (int i = 0; i < incTenStatButtons.size(); i++) {
      incTenStatButtons.get(i).addClickHandler(new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
          handler.onAction(incTenStatButtons.size());
        }
      });
    }
  }

  @Override
  public void addDecreaseStatByOneHandler(final StatsChangedHandler handler) {
    for (int i = 0; i < decStatButtons.size(); i++) {
      decStatButtons.get(i).addClickHandler(new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
          handler.onAction(decStatButtons.size());
        }
      });
    }
  }

  @Override
  public void addDecreaseStatByTenHandler(final StatsChangedHandler handler) {
    for (int i = 0; i < decTenStatButtons.size(); i++) {
      decTenStatButtons.get(i).addClickHandler(new ClickHandler() {

        @Override
        public void onClick(ClickEvent event) {
          handler.onAction(decTenStatButtons.size());
        }
      });
    }
  }

  @Override
  public void loadSelectFactionPanel(DefaultTimadorusWebApp entry, Character character, User user) {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectFactionDialog.getDialog(entry, character, user).getFormPanel());
  }

  @Override
  public void loadGetPotStatsPanel(DefaultTimadorusWebApp entry, Character character, User user) {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(PotStatsDialog.getDialog(entry, character, user).getFormPanel());
  }

}
