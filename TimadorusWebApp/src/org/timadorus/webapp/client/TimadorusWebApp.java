package org.timadorus.webapp.client;

import java.util.HashSet;
import java.util.Set;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.HistoryListener;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;

public class TimadorusWebApp implements EntryPoint, HistoryListener {
	protected final String startState			= "start";
	protected final String loginUserState		= "loginUser";
	protected final String registerUserState	= "registerUser";
	protected final String createToonState		= "createToon";
	protected final String logoutUserState		= "logoutUser";
	protected final String showUserProfileState	= "showUserProfile";
	protected final String showUserToonsState	= "showUserToons";
	protected final String setToonState         = "setToonFeacture";

	//protected final String showToonsState		= "showToons";

	private final SessionId sessionId = new SessionId();

	private boolean userLoggedIn = false;
	private int toonCreateIn = 0;


	private final UserServiceAsync userService	= GWT.create(UserService.class);
	private final ToonServiceAsync toonService	= GWT.create(ToonService.class);

	private User loggedInUserObject			= null;
	private  Toon toonToCreateInUserObject=null;
	private Set<Toon> toonsOfLoggedInUser	= new HashSet<Toon>();

	/**
	 * Returns if the user is logged in
	 * 
	 * @return true: user is logged in; false: user is not logged in 
	 */
	public Boolean getUserLoggedIn() {
		return this.userLoggedIn;
	}

	/**
	 * 
	 */

	public Toon getToonToCreateInUserObject() {
		return this.toonToCreateInUserObject;
	}

	public User getLoggedInUserObject() {
		return this.loggedInUserObject;
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
		return this.loggedInUserObject.getUserName();
	}

	public String getToonName() {
		return this.toonToCreateInUserObject.getName();
	}



	/**
	 * 
	 */
	public void onModuleLoad()
	{
		History.addHistoryListener(this);

		History.onHistoryChanged(startState);

		System.out.println("Session " + sessionId.getSessionId());

		RootPanel.get("head").clear();
		RootPanel.get("head").add(new HTML("<h2>Timadorus Webapplication</h2>"));
		RootPanel.get("menu").clear();
		RootPanel.get("menu").add(new MenuPanel(this));
	}

	/**
	 * 
	 */
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

		if(historyToken.equals(setToonState))
			RootPanel.get("content").add(new SetToonStatePanel(this));
		//RootPanel.get("content").add(new HTML("Please  complet to create your Toon"));

		if (historyToken.equals(showUserProfileState))
			RootPanel.get("content").add(new ShowUserProfilePanel(this));

		if (historyToken.equals(showUserToonsState))
		{
			this.getToonsOfUser(this.getLoggedInUsername());
			RootPanel.get("content").add(new ShowUserToonsPanel(this));

		}



		if (historyToken.equals(logoutUserState)) {
			this.userLoggedIn = false;
			this.loggedInUserObject = null;
			this.toonsOfLoggedInUser = new HashSet<Toon>();
			this.onModuleLoad();
		}
	}

	/**
	 * 
	 * @param _userName
	 * @param _userPassword
	 */
	public void isValidUserPasswordPair(final String _userName, final String _userPassword) {
		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>() {
			public void onFailure(Throwable caught) {
				showError(caught.getCause().toString());
			}
			public void onSuccess(Boolean result) {
				//				Login success
				if (result) {
					userLoggedIn = true;
					getUserInformation(_userName);
					onModuleLoad();
					//				Login failed
				} else {
					loggedInUserObject = null;
					showError("Login failed");
				}
			}
		};
		this.userService.loginUserWithPassword(_userName, _userPassword, callback);
	}

	/**
	 * 
	 * @param _userObj
	 */
	public void registerUser(final User _userObj) {
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

	/**
	 * 
	 * @param _toonObj
	 */
	public void createToon(final Toon _toonObj) {
		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>(){

			public void onFailure(Throwable caught) {
				showError(caught.toString());
				System.out.println(caught.toString());
			}
			public void onSuccess(Boolean result) {			
				//				Login success
				if (result)
				{
					toonCreateIn= 1;
					onModuleLoad();
					toonToCreateInUserObject= _toonObj;

				}else{
					showError(" could'not created  new Toon!");
					toonCreateIn=-1;
				}
			}
		};

		this.toonService.createToon(_toonObj, callback);

	}



	public void setToonFeacture(final Toon toonObj) {
		AsyncCallback<Boolean> callback = new AsyncCallback<Boolean>(){


			public void onFailure(Throwable caught) {
				showError(caught.toString());
				System.out.println(caught.toString());
			}
			public void onSuccess(Boolean result) {			

				if (result)
				{
					if (toonCreateIn==1)
					{
						toonCreateIn=2;
						onModuleLoad();
						toonToCreateInUserObject = toonObj ;
						//getToonInformation(toonObj.getName());
					}

				}else{
					showError("could'not set Toon Features !");
				}
			}
		};

		this.toonService.createToon(toonObj, callback);//???
	}

	public void getToonInformation( final String toonname) {
		AsyncCallback<Toon> callback = new AsyncCallback<Toon>() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				showError(caught.getLocalizedMessage());
			}
			public void onSuccess(Toon result) {
				toonToCreateInUserObject= new Toon( toonname);
				toonToCreateInUserObject.setGender(result.getGender());
				System.out.println("WWWWWWWWWWWWW"+ result.getGender());
				toonToCreateInUserObject.setRace(result.getRace());
				toonToCreateInUserObject.setFraction(result.getFraction());
				toonToCreateInUserObject.setProffesion(result.getProffesion());
				onModuleLoad();
			}
		};
		this.toonService.getToonInformation(toonname, callback);
	}

	/**
	 * 
	 * @param _userName
	 */
	public void getUserInformation(final String _userName) {
		AsyncCallback<User> callback = new AsyncCallback<User>() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				showError(caught.getLocalizedMessage());
			}
			public void onSuccess(User result) {
				loggedInUserObject = new User(_userName);
				loggedInUserObject.setBirthday(result.getBirthday());
				loggedInUserObject.setEmail(result.getEmail());
				loggedInUserObject.setFirstname(result.getFirstname());
				loggedInUserObject.setSurname(result.getSurname());
				onModuleLoad();
			}
		};
		this.userService.getUserInformation(_userName, callback);
	}

	/**
	 * 
	 * @param _userName
	 */

	public void getToonsOfUser(final String _userName) {
		AsyncCallback<Set<Toon>> callback = new AsyncCallback<Set<Toon>>() {
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				showError(caught.getLocalizedMessage());
			}
			public void onSuccess(Set<Toon> result)
			{
				for (Toon tmp : result)					
				toonsOfLoggedInUser.add(tmp);
			}
		};
		this.toonService.getToonsOfUser(_userName, callback);
	}


	public int getToonCreateIn() {
		return toonCreateIn;
	}


	/**
	 * 
	 * @return
	 */
	public Set<Toon> getToonsOfLoggedInUser() {
		return this.toonsOfLoggedInUser;
	}
}