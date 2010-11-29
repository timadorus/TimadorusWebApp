package org.timadorus.webapp.client.character;

import org.timadorus.webapp.client.HistoryStates;
import org.timadorus.webapp.client.TimadorusWebApp;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable;

//This is the FormPanel for the End of creating-Character-Object-procedure
public class CharacterReadyPanel extends FormPanel implements HistoryStates {

  TimadorusWebApp entry;

  Button nextButton = new Button("weiter");

  VerticalPanel panel = new VerticalPanel();

  FlexTable selectGrid = new FlexTable();

  public CharacterReadyPanel(TimadorusWebApp entryIn, Character characterIn) {
    super();
    this.entry = entryIn;
    HTML congratulationLabel = new HTML(
                                        "<p>Herzlichen Glückwunsch.</p><p>Ihr Charakter wurde gespeichert. Loggen sie sich auf dem Client mit ihren Accountdaten ein");

    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    panel.setWidth("100%");
    
    panel.add(congratulationLabel);

    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);
    
  }

  public static CharacterReadyPanel getCharacterReadyPanel(TimadorusWebApp entry, Character character) {
    return new CharacterReadyPanel(entry, character);
  }

  private static final HTML getInformation() {
    HTML information = new HTML(
                                "<h1>Charakter erstellt</h1><p>Herzlichen Glückwunsch. Sie können nun den Client starten und ihren neuen Charakter ausprobieren!");
    return information;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}
