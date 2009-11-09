package org.timadorus.webapp.client;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;

public class ShowUserToonsPanel extends VerticalPanel {
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
			int rows = 1 / 3;
			final Grid showUserToons = new Grid(5, 3);
			
			//for (Toon toonOfUser : ToonSet)
			VerticalPanel tempPanel = new VerticalPanel();
			tempPanel.add(new HTML("<h3>"+toonOfUser.getToonName()+"</h3>"));
			tempPanel.add(new HTML("Gender: "+toonOfUser.getGender()));
			tempPanel.add(new HTML("Race:"+toonOfUser.getRace()));
			tempPanel.add(new HTML("Fraction:"+toonOfUser.getFraction()));
			tempPanel.add(new HTML("Proffesion:"+toonOfUser.getProffesion()));
			
			showUserToons.setWidget(1, 1, new Label(""));
			
			this.add(new HTML("<h2>My Toons</h2>"));
			this.add(showUserToons);
		}
	}
}
