package org.timadorus.webapp.client;

import org.timadorus.webapp.client.rpc.service.SessionService;
import org.timadorus.webapp.client.rpc.service.SessionServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

@SuppressWarnings("deprecation")
public class TimadorusWebApp implements EntryPoint, HistoryListener {

  private static final long serialVersionUID = -5138823406762920058L;

  // Status für nachste Seite
  public static final String LOGIN_STATE = "login";

  public static final String WELCOME_STATE = "welcome";

  public static final String CREATE_STATE = "create";

  public static final String REGISTER_STATE = "register";

  // SessionID
  private SessionId sessionId = new SessionId();

  // Hyperlinks für die Startseite
  private Hyperlink logoutlink;

  private Hyperlink createCharacterlink;

  private Hyperlink registerlink;

  private LoginPanel loginPanel;

  private CharakterPanel cpanel;

  private RegisterPanel registerPanel;

  private boolean loggedin = false;

  public void onModuleLoad() {
   
    VerticalPanel vp = new VerticalPanel();
    RootPanel.get("menu").add(vp);
    
    VerticalPanel vp1 = new VerticalPanel();
    RootPanel.get("content").add(vp1);
    
    VerticalPanel vp2 = new VerticalPanel();
    RootPanel.get("information").add(vp2);

    sessionId.setSessionId(Cookies.getCookie("session"));
    History.onHistoryChanged("welcome");
    System.out.println("Session " + sessionId.getSessionId());
    validateSession();
    setupHistory();
  }

  public void loadWelcomePanel() {
    RootPanel.get("menu").clear();
    RootPanel.get("content").clear();
    RootPanel.get("information").clear();
    
    RootPanel.get("information").add(new Label("information panel"));
    RootPanel.get("content").add(new Label("Willkommen auf der WebApplikation des Projektes Timadoros"));

    if (isLoggedin()) {
      System.out.println("Login status " + isLoggedin());
      RootPanel.get("menu").add(new Label("Du bist als " + getLoginPanel().getUser().getDisplayname() + " angemeldet"));
      if (!getLoginPanel().getUser().getActive()) {
        RootPanel.get("menu").add(new Label("Dein Account wurde noch nicht aktiviert"));
      }
      RootPanel.get("menu").add(getLogoutlink());
      RootPanel.get("menu").add(getCreateCharacterlink());
      RootPanel.get("menu").add(getRegisterlink());
    } else {
      System.out.println("Login status " + isLoggedin());
      RootPanel.get("menu").add(new Label("Logg dich ein, um deinen Account zu bearbeiten"));
      RootPanel.get("menu").add(getLogoutlink());
      RootPanel.get("menu").add(getCreateCharacterlink());
      RootPanel.get("menu").add(getRegisterlink());
    }
  }

  public void setRegisterPanel(RegisterPanel registerPanel) {
    this.registerPanel = registerPanel;
  }

  private void setupHistory() {
    History.addHistoryListener(this);

  }

  private void validateSession() {

    SessionServiceAsync myServiceAsync = GWT.create(SessionService.class);

    AsyncCallback<SessionId> asyncCallback = new AsyncCallback<SessionId>() {
      public void onFailure(Throwable caught) {
        System.out.println(caught);
      }

      public void onSuccess(SessionId result) {
        // if(result != null &&
        // sessionId.getSessionId().equals(result.getSessionId())){
        System.out.println("Result " + result + " SessionID " + sessionId.getSessionId());
        loadWelcomePanel();

      }
    };
    myServiceAsync.session(sessionId, asyncCallback);
  }

  @Override
  public void onHistoryChanged(String historyToken) {

    if (LOGIN_STATE.equals(historyToken)) {
      loadLoginPanel();
    } else if (WELCOME_STATE.equals(historyToken)) {
      loadWelcomePanel();

    } else if (CREATE_STATE.equals(historyToken)) {
      if (isLoggedin()) {
        loadCreateCharacter();
      } else {
        loadWelcomePanel();
        showDialogBox("Fehlermeldung", "Benutzer ist nicht angemeldet.<BR><BR>Bitte erst anmelden");
        History.newItem("welcome");
      }
    } else if (REGISTER_STATE.equals(historyToken)) {
      loadRegisterPanel();
    }
  }

  public RegisterPanel getRegisterPanel() {
    if (registerPanel == null) {
      registerPanel = new RegisterPanel(this);
    }
    return registerPanel;
  }

  private CharakterPanel getcharacterPanel() {
    if (cpanel == null) {
      cpanel = new CharakterPanel(this);
    }
    return cpanel;
  }

  /**
   * loadRegisterPanel
   */
  public void loadCreateCharacter() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Charakter erstellen / bearbeiten"));
    RootPanel.get("content").add(getcharacterPanel());
  }

  /**
   * loadRegisterPanel
   */
  public void loadRegisterPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Benutzregistrierung"));
    RootPanel.get("content").add(getRegisterPanel());
  }

  public void loadLoginPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Bestehenden Account einloggen:"));
    /* getLoginPanel().setStylePrimaryName("loginpanel"); */
    RootPanel.get("content").add(getLoginPanel());
  }

  private LoginPanel getLoginPanel() {
    if (loginPanel == null) {
      loginPanel = new LoginPanel(sessionId);
      /*loginPanel.setTimadorusWebApp(this);*/
    }
    return loginPanel;
  }

  public Hyperlink getLogoutlink() {
    if (logoutlink == null) {
      logoutlink = new Hyperlink("login", LOGIN_STATE);
    }
    return logoutlink;
  }

  public Hyperlink getCreateCharacterlink() {
    if (createCharacterlink == null) {
      createCharacterlink = new Hyperlink("create Character", CREATE_STATE);

    }

    return createCharacterlink;
  }

  public Hyperlink getRegisterlink() {
    if (registerlink == null) {
      registerlink = new Hyperlink("register", REGISTER_STATE);
    }
    return registerlink;
  }

  /**
   * @param loggedin
   *          the loggedin to set
   */
  public void setLoggedin(boolean _loggedin) {
    this.loggedin = _loggedin;
  }

  /**
   * @return the loggedin
   */
  public boolean isLoggedin() {
    return loggedin;
  }

  public void showDialogBox(String title, String message) {
    // Create the popup dialog box
    final DialogBox dialogBox = new DialogBox();

    dialogBox.setText(title);
    dialogBox.setAnimationEnabled(true);
    final Button closeButton = new Button("Close");

    // We can set the id of a widget by accessing its Element
    closeButton.getElement().setId("closeButton");

    VerticalPanel dialogVPanel = new VerticalPanel();
    dialogVPanel.addStyleName("dialogVPanel");

    dialogVPanel.add(new HTML((new StringBuffer().append("<b>").append(message).append("</b>")).toString()));
    dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
    dialogVPanel.add(closeButton);
    dialogBox.setWidget(dialogVPanel);

    dialogBox.center();

    // Add a handler to close the DialogBox
    closeButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        dialogBox.hide();
      }
    });
  }
}
