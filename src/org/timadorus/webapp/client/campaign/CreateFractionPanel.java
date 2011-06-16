package org.timadorus.webapp.client.campaign;

import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.User;
import org.timadorus.webapp.client.rpc.service.CreateFractionService;
import org.timadorus.webapp.client.rpc.service.CreateFractionServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateFractionPanel extends FormPanel {

  
  // TODO Klasse Fraction anpassen, hier das Formular anpassen, DB Tabelle anlegen
  private static final int CELL_SPACING = 8;

  TimadorusWebApp entry;
  User user;
  Button saveButton         = new Button("Speichern");
  VerticalPanel panel       = new VerticalPanel();
  FlexTable selectGrid      = new FlexTable();

  Label campaignNameLabel         = new Label("Name der Kampagne");
  Label descriptionLabel        = new Label("Beschreibung");
  Label checkCampaignNameLabel = new Label("");
  TextBox campaignNameTextBox = new TextBox();
  TextArea descriptionTextArea = new TextArea();

  public CreateFractionPanel(TimadorusWebApp entryIn, final User user, Campaign campaign) {
    super();
    this.entry = entryIn;
    this.user = user;
    
    // Create a handler for the saveButton and nameField
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {

        if (event.getSource().equals(saveButton)) {
          Fraction fraction = new Fraction();
          fraction.setName(campaignNameTextBox.getText());
          fraction.setBeschreibung(descriptionTextArea.getText());
          fraction.setUsername(user.getUsername());
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
    saveButton.setStylePrimaryName("saveButton");
    
    campaignNameTextBox.setSize("180px", "20px");
    descriptionTextArea.setSize("180px", "100px");

    selectGrid.setCellSpacing(CELL_SPACING);
    
    selectGrid.getCellFormatter().setAlignment(1, 1, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_TOP);
    selectGrid.setBorderWidth(0);
    selectGrid.setStylePrimaryName("selectGrid");

    selectGrid.setWidget(0, 0, campaignNameLabel);
    selectGrid.setWidget(1, 0, descriptionLabel);
    
    selectGrid.setWidget(0, 1, campaignNameTextBox);
    selectGrid.setWidget(1, 1, descriptionTextArea);
    selectGrid.setWidget(0, 2, checkCampaignNameLabel);

    HTML headline = new HTML("<h1>Fraktion anlegen</h1>");

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
