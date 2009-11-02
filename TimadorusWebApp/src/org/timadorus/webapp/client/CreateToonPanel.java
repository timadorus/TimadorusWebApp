package org.timadorus.webapp.client;

import com.google.gwt.user.client.ui.VerticalPanel;

public class CreateToonPanel extends VerticalPanel {
	private final TimadorusWebApp appReference;
	
	public CreateToonPanel() throws Exception {
		throw new Exception("No parameter set");
	}
	
	public CreateToonPanel(TimadorusWebApp _appReference) {
		super();
		this.appReference = _appReference;
		initialize();
	}
	
	private void initialize() {
	}
}
