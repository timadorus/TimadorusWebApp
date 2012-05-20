package org.timadorus.webapp.client.campaign;

import java.util.List;

import org.timadorus.webapp.beans.Campaign;
import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.eventhandling.events.ShowCreateFractionEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowEditCampaignEvent;
import org.timadorus.webapp.client.eventhandling.handler.ShowDialogHandler;
import org.timadorus.webapp.client.service.Service;
import org.timadorus.webapp.client.service.ServiceAsync;
import org.timadorus.webapp.client.service.ServiceType;
import org.timadorus.webapp.shared.Action;
import org.timadorus.webapp.shared.Response;

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

public class EditCampaignPanel extends FormPanel implements ShowDialogHandler {

  DefaultTimadorusWebApp entry;

  User user;

  VerticalPanel panel = new VerticalPanel();

  private Grid grid = new Grid(1, 1);

  HTML headline = new HTML("<h1>Kampagne verwalten</h1>");

  Label campaignNameLabel = new Label("Name der Kampagne");

  Label descriptionLabel = new Label("Beschreibung");
  
  private final ServiceAsync<User, List<Campaign>> getService = GWT.create(Service.class);

  public EditCampaignPanel(DefaultTimadorusWebApp entryIn, final User user) {
    super();
    this.entry = entryIn;
    this.user = user;

    entry.addHandler(ShowEditCampaignEvent.SHOWDIALOG, this);

    if (user != null) {
      getCampaigns(user);
    }
    // Style Components
    grid.setWidget(0, 0, new Label("Waiting for ajax response..."));
    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    panel.setWidth("100%");

    panel.add(headline);
    panel.add(grid);

  }

  private void getCampaigns(User user) {
    
    
    Action<User> action = new Action<User>(ServiceType.GETCAMPAIGN, user);
    AsyncCallback<Response<List<Campaign>>> response = new AsyncCallback<Response<List<Campaign>>>() {

      @Override
      public void onFailure(Throwable caught) {
        System.out.println(caught);
      }

      @Override
      public void onSuccess(Response<List<Campaign>> result) {
        if (result.getResult() != null) {
          updateCampaignList(result.getResult());
        }
      }
    };
    
    getService.execute(action, response);
    
    
    /*
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

    createCampaignServiceAsync.getCampaigns(user.getUsername(), asyncCallback); */
    
  }

  /**
   * Updates the campaign list.
   * 
   * @param result
   *          The campaign of the user
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
              getEntry().fireEvent(new ShowCreateFractionEvent(user, campaign));
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
    getEntry().fireEvent(new ShowEditCampaignEvent(user));
  }

  public static EditCampaignPanel getCampaignPanel(DefaultTimadorusWebApp entry, User user) {
    return new EditCampaignPanel(entry, user);
  }

  private static final HTML getInformation() {
    HTML information = new HTML("<h1>Kampagne verwalten</h1><p>Hier kannst du deine Kampagne verwalten. "
        + "Wähle eine Kampagne um eine Fraktion dafür anzulegen.</p>");
    return information;
  }

  public DefaultTimadorusWebApp getEntry() {
    return entry;
  }

  @Override
  public void show(DefaultTimadorusWebApp entry, Character character, User user) {
    this.user = user;
    getCampaigns(user);
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Kampagne verwalten"));
    RootPanel.get("content").add(this);
    RootPanel.get("information").clear();
    RootPanel.get("information").add(getInformation());
  }
}
