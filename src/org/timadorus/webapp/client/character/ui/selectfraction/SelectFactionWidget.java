package org.timadorus.webapp.client.character.ui.selectfraction;

import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.character.attributes.Faction;
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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

//Panel for selecting characters Faction
public class SelectFactionWidget extends FormPanel implements SelectFactionDialog.Display {

  private Button nextButton;

  private Button prevButton;

  private VerticalPanel panel;

  // grid for handling selections
  private FlexTable selectFactionGrid;

  // grid for next/prev buttons
  private FlexTable buttonGrid;

  // listbox for available faction
  private ListBox factionListBox;

  public SelectFactionWidget(final DefaultTimadorusWebApp entryIn, Character characterIn) {
    super();

    nextButton = new Button("weiter");
    prevButton = new Button("zurück");
    panel = new VerticalPanel();
    selectFactionGrid = new FlexTable();
    buttonGrid = new FlexTable();
    factionListBox = new ListBox();

    // headline
    HTML headline = new HTML("<h1>Fraktion wählen</h1>");

    // progress bar picture
    Image progressBar = new Image("media/images/progressbar_3.png");

    // setting properties for selectfactiongrid
    selectFactionGrid.setBorderWidth(0);
    selectFactionGrid.setStylePrimaryName("selectGrid");

    for (Faction faction : entryIn.getTestValues().getFactions()) {
      if (characterIn.getCharClass().containsFaction(faction)) {
        if (characterIn.getRace().containsFaction(faction)) {
          factionListBox.addItem(faction.getName());
        }
      }
    }

    Label factionLabel = new Label("Fraktion wählen: ");

    selectFactionGrid.setWidget(0, 0, factionLabel);
    selectFactionGrid.setWidget(0, 1, factionListBox);

    factionListBox.setVisibleItemCount(factionListBox.getItemCount());

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
    panel.add(new Label("Schritt 3 von 9"));
    panel.add(new Label("Geschlecht: " + characterIn.getGender() + " | Rasse: "
        + characterIn.getRace().getName()));
    panel.add(new Label("Klasse: " + characterIn.getCharClass().getName()));

    panel.add(headline);
    panel.add(selectFactionGrid);
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
                                "<h1>Fraktionen wählen</h1><p>Wählen sie hier die Fraktionen ihres Charakteres. "
                                    + "Beachten sie, dass bestimmte Fraktionen nur von bestimmten Rassen sowie Klassen "
                                    + "gewählt werden können.</p>");
    return information;
  }

  @Override
  public FormPanel getFormPanel() {
    return this;
  }

  @Override
  public String getSelectedFaction() {
    return factionListBox.getValue(factionListBox.getSelectedIndex());

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
  public void addNextButtonHandler(final DefaultActionHandler handler) {
    nextButton.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        handler.onAction();

      }
    });
  }

  @Override
  public void addSelectFactionGridHandler(final DefaultActionHandler handler) {

    selectFactionGrid.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        handler.onAction();

      }
    });
  }

  @Override
  public void setInformation(String msg) {
    RootPanel.get("information").clear();
    RootPanel.get("information").add(new HTML(msg));
  }
}
