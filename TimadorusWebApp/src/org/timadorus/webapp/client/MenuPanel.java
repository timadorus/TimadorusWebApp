package org.timadorus.webapp.client;

import com.google.gwt.user.client.ui.Hyperlink;

import com.google.gwt.user.client.ui.VerticalPanel;

public class MenuPanel extends VerticalPanel {
	private final TimadorusWebApp appReference;
	
	public MenuPanel() throws Exception {
		throw new Exception("No parameter set");
	}
	
	public MenuPanel(TimadorusWebApp _appReference) {
		super();
		this.appReference = _appReference;
		initialize();
	}
	
	private void initialize() {
		this.add(new Hyperlink("Startpage", this.appReference.startState));
		
		if (!this.appReference.getUserLoggedIn()) {
			this.add(new Hyperlink("Login User", this.appReference.loginUserState));
			this.add(new Hyperlink("Register User", this.appReference.registerUserState));
		}
		
		if (this.appReference.getUserLoggedIn()) {
			this.add(new Hyperlink("Logout", this.appReference.logoutUserState));
			this.add(new Hyperlink("My Profile", this.appReference.showUserProfileState));
			this.add(new Hyperlink("My Toons", this.appReference.showUserToonsState));
			this.add(new Hyperlink("Create Toon", this.appReference.createToonState));
		}
	}
}
