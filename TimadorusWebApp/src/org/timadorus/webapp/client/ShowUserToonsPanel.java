package org.timadorus.webapp.client;

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
			int rows	= ((Double) ((this.appReference.getToonsOfLoggedInUser().size() / 3.0) + 1.0)).intValue();
			int row		= 0;
			int col		= 0;
			
			final Grid showUserToons = new Grid(rows, 3);
			
			VerticalPanel tempPanel;
			for (Toon toonOfUser : this.appReference.getToonsOfLoggedInUser()) {
				if (col>2) {col = 0; row++;}
				
				System.out.println(toonOfUser);
				
				tempPanel = new VerticalPanel();
				tempPanel.setStyleName("toonOfUserSmall");
				tempPanel.add(new HTML("<h3>"+toonOfUser.getName()+"</h3>"));
				tempPanel.add(new HTML("<b>Gender:</b> "+toonOfUser.getGender()));
				tempPanel.add(new HTML("<b>Race:</b> "+toonOfUser.getRace()));
				tempPanel.add(new HTML("<b>Fraction:</b> "+toonOfUser.getFraction()));
				tempPanel.add(new HTML("<b>Proffesion:</b> "+toonOfUser.getProffesion()));
				showUserToons.setWidget(row, col++, tempPanel);
				
			}
			
			this.add(new HTML("<h2>My Toons</h2>"));
			this.add(showUserToons);
		}
	}
}
