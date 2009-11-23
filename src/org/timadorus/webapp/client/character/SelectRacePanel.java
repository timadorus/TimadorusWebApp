package org.timadorus.webapp.client.character;

import java.util.ArrayList;
import java.util.List;

import org.timadorus.webapp.client.HistoryStates;
import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.register.RegisterPanel;
import org.timadorus.webapp.client.character.PremadeCharacterPanel;

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
public class SelectRacePanel extends FormPanel implements HistoryStates {

  TimadorusWebApp entry;

  Button nextButton = new Button("weiter");

  VerticalPanel panel = new VerticalPanel();

  RadioButton selectMale = new RadioButton("selectGender", "männlich");

  RadioButton selectFemale = new RadioButton("selectGender", "weiblich");

  FlexTable selectGenderGrid = new FlexTable();

  private static SelectRacePanel CustomCharacterPanel;

  public SelectRacePanel(TimadorusWebApp entry) {
    super();
    this.entry = entry;

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {

      }
    }

    // ProgressBar
    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

    Image progressBar = new Image("media/images/progressbar_1.png");
    HTML stepLabel = new HTML("<h1>Schritt 1 von 6</h1>");

    nextButton.setStylePrimaryName("nextButton");

    selectGenderGrid.setBorderWidth(0);
    selectGenderGrid.setStylePrimaryName("selectGenderGrid");
    
    HTML genderLabel = new HTML("Geschlecht auswählen");
    selectGenderGrid.setTitle("Geschlecht auswählen");
    selectGenderGrid.setWidget(0, 0, selectMale);
    selectGenderGrid.setWidget(0, 1, selectFemale);

    panel.add(genderLabel);
    panel.add(selectGenderGrid);
    
    // Make a new list box, adding a few items to it.
    ListBox lb = new ListBox();
    lb.addItem("Bigfoot");
    lb.addItem("bar");
    lb.addItem("baz");
    lb.addItem("toto");
    lb.addItem("tintin");

    // Make enough room for all five items (setting this value to 1 turns it
    // into a drop-down list).
    lb.setVisibleItemCount(5);

    // Add it to the root panel.

    HTML headline = new HTML("<h1>Rasse und Geschlecht wählen</h1>");
    HTML infotext = new HTML(
                             "<p>Wählen sie hier das Geschlecht und die Rasse ihres Charakteres. Beachten sie, dass bestimmte Rassen nur bestimmte Klassen sowie Fraktionen wählen können.</p>");

    panel.add(selectMale);
    panel.add(selectFemale);
    panel.add(lb);

    panel.add(nextButton);

    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

        
    RootPanel.get("information").add(headline);
    RootPanel.get("information").add(infotext);

    // Add Handlers
    MyHandler handler = new MyHandler();

    nextButton.addClickHandler(handler);

  }

  public static SelectRacePanel getCustomCharacterPanel(TimadorusWebApp entry) {
    // if (characterPanel == null) {
    // characterPanel = new CharacterPanel(entry);
    // }
    // return characterPanel;
    return new SelectRacePanel(entry);
  }

  private static final HTML getInformation() {
    HTML information = new HTML("<h1>Auswahl der Klasse</h1><p>Wähle zwischen... !</p>");
    return information;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}
