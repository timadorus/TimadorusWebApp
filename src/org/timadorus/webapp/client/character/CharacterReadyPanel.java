package org.timadorus.webapp.client.character;

import java.util.ArrayList;
import java.util.List;

import org.timadorus.webapp.client.HistoryStates;
import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.register.RegisterPanel;

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

public class CharacterReadyPanel extends FormPanel implements HistoryStates {

  TimadorusWebApp entry;

  Button nextButton = new Button("weiter");

  VerticalPanel panel = new VerticalPanel();

  FlexTable selectGrid = new FlexTable();

  Label barbarianLabel = new Label("Barbar");

  Label wizzardLabel = new Label("Zauberer");

  Label hunterLabel = new Label("Waldläuferin");

  RadioButton selectBarbarian = new RadioButton("selectCharacter");

  RadioButton selectWizzard = new RadioButton("selectCharacter");
  
  RadioButton selectHunter = new RadioButton("selectCharacter");

  Image selectBarbarianImage = new Image("media/images/characterBarbarian.png");

  Image selectWizzardImage = new Image("media/images/characterWizzard.png");

  Image selectHunterImage = new Image("media/images/characterHunter.png");

  private static CharacterReadyPanel CharacterReadyPanel;

  public CharacterReadyPanel(TimadorusWebApp entry, Character character) {
    super();
    this.entry = entry;
 
    RootPanel.get("information").add(getInformation());
    
    HTML congratulationLabel = new HTML("<p>Herzlichen Glückwunsch.</p><p>Ihr Charakter wurde gespeichert. Loggen sie sich auf dem Client mit ihren Accountdaten ein"); 

    RootPanel.get("content").add(congratulationLabel);
  }
  
  public static CharacterReadyPanel getCharacterReadyPanel(TimadorusWebApp entry, Character character){    
      return new CharacterReadyPanel(entry,character);
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
