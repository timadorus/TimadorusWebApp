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
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

@SuppressWarnings("deprecation")
public class CharacterPanel extends FormPanel implements HistoryListener, HistoryStates {

  TimadorusWebApp entry;
  
  Button nextButton = new Button("weiter");
  Button prevButton = new Button("zurück");
  VerticalPanel panel = new VerticalPanel();
  Grid buttonGrid = new Grid(1, 2);

  private static CharacterPanel characterPanel;

  public CharacterPanel(TimadorusWebApp entry) {
    super();
    this.entry = entry;
    setupHistory();
    
    
    prevButton.setStylePrimaryName("prevButton");    
    nextButton.setStylePrimaryName("nextButton");   
    
    buttonGrid.setBorderWidth(0);
    buttonGrid.setWidth("100%");
    
    buttonGrid.setWidget(0, 0, prevButton);
    buttonGrid.setWidget(0, 1, nextButton);
    
    panel.add(buttonGrid);
    panel.setBorderWidth(0);
    
    setWidget(panel);
    setStyleName("formPanel");
    
  }

  public static final CharacterPanel getCharacterPanel(TimadorusWebApp entry) {
    if (characterPanel == null) {
      characterPanel = new CharacterPanel(entry);
    }
    return characterPanel;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }

  private void setupHistory() {
    History.addHistoryListener(this);
    // History.onHistoryChanged("register");
  }

  @Override
  public void onHistoryChanged(String historyToken) {
    if (LOGIN_STATE.equals(historyToken)) {
      getEntry().loadLoginPanel();
    } else if (WELCOME_STATE.equals(historyToken)) {
      getEntry().loadWelcomePanel();

    } else if (CREATE_STATE.equals(historyToken)) {
      getEntry().loadCreateCharacter();
    } else if (REGISTER_STATE.equals(historyToken)) {
      getEntry().loadRegisterPanel();
    }
  }

}
