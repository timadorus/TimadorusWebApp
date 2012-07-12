package org.timadorus.webapp.client.campaign;

import org.timadorus.webapp.beans.Campaign;
import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.eventhandling.events.CreateCampaineEvent;
import org.timadorus.webapp.client.eventhandling.handler.ShowDialogHandler;
import org.timadorus.webapp.client.service.Service;
import org.timadorus.webapp.client.service.ServiceAsync;
import org.timadorus.webapp.client.service.ServiceType;
import org.timadorus.webapp.shared.Action;
import org.timadorus.webapp.shared.Response;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
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

/**
 * 
 * @author sage
 *
 */
public class CreateCampaignPanel extends FormPanel implements ShowDialogHandler {

  private static final int CELL_SPACING = 8;

  DefaultTimadorusWebApp entry;

  User user;

  Button saveButton = new Button("Speichern");

  VerticalPanel panel = new VerticalPanel();

  FlexTable selectGrid = new FlexTable();

  Label campaignNameLabel = new Label("Name der Kampagne");

  Label descriptionLabel = new Label("Beschreibung");

  Label checkCampaignNameLabel = new Label("");

  TextBox campaignNameTextBox = new TextBox();

  TextArea descriptionTextArea = new TextArea();
  
  private final ServiceAsync<Campaign, String> crtService = GWT.create(Service.class);
  
  private final ServiceAsync<String, String> exsService = GWT.create(Service.class);
  

  /**
   * 
   * @param entryIn application
   * @param user user of the new campaign
   */
  public CreateCampaignPanel(DefaultTimadorusWebApp entryIn, final User user) {
    super();
    this.entry = entryIn;
    this.user = user;

    entry.addHandler(CreateCampaineEvent.SHOWDIALOG, this);

    /** 
     * Create a key-up handler for the nameField.
     */
    class MyKeyUpHandler implements KeyUpHandler {

      @Override
      public void onKeyUp(KeyUpEvent event) {
        checkCampaign(campaignNameTextBox.getText());
      }
      
      /**
       * 
       * @param campaignName potential name of the campaign
       */
      private void checkCampaign(String campaignName) {
       
        Action<String> action = new Action<String>(ServiceType.EXSCAMPAIGN, campaignName);
        AsyncCallback<Response<String>> response = new AsyncCallback<Response<String>>() {

          @Override
          public void onFailure(Throwable caught) {
            System.out.println(caught);
          }

          @Override
          public void onSuccess(Response<String> result) {
            if (result.getResult().equals("SUCCESS")) {
              checkCampaignNameLabel.setText("");
            } else {
              checkCampaignNameLabel.setStyleName("error");
              checkCampaignNameLabel.setText("Ist bereits vergeben");
            }
          }
        };
        
        exsService.execute(action, response);
        /*
        CreateCampaignServiceAsync createCampaignServiceAsync = GWT.create(CreateCampaignService.class);
        AsyncCallback<String> asyncCallback = new AsyncCallback<String>() {

          public void onSuccess(String result) {
            if (result.equals("SUCCESS")) {
              checkCampaignNameLabel.setText("");
            } else {
              checkCampaignNameLabel.setStyleName("error");
              checkCampaignNameLabel.setText("Ist bereits vergeben");
            }
          }

          public void onFailure(Throwable caught) {
            System.out.println(caught);
          }
        };
        createCampaignServiceAsync.existsCampaign(campaignName, asyncCallback);
        */
      }
    }

    /**
     * Create a handler for the saveButton and nameField.
     */
    class MyHandler implements ClickHandler {
      
      /**
       * @param event event to catch 
       */
      public void onClick(ClickEvent event) {

        if (event.getSource().equals(saveButton)) {
          onSaveButtonClick();
        }
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
    MyKeyUpHandler keyUpHandler = new MyKeyUpHandler();

    saveButton.addClickHandler(handler);
    campaignNameTextBox.addKeyUpHandler(keyUpHandler);
  }

  /**
   * 
   */
  public void onSaveButtonClick() {
    // TODO User auf null checken
    Campaign campaign = new Campaign();
    campaign.setName(campaignNameTextBox.getText());
    campaign.setBeschreibung(descriptionTextArea.getText());
    campaign.setUsername(user.getUsername());
    sendToServer(campaign);
    loadSavedCampaignPanel(user);
  }

  /**
   * 
   * @param campaign the campaign to send
   */
  private void sendToServer(Campaign campaign) {
  
    Action<Campaign> action = new Action<Campaign>(ServiceType.CRTCAMPAIGN, campaign);
    AsyncCallback<Response<String>> response = new AsyncCallback<Response<String>>() {

      @Override
      public void onFailure(Throwable caught) {
        System.out.println(caught);
      }

      @Override
      public void onSuccess(Response<String> result) {
        if (result.getResult().equals("SUCCESS")) {
          System.out.println("Sucess");
        } else {
          System.out.println("Failure");
        }
      }
    };
    
    crtService.execute(action, response);
    
    /* CreateCampaignServiceAsync createCampaignServiceAsync = GWT.create(CreateCampaignService.class);
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
    createCampaignServiceAsync.createCampaign(campaign, asyncCallback);
    */
  }

  /**
   * 
   * @param userIn user to load campaigns for
   */
  public void loadSavedCampaignPanel(User userIn) {
    RootPanel.get("information").clear();
    RootPanel.get("content").clear();
    panel.clear();
    HTML savedText = new HTML("<h1>Kampagne anlegen</h1><p>Deine Kampagne wurde erfolgreich angelegt</p>");
    panel.add(savedText);
    RootPanel.get("content").add(panel);
  }

  /**
   * 
   * @param entry application
   * @param user user to get the panel for
   * @return a panel to show the campaigns
   */
  public static CreateCampaignPanel getCampaignPanel(DefaultTimadorusWebApp entry, User user) {
    return new CreateCampaignPanel(entry, user);
  }

  /**
   * 
   * @return the information string to show for user help
   */
  private static final HTML getInformation() {
    HTML information = new HTML("<h1>Kampagne anlegen</h1><p>Hier kannst du eine neue Kampagne anlegen. "
        + "Wähle einen Namen für deine Kampagne und gebe eine Kurzbeschreibung an.</p>");
    return information;
  }

  /**
   * 
   * @return the entry
   */
  public DefaultTimadorusWebApp getEntry() {
    return entry;
  }

  @Override
  public void show(DefaultTimadorusWebApp entry, Character character, User user) {
    this.user = user;
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Kampagne erstellen"));
    RootPanel.get("content").add(this);
  }
}
