package org.timadorus.webapp.client;

import org.timadorus.webapp.client.campaign.CreateCampaignPanel;
import org.timadorus.webapp.client.character.CreateCharacterPanel;
import org.timadorus.webapp.client.character.ShowCharacterlistPanel;
import org.timadorus.webapp.client.character.TestCharacterValues;
import org.timadorus.webapp.client.login.LoginPanel;
import org.timadorus.webapp.client.profile.ProfilePanel;
import org.timadorus.webapp.client.register.RegisterPanel;
import org.timadorus.webapp.client.rpc.service.SessionService;
import org.timadorus.webapp.client.rpc.service.SessionServiceAsync;
import org.timadorus.webapp.client.verify_mail.VerifyMailPanel;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlowPanel;

@SuppressWarnings("deprecation")
public class TimadorusWebApp implements HistoryStates, EntryPoint, HistoryListener {

  private static final long serialVersionUID = -5138823406762920058L;

  // SessionID
  private SessionId sessionId = new SessionId();

  // Hyperlinks für die Startseite
  private Hyperlink logoutlink;
  private Hyperlink loginlink;
  private Hyperlink createCharacterlink;
  private Hyperlink showCharacterlistLink;
  private Hyperlink registerlink;
  private Hyperlink profilelink;
  private Hyperlink createCampaignLink;

  private boolean loggedin = false;
  private boolean activationPage = false;
  
  public  TestCharacterValues testValues;
  
  
  public TimadorusWebApp() {
    this.sessionId              = new SessionId();
    
    this.loginlink              = new Hyperlink("Einloggen", LOGIN_STATE);
    this.logoutlink             = new Hyperlink("Ausloggen", LOGOUT_STATE);
    this.createCharacterlink    = new Hyperlink("Charakter erstellen", CREATE_CHARACTER_STATE);
    this.showCharacterlistLink  = new Hyperlink("Liste der Charaktere", CHARACTER_LIST_STATE);
    this.registerlink           = new Hyperlink("Account registrieren", REGISTER_STATE); 
    this.profilelink            = new Hyperlink("Profil bearbeiten", PROFILE_STATE);
    this.createCampaignLink     = new Hyperlink("Kampagne anlegen", CREATE_CAMPAIGN_STATE);

    this.loggedin = false;
    
    this.testValues = new TestCharacterValues();
  }

  public void onModuleLoad() {

    FlowPanel vp = new FlowPanel();
    vp.setStylePrimaryName("menuPanel");
    RootPanel.get("menu").add(vp);
    

    FlowPanel vp1 = new FlowPanel();
    vp1.setStylePrimaryName("contentPanel");
    RootPanel.get("content").add(vp1);

    FlowPanel vp2 = new FlowPanel();
    vp2.setStylePrimaryName("informationPanel");
    RootPanel.get("information").add(vp2);

    sessionId.setSessionId(Cookies.getCookie("session"));

    if (Window.Location.getParameter("activationCode") != null) {
      activationPage = true;
    }

    History.onHistoryChanged("welcome");
    System.out.println("Session " + sessionId.getSessionId());
    setupHistory();
    loadWelcomePanel();
    validateSession();
  }

  public void loadWelcomePanel() {
    RootPanel.get("menu").clear();
    RootPanel.get("content").clear();
    RootPanel.get("information").clear();
    RootPanel.get("information").add(new HTML("</br>information panel"));
    RootPanel.get("content").add(new HTML("Willkommen auf der WebApplikation des Projektes Timadoros"));
    

    if (isLoggedin()) {
      System.out.println("Login status " + isLoggedin());
      RootPanel.get("menu").add(new Label("Du bist als "
                                          + LoginPanel.getLoginPanel(sessionId, this).getUser().getDisplayname()
                                          + " angemeldet"));
      if (!LoginPanel.getLoginPanel(sessionId, this).getUser().getActive()) {
        RootPanel.get("menu").add(new Label("Dein Account wurde noch nicht aktiviert"));
      }
      RootPanel.get("menu").add(getCreateCharacterlink());
      RootPanel.get("menu").add(getCreateCampaignLink());
      RootPanel.get("menu").add(getShowCharacterlistLink());
      RootPanel.get("menu").add(getProfilelink());
      //RootPanel.get("menu").add(getRegisterlink());
      RootPanel.get("menu").add(getLogoutlink());
    } else {
      
      System.out.println("Login status " + isLoggedin());
      RootPanel.get("menu").add(new Label("Logg dich ein, um deinen Account zu bearbeiten"));
      RootPanel.get("menu").add(getLoginlink());
      RootPanel.get("menu").add(getRegisterlink());
    }
  }

  public TimadorusWebApp getTimadorusWebApp() {
    return this;
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
        System.out.println("Result " + result + " SessionID " + sessionId.getSessionId());
        
        if (activationPage) {
          loadVerifyMailPanel();
        } else {
          loadWelcomePanel();
        }

      }
    };
    myServiceAsync.session(sessionId, asyncCallback);
  }

  @Override
  public void onHistoryChanged(String historyToken) {
    if (LOGIN_STATE.equals(historyToken)) {
      loadLoginPanel();
    } else if (LOGOUT_STATE.equals(historyToken)) {
      loadLogoutPanel();
    } else if (PROFILE_STATE.equals(historyToken)) {
      loadProfilePanel();      
    } else if (WELCOME_STATE.equals(historyToken)) {
      loadWelcomePanel();
    } else if (VERIFY_MAIL_STATE.equals(historyToken)) {
      if (isLoggedin()) {
        loadWelcomePanel();
        showDialogBox("Fehlermeldung", 
                      "Diese Seite kann nicht aufgerufen werden, wenn Sie zur Zeit angemeldet sind");
        History.newItem("welcome");
      } else {
        loadVerifyMailPanel();
      }
    } else if (CREATE_CHARACTER_STATE.equals(historyToken)) {
      if (isLoggedin()) {
        loadCreateCharacterPanel();
      } else {
        loadWelcomePanel();
        showDialogBox("Fehlermeldung", "Benutzer ist nicht angemeldet.<BR><BR>Bitte erst anmelden");
        History.newItem("welcome");
      }
    } else if (CHARACTER_LIST_STATE.equals(historyToken)) {
      if (isLoggedin()) {
        loadShowCharacterlistPanel();
      } else {
        loadWelcomePanel();
        showDialogBox("Fehlermeldung", "Benutzer ist nicht angemeldet.<BR><BR>Bitte erst anmelden");
        History.newItem("welcome");
      }
    } else if (CREATE_CAMPAIGN_STATE.equals(historyToken)) {
      if (isLoggedin()) {
        loadCreateCampaignPanel();
      } else {
        loadWelcomePanel();
        showDialogBox("Fehlermeldung", "Benutzer ist nicht angemeldet.<BR><BR>Bitte erst anmelden");
        History.newItem("welcome");
      }
    } else if (REGISTER_STATE.equals(historyToken)) {
      loadRegisterPanel();
    }
  }

  /**
   * A new ProfilePanel will be loaded and showed on the webpage.
   */
  private void loadProfilePanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Profil bearbeiten"));
    RootPanel.get("content").add(ProfilePanel
                                 .getProfilePanel(this, LoginPanel.getLoginPanel(sessionId, this).getUser()));
  }

  /**
   * A new CreateCharacterPanel will be loaded and showed on the webpage.
   */
  public void loadCreateCharacterPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Charakter erstellen / bearbeiten"));
    RootPanel.get("content").add(CreateCharacterPanel.getCharacterPanel(this, LoginPanel.
                                                                  getLoginPanel(sessionId, this).getUser()));
  }
  
  /**
   * A new CharacterListPanel will be loaded and showed on the webpage.
   */
  public void loadShowCharacterlistPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Liste der registrierten Charaktere"));
    RootPanel.get("content").add(ShowCharacterlistPanel.getShowCharacterlistPanel(this, LoginPanel.
                                                                  getLoginPanel(sessionId, this).getUser()));
  }
  
  /**
   * A new CreateCharacterPanel will be loaded and showed on the webpage.
   */
  public void loadCreateCampaignPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Kampagne erstellen"));
    RootPanel.get("content").add(CreateCampaignPanel.getCampaignPanel(this, LoginPanel.
                                                                  getLoginPanel(sessionId, this).getUser()));
  }

  /**
   * A new RegisterPanel will be loaded and showed on the webpage.
   */
  public void loadRegisterPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Benutzregistrierung"));
    RootPanel.get("content").add(RegisterPanel.getRegisterPanel(this));
  }
  
  /**
   * A new VerifyMailPanel will be loaded and showed on the webpage.
   */
  public void loadVerifyMailPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("E-Mail bestätigen"));
    RootPanel.get("content").add(VerifyMailPanel.getVerifyMailPanel(this));
  }

  /**
   * A new LoginPanel will be loaded and showed on the webpage.
   */
  public void loadLoginPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("In bestehenden Account einloggen:"));
    RootPanel.get("content").add(LoginPanel.getLoginPanel(sessionId, this));
  }
  
  /**
   * A new LogoutPanel will be loaded and showed on the webpage.
   */
  public void loadLogoutPanel() {

    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Sie haben sich ausgeloggt. Unten haben sie die Möglichkeit, sich wieder " 
                                           + "einzuloggen:"));
    /* getLoginPanel().setStylePrimaryName("loginpanel"); */
    
    RootPanel.get("content").add(LoginPanel.getLoginPanel(new SessionId(), new TimadorusWebApp()));
    loggedin = false;
    System.out.println("Login status " + isLoggedin());
    RootPanel.get("menu").clear();
    RootPanel.get("menu").add(new Label("Logg dich ein, um deinen Account zu bearbeiten"));
    RootPanel.get("menu").add(getLoginlink());
    RootPanel.get("menu").add(getCreateCharacterlink());
    RootPanel.get("menu").add(getRegisterlink());
    RootPanel.get("information").clear();
  }

  public Hyperlink getLogoutlink() {
    if (logoutlink == null) {
      logoutlink = new Hyperlink("logout", LOGOUT_STATE);
    }
    return logoutlink;
  }
  
  public Hyperlink getProfilelink() {
    if (profilelink == null) {
      profilelink = new Hyperlink("profile", PROFILE_STATE);
    }
    return profilelink;
  }

  public Hyperlink getLoginlink() {
    if (loginlink == null) {
      loginlink = new Hyperlink("login", LOGIN_STATE);
    }
    return loginlink;
  }

  public Hyperlink getCreateCharacterlink() {
    if (createCharacterlink == null) {
      createCharacterlink = new Hyperlink("create Character", CREATE_CHARACTER_STATE);
    }
    return createCharacterlink;
  }
  
  public Hyperlink getCreateCampaignLink() {
    if (createCampaignLink == null) {
      createCampaignLink = new Hyperlink("create Campaign", CREATE_CAMPAIGN_STATE);
    }
    return createCampaignLink;
  }

  public Hyperlink getShowCharacterlistLink() {
    if (showCharacterlistLink == null) {
      showCharacterlistLink = new Hyperlink("Liste der Charaktere", CHARACTER_LIST_STATE);
    }
    return showCharacterlistLink;
  }

  public Hyperlink getRegisterlink() {
    if (registerlink == null) {
      registerlink = new Hyperlink("register", REGISTER_STATE);
    }
    return registerlink;
  }

  /**
   * @param loggedinIn
   *          the loggedin to set
   */
  public void setLoggedin(boolean loggedinIn) {
    this.loggedin = loggedinIn;
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

  public  TestCharacterValues getTestValues() {
    return testValues;
  }

  public void setTestValues(TestCharacterValues testValuesIn) {
    this.testValues = testValuesIn;
  }
}
