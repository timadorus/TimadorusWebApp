package org.timadorus.webapp.client.campaign;

import java.util.List;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.rpc.service.CreateCampaignService;
import org.timadorus.webapp.client.rpc.service.CreateCampaignServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EditCampaignPanel extends FormPanel {

  TimadorusWebApp entry;
  
  User user;

  VerticalPanel panel       = new VerticalPanel();
  private Grid grid       = new Grid(1, 1);
  HTML headline = new HTML("<h1>Kampagne verwalten</h1>");
  Label campaignNameLabel         = new Label("Name der Kampagne");
  Label descriptionLabel        = new Label("Beschreibung");

  public EditCampaignPanel(TimadorusWebApp entryIn, final User user) {
    super();
    this.entry = entryIn;
    this.user = user;
    
    CreateCampaignServiceAsync createCampaignServiceAsync = GWT.create(CreateCampaignService.class);
    AsyncCallback<List<Campaign>> asyncCallback = new AsyncCallback<List<Campaign>>() {
      
      public void onSuccess(List<Campaign> result) {
        if (result != null) {
          updateCampaignList(result);
        }
      }
      
      public void onFailure(Throwable caught) {
        System.out.println(caught);
      }
    };
    
    createCampaignServiceAsync.getCampaigns(user.getUsername(), asyncCallback);
 
    // Style Components   
    grid.setWidget(0, 0, new Label("Waiting for ajax response..."));
    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    panel.setWidth("100%");

    panel.add(headline);
    panel.add(grid);
    
    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);

    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());
    
  }


  /**
   * Updates the campaign list.
   * @param result The campaign of the user
   */
  private void updateCampaignList(List<Campaign> result) {
    if (result.size() > 0) {
      final int columns = 4;
      grid = new Grid(result.size(), columns);
      grid.setBorderWidth(1);
    
      int i = 0;
      for (final Campaign campaign : result) {
        final Button edit = new Button("Bearbeiten");
        
        class MyHandler implements ClickHandler {
          public void onClick(ClickEvent event) {
            if (event.getSource().equals(edit)) {
              RootPanel.get("content").add(CreateFractionPanel.getCreateFractionPanel(entry, user, campaign));
            }    
          }      
        }
        
        MyHandler handler = new MyHandler();
        edit.addClickHandler(handler);
        
        grid.setWidget(i, 0, new HTML(campaign.getCampaignID().toString()));
        grid.setWidget(i, 1, new HTML(campaign.getName()));
        grid.setWidget(i, 2, new HTML(campaign.getBeschreibung()));
        grid.setWidget(i, 2 + 1, edit);
        i++;
      }
    } else {
      grid = new Grid(1, 1);
      grid.setBorderWidth(0);
      grid.setWidget(0, 0, new Label("Es wurden keine Kampagnen gefunden."));
    }
    panel.clear();
    panel.add(headline);
    panel.add(grid);
    RootPanel.get("content").clear();
    RootPanel.get("content").add(panel);
  }
  
  
  public static EditCampaignPanel getCampaignPanel(TimadorusWebApp entry, User user) {
    return new EditCampaignPanel(entry, user);
  }

  private static final HTML getInformation() {
    HTML information = new HTML("<h1>Kampagne verwalten</h1><p>Hier kannst du deine Kampagne verwalten. "
                                + "Wähle eine Kampagne um eine Fraktion dafür anzulegen.</p>");
    return information;
  }

  public TimadorusWebApp getEntry() {
    return entry;
  }
}
