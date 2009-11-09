package org.timadorus.webapp.client;

import com.google.gwt.user.client.ui.FormPanel;

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
	
	private void initialize(){
	}

}
