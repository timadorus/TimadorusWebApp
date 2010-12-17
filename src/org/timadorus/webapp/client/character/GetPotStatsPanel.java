package org.timadorus.webapp.client.character;

import java.util.LinkedList;

import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.User;

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

//Panel for showing potential stats
public class GetPotStatsPanel extends FormPanel {

  final TimadorusWebApp entry;

  final Character character;
  
  User user;

  Button nextButton = new Button("weiter");

  Button prevButton = new Button("zurück");

  VerticalPanel panel = new VerticalPanel(); // main panel 
  
  FlexTable buttonGrid = new FlexTable(); // grid for next/prev buttons

  FlexTable getPotStatGrid = new FlexTable(); //grid for showing pot stats

  LinkedList<Integer> potStats = new LinkedList<Integer>(); //list holding characters potstats
  
  public GetPotStatsPanel(final TimadorusWebApp entryIn, final Character characterIn, final User user) {
    super();
    this.entry = entryIn;
    this.character = characterIn;
    this.user = user;

    // Create a handler for this panels elements
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {
        // handles prev button
        if (event.getSource().equals(prevButton)) {
          loadSelectTempStatsPanel();
          // handles next button
        } else if (event.getSource().equals(nextButton)) {
          loadSelectSkillPanel(user);
        }       
      }
    }

    //headline
    HTML headline = new HTML("<h1>Potentielle Attribute erhalten</h1>");
    //progressbar picture
    Image progressBar = new Image("media/images/progressbar_5.png");

    // setting properties for potStatGrid
    getPotStatGrid.setBorderWidth(0);
    getPotStatGrid.setStyleName("selectGrid");
        
    //calculating potential stats
    calculatePotStats();
    
    //filling potStatGrid
    getPotStatGrid.setHTML(0, 0, "Attribut");
    getPotStatGrid.setHTML(0, 1, "TempStat");
    getPotStatGrid.setHTML(0, 2, "PotStat");
    
    for (int i = 0; i < 11; i++) {
      getPotStatGrid.setHTML(i + 1, 0, characterIn.getStatList().get(i).toString());
      getPotStatGrid.setHTML(i + 1, 1, characterIn.getTempStats().get(i).toString());
      getPotStatGrid.setHTML(i + 1, 2, potStats.get(i).toString());
    }
    
//    for (int i = 0; i < 11; i++) {
//      getPotStatGrid.getWidget(i + 1, 2).setStyleName("labelColorGreen");
//    }
    
    //saving potential stats to character
    characterIn.setPotStats(potStats);
    
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
    panel.add(new Label("Schritt 5 von 7"));
    panel.add(new Label("Geschlecht: " + characterIn.getGender() + " | Rasse: " + characterIn.getRace().getName()));
    panel.add(new Label("Klasse: " + characterIn.getCharClass().getName() + " | Faction: "
                        + characterIn.getFaction().getName()));

    panel.add(headline);
    panel.add(getPotStatGrid);
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

  }

  //calculates potStats
  public void calculatePotStats() {
    for (int i = 0; i < 12; i++) {
      //random w100 calculation for dummy tests
      final int times = 100;
      int potStat = (int) Math.floor((Math.random() * times) + 1);
      potStats.add(i, potStat);
    }
  }

  // clear "content" #div and add Class SelectTempStats to it
  public void loadSelectTempStatsPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectTempStatsPanel.getSelectTempStatsPanel(entry, character, user));
  }
  // clear "content" #div and add Class SelectSkillPanel to it
  public void loadSelectSkillPanel(User user2) {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectSkillPanel.getSelectSkillPanel(entry, character, user));
  }

  //returns a new instance of GetPotStatsPanel
  public static GetPotStatsPanel getGetPotStatsPanel(TimadorusWebApp entry, Character character, User user) {  
    return new GetPotStatsPanel(entry, character, user);
  }

  //returns and hols current panel information
  private static final HTML getInformation() {
    HTML information = new HTML("<h1>Attribute bekommen</h1><p>Ausgehend von ihren Wahl der temporären Attribute, "
                                + "erhalten sie hier zusätzliche Punkte.</p><p>Diese Punkte werden anhand einer "
                                + "Tabelle ausgewürfelt.</p>");
    return information;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}
