package org.timadorus.webapp.client.character.ui.potstat;

import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.ui.DefaultActionHandler;

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
  private FlexTable getPotStatGrid;

  public PotStatsWidget(Character characterIn) {
    super();

    nextButton = new Button("weiter");
    prevButton = new Button("zurück");
    panel = new VerticalPanel();
    buttonGrid = new FlexTable();
    getPotStatGrid = new FlexTable();

    // headline
    HTML headline = new HTML("<h1>Potentielle Attribute erhalten</h1>");
    // progressbar picture
    Image progressBar = new Image("media/images/progressbar_5.png");

    // setting properties for potStatGrid
    getPotStatGrid.setBorderWidth(0);
    getPotStatGrid.setStyleName("selectGrid");

    // filling potStatGrid
    getPotStatGrid.setHTML(0, 0, "Attribut");
    getPotStatGrid.setHTML(0, 1, "TempStat");
    getPotStatGrid.setHTML(0, 2, "PotStat");

    for (int i = 0; i < characterIn.getStatList().size(); i++) {
      getPotStatGrid.setHTML(i + 1, 0, characterIn.getStatList().get(i).toString());
      getPotStatGrid.setHTML(i + 1, 1, characterIn.getTempStats().get(i).toString());
    }

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
    panel.add(new Label("Geschlecht: " + characterIn.getGender() + " | Rasse: "
        + characterIn.getRace().getName()));
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
  }

  // returns and hols current panel information
  private static final HTML getInformation() {
    HTML information = new HTML(
                                "<h1>Attribute bekommen</h1><p>Ausgehend von ihren Wahl der temporären Attribute, "
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
    getPotStatGrid.setHTML(row, column, text);
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
}