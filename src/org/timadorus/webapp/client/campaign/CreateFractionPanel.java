package org.timadorus.webapp.client.campaign;

import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.User;
import org.timadorus.webapp.client.rpc.service.CreateFractionService;
import org.timadorus.webapp.client.rpc.service.CreateFractionServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateFractionPanel extends FormPanel {

  private static final int CELL_SPACING = 8;

  TimadorusWebApp entry;
  User user;
  Campaign campaign;
  
  Button saveButton         = new Button("Speichern");
  VerticalPanel panel       = new VerticalPanel();
  FlexTable selectGrid      = new FlexTable();

  Label fractionNameLabel         = new Label("Name der Fraktion");
  Label fractionDisplayNameLabel         = new Label("Anzeigename der Fraktion");
  Label descriptionLabel        = new Label("Beschreibung");
  Label informationLabel        = new Label("Informationen");
  Label templateLabel        = new Label("Vorlage-Fraktion");
  Label setTemplateLabel        = new Label("Dauerhafte Übernahme der Vorlage");
  Label checkFractionNameLabel = new Label("");
  
  TextBox fractionNameTextBox = new TextBox();
  TextBox fractionDisplayNameTextBox = new TextBox();
  TextArea descriptionTextArea = new TextArea();
  TextArea informationTextArea = new TextArea();
  ListBox templateListBox = new ListBox();
  CheckBox setTemplateCheckBox = new CheckBox();

  public CreateFractionPanel(TimadorusWebApp entryIn, final User user, final Campaign campaign) {
    super();
    this.entry = entryIn;
    this.user = user;
    this.campaign = campaign;
    
    // Create a key-up handler for the nameField
    class MyKeyUpHandler implements KeyUpHandler {

      @Override
      public void onKeyUp(KeyUpEvent event) {
        checkFraction(fractionNameTextBox.getText(), campaign.getName());
      }
      private void checkFraction(String fractionName, String campaignName) {
        CreateFractionServiceAsync createFractionServiceAsync = GWT.create(CreateFractionService.class);
        AsyncCallback<String> asyncCallback = new AsyncCallback<String>() {
          
          public void onSuccess(String result) {
            if (result.equals("SUCCESS")) {
              checkFractionNameLabel.setText("");
            } else {
              checkFractionNameLabel.setStyleName("error");
              checkFractionNameLabel.setText("Ist bereits vergeben");
            }            
          }
          
          public void onFailure(Throwable caught) {
            System.out.println(caught);
          }
        };
        createFractionServiceAsync.existsFraction(fractionName, campaignName, asyncCallback);
      }
    }
    
    // Create a handler for the saveButton and nameField
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {

        if (event.getSource().equals(saveButton)) {
          Fraction fraction = new Fraction();
          fraction.setCampaignName(campaign.getName());
          fraction.setName(fractionNameTextBox.getText());
          fraction.setAnzeigename(fractionDisplayNameTextBox.getText());
          fraction.setBeschreibung(descriptionTextArea.getText());
          fraction.setInformationen(informationTextArea.getText());
          sendToServer(fraction);
          loadSavedFractionPanel(user);        
        }
      }
      
      private void sendToServer(Fraction fraction) {
        CreateFractionServiceAsync createFractionServiceAsync = GWT.create(CreateFractionService.class);
        AsyncCallback<String> asyncCallback = new AsyncCallback<String>() {
          
          public void onSuccess(String result) {
            if (result.equals("SUCCESS")) {
              System.out.println("Sucess");
            } else {
              System.out.println("Failure");
            }            
          }
          
          public void onFailure(Throwable caught) {
            System.out.println(caught);
          }
        };
        createFractionServiceAsync.createFraction(fraction, asyncCallback);
      }
    }
    
    // Style Components
    HTML headline = new HTML("<h1>Fraktion anlegen</h1>");
    
    saveButton.setStylePrimaryName("saveButton");
    
    fractionNameTextBox.setSize("180px", "20px");
    fractionDisplayNameTextBox.setSize("180px", "20px");
    descriptionTextArea.setSize("180px", "100px");
    informationTextArea.setSize("180px", "100px");
    setTemplateCheckBox.setEnabled(false);

    selectGrid.setCellSpacing(CELL_SPACING);
    
    selectGrid.getCellFormatter().setAlignment(1, 1, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_TOP);
    selectGrid.setBorderWidth(0);
    selectGrid.setStylePrimaryName("selectGrid");

    selectGrid.setWidget(0, 0, fractionNameLabel);
    selectGrid.setWidget(1, 0, fractionDisplayNameLabel);
    selectGrid.setWidget(2, 0, descriptionLabel);
    selectGrid.setWidget(2 + 1, 0, informationLabel);
    selectGrid.setWidget(2 + 2, 0, templateLabel);
    selectGrid.setWidget(2 + 2 + 1, 0, setTemplateLabel);
    
    selectGrid.setWidget(0, 1, fractionNameTextBox);
    selectGrid.setWidget(1, 1, fractionDisplayNameTextBox);
    selectGrid.setWidget(2, 1, descriptionTextArea);
    selectGrid.setWidget(2 + 1, 1, informationTextArea);
    selectGrid.setWidget(2 + 2, 1, templateListBox);
    selectGrid.setWidget(2 + 2 + 1, 1, setTemplateCheckBox);
    selectGrid.setWidget(2 + 2 + 2, 1, checkFractionNameLabel);

    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
    panel.setWidth("100%");

    panel.add(headline);

    panel.add(selectGrid);
    panel.add(saveButton);

    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());
    
    // Add Handlers
    MyHandler handler = new MyHandler();    
    saveButton.addClickHandler(handler);
    
    MyKeyUpHandler keyUpHandler = new MyKeyUpHandler();
    fractionNameTextBox.addKeyUpHandler(keyUpHandler);
  }
  
  public void loadSavedFractionPanel(User userIn) {
    RootPanel.get("information").clear();
    RootPanel.get("content").clear();
    panel.clear();
    HTML savedText = new HTML("<h1>Fraktion gespeichert</h1><p>Die Fraktion wurde erfolgreich hinzugefügt</p>");
    panel.add(savedText);
    RootPanel.get("content").add(panel);
  }
  
  public static CreateFractionPanel getCreateFractionPanel(TimadorusWebApp entry, User user, Campaign campaign) {
    return new CreateFractionPanel(entry, user, campaign);
  }

  private static final HTML getInformation() {
    HTML information = new HTML("<h1>Fraktion anlegen</h1><p>Hier kannst du deiner Kampagne eine Fraktion hinzufügen."
                                + "</p>");
    return information;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}
