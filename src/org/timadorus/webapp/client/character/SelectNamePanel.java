package org.timadorus.webapp.client.character;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.timadorus.webapp.client.HistoryStates;
import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.character.Character;
import org.timadorus.webapp.client.rpc.service.CreateCharacterService;
import org.timadorus.webapp.client.rpc.service.CreateCharacterServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
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

//FormPanel for setting name of Character-Object
public class SelectNamePanel extends FormPanel implements HistoryStates {

  final TimadorusWebApp entry;

  final Character character;

  Button nextButton = new Button("weiter");

  Button prevButton = new Button("zurück");

  VerticalPanel panel = new VerticalPanel();

  FlexTable selectNameGrid = new FlexTable();

  FlexTable buttonGrid = new FlexTable();

  TextBox nameTextBox = new TextBox();

  public SelectNamePanel(final TimadorusWebApp entry, final Character character) {
    super();
    this.entry = entry;
    this.character = character;

    // Create a handler for the sendButton and nameField
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {
        if (event.getSource().equals(prevButton)) {
          loadSelectSkillLvl1Panel();
        } else if (event.getSource().equals(nextButton)) {
          saveSelectedName();
          sendCharacterToServerToSave();
          loadSelectCharacterReadyPanel();
        } else if (event.getSource().equals(selectNameGrid)) {

        }

      }
    }

    HTML headline = new HTML("<h1>Namen wählen</h1>");

    Image progressBar = new Image("media/images/progressbar_7.png");

    selectNameGrid.setBorderWidth(0);
    selectNameGrid.setStylePrimaryName("selectGrid");

    Label nameLabel = new Label("Namen wählen");

    selectNameGrid.setWidget(0, 0, nameLabel);
    selectNameGrid.setWidget(0, 1, nameTextBox);

    buttonGrid.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
    buttonGrid.setWidth("350px");
    buttonGrid.setWidget(0, 0, prevButton);
    buttonGrid.setWidget(0, 1, nextButton);

    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    panel.setWidth("100%");

    panel.add(progressBar);
    panel.add(new Label("Schritt 2 von 7"));
    panel.add(new Label("Geschlecht: " + character.getGender() + " | Rasse: " + character.getRace().getName()));

    panel.add(headline);

    panel.add(selectNameGrid);

    panel.add(buttonGrid);

    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());

    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

    // Add Handlers
    MyHandler handler = new MyHandler();
    nextButton.addClickHandler(handler);
    prevButton.addClickHandler(handler);
    selectNameGrid.addClickHandler(handler);

  }

  public void saveSelectedName() {
    character.setName(nameTextBox.getText());
  }

  public void loadSelectSkillLvl1Panel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectSkill_Level_1_Panel.getSelectSkillLevel1Panel(entry, character));
  }

  public void loadSelectCharacterReadyPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(SelectFactionPanel.getSelectFactionPanel(entry, character));
  }

  public static SelectNamePanel getSelectNamePanel(TimadorusWebApp entry, Character character) {
    return new SelectNamePanel(entry, character);
  }

  private static final HTML getInformation() {
    HTML information = new HTML(
                                "<h1>Namen wählen</h1><p>Wählen sie hier den Namen ihres Charakters. Erlaubt ist was gefällt</p>");

    return information;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
  
  private void sendCharacterToServerToSave() {
    CreateCharacterServiceAsync createServiceAsync = GWT.create(CreateCharacterService.class);

     AsyncCallback<String> asyncCallback = new AsyncCallback<String>() {
      
      @Override
      public void onFailure(Throwable caught) {
        // TODO Auto-generated method stub
        System.out.println("Client/Server Create Character Service Failure!");
      }

      @Override
      public void onSuccess(String result) {
        // TODO Auto-generated method stub
        System.out.println(result);
      }
    };
     createServiceAsync.createCharacter(character, asyncCallback);
}
}