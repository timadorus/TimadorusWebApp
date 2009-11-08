package org.timadorus.webapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;

public class TimadorusWebApp implements EntryPoint, HistoryListener {
	protected final String startState			= "start";
	protected final String loginUserState		= "loginUser";
	protected final String registerUserState	= "registerUser";
	protected final String createToonState		= "createToon";
	protected final String logoutUserState		= "logoutUser";
	protected final String showUserProfileState	= "showUserProfile";
	protected final String showUserToonsState	= "showUserToons";
	
	private final SessionId sessionId = new SessionId();
	
	private boolean userLoggedIn = false;
	private boolean toonCreateIn = false;
	
	private final UserServiceAsync userService	= GWT.create(UserService.class);
	private final ToonServiceAsync toonService	= GWT.create(ToonService.class);
	
	/**
	 * Returns if the user is logged in
	 * 
	 * @return true: user is logged in; false: user is not logged in 
	 */
	public Boolean getUserLoggedIn() {
		return this.userLoggedIn;
	}
	
	/**
	 * Displays an _errMessage on the Webapplication
	 * @param _errMessage The Message to be displayed on the Webapplication
	 */
	public void showError(String _errMessage) {
		RootPanel.get("error").clear();
		RootPanel.get("error").add(new HTML(_errMessage));
	}
	
	/**
	 * Returns the Username of the logged in user or NULL
	 * 
	 * @return Username of logged in User or NULL 
	 */
	public String getLoggedInUsername() {
		if (!this.getUserLoggedIn())
			return null;
		
		return "";
	}
	
	public void onModuleLoad() {
		History.addHistoryListener(this);
		
		History.onHistoryChanged(startState);
		
		System.out.println("Session " + sessionId.getSessionId());
		
		RootPanel.get("head").clear();
		RootPanel.get("head").add(new HTML("<h1>Timadorus Webapplication</h1>"));
		RootPanel.get("menu").clear();
		RootPanel.get("menu").add(new MenuPanel(this));
	}
	
	public void onHistoryChanged(String historyToken) {
		RootPanel.get("content").clear();
		RootPanel.get("error").clear();
		
		if (historyToken.equals(startState))
			RootPanel.get("content").add(new StartPanel(this));
		
		if (historyToken.equals(loginUserState))
			RootPanel.get("content").add(new LoginPanel(this));
		
		if (historyToken.equals(registerUserState))
			RootPanel.get("content").add(new RegisterUserPanel(this));
		
		if (historyToken.equals(createToonState))
			RootPanel.get("content").add(new CreateToonPanel(this));
		
		if (historyToken.equals(showUserProfileState))
			RootPanel.get("content").add(new ShowUserProfilePanel(this));
		
		if (historyToken.equals(showUserToonsState))
			RootPanel.get("content").add(new ShowUserToonsPanel(this));
		
		if (historyToken.equals(logoutUserState)) {
			this.userLoggedIn = false;
			this.onModuleLoad();
		}
	}
	
	public void isValidUserPasswordPair(String _userName, String _userPassword) {
		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {
				showError(caught.getCause().toString());
			}
			public void onSuccess(Boolean result) {
//				Login success
				if (result) {
					userLoggedIn = true;
					onModuleLoad();
//				Login failed
				} else {
					showError("Login failed");
				}
			}
		};
		this.userService.loginUserWithPassword(_userName, _userPassword, callback);
	}
	
	public void registerUser(User _userObj) {
		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {
				showError(caught.toString());
			}
			public void onSuccess(Boolean result) {
//				Login success
				if (result) {
					userLoggedIn = true;
					onModuleLoad();
//				Login failed
				} else {
					showError("Could not register new User");
				}
			}
		};
		this.userService.registerUser(_userObj, callback);
	}
	
	
	public void createToon(  Toon _toonObj){
		
		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {
				showError(caught.toString());
			}
			public void onSuccess(Boolean result) {
			
//				Login success
				if (result) {
					toonCreateIn= true;
					onModuleLoad();
//				Login failed
				} else {
					showError("Could not create new Toon");
				}
			}
		};
		
		this.toonService.createToon(_toonObj, callback);
	}
}