package org.timadorus.webapp.client.character;

import java.util.LinkedList;
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
public class GetPotStatsPanel extends FormPanel implements HistoryStates {

  final TimadorusWebApp entry;

  final Character character;

  Button nextButton = new Button("weiter");

  Button prevButton = new Button("zurück");

  VerticalPanel panel = new VerticalPanel();

  FlexTable selectStatGrid = new FlexTable();

  FlexTable buttonGrid = new FlexTable();

  FlexTable getPotStatGrid = new FlexTable();  

  LinkedList<Integer> potStats = new LinkedList();
  
  
  int i;

  boolean isReady = false;

  public GetPotStatsPanel(final TimadorusWebApp entry, final Character character) {
    super();
    this.entry = entry;
    this.character = character;

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {
        if (event.getSource().equals(prevButton)) {
          loadSelectTempStatsPanel();
        } else if (event.getSource().equals(nextButton)) {
          loadSelectSkillPanel();
        }       
      }
    }

    HTML headline = new HTML("<h1>Potentielle Attribute erhalten</h1>");

    Image progressBar = new Image("media/images/progressbar_5.png");

    getPotStatGrid.setBorderWidth(0);
    getPotStatGrid.setStyleName("selectGrid");
    
    getPotStatGrid.setHTML(0, 0, "Attribut");
    getPotStatGrid.setHTML(0, 1, "TempStat");
    getPotStatGrid.setHTML(0, 2, "PotStat");
    
    calculatePotStats();
    
    for (int i = 0; i < 11; i++){
      getPotStatGrid.setHTML(i + 1, 0, character.getStatList().get(i).toString());
      getPotStatGrid.setHTML(i + 1, 1, character.getTempStats().get(i).toString());
      getPotStatGrid.setHTML(i + 1, 2, potStats.get(i).toString());
    }
    character.setPotStats(potStats);
    
    buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    buttonGrid.setWidth("350px");
    buttonGrid.setWidget(0, 0, prevButton);
    buttonGrid.setWidget(0, 1, nextButton);    

    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    panel.setWidth("100%");

    panel.add(progressBar);
    panel.add(new Label("Schritt 5 von 7"));
    panel.add(new Label("Geschlecht: " + character.getGender() + " | Rasse: " + character.getRace().getName()));
    panel.add(new Label("Klasse: " + character.getCharClass().getName() + " | Faction: "
        + character.getFaction().getName()));

    panel.add(headline);

    panel.add(getPotStatGrid);

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

  }

  public void calculatePotStats(){
    for (int i = 0; i < 11; i++){
      //for future calculations
      int tempStat = character.getTempStats().get(i);
      //random w100 calculation for dummy tests
      int potStat = (int) Math.floor((Math.random() * 100) + 1);
      potStats.add(i, potStat);
    }
  }


  public void loadSelectTempStatsPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectTempStatsPanel.getSelectTempStatsPanel(entry, character));
  }
  
  public void loadSelectSkillPanel(){
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectSkillPanel.getSelectSkillPanel(entry, character));
  }

  public static GetPotStatsPanel getGetPotStatsPanel(TimadorusWebApp entry, Character character) {
    // if (characterPanel == null) {
    // characterPanel = new CharacterPanel(entry);
    // }
    // return characterPanel;
    return new GetPotStatsPanel(entry, character);
  }

  private static final HTML getInformation() {
    HTML information = new HTML(
                                "<h1>Attribute bekommen</h1><p>Ausgehend von ihren Wahl der temporären Attribute, erhalten sie hier zusätzliche Punkte.</p><p>Diese Punkte werden anhand einer Tabelle ausgewürfelt.</p>");

    return information;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}
