package org.timadorus.webapp.client.profile;

import org.timadorus.webapp.client.TimadorusWebApp;
import org.timadorus.webapp.client.User;
import org.timadorus.webapp.client.rpc.service.DeleteUserService;
import org.timadorus.webapp.client.rpc.service.DeleteUserServiceAsync;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Widget;

@SuppressWarnings("deprecation")
public class ProfilePanel extends FormPanel implements HistoryListener {
  
  private static ProfilePanel profilePanel;
  
  private TimadorusWebApp entry;
  
  private User user;
  
  private final int rows = 9;
  
  private final int columns = 3;

  Grid grid = new Grid(rows, columns);
  
  Button delete = new Button("Account löschen");
  
  private void setupHistory() {
    History.addHistoryListener(this);
  }

  public ProfilePanel(TimadorusWebApp timadorusWebApp, final User user) {
    super();
    this.entry = timadorusWebApp;
    this.user = user;
    setupHistory();
    grid.setWidget(0, 0, delete);    
    
    class MyHandler implements ClickHandler, KeyUpHandler {
      /**
       * Wird ausgelöst, wenn Button gedrückt wurde
       */
      public void onClick(ClickEvent event) {
        System.out.println("Löschen Button geklickt");
        handleEvent();
      }

      /**
       * Prüft ob "Enter" gedrückt wurde
       */
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          handleEvent();
        }
      }

      private void handleEvent() {
        System.out.println("handle Event");
        deleteAccount();
        History.newItem("welcome");
      }

      private void deleteAccount() {
        DeleteUserServiceAsync deleteAccountServiceAsync = GWT.create(DeleteUserService.class);
        AsyncCallback<String> asyncCallback = new AsyncCallback<String>() {
          
          public void onSuccess(String result) {
            if (result != null) {         
                History.newItem("welcome");
            }
          }
          
          public void onFailure(Throwable caught) {
            System.out.println(caught);
          }
        };
        deleteAccountServiceAsync.delete(user, asyncCallback);
      }
    }
    
    MyHandler handler = new MyHandler();
    delete.addClickHandler(handler);
    
    setWidget(grid);
    setStyleName("formPanel");
  }

  public static Widget getProfilePanel(TimadorusWebApp entry, User user) {
    if (profilePanel == null) {
      profilePanel = new ProfilePanel(entry, user);
    }
    return profilePanel;
  }
  
  public TimadorusWebApp getEntry() {
    return entry;
  }

  @Override
  public void onHistoryChanged(String arg0) {
    // TODO Auto-generated method stub
    
  }
  
  

}
