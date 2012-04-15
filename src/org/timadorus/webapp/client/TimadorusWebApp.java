package org.timadorus.webapp.client;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.campaign.CreateCampaignPanel;
import org.timadorus.webapp.client.campaign.EditCampaignPanel;
import org.timadorus.webapp.client.character.TestCharacterValues;
import org.timadorus.webapp.client.character.ui.ShowCharacterlistPanel;
import org.timadorus.webapp.client.character.ui.createcharacter.CreateDialog;
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
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

@SuppressWarnings("deprecation")
public class TimadorusWebApp implements HistoryStates, EntryPoint, HistoryListener, DefaultTimadorusWebApp {

  // SessionID
  private SessionId sessionId = new SessionId();

  private boolean loggedin = false;

  private boolean activationPage = false;

  private TestCharacterValues testValues;

  private MenuDialog menu = new MenuDialog(new MenuWidget());

  public TimadorusWebApp() {
    this.sessionId = new SessionId();

    menu.addLink(Role.GUEST, "Einloggen", LOGIN_STATE);
    menu.addLink(Role.USER, "Ausloggen", LOGOUT_STATE);
    menu.addLink(Role.USER, "Charakter erstellen", CREATE_CHARACTER_STATE);
    menu.addLink(Role.USER, "Liste der Charaktere", CHARACTER_LIST_STATE);
    menu.addLink(Role.GUEST, "Account registrieren", REGISTER_STATE);
    menu.addLink(Role.USER, "Profil bearbeiten", PROFILE_STATE);
    menu.addLink(Role.ADMIN, "Kampagne anlegen", CREATE_CAMPAIGN_STATE);
    menu.addLink(Role.GM, "Kampagne verwalten", EDIT_CAMPAIGN_STATE);

    this.loggedin = false;

    this.testValues = new TestCharacterValues();
  }

  public void onModuleLoad() {

    menu.go(RootPanel.get("menu"));

    FlowPanel vp = new FlowPanel();
    vp.setStylePrimaryName("contentPanel");
    RootPanel.get("content").add(vp);

    vp = new FlowPanel();
    vp.setStylePrimaryName("informationPanel");
    RootPanel.get("information").add(vp);

    sessionId.setSessionId(Cookies.getCookie("session"));

    if (Window.Location.getParameter("activationCode") != null) {
      activationPage = true;
    }

    History.fireCurrentHistoryState();
    System.out.println("Session " + sessionId.getSessionId());
    setupHistory();
    loadWelcomePanel();
    validateSession();
  }

  private void loadWelcomePanel() {
    RootPanel.get("content").clear();
    RootPanel.get("information").clear();
    RootPanel.get("information").add(new HTML("</br>information panel"));
    RootPanel.get("content").add(new HTML("Willkommen auf der WebApplikation des Projektes Timadorus"));

  }

  public DefaultTimadorusWebApp getTimadorusWebApp() {
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
        showDialogBox("Fehlermeldung", "Diese Seite kann nicht aufgerufen werden, wenn Sie zur Zeit angemeldet sind");
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
    } else if (EDIT_CAMPAIGN_STATE.equals(historyToken)) {
      if (isLoggedin()) {
        loadEditCampaignPanel();
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
  private void loadCreateCharacterPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Charakter erstellen / bearbeiten"));
    User user = LoginPanel.getLoginPanel(sessionId, this).getUser();
    RootPanel.get("content").add(CreateDialog.getCreateDialog(this, user).getFormPanel());
  }

  /**
   * A new CharacterListPanel will be loaded and showed on the webpage.
   */
  private void loadShowCharacterlistPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Liste der registrierten Charaktere"));
    RootPanel.get("content").add(ShowCharacterlistPanel.getShowCharacterlistPanel(this,
                                                                                  LoginPanel.getLoginPanel(sessionId,
                                                                                                           this)
                                                                                      .getUser()));
  }

  /**
   * A new CreateCampaignPanel will be loaded and showed on the webpage.
   */
  private void loadCreateCampaignPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Kampagne erstellen"));
    RootPanel.get("content").add(CreateCampaignPanel.getCampaignPanel(this, LoginPanel.getLoginPanel(sessionId, this)
                                     .getUser()));
  }

  /**
   * A new EditCampaignPanel will be loaded and showed on the webpage.
   */
  private void loadEditCampaignPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Kampagne verwalten"));
    RootPanel.get("content").add(EditCampaignPanel.getCampaignPanel(this, LoginPanel.getLoginPanel(sessionId, this)
                                     .getUser()));
  }

  /**
   * A new RegisterPanel will be loaded and showed on the webpage.
   */
  private void loadRegisterPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Benutzregistrierung"));
    RootPanel.get("content").add(RegisterPanel.getRegisterPanel(this));
  }

  /**
   * A new VerifyMailPanel will be loaded and showed on the webpage.
   */
  private void loadVerifyMailPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("E-Mail bestätigen"));
    RootPanel.get("content").add(VerifyMailPanel.getVerifyMailPanel(this));
  }

  /**
   * A new LoginPanel will be loaded and showed on the webpage.
   */
  private void loadLoginPanel() {
    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("In bestehenden Account einloggen:"));
    RootPanel.get("content").add(LoginPanel.getLoginPanel(sessionId, this));
  }

  /**
   * A new LogoutPanel will be loaded and showed on the webpage.
   */
  private void loadLogoutPanel() {

    RootPanel.get("content").clear();
    RootPanel.get("content").add(new Label("Sie haben sich ausgeloggt. Unten haben sie die Möglichkeit, sich wieder "
                                     + "einzuloggen:"));
    /* getLoginPanel().setStylePrimaryName("loginpanel"); */

    RootPanel.get("content").add(LoginPanel.getLoginPanel(new SessionId(), new TimadorusWebApp()));
    loggedin = false;
    menu.resetUser();

    RootPanel.get("information").clear();
  }

  /* (non-Javadoc)
   * @see org.timadorus.webapp.client.DefaultTimadorusWebApp#setLoggedin(boolean)
   */
  @Override
  public void setLoggedin(boolean loggedinIn) {
    this.loggedin = loggedinIn;
    if (loggedin) {
      menu.setUser(LoginPanel.getLoginPanel(sessionId, this).getUser());
    } else {
      menu.resetUser();
    }
  }

  /* (non-Javadoc)
   * @see org.timadorus.webapp.client.DefaultTimadorusWebApp#isLoggedin()
   */
  @Override
  public boolean isLoggedin() {
    return loggedin;
  }

  /* (non-Javadoc)
   * @see org.timadorus.webapp.client.DefaultTimadorusWebApp#showDialogBox(java.lang.String, java.lang.String)
   */
  @Override
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

  /* (non-Javadoc)
   * @see org.timadorus.webapp.client.DefaultTimadorusWebApp#getTestValues()
   */
  @Override
  public TestCharacterValues getTestValues() {
    return testValues;
  }

  public void setTestValues(TestCharacterValues testValuesIn) {
    this.testValues = testValuesIn;
  }
}
