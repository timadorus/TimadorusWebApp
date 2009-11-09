package org.timadorus.webapp.client;

import java.util.HashSet;
import java.util.Set;

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
			
			
			Set<Toon> toonSet = new HashSet<Toon>();
			this.appReference.getToonsOfUser(this.appReference.getLoggedInUsername());
			
			VerticalPanel tempPanel;
			for (Toon toonOfUser : toonSet) {
				tempPanel = new VerticalPanel();
				tempPanel.setStylePrimaryName("toonOfUserSmall");
				tempPanel.add(new HTML("<h3>"+toonOfUser.getName()+"</h3>"));
				tempPanel.add(new HTML("<b>Gender:</b> "+toonOfUser.getGender()));
				tempPanel.add(new HTML("<b>Race:</b> "+toonOfUser.getRace()));
				tempPanel.add(new HTML("<b>Fraction:</b> "+toonOfUser.getFraction()));
				tempPanel.add(new HTML("<b>Proffesion:</b> "+toonOfUser.getProffesion()));
				showUserToons.setWidget(1, 1, tempPanel);
			}
			
			this.add(new HTML("<h2>My Toons</h2>"));
			this.add(showUserToons);
		}
	}
}
