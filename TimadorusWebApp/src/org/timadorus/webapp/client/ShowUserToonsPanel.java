package org.timadorus.webapp.client;

import java.util.HashSet;
import java.util.Set;

import javax.jdo.annotations.Serialized;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;

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
	
	@Serialized
	private void initialize() {
		if(this.appReference.getUserLoggedIn()) {
//			int rows	= (this.appReference.getToonsOfLoggedInUser().size() / 3.0) + 1;
			int row		= 0;
			
			final Grid showUserToons = new Grid(this.appReference.getToonsOfLoggedInUser().size(), 1);
			
			VerticalPanel tempPanel;
			for (Toon toonOfUser : this.appReference.getToonsOfLoggedInUser()) {
				
				System.out.println(toonOfUser);
				
				tempPanel = new VerticalPanel();
				tempPanel.setStylePrimaryName("toonOfUserSmall");
				tempPanel.add(new HTML("<h3>"+toonOfUser.getName()+"</h3>"));
				tempPanel.add(new HTML("<b>Gender:</b> "+toonOfUser.getGender()));
				tempPanel.add(new HTML("<b>Race:</b> "+toonOfUser.getRace()));
				tempPanel.add(new HTML("<b>Fraction:</b> "+toonOfUser.getFraction()));
				tempPanel.add(new HTML("<b>Proffesion:</b> "+toonOfUser.getProffesion()));
				showUserToons.setWidget(row++, 0, tempPanel);
				
			}
			
			this.add(new HTML("<h2>My Toons</h2>"));
			this.add(showUserToons);
		}
	}
}
