package org.timadorus.webapp.client;

import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class ShowUserToonsPanel extends FormPanel {
	private final TimadorusWebApp appReference;
	
	public ShowUserToonsPanel() throws Exception {
		throw new Exception("No parameter set");
	}
	
	public ShowUserToonsPanel(TimadorusWebApp _appReference) {
		super();
		this.appReference = _appReference;
		initialize();
	}
	
	private void initialize() {
		if(this.appReference.getUserLoggedIn()) {
			final Grid showUserProfileGrid					= new Grid(5, 2);
			
			this.add(new HTML("<h2>My Profile</h2>"));
			this.add(showUserProfileGrid);
		}
	}
}
