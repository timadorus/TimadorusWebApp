package org.timadorus.webapp.client.profile;

import org.timadorus.webapp.beans.Character;
import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.DefaultTimadorusWebApp;
import org.timadorus.webapp.client.HistoryStates;
import org.timadorus.webapp.client.eventhandling.events.ShowProfileEvent;
import org.timadorus.webapp.client.eventhandling.handler.ShowDialogHandler;
import org.timadorus.webapp.client.rpc.service.UserService;
import org.timadorus.webapp.client.rpc.service.UserServiceAsync;

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
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * A profile panel. Supplies the options to change the user data or to delete the user.
 */
@SuppressWarnings("deprecation")
public class ProfilePanel extends FormPanel implements HistoryListener, ShowDialogHandler {
  
  private DefaultTimadorusWebApp entry;
  
  private User user;
  

  private final int profileRows     = 9;
  private final int profileColumns  = 3;
  private final int deleteRows      = 9;
  private final int deleteColumns   = 3;

  VerticalPanel panel   = new VerticalPanel();
  Grid profileGrid      = new Grid(profileRows, profileColumns); 
  Grid deleteGrid       = new Grid(deleteRows, deleteColumns);
  
  // Profile section elements
  HTML profileHL  = new HTML("<h1>Profil</h1>");
  Button submit   = new Button("Ändern");
  
  private TextBox vornameTextBox                = new TextBox();
  private TextBox nachnameTextBox               = new TextBox();
  private TextBox geburtstagTextBox             = new TextBox();
  private TextBox emailTextBox                  = new TextBox();
  private TextBox emailRepeatTextBox            = new TextBox();
  private TextBox usernameTextBox               = new TextBox();
  private PasswordTextBox passwordTextBox       = new PasswordTextBox();
  private PasswordTextBox passwordRepeatTextBox = new PasswordTextBox();
  private HTML vornameHTML                      = new HTML();
  private HTML nachnameHTML                     = new HTML();
  private HTML geburtstagHTML                   = new HTML();
  private HTML emailHTML                        = new HTML();
  private HTML emailRepeatHTML                  = new HTML();
  private HTML usernameHTML                     = new HTML();
  private HTML passwordHTML                     = new HTML();
  private HTML passwordRepeatHTML               = new HTML();
  
  // Delete section elements
  HTML deleteHL = new HTML("<h1>Profil löschen</h1>");
  
  Button delete     = new Button("Account löschen");
  Button confirm    = new Button("Löschung bestätigen");
  
  Button back       = new Button("Zurück");
  
  PasswordTextBox passBox = new PasswordTextBox();
  
  /**
   * Adds this instance to the history listener.
   */
  private void setupHistory() {
    History.addHistoryListener(this);
  }

  /**
   * Build profile layout panel. This panel contains the ability to change the users data and to delete the account.
   * 
   * @param timadorusWebApp The webapp object
   * @param user The current user
   */
  public ProfilePanel(DefaultTimadorusWebApp timadorusWebApp, final User user) {
    super();
    
    this.entry = timadorusWebApp;
    this.user  = user;
    
    entry.addHandler(ShowProfileEvent.SHOWDIALOG, this);
    
    setupHistory();
    
//    getProfileDataFromServer();
    
    Grid grid = new Grid(1, 1);
    grid.setWidget(0, 0, new Label("Waiting for ajax response..."));
    
    panel.setStyleName("panel");
    panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
    panel.setWidth("100%");

    panel.add(profileHL);
    panel.add(grid);
    
    setContent(panel);

    // No informations for this page (up to now)
    RootPanel.get("information").clear();
  }
  
  /**
   * Get full user object from the server.
   */
  private void getProfileDataFromServer() {
    
    
    //TODO Command-Pattern
    
    UserServiceAsync userServiceAsync = GWT.create(UserService.class);

    AsyncCallback<User> asyncCallback = new AsyncCallback<User>() {
      public void onSuccess(User result) {
        if (result != null) {
          updateProfilePanel(result);
        }
      }

      public void onFailure(Throwable caught) {
        getTimadorus().showDialogBox("Fehlermeldung", "Fehler bei der Abfrage der Profildaten");
        System.out.println(caught);
      }
    };
    
    userServiceAsync.getUser(user, asyncCallback);
  }
  
  /**
   * Generating the actual profile panel after ajax response has arrived.
   * 
   * @param result The full user object containing the information for the profile grid
   */
  private void updateProfilePanel(User result) {
    this.user = result;
    
    // Build grids
    buildProfileGrid();
    buildDeleteGrid();
    
    // Add grids to main grid
    panel.clear();
    
    panel.add(profileHL);
    panel.add(profileGrid);
    panel.add(deleteHL);
    panel.add(deleteGrid);
    
    // Add main grid to window
    setContent(panel);
    setStyleName("formPanel");
  }
  
  /**
   * Builds a grid for the users profile data. Also adds event handler to fields and the submit button.
   * The users profile data can be modified by changing the content of the input fields and pressing
   * either the "Enter" button or hitting the submit button with the left mouse button 
   */
  private void buildProfileGrid() {
    // Initialize profile section
    // CHECKSTYLE OFF
    profileGrid.setWidget(0, 0, new Label("Vorname"));
    profileGrid.setWidget(1, 0, new Label("Nachname"));
    profileGrid.setWidget(2, 0, new Label("Geburtstag (dd.mm.jjjj)"));
    profileGrid.setWidget(3, 0, new Label("Email"));
    profileGrid.setWidget(4, 0, new Label("Email (Wiederholung)"));
    profileGrid.setWidget(5, 0, new Label("Benutzername"));
    profileGrid.setWidget(6, 0, new Label("Passwort"));
    profileGrid.setWidget(7, 0, new Label("Passwort (Wiederholung)"));

    profileGrid.setWidget(0, 1, vornameTextBox);
    profileGrid.setWidget(1, 1, nachnameTextBox);
    profileGrid.setWidget(2, 1, geburtstagTextBox);
    profileGrid.setWidget(3, 1, emailTextBox);
    profileGrid.setWidget(4, 1, emailRepeatTextBox);
    profileGrid.setWidget(5, 1, usernameTextBox);
    profileGrid.setWidget(6, 1, passwordTextBox);
    profileGrid.setWidget(7, 1, passwordRepeatTextBox);

    profileGrid.setWidget(0, 2, vornameHTML);
    profileGrid.setWidget(1, 2, nachnameHTML);
    profileGrid.setWidget(2, 2, geburtstagHTML);
    profileGrid.setWidget(3, 2, emailHTML);
    profileGrid.setWidget(4, 2, emailRepeatHTML);
    profileGrid.setWidget(5, 2, usernameHTML);
    profileGrid.setWidget(6, 2, passwordHTML);
    profileGrid.setWidget(7, 2, passwordRepeatHTML);

    profileGrid.setWidget(8, 1, submit);
    // CHECKSTYLE ON

    // Pre-set fields to actual users values
    vornameTextBox.setText(this.user.getVorname());
    nachnameTextBox.setText(this.user.getNachname());
    geburtstagTextBox.setText(this.user.getGeburtstag());
    emailTextBox.setText(this.user.getEmail());
    emailRepeatTextBox.setText(this.user.getEmail());
    usernameTextBox.setText(this.user.getUsername());
    passwordTextBox.setText(this.user.getPassword());
    passwordRepeatTextBox.setText(this.user.getPassword());
    
    /**
     * Create a handler for the sendButton and nameField.
     */
    class MyHandler implements ClickHandler, KeyUpHandler {
      /**
       * Will be triggered if the the submit button has been clicked.
       * 
       * @param event The ClickEvent object
       */
      public void onClick(ClickEvent event) {
        handleEvent();
      }

      /**
       * Checks whether the "Enter" button was pressed or not.
       * 
       * @param event The KeyUpEvent object
       */
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          handleEvent();
        }
      }

      /**
       * Main method to check and send the form data
       */
      private void handleEvent() {
        submit.setEnabled(false);
        
        setText(vornameHTML, "");
        setText(nachnameHTML, "");
        setText(geburtstagHTML, "");
        setText(emailHTML, "");
        setText(emailRepeatHTML, "");
        setText(usernameHTML, "");
        setText(passwordHTML, "");
        setText(passwordRepeatHTML, "");
        User register = new User(vornameTextBox.getText(), nachnameTextBox.getText(), geburtstagTextBox.getText(),
                                 emailTextBox.getText(), usernameTextBox.getText(), passwordTextBox.getText());

        if (!register.isValid()) {
          if (passwordRepeatTextBox.getText().length() == 0) {
            registerInvalid(User.PASSWORDREPEAT_EMPTY);
            passwordRepeatTextBox.setFocus(true);
          }
          if (register.getPassword().length() == 0) {
            registerInvalid(User.PASSWORD_EMPTY);
            passwordTextBox.setFocus(true);
          }
          if (register.getUsername().length() == 0) {
            registerInvalid(User.USERNAME_EMPTY);
            usernameTextBox.setFocus(true);
          }
          if (emailRepeatTextBox.getText().length() == 0) {
            registerInvalid(User.EMAILREPEAT_EMPTY);
            emailRepeatTextBox.setFocus(true);
          }
          if (register.getEmail().length() == 0) {
            registerInvalid(User.EMAIL_EMPTY);
            emailTextBox.setFocus(true);
          }
          if (register.getGeburtstag().length() == 0) {
            registerInvalid(User.GEBURTSTAG_EMPTY);
            geburtstagTextBox.setFocus(true);
          }
          if (register.getVorname().length() == 0 && register.getNachname().length() == 0) {
            registerInvalid(User.VORNAME_NACHNAME_EMPTY);
            vornameTextBox.setFocus(true);
          }
          History.newItem("profile");

        } else if (!emailTextBox.getText().equals(emailRepeatTextBox.getText())) {
          registerInvalid(User.EMAIL_FAULT);
        } else if (!passwordTextBox.getText().equals(passwordRepeatTextBox.getText())) {
          registerInvalid(User.PASSWORD_FAULT);
        } else {

          sendToServer(register);
        }
        submit.setEnabled(true);
      }

      /**
       * Send new user data to server for update
       * 
       * @param updatedUser The new user data as User object
       */
      private void sendToServer(User updatedUser) {
        UserServiceAsync userServiceAsync = GWT.create(UserService.class);
        
        AsyncCallback<Integer> asyncCallback = new AsyncCallback<Integer>() {
          public void onSuccess(Integer result) {
            if (result != null) {
              if (result == User.OK) {
                RootPanel.get("content").clear();
                getTimadorus().showDialogBox("Erfolg", "Ihre Daten wurden erfolgreich geändert");
                ProfilePanel.getProfilePanel(getTimadorus(), user);
              } else {
                profileHandleErrors(result);
                submit.setEnabled(true);
              }
            } else {
              submit.setEnabled(true);
            }
          }
          public void onFailure(Throwable caught) {
            registerInvalid(0);
            System.out.println(caught);
          }
        };
        
        userServiceAsync.update(user.getId(), updatedUser, asyncCallback);
      }
    }

    MyHandler handler = new MyHandler();
    submit.addClickHandler(handler);
    vornameTextBox.addKeyUpHandler(handler);
    nachnameTextBox.addKeyUpHandler(handler);
    geburtstagTextBox.addKeyUpHandler(handler);
    emailTextBox.addKeyUpHandler(handler);
    emailRepeatTextBox.addKeyUpHandler(handler);
    usernameTextBox.addKeyUpHandler(handler);
    passwordTextBox.addKeyUpHandler(handler);
    passwordRepeatTextBox.addKeyUpHandler(handler);
  }
  
  /**
   * Handles errors which occurs while updating the profile data.
   * 
   * @param result An integer containing all errors that have been occure
   */
  private void profileHandleErrors(int result) {
    if (result >= User.PASSWORD_FAULT) {
      result -= User.PASSWORD_FAULT;
    }
    if (result >= User.USERNAME_FAULT) {
      result -= User.USERNAME_FAULT; 
      registerInvalid(User.USERNAME_FAULT);
    }
    if (result >= User.EMAIL_FORMAT) {
      result -= User.EMAIL_FORMAT;
      registerInvalid(User.EMAIL_FORMAT);
    }
    if (result >= User.GEBURTSTAG_AGE) {
      result -= User.GEBURTSTAG_AGE;
      registerInvalid(User.GEBURTSTAG_AGE);
    }
    if (result >= User.GEBURTSTAG_FORMAT) {
      result -= User.GEBURTSTAG_FORMAT;
      registerInvalid(User.GEBURTSTAG_FORMAT);
    }
    if (result >= User.GEBURTSTAG_FAULT) {
      result -= User.GEBURTSTAG_FAULT;
      registerInvalid(User.GEBURTSTAG_FAULT);
    }
  }

  /**
   * Creates an error HTML label.
   * 
   * @param label The label which shall be modified 
   * @param message The message which shall be displayed
   */
  private void setText(HTML label, String message) {
    label.setHTML("<span class=\"error\">" + message + "</span>");
  }

  /**
   * Within this method, errors while updating the own profile data will be handled.
   * 
   * @param error Error code
   */
  public void registerInvalid(int error) {
    switch (error) {
    case User.VORNAME_NACHNAME_EMPTY:
      setText(vornameHTML, "Bitte ausfüllen!");
      setText(nachnameHTML, "Bitte ausfüllen!");
      break;
    case User.GEBURTSTAG_EMPTY:
      setText(geburtstagHTML, "Bitte ausfüllen!");
      break;
    case User.GEBURTSTAG_AGE:
      setText(geburtstagHTML, "Du bist leider zu jung!");
      break;
    case User.GEBURTSTAG_FORMAT:
      setText(geburtstagHTML, "Das Format ist ungültig!");
      break;
    case User.GEBURTSTAG_FAULT:
      setText(geburtstagHTML, "Das Datum ist ungültig");
      break;
    case User.EMAIL_EMPTY:
      setText(emailHTML, "Bitte ausfüllen!");
      break;
    case User.EMAILREPEAT_EMPTY:
      setText(emailRepeatHTML, "Bitte ausfüllen!");
      break;
    case User.EMAIL_FAULT:
      setText(emailHTML, "Stimmen nicht überein!");
      setText(emailRepeatHTML, "Stimmen nicht überein!");
      break;
    case User.EMAIL_FORMAT:
      setText(emailHTML, "Das Format ist ungültig!");
      setText(emailRepeatHTML, "Das Format ist ungültig!");
      break;
    case User.USERNAME_EMPTY:
      setText(usernameHTML, "Bitte ausfüllen!");
      break;
    case User.USERNAME_FAULT:
      setText(usernameHTML, "Benutzername bereits vergeben!");
      break;
    case User.PASSWORD_EMPTY:
      setText(passwordHTML, "Bitte ausfüllen!");
      break;
    case User.PASSWORDREPEAT_EMPTY:
      setText(passwordRepeatHTML, "Bitte ausfüllen!");
      break;
    case User.PASSWORD_FAULT:
      setText(passwordHTML, "Stimmen nicht überein!");
      setText(passwordRepeatHTML, "Stimmen nicht überein!");
      break;
    default:
      break;
    }
  }

  /**
   * Builds the grid for the area, which provides the deletion of the current user.
   */
  private void buildDeleteGrid() {
 // Initialize delete section
    deleteGrid.setWidget(0, 0, delete);
    

    back.addClickHandler(new ClickHandler() {
      
      @Override
      public void onClick(ClickEvent event) {
        History.newItem(HistoryStates.WELCOME_STATE.getStringRepresentation());
      }
    });
    
    /**
     * The handler class for the delete action.
     */
    class DeleteHandler implements ClickHandler, KeyUpHandler {
      /**
       * Will be triggered if the button was clicked.
       * 
       * @param event The ClickEvent object
       */
      public void onClick(ClickEvent event) {
        System.out.println("Account löschen Button geklickt");
        handleEvent();
      }

      /**
       * Will be triggered if the "Enter" button was hit while located in an input field.
       * 
       * @param event The KeyUpEvent object
       */
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          handleEvent();
        }
      }

      /**
       * Handles the event by asking if the user is really sure + adding a password and a username input field for
       * verification.
       */
      private void handleEvent() {
        deleteGrid.remove(delete);
        deleteGrid.setWidget(0, 0, new Label("Sind Sie sich sicher? Geben Sie Ihr Passwort zur Bestätigung ein."));
        deleteGrid.setWidget(1, 0, passBox);
        deleteGrid.setWidget(2, 0, confirm);
        final int row = 3;
        deleteGrid.setWidget(row, 0, back);
      }      
    }
    
    /**
     * The handler class for the confirm action.
     */
    class ConfirmHandler implements ClickHandler, KeyUpHandler {
      /**
       * Will be triggered if the button was clicked.
       * 
       * @param event The ClickEvent object
       */
      public void onClick(ClickEvent event) {
        handleEvent();
      }

      /**
       * Will be triggered if the "Enter" button was hit while located in an input field.
       * 
       * @param event The KeyUpEvent object
       */
      public void onKeyUp(KeyUpEvent event) {
        if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
          handleEvent();
        }
      }

      /**
       * Handles the event. Verifies the supplied username and password and deletes the user if they were valid.
       */
      private void handleEvent() {
        if (passBox.getText().equals(user.getPassword())) {
          System.out.println("Deleting " + user.getDisplayname());
          deleteAccount();
          getTimadorus().showDialogBox("Information", "Ihr Account wurde erfolgreich entfernt!");
        } else {
          passBox.setText("");
          getTimadorus().showDialogBox("Fehlermeldung", "Passwort falsch! Versuchen Sie es erneut!");
        }
      }

      /**
       * Sends the user object to the server, requesting a deletion of this user.
       */
      private void deleteAccount() {
        UserServiceAsync userServiceAsync = GWT.create(UserService.class);
        AsyncCallback<String> asyncCallback = new AsyncCallback<String>() {
          
          public void onSuccess(String result) {
            if (result != null) {
              if (result.equals(User.USER_INVALID)) {
                System.out.println("Unsuccessfully deleted");                
              }
              if (result.equals(String.valueOf(User.OK))) {
                System.out.println("Successfully deleted");
              }
              History.newItem("welcome");
              History.newItem("logout");
            }
          }
          
          public void onFailure(Throwable caught) {
            System.out.println(caught);
          }
        };
        
        userServiceAsync.delete(user, asyncCallback);
      }
    }
    
    DeleteHandler delHandler = new DeleteHandler();
    delete.addClickHandler(delHandler);
    ConfirmHandler conHandler = new ConfirmHandler();
    confirm.addClickHandler(conHandler);
    passBox.addKeyUpHandler(conHandler);
  }
  
  /**
   * Creates and returns a new profile panel.
   * 
   * @param entry The timadorus web app object
   * @param user The user object
   * @return The ProfilePanel object
   */
  public static ProfilePanel getProfilePanel(DefaultTimadorusWebApp entry, User user) {
    return new ProfilePanel(entry, user);
  }

  /**
   * Clear and add new content to the page content DIV.
   * 
   * @param w New widget
   */
  private void setContent(Widget w) {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(w);
  }
  
  /**
   * Returns the TimadorusWebApp object.
   * 
   * @return The TimadorusWebApp object
   */
  public DefaultTimadorusWebApp getTimadorus() {
    return entry;
  }

  /**
   * An unimplemented method.
   * 
   * @param arg0 The history token
   */
  @Override
  public void onHistoryChanged(String arg0) { }

  @Override
  public void show(DefaultTimadorusWebApp entry, Character character, User user) {
    this.user = user;
    
    getProfileDataFromServer();
    
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Profil bearbeiten"));
    RootPanel.get("content").add(this);    
    
  }
}
