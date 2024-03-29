package org.timadorus.webapp.client.character.ui.potstat;

import java.util.ArrayList;
import java.util.List;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;

import org.timadorus.webapp.client.service.Service;
import org.timadorus.webapp.client.service.ServiceAsync;
import org.timadorus.webapp.client.service.ServiceType;
import org.timadorus.webapp.shared.Action;
import org.timadorus.webapp.shared.Response;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Panel for showing potential stats
 * 
 * @author aaz210
 * 
 */
public class PotStatsWidget extends FormPanel implements PotStatsDialog.Display {

  private Button nextButton;

  private Button prevButton;

  /**
   * main panel
   */
  private VerticalPanel panel;

  /**
   * grid for next/prev buttons
   */
  private FlexTable buttonGrid;

  /**
   * grid for showing pot stats
   */
  private FlexTable myPotStatFlexTable;

  /**
   * This label contains the gender and race name of the character;
   */
  private Label myGenderRaceLbl;

  /**
   * This label contains the class and faction name of the character;
   */
  private Label myClassFactionLbl;

  private final DefaultTimadorusWebApp entry;
  
  private final ServiceAsync<Integer, Integer> crtService = GWT.create(Service.class);
  

  public PotStatsWidget(DefaultTimadorusWebApp entry) {
    super();
    this.entry = entry;

    nextButton = new Button("weiter");
    prevButton = new Button("zurück");
    panel = new VerticalPanel();
    buttonGrid = new FlexTable();
    myPotStatFlexTable = new FlexTable();

    myGenderRaceLbl = new Label("Geschlecht: | Rasse: ");
    myClassFactionLbl = new Label("Klasse: | Fraktion: ");

    // headline
    HTML headline = new HTML("<h1>Potentielle Attribute erhalten</h1>");
    // progressbar picture
    Image progressBar = new Image("media/images/progressbar_5.png");

    fillPotStats();

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
    panel.add(new Label("Schritt 5 von 7"));
    panel.add(myGenderRaceLbl);
    panel.add(myClassFactionLbl);

    panel.add(headline);
    panel.add(myPotStatFlexTable);
    panel.add(buttonGrid);

    // clearing "information" #div and adding actual informations for this panel
    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

    // clearing "content" #div and adding this mainpanel to this #div
    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);
  }

  /**
   * Fills header values into the PotStat Table and formats it.
   */
  private void fillPotStats() {
    fillPotStats(null);
  }

  /**
   * Fills the PotStat table with values of the character. If the {@link Character} is null, an empty table with headers
   * will be created.
   * 
   * @param character
   */
  private void fillPotStats(Character character) {
    // clear previous values
    myPotStatFlexTable.clear();

    // setting properties for potStatGrid
    myPotStatFlexTable.setBorderWidth(0);
    myPotStatFlexTable.setStyleName("selectGrid");

    // filling potStatGrid
    myPotStatFlexTable.setHTML(0, 0, "Attribut");
    myPotStatFlexTable.setHTML(0, 1, "TempStat");
    myPotStatFlexTable.setHTML(0, 2, "PotStat");

    // just add values if a character is available
    if (character != null) {
      for (int i = 0; i < character.getStatList().size(); i++) {
        myPotStatFlexTable.setHTML(i + 1, 0, character.getStatList().get(i).toString());
        myPotStatFlexTable.setHTML(i + 1, 1, character.getTempStats().get(i).toString());
      }
    }
  }

  /**
   * returns and holds current panel information
   * 
   * @return
   */
  private static final HTML getInformation() {
    HTML information = new HTML("<h1>Attribute bekommen</h1><p>Ausgehend von ihren Wahl der temporären Attribute, "
        + "erhalten sie hier zusätzliche Punkte.</p><p>Diese Punkte werden anhand einer "
        + "Tabelle ausgewürfelt.</p>");
    return information;
  }

  @Override
  public FormPanel getFormPanel() {
    return this;
  }

  @Override
  public void addGridEntry(int row, int column, String text) {
    myPotStatFlexTable.setHTML(row, column, text);
  }

  @Override
  public void addNextButtonHandler(final DefaultActionHandler handler) {
    ClickHandler clickHandler = new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        handler.onAction();
      }
    };
    nextButton.addClickHandler(clickHandler);

  }

  @Override
  public void addPrevButtonHandler(final DefaultActionHandler handler) {
    ClickHandler clickHandler = new ClickHandler() {
      @Override
      public void onClick(ClickEvent event) {
        handler.onAction();
      }
    };
    prevButton.addClickHandler(clickHandler);
  }

  /**
   * 
   * @author sage
   * 
   */
  //TODO
  private class PotFieldCallback implements AsyncCallback<Response<Integer>> {

    private int fieldNum;

    private final List<Integer> potStats;

    /**
     * 
     * @param fieldNum
     *          the number of the pot field to set.
     * @param potStats
     */
    public PotFieldCallback(int fieldNum, List<Integer> potStats) {
      this.fieldNum = fieldNum;
      this.potStats = potStats;
    }

    @Override
    public void onFailure(Throwable caught) {
      entry.showDialogBox("Remote Service Failure", "Client/Server Create Character Service Failure!\n"
          + "please contact the support service or server admin. \n" + "RPC that failed was: int makePotStat(int)");
    }

    @Override
    public void onSuccess(Response<Integer> result) {
      potStats.add(fieldNum, result.getResult());
      addGridEntry(fieldNum + 1, 2, result.getResult().toString());
    }
  };

  @Override
  public List<Integer> calculatePotStats(List<Integer> tempStat) {
  
    Action<Integer> action;
    AsyncCallback<Response<Integer>> response;

    List<Integer> potStats = new ArrayList<Integer>();

    for (int i = 0; i < tempStat.size(); i++) {
     
      int temp = tempStat.get(i);
      
      action = new Action<Integer>(ServiceType.MKPOTSTAT , temp);
      response = new PotFieldCallback(i, potStats);
      
      crtService.execute(action, response);
    }

    return potStats;
  }

  @Override
  public void setCharacter(Character character) {
    if (character != null) {
      // setting informations to labels
      myClassFactionLbl.setText("Klasse: " + character.getCharClass().toString() + " | Fraktion: "
          + character.getFaction().toString());
      myGenderRaceLbl.setText("Geschlaecht: " + character.getGender() + " | Rasse: " + character.getRace().toString());
      fillPotStats(character);
    }
  }

  @Override
  public void addToRootPanel(FormPanel aFormPanel) {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(aFormPanel); 
    
  }
}
