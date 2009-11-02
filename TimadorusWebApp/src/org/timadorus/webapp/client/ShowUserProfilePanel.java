package org.timadorus.webapp.client;

import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;

public class ShowUserProfilePanel extends VerticalPanel {
	private final TimadorusWebApp appReference;
	
	public ShowUserProfilePanel() throws Exception {
		throw new Exception("No parameter set");
	}
	
	public ShowUserProfilePanel(TimadorusWebApp _appReference) {
		super();
		this.appReference = _appReference;
		initialize();
	}
	
	private void initialize() {
		if (this.appReference.getUserLoggedIn()) {
			final Grid showUserProfileGrid					= new Grid(5, 2);
			final TextBox firstnameTextBox					= new TextBox();
			final TextBox surnameTextBox					= new TextBox();
			final TextBox birthdayTextBox					= new TextBox();
			final TextBox emailTextBox						= new TextBox();
			final TextBox userNameTextBox					= new TextBox();
			
			showUserProfileGrid.setWidget(0, 0, new Label("Firstname"));
			showUserProfileGrid.setWidget(0, 1, firstnameTextBox);
			showUserProfileGrid.setWidget(1, 0, new Label("Surname"));
			showUserProfileGrid.setWidget(1, 1, surnameTextBox);
			showUserProfileGrid.setWidget(2, 0, new Label("Birthday"));
			showUserProfileGrid.setWidget(2, 1, birthdayTextBox);
			showUserProfileGrid.setWidget(3, 0, new Label("E-Mail"));
			showUserProfileGrid.setWidget(3, 1, emailTextBox);
			showUserProfileGrid.setWidget(4, 0, new Label("Username"));
			showUserProfileGrid.setWidget(4, 1, userNameTextBox);
			
			this.add(new HTML("<h2>My Profile</h2>"));
			this.add(showUserProfileGrid);
		}
	}
}
