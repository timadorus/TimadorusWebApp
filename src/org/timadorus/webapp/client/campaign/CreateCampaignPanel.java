package org.timadorus.webapp.client.campaign;

import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.User;
import org.timadorus.webapp.client.rpc.service.CreateCampaignService;
import org.timadorus.webapp.client.rpc.service.CreateCampaignServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.History;
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

public class CreateCampaignPanel extends FormPanel {

  //private static final int MAX_DESCRIPTION_SIZE = 1000;
  private static final int CELL_SPACING = 8;

  TimadorusWebApp entry;
  
  User user;

  Button saveButton         = new Button("Speichern");
  VerticalPanel panel       = new VerticalPanel();
  FlexTable selectGrid      = new FlexTable();
  Label campaignNameLabel         = new Label("Name der Kampagne");
  Label descriptionLabel        = new Label("Beschreibung");
  TextBox campaignNameTextBox = new TextBox();
  TextArea descriptionTextArea = new TextArea();

  public CreateCampaignPanel(TimadorusWebApp entryIn, final User user) {
    super();
    this.entry = entryIn;
    this.user = user;

    // Create a handler for the saveButton and nameField
    class MyHandler implements ClickHandler {
      public void onClick(ClickEvent event) {

        if (event.getSource().equals(saveButton)) {
          Campaign campaign = new Campaign(campaignNameTextBox.getText(), descriptionTextArea.getText());
          sendToServer(campaign);
          loadSavedCampaignPanel(user);        
        }
      }
      
      private void sendToServer(Campaign campaign) {
        CreateCampaignServiceAsync createCampaignServiceAsync = GWT.create(CreateCampaignService.class);
        AsyncCallback<String> asyncCallback = new AsyncCallback<String>() {
          
          public void onSuccess(String result) {
            if (result.equals("SUCCESS")) {         
                History.newItem("welcome");
            } else {
              System.out.println("Campaign creation failed!");
              History.newItem("welcome");
            }            
          }
          
          public void onFailure(Throwable caught) {
            System.out.println(caught);
          }
        };
        createCampaignServiceAsync.createCampaign(campaign, asyncCallback);
      }
    }

    // Style Components
    saveButton.setStylePrimaryName("saveButton");
    
    campaignNameTextBox.setSize("180px", "20px");
    
    descriptionTextArea.setSize("180px", "100px");
    //descriptionTextArea.setMaxLength(MAX_DESCRIPTION_SIZE);

    selectGrid.setCellSpacing(CELL_SPACING);
    // geht?
    selectGrid.getCellFormatter().setAlignment(1, 1, HasHorizontalAlignment.ALIGN_LEFT, HasVerticalAlignment.ALIGN_TOP);
    selectGrid.setBorderWidth(0);
    selectGrid.setStylePrimaryName("selectGrid");

    selectGrid.setWidget(0, 0, campaignNameLabel);
    selectGrid.setWidget(1, 0, descriptionLabel);
    
    selectGrid.setWidget(0, 1, campaignNameTextBox);
    selectGrid.setWidget(1, 1, descriptionTextArea);

    HTML headline = new HTML("<h1>Kampagne anlegen</h1>");

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

  public void loadSavedCampaignPanel(User userIn) {
    RootPanel.get("information").clear();
    RootPanel.get("content").clear();
    panel.clear();
    HTML savedText = new HTML("<h1>Kampagne anlegen</h1><p>Deine Kampagne wurde erfolgreich angelegt</p>");
    panel.add(savedText);
    RootPanel.get("content").add(panel);
  }
  
  public static CreateCampaignPanel getCampaignPanel(TimadorusWebApp entry, User user) {
    return new CreateCampaignPanel(entry, user);
  }

  private static final HTML getInformation() {
    HTML information = new HTML("<h1>Kampagne anlegen</h1><p>Hier kannst du eine neue Kampagne anlegen. "
                                + "Wähle einen Namen für deine Kampagne und gebe eine Kurzbeschreibung an.</p>");
    return information;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}
