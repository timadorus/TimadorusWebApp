package org.timadorus.webapp.client;

import org.timadorus.webapp.beans.User;
import org.timadorus.webapp.client.campaign.CreateCampaignPanel;
import org.timadorus.webapp.client.campaign.EditCampaignPanel;
import org.timadorus.webapp.client.character.TestCharacterValues;
import org.timadorus.webapp.client.character.ui.characterlist.ShowCharacterListDialog;
import org.timadorus.webapp.client.character.ui.createcharacter.CreateDialog;
import org.timadorus.webapp.client.character.ui.potstat.PotStatsDialog;
import org.timadorus.webapp.client.character.ui.premadecharacter.PremadeCharacterDialog;
import org.timadorus.webapp.client.character.ui.ready.ReadyDialog;
import org.timadorus.webapp.client.character.ui.selectclass.SelectClassDialog;
import org.timadorus.webapp.client.character.ui.selectfraction.SelectFactionDialog;
import org.timadorus.webapp.client.character.ui.selectname.SelectNameDialog;
import org.timadorus.webapp.client.character.ui.selectrace.SelectRaceDialog;
import org.timadorus.webapp.client.eventhandling.events.CreateCampaineEvent;
import org.timadorus.webapp.client.eventhandling.events.CreateCharacterEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowCharacterListEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowEditCampaignEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowLoginEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowLogoutEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowProfileEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowRegisterEvent;
import org.timadorus.webapp.client.eventhandling.events.ShowVerifyMailEvent;
import org.timadorus.webapp.client.eventhandling.handler.ShowLogoutHandler;
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
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

@SuppressWarnings("deprecation")
public class TimadorusWebApp implements EntryPoint, HistoryListener, DefaultTimadorusWebApp, ShowLogoutHandler {

  // SessionID
  private SessionId sessionId = new SessionId();

  private boolean loggedin = false;

  private boolean activationPage = false;

  private TestCharacterValues testValues;

  private MenuDialog menu = new MenuDialog(new MenuWidget());

  private HandlerManager eventBus;

  private LoginPanel loginPanel;

  private VerifyMailPanel verfiyMailPanel;

  private RegisterPanel registerPanel;

  private CreateCampaignPanel createCampaignPanel;

  private CreateDialog createCharacterDialog;

  private SelectRaceDialog selectRaceDialog;

  private ProfilePanel profilePanel;

  private ShowCharacterListDialog characterListDialog;

  private EditCampaignPanel editCampaignPanel;

  private PremadeCharacterDialog premadeCharacterDialog;

  private PotStatsDialog potStatsDialog;

  private ReadyDialog characterReadyDialog;

  private SelectClassDialog selectClassDialog;

  private SelectFactionDialog selectFactionDialog;
  
  private SelectNameDialog selectNameDialog;

  public TimadorusWebApp() {
    this.sessionId = new SessionId();

    menu.addLink(Role.GUEST, "Einloggen", HistoryStates.LOGIN_STATE.getStringRepresentation());
    menu.addLink(Role.USER, "Ausloggen", HistoryStates.LOGOUT_STATE.getStringRepresentation());
    menu.addLink(Role.USER, "Charakter erstellen", HistoryStates.CREATE_CHARACTER_STATE.getStringRepresentation());
    menu.addLink(Role.USER, "Liste der Charaktere", HistoryStates.CHARACTER_LIST_STATE.getStringRepresentation());
    menu.addLink(Role.GUEST, "Account registrieren", HistoryStates.REGISTER_STATE.getStringRepresentation());
    menu.addLink(Role.USER, "Profil bearbeiten", HistoryStates.PROFILE_STATE.getStringRepresentation());
    menu.addLink(Role.ADMIN, "Kampagne anlegen", HistoryStates.CREATE_CAMPAIGN_STATE.getStringRepresentation());
    menu.addLink(Role.GM, "Kampagne verwalten", HistoryStates.EDIT_CAMPAIGN_STATE.getStringRepresentation());

    this.loggedin = false;

    this.testValues = new TestCharacterValues();
  }

  public void onModuleLoad() {

    eventBus = new HandlerManager(this);

    eventBus.addHandler(ShowLogoutEvent.SHOWLOGOUT, this);

    loginPanel = LoginPanel.getLoginPanel(sessionId, this);
    verfiyMailPanel = VerifyMailPanel.getVerifyMailPanel(this);
    registerPanel = RegisterPanel.getRegisterPanel(this);
    createCampaignPanel = CreateCampaignPanel.getCampaignPanel(this, loginPanel.getUser());
    selectRaceDialog = SelectRaceDialog.getDialog(this, null, null);
    createCharacterDialog = CreateDialog.getCreateDialog(this, null);
    profilePanel = ProfilePanel.getProfilePanel(this, null);
    characterListDialog = ShowCharacterListDialog.getDialog(this, null);
    editCampaignPanel = EditCampaignPanel.getCampaignPanel(this, null);
    premadeCharacterDialog = PremadeCharacterDialog.getDialog(this, null);
    potStatsDialog = PotStatsDialog.getDialog(this);
    characterReadyDialog = ReadyDialog.getReadyDialog(this, null);
    selectClassDialog = SelectClassDialog.getSelecteddDialog(this, null, null);
    selectFactionDialog = new SelectFactionDialog(null, this, null, null);
    selectNameDialog = new SelectNameDialog(null, this, null, null);
    
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
    HistoryStates theHistoryState = HistoryStates.findByStringRepresentation(historyToken);
    if (theHistoryState != null) {
      switch (theHistoryState) {
      case LOGIN_STATE:
        eventBus.fireEvent(new ShowLoginEvent());
        break;
      case LOGOUT_STATE:
        eventBus.fireEvent(new ShowLogoutEvent());
        break;
      case PROFILE_STATE:
        loadProfilePanel();
        break;
      case WELCOME_STATE:
        loadWelcomePanel();
        break;
      case VERIFY_MAIL_STATE:
        if (isLoggedin()) {
          loadWelcomePanel();
          showDialogBox("Fehlermeldung", "Diese Seite kann nicht aufgerufen werden, wenn Sie zur Zeit angemeldet sind");
          History.newItem("welcome");
        } else {
          loadVerifyMailPanel();
        }
        break;
      case CREATE_CHARACTER_STATE:
        if (isLoggedin()) {
          loadCreateCharacterPanel();
        } else {
          loadWelcomePanel();
          showDialogBox("Fehlermeldung", "Benutzer ist nicht angemeldet.<BR><BR>Bitte erst anmelden");
          History.newItem("welcome");
        }
        break;
      case CHARACTER_LIST_STATE:
        if (isLoggedin()) {
          loadShowCharacterlistPanel();
        } else {
          loadWelcomePanel();
          showDialogBox("Fehlermeldung", "Benutzer ist nicht angemeldet.<BR><BR>Bitte erst anmelden");
          History.newItem("welcome");
        }
        break;
      case CREATE_CAMPAIGN_STATE:
        if (isLoggedin()) {
          loadCreateCampaignPanel();
        } else {
          loadWelcomePanel();
          showDialogBox("Fehlermeldung", "Benutzer ist nicht angemeldet.<BR><BR>Bitte erst anmelden");
          History.newItem("welcome");
        }
        break;
      case EDIT_CAMPAIGN_STATE:
        if (isLoggedin()) {
          loadEditCampaignPanel();
        } else {
          loadWelcomePanel();
          showDialogBox("Fehlermeldung", "Benutzer ist nicht angemeldet.<BR><BR>Bitte erst anmelden");
          History.newItem("welcome");
        }
        break;
      case REGISTER_STATE:
        loadRegisterPanel();
        break;
      default:
        // TODO - Any default-behavior? Maybe the welcome-panel!? (aaz214 - 15.04.2012)
      }
    }
    // TODO - Any default-behavior? Maybe the welcome-panel!? (aaz214 - 16.04.2012)
  }

  /**
   * A new ProfilePanel will be loaded and showed on the webpage.
   */
  private void loadProfilePanel() {

    User user = loginPanel.getUser();
    fireEvent(new ShowProfileEvent(user));
  }

  /**
   * A new CreateCharacterPanel will be loaded and showed on the webpage.
   */
  private void loadCreateCharacterPanel() {
    User user = loginPanel.getUser();
    fireEvent(new CreateCharacterEvent(user));
  }

  /**
   * A new CharacterListPanel will be loaded and showed on the webpage.
   */
  private void loadShowCharacterlistPanel() {
    User user = LoginPanel.getLoginPanel(sessionId, this).getUser();
    fireEvent(new ShowCharacterListEvent(user));
  }

  /**
   * A new CreateCampaignPanel will be loaded and showed on the webpage.
   */
  private void loadCreateCampaignPanel() {
    fireEvent(new CreateCampaineEvent(loginPanel.getUser()));
  }

  /**
   * A new EditCampaignPanel will be loaded and showed on the webpage.
   */
  private void loadEditCampaignPanel() {
    fireEvent(new ShowEditCampaignEvent(loginPanel.getUser()));
  }

  /**
   * A new RegisterPanel will be loaded and showed on the webpage.
   */
  private void loadRegisterPanel() {
    fireEvent(new ShowRegisterEvent());
  }

  /**
   * A new VerifyMailPanel will be loaded and showed on the webpage.
   */
  private void loadVerifyMailPanel() {
    fireEvent(new ShowVerifyMailEvent());
  }

  /*
   * (non-Javadoc)
   * 
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

  /*
   * (non-Javadoc)
   * 
   * @see org.timadorus.webapp.client.DefaultTimadorusWebApp#isLoggedin()
   */
  @Override
  public boolean isLoggedin() {
    return loggedin;
  }

  /*
   * (non-Javadoc)
   * 
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

  /*
   * (non-Javadoc)
   * 
   * @see org.timadorus.webapp.client.DefaultTimadorusWebApp#getTestValues()
   */
  @Override
  public TestCharacterValues getTestValues() {
    return testValues;
  }

  public void setTestValues(TestCharacterValues testValuesIn) {
    this.testValues = testValuesIn;
  }

  // public HandlerManager getEventBus() {
  // return eventBus;
  // }

  @Override
  public void showLogout() {
    loggedin = false;
    menu.resetUser();
  }

  @Override
  public <T extends EventHandler> void addHandler(GwtEvent.Type<T> type, final T handler) {
    eventBus.addHandler(type, handler);
  }

  public void fireEvent(GwtEvent<? extends EventHandler> event) {
    eventBus.fireEvent(event);
  }

}
