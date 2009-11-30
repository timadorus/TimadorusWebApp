package org.timadorus.webapp.client;

import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StartPanel extends VerticalPanel {
	private final TimadorusWebApp appReference;
	
	public StartPanel() throws Exception {
		throw new Exception("No parameter set");
	}
	
	public StartPanel(TimadorusWebApp _appReference) {
		super();
		this.appReference = _appReference;
		initialize();
	}
	
	private void initialize() {
		this.add(new HTML("<h2>Start</h2>"));
		if (this.appReference.getUserLoggedIn())
		{
			 this.add(new HTML("You are logged in as: "+this.appReference.getLoggedInUsername()));
		}else
			this.add(new HTML("Please login or register first."));
		
		
	}
}
